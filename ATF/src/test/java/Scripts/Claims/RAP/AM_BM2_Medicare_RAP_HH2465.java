package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Verify;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2465 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2465
	'JIRA ID						:	HH-2465
	'Description                    : 	To verify that RAP claim moves to Pending Payment tab when 'Mark Pending Payment' option is clicked
	'Input Parameters             	: 	Patient Name, Claim Number
	'Output Parameters          	: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	When 'Mark Pending Payement' option is clicked in Ready to Send tab, RAP claim moves to 'Pending Payment' tab									
	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2465();
	}

	@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2465() throws Exception {

		String dataSheetName = null;  

    	String PM_PatientName = null;
       /******************************************************************
       * Mandate to call below lines at every test case start up
       *
       * 
       ******************************************************************/
       //@Reports Configuration
      Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2465");
      Report.SetTestName("AM_BM2_Medicare_RAP_HH2465", "To verify that RAP claim moves to Pending Payment tab when 'Mark Pending Payment' option is clicked");
      Report.assignCategory("RAP");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
     //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
     //@Import Test data sheet
      String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2465.xlsx";
      dataSheetName = "AM_BM2_TP85964";
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
      }     
      Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485");       
      if (Datatable.GetValue("CMS485Check").equals("Yes")) {
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
      Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
      AM.Forms.Orders.CMS485.btn_Approve(driver).click();      
      }
            
      //@Step - : To Select the Billing Manager
       Waits.ForBrowserLoad(driver);
       Datatable.loadDataSheet(dataFileName, "AM_BM2_TP85964");
       AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
       Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
       AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
       Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
       
		//@ Step - To select the required option from drop down
		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");

		//@ Step - To select the required option from drop down
		AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
		Waits.ForGlobalAjaxLoader(driver);

		//@ Step - To get the required value from sheet
		if(!GlobalData.getPatientMRNumber().isEmpty()){
			AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		} 
		else{
			AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		}

		//@Step - Create Claim for the Patient and verifying the successful message
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");

		//@Step - Approving the Claim and verifying the successful message------Pending Approval tab
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");

		//@Step - generate Claim by clicking on Mark Pending Payment button and verifying the successful message----Ready to Send tab
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
		Waits.ForGlobalAjaxLoader(driver);
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
		AM.Billing.Claims.ClaimsManager.btn_RS_markPendingPayment(driver).click();

		SwitchWindow.toTopWindow(driver);
		AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
		SwitchWindow.to(driver, "Claims Manager | Kinnser Software"); 

		//@Step - Verify the claim in RAP Pending Payment tab
		Waits.ForBrowserLoad(driver);
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
		AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
		Waits.ForGlobalAjaxLoader(driver);
		if(!GlobalData.getPatientMRNumber().isEmpty()){
			AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		} 
		else{
			AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		}
		AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);  

	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}


}
