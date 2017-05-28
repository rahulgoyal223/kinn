package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;





import com.aventstack.extentreports.Status;
import com.google.common.base.Function;

import java.util.concurrent.TimeUnit;

public class Waits {
	private static WebDriver driver;
	private static int sleepLevelOne = 1000;
	private static int sleepLevelTwo = 2000;
	private static int sleepLevelThree = 3000;
	private static int sleepLevelFour = 4000;
	private static int sleepLevelFive = 5000;
	

	public static void ForElementVisibility(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			//ForBrowserLoad(driver);
		} catch (Exception e) {
			Report.Log(Status.FAIL, e.getMessage());
		}
	}

	public static void ForElementToBeClickable(WebDriver driver,
			WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			ForBrowserLoad(driver);
		} catch (Exception e) {
			Report.Log(Status.FAIL, e.getMessage());
		}
	}

	public static void ForBrowserLoad(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js;
		String pageLoadStatus = null;
		do {
			js = (JavascriptExecutor) driver;
			pageLoadStatus = (String) js
					.executeScript("return document.readyState");
			Thread.sleep(2000);
		} while (!pageLoadStatus.equals("complete"));
		ForPageLoad(driver);
		//ForGlobalAjaxLoader(driver);
	}
	
	/** This method is for waiting for the user's homepage to load after logging in or submitting documents and things like that
	 * @param driver
	 * @param homepage ("Hotbox" is the only thing coded right now...needs Patient Manager and maybe Inbox)
	 * @throws InterruptedException
	 */
	public static void ForHomePage(WebDriver driver, String homepage) throws InterruptedException{
		if(homepage == "Hotbox"){
			Waits.fluentWaitIsDisplayed(driver, "apply-filters", 30);
		}
		else{
			Thread.sleep(1000);
		}
		Waits.ForBrowserLoad(driver);
	}

	public static void ForGlobalAjaxLoader(WebDriver driver) {
		try {
			WebElement element = driver.findElement(By.id("globalAjaxLoader"));
			do {
				// System.out.println("Inside - " +
				// element.getAttribute("style"));
				Thread.sleep(1000);
			} while (!element.getAttribute("style").trim()
					.equals("display: none;"));
			// System.out.println("Outside - " + element.getAttribute("style"));
		} catch (Exception e) {
			Report.Log(Status.FAIL, e.getMessage());
		}
	}

	private static void ForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void ForPageWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void ForElementStaleness(WebDriver driver, WebElement staleElement) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.stalenessOf(staleElement));
		Waits.ForElementToBeClickable(driver, driver.findElement(By.linkText("Go To")));
	}
			
	public static WebElement StalenessPreset(WebDriver driver){
		Waits.ForElementToBeClickable(driver, driver.findElement(By.linkText("Go To")));
		WebElement stalenessPreset = driver.findElement(By.linkText("Go To"));
		return stalenessPreset;
	}
	
	public static int getSleepLevelOne() {
		return sleepLevelOne;
	}

	public static int getSleepLevelTwo() {
		return sleepLevelTwo;
	}

	public static int getSleepLevelThree() {
		return sleepLevelThree;
	}

	public static int getSleepLevelFour() {
		return sleepLevelFour;
	}

	public static int getSleepLevelFive() {
		return sleepLevelFive;
	}
	
	/**
	 * Fluent wait for WebElement to be enabled
	 * polls page every second for time set in minutes(timeout)
	 * @param element(getter)
	 * @param timeout
	 */
	public static void fluentWaitIsEnabled(WebDriver driver, final WebElement element, final int timeout) {
		final WebElement we = element;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					return element.isEnabled();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * Fluent wait for WebElement to be disabled
	 * polls page every second for time set in minutes(timeout)
	 * @param element(getter)
	 * @param timeout
	 */
	public static void fluentWaitIsNotEnabled(WebDriver driver, final WebElement element, final int timeout) {
		final WebElement we = element;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					return !(element.isEnabled());
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * Fluent wait for WebElement to be displayed
	 * polls page every second for time set in minutes(timeout)
	 * @param element(getter)
	 * @param timeout
	 */
	public static void fluentWaitIsDisplayed(WebDriver driver, final WebElement element, final int timeout) {
		final WebElement we = element;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * @param driver
	 * @param element
	 * @param timeout
	 */
	public static void fluentWaitIsNotDisplayed(WebDriver driver, final WebElement element, final int timeout) {
		final WebElement we = element;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					return !(element.isDisplayed());
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * Boolean fluent wait for WebElement to be displayed = true
	 * polls page every second for time set in minutes(timeout)
	 * @param element(getter)
	 * @param timeout
	 * @return 
	 */
	public static boolean fluentWaitIsDisplayedTrue(WebDriver driver, final WebElement element, final int timeout) {
		final WebElement we = element;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return true;
		}
		return true;
	}
	
	/**
	 * FluentWait to wait for element to be displayed.
	 * polls page every second for time set in minutes(timeout)
	 * @throws Exception
	 */
	public static void fluentWaitIsDisplayed(WebDriver driver, final String elementid, final int timeout) {
		final String id = elementid;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = d.findElement(By.id(id));
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * FluentWait to wait for element to be displayed.
	 * polls page every second for time set in minutes(timeout)
	 * @throws Exception
	 */
	public static void fluentWaitTextIsDisplayed(WebDriver driver, final WebElement element, final String text, final int timeout) {
		final WebElement we = element;
		final String txt = text;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = we;
					ExpectedConditions.textToBePresentInElement(we, txt);
					return element.isDisplayed();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
	/**
	 * FluentWait to wait for element to be disabled.
	 * polls page every second for time set in minutes(timeout)
	 * @throws Exception
	 */
	public static void fluentWaitIsDisabled(WebDriver driver, final String elementid, final int timeout) {
		final String id = elementid;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = d.findElement(By.id(id));
					return !element.isEnabled();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}
	
		/**
	 * FluentWait to wait for element to be enabled.
	 * polls page every second for time set in minutes(timeout)
	 * @throws Exception
	 */
	public static void fluentWaitIsEnabled(WebDriver driver, final String elementid, final int timeout) {
		final String id = elementid;
		try {
			new FluentWait<WebDriver>(driver)
			.withTimeout(timeout, TimeUnit.MINUTES)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver d) {
					WebElement element = d.findElement(By.id(id));
					return element.isEnabled();
				}
			});
		} catch (TimeoutException te) {
			System.out.println(te);
			return;
		}
	}

}
