package Scripts.Clinical.Visits;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import AM.Forms.Nursing.OasisCheckMessageType;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_ClinicalVisit_HH4163 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH4163
		'JIRA ID						:	HH-4163
		'Description                    : 	To Verify the user can't create/edit Medications with Longstanding and the 
											Change/New flags enabled at the same time	
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the user can't create/edit Medications with 
											Longstanding and the Change/New flags enabled at the same time	
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4163();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH4163() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH4163");
		Report.SetTestName("AM_ClinicalVisit_HH4163", "To Verify the user can't create/edit Medications with Longstanding and the "
				+ "Change/New flags enabled at the same time");
		Report.assignCategory("Visits");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH1518.xlsx";
		dataSheetName = "AM_BM2_MedicationProfile_HH4163";
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
        

        //@ Step Fill the Medication Profile with medication type as "Medication"
		AM.Menu.TopMenu.Select(driver, "View/Medication Profile");
        Waits.ForBrowserLoad(driver);
        Datatable.loadDataSheet(dataFileName, dataSheetName);  
        Waits.ForElementVisibility(driver, AM.Forms.Nursing.MedicationProfile.txt_PatientInfoHeader(driver));
        AM.Forms.Nursing.MedicationProfile.FillMedication(driver);
        AM.Forms.Nursing.MedicationProfile.SaveAndAddMedication(driver);

        //@ Step Edit existing medication
        AM.Forms.Nursing.MedicationProfile.OpenMedicationPopupToEdit(driver);

        //@ Step check flags enabled/diabled
        AM.Forms.Nursing.MedicationProfile.VerifyNewChangeLSFlags(driver);


	}

    @AfterClass(alwaysRun = true)
    public static void Teardown() {
    	components.Browser.teardownTest();
	}

}


