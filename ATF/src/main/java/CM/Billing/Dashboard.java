package CM.Billing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;


public class Dashboard {
	
	public static Select lst_hardClosePeriodMonth(WebDriver driver) {
		WebElement element = null;
		Select list = null;
		element = driver.findElement(By.id("hardClosePeriodMonth"));
		list = new Select(driver.findElement(By.id("hardClosePeriodMonth")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
	
	public static WebElement rbn_hardClosePeriodType(WebDriver driver) {
		WebElement element = driver.findElement(By.id("hardClosePeriodType"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

}
