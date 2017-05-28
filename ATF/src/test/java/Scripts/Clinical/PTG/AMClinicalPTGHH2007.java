package Scripts.Clinical.PTG;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import AM.PTG.Goal;
import components.Browser;
import components.Report;
import components.Waits;

public class AMClinicalPTGHH2007 {
	/************************************************************************************
	 * 'Class name : AM_Clinical_PTG_HH2007 'JIRA ID : HH-2007 'Description :
	 * Verify that A New Goal is added to the Library
	 * Parameters : 'Output Parameters : 'Assumptions : 'Use : The following
	 * test verifies that the user is able to add a Goal to the Library
	 * 'Tags : Functional
	 ************************************************************************************/

	@Test
	public void Test() throws Exception {
		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		Report.generateReportsFile("html", "AM_Patient_HH3154");
		Report.SetTestName("AM_Patient_HH3154",
				"Verify that A New Goal is added to the Library");
		Report.assignCategory("PTG");

		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		String goalName = "New Goal"+ RandomStringUtils.randomAlphanumeric(5);
		String goalDiscipline = "SN";
		String goalProblem = "Supportive Assistance";
		String goalInstruction = "Instruction for goal " + goalName;
		String successMesage = "The Goal '" + goalName + "' was saved successfully.";
		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		// @Activate PTG
		WebDriver newDriver = AM.ReportsAdmin.ReportsAdmin.turnOnPTG(driver);
		AM.Menu.TopMenu.Select(newDriver, "Go To/Reports/Admin");
		AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(newDriver).click();
		Goal goalPage = new Goal();
		final WebElement btnNewGoal = goalPage.getBtnNewGoal(newDriver);
		Waits.ForElementVisibility(newDriver, btnNewGoal);
		
		try{
			btnNewGoal.click();
			Waits.ForElementVisibility(newDriver, goalPage.getTxtInstruction(newDriver, 1));
			// @@Step :Completes all the required fields
			goalPage.getTxtGoalName(newDriver).sendKeys(goalName);
			goalPage.getRadDiscipline(newDriver, goalDiscipline).click();
			goalPage.getChkProblem(newDriver, goalProblem).click();
			goalPage.getTxtInstruction(newDriver, 1).sendKeys(goalInstruction);
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			goalPage.getBtnSave(newDriver).click();
			Waits.ForBrowserLoad(newDriver);
			// @Step :Verify success message
			Assert.assertTrue(driver.getPageSource().contains(successMesage), "There was an issue saving the goal");
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue enabling");
			Assert.fail("There was an turning ON the PTG setting");
			e.printStackTrace();
		}
	}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}
}
