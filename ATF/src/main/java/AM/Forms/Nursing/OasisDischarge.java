package AM.Forms.Nursing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Report;
import components.Verify;
import components.Waits;

import java.util.Iterator;

import org.testng.Assert;

public class OasisDischarge {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list;

	// @Method To select the Form
	public static WebElement SelectForm(WebDriver driver, String formname)
			throws Exception {

		try {
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().contains(formname)) {
					row.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	
	
	// @Method To fill the Demographics form
	public static void FillDemographicForm(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("OA_cTO_timein");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeIn(driver).clear();
			txt_DG_TimeIn(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_timeout");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeOut(driver).clear();
			txt_DG_TimeOut(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_visitdate");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_VisitDate(driver).clear();
			txt_DG_VisitDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_associatedMileage");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_AssociatedMileage(driver).clear();
			txt_DG_AssociatedMileage(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_dt_DG_M0090INFOCOMPLETEDDT");
		if (!strInputValue.trim().isEmpty()) {
			dt_DG_M0090INFOCOMPLETEDDT(driver).clear();
			dt_DG_M0090INFOCOMPLETEDDT(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_M0032_ROC_DT_NA");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.trim().equals("Yes")) {
			chk_DG_ResumptionOfCareDate(driver).click();

		}

		strInputValue = Datatable.GetValue("OA_M0150_CPS_HC");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.trim().equals("Yes")) {
			chk_DG_M0150_CurrentPaymentSourcesforHomeCare(driver).click();
		}

	}

	// @Step OASIS OT Discharge Evaluation
	public static void FillOTDischargeEvaluation(WebDriver driver)
			throws Exception {

		strInputValue = Datatable.GetValue("OA_MedicalDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_MedicalDiagnosis(driver).clear();
			OA_MedicalDiagnosis(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_OT Diagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_OTDiagnosis(driver).clear();
			OA_OTDiagnosis(driver).sendKeys(strInputValue);
		}
	}

	// @Step OASIS OT Discharge Risk Assessment
	public static void FillOTDischargeRiskAssessment(WebDriver driver)
			throws Exception {

		strInputValue = Datatable.GetValue("OA_M1041_InfluenzaVaccine");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
			OA_M1041_InfluenzaVaccine(driver).click();

		}

		strInputValue = Datatable.GetValue("OA_M1051_PneumococcalVaccine");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
			OA_M1051_PneumococcalVaccine(driver).click();
		}
	}

	// @Step OASIS OT Discharge Sensory Status
	public static void FillOTDischargeSensoryStatus(WebDriver driver)
			throws Exception {

		strInputValue = Datatable.GetValue("OA_M1230_Speech");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
			OA_M1230_Speech(driver).click();

		}
	}

	// @Step OASIS OT Discharge Pain
	public static void FillOTDischargePain(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("OA_M1242_FrequencyofPain");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
			OA_M1242_FrequencyofPain(driver).click();

		}
	}

	// @ Method PT Discharge Evaluation
	public static void FillPTDischargeEvaluation(WebDriver driver)
			throws Exception {

		strInputValue = Datatable.GetValue("OA_MedicalDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_PTMedicalDiagnosis(driver).clear();
			OA_PTMedicalDiagnosis(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_PTDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_PTDiagnosis(driver).clear();
			OA_PTDiagnosis(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_FA_HomeExerciseProgram");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.equals("yes".toLowerCase())) {
			chk_FAC_HomeExerciseProgram(driver).click();
		}
		Report.Log(Status.INFO,
				"FillPTDischargeEvaluation form has been completed successfully");
	}

	// @Method to check Oasis
	public static void SubmitOasisWithSignature(WebDriver driver) throws Exception {
		
		strInputValue = Datatable.GetValue("OA_signatureRequired");
		if (!strInputValue.trim().isEmpty() || strInputValue != "No Column Found") {
			chk_OC_ReturnToClinicianSignature(driver).click();
		}
		else{ strInputValue = Datatable.GetValue("OA_ReturnToClinician");
		if(!strInputValue.trim().isEmpty() || strInputValue != "No Column Found"){
			chk_OC_ReturnToClinicianSignature(driver).click();
		}
		}
		strInputValue = Datatable.GetValue("OA_clinicianSignature");
		if (!strInputValue.trim().isEmpty()) {
			txt_OC_ElectronicSignature(driver).sendKeys(strInputValue);

		}
		
		Waits.fluentWaitIsEnabled(driver, AM.Forms.Nursing.OasisSOC.btn_Submit(driver), 5);
	    AM.Forms.Nursing.OasisSOC.btn_Submit(driver).click();
	    Waits.ForHomePage(driver, "Hotbox");
	    Verify.ExactPageHeader(driver, "Hotbox");

	}
	
	public static void SaveOasisPage(WebDriver driver) {
		try {
			btn_DG_Save(driver).click();
			saveAlertWindow(driver);
		} catch (Exception e) {
			Report.Log(Status.FAIL, "Page was NOT saved");
			Assert.fail("Page was NOT saved");
			e.printStackTrace();
		}
	}

	public static void saveAlertWindow(WebDriver driver)
			throws InterruptedException {
		WebElement element = driver.findElement(By.id("saveWindowText"));
		//Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForBrowserLoad(driver);
		Waits.fluentWaitIsNotDisplayed(driver, element, 30);
//		for (int i = 0; i <= 40; i++) {
//			if (element.isDisplayed()
//					&& element.getText().trim().equals("Page has been saved!")) {
//				driver.navigate().refresh();
//				break;
//			}
//		}
//		driver.navigate().refresh();
	}
	
	public static void waitForOASISPage(WebDriver driver, String oasisPage)
		throws InterruptedException {
		//just implicit wait here, since it should be a different page
		driver.findElement(By.xpath("//h3[.='" + oasisPage + "']"));
		return;
	}

	// @ Objects for Oasis check
	public static WebElement chk_OC_ReturnToClinicianSignature(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
	}

	public static WebElement txt_OC_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement dt_OC_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}

	// @OASIS OT Evaluation
	public static WebElement OA_MedicalDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("frm_MedDiagText"));
		return element;
	}

	public static WebElement OA_OTDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("frm_PTDiagText"));
		return element;
	}

	// @OASIS PT Evaluation
	public static WebElement OA_PTMedicalDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("frm_MedDiagText"));
		return element;
	}

	public static WebElement OA_PTDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("frm_PTDiagText"));
		return element;
	}

	public static WebElement chk_FAC_HomeExerciseProgram(WebDriver driver) {
		element = driver.findElement(By.id("frm_tHEP"));
		return element;
	}
	
	//Method to fill form -Data Items Collected at Inpatient Facility Admission or Agency Discharge Only
	
	public static void FillAgencyDischargeOnly(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("M093_DateOfLastHomeVisit");
		if (!strInputValue.trim().isEmpty()) {
			txt_DateOfLastHomeVisit(driver).clear();
			txt_DateOfLastHomeVisit(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("M0906_DateDischarge");
		if (!strInputValue.trim().isEmpty()) {
			txt_DischargeDate(driver).clear();
			txt_DischargeDate(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("PatientDischarge");
		if (!strInputValue.trim().isEmpty()) {
			patientDischargeStatus(driver).selectByVisibleText(strInputValue);
		}
		btn_DG_Save(driver).click();
		
		
	}
	

	// @OASIS OT Risk Assessment
	public static WebElement OA_M1041_InfluenzaVaccine(WebDriver driver) {
		element = driver.findElement(By.id("M1041_1"));
		return element;
	}

	public static WebElement OA_M1051_PneumococcalVaccine(WebDriver driver) {
		element = driver.findElement(By.id("M1051_01"));
		return element;
	}

	// @OASIS OT Sensory Status
	public static WebElement OA_M1230_Speech(WebDriver driver) {
		element = driver.findElement(By.id("M1230_00"));
		return element;
	}

	// @OASIS OT Pain
	public static WebElement OA_M1242_FrequencyofPain(WebDriver driver) {
		element = driver.findElement(By.id("M1242_00"));
		return element;
	}

	// @ OasisSOC test objects
	public static WebElement txt_DG_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("cTO_timein"));
		return element;
	}

	public static WebElement txt_DG_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("cTO_timeout"));
		return element;
	}

	public static WebElement txt_DG_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("cTO_visitdate"));
		return element;
	}

	public static WebElement txt_DG_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		return element;
	}

	public static WebElement dt_DG_ResumptionOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("M0032_ROC_DT"));
		return element;
	}

	public static WebElement dt_DG_M0090INFOCOMPLETEDDT(WebDriver driver) {
		element = driver.findElement(By.id("M0090_INFO_COMPLETED_DT"));
		return element;
	}

	public static WebElement chk_DG_ResumptionOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("M0032_ROC_DT_NA"));
		return element;
	}

	public static WebElement chk_DG_M0150_CurrentPaymentSourcesforHomeCare(
			WebDriver driver) {
		element = driver.findElement(By.id("M0150_CPAY_MCARE_FFS"));
		return element;
	}

	public static WebElement btn_DG_Save(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("oasisSaveButton"));
		return element;
	}

	// For Oasis Form approval
	public static WebElement txt_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By.name("whattodo"));
		return element;
	}

	public static WebElement btn_Approve(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Approve']"));
		return element;

	}
	
	//Data Items Collected at Inpatient Facility Admission or Agency 
	
	public static WebElement txt_DateOfLastHomeVisit(WebDriver driver) {
		element = driver.findElement(By.id("M0903_LAST_HOME_VISIT"));
		return element;
	}

	public static WebElement txt_DischargeDate(WebDriver driver) {
		element = driver.findElement(By.id("M0906_DC_TRAN_DTH_DT"));
		return element;
	}
	
	public static Select patientDischargeStatus(WebDriver driver) {
		list = new Select(driver.findElement(By.id("Source")));
		Report.Log(Status.INFO, "Acted on Patient Discharge Status");
		return list;
	}
	
	public static WebElement btn_clickSaveAndContinue(WebDriver driver) {
		element = driver.findElement(By.id("oasisSaveContinueButton"));
		return element;
	}
	public static void VerifyM2005Options(WebDriver driver) throws Exception {
		Waits.ForBrowserLoad(driver);
		String expectedOptions[] = {
				"0   No",
				"1   Yes",
				"9   NA - There were no potential clinically significant medication issues identified since SOC/ROC or patient is not taking any medications",
				"-   Not assessed/no information"
		}; 
		
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id='M2005']//label"));
		
		if( expectedOptions.length != rows.size() ){
			Report.Log(
					Status.FAIL, 
					"M2005 does not contain " + String.valueOf(expectedOptions.length) + " options. " + 
					"Only " + String.valueOf(rows.size()) + " options were found"
					);
			Assert.fail("M2005 missing options.");
		} else {
			for( Iterator<WebElement> i = rows.iterator(); i.hasNext();){
				WebElement row = i.next();
				int position = rows.indexOf(row);
				boolean pass = row.getText().trim().equals( expectedOptions[position].trim() );
						
				Report.Log(
						pass ? Status.PASS : Status.ERROR, 
						"M2005 option " + String.valueOf(position+1) 
							+ " is <pre>" + row.getText() + "</pre> " 
							+ ( 
								!pass 
									? " expected was <pre>" + expectedOptions[position] + "</pre>." 
									: ""
							)
						);
			}
		}
		Report.attachScreenShotToReport(driver);
	}

}
