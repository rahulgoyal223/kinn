package Scripts.Clinical.PTG;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import AM.PTG.Intervention;
import components.Browser;
import components.Report;
import components.Waits;

public class AMClinicalPTGHH2093 {

	/************************************************************************************
	 * 'Class name : AM_Clinical_PTG_HH2093 'JIRA ID : HH-2093 'Description :
	 * Verify that A New Intervention is added to the Library
	 * Parameters : 'Output Parameters : 'Assumptions : 'Use : The following
	 * test verifies that the user is able to add a Intervention to the Library
	 * 'Tags : Functional
	 ************************************************************************************/

	@Test
	public void Test() throws Exception {
		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		Report.generateReportsFile("html", "AM_Clinical_PTG_HH2093");
		Report.SetTestName("AM_Clinical_PTG_HH2093",
				"Verify that A New Intervention is added to the Library");
		Report.assignCategory("Patient");

		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		String interventionName = "New Intervention"+ RandomStringUtils.randomAlphanumeric(5);
		String interventionProblem = "Supportive Assistance";
		String interventionInstruction = "Instruction for Intervention " + interventionName;
		String successMesage = "The Intervention '" + interventionName + "' was saved successfully.";
		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		// @Activate PTG
		WebDriver newDriver = AM.ReportsAdmin.ReportsAdmin.turnOnPTG(driver);
		AM.Menu.TopMenu.Select(newDriver, "Go To/Reports/Admin");
		AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(newDriver).click();
		Waits.ForElementVisibility(newDriver, AM.PTG.Library.getBtnNewGoal(newDriver));
		
		Intervention interventionPage = new Intervention();
		
		try{
			interventionPage.getBtnInterventions(newDriver).click();
			interventionPage.getBtnNewIntervention(newDriver).click();
			final WebElement txtInterventionName = interventionPage.getTxtInterventionName(driver);
			Waits.ForElementVisibility(newDriver, txtInterventionName);
			// @@Step :Completes all the required fields
			txtInterventionName.sendKeys(interventionName);
			interventionPage.getChkProblem(newDriver, interventionProblem).click();
			interventionPage.getTxtInstruction(newDriver, 1).sendKeys(interventionInstruction);
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			interventionPage.getBtnSave(newDriver).click();
			Waits.ForBrowserLoad(newDriver);
			// @Step :Verify success message
			Assert.assertTrue(driver.getPageSource().contains(successMesage), "There was an issue saving the intervention");
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue saving an Intervention");
			Assert.fail("There was an issue saving an Intervention");
			e.printStackTrace();
		}
	}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}

}
