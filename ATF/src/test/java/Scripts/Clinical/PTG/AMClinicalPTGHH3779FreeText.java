package Scripts.Clinical.PTG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import components.Browser;
import components.Report;
import components.Waits;

public class AMClinicalPTGHH3779FreeText {
	
	/************************************************************************************
	 * 'Class name : AM_ClinicalPTG_HH3779 'JIRA ID : HH-3779 'Description :
	 * Verify that the new goal modal is working
	 * Parameters : 'Output Parameters : 'Assumptions : 'Use : The following
	 * test verifies that the user is able select a goal to assign a Progress to Goal
	 * 'Tags : Functional
	 ************************************************************************************/

	@Test
	public static void Test() throws Exception {

		String Url = "https://staging.kinnser.net/EHR/#/AM/progress-to-goal/unmet-goals/patienttask/337975536";
		
		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		Report.generateReportsFile("html", "AM_Patient_HH3779FreeText");
		Report.SetTestName("AM_Patient_HH3779","To Verify User can able to add a free text goal from the modal");
		Report.assignCategory("PTG");

		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		// @Activate PTG
		WebDriver newDriver = AM.ReportsAdmin.ReportsAdmin.turnOnPTG(driver);
		newDriver.navigate().to(Url);
		
		// @@Step :
		try{
			Waits.ForElementVisibility(newDriver, newDriver.findElement(By.id("unmet-goal-container")));
			AM.PTG.ProgressUnmet.selectFreeTextGoal(newDriver);
			Waits.ForElementVisibility(newDriver, newDriver.findElement(By.className("goal-lower-well-first-view")));
			Report.attachScreenShotToReport(newDriver);
			AM.PTG.ProgressUnmet.getChkByEndEpisode(newDriver, 1).click();
			AM.PTG.ProgressUnmet.getTxtInstructions(newDriver, 1).sendKeys("Adding instruction text");
			AM.PTG.ProgressUnmet.getBtnAssignGoal(newDriver, 1).click();
			Report.Log(Status.PASS, "Goal Free Text assigned.");
			Report.attachScreenShotToReport(newDriver);
			Assert.assertTrue(true);
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue adding a new free text goal");
			Assert.fail("Failed adding a new free text Goal");
			e.printStackTrace();
		}
	}
	@AfterTest(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}
}
