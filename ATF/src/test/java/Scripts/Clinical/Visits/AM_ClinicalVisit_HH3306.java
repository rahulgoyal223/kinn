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

public class AM_ClinicalVisit_HH3306 {

	/************************************************************************************
	'Class name              : 	AM_ClinicalVisit_HH3306
	'JIRA ID				 :	HH-3306
	'Description             :  To verify the Schedule, update and completion of MSW Visit in AM.
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies if the patient created
								appears in MSW Visit the Schedule, update and completion of MSW Visit in AM.
	'Tags                    : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3306();
	}
	
	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3306() throws Exception {	

		String dataSheetName = null;
		String PM_PatientName = null;
	   /******************************************************************
	   * Mandate to call below lines at every test case start up
	   * Initialize browser, login, setup for extent reports
	   * and load any necessary datasheets for test
	   ******************************************************************/
       //@Reports Configuration   	  
       Report.generateReportsFile("html","AM_ClinicalVisit_HH3306");
       Report.SetTestName("AM_ClinicalVisit_HH3306","To verify the Schedule, update and completion of MSW Visit in AM.");
       Report.assignCategory("ClinicalVisit");
       //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
       //@ Get Current WebDriver
       WebDriver driver = Browser.getDriver();
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3306.xlsx";
       dataSheetName = "AM_ClinicalVisit_HH3306";
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
       
        //@ Step -Load the data sheet
        Datatable.loadDataSheet(dataFileName, dataSheetName);      
       
        //@ Step -  :To select and schedule a Task 
        Waits.ForBrowserLoad(driver);
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "MSW");
        AM.Episode.EpisodeManager.AddScheduleTasks(driver);
       
        	
        driver.findElement(By.id("Details1")).click();
       
        //Fill the task
        AM.Episode.TaskDetails.FillTaskDetails(driver);
        Waits.ForBrowserLoad(driver);
       
        //Verify the task status       
        if (!GlobalData.getPatientFullName().isEmpty()) {
        	PM_PatientName = GlobalData.getPatientFullName();
        }else {
        	PM_PatientName = Datatable.GetValue("PM_PatientName");
        }
        
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        System.out.println("Patient Name is : "+ PM_PatientName);
        
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
        AM.Patient.PatientManager.SelectActivePatient(driver);
        Waits.ForBrowserLoad(driver);
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "MSW");
        Report.attachScreenShotToReport(driver);
        AM.Episode.EpisodeManager.VerifyStatus(driver, 1); 
        
	}

	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
