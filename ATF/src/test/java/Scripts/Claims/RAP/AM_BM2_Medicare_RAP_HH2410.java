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

public class AM_BM2_Medicare_RAP_HH2410 {
	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2410
	'JIRA ID						:	HH-2410
	'Description                    : 	To Verify First Billable Visit status when Billable
										and non billable visits are scheduled and completed
										on the same day
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the first billable
										visit light is green in pending creation when billable
										and non billable visits are scheduled and completed
										on the same day
	'Tags                           : 	Regression
	 ************************************************************************************/
	
    public static void main(String[] args) throws Exception {
    	AM_BM2_Medicare_RAP_HH2410();
	}
    
    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2410() throws Exception {	
		
		String dataSheetName = null;
		String PM_PatientName = null;
		String EM_TaskName = null;
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2410");
			Report.SetTestName("AM_BM2_Medicare_RAP_HH2410","Verify First Billable Visit status in RAP Not Ready claim page when first billable and non billable visits are scheduled and completed on the same day");
			Report.assignCategory("RAP");
			Report.assignCategory("Not Ready");
			//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2410.xlsx";			
			dataSheetName = "AM_BM2_Medicare_RAP_HH2410";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/	
		       //@Step - Create New Patient, if Required
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
	        	//@ Load Data and Add New Patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");	            
	         }	        
	        
	        if (!GlobalData.getPatientFullName().isEmpty()){
	        	PM_PatientName = GlobalData.getPatientFullName();
	        }else {
	        	PM_PatientName = Datatable.GetValue("PM_PatientName");
	        }	   	      
	      
	        System.out.println("Patient Name :" + PM_PatientName);
	       //@Step - To Fill new episode
	       AM.PendingAdmissions.PendingAdmissions.SelectPatient(driver, PM_PatientName);	        
	       AM.PendingAdmissions.EpisodeList.lst_EL_Add(driver).selectByVisibleText("New Episode");	   
		   
	       Datatable.loadDataSheet(dataFileName, "FillNewEpisode");	       
	       AM.Episode.AddNewEpisode.Filladdnewepisode(driver);		   
		   AM.Episode.AddNewEpisode.btn_AddEpisode(driver).click();	
		   AM.Episode.AddNewEpisode.clk_btnYes(driver, "Yes");
		   
		   Datatable.loadDataSheet(dataFileName, dataSheetName );		 	
		   EM_TaskName = Datatable.GetValue("EM_Taskname");
		   System.out.println("TaskName : " + EM_TaskName);
		   //@Step - Navigate --> Patient manager --> Schedule task
		   AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");	
		   Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		   AM.Patient.PatientManager.SelectActivePatient(driver);	
		   Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
		   AM.Episode.EpisodeManager.AddScheduleTasks(driver);			  
		   AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();	
		   
		   			 
		   driver.findElement(By.id("Details1")).click();		   
	        
		   //@ Step - To fill --> Task details --> Update task
		   AM.Episode.TaskDetails.FillTaskDetails(driver);		        
		   AM.Episode.TaskDetails.btn_UpdateTask(driver).click();	 
		        
		   //@Step - Navigate --> Calendar menu		        
		   Verify.ExactPageHeader(driver, "Episode Manager");
		   AM.Menu.TopMenu.Select(driver, "View/Calendar");		        
	        
		   //@Step - Navigate --> Patient Manager --> Select Active patient 
		   AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");	
		   Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		   AM.Patient.PatientManager.SelectActivePatient(driver);	
		   Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
		        
		   //@Step - Episode manager --> To schedule task
		   Datatable.setCurrentRow(2);
		   EM_TaskName = Datatable.GetValue("EM_Taskname");	 
		   AM.Episode.EpisodeManager.AddScheduleTasks(driver);		        
		   AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
		 	        
		   
		   driver.findElement(By.id("Details2")).click();		 
	        
		   //@Step - Fill --> Task Details
		   AM.Episode.TaskDetails.FillTaskDetails(driver);	 		        
		   AM.Episode.TaskDetails.btn_UpdateTask(driver).click();	        
		        
		   //@Step - Navigate --> Billing manager --> Verify the patient First billable status	
		   AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		   Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
		   AM.Billing.BillingManager.SelectMenu(driver, "RAP/Not Ready");
		   Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
		   AM.Billing.Claims.ClaimsManager.lst_NR_Payer(driver).selectByVisibleText("Palmetto GBA");
		   AM.Billing.Claims.ClaimsManager.lst_NR_ClaimAge(driver).selectByVisibleText("0-90 days");        
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
