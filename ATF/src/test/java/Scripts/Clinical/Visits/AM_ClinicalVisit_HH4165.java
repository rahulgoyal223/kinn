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

public class AM_ClinicalVisit_HH4165 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH4165
		'JIRA ID						:	HH-4165
		'Description                    : 	To Verify user can't create/edit Off Market / Unlisted Medication with Longstanding and the
											Change/New flags enabled at the same time	
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies the user can't create/edit Off Market / Unlisted Medication with 
											Longstanding and the Change/New flags enabled at the same time	
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4165();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH4165() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH4165");
		Report.SetTestName("AM_ClinicalVisit_HH4165", "To Verify the user can't create/edit Off Market / Unlisted Medication with Longstanding and the "
				+ "Change/New flags enabled at the same time");
		Report.assignCategory("Visits");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH1518.xlsx";
		dataSheetName = "AM_BM2_MedicationProfile_HH4165";
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

        //@ Step Fill the Medication Profile with medication type as "Medication"
		AM.Menu.TopMenu.Select(driver, "View/Medication Profile");
        Datatable.loadDataSheet(dataFileName, dataSheetName);   
        Waits.ForBrowserLoad(driver);
        AM.Forms.Nursing.MedicationProfile.FillOffMarketUnlisted(driver);
        AM.Forms.Nursing.MedicationProfile.SaveAndAddAdditional(driver);
        
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


