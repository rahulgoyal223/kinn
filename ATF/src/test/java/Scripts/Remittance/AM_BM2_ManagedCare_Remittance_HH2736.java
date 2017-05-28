package Scripts.Remittance;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_ManagedCare_Remittance_HH2736 {

		/************************************************************************************
		'Class name                     : 	AM_BM2_ManagedCare_Remittance_HH2736
		'JIRA ID						:	HH-2736
		'Description                    : 	To Verify A new remittance can be manually entered into Remittance Manager
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies that the user is able
											to create new remittance can be manually entered into Remittance Manager
											
		'Tags                           : 	Regression, Smoke Test.
		 ************************************************************************************/

		public static void main(String[] args) throws Exception {
			AM_BM2_ManagedCare_Remittance_HH2736();
		}
		 @Test(groups = { "AM_Regression", "AM_Remittance" })
		 public static void AM_BM2_ManagedCare_Remittance_HH2736() throws Exception {

		String dataSheetName = null;
		/******************************************************************
		* Mandate to call below lines at every test case start up
		******************************************************************/
		//@Reports Configuration			
	    Report.generateReportsFile("html","AM_BM2_ManagedCare_Remittance_HH2736");
	    Report.SetTestName("AM_BM2_ManagedCare_Remittance_HH2736", "To Verify A new remittance can be manually entered into Remittance Manager");
	    Report.assignCategory("Remittance");
	    //@Open Application and submit credentials
	    AM.Login.openAppAndSubmitCredentials();
	    //@ Get Current WebDriver
	    WebDriver driver = Browser.getDriver();
	    //@Import Test data sheet
	    String dataFileName = "Remittance\\AM_BM2_ManagedCare_Remittance_HH2736.xlsx";
	    dataSheetName = "AM_BM2_MC_HH2736";
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    //**********************************************************************//	 
		
		//@ Step - To navigate to billing manager
		AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
		Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		
		//@ Step - To navigate to the pending payment tab in Claims Manager-Primary Payer
		AM.Billing.Claims.ClaimsManager.SelectClaimFromMCPendingPayment(driver);
		
		int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
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
			AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
		} else{
			AM.DataCreation.DataCreation.CreateAndMoveMCPrimaryClaimToPendingPayment(driver);
			int altcolIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
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
			AM.Billing.Remittance.RemittanceManager.SaveAndCompleteRemit(driver);
		}
		
	}	
		 @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
}
