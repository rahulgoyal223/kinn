package AM.ReportsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import components.Waits;

public class ReferralManagement {
	
	public static void SelectManageReferrals(WebDriver driver) throws Exception {
		Waits.ForBrowserLoad(driver);
		driver.findElement(By.linkText("Manage Referrals")).click();
	}
	
	public static void sendReferral(WebDriver driver, String MRN) throws Exception {
		Waits.ForBrowserLoad(driver);
		WebElement toBeSent = driver.findElement(By.xpath("//*/li[contains(text(), '" + MRN + "')]/ancestor::ul[1]/li[1]/input"));
		toBeSent.click();
		driver.findElement(By.xpath("//*[@value='Send Referral(s)']")).click(); 
	}

}
