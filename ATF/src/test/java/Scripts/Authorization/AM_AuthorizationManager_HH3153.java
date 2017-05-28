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

public class AM_AuthorizationManager_HH3153 {
	
	/************************************************************************************
	'Class name             : 	AM_AuthorizationManager_HH3153
	'JIRA ID				:	HH-3153
	'Description            : 	To Verify if insurance does not require authorization, "N/A" is 
								displayed in Authorization Number column Of Ready to Schedule tab		
	'Input Parameters      	: 	Patient Name.
	'Output Parameters 	 	: 	Approved Authorization status, Authorization Number status.
	'Assumptions         	: 	Test Data is present in the Global Sheet.
	'Use                 	: 	The following test verifies  
								if insurance does not require authorization,
								"N/A" is displayed in Authorization Number column Of Ready to Schedule tab
	'Tags                	: 	Regression, Smoke Test, E2E
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_AuthorizationManager_HH3153();
	 }
	
   	@Test(groups = { "AM_Regression", "AM_Clinical" })
	 public static void AM_AuthorizationManager_HH3153() throws Exception {
    	
		String dataSheetName = null;  
    	String PM_PatientName = null;
       /******************************************************************
	   * Mandate to call below lines at every test case start up
	   * Initialize browser, login, setup for extent reports
	   * and load any necessary datasheets for test
	   ******************************************************************/
       //@Reports Configuration
      Report.generateReportsFile("html","AM_AuthorizationManager_HH3153");
      Report.SetTestName("AM_AuthorizationManager_HH3153", "if insurance does not require authorization, N/A is displayed in Authorization Number column Of Ready to Schedule tab");
      Report.assignCategory("Authorization");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
     //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
     //@Import Test data sheet
      String dataFileName = "Authorizations\\AM_AuthorizationManager_HH3153.xlsx";
      dataSheetName = "AM_Patient_AuthManager";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
     //**********************************************************************
    	
      //@Step - Precondition to Update Insurance
      if(Datatable.GetValue("InsuranceUpdate").equals("Yes")) {
      AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
      AM.ReportsAdmin.Administration.SelectTask(driver, "Administration/Insurance");
      AM.Insurance.InsuranceManager.SelectInsurance(driver, "Palmetto GBA").click();     
      AM.Menu.TopMenu.Select(driver, "Edit/Detail");
      AM.Insurance.InsuranceManager.rdb_CI_VisitAuthorizationReq_NO(driver).click();
      AM.Insurance.InsuranceManager.rdb_CI_DisplayInAM_YES(driver).click();
      AM.Insurance.InsuranceManager.btn_UpdateInsurance(driver).click();
      }
      
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
      
      	  //@Step - To navigate to Authorization Manager and check in Ready to Schedule tab
  	  Waits.ForBrowserLoad(driver);
      AM.Menu.TopMenu.Select(driver, "Go To/Authorization Manager");
      Verify.ExactPageTitle(driver, "Authorization Manager - Pending Verification | Kinnser Software");
      AM.Authorization.AuthorizationManager.MarkAuthElgible(driver, PM_PatientName);	      
      AM.Authorization.AuthorizationManager.SelectTab(driver, "Ready to Schedule");
      Verify.ExactPageTitle(driver, "Authorization Manager - Ready To Schedule | Kinnser Software");
      AM.Authorization.AuthorizationManager.SearchInReady2Schedule(driver, PM_PatientName);      
   
      //@Step - To verify the Patient Name,Authorization Number status
  	  int colIndex = components.ksGrid.getColumnIndex(driver, "Authorization Number");
  	  int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
	  String AuNo = components.ksGrid.getCellData(driver, rowIndex, colIndex);
	  components.ksGrid.verifyCellData(driver, rowIndex, colIndex, AuNo);	
	
	  Report.attachScreenShotToReport(driver);
	}
   	
   	@AfterClass(alwaysRun=true)
   	public void Teardown() {
		 components.Browser.teardownTest();
	 }

}
