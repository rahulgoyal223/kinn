package Scripts.Clinical.PTG;

import java.util.ArrayList;

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

public class AMClinicalPTGHH2008 {


	/************************************************************************************
	 * 'Class name : AM_Clinical_PTG_HH2008 'JIRA ID : HH-2008 'Description :
	 * Verify that a Goal is edited to the Library
	 * Parameters : 'Output Parameters : 'Assumptions : 'Use : The following
	 * test verifies that the user is able to edit a Goal from the Library
	 * 'Tags : Functional
	 ************************************************************************************/

	@Test
	public void Test() throws Exception {
		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		Report.generateReportsFile("html", "AM_Clinical_PTG_HH2008");
		Report.SetTestName("AM_Clinical_PTG_HH2008",
				"Verify that a Goal is edited to the Library");
		Report.assignCategory("PTG");

		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		String goalName = "New Goal"+ RandomStringUtils.randomAlphanumeric(5);
		String secondGoalName = "New Goal"+ RandomStringUtils.randomAlphanumeric(5);
		String goalDiscipline = "SN";
		ArrayList<String> problemList = new ArrayList<String>();
		problemList.add("Supportive Assistance");
		ArrayList<String[]> instructionList = new ArrayList<String[]>();
		String[] instruction = new String[2];
		instruction[0] = "Instruction for goal " + goalName;
		instruction[1] = "NO";
		instructionList.add(instruction);
		String successMesage = "The Goal '" + secondGoalName + "' was saved successfully.";
		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		// @Activate PTG
		WebDriver newDriver = AM.ReportsAdmin.ReportsAdmin.turnOnPTG(driver);
		
		Goal goalPage = new Goal();
		
		try{
			
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(driver).click();
			Waits.ForBrowserLoad(driver);
			AM.PTG.Library.getBtnNewGoal(driver).click();
			Waits.ForBrowserLoad(driver);
			goalPage.getTxtGoalName(driver).sendKeys(goalName);
			goalPage.getRadDiscipline(driver, goalDiscipline).click();
			
			if(problemList.size()==1){
				goalPage.getChkProblem(driver, problemList.get(0)).click();
			}
			else
			{
				for(String problem : problemList){
					goalPage.getChkProblem(driver, problem).click();
				}
			}
			if(instructionList.size()==1)
			{
				goalPage.getTxtInstruction(driver, 1).sendKeys(instruction[0]);
				if(instruction[1] == "YES")
				{
					goalPage.getChkEditable(driver, 1).click();
				}
			}
			else
			{
				int count = 0;
				for(String[] currentInstruction : instructionList){
					count = count +1 ;
					goalPage.getTxtInstruction(driver, count).sendKeys(currentInstruction);
					if(currentInstruction[1] == "Yes")
					{
						goalPage.getChkEditable(driver, count).click();
					}
					if(count != instructionList.size()){
						goalPage.getBtnMoreInstruction(driver).click();
					}
				}
			}	
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			goalPage.getBtnSave(driver).click();
			Waits.ForBrowserLoad(driver);
			
			goalPage.getTxtNameGoalsFilter(newDriver).sendKeys(goalName);
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			goalPage.getIconEdit(newDriver, 1).click();
			WebElement txtGoalName = goalPage.getTxtGoalName(newDriver);
			Waits.ForElementVisibility(newDriver, txtGoalName);
			txtGoalName.sendKeys(secondGoalName);
			goalPage.getRadDiscipline(newDriver, "PT").click();
			goalPage.getBtnSave(newDriver).click();
			Waits.ForBrowserLoad(newDriver);
			// @Step :Verify success message
			Assert.assertTrue(driver.getPageSource().contains(successMesage), "There was an issue saving the goal");
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue while editing a Goal");
			Assert.fail("There was an issue editing a Goal");
			e.printStackTrace();
		}
	}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}
}
