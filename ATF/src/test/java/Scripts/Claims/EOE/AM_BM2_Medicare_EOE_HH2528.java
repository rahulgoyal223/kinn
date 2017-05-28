package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2528 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2528
	'JIRA ID						:	HH-2528
	'Description                    : 	Verify that user is able to Add Occurrences codes/ Occurance Date in RAP Claim worksheet
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	To Verify that user is able to Add Occurrences codes/ Occurance Date in RAP Claim worksheet
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
	public static void Test() throws Exception {
		
	String RD_MrecordNumber = null;	
    String dataSheetName = null;
    
    
    
    Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2528");
    Report.SetTestName("AM_BM2_Medicare_EOE_HH2528", "Verify that user is able to Add Occurrences codes/ Occurrence Date in RAP Claim worksheet");
    Report.assignCategory("EOE");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_EOE_HH2528.xlsx";
    dataSheetName = "EOE";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
  //@ Step - To navigate to billing manager
  		AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
  	
  		//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
  		AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
  		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
  		AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
  		components.Waits.getSleepLevelFive();


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
  	
  	     //@Step - Open Claim Work sheet and add occurances code ------Pending Approval tab
  	    Thread.sleep(Waits.getSleepLevelFive());
  		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
  		if(!GlobalData.getPatientFullName().isEmpty()){
  			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientFullName());;
  		} else{
  			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);
  		}
  		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.UB04_Worksheet.FillOccurenceCode(driver);
		AM.Billing.Claims.UB04_Worksheet.dt_Occurancedate(driver);
		Thread.sleep(Waits.getSleepLevelFive());	   
		AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.clickButton(driver, "Save and Close").click();
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		Report.attachScreenShotToReport(driver);

    }
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
