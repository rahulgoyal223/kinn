package CM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class Swapuser {
	public static Select lst_CM_Swapuser(WebDriver driver) {
		WebElement element = null;
		Select list = null;
		element = driver.findElement(By.id("swapUser"));
		list = new Select(driver.findElement(By.id("swapUser")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}
}
