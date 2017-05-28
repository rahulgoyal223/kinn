package KH.ReportsAdmin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import components.Report;
import components.Waits;

public class ViewRestrictedClinicSettings {

private static WebElement element = null;

	//@Method to disable QAPI Restricted Clinic Setting in the Settings box
	public static void disableQAPIDashboard(WebDriver driver) throws Exception {		
		if (chk_QAPIDashboard(driver).isSelected() == true) {
				chk_QAPIDashboard(driver).click();
				btn_SaveChangesToSettings(driver).click();
				Report.attachScreenShotToReport(driver);
		}	
	}

	//@Method to enable QAPI Restricted Clinic Setting in the Settings box
	public static void enableQAPIDashboard(WebDriver driver) throws Exception {
		if (chk_QAPIDashboard(driver).isSelected() == false) {
				chk_QAPIDashboard(driver).click();
				btn_SaveChangesToSettings(driver).click();
				Report.attachScreenShotToReport(driver);
		}		
	}

	// @Element to find QAPI checkbox
	public static WebElement chk_QAPIDashboard(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[1]/div[3]/div[2]/form[4]/table/tbody/tr[6]/td[2]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement btn_SaveChangesToSettings(WebDriver driver) {
		element = driver.findElement(By.id("button"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	
	// @Element to select link
	public static WebElement lnk_selectRestrictedClinicSettings(WebDriver driver) {
		element = driver.findElement(By.linkText("Restricted Clinic Settings"));
		Waits.ForElementVisibility(driver, element);
		return element;
	
	}
}
