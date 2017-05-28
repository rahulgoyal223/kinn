package Scripts.Corporate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Config;
import components.Report;
import components.Waits;

public class CM_OASIS_Export_Mirrored_Task_HH4254 {

	/************************************************************************************
	'Class name                     : 	CM_OASIS_Export_HH4063
	'JIRA ID						:	HH-4063
	'Description                    : 	To Verify Corporate OASIS Export include mirrored OASIS-C2
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies mirrored OASIS-C2 is included in Corporate OASIS Export
	'Tags                           : 	Regression, Smoke Test, E2E
	************************************************************************************/
	
	public static void main(String[] args) throws Exception {
		CM_OASIS_Export_Mirrored_Task_HH4254();
	}

	@Test(groups = {})
	public static void CM_OASIS_Export_Mirrored_Task_HH4254() throws Exception {
		String dataSheetName = null;
		String PM_PatientName = null;
		String PD_MRN = null;
		//@Reports Configuration			
		Report.generateReportsFile("html","CM_OASIS_Export_Mirrored_Task_HH4254");
		Report.SetTestName("CM_OASIS_Export_Mirrored_Task_HH4254", "To Verify Mirrored OASIS-C2 Start Of Care (Nursing)");
		Report.assignCategory("RAP");
		
		Config.setAppUserNameKH("uber.user.corp");
		
		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();
		
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		
		//@Step Go to AM
		CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("KoS Test Clinic");
		Waits.ForBrowserLoad(driver);
		AM.Login.skipExpiredPassword(driver);
		
		//@Import Test data sheet
		String dataFileName = "Clinical\\ClinicalVisit\\CM_OASIS_Export_Mirrored_Task_HH4254.xlsx";
		dataSheetName = "AM_CV_OASISC2StartOfCare_HH4254";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************

		//@@Step  :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			//@Step :To load sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient");            
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);           
		}                  

		if (!GlobalData.getPatientFullName().isEmpty()) {
			PM_PatientName = GlobalData.getPatientFullName();
			PD_MRN = GlobalData.getPatientMRNumber();
		}else {
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		}
		
		System.out.println("Patient Name is : "
				+ PM_PatientName);
		
		System.out.println("Patient MRN is : "
				+ PD_MRN);
		
        Waits.ForBrowserLoad(driver);
		
	    //@ Step: Schedule E-Referral
	    Datatable.loadDataSheet(dataFileName, "Ereferral");
	    AM.Episode.EpisodeManager.SelectTaskTab(driver, "Misc");
	    AM.Episode.EpisodeManager.AddScheduleTasks(driver);
	    AM.Episode.EpisodeManager.btn_InsertUpdateTasks(driver).click();
	    AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "E-Referral");
	    
	    AM.Forms.Misc.EReferral.getReferTo(driver).selectByVisibleText("KOS Therapy");
	    AM.Forms.Misc.EReferral.singAndSubmit(driver);
	    
	    Waits.ForBrowserLoad(driver);
	    AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
	    AM.ReportsAdmin.ReferralManagement.SelectManageReferrals(driver);
	    
	    AM.ReportsAdmin.ReferralManagement.sendReferral(driver, PD_MRN);
	    
	    //@Step: Go to TM
		CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("KOS Therapy");
		Waits.ForBrowserLoad(driver);
		TM.Login.skipExpiredPassword(driver);
	    
	    //@Step: Accept Referral
		Waits.ForBrowserLoad(driver);
		AM.Menu.TopMenu.Select(driver, "Go To/Reports / Admin");
		AM.ReportsAdmin.ReferralManagement.SelectManageReferrals(driver);
		TM.ReportsAdmin.ReferralManagement.acceptReferral(driver, PM_PatientName);

		//@ Step: Create SOC 
		Datatable.loadDataSheet(dataFileName, "SOC");
		AM.Episode.EpisodeManager.SelectTaskTab(driver, "Nursing");
		AM.Episode.EpisodeManager.ScheduleTask(driver);
		
		//@ Step -  :To select and schedule a Task 		
		AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Start of Care");

		Waits.ForBrowserLoad(driver);
		
		//@Step Fill for OASIS-C2 Discharge Demographics
	 	AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
		AM.Forms.Nursing.OasisSOC.FillDemographicForm(driver);
		AM.Forms.Nursing.OasisSOC.SaveOasisPage(driver);
		
		AM.Menu.TopMenu.Select(driver, "File/OASIS Menu");
		AM.Forms.Nursing.OasisSOC.SubmitOasisWithSignature(driver);
		
		Waits.ForBrowserLoad(driver);

		//@Step Run Mirror Job
		WebDriver calltrackDriver = Calltrack.Login.openAppAndSubmitCredentials();
		Waits.ForBrowserLoad(calltrackDriver);
		Calltrack.Mirroring.RunMirrorShob(calltrackDriver);
		Waits.ForBrowserLoad(calltrackDriver);
		calltrackDriver.quit();
		
		//@Step Go to Corporate OASIS Export page
		CM.Swapuser.lst_CM_Swapuser(driver).selectByVisibleText("KoS Corporation Sandbox - DO NOT USE");
		Waits.ForBrowserLoad(driver);
		
		CM.Login.skipExpiredPassword(driver);
		
        AM.Menu.TopMenu.Select(driver, "Go To");
        driver.findElement(By.id("goto-corporate-item-link")).click();
        Waits.ForBrowserLoad(driver);
        
        CM.Administration.Menu.lnkCorporateOASISExport(driver).click();
        
        Datatable.loadDataSheet(dataFileName, "CorporateOASISExport");
        CM.Administration.CorporateOASISExport.FillFilters(driver);
        
        CM.Administration.CorporateOASISExport.getBtnApplyFilter(driver).click();
        CM.Administration.CorporateOASISExport.VerifyReportRow(driver, PM_PatientName, "OASIS-C2 Start of Care");
	}
	
	 @AfterClass(alwaysRun = true)
	 public static void Teardown() {
		 components.Browser.teardownTest();
	 }
}
