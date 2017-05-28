package KH.Forms.Nursing;

import DataSource.Datatable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SNV {

	private static WebElement element = null;
	private static String strInputValue;

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

	public static WebElement txt_SNV_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("frm_timein"));
		return element;
	}
		
	public static WebElement txt_SNV_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("frm_timeout"));
		return element;
	}

	public static WebElement txt_SNV_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("frm_visitdate"));
		return element;
	}
	
	public static WebElement chk_Return_to_Clinician(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
	}
	
	public static WebElement txt_Clinician_Signature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}
	
	public static WebElement txt_Signature_Date(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}
	
	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		return element;
	}
	
	public static WebElement btn_save_continue(WebDriver driver) {
		element = driver.findElement(By.name("sc"));
		return element;
	}
	
	public static WebElement btn_save_submit(WebDriver driver) {
		element = driver.findElement(By.id("btnSumbit"));
		return element;
	}

	public static void FillHeader(WebDriver driver) throws Exception {
	
		strInputValue = Datatable.GetValue("TimeIn");
		if (!strInputValue.trim().isEmpty()) {
			txt_SNV_TimeIn(driver).clear();
			txt_SNV_TimeIn(driver).sendKeys(strInputValue);
		}
	
		strInputValue = Datatable.GetValue("TimeOut");
		if (!strInputValue.trim().isEmpty()) {
			txt_SNV_TimeOut(driver).clear();
			txt_SNV_TimeOut(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("VisitDate");
		if (!strInputValue.trim().isEmpty()) {
			txt_SNV_VisitDate(driver).clear();
			txt_SNV_VisitDate(driver).sendKeys(strInputValue);
		}
	
	}
		
	// @Method To sign and submit the SNV
	public static void FillFooter(WebDriver driver) throws Exception {

		chk_Return_to_Clinician(driver).click();
		
		strInputValue = Datatable.GetValue("Signature");
		if (!strInputValue.trim().isEmpty()) {
			txt_Clinician_Signature(driver).clear();
			txt_Clinician_Signature(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("SignatureDate");
		if (!strInputValue.trim().isEmpty()) {
			txt_Signature_Date(driver).clear();
			txt_Signature_Date(driver).sendKeys(strInputValue);
		}
			
	}
		
	// @Method To fill the SNV
		public static void FillSNVComplete(WebDriver driver) throws Exception {


			FillHeader(driver);
			
			
			
			
			FillFooter(driver);
			btn_save_submit(driver).click();

		}

}
