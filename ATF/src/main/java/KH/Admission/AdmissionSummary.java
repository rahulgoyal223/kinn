package KH.Admission;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import components.Waits;

public class AdmissionSummary {

	public static void SelectAdmission(WebDriver driver, String admissionDate) throws Exception {
		
		Waits.ForGlobalAjaxLoader(driver);

		WebElement element = driver.findElement(By.linkText(admissionDate));
		
		element.click();

		return;
	}
}
