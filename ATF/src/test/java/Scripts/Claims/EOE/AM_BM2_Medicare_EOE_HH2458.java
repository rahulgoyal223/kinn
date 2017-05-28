package Scripts.Claims.EOE;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.SwitchWindow;
import components.Waits;

public class AM_BM2_Medicare_EOE_HH2458 {
	

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2458
	'JIRA ID						:	HH-2458
	'Description                    : 	Verify that after clicking on 'Resend' button, the 
										claim has to remain in Pending payment page in EOE
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies Resend functionality in 
										Claims Manager
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
	public static void Test() throws Exception {
		
	String PM_PatientName = null;
    String dataSheetName = null;
    /******************************************************************
     * Mandate to call below lines at every test case start up
     ******************************************************************/
    Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2458");
    Report.SetTestName("AM_BM2_Medicare_EOE_HH2458", "Verify that after clicking on 'Resend' button, the claim has to remain in Pending payment page in EOE");
    Report.assignCategory("EOE");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_HH2458.xlsx";
    dataSheetName = "AM_BM2_Medicare_HH2458_RAP";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
     if (Datatable.GetValue("CreatePatient").equals("Yes")) {

    	//@Step :To load sheet and add new patient
        Datatable.loadDataSheet(dataFileName, "CreatePatient");            
        //@Step :To select the required menu
        Thread.sleep(Waits.getSleepLevelFive());
        AM.Menu.TopMenu.Select(driver, "File/New/Patient");
        AM.Patient.AddNewPatient.FillAddNewPatient(driver);
        AM.Patient.AddNewPatient.AddPatient(driver);
                   
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
    AM.Patient.PatientManager.SelectInActivePatient(driver);
    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
    AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
    AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
    AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
    AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Patient History and Diagnoses");
	AM.Forms.Nursing.OasisSOC.FillPatientDiagnosisForm(driver);
	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
	AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Therapy Need and Plan of Care");
	AM.Forms.Nursing.OasisSOC.FillTherapyNeedForm(driver);
	AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
      
    
    //@ Step - Schedule Task in Episode Manager
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        
    AM.Patient.PatientManager.SelectInActivePatient(driver);        
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
    AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);

     //@ Step - Schedule Task in Episode Manager
    AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    components.Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
    AM.Patient.PatientManager.SelectInActivePatient(driver);
    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");

    AM.Forms.Nursing.OasisSOC.ApproveOasis(driver);    
    }
    
    Datatable.loadDataSheet(dataFileName, "Verify485");
    if(Datatable.GetValue("CMS485Check").equals("Yes")){
    	
    	//@ Step - Fill CMS 485 form from orders tab in episode manager
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
        AM.Patient.PatientManager.SelectInActivePatient(driver);  
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
        AM.Episode.EpisodeManager.ScheduleTask(driver);
        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
        AM.Forms.Orders.CMS485.FillCMS485Form(driver);
        AM.Forms.Orders.CMS485.SubmitCMS485(driver);
    	
    	AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    	AM.Patient.PatientManager.SelectInActivePatient(driver);
        components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
    	AM.Forms.Orders.CMS485.ApproveCMS485(driver);
    	
    	/*AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
    	AM.Patient.PatientManager.SelectInActivePatient(driver);
    	AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");        
    	AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
    	//AM.Episode.CMS485.CMS485_Approve(driver);
    	AM.Forms.Orders.CMS485.btn_Approve(driver).click();*/
    
      Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_HH2458_RAP");
      
      Thread.sleep(Waits.getSleepLevelFive());
      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
      components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
      
      if(!GlobalData.getPatientMRNumber().isEmpty()){
     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
      } 
      else{
     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
      }
      
      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	  AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	 	
	     //@Step - Approving the Claim and verifying the successful message------Pending Approval tab
	    Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		if(!GlobalData.getPatientMRNumber().isEmpty()){
	     	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	      } 
	      else{
	     	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	      }
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
			
		//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
			if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_RS_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
			AM.Billing.Claims.ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab. Save File");
				
			//@ Step - To navigate to the pending payment tab in Claims Manager-RAP
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");      
		      if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
			components.Waits.getSleepLevelFive();

			
			int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
			int rowIndex = components.ksGrid.getRowIndex(driver, "1.");
			String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			System.out.println(claimnum);
			
			//@Step - Navigating to Remittance Managers page
			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 				
			AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager"); 				
			
			//@Step - Adding Remittance				
			AM.Menu.TopMenu.Select(driver, "File/New/Remittance");
			AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
			AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
			AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
			AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
			AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
			
			//@Step - Verifying the Patient in RAP paid tab---------Paid tab
			Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_HH2458_RAP");
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			AM.Billing.BillingManager.SelectMenu(driver, "RAP/Paid");
			AM.Billing.Claims.ClaimsManager.lst_PD_Payer(driver).selectByVisibleText("Palmetto GBA");
			
			if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName); 		
    	       
    		 //@Step - EOE Creating claim  		
			AM.Menu.TopMenu.Select(driver,"Go To/Billing Manager");
			AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
			AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
			AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
			if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
			
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);			
			AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
			
			//@Step - Approve claim 
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
			AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
				
			//@Step - Sending the claim 
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
			AM.Billing.Claims.ClaimsManager.RS_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.	Save File");
					
			//@Step- Resending the claim 
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			Thread.sleep(Waits.getSleepLevelFive());
			if(!GlobalData.getPatientMRNumber().isEmpty()){
		     	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		      } 
		      else{
		     	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		      }
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_PP_ResendManually(driver).click();
            SwitchWindow.toTopWindow(driver);
	        AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
	        SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
	        AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.	Save File");
	        
           //@Step verify the patient in Pending Payment tab
			AM.Billing.Claims.ClaimsManager.lst_PP_Payer(driver).selectByVisibleText("Palmetto GBA");
			if(!GlobalData.getPatientMRNumber().isEmpty()){
	        	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	         }
	         else{
	        	 AM.Billing.Claims.ClaimsManager.txt_PP_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	         }
	        AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);
	        
	}
  }		
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    
}


