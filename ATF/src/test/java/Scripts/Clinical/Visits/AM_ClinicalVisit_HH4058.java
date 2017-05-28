package Scripts.Clinical.Visits;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;

import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_ClinicalVisit_HH4058 {

	/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH4058
		'JIRA ID						:	HH-4058
		'Description                    : 	To Verify Wounds prefilled when having 2 or more visits same target date on AM 
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies Wounds prefilled when having 2 or more visits same target date on AM 
		'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH4058();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH4058() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","AM_ClinicalVisit_HH4058");
		Report.SetTestName("AM_ClinicalVisit_HH4058", "To Verify Wounds prefilled when having 2 or more visits same target date on AM");
		Report.assignCategory("RAP");
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH4058.xlsx";
		dataSheetName = "AM_CV_WoundCareWorksheet";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		//@ Step - Enable Clinic setting to use New Wound Care Worksheet
		AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin"); 
		AM.ReportsAdmin.ReportsAdmin.lnk_ClinicSetting(driver).click();
		if (! AM.ReportsAdmin.ClinicSetting.chk_NewWoundCareWorksheet(driver).isSelected())
		{
			AM.ReportsAdmin.ClinicSetting.chk_NewWoundCareWorksheet(driver).click();
			AM.ReportsAdmin.ClinicSetting.btn_SaveSettings(driver).click();
		}	
		
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
		
		
		//@ Step -  :To select the OASIS Task 
		AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");

		//@Step Opens for OASIS-C2 Start of Care Integumentary Status
		AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Integumentary Status");
		AM.Forms.Nursing.OasisSOC.AddWound(driver);
				
		//@Step to fill out the Wound Care Worksheet
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		AM.Forms.Nursing.WoundCareWorksheet.fillWoundCareWorhsheet(driver,1);
		AM.Forms.Nursing.OasisSOC.SaveWound(driver);
		AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);

		//@ Step -  :To navigate to patient manager
		Datatable.loadDataSheet(dataFileName, "AM_BM2_OTVisit");
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
		AM.Patient.PatientManager.SelectActivePatient(driver);
		Datatable.setCurrentRow(1);
		
		//@ Step -  :To select and schedule a Task
		AM.Episode.EpisodeManager.SelectTaskTab(driver, "OT");
		AM.Episode.EpisodeManager.ScheduleTask(driver);
		WebElement stale = Waits.StalenessPreset(driver);
		AM.Episode.EpisodeManager.SelectTaskDetailsByRow(driver, "1");
		Waits.ForElementStaleness(driver, stale);
		   
		//@ Step -  : Click on View--> OT Visit 
		AM.Menu.TopMenu.Select(driver, "View/OT Visit");
		
		//@ Steps to Fill the OT Visit
		AM.Forms.OT.OTVisitRecord.FillOTVisitRecord(driver);
		AM.Forms.OT.OTVisitRecord.GoToWoundCareWorksheet(driver);
		
		//@Step to fill put the Wound Care Worksheet for OT Visit
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		Datatable.setCurrentRow(2);
		AM.Forms.Nursing.WoundCareWorksheet.AddWound(driver);
		AM.Forms.Nursing.WoundCareWorksheet.fillWoundCareWorhsheet(driver,2);
		AM.Forms.Nursing.WoundCareWorksheet.SaveWound(driver);
		AM.Forms.Nursing.WoundCareWorksheet.SaveAndContinue(driver);
		Datatable.loadDataSheet(dataFileName, "AM_BM2_OTVisit");
		AM.Forms.OT.OTVisitRecord.FillElectronicSignature(driver);       
		AM.Forms.OT.OTVisitRecord.SubmitOTVisit(driver);
		Waits.ForBrowserLoad(driver);
		
		
		////@ Steps to Fill the SNV Visit
		Datatable.loadDataSheet(dataFileName, "AM_BM2_SNV");
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
	    AM.Patient.PatientManager.SelectActivePatient(driver);
	    AM.Episode.EpisodeManager.ScheduleTask(driver);
		WebElement stale2 = Waits.StalenessPreset(driver);
		AM.Episode.EpisodeManager.SelectTaskDetailsByRow(driver, "2");
		Waits.ForElementStaleness(driver, stale2);
		   
		//@ Step -  : Click on View--> SNV Visit 
		AM.Menu.TopMenu.Select(driver, "View/Skilled Nurse Visit");
		
		//@ Steps to Fill the SNV Visit
		AM.Forms.Nursing.NursingVisit.FillNursingVisitRecord(driver);
		AM.Forms.Nursing.NursingVisit.GoToWoundCareWorksheet(driver);
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		AM.Forms.Nursing.WoundCareWorksheet.VerifyPrefilledWounds(driver, 2);
				
	}

	@AfterClass(alwaysRun = true)
	 public static void Teardown() {
		components.Browser.teardownTest();
	 }

}


