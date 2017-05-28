package Scripts.Authorization;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_AuthorizationManager_HH3182 {
		
		/************************************************************************************
		'Class name             : 	AM_AuthorizationManager_HH3182
		'JIRA ID				:	HH-3182
		'Description            :  	To Verify that the task, that are not billable are not 
									taken account in the Authorization.
		'Input Parameters      	: 	Patient Name
		'Output Parameters   	: 	Task status
		'Assumptions            : 	Test Data is present in the Global Sheet.
		'Use                    : 	The following test verifies that tasks that are non-billable 
									are not taken into account in an Authorization
		'Tags                   : 	Regression
		 ************************************************************************************/

		public static void main(String[] args) throws Exception {
			AM_AuthorizationManager_HH3182();
		}
		
		@Test(groups= {"AM_Regression", "AM_Clinical" })
	 	public static void AM_AuthorizationManager_HH3182() throws Exception {	
			
			String dataSheetName = null;  
			String PM_PatientName = null;
	       /******************************************************************
	       * Mandate to call below lines at every test case start up
	       * 
	       * 
	       ******************************************************************/
	       //@Reports Configuration
	      Report.generateReportsFile("html","AM_AuthorizationManager_HH3182");
	      Report.SetTestName("AM_AuthorizationManager_HH3182", "Verify that the task, that are not billable are not taken account in the Authorization");
	      Report.assignCategory("Authorization");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	     //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	     //@Import Test data sheet
	      String dataFileName = "Authorizations\\AM_AuthorizationManager_HH3182.xlsx";
	      dataSheetName = "AM_PatientManager_HH3182";
	      Datatable.loadDataSheet(dataFileName, dataSheetName);
	     //**********************************************************************

	      //@@Step  :To create new patient
	      if (Datatable.GetValue("CreatePatient").equals("Yes")) 
	      {
	      	//@Step :To Load The Sheet
	          Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	          AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	          AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	          AM.Patient.AddNewPatient.AddPatient(driver);			            
	      }
	      
	      if (!GlobalData.getPatientFullName().isEmpty()) {
	          PM_PatientName = GlobalData.getPatientFullName();
	      }
	      else {
	      Datatable.GetValue("PM_PatientName");
	      }      
	      
	      Datatable.loadDataSheet(dataFileName, dataSheetName);
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      
	      AM.Menu.TopMenu.Select(driver, "File/New/Authorization");
	      AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
	      AM.Authorization.AddNewAuthorization.InsertUpdateAuth(driver);
	      
	      AM.Menu.TopMenu.Select(driver, "Edit/Schedule");
	      
	      Datatable.loadDataSheet(dataFileName, "AM_EpisodeManager_HH3182");
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");      
	      AM.Episode.EpisodeManager.ScheduleTask(driver);
	      
	      Datatable.setCurrentRow(2);	  
	      Waits.ForBrowserLoad(driver);
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");  
	      AM.Episode.EpisodeManager.ScheduleTask(driver);
	      
	      Datatable.setCurrentRow(3);	
	      Waits.ForBrowserLoad(driver);   
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");  
	      AM.Episode.EpisodeManager.ScheduleTask(driver);
	      
	      Datatable.setCurrentRow(4); 
	      Waits.ForBrowserLoad(driver);    
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");  
	      AM.Episode.EpisodeManager.ScheduleTask(driver); 
	      
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      AM.Menu.TopMenu.Select(driver, "View/Reconcile Authorization");
	      AM.Authorization.ReconcileAuthorization.VerifyNumVisitsOnAuth(driver, 3);
		
			
	}

	@AfterClass(alwaysRun=true)
	public void Teardown() 	{
		components.Browser.teardownTest();
	}
}
