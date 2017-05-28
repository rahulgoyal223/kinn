package Scripts.Authorization;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_AuthorizationManager_HH3089 {
		
		/************************************************************************************
		'Class name           	: 	AM_AuthorizationManager_HH3089
		'JIRA ID				:	HH-3089
		'Description            : 	Upon clicking the Authorization Number in Ready to schedule tab, verify if the system redirects to Add/Edit Authorization page.
		'Input Parameters   	: 	Patient Name
		'Output Parameters 		: 	Patient Name
		'Assumptions          	: 	Test Data is present in the Global Sheet.
		'Use                 	: 	The following test verifies that Upon clicking the Authorization Number in Ready to schedule tab, verify if the system redirects to Add/Edit Authorization page.
		'Tags                	:  	Smoke Test
		 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_AuthorizationManager_HH3089();
	}
	
	@Test(groups= {"AM_Regression", "AM_Clinical" })
 	public static void AM_AuthorizationManager_HH3089() throws Exception {
	    	
			String dataSheetName = null;  
	    	String PM_PatientName = null;
		  /******************************************************************
		  * Mandate to call below lines at every test case start up
		  * Initialize browser, login, setup for extent reports
		  * and load any necessary datasheets for test
		  ******************************************************************/
	       //@Reports Configuration
	      Report.generateReportsFile("html","AM_AuthorizationManager_HH3089");
	      Report.SetTestName("AM_AuthorizationManager_HH3089", "Upon clicking the Authorization Number in Ready to schedule tab, verify if the system redirects to Add/Edit Authorization page.");
	      Report.assignCategory("Authorization");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	     //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	     //@Import Test data sheet
	      String dataFileName = "Authorizations\\AM_AuthorizationManager_HH3089.xlsx";
	      dataSheetName = "AM_AuthorizationManager_HH3089";
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
	      //@Step - Navigate to patient manager and select active patient
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      
	      Datatable.loadDataSheet(dataFileName, "AM_AuthorizationManager_HH3089");
	       AM.Menu.TopMenu.Select(driver, "File/New/Authorization");
	       AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
	       AM.Authorization.AddNewAuthorization.InsertUpdateAuth(driver);
	            
	      //@Step - Navigate to Authorization Manager 
	      Waits.ForBrowserLoad(driver);
	      AM.Menu.TopMenu.Select(driver, "Go To/Authorization Manager");
	      //Auth Manager not moving auths into pre-auth or ready to schedule tab after clicking eligible once bug is fixed update test
	      /*AM.Authorization.AuthorizationManager.btn_Branch_SelectAll(driver).click();
	      AM.Authorization.AuthorizationManager.btn_Insurance_SelectAll(driver).click();
	      AM.Authorization.AuthorizationManager.btn_ApplyFilters(driver).click();	
	      AM.Authorization.AuthorizationManager.txt_Searchbox(driver).sendKeys(PM_PatientName);	      
	      AM.Authorization.AuthorizationManager.SelectPatient(driver, PM_PatientName);	      
	      AM.Authorization.AuthorizationManager.btn_Eligibility(driver).click();	      
	      AM.Authorization.AuthorizationManager.SelectEligibility(driver, "Eligible");*/	 
	      
	      //@Step - Navigate to Pre Authorization tab and verify the patient name is displayed
	      Verify.ExactPageTitle(driver, "Authorization Manager - Pending Verification | Kinnser Software");
	      AM.Authorization.AuthorizationManager.SelectTab(driver, "Pre-authorization");
	      AM.Authorization.AuthorizationManager.ApprovePreAuth(driver, PM_PatientName);
	     
	      AM.Authorization.AuthorizationManager.SelectTab(driver, "Ready to Schedule");
	      Waits.ForGlobalAjaxLoader(driver);
	      Verify.ExactPageTitle(driver, "Authorization Manager - Ready To Schedule | Kinnser Software");
	      AM.Authorization.AuthorizationManager.SearchInReady2Schedule(driver, PM_PatientName);
	      AM.Authorization.AuthorizationManager.VerifyPatient(driver, PM_PatientName);
	}

	@AfterClass(alwaysRun=true)
	public void Teardown() {
		components.Browser.teardownTest();
	}
}
