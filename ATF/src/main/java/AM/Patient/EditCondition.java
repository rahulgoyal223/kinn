package AM.Patient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public  class EditCondition {
	
	// @declare all episode condition variables in this section
	
	private static WebElement element = null;
	private static Select listbox = null;

	// @Objects Edit Episode
	public static WebElement btn_EE_Save(WebDriver driver) {
		element = driver.findElement(By.id("save_button"));
		return element;
	}
	

public static Select lst_ConditionCode18(WebDriver driver) {
	WebElement element = driver.findElement(By.id("conditionCode18"));
    Select list = new Select(element);
    Waits.ForElementVisibility(driver, element);
    return list;
}}