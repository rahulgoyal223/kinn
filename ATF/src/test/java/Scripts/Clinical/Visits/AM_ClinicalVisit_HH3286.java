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

public class AM_ClinicalVisit_HH3286 {

	/************************************************************************************
	'Class name                     : 	AM_ClinicalVisit_HH3286
	'JIRA ID						:	HH-3286
	'Description                    : 	To Verify OASIS-C1 Discharge (PT) Task Process in AM
	'Input Parameters             	: 	Patient Name,MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies OASIS-C1 Discharge (PT) should be in completed status
	'Tags                           : 	Smoke Test, Regression
	 ************************************************************************************/
	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3286();
	}

	@Test(groups = { "AM_Regression", "AM_Clinical" })
	public static void AM_ClinicalVisit_HH3286() throws Exception {
	String dataSheetName = null;
	String PM_PatientName = null;
	//@Reports Configuration			
    Report.generateReportsFile("html","AM_ClinicalVisit_HH3286");
    Report.SetTestName("AM_ClinicalVisit_HH3286", "To Verify OASIS-C1 Discharge (PT) Task Process in AM");
    Report.assignCategory("ClinicalVisit");
    //@Open Application and submit credentials
    AM.Login.openAppAndSubmitCredentials();
    //@ Get Current WebDriver
    WebDriver driver = Browser.getDriver();
    //@Import Test data sheet
    String dataFileName = "Clinical\\ClinicalVisit\\AM_ClinicalVisit_HH3286.xlsx";
    dataSheetName = "AM_CV_OASISDischargePT_HH3286";
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
     
    System.out.println("Patient Name is : "+ PM_PatientName);
    //@ Step -  :To navigate to patient manager
    Datatable.loadDataSheet(dataFileName, dataSheetName);	
 	if(Datatable.GetValue("OASISCheck").equals("Yes")){   
 	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
    AM.Patient.PatientManager.SelectActivePatient(driver);
    Datatable.setCurrentRow(1);

	//@ Step -  :To select and schedule a Task 
	AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");
	AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge (PT)");
       
    //@Step Fill for OASIS-C1 Discharge (OT) Demographics
	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
	AM.Forms.Nursing.OasisDischarge.FillDemographicForm(driver);
	AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);
		
	//@Step Fill OT Discharge Evaluation screen
	AM.Menu.TopMenu.Select(driver, "Edit/PT Discharge Evaluation");      
	AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "PT Discharge Evaluation");
    AM.Forms.Nursing.OasisDischarge.FillPTDischargeEvaluation(driver);
    AM.Forms.Nursing.OasisDischarge.SaveOasisPage(driver);
	    
	//@Step Verify Electronic Signature in OASIS-C1 Discharge (OT) screen
	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");      
	AM.Forms.Nursing.OasisDischarge.waitForOASISPage(driver, "Table Of Contents");
    AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
        
    //@Step Verify OASIS-C1 Discharge (OT) Approve screen
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge (PT)");
    AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);
        
    //@Step To Verify Status completed in OASIS-C1 Discharge (OT) screen
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
    AM.Patient.PatientManager.SelectActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "PT");
	AM.Episode.EpisodeManager.VerifyStatus(driver, 1);
	Report.attachScreenShotToReport(driver);   
	        
    }

}
	@AfterClass(alwaysRun = true)
	public static void Teardown() {
	 components.Browser.teardownTest();
	}
}
