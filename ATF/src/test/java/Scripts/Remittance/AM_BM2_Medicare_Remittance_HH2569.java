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

public class AM_BM2_Medicare_Remittance_HH2569 {
	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_Remittance_HH2569
	'JIRA ID						:	HH-2569
	'Description                    : 	To Verify that user is able create a new Remittance
										in Remittance Manager for a Medicare claim
	'Input Parameters           	: 	Remittance Number
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that user is able to 
										create a new Remittance in Remittance Manager by 
										clicking on the Remittance Manager link Medicare
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_Remittance_HH2569();
	}
	 @Test(groups = { "AM_Regression", "AM_Remittance" })
		public static void AM_BM2_Medicare_Remittance_HH2569() throws Exception {
			
				/******************************************************************
				 * Mandate to call below lines at every test case start up
				 ******************************************************************/	
				//@Reports Configuration
					Report.generateReportsFile("html","AM_BM2_Medicare_Remittance_HH2569");
					Report.SetTestName("AM_BM2_Medicare_Remittance_HH2569","To Verify that user is able to create a new Remittance in Remittance Manager by clicking on the Remittance Manager link under Medicare/Managed Care section");
					Report.assignCategory("Remittance");
					//@Open Application and submit credentials
					AM.Login.openAppAndSubmitCredentials();
					//@ Get Current WebDriver
					WebDriver driver = Browser.getDriver();
					//@Import Test data sheet
					String dataFileName = "Remittance\\AM_BM2_Medicare_Remittance_HH2569.xlsx";
					Datatable.loadDataSheet(dataFileName, "Remittance");
				/*****************************************************************/	
				
				
				//@Step - Navigating to Remittance Managers page
				AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
				Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
				AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 				
				Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software"); 
				//@Step - Adding Remittance		
				AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
				AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
				AM.Billing.Remittance.RemittanceManager.SaveRemit(driver);
				
				Waits.ForBrowserLoad(driver);				
				//@Step - Navigating to Remittance Managers page
				AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 
				Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
				AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");
				Verify.ExactPageTitle(driver, "Remittance Manager | Kinnser Software"); 
				
				//@Step - Verifying the Remittance number in Remittance Manager's page
				String rmNumber = GlobalData.getRemittanceNumber();
				if (rmNumber.trim().isEmpty()){
					rmNumber = Datatable.GetValue("AR_RemittanceNumber");
				}
				AM.Billing.Remittance.RemittanceManager.FilterAndSearchForRemit(driver, rmNumber);
				int colIndex = components.ksGrid.getColumnIndex(driver, "Status");
				int rowIndex = components.ksGrid.getRowIndex(driver, rmNumber);
				components.ksGrid.verifyCellData(driver, rowIndex, colIndex, "Saved");				
	    
	    }	
	    
		 @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }

}
