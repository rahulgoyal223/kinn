package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2412 {
		
		/************************************************************************************
		'Class name                     : 	AM_BM2_Medicare_RAP_HH2412
		'JIRA ID						:	HH-2412
		'Description                    : 	To Verify that claim preview is not generated for a patient without zipcode.											
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	Patient Name
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies Verify that claim preview is not generated for a patient without zipcode
		'Tags                           : 	Regression
		 ************************************************************************************/
		
	    public static void main(String[] args) throws Exception {
	    	AM_BM2_Medicare_RAP_HH2412();
		}
	    
	    @Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_Medicare_RAP_HH2412() throws Exception {	
			
		String dataSheetName = null;
		String PM_PatientName = null;
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2412");
			Report.SetTestName("AM_BM2_Medicare_RAP_HH2412","Verify that claim preview is not generated for a patient without zipcode");
			Report.assignCategory("RAP");
			Report.assignCategory("Ready");
			//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2412.xlsx";			
			dataSheetName = "AM_BM2_Medicare_RAP_HH2412";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/			            
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
	        
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");
	        //@Step Fill out OASIS
	        if(Datatable.GetValue("OASISCheck").equals("Yes")) {
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
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
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        

	            AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);        
	            
	            
	            //@ Step - Approve Scheduled Task
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	            components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	            AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
	        }
	        
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");
	        if(Datatable.GetValue("CMS485Check").equals("Yes")) {
	        	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");    
	            AM.Patient.PatientManager.SelectActivePatient(driver);        
	            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
	            AM.Episode.EpisodeManager.ScheduleTask(driver);
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	            AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	            AM.Forms.Orders.CMS485.btn_Submit(driver).click();      
	            
	            //@Step - Approve CMS 485 form
	            AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");       
	            AM.Patient.PatientManager.SelectActivePatient(driver);
	            components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	            AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	            AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");		            
	            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
	            AM.Forms.Orders.CMS485.chk_ReturnToClinician(driver).click();
	            AM.Forms.Orders.CMS485.txt_ElectronicSignature(driver).sendKeys(Datatable.GetValue("clinicianSignature"));
	            AM.Forms.Orders.CMS485.btn_Approve(driver).click();
	        }
	        
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
	        AM.Billing.Claims.ClaimsManager.lst_RE_Payer(driver).selectByVisibleText("Palmetto GBA");
	        AM.Billing.Claims.ClaimsManager.lst_RE_ClaimAge(driver).selectByVisibleText("All Claims");
	        Waits.ForGlobalAjaxLoader(driver);
	        AM.Billing.Claims.ClaimsManager.txt_RE_Searchbox(driver).sendKeys(PM_PatientName);   
	        
	        //@Step - to verify the error message
	        AM.Billing.Claims.ClaimsManager.ClaimPreviewIcon(driver).click();	
	        components.SwitchWindow.toTopWindow(driver);
	        Waits.ForElementVisibility(driver, AM.Billing.Claims.ClaimsManager.msg_RCP_ErrorMessage(driver));
	        AM.Billing.Claims.ClaimsManager
	        .VerifyErrorMessage(driver, "An error occurred when generating the claim preview. Not all information could be gathered because of missing or incorrect information.");
	        
	    }

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
	    
}
