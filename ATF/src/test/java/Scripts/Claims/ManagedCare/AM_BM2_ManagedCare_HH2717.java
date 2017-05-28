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

public class AM_BM2_ManagedCare_HH2717 {

		/************************************************************************************
		'Class name                     : 	AM_BM2_ManagedCare_HH2717
		'JIRA ID						:	HH-2717
		'Description                    : 	Verify that claim is available in Pending Approval tab when it is canceled from Paid tab and verify the data integrity for the patient
		'Input Parameters           	: 	Patient Name, Claim Number, MRN
		'Output Parameters        		: 	Patient
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies that claim is available in Pending Approval tab when it is canceled from Paid tab
		'Tags                           : 	Regression
		 ************************************************************************************/

		public static void main(String[] args) throws Exception {
			Test();

		  }
		@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
		  public static void Test() throws Exception {

			String dataSheetName = null;  
	    	String PM_PatientName = null;
	       /******************************************************************
	       * Mandate to call below lines at every test case start up
	       * 
	       ******************************************************************/
	       //@Reports Configuration
	      Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2717");
	      Report.SetTestName("AM_BM2_ManagedCare_HH2717", "Verify that claim is available in Pending Approval tab when it is canceled from Paid tab");
	      Report.assignCategory("ManagedCare");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	     //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	     //@Import Test data sheet
	      String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2717.xlsx";
	      dataSheetName = "AM_BM2_ManagedCare_84338";
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
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	        AM.Patient.PatientManager.SelectActivePatient(driver);
	        components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	        AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	        AM.Forms.Orders.CMS485.btn_Approve(driver).click();      
	        }
	        
	        //@Step - : To Select the Billing Manager
	        Waits.ForBrowserLoad(driver);
	        Datatable.loadDataSheet(dataFileName, "AM_BM2_ManagedCare_84338");
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	     	Waits.ForGlobalAjaxLoader(driver);
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
			
	     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
	     	
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
			Waits.ForGlobalAjaxLoader(driver);
			AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
			AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
			AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Managed Care Insurance: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
			
			//@Step- Move To Pending payment Tap and Verifying the Patient information
			
			AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");	     	
	     	Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty()){
	            AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        } 
	        else{
	       	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }		
	     	
	     	//@Step Claim number getting from pending payment page should be changed by integrating with other test cases
	     	int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
	     	
			int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName );
			String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
			
			//@Step - Navigating to Remittance Managers page
			Waits.ForBrowserLoad(driver);
			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 				
			AM.Billing.BillingManager.ClickLink(driver, "Managed Care/Remittance Manager"); 				
			
			//@Step - Adding Remittance				
			AM.Billing.Remittance.RemittanceManager.goToNewRemittance(driver);
			Waits.ForBrowserLoad(driver);
			AM.Billing.Remittance.RemittanceManager.AddRemittance(driver);
			AM.Billing.Remittance.RemittanceManager.AddClaims(driver, claimnum);
			AM.Billing.Remittance.RemittanceManager.FillClaimDetailsPrimarypayer(driver, 1);
			AM.Billing.Remittance.RemittanceManager.chk_PP_SecondarypayerEnable(driver).click();
			AM.Billing.Remittance.RemittanceManager.btn_AR_SaveandComplete(driver).click(); 

			//@Step - Verify the successful message
			AM.Billing.Remittance.RemittanceManager.VerifyAlertMessage(driver, "The Payment Remittance has been saved and completed.");
			
			//@Step - Verify in Paid tab
			
			AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager"); 		
			AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Paid");
			AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
			Waits.ForGlobalAjaxLoader(driver);
			if(!GlobalData.getPatientMRNumber().isEmpty())
	     	{
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        } 
	        else
	        {
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }
			
			AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);		
			AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);
			
			AM.Billing.Claims.ClaimsManager.btn_PD_Cancel(driver).click();
			SwitchWindow.toTopWindow(driver);
		    AM.Billing.Claims.ClaimsManager.btn_PP_Yes(driver).click();
			SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
			AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver,  "Claim(s) marked as cancelled and returned to the Pending Approval tabasdf.");
			
			//@Step - verifying Corrected claim available Pending Approval tab
	     	
	     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	     	Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty()){
	   		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	   		 } 
	   		else{
	   		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	   		}
	     	
	     	//Verify Fields should be displayed and data should match with patient information
	     	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);     	
	     	int colIndex1 = components.ksGrid.getColumnIndex(driver, "Claim #");     	
	     	String claimnum1 = components.ksGrid.getCellData(driver, rowIndex, colIndex1);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndex1, claimnum1);
	     	
	     	int colIndexMRN = components.ksGrid.getColumnIndex(driver, "MRN");     	
	     	String claimnumMRN = components.ksGrid.getCellData(driver, rowIndex, colIndexMRN);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndexMRN, claimnumMRN);    	
	     	     	
	     	int colIndexTotal = components.ksGrid.getColumnIndex(driver, "Total Charges");     	
	     	String claimnumTotal = components.ksGrid.getCellData(driver, rowIndex, colIndexTotal);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndexTotal, claimnumTotal);
	     	
	     	int colIndexStatus = components.ksGrid.getColumnIndex(driver, "Status");     	
	     	String claimnumStatus = components.ksGrid.getCellData(driver, rowIndex, colIndexStatus);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndexStatus, claimnumStatus);
	     	
	     	int colIndexBilling = components.ksGrid.getColumnIndex(driver, "Billing Period");     	
	     	String claimnumBilling = components.ksGrid.getCellData(driver, rowIndex, colIndexBilling);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndexBilling, claimnumBilling);
	     	
	     	//As per the manual test case instruction we have checked TOB
	     	int colIndexTOB = components.ksGrid.getColumnIndex(driver, "TOB");     	
	     	String claimnumTOB = components.ksGrid.getCellData(driver, rowIndex, colIndexTOB);
	     	components.ksGrid.verifyCellData(driver, rowIndex, colIndexTOB, claimnumTOB);     	 
	     	  

	}

	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }
	    
}
