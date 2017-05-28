package Scripts.Claims.ManagedCare;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Verify;
import components.Waits;

public class AM_BM2_ManagedCare_HH2707 {

		/****************************************************************
		 *Class name		: AM_BM2_ManagedCare_HH2707
		 *JIRA ID			: HH-2707
		 *Description		: AM_84334_BM2 Managed Care_Verify that claim is available in Pending Approval
		 *Input Parameters	: Patient Name
		 *Output Parameters	: MRN,Claim number, Billing period
		 *Assumptions		: Test Data is present in the Global Sheet.
		 *Use				: N/A
		 *Tags				: AM: Regression
		******************************************************************/
		
		public static void main(String[] args) throws Exception {
			AM_BM2_ManagedCare_HH2707();
		}
			
		@Test(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_ManagedCare_HH2707() throws Exception {
			
		String PM_PatientName = null;
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
			Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2707");
			Report.SetTestName("AM_BM2_ManagedCare_HH2707","To Verify that claim is available in Pending Payment tab after it was Resend Manually");
			Report.assignCategory("ManagedCare");
		//@Open Application and submit credentials
			AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
			String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2707.xlsx";
			Datatable.loadDataSheet(dataFileName, "AM_BM2_ManagedCare_84334");
		/*****************************************************************/
	
		    //@Step :To create new patient if needed
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
		      }  
		      
		      Datatable.loadDataSheet(dataFileName, "Verify485");       
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
		      Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_RAP_84335");
		      //@Step - Navigate to Billing Manager and create claims
		      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");	
		      Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
		      AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");		
		      Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software"); 
		      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
		      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");		      
		      Waits.ForGlobalAjaxLoader(driver);
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
	               AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      }else {
	            AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }      
		      AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);		      
		      AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
		      
		      //@Step - Move to pending approval and approve
		      Waits.ForGlobalAjaxLoader(driver);
		      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");		      
		      Waits.ForBrowserLoad(driver);
		      AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("All Insurances");
		      Waits.ForGlobalAjaxLoader(driver);
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
	               AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      }else {
	            AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }    
		      AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
		      AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
		      //add verification for alert messages 
		      
		      //@Step - Move to Ready To Send 
		      Waits.ForBrowserLoad(driver);
		      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
		      AM.Billing.Claims.ClaimsManager.lst_RS_Payer(driver).selectByVisibleText("All Insurances");
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
	               AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      }else {
	            AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }    
		      AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
		      AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
		      AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
		      
		      //@Step - Move to Pending payment and verify the patient details
		      Waits.ForBrowserLoad(driver);
		      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
		      AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Managed Care Insurance");
		      Waits.ForGlobalAjaxLoader(driver);
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
	               AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      }else {
	            AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }    
		      AM.Billing.Claims.ClaimsManager.PP_SelectPatient(driver, PM_PatientName);
		      AM.Billing.Claims.ClaimsManager.btn_PP_MCSResendManually(driver).click();
		      SwitchWindow.toTopWindow(driver);
		      AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
		      Waits.ForBrowserLoad(driver);
		      SwitchWindow.toParentWindow(driver);
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
	               AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      }else {
	            AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
		      
			  int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
			  int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
			  String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			  components.ksGrid.verifyCellData(driver, rowIndex, colIndex, claimnum);
			  
			  int colIndex1 = components.ksGrid.getColumnIndex(driver, "MRN");			 
			  String MRN = components.ksGrid.getCellData(driver, rowIndex, colIndex1);
			  components.ksGrid.verifyCellData(driver, rowIndex, colIndex1, MRN);			  
			  
			  int colIndex2 = components.ksGrid.getColumnIndex(driver, "Remittance Number");			 
			  String RemittanceNumber = components.ksGrid.getCellData(driver, rowIndex, colIndex2);
			  components.ksGrid.verifyCellData(driver, rowIndex, colIndex2, RemittanceNumber);

	}

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    
}
