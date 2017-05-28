package Scripts.Claims.RAP;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class AM_BM2_Medicare_OASIS_HH2730 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_OASIS_HH2730
	'JIRA ID						:	HH-2730
	'Description                    : 	Verify OASIS completion reflected in RAP
	'Input Parameters           	: 	
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies if the OASIS completion 
										is reflected in the RAP->Pending Creation, as a green light
	'Tags                           : 	Regression, Smoke, E2E
	 ************************************************************************************/
	
    public static void main(String[] args) throws Exception {
    	AM_BM2_Medicare_OASIS_HH2730();
	}
    
    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_OASIS_HH2730() throws Exception {

        /******************************************************************
         * Mandate to call below lines at every test case start up
         * These will setup reports, Application and test data.
         ******************************************************************/
        //@Reports Configuration
        Report.generateReportsFile("html","AM_BM2_Medicare_OASIS_HH2730");
        Report.SetTestName("AM_BM2_Medicare_OASIS_HH2730", "Verify if the OASIS completion is reflected in the RAP->Pending Creation, as a green light");
        Report.assignCategory("RAP");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_ClaimsManager_TaskLights.xlsx";
        String dataSheetName = "AM_BM2_Medicare_OASIS_HH2730";
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        /*****************************************************************/
 
        //@Step - Create New Patient, if Required
        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
        	//@ Load Data and Add New Patient
            Datatable.loadDataSheet(dataFileName, "CreatePatient");            
            AM.Menu.TopMenu.Select(driver, "File/New/Patient");
            AM.Patient.AddNewPatient.FillAddNewPatient(driver);
            AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
            AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        }
        
        //@ Step - To Load needed data sheet
        Datatable.loadDataSheet(dataFileName, dataSheetName);
 
        //@ Step - Fill patient OASIS demographics form and Save
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver); 
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
        AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
        Waits.ForElementVisibility(driver, AM.Forms.Nursing.OasisSOC.txt_DG_TimeIn(driver));
        AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
        AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
        AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);  
        AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	    AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	    AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();;
	    AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
	    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	    AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	    AM.Forms.Nursing.OasisSOC.btn_DG_Save(driver).click();
	    AM.Forms.Nursing.OasisSOC.saveAlertWindow(driver);
        
        //@ Step - Schedule Task in Episode Manager

        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver);  
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software"); 
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
        AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
        
        
        //@ Step - Approve Scheduled Task
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
        AM.Patient.PatientManager.SelectActivePatient(driver);    
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software"); 
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
        AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
        
        //@ Step - Verify Patient in Not ready tab status under RAP claims manager under 
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");        
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Not Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");        
        AM.Billing.Claims.ClaimsManager.lst_NR_Payer(driver).selectByVisibleText("Palmetto GBA");        
        AM.Billing.Claims.ClaimsManager.lst_NR_ClaimAge(driver).selectByVisibleText("All Claims");
        Waits.ForGlobalAjaxLoader(driver);
        if (!GlobalData.getPatientMRNumber().isEmpty()){
            AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
        }else {
            AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }

        if (!GlobalData.getPatientFullName().isEmpty()){
            AM.Billing.Claims.ClaimsManager.NR_VerifyPatient(driver, GlobalData.getPatientFullName());
        }else {
            AM.Billing.Claims.ClaimsManager.NR_VerifyPatient(driver, Datatable.GetValue("PM_PatientName"));
        }
        
    }
    
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}