package components;

import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {

	private static WebDriver driver;

	public static WebDriver getDriver(String browsertype, String Url,
			String driverpath) throws MalformedURLException {
		WebDriver ffdriver;
		WebDriver iedriver;
		WebDriver crdriver;
		switch (browsertype) {
		case "firefox":
			if (Config.isGridExecution()) {
				DesiredCapabilities capability = DesiredCapabilities.firefox();
				ffdriver = new RemoteWebDriver(new URL(
						Config.getGridHubAddress()), capability);
			} else {
				ffdriver = new FirefoxDriver();
			}
			ffdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			ffdriver.manage().window().maximize();
			ffdriver.navigate().to(Url);
			Report.Log(Status.INFO, "Browser firefox is opened with url '"
					+ Url + "'");
			setDriver(ffdriver);
			return ffdriver;
		case "ie":
			System.setProperty("webdriver.ie.driver", driverpath
					+ "iedriver.exe");
			if (Config.isGridExecution()) {
				DesiredCapabilities capability = DesiredCapabilities
						.internetExplorer();
				iedriver = new RemoteWebDriver(new URL(
						Config.getGridHubAddress()), capability);
			} else {
				iedriver = new InternetExplorerDriver();
			}
			iedriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			iedriver.manage().window().maximize();
			iedriver.navigate().to(Url);
			Report.Log(Status.INFO, "Browser IE is opened with url '" + Url
					+ "'");
			setDriver(iedriver);
			return iedriver;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverpath
					+ "chromedriver.exe");
			if (Config.isGridExecution()) {
				DesiredCapabilities capability = DesiredCapabilities.chrome();
				crdriver = new RemoteWebDriver(new URL(
						Config.getGridHubAddress()), capability);
			} else {
				crdriver = new ChromeDriver();
			}
			crdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			crdriver.manage().window().maximize();
			crdriver.navigate().to(Url);
			Report.Log(Status.INFO, "Browser chrome is opened with url '" + Url
					+ "'");
			setDriver(crdriver);
			return crdriver;
		default:
			System.out.println("Please provide right target browser");
		}
		return null;

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Browser.driver = driver;
	}

	public static void closeDriver(WebDriver driver) {
		driver.close();
	}
	
	public static void teardownTest() {
		Report.attachScreenShotToReport(driver);
		driver.quit();
	}
}
