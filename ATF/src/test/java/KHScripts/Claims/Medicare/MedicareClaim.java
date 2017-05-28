package KHScripts.Claims.Medicare;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MedicareClaim {

	public static void main(String[] args) throws Exception {
		/****************************************************************
		 *Class name		: KH_MedicareClaim
		 *Description		: Test to verify claim appears in Ready Tab of Medicare Claims
		 *Input Parameters	: Patient Name and Info
		 *Output Parameters	: None
		 *Assumptions		: Test Data is present in the Global Sheet.
		 *Use				: N/A
		 *Tags				: KH:SmokeTest
		******************************************************************/

		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
			//@Reports Configuration
			Report.generateReportsFile("html","KH_MedicareClaims");
			Report.SetTestName("KH_CreateMedicareClaim","KH_CreateMedicareClaim");
			Report.assignCategory("TBD");
			Report.assignCategory("Not Ready");
			//@Open Application and submit credentials
			KH.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "KHClaims\\Medicare\\KH_Medicare_Claims.xlsx";
			String dataSheetName = null;
			dataSheetName = "KH_MedicareClaimInfo";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/
		       //@Step - Create New Patient, if Required
	        if (Datatable.GetValue("CreatePatient").equals("Yes")) {
	        	//@ Load Data and Add New Patient
	            Datatable.loadDataSheet(dataFileName, "CreatePatient");

	            KH.Menu.TopMenu.Select(driver, "File/New/Patient");
				KH.Patient.AddNewPatient.FillAddNewPatient(driver);
				KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
	         }

	        String PM_PatientName = null;
	        if (!GlobalData.getPatientFullName().isEmpty()){
	        	PM_PatientName = GlobalData.getPatientFullName();
	        }else {
	        	PM_PatientName = Datatable.GetValue("PM_PatientName");
	        }

	         // @Add New Admission code here
	         Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
		     KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
		     KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();

			 //@Goto Billing Manager
			KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
			KH.Billing.BillingManager.ClickLink(driver, "Medicare/Ready");

			 //@ Step - Report screenshot
			 Report.attachScreenShotToReport(driver);
		     driver.close();

	
	}

}
