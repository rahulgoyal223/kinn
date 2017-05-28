package Scripts.Clinical.Visits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

public class AM_ClinicalVisit_HH3920 {

	private static WebElement element = null;
	private static Select list = null;

	/************************************************************************************
	'Class name                     : 	AM_ClinicalVisit_HH3920
	'JIRA ID						:	HH-3920
	'Description                    : 	Verify that the system displays G0299 as default 
	                                    value of HCPCS dropdown list
	'Input Parameters           	: 	Patient Name
	'Output Parameters        		: 	
	'Assumptions                    : 	Test Data is present in the Global Sheet.
	'Use                            : 	The following test verifies the Detail and 
	                                    Document of a OASIS C1/C2 SOC	                                    
	'Tags                           : 	Smoke Test
	 ************************************************************************************/

	public static void main(String[] args) throws Exception {
		AM_ClinicalVisit_HH3920();
	}

	@Test(groups = {"AM_Regression", "AM_Clinical"})
	public static void AM_ClinicalVisit_HH3920() throws Exception {

		String PM_PatientName = null;
		String dataSheetName = null;
		String selectedHCPCSCode = null;

		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/
		Report.generateReportsFile("html","AM_ClinicalVisit_HH3920");
		Report.SetTestName("AM_ClinicalVisit_HH3920", "AM_Clinical Visit_To Verify that the system displays G0299 as default value of HCPCS dropdown list");
		Report.assignCategory("ClinicalVisit");

		AM.Login.openAppAndSubmitCredentials();
		WebDriver driver = Browser.getDriver();
		String dataFileName = "Clinical\\AM_Patient_HH3920.xlsx";
		dataSheetName = "CreatePatient";

		Datatable.loadDataSheet(dataFileName, dataSheetName);

		/*****************************************************************/

		if (Datatable.GetValue("CreatePatient").equals("Yes")) {
			//@Step :To load sheet and add new patient
			Datatable.loadDataSheet(dataFileName, "CreatePatient");

			//@Step :To select the required menu
			Thread.sleep(Waits.getSleepLevelFive());
			AM.Menu.TopMenu.Select(driver, "File/New/Patient");
			AM.Patient.AddNewPatient.FillAddNewPatient(driver);
			AM.Patient.AddNewPatient.AddPatient(driver);
		}    

		if (!GlobalData.getPatientFullName().isEmpty()) {
			PM_PatientName = GlobalData.getPatientFullName();
		} else {
			PM_PatientName = Datatable.GetValue("PM_PatientName");
		}  

		System.out.println("Patient Name is : "+ PM_PatientName);

		//@Step: Verify HCPCS in Detail View
		AM.Episode.EpisodeManager.SelectTaskDetailsByRow(driver, "1");
		Waits.ForBrowserLoad(driver);
		AM.Episode.TaskDetails.verifySelectedHCPCSCode(driver, "G0299");

		//@Step: Verify HCPCS in Form
		AM.Menu.TopMenu.Select(driver, "View/OASIS-C1 Start of Care");
		AM.Forms.Nursing.OasisSOC.SelectForm(driver, "Demographics");
		Waits.ForBrowserLoad(driver);
		AM.Forms.Nursing.OasisSOC.verifySelectedHCPCSCode(driver, "G0299");


	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}

}
