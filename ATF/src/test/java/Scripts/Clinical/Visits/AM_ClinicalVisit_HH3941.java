package Scripts.Clinical.Visits;

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

public class AM_ClinicalVisit_HH3941 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH3941
		'JIRA ID						:	HH-3941
		'Description                    : 	To Verify M2001 in OASIS-C2 Start Of Care (Nursing)
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the options displayed for M2001 in the OASIS-C2 Start Of Care (Nursing)
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3941();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3941() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH3941");
		Report.SetTestName("AM_ClinicalVisit_HH3941", "To Verify M2001 in OASIS-C2 Start Of Care (Nursing)");
		Report.assignCategory("RAP");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3941.xlsx";
		dataSheetName = "AM_CV_OASISC2StartOfCare_HH3941";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		//@@Step  :To create new patient
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
		System.out.println("Patient Name is : "
				+ PM_PatientName);
		
		//@ Step -  :To navigate to patient manager
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
		AM.Patient.PatientManager.SelectActivePatient(driver);

		//@ Step -  :To select and schedule a Task 
		AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");

		//@Step Opens for OASIS-C2 Start of Care Medications
		AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Medications");
		AM.Forms.Nursing.OasisSOC.VerifyM2001Options(driver);
    

	}

	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}


