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

public class AM_AuthorizationManager_HH3184 {
	 
		/************************************************************************************
		'Class name             : 	AM_AuthorizationManager_HH3184
		'JIRA ID			    :	HH-3184
		'Description            : 	To verify that patients marked as eligible and with an insurance that require Authorization are displayed in Pre-Authorization tab.
		'Input Parameters      	: 	Patient Name
		'Output Parameters   	: 	Patient Name
		'Assumptions            : 	Test Data is present in the Global Sheet.
		'Use                    : 	The following test verifies if the patient created
									marked as eligible and with an insurance that require Authorization are displayed in Pre-Authorization tab.
		'Tags                   : 	Regression
		 ************************************************************************************/
		public static void main(String[] args) throws Exception {
			AM_AuthorizationManager_HH3184();
		}
		
		@Test(groups= {"AM_Regression", "AM_Clinical" })
		public static void AM_AuthorizationManager_HH3184() throws Exception {
			
			String dataSheetName = null;
			String PM_PatientName = null;
			 /******************************************************************
	        * Mandate to call below lines at every test case start up
	        * 
	        ******************************************************************/
	       //@Reports Configuration
	       Report.generateReportsFile("html","AM_AuthorizationManager_HH3184");
	       Report.SetTestName("AM_AuthorizationManager_HH3184","Verify patients marked as eligible and with an insurance that require Auth are displayed in Pre-Auth tab");
	       Report.assignCategory("Authorization");
	       //@Open Application and submit credentials
	       AM.Login.openAppAndSubmitCredentials();
	       //@ Get Current WebDriver
	       WebDriver driver = Browser.getDriver();
	       //@Import Test data sheet
	       String dataFileName = "Authorizations\\AM_AuthorizationManager_HH3184.xlsx";
	       dataSheetName = "AM_PatientManager";
	       Datatable.loadDataSheet(dataFileName, dataSheetName);
	      //**********************************************************************
		
	     //@Step  :To create new patient if needed
	       if (Datatable.GetValue("CreatePatient").equals("Yes")) {

	       	   //@Step :To load sheet
	           Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	           AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	           AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	           AM.Patient.AddNewPatient.AddPatient(driver);           
	       }         
	       
	       if (!GlobalData.getPatientFullName().isEmpty()) {
	          	PM_PatientName = GlobalData.getPatientFullName();
	          }else {
	          	PM_PatientName = Datatable.GetValue("PM_PatientName");
	          } 
	       
	       Datatable.loadDataSheet(dataFileName, dataSheetName);
	       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	       AM.Patient.PatientManager.SelectActivePatient(driver);       
	       
	       
	       Datatable.loadDataSheet(dataFileName, "Add_EditAuthorization");
	       AM.Menu.TopMenu.Select(driver, "File/New/Authorization");
	       AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);
	       AM.Authorization.AddNewAuthorization.InsertUpdateAuth(driver);
	       
	       //@Step : To navigate to Authroization manager and verify patient name in Pre Authorization tab
	       AM.Menu.TopMenu.Select(driver, "Go To/Authorization Manager");
	       Verify.ExactPageTitle(driver, "Authorization Manager - Pending Verification | Kinnser Software");

	       AM.Authorization.AuthorizationManager.SelectTab(driver, "Pre-authorization");	
	       Waits.ForGlobalAjaxLoader(driver);
	       Verify.ExactPageTitle(driver, "Authorization Manager - Pre Authorization | Kinnser Software");
	       AM.Authorization.AuthorizationManager.ApprovePreAuth(driver, PM_PatientName);
	       AM.Authorization.AuthorizationManager.SelectTab(driver, "Ready to Schedule");
	       Verify.ExactPageTitle(driver, "Authorization Manager - Ready To Schedule | Kinnser Software");
	       AM.Authorization.AuthorizationManager.SearchInReady2Schedule(driver, PM_PatientName);
	       AM.Authorization.AuthorizationManager.VerifyPatient(driver, PM_PatientName);
	       Report.attachScreenShotToReport(driver);
	}
		
	@AfterClass(alwaysRun=true)
	public void Teardown() {
		components.Browser.teardownTest();
	}

}
