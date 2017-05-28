package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2460 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_Medicare_RAP_HH2460
	'JIRA ID						:	HH-2460
	'Description                    : 	To verify that, clicking on Refresh Data button in Claim Worksheet should update data in the RAP Claim worksheet
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies user is able to verify that, clicking on Refresh Data button in Claim Worksheet should update data in the RAP Claim worksheet
	'Tags                           : 	Regression, Smoke Test, E2E
	 ************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2460();
	}
		
	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2460() throws Exception {
		
	String PM_PatientName = null;
    String dataSheetName = null;
    /******************************************************************
    * Mandate to call below lines at every test case start up
    * 
    ******************************************************************/
    
    Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2460");
    Report.SetTestName("AM_BM2_Medicare_RAP_HH2460", "Verify that, clicking on Refresh Data button in Claim Worksheet should update data in the RAP Claim worksheet");
    Report.assignCategory("RAP");
    AM.Login.openAppAndSubmitCredentials();
    WebDriver driver = Browser.getDriver();
    String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2460.xlsx";
    dataSheetName = "AM_BM2_RAP_82705";
    
    Datatable.loadDataSheet(dataFileName, dataSheetName);
    
   /*****************************************************************/
    
     if (Datatable.GetValue("CreatePatient").equals("Yes")) {

    	//@Step :To load sheet and add new patient
        Datatable.loadDataSheet(dataFileName, "CreatePatient");            
        //@Step :To select the required menu
        Waits.ForBrowserLoad(driver);
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
    if(Datatable.GetValue("CMS485Check").equals("Yes")){
    	
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
    
    	Datatable.loadDataSheet(dataFileName, dataSheetName);
      
      Waits.ForBrowserLoad(driver);
      AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
      components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
      AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
      AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
      AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
      Waits.ForGlobalAjaxLoader(driver);
      
      if(!GlobalData.getPatientMRNumber().isEmpty()){
     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
      } 
      else{
     	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
      }
      
      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
      AM.Billing.Claims.ClaimsManager.btn_RD_Createclaim(driver).click();
	  AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	  
	//@Step - Selecting the Patient Billing Period
	    Waits.ForBrowserLoad(driver);
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
		Waits.ForGlobalAjaxLoader(driver);
		
	      if(!GlobalData.getPatientMRNumber().isEmpty()){
	      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	       } 
	       else{
	      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	       }
	      
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName);
		
		Datatable.loadDataSheet(dataFileName, "EditPatient");
		
		//@Step - Navigating to Episode Manager page to Edit the Patient Name
		AM.Billing.Claims.ClaimsManager.PA_BillingPeriod(driver).click();
		Waits.ForBrowserLoad(driver);
        AM.Menu.TopMenu.Select(driver, "Edit/Patient");
        AM.Patient.EditPatient.EditPatientDetails(driver);
        AM.Patient.AddNewPatient.btn_UpdatePatient(driver).click();
        AM.Patient.AddNewPatient.clk_btnYes(driver, "Yes");
        
      //@Step - Navigating to Pending approval tab to Edit the Worksheet
        Waits.ForBrowserLoad(driver);
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Approval");
        AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
        Waits.ForGlobalAjaxLoader(driver);
        
	      if(!GlobalData.getPatientMRNumber().isEmpty()){
		      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		       } 
		       else{
		      	 AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		       }
		      
		AM.Billing.Claims.ClaimsManager.PA_SelectPatient(driver, PM_PatientName); 
		AM.Billing.Claims.ClaimsManager.Edit_PA_icon(driver).click();
		AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_Refresh(driver).click();
    	AM.Billing.Claims.ClaimsManager.VerifyClaimsAlertmessage(driver, "Claim data has been refreshed on the page but not saved.");
    	AM.Billing.Claims.HCFA_1500_08_05ClaimWorksheet.CWS_PA_SaveClose(driver).click();
     	AM.Billing.Claims.ClaimsManager.VerifyClaimsAlertmessage(driver, "Claim data has been saved.");
     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Palmetto GBA");
     	Waits.ForGlobalAjaxLoader(driver);
     	
		if(!GlobalData.getPatientMRNumber().isEmpty()){
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	      } 
	      else{
	     	 AM.Billing.Claims.ClaimsManager.txt_PD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
	      }
		AM.Billing.Claims.ClaimsManager.VerifyPatient(driver, PM_PatientName);

		
	 }
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
    
}
