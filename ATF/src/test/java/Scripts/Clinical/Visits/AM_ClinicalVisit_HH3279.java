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

public class AM_ClinicalVisit_HH3279 {

		/************************************************************************************
		'Class name                     : 	AM_ClinicalVisit_HH3279
		'JIRA ID						:	HH-3279
		'Description                    : 	To Verify OASIS-C1 Discharge (Nursing) Task Process in AM
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies OASIS-C1 Discharge (Nursing) should be in completed status
		'Tags                           : 	Regression, Smoke Test, E2E
		 ************************************************************************************/
public static void main(String[] args) throws Exception {
	AM_ClinicalVisit_HH3279();
}

@Test(groups = { "AM_Regression", "AM_Clinical" })
public static void AM_ClinicalVisit_HH3279() throws Exception {
String dataSheetName = null;
String PM_PatientName = null;
//@Reports Configuration			
Report.generateReportsFile("html","AM_ClinicalVisit_HH3279");
Report.SetTestName("AM_ClinicalVisit_HH3279", "To Verify OASIS-C1 Discharge (Nursing) Task Process in AM");
Report.assignCategory("RAP");
//@Open Application and submit credentials
AM.Login.openAppAndSubmitCredentials();
//@ Get Current WebDriver
WebDriver driver = Browser.getDriver();
//@Import Test data sheet
String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3279.xlsx";
dataSheetName = "AM_CV_OASISDischarge_HH3279";
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
	Datatable.loadDataSheet(dataFileName, "AM_CV_OASISDischarge_HH3279");	
	if(Datatable.GetValue("OASISCheck").equals("Yes")){   
	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
    AM.Patient.PatientManager.SelectActivePatient(driver);
    Waits.ForBrowserLoad(driver);
    Datatable.setCurrentRow(1);

    //@ Step -  :To select and schedule a Task 
    AM.Episode.EpisodeManager.SelectTaskTab(driver,"Nursing");
    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge");
   
    //@Step Fill for OASIS-C1 Discharge Demographics
 	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	AM.Forms.Nursing.OasisDischarge.FillDemographicForm(driver);
	AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);
	
	//@Step Fill for OASIS-C1 Discharge Risk Assessment screen
	AM.Menu.TopMenu.Select(driver, "Edit/Risk Assessment");      
	AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "Risk Assessment");
    AM.Forms.Nursing.OasisDischarge.FillOTDischargeRiskAssessment(driver);
    AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);
    
	//@Step Fill for OASIS-C1 Discharge Sensory Status screen
    AM.Menu.TopMenu.Select(driver, "Edit/Sensory Status");      
	AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "Sensory Status");
    AM.Forms.Nursing.OasisDischarge.FillOTDischargeSensoryStatus(driver);
    AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);
    
	//@Step Fill for OASIS-C1 Discharge Pain screen
    AM.Menu.TopMenu.Select(driver, "Edit/Pain");      
	AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "Pain");
    AM.Forms.Nursing.OasisDischarge.FillOTDischargePain(driver);
    AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);

    //@Step Verify Electronic Signature in OASIS-C1 Discharge screen
    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
    AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "Table Of Contents");
    AM.Forms.Nursing.OasisDischarge.SubmitOasisWithSignature(driver);
    
    //@Step Verify OASIS-C1 Discharge Approve screen
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge");
	AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);
    
    //@Step To Verify Status completed in OASIS-C1 Discharge (OT) screen
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
    AM.Episode.EpisodeManager.VerifyStatus(driver, 2);
    Report.attachScreenShotToReport(driver);     
    
	}
 		
 }

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
	 components.Browser.teardownTest();
	}


	}


