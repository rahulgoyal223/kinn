package Scripts.Claims;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Claims_HH2492 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Claims_HH2492
	'JIRA ID						:	HH-2492
	'Description                    : 	Verify and validate the claim by selecting the claim frequency as monthly for Non- Medicare(HMO/Per visit)-Medicare advantage plan.
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	Patient
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the claim by selecting the claim frequency as monthly for Non- Medicare(HMO/Per visit)-Medicare advantage plan
	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Claims_HH2492();

	}
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Claims_HH2492() throws Exception {
		
       /******************************************************************
       * Mandate to call below lines at every test case start up
       *
       * 
       ******************************************************************/
       //@Reports Configuration       
      Report.generateReportsFile("html","AM_BM2_Claims_HH2492");
      Report.SetTestName("AM_BM2_Claims_HH2492", "Verify and validate the claim by selecting the claim frequency as monthly for Non- Medicare(HMO/Per visit)-Medicare advantage plan");
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
        AM.Insurance.InsuranceManager.lst_BillingFrequency(driver).selectByVisibleText("Monthly");
        AM.Insurance.InsuranceManager.btn_UpdateInsurance(driver).click();
        components.Verify.VerifySelectedText(driver, AM.Insurance.InsuranceManager.lst_BillingFrequency(driver), "Monthly");    

	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
