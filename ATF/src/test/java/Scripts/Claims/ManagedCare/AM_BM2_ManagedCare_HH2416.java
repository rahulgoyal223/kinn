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

public class AM_BM2_ManagedCare_HH2416 {


	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2416
	'JIRA ID						:	HH-2416
	'Description                    : 	To verify that user is able to click on 'Void' buttons in the Ready to Send tab of the Managed Care->Secondary Payer.
	'Input Parameters            	: 	Patient Name, MRN
	'Output Parameters         		: 	Patient Name,MRN
	'Assumptions                   	: 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the functionality of user is able to click on 'Void' buttons in the Ready to Send tab of the Managed Care->Secondary Payer.
	'Tags                           : 	Smoke Test
	 * @throws Exception 
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH2416();
	}
    
    @Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_ManagedCare_HH2416() throws Exception {
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		String PM_PatientName = null;
	    String dataSheetName = null;
	    
	    Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2416");
	    Report.SetTestName("AM_BM2_ManagedCare_HH2416", "AM_84411_Claims_BM2_MC Secondary_Verify if user can void a claim from Pending Payment tab");
	    Report.assignCategory("ManagedCare");
	    AM.Login.openAppAndSubmitCredentials();
	    WebDriver driver = Browser.getDriver();
	    String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2416.xlsx";
	    dataSheetName = "AM_BM2_MC_HH2416";
	    
	    Datatable.loadDataSheet(dataFileName, dataSheetName);
	    /*****************************************************************/	
	    
	   ///@@Step :To create new patient if needed
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
       	
       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
       //AM.Patient.PatientManager.SelectActivePatient(driver);
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

       
        //@ Step - Schedule Task in Episode Manager
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
             
     //@Step - Approve CMS 485 form
       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
       AM.Patient.PatientManager.SelectActivePatient(driver);
       components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
       AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
       AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
       AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();      
       }
       Datatable.loadDataSheet(dataFileName, dataSheetName);
    	//@step Billing Manager
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
     	components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
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
     	AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("All Insurances");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
     		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
     	AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
     	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
     	
     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
     	Thread.sleep(Waits.getSleepLevelFive());
        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
        Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
     		AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
        AM.Billing.Claims.ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
		AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Managed Care Insurance: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
		
		//@Step Creating a Remittance 
		Thread.sleep(Waits.getSleepLevelFive());
     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
     	AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Managed Care Insurance");
     	Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
     		AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }
     	//@Step Claim number getting from pending payment page should be changed by integrating with other test cases
     	int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
     	
		int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName );
		String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
		
		//@Step - Navigating to Remittance Managers page
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 				
		AM.Billing.BillingManager.ClickLink(driver, "Managed Care/Remittance Manager"); 				
		
		//@Step - Adding Remittance				
		AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
		Waits.ForBrowserLoad(driver);
		AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
		AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
		Waits.ForBrowserLoad(driver);
		AM.Billing.Remittance.RemittanceManager.FillClaimDetailsPrimarypayer(driver, 1);
		AM.Billing.Remittance.RemittanceManager.chk_PP_SecondarypayerEnable(driver).click();
		AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click(); 
        //@Step - Verify the successful message
		AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
		//@Step - Verify in Paid tab
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 		
		AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Paid");
		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
		Waits.ForGlobalAjaxLoader(driver);
     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
     		AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        }else {
        	AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }

		 AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);
		 //@step Secondary Managed care -Ready tab
		 Thread.sleep(Waits.getSleepLevelFive());
		 AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		 AM.Billing.BillingManager.SelectMenu(driver, "Secondary Payer/Ready");
		 AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	     Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
	     		AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        }else {
	        	AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }

	     	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	     	AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	     	
	     	//@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	     	Thread.sleep(Waits.getSleepLevelFive());
	     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	     	AM.Billing.Claims.ClaimsManager.lst_PA_Payer(driver).selectByVisibleText("All Insurances");
	     	Waits.ForGlobalAjaxLoader(driver);
		     if(!GlobalData.getPatientMRNumber().isEmpty()){
		        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		     }else {
		        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		     }
	     	AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
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
	        AM.Billing.Claims.ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_RS_Markpendingpayment(driver).click();
			SwitchWindow.toTopWindow(driver);
			AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
            AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Pending Payment and can be reviewed in the Pending Payment tab.");
            //@Step Verify the patient in Pending Payment tab 
			Thread.sleep(Waits.getSleepLevelFive());
	     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
	     	AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("MHMO Advantage Plan");
	     	Waits.ForGlobalAjaxLoader(driver);
		     if(!GlobalData.getPatientMRNumber().isEmpty()){
		        AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		     }else {
		        AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		     }
	     	AM.Billing.Claims.ClaimsManager.PP_SelectPatient(driver, PM_PatientName);
	     	AM.Billing.Claims.ClaimsManager.btn_PP_Void(driver).click();
	     	SwitchWindow.toTopWindow(driver);
	     	AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
	     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been voided and can be reviewed in the Rejected/Cancelled tab.");
			//@step Patient in Reject/Cancelled tab
	     	Thread.sleep(Waits.getSleepLevelFour());
	        AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");
	    	AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("MHMO Advantage Plan");
	    	AM.Billing.Claims.ClaimsManager.lst_RC_Type(driver).selectByVisibleText("Void");
	    	Waits.ForGlobalAjaxLoader(driver);
		     if(!GlobalData.getPatientMRNumber().isEmpty()){
		        AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		     }else {
		        AM.Billing.Claims.ClaimsManager.txt_RC_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		     }
	    	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);
	    	
		}
    
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
