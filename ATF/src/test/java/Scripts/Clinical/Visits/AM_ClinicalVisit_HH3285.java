package Scripts.Clinical.Visits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_ClinicalVisit_HH3285 {

	/************************************************************************************
	'Class name             : 	AM_ClinicalVisit_HH3285
	'JIRA ID				:	HH-3285
	'Description            : 	To verify the Schedule, update and completion of HHA Visit in AM.
	'Input Parameters      	: 	Patient Name
	'Output Parameters   	: 	Task status
	'Assumptions            : 	Test Data is present in the Global Sheet.
	'Use                    : 	The following test verifies if the patient created
								appears in HHA the Schedule, update and completion of HHA Visit in AM.
	'Tags                   : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3285();
	}
	
	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3285() throws Exception {	

		String dataSheetName = null;
		String PM_PatientName = null;
		 /******************************************************************
        * Mandate to call below lines at every test case start up
        * 
        ******************************************************************/
       //@Reports Configuration
       Report.generateReportsFile("html","AM_ClinicalVisit_HH3285");
       Report.SetTestName("AM_ClinicalVisit_HH3285", "Clinical Visit_To Verify PT Visit Task process in AM ");
       Report.assignCategory("ClinicalVisit");
       //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
       //@ Get Current WebDriver
       WebDriver driver = Browser.getDriver();
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3285.xlsx";
       dataSheetName = "AM_VerifyHHAVisit";
       Datatable.loadDataSheet(dataFileName, dataSheetName);
      //**********************************************************************
         
       //@@Step  :To create new patient if needed
       if (Datatable.GetValue("CreatePatient").equals("Yes")) {

       	   //@Step :To load sheet
           Datatable.loadDataSheet(dataFileName, "CreatePatient");            
           AM.Menu.TopMenu.Select(driver, "File/New/Patient");
           AM.Patient.AddNewPatient.FillAddNewPatient(driver);
           AM.Patient.AddNewPatient.AddPatient(driver);           
       }         
            
        //Load the data sheet
        Datatable.loadDataSheet(dataFileName, dataSheetName);
      
       
       //@ Step -  :To select and schedule a Task 
       Waits.ForBrowserLoad(driver);
       AM.Episode.EpisodeManager.SelectTaskTab(driver, "HHA");
       AM.Episode.EpisodeManager.AddScheduleTasks(driver);
       
       	
       driver.findElement(By.id("Details1")).click();
       
       //@Step - Fill the task
       AM.Episode.TaskDetails.FillTaskDetails(driver);
       Waits.ForBrowserLoad(driver);
       
       //@Step - Verify the task status          
       if (!GlobalData.getPatientFullName().isEmpty()) {
       	PM_PatientName = GlobalData.getPatientFullName();
       }else {
       	PM_PatientName = Datatable.GetValue("PM_PatientName");
       }     
       System.out.println("Patient Name : " +PM_PatientName);
       Datatable.loadDataSheet(dataFileName, dataSheetName);       
       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
       AM.Patient.PatientManager.SelectActivePatient(driver);
       Waits.ForBrowserLoad(driver);
       AM.Episode.EpisodeManager.SelectTaskTab(driver, "HHA");
       Report.attachScreenShotToReport(driver);     
       AM.Episode.EpisodeManager.VerifyStatus(driver, 1);
	}	
	
	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
