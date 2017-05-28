package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2489 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2489
	'JIRA ID						:	HH-2489
	'Description                    : 	Verify that user is able to pay a RAP claim using Remittance Manager
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that a user is able to pay a RAP claim
										using Remittance Manager
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	 public static void main(String[] args) throws Exception {
		 AM_BM2_Medicare_RAP_HH2489();
		}
	    
	    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_Medicare_RAP_HH2489() throws Exception {
			
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2489");
			Report.SetTestName("AM_BM2_Medicare_RAP_HH2489","verify if the pateint appears in RAP-> Paid");
			Report.assignCategory("RAP");
		//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
			String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2489.xlsx";
			Datatable.loadDataSheet(dataFileName, "Remittance");
		/*****************************************************************/	
				
			//@ Step - To navigate to billing manager
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			
			//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Payment");
			Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			Waits.ForGlobalAjaxLoader(driver);

			
			int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
			int rowIndex = components.ksGrid.getRowIndex(driver, "2.");
			String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			System.out.println(claimnum);
			
			//@Step - Navigating to Remittance Managers page
			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
			Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 	
			Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
			
			//@Step - Adding Remittance				
			AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
			AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
			AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
			Waits.ForBrowserLoad(driver);
			AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
			AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
			Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
			AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
				
			//@Step - Verifying the Patient in RAP paid tab---------Paid tab				
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
			Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
			if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getClaimNumer());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(claimnum);
		      }
			AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
			Waits.ForGlobalAjaxLoader(driver);
			AM.Billing.Claims.ClaimsManager.VerifyClaim(driver, claimnum);

		}	
	    
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
		
	}


