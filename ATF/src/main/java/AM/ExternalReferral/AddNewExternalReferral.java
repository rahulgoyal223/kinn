package AM.ExternalReferral;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewExternalReferral {

	private static WebElement element = null;
	private static Select list = null;

	// @ Test Objects for add new external referral screen
	// @ Objects External Referral Information
	public static WebElement txt_ERI_Name(WebDriver driver) {
		element = driver.findElement(By.id("Name"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_Company(WebDriver driver) {
		element = driver.findElement(By.id("Company"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("AddressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_ZIPCode(WebDriver driver) {
		element = driver.findElement(By.id("ZIPCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_City(WebDriver driver) {
		element = driver.findElement(By.id("City"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_State(WebDriver driver) {
		element = driver.findElement(By.id("State"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_Phone(WebDriver driver) {
		element = driver.findElement(By.id("Phone"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ERI_Fax(WebDriver driver) {
		element = driver.findElement(By.id("Facimile"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_ERI_InternalReferralSource(WebDriver driver) {
		element = driver.findElement(By.id("fClinicUserKeyInternalReferral"));
		list = new Select(driver.findElement(By
				.id("fClinicUserKeyInternalReferral")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement btn_ERI_AddExternalReferral(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='CFForm_1']/fieldset/div[2]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
