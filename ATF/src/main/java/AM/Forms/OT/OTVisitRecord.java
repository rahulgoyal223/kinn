package AM.Forms.OT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Verify;
import components.Report;
import components.Waits;

public class OTVisitRecord {

	private static WebElement element;
	private static String StrInputvalue;

	// @Method for filling OT visit record
	public static void FillOTVisitRecord(WebDriver driver) throws Exception {

		Waits.ForElementToBeClickable(driver, btn_ES_Submit(driver));

		StrInputvalue = Datatable.GetValue("OT_TimeIn");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_OT_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("OT_TimeOut");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_OT_TimeOut(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("OT_VisitDate");
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_OT_VisitDate(driver)).click()
					.perform();
			dt_OT_VisitDate(driver).clear();
			dt_OT_VisitDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("OT_AssociatedMileage");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_OT_AssociatedMileage(driver).sendKeys(StrInputvalue);
		}

	}
	
	public static void SubmitOTVisit(WebDriver driver) {
		try {
			Waits.ForElementToBeClickable(driver, btn_ES_Submit(driver));
			btn_ES_Submit(driver).click();
			Verify.ExactPageHeader(driver, "Hotbox");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "OT Visit was not submitted successfully");
			e.printStackTrace();
		}
	}

	// @objects for Task Details
	public static WebElement txt_OT_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("frm_timein"));
		return element;
	}

	public static WebElement txt_OT_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("frm_timeout"));
		return element;
	}

	public static WebElement dt_OT_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("frm_visitdate"));
		return element;
	}

	public static WebElement txt_OT_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		return element;
	}
	
	public static WebElement lnk_WoundCareWorksheet(WebDriver driver) {
		element = driver.findElement(By.linkText("Wound Care Worksheet")); 
		return element;
	}

	
	public static void SaveOTVisit(WebDriver driver) throws Exception{
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
	
	// @Method for filling Electronic signature
	public static void FillElectronicSignature(WebDriver driver)
			throws Exception {
		Waits.fluentWaitIsDisplayed(driver, txt_ES_ElectronicSignature(driver), 10);
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

	public static WebElement btn_ES_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		return element;
	}

	public static WebElement btn_CV_SaveGcodeVerification(WebDriver driver) {
		element = driver.findElement(By.id("gcodeModalOKButton"));
		return element;
	}

}
