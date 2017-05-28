package Scripts.Clinical.PTG;

import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import AM.PTG.Intervention;
import components.Browser;
import components.Report;
import components.Waits;

public class AMClinicalHH4275 {


	/************************************************************************************
	 * 'Class name : AM_Clinical_HH4275 'JIRA ID : HH-4275 'Description :
	 * Verify that an Intervention is edited to the Library
	 * Parameters : 'Output Parameters : 'Assumptions : 'Use : The following
	 * test verifies that the user is able to edit an Intervention from the Library
	 * 'Tags : Functional
	 ************************************************************************************/

	private static final String YES_STRING = "YES";
	
	@Test
	public void Test() throws Exception {
		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		Report.generateReportsFile("html", "AM_Clinical_HH4275");
		Report.SetTestName("AM_Clinical_HH4275",
				"Verify that an Intervention is edited to the Library");
		Report.assignCategory("PTG");

		// @Reports Configuration
		// TODO Show demo as test suite using grid.
		String interventionName = "New Intervention"+ RandomStringUtils.randomAlphanumeric(5);
		String secondInterventionName = "New Intervention"+ RandomStringUtils.randomAlphanumeric(5);
		ArrayList<String> problemList = new ArrayList<String>();
		problemList.add("Supportive Assistance");
		ArrayList<String[]> instructionList = new ArrayList<String[]>();
		String[] instruction = new String[2];
		instruction[0] = "Instruction for intervention " + interventionName;
		instruction[1] = "NO";
		instructionList.add(instruction);
		String successMesage = "The Intervention '" + secondInterventionName + "' was saved successfully.";
		// @Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		// @ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		// @Activate PTG
		WebDriver newDriver = AM.ReportsAdmin.ReportsAdmin.turnOnPTG(driver);
		
		Intervention interventionPage = new Intervention();
		
		try{
			
			//Create Intervention
			AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
			AM.ReportsAdmin.ReportsAdmin.lnk_GoalsAdmin(driver).click();
			Waits.ForBrowserLoad(driver);
			interventionPage.getBtnInterventions(driver).click();
			Waits.ForBrowserLoad(driver);
			interventionPage.getBtnNewIntervention(driver).click();
			Waits.ForBrowserLoad(driver);
			interventionPage.getTxtInterventionName(driver).sendKeys(interventionName);
			
			if(problemList.size()==1){
				interventionPage.getChkProblem(driver, problemList.get(0)).click();
			}
			else
			{
				for(String problem : problemList){
					interventionPage.getChkProblem(driver, problem).click();
				}
			}
			if(instructionList.size()==1)
			{
				interventionPage.getTxtInstruction(driver, 1).sendKeys(instruction[0]);
				
				if(instruction[1].equalsIgnoreCase(YES_STRING))
				{
					interventionPage.getChkEditable(driver, 1).click();
				}
			}
			else
			{
				int count = 0;
				for(String[] currentInstruction : instructionList){
					count = count +1 ;
					interventionPage.getTxtInstruction(driver, count).sendKeys(currentInstruction);
					if(currentInstruction[1].equalsIgnoreCase(YES_STRING))
					{
						interventionPage.getChkEditable(driver, count).click();
					}
					if(count != instructionList.size()){
						interventionPage.getBtnMoreInstruction(driver).click();
					}
				}
			}	
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			interventionPage.getBtnSave(driver).click();
			Waits.ForBrowserLoad(driver);
			
			interventionPage.getTxtNameInterventionsFilter(newDriver).sendKeys(interventionName);
			//Wait until the instruction is processed by the frontend
			Thread.sleep(1100);
			interventionPage.getIconEdit(newDriver, 1).click();
			interventionPage.getTxtInstruction(newDriver, 1).sendKeys(secondInterventionName);
			interventionPage.getBtnSave(newDriver).click();
			Waits.ForBrowserLoad(newDriver);
			// @Step :Verify success message
			Assert.assertTrue(driver.getPageSource().contains(successMesage), "There was an issue saving the intervention");
		}
		catch(Exception e){
			Report.Log(Status.FAIL, "There was an issue while editing a Intervention");
			Assert.fail("There was an issue editing a Intervention");
			e.printStackTrace();
		}
	}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		AM.ReportsAdmin.ReportsAdmin.turnOffPTG(Browser.getDriver());
		components.Browser.teardownTest();
	}

}
