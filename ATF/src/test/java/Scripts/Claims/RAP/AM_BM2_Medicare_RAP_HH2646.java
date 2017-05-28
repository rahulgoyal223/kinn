package Scripts.Claims.RAP;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2646 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2646
	'JIRA ID						:	HH-2646
	'Description                    : 	To Verify First Billable Visit status when Billable
										and non billable visit are scheduled different days 
										and completed on Non Billable visit date
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the first billable
										visit light is green in pending creation when Billable
										and non billable visit are scheduled different days 
										and completed on Non Billable visit date
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2646();
	}
		
		@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_Medicare_RAP_HH2646() throws Exception {

	      String dataSheetName = null;    	
     /******************************************************************
     * Mandate to call below lines at every test case start up
     * 
     ******************************************************************/
     //@Reports Configuration
    Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2646");
    Report.SetTestName("AM_BM2_Medicare_RAP_HH2646", "Verify First Billable Visit status when Billable & Non Billable Visits scheduled different days and completed on Non billable visit date");
    Report.assignCategory("RAP");
    //@Open Application and submit credentials
    AM.Login.openAppAndSubmitCredentials();
    //@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    //@Import Test data sheet
    String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2646.xlsx";
    dataSheetName = "AM_BM2_Medicare_RAP_HH2646";
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    //**********************************************************************
    
   //@@Step :To create new patient if needed
    if (Datatable.GetValue("CreatePatient").equals("Yes")) {

    	//@Step :To load sheet
        Datatable.loadDataSheet(dataFileName, "CreatePatient");            
        AM.Menu.TopMenu.Select(driver, "File/New/Patient");
        AM.Patient.AddNewPatient.FillAddNewPatient(driver);
        AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");        
    }     
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
    //@Step- Navigate Patient Manager --> Schedule Task
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    Datatable.setCurrentRow(1);
    String PM_PatientName = Datatable.GetValue("PM_PatientName");
    
    //@ Step :To navigate to patient manager
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
    Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
    //@ Step :To select and schedule a Task 
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
    AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
    driver.findElement(By.id("Details2")).click();
    AM.Episode.TaskDetails.FillTaskDetails(driver);
    AM.Episode.TaskDetails.btn_UpdateTask(driver).click();
    
    //@ Step :To navigate to patient manager
    Waits.ForBrowserLoad(driver);
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
    Datatable.setCurrentRow(2);
   //@ Step :To select and schedule a Task 
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
    AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
    
   //@Step : To Select the Billing Manager
    Waits.ForBrowserLoad(driver);
    AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
    Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
    AM.Billing.BillingManager.SelectMenu(driver, "RAP/Not Ready");
    Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
    AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
    AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("0-90 days");
    Waits.ForGlobalAjaxLoader(driver);  
    if(!GlobalData.getPatientMRNumber().isEmpty()){
   	 	AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
    }
    else{
    	AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("PM_PatientName"));
    }
        AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
    
	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
