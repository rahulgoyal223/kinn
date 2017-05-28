package KHScripts.Patient;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Waits;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

/****************************************************************
 *Class name		: KH141_KH_SMK_PendingAdmissions
 *Description		: Test to verify whether newly created patient is listed under Pending Admission 
 *Input Parameters	: Patient details
 *Output Parameters	: None
 *Assumptions		: Test Data is present in the Global Sheet.
 *Use				: N/A
 *Tags				: Regression
 ******************************************************************/
public class KH141_KH_SMK_PendingAdmissions {
	private static WebElement element = null;

	public static void main(String[] args) throws Exception {
		KH141_KH_SMK_PendingAdmissions();
	}


	@Test(groups = { "KH_Regression", "KH_Clinical" ,"KH_Smoke"})
	public static void KH141_KH_SMK_PendingAdmissions() throws Exception {

		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
		Report.generateReportsFile("html","KH141_KH_SMK_PendingAdmissions");
		Report.SetTestName("KH141_KH_SMK_PendingAdmissions","Verify whether newly created patient is listed under Pending Admission");
		Report.assignCategory("TBD");
		Report.assignCategory("Not Ready");

		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();

		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();

		//@Import Test data sheet
		String dataFileName = "KHPatient\\KH141_KH_SMK_PendingAdmissions.xlsx";
		String dataSheetName = "CreatePatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/
		try {
			//@Step - Navigate to Add Patient
			KH.Menu.TopMenu.Select(driver, "File/New/Patient");
			//@Step - To fill patient
			KH.Patient.AddNewPatient.FillAddNewPatient(driver);
			//@Step -To click on Save and Create New Patient
			KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();
			// Page navigates to Create New Admission			
			//@Step - To wait until the Save & Submit button is enabled
			element = driver.findElement(By.id("submitBtn"));
			Waits.fluentWaitIsDisplayed(driver, element, 1);
			Report.attachScreenShotToReport(driver);
			//@Step - Navigate to Reports/Admin page
			KH.Menu.TopMenu.Select(driver, "Go To/Reports/Admin");
			Report.attachScreenShotToReport(driver);
			//@Step - Click on Pending Admissions link
			KH.ReportsAdmin.Administration.link_Pending_Admissions(driver).click();;
			Report.attachScreenShotToReport(driver);
			//@Step - Get Patient full name 
			String patientFullName=GlobalData.getPatientFullName();
			//@Step - To verify page title
			KH.PendingAdmissions.PendingAdmissions.verifyPageTitle(driver);
			//@Step - To search with patient full name in Pending Admissions and verify whether the patient is listed 
			KH.PendingAdmissions.PendingAdmissions.searchAndVerifyPatientName(driver, patientFullName);
			Report.attachScreenShotToReport(driver);

		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@AfterClass(alwaysRun = true)
	public static void Teardown() {
		components.Browser.teardownTest();
	}
}