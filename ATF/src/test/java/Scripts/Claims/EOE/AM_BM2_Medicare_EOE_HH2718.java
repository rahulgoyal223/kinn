package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2718 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2718
	'JIRA ID						:	HH-2718
	'Description                    : 	To verify if the user is able to correct an EOE 
									   	claim from the Rejected tab of Claims Manager.
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	MRN
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies correction of an EOE from
									   	the Rejected/Cancelled tab of Claims Manager
	'Tags                           : 	Regression, Smoke, E2E
	 ************************************************************************************/
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
	public static void Test() throws Exception {
		
		   String PM_PatientName = null;	
	       /******************************************************************
	       * Mandate to call below lines at every test case start up
	       * 	       
	       ******************************************************************/
			Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2718");
			Report.SetTestName("AM_BM2_Medicare_EOE_HH2718", "To verify if the user is able to correct an EOE claim from the Rejected tab of Claims Manager.");
			Report.assignCategory("EOE");
			AM.Login.openAppAndSubmitCredentials();
			WebDriver driver = Browser.getDriver();
		    /*****************************************************************/			
			//@ Step - To navigate to billing manager
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			
			//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
			AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Payment");
			Waits.ForGlobalAjaxLoader(driver);
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			components.Waits.getSleepLevelFive();

			
			int colIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
			int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
			String patientName = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			System.out.println(patientName);
		
			//@Step - Reject the claim in Pending Payment
			AM.Billing.Claims.ClaimsManager.SelectPatient(driver, patientName);
			AM.Billing.Claims.ClaimsManager.btn_PP_Markrejected(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
			
			//@Step - Correct the claim in Rejected/Cancelled tab
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");
			AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");
			AM.Billing.Claims.ClaimsManager.lst_RC_Type(driver).selectByVisibleText("Rejected");
			
			if(!GlobalData.getPatientFullName().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(patientName);
		      }
			
			AM.Billing.Claims.ClaimsManager.SelectPatient(driver, patientName);
		    AM.Billing.Claims.ClaimsManager.btn_RC_Correct(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_Correct_Yes(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
			
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");			
          
            if(!GlobalData.getPatientFullName().isEmpty()) {
            Thread.sleep(Waits.getSleepLevelFive());
	     	AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
	        } 
            else {
	     	AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(patientName);
	        }			
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
			
			//Step - To take screenshot
			Report.attachScreenShotToReport(driver);
			
	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
