package KH.Insurance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.Waits;

public class InsuranceManager {
	private static WebElement element = null;

	public static void SelectActiveInsurance(WebDriver driver,
			String InsuranceName) throws Exception {

	}

	// @ Insurance Manager test objects
	public static WebElement lnk_InsuranceIndex(WebDriver driver) {
		element = driver.findElement(By.id("InsuranceName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
