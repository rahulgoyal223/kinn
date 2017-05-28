package Scripts.Claims.RAP;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class AM_BM2_Medicare_CMS485_HH2614 {
	
	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_CMS485_HH2614
	'JIRA ID						:	HH-2614
	'Description                    : 	To verify CMS 485 completion reflected in RAP
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	MRN
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies If the CMS 485 is 
										completed, verify if the pateint's claim appears 
										in RAP-> Ready under Medicare
	'Tags                           : 	Regression, Smoke, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_CMS485_HH2614();
	}
	
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
    public static void AM_BM2_Medicare_CMS485_HH2614() throws Exception {
        //public void test() throws Exception {

    	String dataSheetName = null;    	
    	String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BM2_Medicare_CMS485_HH2614");
        Report.SetTestName("AM_BM2_Medicare_CMS485_HH2614", "If the CMS 485 is completed, verify if the patient's claim appears in RAP-> Ready under Medicare");
        Report.assignCategory("RAP");
        Report.assignCategory("CMS485");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_ClaimsManager_TaskLights.xlsx";
        dataSheetName = "AM_BM2_Medicare_OASIS_HH2730";
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
        
        if (!GlobalData.getPatientFullName().isEmpty()){
        	PM_PatientName = GlobalData.getPatientFullName();
        }else {
        	PM_PatientName = Datatable.GetValue("PM_PatientName");
        }
        System.out.println("Patient Name is : "+ PM_PatientName);
 
        Datatable.loadDataSheet(dataFileName, dataSheetName);
        //@ Step - Fill patient OASIS demographics form and Save
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver); 
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
        AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
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
        
        //@Step - Load data sheet for CMS485
        Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_CMS485_HH2614");
        
        //@ Step - Fill CMS 485 form from orders tab in episode manager 
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver); 
        Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
        AM.Episode.EpisodeManager.ScheduleTask(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
        AM.Forms.Orders.CMS485.FillCMS485Form(driver);
        AM.Forms.Orders.CMS485.btn_Submit(driver).click();      
        
        //@Step - Approve CMS 485 form
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager"); 
        Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");  
        AM.Patient.PatientManager.SelectActivePatient(driver);
        components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");

        AM.Forms.Orders.CMS485.btn_Approve(driver).click();

        //@Step - Verify patient is in ready tab under RAP claims manager
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
        components.Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
        AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
        
        if(!GlobalData.getPatientMRNumber().isEmpty()) {
         	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
          }
          else {
         	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(Datatable.GetValue("PP_MrecordNumber"));
          }                
        //@Step - To take screenshots
        Report.attachScreenShotToReport(driver);
    }
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
