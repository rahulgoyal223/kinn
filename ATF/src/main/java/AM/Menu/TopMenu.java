package AM.Menu;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TopMenu {

	public static void Select(WebDriver driver, String MenuItems) throws InterruptedException {
		try {
			Report.attachScreenShotToReport(driver);
			toNavigateMenuBar(driver, MenuItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void lnk_Logout(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("Log Out"));
        Waits.ForElementVisibility(driver, element);
        element.click();
    }

	// @ Inner methods to select menu item
	private static void toNavigateMenuBar(WebDriver driver, String Menus)
			throws Exception {
		String[] parts = Menus.split("/");
		int count = parts.length;
		if (count == 1) {
			String part1 = parts[0]; // Main Menu
			toSelectMainMenu(driver, part1);
		}
		if (count == 2) {
			String part1 = parts[0];
			toSelectMainMenu(driver, part1);
			String part2 = parts[1];
			toSelectSubmenu(driver, part2);
		}
		if (count == 3) {
			if (Menus.contains("Reports / Admin")) {
				String part1 = parts[0];
				String part2 = parts[1];
				String part3 = parts[2];
				toSelectMainMenu(driver, part1);
				toSelectSubmenu(driver, part2.concat("/").concat(part3));
			} else {
				String part1 = parts[0];
				toSelectMainMenu(driver, part1);
				String part2 = parts[1];
				toMouseHoverOnSubmenu(driver, part2);
				String part3 = parts[2];
				toSelectSubmenu(driver, part3);
			}
		}
	}

	private static void toSelectMainMenu(WebDriver driver, String mainMenu)
			throws Exception {

		try {
			Waits.fluentWaitIsDisplayed(driver, driver.findElement(By.linkText(mainMenu)), 5);
			driver.findElement(By.linkText(mainMenu)).click();;
		} catch (Exception e) {
			throw e;
		}

	}

	private static void toMouseHoverOnSubmenu(WebDriver driver, String subMenu)
			throws Exception {
		try {
			boolean isMenuItemFound = false;
			List<WebElement> subMnu = driver
					.findElements(By
							.xpath("//*[@class='menuItemText' or @class='ng-binding']"));
			for (WebElement Mnu : subMnu) {
				String rnValue = Mnu.getText().trim();
				if (rnValue.trim().contains(subMenu)) {
					Actions builder = new Actions(driver);
					builder.moveToElement(Mnu).build().perform();
					isMenuItemFound = true;
					break;
				}
			}
			if (isMenuItemFound) {
				Report.Log(Status.PASS, "SubMain menu - " + subMenu
						+ " is selected");
			} else {
				Report.Log(Status.FAIL, "SubMain menu - " + subMenu
						+ " is NOT selected");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private static void toSelectSubmenu(WebDriver driver, String subMenu1)
			throws Exception {
		try {
			Waits.fluentWaitIsDisplayed(driver, driver.findElement(By.linkText(subMenu1)), 5);
			driver.findElement(By.linkText(subMenu1)).click();
		} catch (Exception e) {
			throw e;
		}
	}
}
