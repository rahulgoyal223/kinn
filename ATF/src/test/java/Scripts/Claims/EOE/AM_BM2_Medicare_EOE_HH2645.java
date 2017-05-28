package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2645 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2645
	'JIRA ID						:	HH-2645
	'Description                    : 	To verify if the patient's claim appears in EOE
										Ready tab when RAP is paid
	'Input Parameters           	: 	Patient Name, MRN, Claim Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	If the RAP is completed and paid verify if the RAP
										completion appears in EOE - Ready tab
	'Tags                           : 	Regression, Smoke, E2E
	 ************************************************************************************/
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/

	public static void Test() throws Exception {
    	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
			//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2645");
			Report.SetTestName("AM_BM2_Medicare_EOE_HH2645","If the RAP is completed and paid verify if the RAP completion appears in EOE - Ready tab");
			Report.assignCategory("EOE");
			//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			
		/*****************************************************************/	
		
			//@ Step - To navigate to billing manager
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			
			//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
			Waits.ForGlobalAjaxLoader(driver);
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			components.Waits.getSleepLevelFive();

			
			int colIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
			int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
			String patientName = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			System.out.println(patientName);
		
			//@Step - Verifying the Patient in EOE 'Ready' tab
			AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
			AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
			if(!GlobalData.getPatientFullName().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(patientName);
		      }
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, patientName);  
			
	}
    
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    

}
