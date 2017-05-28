package AM.ExternalReferral;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReviewExternalReferral {

	private static WebElement element = null;

	public static void SelectActiveExternalReferral(WebDriver driver,
			String Name) throws Exception {

	}

	// @ User Manager test objects
	public static WebElement lnk_ExternalReferralIndex(WebDriver driver) {
		element = driver.findElement(By.id("Name"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
