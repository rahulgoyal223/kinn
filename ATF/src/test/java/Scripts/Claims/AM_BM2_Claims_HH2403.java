package Scripts.Claims;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Claims_HH2403 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Claims_HH2403
	'JIRA ID						:	HH-2403
	'Description                    : 	Verify validate that the user is able to edit the billing frequency. 
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies and  validate that the user is able to edit the billing frequency.
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Claims_HH2403();
	}
		
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Claims_HH2403() throws Exception {
    
    Report.generateReportsFile("html","AM_BM2_Claims_HH2403");
    Report.SetTestName("AM_BM2_Claims_HH2403", "AM_84587_Claims Manager_ HCFA 1500-08-05- Verify and validate the claim by selecting the claim frequency as Weekly for a Non- Medicare(HMO/Per visit)-Medicare advantage plan.");
    Report.assignCategory("Claims");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    
    /*****************************************************************/
    //@Step - : To Select the Billing Manager
    Waits.ForBrowserLoad(driver);
    AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
    Verify.ExactPageTitle(driver, "Reports & Administration | Kinnser Software");
    AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Insurance");
    Verify.ExactPageTitle(driver, "Insurance Manager | Kinnser Software");
    AM.Insurance.InsuranceManager.SelectInsurance(driver, "MHMO Advantage Plan").click();
    Verify.ExactPageTitle(driver, "Insurance | Kinnser Software");
    Waits.ForBrowserLoad(driver);
	AM.Menu.TopMenu.Select(driver, "Edit/Detail");
    AM.Insurance.InsuranceManager.lst_BillingFrequency(driver).selectByVisibleText("Weekly");
    AM.Insurance.InsuranceManager.btn_UpdateInsurance(driver).click();
    components.Verify.VerifySelectedText(driver, AM.Insurance.InsuranceManager.lst_BillingFrequency(driver), "Weekly");
	}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
