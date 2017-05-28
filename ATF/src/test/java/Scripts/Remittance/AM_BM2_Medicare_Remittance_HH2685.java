package Scripts.Remittance;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_Remittance_HH2685 {
	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_Remittance_HH2685
	'JIRA ID						:	HH-2685
	'Description                    : 	To Verify user is able to cancel or delete a Remittance
	'Input Parameters           	: 	Remittance Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able
										to cancel or delete a remittance in Remittance Manager
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_Remittance_HH2685();
	}
	 @Test(groups = { "AM_Regression", "AM_Remittance" })
		public static void AM_BM2_Medicare_Remittance_HH2685() throws Exception {
			
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 ******************************************************************/	
				//@Reports Configuration
					Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH2685");
					Report.SetTestName("AM_BM2_Medicare_Remittance_HH2685","Verify that user is able to Cancel or Delete a Remittance in Remittance Manager");
					Report.assignCategory("Remittance");
					//@Open Application and submit credentials
					AM.Login.openAppAndSubmitCredentials();
					//@ Get Current WebDriver
					WebDriver driver = Browser.getDriver();
					//@Import Test data sheet
					String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH2685.xlsx";
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
						AM.Billing.Remittance.RemittanceManager.SaveRemit(driver);
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
						AM.Billing.Remittance.RemittanceManager.SaveRemit(driver);
					}
					
					//@Step - Navigating to Remittance Managers page
					
					String rmNumber = GlobalData.getRemittanceNumber();
					if (rmNumber.trim().isEmpty()){
						rmNumber = Datatable.GetValue("AR_RemittanceNumber");
					} 
					
					AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 		
					Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
					AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");	
					AM.Billing.Remittance.RemittanceManager.FilterAndSearchForRemit(driver, rmNumber);
				
					//@Step - Opening the Remittance page
					colIndex = components.ksGrid.getColumnIndex(driver, "Remittance #");
					rowIndex = components.ksGrid.getRowIndex(driver, rmNumber);
					AM.Billing.Remittance.RemittanceManager.OpenRemittance(driver, rowIndex, colIndex);
				
					//@Step - Cancel the Remittance
					AM.Billing.Remittance.RemittanceManager.CancelRemit(driver);
				
					//@Step - Delete the Remittance
					Verify.ExactPageHeader(driver, "Remittance Manager");
					AM.Billing.Remittance.RemittanceManager.FilterAndSearchForRemit(driver, rmNumber);
					AM.Billing.Remittance.RemittanceManager.OpenRemittance(driver, rowIndex, colIndex);
					AM.Billing.Remittance.RemittanceManager.DeleteRemit(driver);

	    }	
	    
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }

}
