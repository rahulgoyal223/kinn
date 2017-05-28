package KHScripts.Claims.RoomAndBoard;

import java.util.ArrayList;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Browser;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/****************************************************************
 *Class name		: KH1011_ICD10_Appear_On_Claim
 *Description		: Test to verify ICD-10 code appears on room and board claim.
 *Input Parameters	: Patient and Admission detail
 *Output Parameters	: None
 *Assumptions		: Test Data is present in the Global Sheet.
 *Use				: N/A
 *Tags				: Regression
******************************************************************/
public class KH1011_ICD10_Appear_On_Claim {

	public static void main(String[] args) throws Exception {
		Test();
	}

	
	@Test
	public static void Test() throws Exception {
	
		/******************************************************************
		 * Mandate to call below lines at every test case start up
		 ******************************************************************/	
		//@Reports Configuration
		Report.generateReportsFile("html","KH1011_ICD10_Appear_On_Claim");
		Report.SetTestName("KH1011_ICD10_Appear_On_Claim","Verify ICD-10 code appears on UB04 form.");
		Report.assignCategory("TBD");
		Report.assignCategory("Not Ready");
		//@Open Application and submit credentials
		KH.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "KHClaims\\RoomAndBoard\\KH1011_ICD10_Appear_On_Claim.xlsx";
		String dataSheetName = "CreatePatient";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
		/****************************************************************/

        String PM_PatientName = null;
        String DI_TerminalDiagnosis = null;
        
        //@Step - Create New Patient
		Datatable.loadDataSheet(dataFileName, "CreatePatient");

		KH.Menu.TopMenu.Select(driver, "File/New/Patient");
		KH.Patient.AddNewPatient.FillAddNewPatient(driver);
		KH.Patient.AddNewPatient.btn_P_SaveAndCreate(driver).click();

		PM_PatientName = GlobalData.getPatientFullName();
		
		//@Step - Add Admission
        Datatable.loadDataSheet(dataFileName, "FillNewAdmission");
        KH.Admission.AddNewAdmission.FillAddNewAdmission(driver);
        KH.Admission.AddNewAdmission.btn_BP_SaveAndSubmit(driver).click();

		DI_TerminalDiagnosis = Datatable.GetValue("DI_TerminalDiagnosis");

		//@Step - Admission Summary select admission for edit
		KH.Menu.TopMenu.Select(driver, "View/Admission Summary");
		
		String admissionDate = Datatable.GetValue("AB_StartOfCareDate");
		KH.Admission.AdmissionSummary.SelectAdmission(driver, admissionDate);
        
		//@Step - Edit Admission to add room and board detail
		KH.Admission.EditAdmission.FillInsuranceInformationRoomAndBoard(driver);
		KH.Admission.EditAdmission.btn_BP_SaveAndSubmit(driver).click();
		
		//@Step - Claim Manager Create Claim		
		KH.Menu.TopMenu.Select(driver, "Go To/Billing Manager");
		KH.Billing.BillingManager.SelectMenu(driver, "Room and Board/Ready");
        KH.Billing.Claims.RoomAndBoard.ClaimsManager.lst_R_ClaimAge(driver).selectByVisibleText("All Claims");
        KH.Billing.Claims.RoomAndBoard.ClaimsManager.SelectPatient(driver, PM_PatientName);
        KH.Billing.Claims.RoomAndBoard.ClaimsManager.btn_R_CreateClaim(driver).click();
        KH.Billing.Claims.RoomAndBoard.ClaimsManager.VerifyReadyAlertMessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");

 	   	//@Step - Print UB04 from Pending Approval
 	   	KH.Billing.Claims.RoomAndBoard.ClaimsManager.SelectStatusTab(driver, "Pending Approval");
 	   	KH.Billing.Claims.RoomAndBoard.ClaimsManager.SelectPatient(driver, PM_PatientName);
 	   	KH.Billing.Claims.RoomAndBoard.ClaimsManager.txt_PA_SearchBox(driver).sendKeys(PM_PatientName);
 	   	KH.Billing.Claims.RoomAndBoard.ClaimsManager.btn_PA_PrintView(driver).click();
 	   	
 	   	Waits.ForGlobalAjaxLoader(driver);
 	   	
 	   	//@Step - Test terminal diagnosis code on UB04 form.
 	   	String pdfUrl = "";
 	   	String originalTab = driver.getWindowHandle();
 	   	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

 	   	for(int i = 0; i < tabs.size(); i++) {
 	   		driver.switchTo().window(tabs.get(i));		 		   
 	   		pdfUrl = driver.getCurrentUrl();
 	   		if(pdfUrl.endsWith("pdf")) {
 	   			Report.attachScreenShotOfPDFToReport(driver);

 	   			Verify.PDFText(driver, pdfUrl, PM_PatientName);
 	   			Verify.PDFText(driver, pdfUrl, DI_TerminalDiagnosis);
 	   			
 	   			((JavascriptExecutor)driver).executeScript("window.close()");
 	   			driver.switchTo().window(originalTab);
 	   		}		 		   
 	   	}
	}

	@AfterClass(alwaysRun = true)
    public static void Teardown() {
        components.Browser.teardownTest();
    }

}
