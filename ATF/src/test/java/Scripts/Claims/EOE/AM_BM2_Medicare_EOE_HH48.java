package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH48 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH48
	'ID								:	HH-48
	'Description                    : 	Verify that user is able to Add condition codes and occurrence codes
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	To Verify that user is able to Add Condition codes and Occurrence codes
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		Test();
	}
		
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void Test() throws Exception {
			
    String dataSheetName = null;
    
    /******************************************************************
	 * Mandate to call below lines at every test case start up
	 * 
	*****************************************************************/
    
    Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH48");
    Report.SetTestName("AM_BM2_Medicare_EOE_HH48", "Verify that user is able to Add Condition codes and Occurrence codes");
    Report.assignCategory("EOE");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
   // String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH2463.xlsx";
   // dataSheetName = "EOE";
    
   // Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
		//@ Step - To navigate to billing manager
		AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
	
		//@ Step - To navigate to the EOE_pending creation Ready  tab in Claims Manager-RAP
		AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
		AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
     	Waits.ForGlobalAjaxLoader(driver);


		int colIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
		int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
		String PM_PatientName = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		System.out.println(PM_PatientName);
	
		if(!GlobalData.getPatientFullName().isEmpty()){
			AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
		} else{
			AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(PM_PatientName);
		}
     
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	
	     //@Step - Open Claim Work sheet and add occurrences code ------Pending Approval tab

		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
     	Waits.ForGlobalAjaxLoader(driver);

		if(!GlobalData.getPatientFullName().isEmpty()){
			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
		} else{
			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
		}
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
     	Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.EOE_Worksheet.EnterConditionCode(driver, "conditionCode1", "21");
		AM.Billing.Claims.EOE_Worksheet.EnterOccurrenceCode(driver, "occurenceCode1", "39");
		Thread.sleep(Waits.getSleepLevelThree());	   
		AM.Billing.Claims.EOE_Worksheet.btn_SaveAndClose(driver).click();
		Waits.ForGlobalAjaxLoader(driver);

        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Pending Approval");
        AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("Palmetto GBA");
        Waits.ForGlobalAjaxLoader(driver);
        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);
        AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
        Waits.ForGlobalAjaxLoader(driver);
       String conditionCode = AM.Billing.Claims.EOE_Worksheet.GetConditionCode(driver, "conditionCode1").getText();
        Assert.assertEquals(conditionCode, "21 - Billing for Denial Notice");
	
		Report.attachScreenShotToReport(driver);
	
					
	}
	
    @AfterTest(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
    
}


