package Scripts.Remittance;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_Remittance_HH2598 {
	


	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_Remittance_HH2598
	'JIRA ID						:	HH-2598
	'Description                    : 	To add Provider Adjustment to Remittance in Remittance Manager
	'Input Parameters           	: 	Remittance Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that user is able to add Provider 
										Adjustment to Remittance in Remittance Manager
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_Remittance_HH2598();
	}
	 @Test(groups = { "AM_Regression", "AM_Remittance" })
		public static void AM_BM2_Medicare_Remittance_HH2598() throws Exception {
		
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 ******************************************************************/	
				//@Reports Configuration
					Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH2598");
					Report.SetTestName("AM_BM2_Medicare_Remittance_HH2598","To add Provider Adjustment to Remittance in Remittance Manager");
					Report.assignCategory("Remittance");
					//@Open Application and submit credentials
					AM.Login.openAppAndSubmitCredentials();
					//@ Get Current WebDriver
					WebDriver driver = Browser.getDriver();
					//@Import Test data sheet
					String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH2598.xlsx";
					Datatable.loadDataSheet(dataFileName, "Remittance");
				/*****************************************************************/	
				
			
				//@Step - Navigating to Remittance Managers page
				AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 				
				AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 				
				
				//@Step - Adding Remittance				
				AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
				AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
				AM.Billing.Remittance.RemittanceManager.AddProviderAdjustment(driver);
				AM.Billing.Remittance.RemittanceManager.AddExternalAdjustment(driver);
				AM.Billing.Remittance.RemittanceManager.SaveRemit(driver);
							
		      	//@Step - Navigating to Remittance Managers page
				String rmNumber = GlobalData.getRemittanceNumber();
				if (rmNumber.trim().isEmpty()){
					rmNumber = Datatable.GetValue("AR_RemittanceNumber");
				} 
		     	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
		     	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		      	AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");
		      	Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software"); 	
		      	AM.Billing.Remittance.RemittanceManager.FilterAndSearchForRemit(driver, rmNumber);
		                  		
				//@Step - Opening the Remittance page	     
		     	int colIndex = components.ksGrid.getColumnIndex(driver, "Remittance #");
				int rowIndex = components.ksGrid.getRowIndex(driver, rmNumber);
				AM.Billing.Remittance.RemittanceManager.OpenRemittance(driver, rowIndex, colIndex);
				Verify.ExactPageTitle(driver, "Remittance | Kinnser Software");
				//@Step - Verifying the External Adjustment
				String Amt = AM.Billing.Remittance.RemittanceManager.txt_AR_ProviderAdjustment(driver).getAttribute("value");
				String ExpAmount = "20.00";
				if(Amt.trim().equals(ExpAmount.trim())) {
					Report.Log(Status.PASS, "External Adj Amount " +ExpAmount+ " is displayed");
				}else{
					Report.Log(Status.FAIL, "External Adj Amount " +ExpAmount+ " is NOT displayed");
					Assert.fail();
				} 	
				
			}	
	    
		 @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }

}
