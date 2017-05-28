package Scripts.Clinical.Visits;

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
import AM.Forms.Nursing.OasisCheckMessageType;

public class AM_ClinicalVisit_HH3934 {

	/************************************************************************************
	'Class name              : 	AM_ClinicalVisit_HH3934
	'JIRA ID				 :	HH-3934
	'Description             :  To verify that M2005 displays the option (-) Not assessed/No Information in AM
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies if the patient created
								and Verify update to M2005 component
	'Tags                    : 	Regression, Clinical
	 ************************************************************************************/

   	public static void main(String[] args) throws Exception {
   		AM_ClinicalVisit_HH3934();
	 }
	
   	@Test(groups = {"AM_Regression", "AM_Clinical" })
	 public static void AM_ClinicalVisit_HH3934() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;  
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
       Report.generateReportsFile("html","AM_ClinicalVisit_HH3934");
       Report.SetTestName("AM_ClinicalVisit_HH3934", "To verify  M2005 displays option Not assessed/No Information");       
       Report.assignCategory("ClinicalVisit");
       
      //@Open Application and submit credentials
       AM.Login.openAppAndSubmitCredentials();
      
       //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
       
       //@Import Test data sheet
       String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3934.xlsx";
       dataSheetName = "CreatePatient";
       Datatable.loadDataSheet(dataFileName, dataSheetName);
       //********************************************************************** 
             
      //@@Step :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		} else {
				//@ Step -  :To navigate to patient manager
				AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
				AM.Patient.PatientManager.SelectActivePatient(driver);
			}
		
		System.out.println("Patient Name is : "+ PM_PatientName);
		
		//@ Step -  :To navigate to patient manager
 		Datatable.loadDataSheet(dataFileName, "AM_CV_OASISC2Discharge_HH3934");
        Datatable.setCurrentRow(1);
             
		//@ Step -  :To select and schedule a Task 
	    AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");
	    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge");
       
      //@Step Run Oasis check screen
       AM.Menu.TopMenu.Select(driver, "Tools/OASIS Check");
       AM.Forms.Nursing.OasisCheck.VerifyMessage(driver, OasisCheckMessageType.ERROR, "M2005 must have an option selected");
       
       //@Step Opens for OASIS-C2 Start of Care Medications
       AM.Menu.TopMenu.Select(driver, "Edit/Medications");
       AM.Forms.Nursing.OasisDischarge.VerifyM2005Options(driver);       
       driver.findElement(By.id("M2005_-")).click();
       
     //@Step Run Oasis check screen and check "M2005 must have an option selected" is not present.
       AM.Menu.TopMenu.Select(driver, "Tools/OASIS Check");
       AM.Forms.Nursing.OasisCheck.VerifyMissingMessage(driver, OasisCheckMessageType.ERROR, "M2005 must have an option selected");       
       
	}
   	 //@Teardown
       	@AfterClass(alwaysRun = true)
    	public static void Teardown() {
    		components.Browser.teardownTest();
     	}
}