package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_ManagedCare_HH417 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH417
	'JIRA ID						:	HH-417
	'Description                    : 	To Verify 'Managed Care', 'Primary Payer', 
										'Pending Creation' Tab, 'Claim Age' drop-downs
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the 'Managed Care', 
										'Primary Payer', 'Pending Creation' Tab, 'Claim Age' 
										drop-downs
	'Tags                           : 	Regression
	 ************************************************************************************/
	
		public static void main(String[] args) throws Exception {
			AM_BM2_ManagedCare_HH417();
			}
				
				@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
				public static void AM_BM2_ManagedCare_HH417() throws Exception {	
			 /******************************************************************
		       * Mandate to call below lines at every test case start up
		       * 
		       * 
		       ******************************************************************/
		       //@Reports Configuration
		      Report.generateReportsFile("html","AM_BM2_ManagedCare_HH417");
		      Report.SetTestName("AM_BM2_ManagedCare_HH417", "AM_HH-119_Billing Manager_ Managed Care_To verify 'Managed Care' 'Primary Payer' 'Pending creation' Tab 'Claim Age' drop-down");
		      Report.assignCategory("ManagedCare");
		      //@Open Application and submit credentials
		      AM.Login.openAppAndSubmitCredentials();
		     //@ Get Current WebDriver
		      WebDriver driver = Browser.getDriver();
		     //**********************************************************************
		      
		      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		      Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		      AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Not Ready");
		      Verify.ExactPageHeader(driver, "Claims Manager: Primary Managed Care");
		      AM.Billing.Claims.ClaimsManager.lst_NR_Payer(driver).selectByVisibleText("Managed Care Insurance");
		      AM.Billing.Claims.ClaimsManager.lst_NR_Payer(driver).selectByVisibleText("All Insurances");
		      AM.Billing.Claims.ClaimsManager.lst_NR_ClaimAge(driver).selectByVisibleText("0-60 days");
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Billing.Claims.ClaimsManager.lst_NR_ClaimAge(driver).selectByVisibleText("All Claims");
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		      Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		      AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
		      Verify.ExactPageHeader(driver, "Claims Manager: Primary Managed Care");
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
		      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("0-60 days");
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
		      Verify.VerifySelectedText(driver, AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver), "All Claims");
		      Verify.VerifySelectedText(driver, AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver), "All Insurances");
	}
				
		    @AfterClass(alwaysRun = true)
			 public static void Teardown() {
				 components.Browser.teardownTest();
			 }

}
