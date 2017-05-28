package KHScripts.Clinical;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

public class KH_CHCNote_Schedule_KH815{


		/****************************************************************
		 *Class name		: KH_CHCNote_KH815
		 *Description		: Test to verify Continuous Care Note can be successfully scheduled/completed
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
			//@ Assert declaration
			SoftAssert sa = new SoftAssert();
		
			//@Reports Configuration
			Report.generateReportsFile("html","KH_CHCNote");
			Report.SetTestName("KH_CreateCHCNote","KH_CreateCHCNote");
			Report.assignCategory("TBD");
			Report.assignCategory("Not Ready");
			//@Open Application and submit credentials
			KH.Login.openAppAndSubmitCredentials();
			//@ Get Current WebDriver
			WebDriver driver = Browser.getDriver();
			//@Import Test data sheet
			String dataFileName = "KHClinical\\KHVisits\\CHC_Note\\KH_ClinicalVisit_KH815.xlsx";
			String dataSheetName = "CHCNote";
			Datatable.loadDataSheet(dataFileName, dataSheetName );
			String recent_patient = Datatable.GetValue("EM_Recent_Patient");
		/****************************************************************/
			
	        //@Step - Create New Patient, if Required
			if (Datatable.GetValue("CreatePatient").equals("Yes")) {
				//@ Load Data and Add New Patient
			    Datatable.loadDataSheet(dataFileName, "CreatePatient");
			
			    KH.Menu.TopMenu.Select(driver, "File/New/Patient");
				KH.Patient.AddNewPatient.FillAddNewPatient(driver);
				KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
			}

			// @Add New Admission code here
			Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
			KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
			KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();
			Datatable.loadDataSheet(dataFileName, "CHCNote");
			KH.BenefitPeriod.BenefitPeriodManager.ScheduleTask(driver);
			//AM.Episode.EpisodeManager.ScheduleTask(driver);
			driver.findElement(By.id("Details1")).click();

			KH.Menu.TopMenu.Select(driver, "View/"+Datatable.GetValue("EM_Taskname"));
			// ****************************************************************
			
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);
			
			//@ Validate required fields are actually required on Save
			KH.Forms.Nursing.CHCNote.btn_CHC_Save(driver).click();
			Thread.sleep(Waits.getSleepLevelFive());
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Visit Date must be a date");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Visit Date must be a date"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Time In must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Time In must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: Start Time must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: Start Time must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: Start Date must be a date");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: Start Date must be a date"), "ERROR");
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);
			
			//@ Step - Dismiss pop up
			KH.Forms.Nursing.CHCNote.btn_CHC_Validation_Err_Ok(driver).click();
			// *******************************
			
			//@ Validate required fields are actually required on Submit
			KH.Forms.Nursing.CHCNote.btn_CHC_Submit(driver).click();
			Thread.sleep(Waits.getSleepLevelFive());
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Visit Date must be a date");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Visit Date must be a date"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Time In must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Time In must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: Start Time must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: Start Time must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: Start Date must be a date");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: Start Date must be a date"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "The signature date you entered is invalid");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("The signature date you entered is invalid"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "The signature password you entered is invalid.");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("The signature password you entered is invalid"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Time Out must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Time Out must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: End Time must be a time");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: End Time must be a time"), "ERROR");
			KH.Forms.Nursing.CHCNote.ValidateErrorWindowText(driver, "Section 1: End Date must be a date");
			sa.assertTrue(KH.Forms.Nursing.CHCNote.win_CHC_Validation_Error_Window(driver).getText().contains("Section 1: End Date must be a date"), "ERROR");
			
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);
			
			//@ Step - Dismiss pop up
			KH.Forms.Nursing.CHCNote.btn_CHC_Validation_Err_Ok(driver).click();
			// *******************************

			//@ Fill, Complete, and Sign/Submit CCN Form
			Datatable.loadDataSheet(dataFileName, "FillCHCNote1");
			KH.Forms.Nursing.CHCNote.FillCHCForm(driver);
			KH.Forms.Nursing.CHCNote.FillCHCSection(driver, 0);
			KH.Forms.Nursing.CHCNote.acc_CHC_AddSection(driver).click();
			Datatable.loadDataSheet(dataFileName, "FillCHCNote2");
			KH.Forms.Nursing.CHCNote.FillCHCSection(driver, 1);
			KH.Forms.Nursing.CHCNote.FillCHCSignDate(driver);
			KH.Forms.Nursing.CHCNote.chk_CHC_ReturnToClinician(driver).click();
			KH.Forms.Nursing.CHCNote.btn_CHC_Submit(driver).click();
			Verify.Homepage(driver);
			KH.Menu.TopMenu.Select(driver, "Go To/"+recent_patient);
			
			//@ Validate Continuous Care Note is in a completed status
			Datatable.loadDataSheet(dataFileName, "CHCNote");
			KH.BenefitPeriod.BenefitPeriodManager.VerifyTasktExists(driver, Datatable.GetValue("EM_Taskname"));
			sa.assertEquals((KH.BenefitPeriod.BenefitPeriodManager.datatable_Task(driver).getText().trim()),Datatable.GetValue("EM_Taskname"));
			KH.BenefitPeriod.BenefitPeriodManager.VerifyTaskStatus(driver, "Completed");
			sa.assertEquals((KH.BenefitPeriod.BenefitPeriodManager.datatable_Task_Status(driver).getText().trim()),"Completed");
			
			//@ Step - Report screenshot
			Report.attachScreenShotToReport(driver);
			
			//@ Execute all asserts
			sa.assertAll();
			
	}
	
	@AfterClass(alwaysRun=true)
	public static void Teardown() {
	components.Browser.teardownTest();
	}

}