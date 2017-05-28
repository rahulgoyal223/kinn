package Scripts.Claims.ManagedCare;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

public class AM_BM2_ManagedCare_HH2409 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2409
	'JIRA ID						:	HH-2409
	'Description                    : 	To verify the Work flow for creating Managed care claims in
										Primary Managed Care
	'Input Parameters           	: 	Patient Name, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the functionality of
										To verify the Work flow for creating Managed care claims in BM2
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	
	public static void main(String[] args) throws Exception {
		AM_BM2_ManagedCare_HH2409();
	}
    
    @Test(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_ManagedCare_HH2409() throws Exception {

	    	String dataSheetName = null;
	    	String PM_PatientName = null;
	        /******************************************************************
	         * Mandate to call below lines at every test case start up
	         ******************************************************************/
	    	//@Reports Configuration
	        Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2409");
	        Report.SetTestName("AM_BM2_ManagedCare_HH2409", "To verify the Work flow for creating Managed care claims");
	        Report.assignCategory("ManagedCare");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2409.xlsx";
	        dataSheetName = "AM_BM2_ManagedCare_HH2409";
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	        /*****************************************************************/
	        //@Step - :To create new patient if needed

	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {

	       	//@Step :To load sheet and add new patient
	           Datatable.loadDataSheet(dataFileName, "CreatePatient");            
	           //@Step :To select the required menu
	           Thread.sleep(Waits.getSleepLevelFive());
	           AM.Menu.TopMenu.Select(driver, "File/New/Patient");
	           AM.Patient.AddNewPatient.FillAddNewPatient(driver);
	           AM.Patient.AddNewPatient.btn_AddPatient(driver).click();
	           AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");                      
	       }  
	        
	       Datatable.loadDataSheet(dataFileName, dataSheetName);
	       if (!GlobalData.getPatientFullName().isEmpty()){
	       	PM_PatientName = GlobalData.getPatientFullName();
	       }else {
	       	Datatable.GetValue("PM_PatientName");
	       }  
	       
	       //@step for creating authorization
	        AM.Menu.TopMenu.Select(driver,"File/New/Authorization");
	        AM.Authorization.AddNewAuthorization.AddEditAuthorization(driver);	       
	        AM.Authorization.AddNewAuthorization.btn_A_InsertUpdateAuth(driver).click();     
	        
	       Datatable.loadDataSheet(dataFileName, "VerifyOASIS");    
	       //@Step :To verify and fill the OASIS
	       if(Datatable.GetValue("OASISCheck").equals("Yes")){
	       	
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
	       
	       //@ Step - Schedule Task in Episode Manager
	       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");        

	       Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	       AM.Patient.PatientManager.SelectActivePatient(driver);
	       Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");      
	       AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");        
	       AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);	       
	        //@ Step - Schedule Task in Episode Manager
	       AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	       Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
	       AM.Patient.PatientManager.SelectActivePatient(driver);

	       Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	       AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "Oasis-C2 Start of Care");
	       AM.Forms.Nursing.OasisSOC.btn_Approve(driver).click();    
	       }
         
	       //@Step- Navigate Patient Manager --> Schedule Task
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		    Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		    AM.Patient.PatientManager.SelectActivePatient(driver);
		    Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	        AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
	        AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	        AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
	        Waits.ForBrowserLoad(driver);
	        driver.findElement(By.id("Details2")).click();
	        AM.Episode.TaskDetails.FillTaskDetails(driver);
	        AM.Episode.TaskDetails.btn_UpdateTask(driver).click();
	        
	        //@step Reconcile auth        
	        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
		    Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		    AM.Patient.PatientManager.SelectActivePatient(driver);
		    Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");  
	        AM.Menu.TopMenu.Select(driver, "View/Reconcile Authorization");	        
	        AM.Authorization.AuthorizationManager.lst_OT_Action(driver, 0).selectByIndex(1);
	        AM.Authorization.AuthorizationManager.lst_OT_Action(driver, 1).selectByIndex(1);
	        AM.Authorization.AuthorizationManager.btn_PO_Update(driver).click();
	  
	        //@Navigate --> Billing Manager 	         
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	        Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software"); 
	     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
	     	Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software"); 
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	     	Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty())
	     	{
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
	     	Verify.ExactPageHeader(driver, "Claims Manager: Primary Managed Care");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	Waits.ForGlobalAjaxLoader(driver);
	     	Thread.sleep(Waits.getSleepLevelFour());
	     	if(!GlobalData.getPatientMRNumber().isEmpty()) {
	        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        }else {
	        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }
	     	AM.Billing.Claims.ClaimsManager.PA_BillingPeriod(driver).click();
	     	Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software"); 
	     	
	        //@step Reconcile auth
	     	Waits.ForBrowserLoad(driver);
	        AM.Menu.TopMenu.Select(driver, "View/Reconcile Authorization");
	        AM.Authorization.ReconcileAuthorization.lst_OT_Action(driver,1).selectByVisibleText("Make Orphan");
	        AM.Authorization.AuthorizationManager.btn_PO_Update(driver).click();
	        
	      //@Navigate --> Billing Manager 
	        Waits.ForBrowserLoad(driver);
	        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
	     	Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
	     	AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Pending Approval");
	     	Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software"); 
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty())
	     	{
	        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	        } 
	        else
	        {
	        AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }
	     	AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
	     	//AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_ClaimFrequencyCode(driver).sendKeys(Datatable.GetValue("Claim_Frequency_Code"));;
	     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_Refresh(driver).click();
	    	AM.Billing.Claims.ClaimsManager.VerifyClaimsAlertmessage(driver, "This claim no longer has line items. You will not be able to save the claim in this state.");
	     	
	     	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_Cancel(driver).click();
	     	Waits.ForGlobalAjaxLoader(driver);
	     		     	
	     	//@Step - Not ready tab(is this step valid?)
	     	/*AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Not Ready");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("All Insurances");
	     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
	     	Waits.ForGlobalAjaxLoader(driver);
	     	if(!GlobalData.getPatientMRNumber().isEmpty())
	     	{
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());
	        } 
	        else
	        {
	        AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	        }	     	
	     	AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);*/

	}

    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
