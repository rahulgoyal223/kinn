package AM.User;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserManager {

	private static WebElement element = null;

	public static void SelectActiveUser(WebDriver driver, String UserName)
			throws Exception {

	}

	// @ User Manager test objects
	public static WebElement lnk_UserIndex(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// @Step To click on complete user setup
	public static WebElement btn_U_CompleteUserSetup(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='AMContainer']/div[3]/div/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
