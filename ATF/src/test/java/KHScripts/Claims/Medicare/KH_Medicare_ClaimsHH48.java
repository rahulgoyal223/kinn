package KHScripts.Claims.Medicare;

import DataSource.Datatable;
import DataSource.GlobalData;
import KH.Forms.Nursing.RNIA;
import components.Browser;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;


public class KH_Medicare_ClaimsHH48{

	private static String strInputValue;
		/****************************************************************
		 *Class name		: KH_HH48
		 *Description		: Test to verify Condition codes/Occurrence codes on medicare claim worksheet
		 *Input Parameters	: Patient Name and Info
		 *Output Parameters	: None
		 *Assumptions		: Test Data is present in the Global Sheet.
		 *Use				: N/A
		 *Tags				: KH:Regression
		******************************************************************/

	public static void main(String[] args) throws Exception {
		Test();
	}
		
	@Test
	public static void Test() throws Exception {
	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
			//@Reports Configuration
			Report.generateReportsFile("html","KH_HH48");
			Report.SetTestName("KH_HH48","KH_HH48");
			Report.assignCategory("TBD");
			Report.assignCategory("Medicare");
			//@Open Application and submit credentials
			KH.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "KHClaims\\KH_Medicare_HH48.xlsx";
			String dataSheetName = "KH_Medicare-HH48";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/
			//@Step - Create New Patient, if Required
			if (Datatable.GetValue("CreatePatient").equals("Yes")) {
				//@ Load Data and Add New Patient
			    Datatable.loadDataSheet(dataFileName, "CreatePatient");
			
			    KH.Menu.TopMenu.Select(driver, "File/New/Patient");
				KH.Patient.AddNewPatient.FillAddNewPatient(driver);
				Thread.sleep(Waits.getSleepLevelFive());
				Waits.ForBrowserLoad(driver);
				KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
				Waits.ForBrowserLoad(driver);
			}
			
			String PM_PatientName = null;
			if (!GlobalData.getPatientFullName().isEmpty()){
				PM_PatientName = GlobalData.getPatientFullName();
			}else {
				PM_PatientName = Datatable.GetValue("PM_PatientName");
			}
			Waits.ForBrowserLoad(driver);
			
			Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
			KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
			Waits.ForBrowserLoad(driver);
			Thread.sleep(Waits.getSleepLevelFive());
			KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();
			Waits.ForBrowserLoad(driver);
			Thread.sleep(Waits.getSleepLevelFive());
			
			 //@Goto Billing Manager
			KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			 components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
			KH.Billing.BillingManager.SelectMenu(driver, "Medicare/Ready");
			 Waits.ForBrowserLoad(driver);
			Thread.sleep(Waits.getSleepLevelFive());
		    KH.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");  
		   KH.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(PM_PatientName);
			KH.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
			KH.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
			   AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
			 	
			    //@Step - Go to Pending Approval>Enter some condition codes>Save
			   Thread.sleep(Waits.getSleepLevelFive());
				KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
				 components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
				 KH.Billing.BillingManager.SelectMenu(driver, "Medicare/Pending Approval");
				 Waits.ForBrowserLoad(driver);
					Thread.sleep(Waits.getSleepLevelFive());
					 KH.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
				
			 KH.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
			 KH.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
			 Waits.ForBrowserLoad(driver);
			 
		

	            KH.Billing.Claims.Medicare_Worksheet.EnterConditionCode(driver, "conditionCode1", "21");
	            String conditionCode = KH.Billing.Claims.Medicare_Worksheet.GetConditionCode(driver, "conditionCode1").getText();
	            Assert.assertEquals(conditionCode, "21 - Billing for Denial Notice");
	            KH.Billing.Claims.Medicare_Worksheet.btn_SaveAndClose(driver).click();
	            Waits.ForGlobalAjaxLoader(driver);

	            //Go back to pending approval and check the saved condition codes on worksheet.
	            KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	            KH.Billing.BillingManager.SelectMenu(driver, "Medicare/Pending Approval");
				 Waits.ForGlobalAjaxLoader(driver);
				 KH.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
				 KH.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
	            AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	            Waits.ForGlobalAjaxLoader(driver);
	            conditionCode = KH.Billing.Claims.Medicare_Worksheet.GetConditionCode(driver, "conditionCode1").getText();
	            Assert.assertEquals(conditionCode, "21 - Billing for Denial Notice");
			}

			
			
			
			
	
	@AfterTest(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
