package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2597 {
	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2597
	'JIRA ID						:	HH-2597
	'Description                    : 	To Verify that user is able cancel a Medicare RAP
	'Input Parameters           	: 	Patient Name, Claim Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that user is able to 
										cancel a RAP claim that is added to a Remittance
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2597();
	}
    
    	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_Medicare_RAP_HH2597() throws Exception {
		
    		/******************************************************************
    		 * Mandate to call below lines at every test case start up
    		 ******************************************************************/	
    		//@Reports Configuration
			Report.generateReportsFile("html", "AM_BM2_Medicare_RAP_HH2597");
			Report.SetTestName("AM_BM2_Medicare_RAP_HH2597","Verify that user is able to cancel a RAP claim that is added to a Remittance");
			Report.assignCategory("RAP"); Report.assignCategory("CancelRAPClaim");
			//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2597.xlsx";
			String dataSheetName = "Remittance";
			Datatable.loadDataSheet(dataFileName, dataSheetName);
			/*****************************************************************/	
			String PM_PatientName = null;
			String PM_MRNNumber = null;
			//@ Step - To navigate to billing manager
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			
			//@ Step - To navigate to pending payment tab in Claims Manager-RAP
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Payment");
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			Waits.ForGlobalAjaxLoader(driver);
			
			PM_PatientName = Datatable.GetValue("PM_PatientName");
			if (PM_PatientName.isEmpty() || PM_PatientName.trim().toLowerCase().equals("dynamicvalue")){
				PM_PatientName = GlobalData.getPatientFullName();
	        }
			
			PM_MRNNumber = Datatable.GetValue("PM_MRNNumber");
			if (PM_MRNNumber.isEmpty() || PM_MRNNumber.trim().toLowerCase().equals("dynamicvalue")){
				PM_MRNNumber = GlobalData.getPatientMRNumber();
	        }
			
			int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
			int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
			String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
						
			//@Step - Navigating to Remittance Managers page
			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
			Waits.ForBrowserLoad(driver);
			AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 
			
			AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
			AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
			
			String rmNumber = GlobalData.getRemittanceNumber();
			if (rmNumber.trim().isEmpty()){
				rmNumber = Datatable.GetValue("AR_RemittanceNumber");
			} 
			
			System.out.println("Remittance Number : " +rmNumber);
			AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
			Waits.ForBrowserLoad(driver);
			AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
			AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
			AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
			
			//@Step - Verifying the Patient in RAP paid tab---------Rejected/Cancelled (Clinic is set to autocancel which goes to Ready to Send) tab
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready to Send");
			//AM.Billing.BillingManager.SelectMenu(driver, "RAP/Rejected/Cancelled");
			AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
			//AM.Billing.Claims.ClaimsManager.lst_RC_Type(driver).selectByVisibleText("Cancelled");
			Waits.ForGlobalAjaxLoader(driver);
			if(!GlobalData.getPatientMRNumber().isEmpty()){
	        	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getClaimNumer());
	         }
	         else{
	        	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(claimnum);
	         }
			
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, claimnum);   
			
	}

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
    	
}
