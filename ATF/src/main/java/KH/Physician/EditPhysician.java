package KH.Physician;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class EditPhysician {
	private static WebElement element = null;

	// @Edit Physician Objects
	public static WebElement txt_EP_LastName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianLastName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_FirstName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianFirstName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Suffix(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianSuffix"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_PhysicianUPIN(WebDriver driver) {
		element = driver.findElement(By.id("M0072"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_PhysicianNPI(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianNPI"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_PhysicianLicenseNum(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianStateID"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_EP_LicenseExpiration(WebDriver driver) {
		element = driver.findElement(By.name("LicenseExpiration"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_CommunityCareNum(WebDriver driver) {
		element = driver.findElement(By.id("CommunityCareID"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_ContactPerson(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianContact"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Address(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianAddressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianZIPCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_ZipCodePlus4(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianZIPCodePlus4"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_City(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianCity"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_State(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianState"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Fax1(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimilea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Fax2(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimileb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Fax3(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimilec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_PhysicianContactEmail(WebDriver driver) {
		element = driver.findElement(By.id("physicianEmail"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_EP_ExternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("ExternalReferral"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_AlternateAddress(WebDriver driver) {
		element = driver.findElement(By.id("altAddr"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EP_Comments(WebDriver driver) {
		element = driver.findElement(By.name("PhysicianComment"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_EP_AddPhysician(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_EP_DeletePhysician(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='PhysicianForm']/div[2]/div[6]/form/input[2]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
