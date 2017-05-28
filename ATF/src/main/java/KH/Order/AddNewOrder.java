package KH.Order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class AddNewOrder {
	private static WebElement element = null;
	private static Select list = null;

	// @New Order Objects
	public static WebElement chk_NO_SendAsKMail(WebDriver driver) {
		element = driver.findElement(By.name("SendMessage"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_NO_PatientName(WebDriver driver) {
		element = driver.findElement(By.name("patientkey"));
		list = new Select(driver.findElement(By.name("patientkey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement dt_NO_OrderDate(WebDriver driver) {
		element = driver.findElement(By.id("OrderDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_NO_AttendingPhysician(WebDriver driver) {
		element = driver.findElement(By.name("physiciankey"));
		list = new Select(driver.findElement(By.name("physiciankey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_NO_AssignedClinician(WebDriver driver) {
		element = driver.findElement(By.name("AmuserKey"));
		list = new Select(driver.findElement(By.name("AmuserKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_NO_UseTemplate(WebDriver driver) {
		element = driver.findElement(By.id("OrderText_template"));
		list = new Select(driver.findElement(By.id("OrderText_template")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_NO_OrderText(WebDriver driver) {
		element = driver.findElement(By.id("OrderText"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_NO_OrderSummary(WebDriver driver) {
		element = driver.findElement(By.name("OrderSummary"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_NO_AddOrder(WebDriver driver) {
		element = driver
				.findElement(By.xpath("//*[@id='PTForm']/div[6]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
