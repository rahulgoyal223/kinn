package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2577 {

	/************************************************************************************
	'Class name              : 	AM_BM2_Medicare_RAP_HH2577
	'JIRA ID				 :	HH-2577
	'Description             :  Verify the process Approve a claim from Pending Approval after correcting the claim
	'Input Parameters        : 	Patient Name
	'Output Parameters       : 	Task status
	'Assumptions             : 	Test Data is present in the Global Sheet.
	'Use                     : 	The following test verifies Verify the process Approve a claim from Pending Approval after correcting the claim
	'Tags                    : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2577();

	}
	
	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2577() throws Exception {

    	String dataSheetName    = null;  
    	String PM_PatientName   = null;
    	String RD_MrecordNumber = null;
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       * 
       ******************************************************************/
       //@Reports Configuration       
      Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2577");
      Report.SetTestName("AM_BM2_Medicare_RAP_HH2577", "AM_82697_Claims Manager_Approve a claim from Pending Approval to Ready to Send Tab");
      Report.assignCategory("RAP");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
      //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
      //@Import Test data sheet
      String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2577.xlsx";
      dataSheetName = "AM_BM2_Medicare_RAP_HH2577";
      Datatable.loadDataSheet(dataFileName, dataSheetName);
      //**********************************************************************     
      
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
      
      Datatable.loadDataSheet(dataFileName, "VerifyOASIS");    
      //@Step :To verify and fill the OASIS
      if(Datatable.GetValue("OASISCheck").equals("Yes")){
      //@ Step - Fill patient OASIS demographics form and Save
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
      Datatable.loadDataSheet(dataFileName, "Verify485");       
      if (Datatable.GetValue("CMS485Check").equals("Yes")) {
      //@ Step - Fill CMS 485 form from orders tab in episode manager
      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
      AM.Patient.PatientManager.SelectActivePatient(driver);        
      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
      AM.Episode.EpisodeManager.ScheduleTask(driver);
      AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
      AM.Forms.Orders.CMS485.FillCMS485Form(driver);
      AM.Forms.Orders.CMS485.btn_Submit(driver).click();      
      
      //@Step - Approve CMS 485 form
      Waits.ForBrowserLoad(driver);
      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
      AM.Patient.PatientManager.SelectActivePatient(driver);
      components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
      AM.Forms.Orders.CMS485.btn_Approve(driver).click();      
      }
      
      //@Step - : To Select the Billing Manager
      Waits.ForBrowserLoad(driver);
      Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_Remittance");
      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
      
     //@ Step - To select the required option from drop down
      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
    		
      //@ Step - To select the required option from drop down
      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");			
      Waits.ForGlobalAjaxLoader(driver);
      
      //@ Step - To get the required value from sheet
      if(!GlobalData.getPatientMRNumber().isEmpty()){
      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
       }else {
      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(RD_MrecordNumber);
      	  
       }
    		
      //@Step - Create Claim for the Patient and verifying the successful message
      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
      AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
    		
      //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
      Waits.ForGlobalAjaxLoader(driver);
      if(!GlobalData.getPatientMRNumber().isEmpty()){
     	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
      }else {
     	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(RD_MrecordNumber);
     	  
      }
      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
      AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
    		
      //@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
      Waits.ForGlobalAjaxLoader(driver);
      if(!GlobalData.getPatientMRNumber().isEmpty()){
     	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
      }else {
     	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(RD_MrecordNumber);
     	  
      }
      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
      AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
      AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");	
      //@Step - getting Claim number of the patient
      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
      AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
      Waits.ForGlobalAjaxLoader(driver);
    		
      if(!GlobalData.getPatientMRNumber().isEmpty()){
      	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
       }else {
      	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(RD_MrecordNumber);
      	  
       }
        AM.Billing.Claims.ClaimsManager.PP_SelectPatient(driver, PM_PatientName);
   	    AM.Billing.Claims.ClaimsManager.btn_PP_Markrejected(driver).click();
        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
       //@Step - Correct the claim in Rejected/Cancelled tab
        Waits.ForBrowserLoad(driver);
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");
		AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.lst_RC_Type(driver).selectByVisibleText("Rejected");
		Waits.ForGlobalAjaxLoader(driver);
	      if(!GlobalData.getPatientMRNumber().isEmpty()){
	      	 AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	       }else {
	      	 AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(RD_MrecordNumber);
	      	  
	       }
		 AM.Billing.Claims.ClaimsManager.SelectPatient(driver, PM_PatientName);
		 AM.Billing.Claims.ClaimsManager.btn_RC_Claimactions(driver).click();
	     AM.Billing.Claims.ClaimsManager.btn_RC_Correct(driver).click();
	     Waits.ForElementVisibility(driver, AM.Billing.Claims.ClaimsManager.btn_Correct_Yes(driver));
		 AM.Billing.Claims.ClaimsManager.btn_Correct_Yes(driver).click();
		 AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
		//@Step Pending approval tab after correcting the claim
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");			
		Waits.ForGlobalAjaxLoader(driver);
		if(!GlobalData.getPatientMRNumber().isEmpty()) {
			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		} else {
			AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("PA_MrecordNumber"));
		}			
		AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
		
      //Step Approving the claim
		 Waits.ForBrowserLoad(driver);
	     AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	     Waits.ForGlobalAjaxLoader(driver);
	      if(!GlobalData.getPatientMRNumber().isEmpty()){
	      	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	       }else {
	      	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(RD_MrecordNumber);
	      	  
	       }
	     AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	     AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
	     AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
	    		
	   //@Step - Verify the patient on Send manually tab and verifying the successful message----Ready to Send tab
	     AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
	     AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");	
	     Waits.ForGlobalAjaxLoader(driver); 
	     if(!GlobalData.getPatientMRNumber().isEmpty()) {
	    	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	     }else {
	    	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("PA_MrecordNumber"));
	     }	
	     	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);

	}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}

