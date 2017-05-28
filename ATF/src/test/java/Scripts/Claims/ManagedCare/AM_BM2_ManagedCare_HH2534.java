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

public class AM_BM2_ManagedCare_HH2534 {

	/************************************************************************************
	'Class name                     : 	AM_BM2_ManagedCare_HH2534
	'JIRA ID						:	HH-2534
	'Description                    : 	Verify that claim is in Pending Approval tab when it is corrected from Rejected/Canceled tab
	'Input Parameters           	: 	Patient Name, Claim Number, MRN
	'Output Parameters        		: 	Patient
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies that claim is in Pending Approval tab when it is corrected from Rejected/Canceled tab
	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		Test();

	}
	@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
	public static void Test() throws Exception {

		String dataSheetName = null;  
    	String PM_PatientName = null;
    	String RD_MrecordNumber = null;
       /******************************************************************
       * Mandate to call below lines at every test case start up
       * 
       ******************************************************************/
       //@Reports Configuration
      Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2534");
      Report.SetTestName("AM_BM2_ManagedCare_HH2534", "Verify that claim is available in Paid tab when payment for that claim is completed");
      Report.assignCategory("ManagedCare");
      //@Open Application and submit credentials
      AM.Login.openAppAndSubmitCredentials();
     //@ Get Current WebDriver
      WebDriver driver = Browser.getDriver();
     //@Import Test data sheet
      String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2534.xlsx";
      dataSheetName = "AM_ManagedCare_84337";
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
        Thread.sleep(Waits.getSleepLevelFive());
        Thread.sleep(Waits.getSleepLevelFive());
        Datatable.loadDataSheet(dataFileName, "AM_ManagedCare_84337");
        AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
     	AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
     	AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
      
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
     	Thread.sleep(Waits.getSleepLevelFive());
     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
     	AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
     	AM.Billing.Claims.ClaimsManager.btn_PA_Approve(driver).click();
     	AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");
		
     	//@Step - generate Claim by clicking on Send manually button and verifying the successful message----Ready to Send tab
     	Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Ready To Send");
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_RS_Claimactions(driver).click();
		AM.Billing.Claims.ClaimsManager.btn_RS_Sendmanually(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Managed Care Insurance: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
		
		//@Step- Move To Pending payment Tap and Verifying the Patient information
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Payment");		    	
     	
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
            AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
        } 
        else{
       	AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
        }		
		AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
		AM.Billing.Claims.ClaimsManager.btn_PP_Markreject(driver).click();
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) have been marked as Rejected and can be reviewed in the Rejected / Cancelled tab.");
		
		//@Step- Click on Rejected/Canceled tab and select Type as Rejected
		Thread.sleep(Waits.getSleepLevelFive());
		AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Rejected / Cancelled");		    	
		     	
		if(!GlobalData.getPatientMRNumber().isEmpty()){
		 AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		 } 
		 else{
		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		}		
	    AM.Billing.Claims.ClaimsManager.RD_SelectPatient(driver, PM_PatientName);
	    AM.Billing.Claims.ClaimsManager.btn_PP_Correct(driver).click();
	    SwitchWindow.toTopWindow(driver);
		AM.Billing.Claims.ClaimsManager.btn_PP_Yes(driver).click();
		SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
		AM.Billing.Claims.ClaimsManager.VerifyAlertmessage(driver, "Claim(s) returned to the Pending Approval tab for correction.");
		
		//@Step - verifying corrected claim available Pending Approval tab
     	Thread.sleep(Waits.getSleepLevelFive());
     	AM.Billing.Claims.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
     	if(!GlobalData.getPatientMRNumber().isEmpty()){
   		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
   		 } 
   		else{
   		AM.Billing.Claims.ClaimsManager.txt_PA_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
   		}	
     	
     	//Verify Fields should be displayed and data should match with patient information
        AM.Billing.Claims.ClaimsManager.VerifyPatient(driver,PM_PatientName);     
        int colIndex = components.ksGrid.getColumnIndex(driver, "Claim #");
        int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
        String claimnum = components.ksGrid.getCellData(driver, rowIndex, colIndex);
        components.ksGrid.verifyCellData(driver, rowIndex, colIndex, claimnum);
        
        int colIndexMRN = components.ksGrid.getColumnIndex(driver, "MRN");         
        String claimnumMRN = components.ksGrid.getCellData(driver, rowIndex, colIndexMRN);
        components.ksGrid.verifyCellData(driver, rowIndex, colIndexMRN, claimnumMRN);    
               
        int colIndexTotal = components.ksGrid.getColumnIndex(driver, "Total Charges");           
        String claimnumTotal = components.ksGrid.getCellData(driver, rowIndex, colIndexTotal);
        components.ksGrid.verifyCellData(driver, rowIndex, colIndexTotal, claimnumTotal);
        
        int colIndexStatus = components.ksGrid.getColumnIndex(driver, "Status");          
        String claimnumStatus = components.ksGrid.getCellData(driver, rowIndex, colIndexStatus);
        components.ksGrid.verifyCellData(driver, rowIndex, colIndexStatus, claimnumStatus);
        
        //As per the manual test case instruction we have checked TOB
        int colIndexTOB = components.ksGrid.getColumnIndex(driver, "TOB");         
        String claimnumTOB = components.ksGrid.getCellData(driver, rowIndex, colIndexTOB);
        components.ksGrid.verifyCellData(driver, rowIndex, colIndexTOB, claimnumTOB);      
         
        Report.attachScreenShotToReport(driver);      

	}
	
    @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }

}
