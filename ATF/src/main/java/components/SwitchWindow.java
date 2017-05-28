package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;

import java.util.Set;

public class SwitchWindow {
	public static void to(WebDriver driver, String title) {
		try {
			String currentWindow = driver.getWindowHandle();
			Set<String> openAllWindos = driver.getWindowHandles();
			if (!openAllWindos.isEmpty()) {
				for (String windowId : openAllWindos) {
					String switchedWindowTitle = driver.switchTo()
							.window(windowId).getTitle();
					if ((switchedWindowTitle.equals(title))
							|| (switchedWindowTitle.contains(title))) {
						driver.switchTo().window(windowId);
						Thread.sleep(Waits.getSleepLevelThree());
						break;

					} else {
						driver.switchTo().window(currentWindow);
						Thread.sleep(Waits.getSleepLevelThree());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void toTopWindow(WebDriver driver)
			throws InterruptedException {
		driver.switchTo().window(
				driver.switchTo().defaultContent().getWindowHandle());
		System.out.println("Switched");
		Thread.sleep(Waits.getSleepLevelThree());
	}

	public static void toParentWindow(WebDriver driver)
			throws InterruptedException {
		driver.switchTo().window(
				driver.switchTo().parentFrame().getWindowHandle());
		Thread.sleep(Waits.getSleepLevelThree());
	}
	
	public static void toModalDialog(WebDriver driver) throws InterruptedException {
		String parent = driver.getWindowHandle();
		if(driver.getWindowHandles().size() >= 2) {
			for(String window : driver.getWindowHandles()) {
				if(!window.equals(parent)) {
					driver.switchTo().window(window);
					System.out.println("Modal found");
					break;
				}
			}
		}
	}
}
