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

public class AM_BM2_Medicare_RAP_HH2710 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2710
	'JIRA ID						:	HH-2710
	'Description                    : 	To Verify First Billable Visit status when Billable
										is completed and Non Billable visit scheduled the 
										next day is pending
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the first billable
										visit light is green in pending creation when billable
										visit is completed and non billable visit which is 
										scheduled for the next day is incomplete
										on the same day
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2710();
	}
    
    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2710() throws Exception {	
				
	    	String dataSheetName = null;
	    	String PM_PatientName = null;
	        /******************************************************************
	         * Mandate to call below lines at every test case start up
	         ******************************************************************/
	    	//@Reports Configuration
	        Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2710");
	        Report.SetTestName("AM_BM2_Medicare_RAP_HH2710", "Verify First Billable Visit status of a patient in RAP Pending creation claim page when first billable visit is completed and non billable visit which is scheduled next day is incomplete");
	        Report.assignCategory("RAP");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2710.xlsx";
	        dataSheetName = "AM_BM2_Medicare_RAP_HH2710";
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	        /*****************************************************************/
	             
	        //@Step - :To create new patient if needed
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {

	        	//Load Data and add new patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	            //@Step :To select the required menu
	            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
	                       
	        }        
	               
	        //@Step- Navigate Patient Manager --> Schedule Task
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	        AM.Patient.PatientManager.SelectActivePatient(driver);
	        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        
	        if (!GlobalData.getPatientFullName().isEmpty()){
	        	PM_PatientName = GlobalData.getPatientFullName();
	        }else {
	        	PM_PatientName = Datatable.GetValue("PM_PatientName");
	        }
	         
	         AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	         AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	         AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
	         
	         driver.findElement(By.id("Details2")).click();
	         AM.Episode.TaskDetails.FillTaskDetails(driver);
	         AM.Episode.TaskDetails.btn_UpdateTask(driver).click();   
	         Waits.ForBrowserLoad(driver);
	         AM.Menu.TopMenu.Select(driver, "View/Calendar");      
	         //TODO to add status verification
	         
	        //@ Step - :To select the required menu
	         Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	         Datatable.setCurrentRow(2);
	         AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	         AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	         AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click(); 
	         
	         //@Navigate --> Billing Manager 	         
	         Waits.ForBrowserLoad(driver);
	         AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
	         Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
	         AM.Billing.BillingManager.SelectMenu(driver, "RAP/Not Ready");
	         Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
	         AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
	         AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("0-90 days");
	         Waits.ForGlobalAjaxLoader(driver);
	         if(!GlobalData.getPatientMRNumber().isEmpty()){
	        	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
	         }
	         else{
	        	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(Datatable.GetValue("PP_MrecordNumber"));
	         }
	         
	         AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);

		}       
		
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
	}


