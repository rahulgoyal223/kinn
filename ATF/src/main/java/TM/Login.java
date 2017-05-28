package TM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.Waits;

public class Login {

	public static void skipExpiredPassword(WebDriver driver) throws Exception {
		if(driver.getPageSource().contains("Your current (or temporary) password has expired")) {
			getBypassThisScreen(driver).click();
			Waits.ForBrowserLoad(driver);
        }
	}
	
	
	public static WebElement getBypassThisScreen(WebDriver driver) {
		return driver.findElement(By.linkText("Bypass this screen"));
	}
}
