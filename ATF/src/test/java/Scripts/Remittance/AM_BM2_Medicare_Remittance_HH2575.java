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

public class AM_BM2_Medicare_Remittance_HH2575 {
	

	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_Remittance_HH2575
	'JIRA ID						:	HH-2575
	'Description                    : 	Verify that user is able to add Claim Adjustments for RAP claim in Remittance
	'Input Parameters           	: 	Remittance Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that user is able to add Claim Adjustments for RAP claim in Remittance
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_Remittance_HH2575();
	}
	 @Test(groups = { "AM_Regression", "AM_Remittance" })
		public static void AM_BM2_Medicare_Remittance_HH2575() throws Exception {

			
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 ******************************************************************/	
				//@Reports Configuration
					Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH2575");
					Report.SetTestName("AM_BM2_Medicare_Remittance_HH2575","Verify that user is able to add Claim Adjustments for RAP claim in Remittance");
					Report.assignCategory("RAP");
					//@Open Application and submit credentials
					AM.Login.openAppAndSubmitCredentials();
					//@ Get Current WebDriver
					WebDriver driver = Browser.getDriver();
					//@Import Test data sheet
					String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH2575.xlsx";
					Datatable.loadDataSheet(dataFileName, "Remittance");
				/*****************************************************************/	
				
				//@ Step - To navigate to billing manager
				AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
				Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
				//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
				AM.Billing.Claims.ClaimsManager.SelectClaimFromRAPPendingPayment(driver);
				
				int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
				int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
				String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
				System.out.println(claimnum);
				
				if(!claimnum.isEmpty()){
					//@Step - Navigating to Remittance Managers page
					AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
					Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
					AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 				
					Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
					//@Step - Adding Remittance				
					AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
					AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
					AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
					AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
					AM.Billing.Remittance.RemittanceManager.AddClaimsAdjustment(driver, claimnum);
					AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
				} else{
					AM.DataCreation.DataCreation.CreateAndMoveRAPToPendingPayment(driver);
					int altcolIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
					int altrowIndex = components.ksGrid.getRowIndex(driver, "1.");
					String altclaimnum = components.ksGrid.getCellData(driver, altrowIndex, altcolIndex);
					System.out.println(altclaimnum);
					
					Datatable.loadDataSheet(dataFileName, "Remittance");
					AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 	
					Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
					AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 	
					Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
					
					//@Step - Adding Remittance				
					AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
					AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
					AM.Billing.Remittance.RemittanceManager.AddClaims(driver, altclaimnum);
					AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
					AM.Billing.Remittance.RemittanceManager.AddClaimsAdjustment(driver, altclaimnum);
					AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
				}
				
				String rmNumber = GlobalData.getRemittanceNumber();
				if (rmNumber.trim().isEmpty()){
					rmNumber = Datatable.GetValue("AR_RemittanceNumber");
				} 
				
		      	//@Step - Navigating to Remittance Managers page
		      	AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
		      	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
		      	AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");	
		      	Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software");
		      	AM.Billing.Remittance.RemittanceManager.FilterAndSearchForRemit(driver, rmNumber);
		                  		
				//@Step - Opening the Remittance page	     
		     	colIndex = components.ksGrid.getColumnIndex(driver, "Remittance #");
				rowIndex = components.ksGrid.getRowIndex(driver, rmNumber);
				AM.Billing.Remittance.RemittanceManager.OpenRemittance(driver, rowIndex, colIndex);
				
				//@Step - Verifying the Net Reimbursement Amount
				String Amt = AM.Billing.Remittance.RemittanceManager.txt_AR_NetReimbursementAmt(driver).getAttribute("value");
				String ExpAmount = "900.00";
				if(Amt.trim().equals(ExpAmount.trim())) {
					Report.Log(Status.PASS, "Reimbursement Amount " +ExpAmount+ " is displayed");
				}else{
					Report.Log(Status.FAIL, "Reimbursement " +ExpAmount+ " is NOT displayed");
					Assert.fail();
				}
				
			}
	    
		 @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }

}
