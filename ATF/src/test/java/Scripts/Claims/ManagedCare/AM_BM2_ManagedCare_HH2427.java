package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

public class AM_BM2_ManagedCare_HH2427 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2427 
	'JIRA ID						:	HH-2427
	'Description                    : 	To Verify user is able reopen a claim from Ready 
										to Send tab
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that the user is able
										to reopen managed care claims using primary payer 
										from Ready To Send tab and validate the data
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH2427();
	}
    
    @Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_ManagedCare_HH2427() throws Exception {
		
		//@Reports Configuration
		String dataSheetName = null;
	    String PM_PatientName = null;
		
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
    	Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2427");
		Report.SetTestName("AM_BM2_ManagedCare_HH2427","To verify that user is able to reopen a claim from Ready To Send tab");
        Report.assignCategory("ManagedCare");
        //@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		 String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2427.xlsx";
	     dataSheetName = "AM_BM2_ManagedCare_HH2427";
	     Datatable.loadDataSheet(dataFileName, dataSheetName);
	    /****************************************************************/	
	       //@@Step  :To create new patient
		   if (Datatable.GetValue("CreatePatient").equals("Yes")) {

		   //@Step :To load sheet
		   Datatable.loadDataSheet(dataFileName, "CreatePatient");            
		   AM.Menu.TopMenu.Select(driver, "File/New/Patient");
		   AM.Patient.AddNewPatient.FillAddNewPatient(driver);
		   AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
		   AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");           
		   }   
		   
		   if (!GlobalData.getPatientFullName().isEmpty()) {
		   PM_PatientName = GlobalData.getPatientFullName();
		   }else {
		   PM_PatientName = Datatable.GetValue("PM_PatientName");
		   }
		   Datatable.loadDataSheet(dataFileName, dataSheetName);
			
		    //@Step :To verify and fill the OASIS
			Datatable.loadDataSheet(dataFileName, "AM_BM2_ManagedCare_HH2427");    
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
	   
			//@ Step - Fill CMS 485 form from orders tab in episode manager
	       Datatable.loadDataSheet(dataFileName, "AM_BM2_MC_HH2427_CMS485");	
	       if(Datatable.GetValue("CMS485Check").equals("Yes")){
	       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	   	   AM.Patient.PatientManager.SelectActivePatient(driver);
	   	   Datatable.setCurrentRow(1);
	   	   AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");    
	   	   AM.Episode.EpisodeManager.ScheduleTask(driver);
	   	   AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
	   	   AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");            	
	   	   AM.Forms.Orders.CMS485.FillCMS485Form(driver);
	   	   AM.Forms.Orders.CMS485.btn_Submit(driver).click();
	        }
	    //@Step - Searching for Claim in Ready tab
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
     		AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
     	//@Step - Create Claim for the Patient and verifying the successful message
       	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
     	AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
 	    //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
     	Thread.sleep(Waits.getSleepLevelFive());
     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
     		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
     	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
		//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
     	Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
     		AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
		AM.Billing.Claims.ClaimsManager.btn_RS_Reopen(driver).click();
		SwitchWindow.toTopWindow(driver);
		AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
        SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
	    AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been reopened successfully and can be reviewed in the Pending Approval tab");
	    
	    Thread.sleep(Waits.getSleepLevelFive());
     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
     	AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("All Insurances");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
     		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
	    AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
		Report.attachScreenShotToReport(driver);	

	   }
    
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
   	}

