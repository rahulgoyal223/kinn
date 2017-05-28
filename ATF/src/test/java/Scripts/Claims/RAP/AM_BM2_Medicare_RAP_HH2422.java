package Scripts.Claims.RAP;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_Medicare_RAP_HH2422 {

		/************************************************************************************
		'Class name              : 	AM_BM2_Medicare_RAP_HH2422
		'JIRA ID				 :	HH-2422
		'Description             :  To Verify user is able to add a new Comment to RAP claim
		'Input Parameters        : 	Patient Name
		'Output Parameters       : 	Task status
		'Assumptions             : 	Test Data is present in the Global Sheet.
		'Use                     : 	The following test verifies To Verify user is able to add a new Comment to RAP claim
		'Tags                    : 	Regression
		 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_BM2_Medicare_RAP_HH2422();
	}
		
	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void AM_BM2_Medicare_RAP_HH2422() throws Exception {
		
	    	String dataSheetName    = null;  
	    	String PM_PatientName   = null;
	    	String RD_MrecordNumber = null;
	       /******************************************************************
	       * Mandate to call below lines at every test case start up
	       * 
	       * 
	       ******************************************************************/
	       //@Reports Configuration       
	      Report.generateReportsFile("html","AM_BM2_Medicare_RAP_HH2422");
	      Report.SetTestName("AM_BM2_Medicare_RAP_HH2422", "o Verify user is able to add a new Comment to RAP claim");
	      Report.assignCategory("RAP");
	      //@Open Application and submit credentials
	      AM.Login.openAppAndSubmitCredentials();
	      //@ Get Current WebDriver
	      WebDriver driver = Browser.getDriver();
	      //@Import Test data sheet
	      String dataFileName = "Claims\\Medicare\\AM_BM2_Medicare_RAP_HH2422.xlsx";
	      dataSheetName = "AM_BM2_RAP_HH2422";
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
	      AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
	      AM.Patient.PatientManager.SelectActivePatient(driver);
	      components.Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
	      AM.Episode.EpisodeManager.SelectTaskTab(driver, "Orders");
	      AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "CMS 485");
	      AM.Forms.Orders.CMS485.btn_Approve(driver).click();          
	      }
	      
	      //@Step - : To Select the Billing Manager
	      Waits.ForBrowserLoad(driver);
	      Datatable.loadDataSheet(dataFileName, "AM_BM2_RAP_HH2422");
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
	      
	      //@Step - Add Comment on Pending Approval tab and verifying the Green Icon
	      AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
	      Waits.ForGlobalAjaxLoader(driver);
	      if(!GlobalData.getPatientMRNumber().isEmpty()){
	    	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
	      }else {
	    	 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(RD_MrecordNumber);
	    	  
	      }
	      AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	      AM.Billing.Claims.ClaimsManager.btn_PA_Comment(driver).click();
	      AM.Billing.Claims.ClaimsManager.txt_AC_AddComment(driver).sendKeys(Datatable.GetValue("AC_AddComment"));
	      Waits.ForBrowserLoad(driver);
	      AM.Billing.Claims.ClaimsManager.btn_AC_SaveComment(driver).click();
	      AM.Billing.Claims.ClaimsManager.toMouseHoverOnIcon(driver);
	      Waits.ForBrowserLoad(driver);
	      
	      //@Step - Verify Comment
	      String Comment = AM.Billing.Claims.ClaimsManager.clk_greenicon(driver).getAttribute("tooltip");
	      try {
	        	Assert.assertEquals(Comment, Datatable.GetValue("AC_AddComment"), "Comment saved was not the comment added");
	        	Report.Log(Status.PASS, "Actual Comment matches the expected.");
	        	Report.attachScreenShotToReport(driver);
	         
	        } catch(AssertionError ae){
	        	Report.Log(Status.FAIL, "Comment saved was not the comment added" + ae.getMessage());
	        	throw ae;
	        }
	      
	      
		}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
	
	}
	      
