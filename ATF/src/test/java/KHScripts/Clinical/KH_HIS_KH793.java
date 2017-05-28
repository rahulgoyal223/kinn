package KHScripts.Clinical;

import DataSource.Datatable;
import DataSource.GlobalData;
import KH.Forms.Nursing.RNIA;
import components.Browser;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;


public class KH_HIS_KH793{

	private static String strInputValue;
		/****************************************************************
		 *Class name		: KH_HIS_KH793
		 *Description		: Test to verify HIS Measures input into RN Initial Assessment are displayed in the patient's HIS Measure screen
		 *Input Parameters	: Patient Name and Info
		 *Output Parameters	: None
		 *Assumptions		: Test Data is present in the Global Sheet.
		 *Use				: N/A
		 *Tags				: KH:SmokeTest
		******************************************************************/

	public static void main(String[] args) throws Exception {
		KH_HIS_KH793();
	}
		
	@Test(groups = { "KH_Regression", "KH_Clinical", "SmokeTest" })
	public static void KH_HIS_KH793() throws Exception {
	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
			//@Reports Configuration
			Report.generateReportsFile("html","KH_RNIA");
			Report.SetTestName("KH_CreateRNIA","KH_CreateRNIA");
			Report.assignCategory("TBD");
			Report.assignCategory("Not Ready");
			//@Open Application and submit credentials
			KH.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "KHClinical\\HISMeasures\\KH_HISMeasures_KH793.xlsx";
			String dataSheetName = "RNIA";
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
			
			Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
			KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
			KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();
			Datatable.loadDataSheet(dataFileName, dataSheetName);
			KH.BenefitPeriod.BenefitPeriodManager.ScheduleTask(driver);
			driver.findElement(By.id("Details1")).click();
			KH.Menu.TopMenu.Select(driver, "View/RN Initial Assessment");
			Datatable.loadDataSheet(dataFileName, "FillRNIA");
			KH.Forms.Nursing.RNIA.FillRNIAComplete(driver);
			KH.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			KH.Patient.PatientManager.SelectActivePatient(driver);
			KH.Menu.TopMenu.Select(driver, "View/HIS Measures");
			
			KH.Patient.ViewHISMeasures.lst_SiteOfServiceAtAdmission(driver);
	        
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(KH.Patient.ViewHISMeasures.get_txt_HIS_F3000A_from_HIS(driver), KH.Patient.ViewHISMeasures.get_txt_HIS_F3000A_from_Excel(driver));
			sa.assertEquals(KH.Patient.ViewHISMeasures.get_txt_HIS_F3000A_Date_from_HIS(driver), KH.Patient.ViewHISMeasures.get_txt_HIS_F3000A_Date_from_Excel(driver));
			sa.assertAll();
						
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);

	}

	@AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
