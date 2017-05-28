package KHScripts.Patient;

import DataSource.Datatable;
import components.Browser;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

/****************************************************************
 *Class name		: KH1062_TrackingFacilityRoomAptNo
 *Description		: Test to verify that AptRoom appears as input field
 *						and that data is being saved, accessed, and displayed.
 *Input Parameters	: Patient and Admission detail
 *Output Parameters	: None
 *Assumptions		: Test Data is present in the Global Sheet.
 *Use				: N/A
 *Tags				: Regression
 ******************************************************************/
public class KH1062_TrackingFacilityRoomAptNo {



	public static void main(String[] args) throws Exception {
		Test();
	}


	@Test
	public static void Test() throws Exception {

		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
		Report.generateReportsFile("html","KH1062_TrackingFacilityRoomAptNo");
		Report.SetTestName("KH1062_TrackingFacilityRoomAptNo","Verify AptRoom being saved, accessed, displayed");
		Report.assignCategory("TBD");
		Report.assignCategory("Not Ready");
		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "KHPatient\\KH1062_TrackingFacilityRoomAptNo.xlsx";
		String dataSheetName = "CreatePatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/

		String filePath="D:/ATF/ATF/src/test/resources/attachments/TestAttachment.txt";
		String aptRoom =  null;
		String aptRoomEdit = null;
		WebElement element = null;

		try {
			//@Step - Add Patient
			KH.Menu.TopMenu.Select(driver, "File/New/Patient");
			Waits.ForBrowserLoad(driver);
			element = driver.findElement(By.xpath("//label[@for='aptRoom']"));

			if (!element.getText().equals("Apt/Room"))
				throw new Exception(element.getText() + " NOT Apt/Room on add patient screen.");

			KH.Patient.AddNewPatient.FillAddNewPatient(driver);

			aptRoom = Datatable.GetValue("PD_AptRoom");

			element = driver.findElement(By.id("aptRoom"));

			element.clear();
			element.sendKeys(aptRoom);

			KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();

			// get new value of Apt/Room from spreadsheet for future edit
			aptRoomEdit = Datatable.GetValue("PD_AptRoomEdit");

			//@Step - Add Admission
			Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
			KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
			KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();	     


			//Start of code snippet added as part of KH-429:KH_SMK_Attachments

			Waits.ForBrowserLoad(driver);
			//@Step - Navigate to Patient Insurance - Edit page
			KH.Menu.TopMenu.Select(driver, "Edit/Insurance");
			Waits.ForBrowserLoad(driver);
			//@Step - Upload attachment
			driver.findElement(By.id("attachment")).sendKeys(filePath);
			driver.findElement(By.id("uploadAttachmentattachment")).click();

			//@Step - Verify the uploaded attachment
			WebElement ele=driver.findElement(By.linkText("TestAttachment.txt"));
			Waits.ForElementVisibility(driver, element);
			if(ele.isDisplayed())
				Report.Log(Status.INFO, "Attachment has been added successfully");
			else
				Report.Log(Status.ERROR, "Unable to upload attachment in Edit-->Insurance");

			Report.attachScreenShotToReport(driver);
			//@Step - Navigate to Admission Summary page
			KH.Menu.TopMenu.Select(driver, "View/Admission Summary");
			Waits.ForBrowserLoad(driver);
			String admissionDate=Datatable.GetValue("AB_StartOfCareDate");
			//@Step - Click on Admission Date
			KH.Admission.AdmissionSummary.SelectAdmission(driver,admissionDate );
			//@Step - Add and verify whether attachment is added successfully
			KH.Admission.EditAdmission.addAndVerifyAttachment(driver,filePath);

			//End of code snippet for KH-429:KH_SMK_Attachments

			//@Step - Edit Patient
			KH.Menu.TopMenu.Select(driver, "Edit/Patient");
			Waits.ForBrowserLoad(driver);
			element = driver.findElement(By.xpath("//label[@for='aptRoom']"));

			if (!element.getText().equals("Apt/Room"))
				throw new Exception(element.getText() + " NOT Apt/Room on edit patient screen.");

			element = driver.findElement(By.id("aptRoom"));

			if (!element.getAttribute("value").equals(aptRoom))
				throw new Exception(element.getAttribute("value") + " NOT " + aptRoom + " on edit patient screen.");

			element.clear();
			element.sendKeys(aptRoomEdit);

			// button has same id on edit patient screen
			java.lang.Thread.sleep(500);
			KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();

			// check information on Patient Profile screen
			KH.Menu.TopMenu.Select(driver, "View/Patient Profile");
			Waits.ForBrowserLoad(driver);

			element = driver.findElement(By.xpath("/html/body/div/div[5]/div[2]/div/div[2]/div[1]/table[1]/tbody/tr[8]/td[1]"));

			if (!element.getText().equals("Apt/Room"))
				throw new Exception(element.getText() + " NOT Apt/Room on patient profile screen.");

			element = driver.findElement(By.xpath("/html/body/div/div[5]/div[2]/div/div[2]/div[1]/table[1]/tbody/tr[8]/td[2]"));

			if (!element.getText().equals(aptRoomEdit))
				throw new Exception(element.getText() + " NOT " + aptRoomEdit + " on patient profile screen.");

			// click Print button to change to 'print view'
			driver.findElement(By.tagName("button")).click();

			element = driver.findElement(By.xpath("//*[@id='PatientInformation']/table/tbody/tr[8]/td[1]"));

			if (!element.getText().equals("Apt/Room"))
				throw new Exception(element.getText() + " NOT Apt/Room on patient profile print screen.");

			element = driver.findElement(By.xpath("//*[@id='PatientInformation']/table/tbody/tr[8]/td[2]/span"));

			if (!element.getText().equals(aptRoomEdit))
				throw new Exception(element.getText() + " NOT " + aptRoomEdit + " on patient profile print screen.");

			// return to Patient Profile screen
			driver.navigate().back();

			//@Step - Generate Patient Roster
			KH.Menu.TopMenu.Select(driver, "Go To/Admin");
			Waits.ForBrowserLoad(driver);

			driver.findElement(By.linkText("Patient Roster")).click();

			// run check on default selection (all non-discharged patients)
			driver.findElement(By.id("create")).click();

			if (!driver.getPageSource().contains("Apt/Room: " + aptRoomEdit))
				throw new Exception("Apt/Room: " + aptRoomEdit + " NOT in Patient Roster report.");

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