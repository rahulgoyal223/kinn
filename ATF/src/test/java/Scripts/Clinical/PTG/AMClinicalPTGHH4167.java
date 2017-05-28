package Scripts.Clinical.PTG;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import AM.PTG.Goal;
import AM.PTG.Intervention;
import components.Browser;
import components.Report;
import components.Waits;
import utils.NumberUtil;

public class AMClinicalPTGHH4167 {
	
	/************************************************************************************
	'Class name                     : 	AMClinicalPTGHH4167
	'JIRA ID						:	HH-4167
	'Description                    : 	Verify the attachment of one Intervention to a Goal.
	'Input Parameters           	: 	Goal
	'Output Parameters        		: 	
	'Assumptions                    : 	PTG Roles and settings are enabled.
	'Use                            : 	The following test verifies that the user is able
										to create a goal
	'Tags                           : 	Regression
	 * @throws Exception 
	 ************************************************************************************/
	
	@Test(groups = { "AM_PTG" })
	public void test() throws Exception {
	  
		
		Report.generateReportsFile("html","AM_PTG_HH4167");
		Report.SetTestName("AM_Patient_HH4167","To Verify the attachment of one Intervention to a Goal");
        Report.assignCategory("PTG");
        
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();

		AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
		AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(driver).click();
		
		//Create a new Intervention
		Intervention interventionPage = new Intervention();
		interventionPage.getBtnInterventions(driver).click();
		interventionPage.getBtnNewIntervention(driver).click();
		interventionPage.getTxtInterventionName(driver).sendKeys("Intervention Automation HH417 "+NumberUtil.getRandomNumber());
		interventionPage.getChkProblem(driver, "Supportive Assistance").click();
		interventionPage.getChkProblem(driver, "Elimination").click();
		interventionPage.getBtnMoreInstruction(driver).click();
		interventionPage.getTxtInstruction(driver, 1).sendKeys("Instruction Intervention part 1");
		interventionPage.getTxtInstruction(driver, 2).sendKeys("Instruction Intervention part 2");
		interventionPage.getChkEditable(driver, 1);
		//Wait until the instruction is processed by the frontend
		Thread.sleep(1100);
		Report.Log(Status.PASS, "Inserted all the data for the new Intervention");
		Report.attachScreenShotToReport(driver);
		interventionPage.getBtnSave(driver).click();
		//Wait to see the success message
		Thread.sleep(Waits.getSleepLevelFive());
		Report.Log(Status.PASS, "Interventions added");
		Report.attachScreenShotToReport(driver);
		
		//Create a new Goal
		Goal goalPage = new Goal();
		WebElement btnGoals = goalPage.getBtnGoals(driver);
		Waits.ForElementToBeClickable(driver, btnGoals);
		btnGoals.click();
		Waits.ForBrowserLoad(driver);
		
		WebElement btnNewGoal = goalPage.getBtnNewGoal(driver);
		Waits.ForElementVisibility(driver, btnNewGoal);
		btnNewGoal.click();
		goalPage.getTxtGoalName(driver).sendKeys("Goal Automation  HH417 "+NumberUtil.getRandomNumber());
		goalPage.getRadDiscipline(driver, "SN").click();
		goalPage.getChkProblem(driver, "Supportive Assistance").click();
		goalPage.getChkProblem(driver, "Elimination").click();
		goalPage.getBtnMoreInstruction(driver).click();
		goalPage.getTxtInstruction(driver, 1).sendKeys("Instruction Goal part 1");
		goalPage.getTxtInstruction(driver, 2).sendKeys("Instruction Goal part 2");
		//Wait until the instruction is processed by the frontend
		Thread.sleep(1100);
		Report.Log(Status.PASS, "Inserted all the data for the new goal");
		Report.attachScreenShotToReport(driver);
		goalPage.getBtnOpenModalGoal(driver).click();
		WebElement btnContinueModal = goalPage.getBtnContinueModal(driver);
		Waits.ForElementToBeClickable(driver, btnContinueModal);
		List<WebElement> interventionsFromDialog = goalPage.getInterventionsFromDialog(driver);
		interventionsFromDialog.get(0).click();
		btnContinueModal.click();
		goalPage.getBtnSave(driver).submit();
		
		//Wait to see the success message
		Thread.sleep(Waits.getSleepLevelFive());
		Report.Log(Status.PASS, "Goal added");
		Report.attachScreenShotToReport(driver);
		
	}
	
	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
	
}
