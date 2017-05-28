package AM.ReportsAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

public class Administration {
	private static WebElement element = null;

	// @Method to select the menus
	public static void SelectTask(WebDriver driver, String Menus)
			throws InterruptedException {
		try {
			String[] parts = Menus.split("/");
			String part1 = parts[0];

			switch (part1) {
			case "Administration":
				String part2 = parts[1];
				boolean isMenuSelected = false;
				List<WebElement> menus = driver
						.findElements(By
								.xpath("//*[contains(@href,'/am/') and contains(@class,'HotBox')]"));
				for (WebElement menu : menus) {
					String linktxt = menu.getText().trim();
					if (linktxt.equals(part2)) {
						menu.click();
						isMenuSelected = true;
						break;
					}
				}
					if (isMenuSelected = true) {
						Report.Log(Status.PASS, "" + part2 + " under " + part1
								+ " is selected successfully");
						Assert.assertTrue(isMenuSelected, "Patient is selected");
						break;
					} else {
						Report.Log(Status.FAIL, "" + part2 + " under " + part1
								+ " is NOT selected successfully");
						Assert.assertFalse(false);
						break;
					
				}

				// TODO To add cases for other menus
			case "":

				// To Navigate thru Reports Links
			case "Reports":

				// To Navigate thru Hard Close Links
			case "Hard Close":

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static WebElement txt_AP_PhysicianLastName(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='referralManagementTable']/tbody/tr/td[1]/a/"));
		//Waits.ForElementVisibility(driver, element);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='3px solid red'", element);
		return element;
	}
}
