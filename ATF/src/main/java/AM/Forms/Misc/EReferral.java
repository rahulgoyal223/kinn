package AM.Forms.Misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DataSource.Datatable;

public class EReferral {

	private static String strInputValue;
	
	public static Select getReferTo(WebDriver driver) {
		WebElement element = null;
		Select list = null;
		element = driver.findElement(By.name("clinickey"));
		list = new Select(element);
		return list;
	}
	
	public static void singAndSubmit(WebDriver driver) throws Exception {
		strInputValue = Datatable.GetValue("ER_signatureRequired");
		if (!strInputValue.trim().isEmpty()) {
			chkReturnToClinicianSignature(driver).click();

		}
		strInputValue = Datatable.GetValue("ER_clinicianSignature");
		if (!strInputValue.trim().isEmpty()) {
			txtElectronicSignature(driver).sendKeys(strInputValue);

		}

		strInputValue = Datatable.GetValue("ER_signatureDate");
		if (!strInputValue.trim().isEmpty()) {
			dtSignatureDate(driver).sendKeys(strInputValue);

		}

		btnSubmit(driver).click();
	}
	
	public static WebElement chkReturnToClinicianSignature(WebDriver driver) {
		return driver.findElement(By.id("signatureRequired"));
	}

	public static WebElement txtElectronicSignature(WebDriver driver) {
		return driver.findElement(By.id("clinicianSignature"));
	}

	public static WebElement dtSignatureDate(WebDriver driver) {
		return driver.findElement(By.name("signDate"));
	}
	
	public static WebElement btnSubmit(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@value='Submit']")); 
	}

}
