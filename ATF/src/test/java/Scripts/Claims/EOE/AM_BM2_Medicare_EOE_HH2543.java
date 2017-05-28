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

public class AM_BM2_Medicare_EOE_HH2543 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_EOE_HH2543
	'JIRA ID						:	HH-2543
	'Description                    : 	Verify if user is able to Void an EOE and RAP 
										that is attached to claim
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	MRN
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the ability to void an
										EOE and RAP that are tied to a claim.
	'Tags                           : 	Regression, Smoke, E2E
	 ************************************************************************************/
	
	/*public static void main(String[] args) throws Exception {
	Test();
	}
	
	@Test//(groups = { "AM_BM2_Claims", "AM_Regression" })*/
		public static void Test() throws Exception {		
		
		String dataSheetName = null;
    	String PM_PatientName = null;
        /******************************************************************
         * Mandate to call below lines at every test case start up
         ******************************************************************/
    	//@Reports Configuration
        Report.generateReportsFile("html","AM_BM2_Medicare_EOE_HH2543");
        Report.SetTestName("AM_BM2_Medicare_EOE_HH2543", "Verify if user is able to Void an EOE and RAP that is attached to claim ");
        Report.assignCategory("EOE");
        //@Open Application and submit credentials
        AM.Login.openAppAndSubmitCredentials();
        //@ Get Current WebDriver
        WebDriver driver = Browser.getDriver();
        //@Import Test data sheet
        String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_HH2543.xlsx";
        dataSheetName = "AM_BM2_Medicare_HH2543_RAP";
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
        
          Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_HH2543_RAP");
          
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
    			
    			int colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
    			int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
    			String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
    			//@Step - Navigating to Remittance Managers page
    			Thread.sleep(Waits.getSleepLevelFive());
    			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 		
    			AM.Billing.BillingManager.ClickLink(driver, "Medicare/Remittance Manager");		
    			
    			//@Step - Adding claims in Remittance
    			Thread.sleep(Waits.getSleepLevelFive());
    			Thread.sleep(Waits.getSleepLevelFive());
    			AM.Menu.TopMenu.Select(driver, "File/New/Remittance");
    			AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
    	 
    			AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
    			AM.Billing.Remittance.RemittanceManager.FillClaimDetails(driver, 1);
    			AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click();
    			
    			//@Step - Verifying the Patient in RAP paid tab---------Paid tab
    			Datatable.loadDataSheet(dataFileName, "AM_BM2_Medicare_HH2543_RAP");
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
        	       
       
        //@ Step - To navigate to billing manager and create claim
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");        
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");        
        AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");        
        AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");          
        if(!GlobalData.getPatientMRNumber().isEmpty()){
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	      } 
	      else{
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	      }      
		AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);			
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
		
		//@Step - Approving the Claim and verifying the successful message 'Pending Approval tab'
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");		
		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(PM_PatientName);  
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);		
		AM.Billing.Claims.ClaimsManager.btn_PA_VoidEOEAndRAP(driver).click();	
	    SwitchWindow.toTopWindow(driver);
	    AM.Billing.Claims.ClaimsManager.btn_Resend_Yes(driver).click();
	    SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
		
		//@Step - Select Rejected / Cancelled and 	verify the patient details
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");	
		AM.Billing.Claims.ClaimsManager.lst_RC_Payer(driver).selectByVisibleText("Palmetto GBA");
		AM.Billing.Claims.ClaimsManager.lst_RC_Type(driver).selectByVisibleText("Void");		
        if(!GlobalData.getPatientMRNumber().isEmpty()){
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	      } 
	      else{
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	      }   
		//@Step - To verify Patient details
		colIndex = components.ksGrid.getColumnIndex(driver, "Claim#");
		rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
		claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, claimnum);
		
		colIndex = components.ksGrid.getColumnIndex(driver, "MRN");
		String MRN = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, MRN);
		//components.ksGrid.Verify(driver, MRN);
		
		colIndex = components.ksGrid.getColumnIndex(driver, "Billing Period");
		String BillingPeriod = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, BillingPeriod);
		//components.ksGrid.Verify(driver, BillingPeriod);
		
		colIndex = components.ksGrid.getColumnIndex(driver, "Total Charges");
		String TotalCharges = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, TotalCharges);
		//components.ksGrid.Verify(driver, TotalCharges);
		
		colIndex = components.ksGrid.getColumnIndex(driver, "Patient Name");
		String PatientName = components.ksGrid.getCellData(driver, rowIndex, colIndex);	
		AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PatientName);
		
		//@Step - To take screenshot
		Report.attachScreenShotToReport(driver);
		
		
        }
    }

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    
}
