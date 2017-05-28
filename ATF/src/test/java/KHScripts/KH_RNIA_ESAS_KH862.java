package KHScripts;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import components.SwitchWindow;
import KH.Forms.Nursing.RNIA;


public class KH_RNIA_ESAS_KH862{

	/****************************************************************
		 *Class name		: KH_RNIA_ESAS_KH862
		 *User Story		: KH416
		 *Author			: arun.prasath@kinnser.com
		 *Description		: Test to verify ESAS section is available in RN Initial Assessment under Vital Science/Diagnosis form and the saved values are displayed in the print view.
		 *Input Parameters	: Patient Name and Info
		 *Output Parameters	: None
		 *Assumptions		: Test Data is present in the Global Sheet.
		 *Use				: N/A
		 *Tags				: KH:SmokeTest
		******************************************************************/

	public static void main(String[] args) throws Exception {
		Test();
	}
		
	@Test
	public static void Test() throws Exception {
	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
			//@Reports Configuration
			Report.generateReportsFile("html","KH_RNIA");
			Report.SetTestName("KH_RNIA_ESAS","KH_RNIA_ESAS");
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
			RNIA.lnk_RNIA_Details(driver).click();
			KH.Menu.TopMenu.Select(driver, "View/RN Initial Assessment");
			RNIA.lnk_RNIA_VitalSignsDiagnosis(driver).click();
			
			//@Load Data and fill in Vital Signs/Diagnosis page
			Datatable.loadDataSheet(dataFileName, "FillRNIA-VSD");
			
			//To fill in the Vital Science/Diagnosis form's required fields
			RNIA.FillVitalSignForm(driver);
			
			//To assert the ESAS section is present in the RNIA Vital Science/Diagnosis form
			String ESAS = "Edmonton Symptom Assessment System"; 
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(RNIA.txt_RNIA_VitalSignsDiagnosis_ESAS(driver).getText(), ESAS);
			sa.assertAll();

			//Capturing screenshot of what is selected in ESAS table
			Report.attachScreenShotToReport(driver, RNIA.txt_RNIA_VitalSignsDiagnosis_ESAS(driver));
			//Click on Form Save
			KH.Forms.Nursing.RNIA.btn_save_continue(driver).click();
			
			//Go back to the patient and click on Print view
			KH.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			KH.Patient.PatientManager.SelectActivePatient(driver);
			RNIA.lnk_RNIA_PrintView(driver).click();
			SwitchWindow.to(driver, "Print Preview");
			
			//Assert the print view
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[1]/td[2]")).getText(), Datatable.GetValue("ESAS_Pain"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[2]/td[2]")).getText(), Datatable.GetValue("ESAS_Tiredness"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[3]/td[2]")).getText(), Datatable.GetValue("ESAS_Drowsiness"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[4]/td[2]")).getText(), Datatable.GetValue("ESAS_Nausea"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[5]/td[2]")).getText(), Datatable.GetValue("ESAS_LackofAppetite"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[6]/td[2]")).getText(), Datatable.GetValue("ESAS_ShortnessofBreath"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[7]/td[2]")).getText(), Datatable.GetValue("ESAS_Depression"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[8]/td[2]")).getText(), Datatable.GetValue("ESAS_Anxiety"));
			sa.assertEquals(driver.findElement(By.xpath("//*[@id='VitalSigns']/div[5]/div[2]/div[4]/table/tbody/tr[9]/td[2]")).getText(), Datatable.GetValue("ESAS_Wellbeing"));
			sa.assertAll();
						
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver, RNIA.txt_RNIA_VitalSignsDiagnosis_ESAS_PrintView(driver));

	}

	@AfterTest(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
