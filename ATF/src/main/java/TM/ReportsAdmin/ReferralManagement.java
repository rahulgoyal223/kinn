package TM.ReportsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.Waits;

public class ReferralManagement {
	
	public static void acceptReferral(WebDriver driver, String patientName) throws Exception {
		Waits.ForBrowserLoad(driver);
		driver.findElement(By.linkText(patientName)).click();
		Waits.ForBrowserLoad(driver);
		driver.findElement(By.xpath("//*[@value='Accept Referral']")).click();		
	}
}
