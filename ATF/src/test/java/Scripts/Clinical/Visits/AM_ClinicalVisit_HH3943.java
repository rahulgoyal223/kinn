package Scripts.Clinical.Visits;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import AM.Forms.Nursing.OasisCheckMessageType;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_ClinicalVisit_HH3943 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH3943
		'JIRA ID						:	HH-3943
		'Description                    : 	To Verify oasis check for M2001 in OASIS-C2 Start Of Care (Nursing)
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the options displayed for M2001 in the OASIS-C2 Start Of Care (Nursing)
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3943();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3943() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH3943");
		Report.SetTestName("AM_ClinicalVisit_HH3943", "To Verify M2001 in OASIS-C2 Start Of Care (Nursing)");
		Report.assignCategory("RAP");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3943.xlsx";
		dataSheetName = "AM_CV_OASISC2StartOfCare_HH3943";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		if (!GlobalData.getPatientFullName().isEmpty()) {
			PM_PatientName = GlobalData.getPatientFullName();
		}
		
		//@@Step  :To create new patient
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

		System.out.println("Patient Name is : "
				+ PM_PatientName);
		
		//@ Step -  :To select and schedule a Task 
		AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");
		
        //@Step Run Oasis check screen
        AM.Menu.TopMenu.Select(driver, "Tools/OASIS Check");
        AM.Forms.Nursing.OasisCheck.VerifyMessage(driver, OasisCheckMessageType.ERROR, "M2001 must have an option selected");


	}

	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		components.Browser.teardownTest();
	 }

}


