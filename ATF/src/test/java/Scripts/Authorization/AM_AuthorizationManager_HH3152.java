package Scripts.Authorization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_AuthorizationManager_HH3152 {
		
		/************************************************************************************
		'Class name             : 	AM_AuthorizationManager_HH3152
		'JIRA ID				:	HH-3152
		'Description            : 	To Verify that the tasks, which are marked as Missed Visit
									are not taken into account in the Authorization
		'Input Parameters      	: 	Patient Name.
		'Output Parameters 	 	: 	Approved Authorization status, Authorization Number status.
		'Assumptions         	: 	Test Data is present in the Global Sheet.
		'Use                 	: 	The following test verifies that tasks marked as Missed Visits
									are not taken into account in Authorizations
		'Tags                	: 	Regression, Smoke Test, E2E
		 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_AuthorizationManager_HH3152();
	}
	
	@Test(groups= {"AM_Regression", "AM_Clinical" })
 	public static void AM_AuthorizationManager_HH3152() throws Exception {
	    	
			String dataSheetName = null;  
	    	String PM_PatientName = null;
		   /******************************************************************
		   * Mandate to call below lines at every test case start up
		   * Initialize browser, login, setup for extent reports
		   * and load any necessary datasheets for test
		   ******************************************************************/
	       //@Reports Configuration
	      Report.generateReportsFile("html","AM_AuthorizationManager_HH3152");
	      Report.SetTestName("AM_AuthorizationManager_HH3152", "Verify that the tasks, which are marked as Missed Visit are not taken accound in the Authorization");
	      Report.assignCategory("Authorization");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	     //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	     //@Import Test data sheet
	      String dataFileName = "Authorizations\\AM_AuthorizationManager_HH3152.xlsx";
	      dataSheetName = "AM_AuthorizationManager_HH3152";
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
	      
	      //@Step - Navigate to PatientManager
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      AM.Menu.TopMenu.Select(driver, "File/New/Authorization");	      
	      AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
	      AM.Authorization.AddNewAuthorization.InsertUpdateAuth(driver);
	      AM.Menu.TopMenu.Select(driver, "Edit/Schedule");	      
	           
	      //@Step - To Schedule a task
	      Datatable.loadDataSheet(dataFileName, "AM_EpisodeManager_HH3152");
	      AM.Episode.EpisodeManager.ScheduleTask(driver); 
	      WebElement stale = Waits.StalenessPreset(driver);
	      AM.Episode.EpisodeManager.SelectTaskDetailsByRow(driver, "1");
	      Waits.ForElementStaleness(driver, stale);
	      
	      //@Step - To fill in task details
	      AM.Episode.TaskDetails.FillMissedVisit(driver);
	      
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      
	      //@Step - To navigate reconcile authorization and update
	      Datatable.loadDataSheet(dataFileName, "AM_AuthorizationManager_HH3152");
	      AM.Menu.TopMenu.Select(driver, "View/Reconcile Authorization");
	      String AuthNum = Datatable.GetValue("AE_Authorization");
	      WebElement stale2 = Waits.StalenessPreset(driver);
	      AM.Authorization.ReconcileAuthorization.MoveOrphanTask(driver, AuthNum);
	      Waits.ForElementStaleness(driver, stale2);
	      AM.Authorization.ReconcileAuthorization.VerifyReconciledMVTask(driver, "0");
	}
	
	@AfterClass(alwaysRun=true)
	public void Teardown() {
		components.Browser.teardownTest();
	}
	
}
