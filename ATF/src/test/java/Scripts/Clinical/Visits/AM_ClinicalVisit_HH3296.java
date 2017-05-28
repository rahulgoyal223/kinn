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

public class AM_ClinicalVisit_HH3296 {

	/************************************************************************************
	'Class name                     : 	AM_ClinicalVisit_HH3296
	'JIRA ID						:	HH-3296
	'Description                    : 	To Verify Discharge Summary Task process in AM
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the schedule, update
										and completion of Discharge Summary in AM
	'Tags                           : 	Regression
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3296();
	}
	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3296() throws Exception {
		
	String PM_PatientName = null;
    String dataSheetName = null;
    /******************************************************************
     * Mandate to call below lines at every test case start up
     ******************************************************************/
    Report.generateReportsFile("html","AM_ClinicalVisit_HH3296");
    Report.SetTestName("AM_ClinicalVisit_HH3296", "AM_Clinical Visit_To Verify Discharge Summary Task process in AM ");
    Report.assignCategory("ClinicalVisit");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3296.xlsx";
    dataSheetName = "AM_OT_Discharge Summary";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
    if (Datatable.GetValue("CreatePatient").equals("Yes")) {

     //@Step :To load sheet and add new patient
     Datatable.loadDataSheet(dataFileName, "CreatePatient");            
     //@Step :To select the required menu
     Waits.ForBrowserLoad(driver);
     AM.Menu.TopMenu.Select(driver, "File/New/Patient");
     AM.Patient.AddNewPatient.FillAddNewPatient(driver);
     Waits.ForBrowserLoad(driver);
     AM.Patient.AddNewPatient.AddPatient(driver);
                   
     }    
    
     Datatable.loadDataSheet(dataFileName, dataSheetName);
     if (!GlobalData.getPatientFullName().isEmpty()){
     PM_PatientName = GlobalData.getPatientFullName();
     }
     else {
     Datatable.GetValue("PM_PatientName");
     }  
     System.out.println("Patient Name is : "+ PM_PatientName);
     
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");
    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
    //TODO to replace with new table function or create method for task in tab by index?
    driver.findElement(By.id("Details1")).click();
    AM.Episode.TaskDetails.FillTaskDetails(driver);
    Waits.ForBrowserLoad(driver);
    //Check the OT visit is completed
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");     
    AM.Episode.EpisodeManager.VerifyStatus(driver, 1);
    Report.attachScreenShotToReport(driver);

	 }
	 
	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}


