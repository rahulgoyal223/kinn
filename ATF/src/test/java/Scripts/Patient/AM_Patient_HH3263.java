package Scripts.Patient;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_Patient_HH3263 {

	/************************************************************************************
	'Class name                     : 	AM_Patient_HH3263
	'JIRA ID						:	HH-3263
	'Description                    : 	To Verify the outcome of adding a patient with
										'Create Episode & Schedule Start of Care Visit'
										deselected
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies if the patient created
										appears in Pending Admissions page when a patient is 
										created deselecting 'Create Episode & Schedule Start
										of Care Visit' section
	'Tags                           : 	Regression
	 ************************************************************************************/
	
    public static void main(String[] args) throws Exception {
    	AM_Patient_HH3263();
	}
    
    @Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_Patient_HH3263() throws Exception {

        /******************************************************************
         * Mandate to call below lines at every test case start up
         * These will setup reports, Application and test data.
         ******************************************************************/
        //@Reports Configuration

        Report.generateReportsFile("html","AM_Patient_HH3263");
        Report.SetTestName("AM_Patient_HH3263", "AM_Patient_Verify the outcome of adding a patient with 'Create Episode & Schedule Start of Care Visit' deselected.");
        Report.assignCategory("Patient");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Clinical\\AM_Patient_HH3263.xlsx";
        String dataSheetName = "CreatePatient";
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        /*****************************************************************/
		
       	//@ Load Data and Add New Patient
        Datatable.loadDataSheet(dataFileName, "CreatePatient");            
        AM.Menu.TopMenu.Select(driver, "File/New/Patient");
        AM.Patient.AddNewPatient.FillAddNewPatient(driver);
        AM.Patient.AddNewPatient.AddPatient(driver);   

        //@Step - To verify the patient name is displayed under the Pending admission screen
        AM.PendingAdmissions.PendingAdmissions.SearchBox(driver, "Pending Admissions/Searchbox");        
        if(!GlobalData.getPatientFullName().isEmpty()){
        	 AM.PendingAdmissions.PendingAdmissions.VerifyPatient(driver, GlobalData.getPatientFullName());
        }
        else{
        	AM.PendingAdmissions.PendingAdmissions.VerifyPatient(driver, Datatable.GetValue("PM_PatientName"));
        }       
		
    }
    
	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		components.Browser.teardownTest();
	 }
}
