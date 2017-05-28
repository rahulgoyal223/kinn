package AM.Forms.Nursing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DataSource.Datatable;
import components.Events;
import components.Verify;
import components.Waits;

/**
 * @author jonathan.martindill
 *
 */
public class NursingVisit {

	private static WebElement element;
	private static String StrInputvalue;

	// @Method for filling Nursing visit record
	public static void FillNursingVisitRecord(WebDriver driver)
			throws Exception {

		StrInputvalue = Datatable.GetValue("NV_TimeIn");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_NV_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("NV_TimeOut");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_NV_TimeOut(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("NV_VisitDate");
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_NV_VisitDate(driver)).click()
					.perform();
			dt_NV_VisitDate(driver).clear();
			dt_NV_VisitDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("NV_AssociatedMileage");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_NV_AssociatedMileage(driver).sendKeys(StrInputvalue);
		}

	}

	// @Method for filling Electronic signature
	public static void FillElectronicSignature(WebDriver driver)
			throws Exception {

		StrInputvalue = Datatable.GetValue("ES_ElectronicSignature");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_ES_ElectronicSignature(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("ES_SignatureDate");
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_ES_SignatureDate(driver))
					.click().perform();
			dt_ES_SignatureDate(driver).clear();
			dt_ES_SignatureDate(driver).sendKeys(StrInputvalue);
		}

	}
	
	/**Method to click the submit button and wait for the Hotbox to appear
	 * @param driver
	 * @throws Exception
	 */
	public static void SubmitNurseNote(WebDriver driver) throws Exception{
		Waits.ForElementToBeClickable(driver, btn_ES_Submit(driver));
		btn_ES_Submit(driver).click();
		Verify.ExactPageHeader(driver, "Hotbox");
	}
	
	/**Methos to save the nurse note and wait for the page to reload
	 * @param driver
	 * @throws Exception
	 */
	public static void SaveNurseNote(WebDriver driver) throws Exception{
		Waits.ForElementToBeClickable(driver, btn_ES_Save(driver));
		WebElement stale = Waits.StalenessPreset(driver);
		btn_ES_Save(driver).click();
		Waits.ForElementStaleness(driver, stale);
		Waits.ForElementToBeClickable(driver, btn_ES_Save(driver));
	}

	/**Clicks into the wound care worksheet and verifies the header - CURRENTLY UNUSED/UNTESTED
	 * @param driver
	 * @throws Exception
	 */
	public static void GoToWoundCareWorksheet(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, lnk_WoundCareWorksheet(driver));
		lnk_WoundCareWorksheet(driver).click();
		Waits.ForElementToBeClickable(driver, AM.Forms.Nursing.WoundCareWorksheet.btn_AddWound(driver));
	}
	
	// @objects for Task Details
	public static WebElement txt_NV_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("eTimein"));
		return element;
	}

	public static WebElement txt_NV_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("eTimeout"));
		return element;
	}

	public static WebElement dt_NV_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("VisitDate"));
		return element;
	}

	public static WebElement txt_NV_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		return element;
	}
	
	public static WebElement lnk_WoundCareWorksheet(WebDriver driver) {
		element = driver.findElement(By.linkText("Wound Care Worksheet")); 
		return element;
	}
	

	public static WebElement txt_ES_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement dt_ES_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}

	public static WebElement btn_ES_Save(WebDriver driver) {
		element = driver.findElement(By.id("SaveButton"));
		return element;
	}

	public static WebElement btn_CV_SaveGcodeVerification(WebDriver driver) {
		element = driver.findElement(By.id("gcodeModalOKButton"));
		return element;
	}

	public static WebElement btn_ES_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		return element;
	}

}
