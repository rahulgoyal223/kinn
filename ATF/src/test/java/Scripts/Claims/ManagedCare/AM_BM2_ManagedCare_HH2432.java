package Scripts.Claims.ManagedCare;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_BM2_ManagedCare_HH2432 {

		/************************************************************************************
		'Class name                     : 	AM_BM2_ManagedCare_HH2432
		'JIRA ID						:	HH-2432
		'Description                    : 	Verify Patient Claim in Ready and Not Ready tabs in Managed Care Primary Claims 
		'Input Parameters           	: 	Patient Name
		'Output Parameters        		: 	
		'Assumptions                    : 	Test Data is present in the Global Sheet.
		'Use                            : 	The following test verifies that user can view Result from Ready Tab 
		'Tags                           : 	Regression, Smoke Test, E2E
		 ************************************************************************************/

		public static void main(String[] args) throws Exception {
			AM_BM2_ManagedCare_HH2432();

		}
	    
		@Test//FIXME(groups = { "AM_BM2_Claims", "AM_Regression" })
		public static void AM_BM2_ManagedCare_HH2432() throws Exception {
	    	String dataSheetName = null;
		    String PM_PatientName = null;
	    	//@Reports Configuration			
	        Report.generateReportsFile("html","AM_BM2_ManagedCare_HH2432");
	        Report.SetTestName("AM_BM2_ManagedCare_HH2432", "Verify Patient Claim in Ready and Not Ready tabs in Managed Care Primary Claims");
	        Report.assignCategory("ManagedCare");
	        //@Open Application and submit credentials
	        AM.Login.openAppAndSubmitCredentials();
	        //@ Get Current WebDriver
	        WebDriver driver = Browser.getDriver();
	        //@Import Test data sheet
	        String dataFileName = "Claims\\ManagedCare\\AM_BM2_ManagedCare_HH2432.xlsx";
	        dataSheetName = "AM_BM2_ManagedCare_HH2432";
	        Datatable.loadDataSheet(dataFileName, dataSheetName);
	      //**********************************************************************
	             
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

        //@View on Billing Summary in Non Billable Status
		AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
		AM.Patient.PatientManager.SelectActivePatient(driver);
		Datatable.setCurrentRow(1);
		AM.Menu.TopMenu.Select(driver, "Tools/Billing Summary");
		Waits.ForBrowserLoad(driver);
		
		//@ Creation of Billing Manager
		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		Waits.ForBrowserLoad(driver);
		components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
		AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Not Ready");
		AM.Billing.Claims.ClaimsManager.lst_RD_Payer(driver).selectByVisibleText("Managed Care Insurance");
		AM.Billing.Claims.ClaimsManager.lst_RD_ClaimAge(driver).selectByVisibleText("All Claims");
		Waits.ForGlobalAjaxLoader(driver);
		if(!GlobalData.getPatientMRNumber().isEmpty()){
		AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(GlobalData.getPatientMRNumber());;
		} 
		else{
		AM.Billing.Claims.ClaimsManager.txt_RD_Searchbox(driver).sendKeys(Datatable.GetValue("RD_MrecordNumber"));
		}
 	
		//@Step :To verify and fill the OASIS
		Datatable.loadDataSheet(dataFileName, "AM_BM2_ManagedCare_HH2432");    
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
        Datatable.loadDataSheet(dataFileName, "AM_BM2_VerifyCMS485_HH2432");	
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
                    
        //@View on Billing Summary in Billable Status
        AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");      
   		AM.Patient.PatientManager.SelectActivePatient(driver);
   		Datatable.setCurrentRow(1);
   		AM.Menu.TopMenu.Select(driver, "Tools/Billing Summary");
   		Thread.sleep(Waits.getSleepLevelFive());
   		//@ Creation of Billing Manager
   		AM.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
   		components.Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
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
   		int colIndex = components.ksGrid.getColumnIndex(driver, "Ready");
   		int rowIndex = components.ksGrid.getRowIndex(driver, PM_PatientName);
   		String ReadyStatus = components.ksGrid.getCellData(driver, rowIndex, colIndex);
   		components.ksGrid.verifyCellData(driver, rowIndex, colIndex, ReadyStatus);		

	}
		
	    @AfterClass(alwaysRun = true)
		 public static void Teardown() {
			 components.Browser.teardownTest();
		 }

}
