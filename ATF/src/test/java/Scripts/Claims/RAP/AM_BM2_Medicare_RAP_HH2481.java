package Scripts.Claims.RAP;

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

public class AM_BM2_Medicare_RAP_HH2481 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2481
	'JIRA ID						:	HH-2481
	'Description                    : 	Create a RAP Claim with CMS 485 not completed 
	'Input Parameters            	: 	Patient Name, MRN
	'Output Parameters          	: 	MRN
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies If the CMS 485 is 
										completed, user should not be able to create a claim
	'Tags                          	: 	Regression, Smoke, E2E
	 ************************************************************************************/
	
		public static void main(String[] args) throws Exception {
			AM_BM2_Medicare_RAP_HH2481();
	}
		
	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2481() throws Exception {

		String dataSheetName = null;  
    	String PM_PatientName = null;
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       ******************************************************************/
       //@Reports Configuration
      Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2481");
      Report.SetTestName("AM_BM2_Medicare_RAP_HH2481", "To verify that RAP claim moves to Pending Payment tab when 'Mark Pending Payment' option is clicked");
      Report.assignCategory("RAP");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
     //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
     //@Import Test data sheet
      String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2481.xlsx";
      dataSheetName = "CMS485_TP88060";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
     //**********************************************************************
      
    //TODO To create a new function to take the formulae from excel using poi to fetch current date 
    //@@Step :To create new patient if needed
      if (Datatable.GetValue("CreatePatient").equals("Yes")) {

      	  //@Step :To load sheet
          Datatable.loadDataSheet(dataFileName, "CreatePatient");            
          AM.Menu.TopMenu.Select(driver, "File/New/Patient");
          AM.Patient.AddNewPatient.FillAddNewPatient(driver);
          AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
          AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");          
      }     
      
      if (!GlobalData.getPatientFullName().isEmpty()){
      	PM_PatientName = GlobalData.getPatientFullName();
      }else {
      	Datatable.GetValue("PM_PatientName");
      }  
      
      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyOasis");    
      //@Step :To verify and fill the OASIS
      if(Datatable.GetValue("OASISCheck").equals("Yes")){
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
      components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");        

      AM.Patient.PatientManager.SelectActivePatient(driver);
      Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
      AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();
      }     

    //@Step - Verify patient is in Not ready tab under RAP claims manager
      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
      components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Not Ready");
      components.Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
      Waits.ForGlobalAjaxLoader(driver);
      
      if(!GlobalData.getPatientMRNumber().isEmpty()) {
       	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
        }
        else {
       	 AM.Billing.Claims.ClaimsManager.txt_NR_Searchbox(driver).sendKeys(Datatable.GetValue("PP_MrecordNumber"));
      	}
      AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);  
        
	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
