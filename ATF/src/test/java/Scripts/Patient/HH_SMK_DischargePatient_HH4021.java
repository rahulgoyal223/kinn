
package Scripts.Patient;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;
public class HH_SMK_DischargePatient_HH4021 {

	/************************************************************************************
	'Class name                     : 	HH_SMK_DischargePatient_HH4021
	'JIRA ID						:	HH-4021
	'Description                    : 	To bill the visits of the patient and discharge the patient.
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies whether a patient can be billed and discharged
	'Tags                           : 	Regression
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		HH_SMK_DischargePatient_HH4021();

	}
	@Test(groups = { "AM_Regression", "AM_Clinical", "SmokeTest" })
	public static void HH_SMK_DischargePatient_HH4021() throws Exception {
		String dataSheetName = null;
		String endDate=null;
		//@Reports Configuration
		Report.generateReportsFile("html","AM_DischargePatient_HH4021");
		Report.SetTestName("AM_DischargePatient_HH4021","To discharge a patient");
		Report.assignCategory("Discharge Patient");

		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet

		String dataFileName = "Clinical\\AM_DischargePatient_HH4021.xlsx";
		//@Load CreatePatient Sheet
		dataSheetName = "CreatePatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName);
		//**********************************************************************	          

		//@@Step  :To create new patient
		if (Datatable.GetValue("CreatePatient").equals("Yes")) 
		{
			//@Step :To Load The Sheet
			Datatable.loadDataSheet(dataFileName, "CreatePatient"); 
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			Report.attachScreenShotToReport(driver);
			AM.Patient.AddNewPatient.chk_AF_AngryDogAtHouse(driver).click();
			Report.attachScreenShotToReport(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
			Report.attachScreenShotToReport(driver);
		}

		else{
			System.out.print("\n CreatePatient Field is not set to Yes.");
			Report.Log(com.aventstack.extentreports.Status.FAIL, "CreatePatient Field is not set to Yes");
			Assert.fail("CreatePatient field should be set to Yes to proceed");
		}

		//@Load AddDischarge Sheet
		Datatable.loadDataSheet(dataFileName,"AddDischarge");
		if(Datatable.GetValue("AddDischarge").equals("Yes")){
			AM.Episode.EpisodeManager.ScheduleTask(driver);
			//@Step to Add Discharge - OASIS-C2 Discharge
			AM.Episode.EpisodeManager.SelectScheduleTasks(driver, "OASIS-C2 Discharge");
			//@Steps to fill form-Agency Discharge Only
			AM.Forms.Nursing.OasisDischarge.SelectForm(driver, "Data Items Collected at Inpatient Facility Admission or Agency Discharge Only");
			AM.Forms.Nursing.OasisDischarge.FillAgencyDischargeOnly(driver);
			AM.Forms.Nursing.OasisDischarge.saveAlertWindow(driver); 
			//@Step to navigate back to Patient Manager and select Patient
			AM.Menu.TopMenu.Select(driver, "Go To/Patient Manager");
			Waits.ForBrowserLoad(driver);
			AM.Patient.PatientManager.SelectActivePatient(driver); 
			//AM.Patient.PatientManager.ToSelectAlphabetOfPatient(driver, "Z").click();
			Report.attachScreenShotToReport(driver);
			AM.Patient.PatientManager.SelectPatientNameLink(driver, GlobalData.getPatientFirstName(), GlobalData.getPatientLastName());
			Report.attachScreenShotToReport(driver);
			//@Step to navigate to Edit->Episode
			AM.Menu.TopMenu.Select(driver, "Edit/Episode");
			//@Step to verify end date
			//AM.Episode.EditEpisode.verifyEndDate(driver);
			endDate=Datatable.GetValue("M0906_DateDischarge");
			if(AM.Episode.EditEpisode.dt_EE_EpisodeEndDate(driver).getAttribute("value").equalsIgnoreCase(endDate)){
				Report.Log(Status.PASS, "" + endDate + " is updated");
				Report.attachScreenShotToReport(driver);
				Assert.assertTrue(true);
			} else {
				Report.Log(Status.FAIL, "" + endDate + " is NOT updated");
				Assert.fail("" + endDate + " is NOT updated");
			}


		}
		else{
			System.out.print("\n AddDischarge Field is not set to Yes.");
			Report.Log(com.aventstack.extentreports.Status.FAIL, "AddDischarge Field is not set to Yes");
			Assert.fail("AddDischarge field should be set to Yes to proceed");
		}
	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}
}
