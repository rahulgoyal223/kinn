package KH.Profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class UserProfile {
	private static WebElement element = null;
	private static Select list = null;

	// @User Profile Objects
	public static WebElement btn_UP_UpdateButton(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='userProfile']/input[2]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Login Password Objects
	public static WebElement txt_LP_CurrentPassword(WebDriver driver) {
		element = driver.findElement(By.id("oldpassword"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_LP_NewPassword(WebDriver driver) {
		element = driver.findElement(By.id("newpassword1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_LP_ConfirmNewPassword(WebDriver driver) {
		element = driver.findElement(By.id("newpassword2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Electronic Signature Objects
	public static WebElement txt_ES_CurrentSignature(WebDriver driver) {
		element = driver.findElement(By.id("oldSignature"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ES_NewSignature(WebDriver driver) {
		element = driver.findElement(By.id("newSignature1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_ES_ConfirmNewSignature(WebDriver driver) {
		element = driver.findElement(By.id("newSignature2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @My Address Objects
	public static WebElement txt_MA_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("AddressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("ZIPCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_City(WebDriver driver) {
		element = driver.findElement(By.id("City"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_State(WebDriver driver) {
		element = driver.findElement(By.id("State"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("phonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("phoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("phonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_PhoneTwo1(WebDriver driver) {
		element = driver.findElement(By.id("phone2a"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_PhoneTwo2(WebDriver driver) {
		element = driver.findElement(By.id("phone2b"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_PhoneTwo3(WebDriver driver) {
		element = driver.findElement(By.id("phone2c"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_MA_Email(WebDriver driver) {
		element = driver.findElement(By.id("email"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Security Objects
	public static Select lst_S_Question1(WebDriver driver) {
		element = driver.findElement(By.id("question1"));
		list = new Select(driver.findElement(By.id("question1")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_S_Answer1(WebDriver driver) {
		element = driver.findElement(By.id("answer1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_S_Question2(WebDriver driver) {
		element = driver.findElement(By.id("question2"));
		list = new Select(driver.findElement(By.id("question2")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_S_Answer2(WebDriver driver) {
		element = driver.findElement(By.id("answer2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_S_Question3(WebDriver driver) {
		element = driver.findElement(By.id("question3"));
		list = new Select(driver.findElement(By.id("question3")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_S_Answer3(WebDriver driver) {
		element = driver.findElement(By.id("answer3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
