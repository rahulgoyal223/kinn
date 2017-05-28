package AM.Forms.Nursing;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Events;
import components.Report;
import components.Verify;
import components.Waits;

public class MedicationProfile {

	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	private static WebDriver driver;

	public static void FillMedicationProfile(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("Ref_MedicationType");
		if (!strInputValue.trim().isEmpty()) {
			lst_MedicationType(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_StartDate");
		if (!strInputValue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(txt_StartDate(driver)).click()
					.perform();
			txt_StartDate(driver).clear();
			txt_StartDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_Strength");
		if (!strInputValue.trim().isEmpty()) {
			txt_Strength(driver).clear();
			txt_Strength(driver).sendKeys(strInputValue);
			GlobalData.setPatientLastName(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_Amount");
		if (!strInputValue.trim().isEmpty()) {
			txt_Amount(driver).clear();
			txt_Amount(driver).sendKeys(strInputValue);
			GlobalData.setPatientLastName(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_Frequency");
		if (!strInputValue.trim().isEmpty()) {
			txt_Frequency(driver).clear();
			txt_Frequency(driver).sendKeys(strInputValue);
			GlobalData.setPatientLastName(strInputValue);
		}

		strInputValue = Datatable.GetValue("Ref_Classification");
		if (!strInputValue.trim().isEmpty()) {
			lst_Classification(driver).selectByVisibleText(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_Explain");
		if (!strInputValue.trim().isEmpty()) {
			txt_Explain(driver).clear();
			txt_Explain(driver).sendKeys(strInputValue);
			GlobalData.setPatientLastName(strInputValue);
		}

		strInputValue = Datatable.GetValue("MP_Acknowledgement_Explain");
		if (!strInputValue.trim().isEmpty()) {
			txt_Acknowledgement_Explain(driver).clear();
			txt_Acknowledgement_Explain(driver).sendKeys(strInputValue);
			GlobalData.setPatientLastName(strInputValue);
		}

		// @Method for filling Electronic signature

		strInputValue = Datatable.GetValue("ES_ElectronicSignature");
		if (!strInputValue.trim().isEmpty()) {
			txt_ES_ElectronicSignature(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("ES_SignatureDate");
		if (!strInputValue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_ES_SignatureDate(driver))
					.click().perform();
			dt_ES_SignatureDate(driver).clear();
			dt_ES_SignatureDate(driver).sendKeys(strInputValue);
		}

	}

	public static void FillMedication(WebDriver driver) throws Exception {

		Waits.ForElementVisibility(driver, driver.findElement(By.id("medicationType")));
		lst_MedicationType(driver).selectByVisibleText("Medication");

		Waits.fluentWaitIsEnabled(driver, txt_StartDate(driver), 10);
		String startDate = Datatable.GetValue("MP_StartDate");
		if (!startDate.trim().isEmpty()) {
			txt_StartDate(driver).clear();
			txt_StartDate(driver).sendKeys(startDate);
		}
		String medication = Datatable.GetValue("MP_Strength");
		if (!medication.trim().isEmpty()) {
			txt_Strength(driver).sendKeys(medication);
		}
		String amount = Datatable.GetValue("MP_Amount");
		if (!amount.trim().isEmpty()) {
			txt_Amount(driver).sendKeys(amount);
		}
		String frequency = Datatable.GetValue("MP_Frequency");
		if (!frequency.trim().isEmpty()) {
			txt_Frequency(driver).sendKeys(frequency);
		}
	}

	public static void FillNonStandardDosage(WebDriver driver) throws Exception {

		Waits.ForElementVisibility(driver, driver.findElement(By.id("medicationType")));
		lst_MedicationType(driver).selectByVisibleText("Nonstandard Dosage");

		Waits.fluentWaitIsEnabled(driver, txt_StartDate(driver), 10);
		String startDate = Datatable.GetValue("MP_StartDate");
		if (!startDate.trim().isEmpty()) {
			txt_StartDate(driver).clear();
			txt_StartDate(driver).sendKeys(startDate);
		}
		String medication = Datatable.GetValue("MP_Strength");
		if (!medication.trim().isEmpty()) {
			txt_Strength(driver).sendKeys(medication);
		}
		String amount = Datatable.GetValue("MP_Amount");
		if (!amount.trim().isEmpty()) {
			txt_Amount(driver).sendKeys(amount);
		}
		String frequency = Datatable.GetValue("MP_Frequency");
		if (!frequency.trim().isEmpty()) {
			txt_Frequency(driver).sendKeys(frequency);
		}
	}

	public static void FillOffMarketUnlisted(WebDriver driver) throws Exception {

		Waits.ForElementVisibility(driver, driver.findElement(By.id("medicationType")));
		lst_MedicationType(driver).selectByVisibleText("Off Market / Unlisted");
		
		Waits.fluentWaitIsEnabled(driver, txt_StartDate(driver), 1);
		String startDate = Datatable.GetValue("MP_StartDate");
		if (!startDate.trim().isEmpty()) {
			txt_StartDate(driver).clear();
			txt_StartDate(driver).sendKeys(startDate);
		}
		String medication = Datatable.GetValue("MP_Strength");
		if (!medication.trim().isEmpty()) {
			txt_Strength(driver).sendKeys(medication);
		}
		String classification = Datatable.GetValue("Ref_Classification");
		if (!classification.trim().isEmpty()) {
			lst_Classification(driver).selectByVisibleText(classification);
		}
	}

	public static void OpenMedicationPopupToEdit(WebDriver driver) throws Exception {

		String medicationName = Datatable.GetValue("MP_MedicationColumnInTable");
		Waits.ForElementToBeClickable(driver, div_ExistingMedicationToEdit(driver, medicationName));
		if (!medicationName.trim().isEmpty()) {
			div_ExistingMedicationToEdit(driver, medicationName).click();
		}
		Waits.fluentWaitIsDisplayed(driver, driver.findElement(	By.xpath("//*[@id='updateMedicationModal' or @id='updateFreeFormModal' or @id='updateMultiDoseModal']//form")), 1);
	}

	public static void VerifyNewChangeLSFlags(WebDriver driver) {
		WebElement popupForm = driver.findElement(
				By.xpath("//*[@id='updateMedicationModal' or @id='updateFreeFormModal' or @id='updateMultiDoseModal']//form")
				);

		WebElement lsFlag = popupForm.findElement(By.name("LSFlag"));
		WebElement newFlag = popupForm.findElement(By.name("NewFlag"));
		WebElement changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		// Set initial status workaround for double form issue
		// @TODO Remove when Medication page is refactored and the bug fixed
		int countMedicationForms = driver.findElements( By.xpath("//*[@id='LSFlag']/ancestor::form")).size();
		if( countMedicationForms > 1 ){
			if( lsFlag.isSelected() ) lsFlag.click();
			if( ! changeFlag.isSelected() ) changeFlag.click();
			if( newFlag.isSelected() ) newFlag.click();
		}
		// --------------------------------------------------------------------
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		// Verify NewChangeLSFlags initial status
    	Report.Log(Status.INFO, "Verify initial flag statuses on medication edit");
		Verify.VerifyBoolStatus( lsFlag.isSelected(), false, "LongStanding flag initial status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), false, "New flag initial status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), true, "Change flag initial status is ", "checked", "unchecked");
		Report.attachScreenShotToReport(driver, changeFlag);
		
		//unchecking change enables all checkboxes
    	Report.Log(Status.INFO, "When unchecking Change flag");
		changeFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( lsFlag.isSelected(), false, "LongStanding flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), false, "New flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), false, "Change flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( lsFlag.isEnabled(), true, "LongStanding flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( newFlag.isEnabled(), true, "New flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( changeFlag.isEnabled(), true, "Change flag is ", "enable", "disabled");
		Report.attachScreenShotToReport(driver, changeFlag);
		
		//checking LS makes New and change not to toggle
    	Report.Log(Status.INFO, "When checking LongStanding flag");
		lsFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( lsFlag.isSelected(), true, "LongStanding flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), false, "New flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), false, "Change flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( lsFlag.isEnabled(), true, "LongStanding flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( newFlag.isEnabled(), true, "New flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( changeFlag.isEnabled(), true, "Change flag is ", "enable", "disabled");
		Report.attachScreenShotToReport(driver, changeFlag);

    	Report.Log(Status.INFO, "then clicking New flag should not change its status");
		newFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( newFlag.isSelected(), false, "New flag status is ", "checked", "unchecked");

    	Report.Log(Status.INFO, "then clicking Change flag should not change its status");
		changeFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( changeFlag.isSelected(), false, "Change flag status is ", "checked", "unchecked");

    	Report.Log(Status.INFO, "When unchecking LongStanding flag");
		lsFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( lsFlag.isSelected(), false, "LongStanding flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), false, "New flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), false, "Change flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( lsFlag.isEnabled(), true, "LongStanding flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( newFlag.isEnabled(), true, "New flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( changeFlag.isEnabled(), true, "Change flag is ", "enable", "disabled");
		Report.attachScreenShotToReport(driver, changeFlag);

    	Report.Log(Status.INFO, "When checking New flag");
		newFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( lsFlag.isSelected(), false, "LongStanding flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), true, "New flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), false, "Change flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( lsFlag.isEnabled(), false, "LongStanding flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( newFlag.isEnabled(), true, "New flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( changeFlag.isEnabled(), true, "Change flag is ", "enable", "disabled");
		Report.attachScreenShotToReport(driver, changeFlag);
		
    	Report.Log(Status.INFO, "When checking Change flag should only affect its own status");
		changeFlag.click();
		
		lsFlag = popupForm.findElement(By.name("LSFlag"));
		newFlag = popupForm.findElement(By.name("NewFlag"));
		changeFlag = popupForm.findElement(By.name("ChangeFlag"));
		
		Verify.VerifyBoolStatus( lsFlag.isSelected(), false, "LongStanding flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( newFlag.isSelected(), true, "New flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( changeFlag.isSelected(), true, "Change flag status is ", "checked", "unchecked");
		Verify.VerifyBoolStatus( lsFlag.isEnabled(), false, "LongStanding flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( newFlag.isEnabled(), true, "New flag is ", "enabled", "disabled");
		Verify.VerifyBoolStatus( changeFlag.isEnabled(), true, "Change flag is ", "enable", "disabled");
		Report.attachScreenShotToReport(driver, changeFlag);
	}

	public static void SaveAndAddMedication(WebDriver driver) throws Exception {
		btn_Save_MedicationAndAddAditional(driver).click();
		WebElement error = driver.findElement(By.id("errorModal"));
		if(error.isDisplayed()) {
			driver.findElement(By.id("gTemplateErrorOkButton")).click();
			String startDate = Datatable.GetValue("MP_StartDate");
			if (!startDate.trim().isEmpty()) {
				txt_StartDate(driver).sendKeys(startDate);
				btn_Save_MedicationAndAddAditional(driver).click();
			}
		
		}
	}

	
	public static void SaveMedication(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_Save_Medication(driver));
		WebElement stale = btn_Save_Medication(driver);
		btn_Save_Medication(driver).click();
		Waits.ForElementStaleness(driver, stale);
	}
	
	public static void SaveNonStandardMedication(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_Save_Nonstandard(driver));
		WebElement stale = btn_Save_Nonstandard(driver);
		btn_Save_Nonstandard(driver).click();
		Waits.ForElementStaleness(driver, stale);
	}
	
	public static void SaveOffMarketMedication(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_Save_OffMarket(driver));
		WebElement stale = btn_Save_OffMarket(driver);
		btn_Save_OffMarket(driver).click();
		Waits.ForElementStaleness(driver, stale);
	}
	
	public static void SaveAndAddAdditional(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_Save_MedicationAndAddAditional(driver));
		btn_Save_MedicationAndAddAditional(driver).click();
		Waits.fluentWaitIsDisplayed(driver, driver.findElement(By.id("medAdded")), 1);
	}
	
	// @ Objects under section Medication Profile
	public static WebElement txt_PatientInfoHeader(WebDriver driver) {
		element = driver.findElement(By.id("PatientInformation"));
		return element;
	}
	
	public static Select lst_MedicationType(WebDriver driver) {
		WebElement el = driver.findElement(By.id("medicationType"));
		list = new Select(el);
		return list;
	}

	public static WebElement txt_StartDate(WebDriver driver) {
		element = driver.findElement(By.id("newStartDate"));
		return element;
	}

	public static WebElement txt_Strength(WebDriver driver) {
		element = driver.findElement(By.id("Medication"));
		return element;
	}

	public static WebElement txt_Amount(WebDriver driver) {
		element = driver.findElement(By.id("Dose1"));
		return element;
	}

	public static WebElement txt_Frequency(WebDriver driver) {
		element = driver.findElement(By.id("Frequency"));
		return element;
	}

	public static WebElement btn_Save_Medication(WebDriver driver) {
		element = driver.findElement(By.id("addMedispanButton"));
		return element;
	}
	
	public static WebElement btn_Save_MedicationAndAddAditional(WebDriver driver) {
		element = driver.findElement(By.id("addAdditionalButton"));
		return element;
	}

	public static Select lst_Clarification(WebDriver driver) {
		list = new Select(driver.findElement(By.id("classificationId")));
		return list;
	}

	public static WebElement btn_Reconciliation(WebDriver driver) {
		element = driver.findElement(By.id("medCheck"));
		return element;
	}

	public static Select lst_Classification(WebDriver driver) {
		list = new Select(driver.findElement(By.id("classificationId")));
		return list;
	}

	public static WebElement btn_Medication_Reconciliation(WebDriver driver) {
		element = driver.findElement(By.id("medCheck"));
		return element;
	}

	public static WebElement btn_Continue_Reconciliation(WebDriver driver) {
		element = driver.findElement(By.id("ContinueReconciliation"));
		return element;
	}

	// @ Objects under section Medication Profile - Medication Type as
	// "Nonstandard Dosage"

	public static WebElement btn_Save_Nonstandard(WebDriver driver) {
		element = driver.findElement(By.id("saveMultiDoseButton"));
		return element;
	}

	// @ Objects under section Medication Profile - Medication Type as
	// "Off Market / Unlisted"

	public static WebElement btn_Save_OffMarket(WebDriver driver) {
		element = driver.findElement(By.id("addFreeFormButton"));
		return element;
	}

	// @ Objects under section Medication Reconciliation and Acknowledgement

	public static WebElement rb_Yes(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianDiscrepancies1"));
		return element;
	}

	public static WebElement txt_Explain(WebDriver driver) {
		element = driver.findElement(By.id("ExplainDiscrepancies"));
		return element;
	}

	public static WebElement rb_Acknowledgement_Yes(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianSevere1"));
		return element;
	}

	public static WebElement txt_Acknowledgement_Explain(WebDriver driver) {
		element = driver.findElement(By.id("ExplainSevere"));
		return element;
	}

	public static WebElement div_ExistingMedicationToEdit(WebDriver driver, String medicationName) throws Exception {
		List<WebElement> allrows = driver.findElements(By.xpath("//*[contains(@class,'medTitle') and contains(text(), '" + medicationName + "')]/ancestor::*[contains(@class,'medCol')]"));
		element = null;
		if( allrows.size() > 0 ){
			element = allrows.get(0);
			Waits.ForElementVisibility(driver, element);
		} 
		return element;
	}
	

	// @ Step filling Electronic signature
	public static WebElement txt_ES_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("ClinicianSignature"));
		return element;
	}

	public static WebElement dt_ES_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}

	public static WebElement btn_SignDocument(WebDriver driver) {
		element = driver.findElement(By.id("Sign"));
		return element;

	}

}
