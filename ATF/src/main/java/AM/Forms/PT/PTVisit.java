package AM.Forms.PT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;

public class PTVisit {

	private static WebElement element;
	private static String StrInputvalue;

	// @Method for filling Nursing visit record
	public static void FillPTVisitRecord(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_ES_Submit(driver));
		StrInputvalue = Datatable.GetValue("PT_TimeIn");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("PT_TimeOut");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("PT_VisitDate");
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_PT_VisitDate(driver)).click()
					.perform();
			dt_PT_VisitDate(driver).clear();
			dt_PT_VisitDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("PT_AssociatedMileage");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_AssociatedMileage(driver).sendKeys(StrInputvalue);
		}

	}

	public static void FillPTVisitRecordWithDate(WebDriver driver, String date) throws Exception {

		StrInputvalue = Datatable.GetValue("PT_TimeIn");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("PT_TimeOut");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_TimeIn(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = date;
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_PT_VisitDate(driver)).click()
					.perform();
			dt_PT_VisitDate(driver).clear();
			dt_PT_VisitDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("PT_AssociatedMileage");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_PT_AssociatedMileage(driver).sendKeys(StrInputvalue);
		}

	}


	public static void SubmitPTVisit(WebDriver driver) {
		try{
			FillElectronicSignature(driver);
			Waits.fluentWaitIsEnabled(driver, btn_ES_Submit(driver), 5);
			btn_ES_Submit(driver).click();
			Waits.ForHomePage(driver, "Hotbox");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "PT Visit was not submitted successfully");
			e.printStackTrace();
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

	// @Method for filling Electronic signature
	public static void FillElectronicSignatureWithDate(WebDriver driver, String date)
			throws Exception {

		StrInputvalue = Datatable.GetValue("ES_ElectronicSignature");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_ES_ElectronicSignature(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = date;
		if (!StrInputvalue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_ES_SignatureDate(driver))
					.click().perform();
			dt_ES_SignatureDate(driver).clear();
			dt_ES_SignatureDate(driver).sendKeys(StrInputvalue);
		}

	}

	// @objects for Task Details
	public static WebElement txt_PT_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("frm_timein"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PT_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("frm_timeout"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_PT_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("frm_visitdate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_PT_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ES_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_ES_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_ES_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_ES_Approve(WebDriver driver) {
		element = driver.findElement(By.id("btnApprove"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

    public static WebElement chk_SignatureRequired(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
    }
}
