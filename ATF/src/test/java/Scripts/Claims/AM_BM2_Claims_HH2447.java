package Scripts.Claims;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Claims_HH2447 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Claims_HH2447
	'JIRA ID						:	HH-2447
	'Description                    : 	Verify and validate the claim by selecting the claim  frequency  as Episodic for Non- Medicare(HMO/Per visit)-Medicare advantage plan.
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	Patient
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the claim by selecting the claim  frequency  as Episodic for Non- Medicare(HMO/Per visit)-Medicare advantage plan
	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Claims_HH2447();

	}
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Claims_HH2447() throws Exception {
		
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
      Report.generateReportsFile("html","AM_BM2_Claims_HH2447");
      Report.SetTestName("AM_BM2_Claims_HH2447", "Verify and validate the claim by selecting the claim  frequency  as Episodic for Non- Medicare(HMO/Per visit)-Medicare advantage plan.");
      Report.assignCategory("Claims");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
      //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();     
      //**********************************************************************     
   
        
        //@Step - : To Select the Billing Manager
        Waits.ForBrowserLoad(driver);
        AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
        Verify.ExactPageTitle(driver, "Reports & Administration | Kinnser Software");
        AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Insurance");
        Verify.ExactPageTitle(driver, "Insurance Manager | Kinnser Software");
        AM.Insurance.InsuranceManager.SelectInsurance(driver, "MHMO Advantage Plan").click();
        Verify.ExactPageTitle(driver, "Insurance | Kinnser Software");
        AM.Menu.TopMenu.Select(driver, "Edit/Detail");      
        AM.Insurance.InsuranceManager.lst_BillingFrequency(driver).selectByVisibleText("Episodic");
        AM.Insurance.InsuranceManager.btn_UpdateInsurance(driver).click();
        components.Verify.VerifySelectedText(driver, AM.Insurance.InsuranceManager.lst_BillingFrequency(driver), "Episodic");

	}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }

}
