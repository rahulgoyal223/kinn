package AM.Patient;


	import java.util.List;

import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

	public class EpisodeList {

		private static WebElement element = null;

		public static void SelectActiveExternalReferral(WebDriver driver,
				String Name) throws Exception {

		}

		// @ User Manager test objects
		public static WebElement lnk_Admission(WebDriver driver) {
		List < WebElement > elements = driver.findElements(By.tagName("a"));
		for (WebElement element:elements){
			String href = element.getAttribute("href");
			if (href.contains("/EHR/#/AM/admission/")) {
				return element; 
			}
		}
		return null;
		}
	}

