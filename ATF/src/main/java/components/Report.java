package components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;

public class Report {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static boolean isreportFileCreated = false;

	public static void consoleLog(Status Status, String Details) {
		System.out.println(Status + "- " + Details + "");
	}

	public static void configure(String FilePath, String Host) {

		if (Config.getTestReportType().equals("html")) {
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(FilePath);
			// ExtentXReporter extentxReporter = new ExtentXReporter("host");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
	}

	public static void SetTestName(String Name, String Description) {
		if (Config.getTestReportType().equals("html")) {
			test = extent.createTest(Name, Description);
		}
	}

	public static void Log(Status status, String details) {
		if (Config.getTestReportType().equals("html")) {
			test.log(status, details);
			if (status.toString().equals("fail")) {
				WebDriver driver = Browser.getDriver();
				Report.attachScreenShotToReport(driver);
				consoleLog(status, details);
				Assert.fail(details);
			}
			extent.flush();
		} else {
			consoleLog(status, details);
		}
	}

	/**
	 * Attaches an screenshot of the web page to the report. If parameter element is provided, 
	 * the browser window will scroll until the element is visible in the view port to appear in 
	 * the screenshot.
	 * 
	 * @param driver
	 * @param element 
	 */
	public static void attachScreenShotToReport(WebDriver driver, WebElement element) {
		try {
			String path = getScreenShot(driver, element);
			test.addScreenCaptureFromPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
	}
	
	public static void attachScreenShotOfPDFToReport(WebDriver driver) {
		try {
			String path = getScreenShotOfPDF(driver);
			test.addScreenCaptureFromPath(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
	}
	
	
	public static void attachScreenShotToReport(WebDriver driver) {
		attachScreenShotToReport(driver, driver.findElement(By.tagName("body")));
	}

	public static void assignCategory(String Category) {
		if (Config.getTestReportType().equals("html")) {
			test.assignCategory(Category);
		}
	}
	
	private static String getScreenShotOfPDF(WebDriver driver) throws Exception {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String snapShotName = TimeDate.getUniqueInteger();
		String SetFileName = Config.getScreenShotsPath() + snapShotName
				+ ".png";
		FileUtils.copyFile(src, new File(SetFileName));
		return SetFileName;
	}

	/**
	 * Takes an screenshot of the browser and saves it in a file. If parameter element is provided, 
	 * the browser window will scroll until the element is visible in the view port to appear in 
	 * the screenshot.
	 * 
	 * @return String Screenshot filename.
	 * @param driver
	 * @param element 
	 */
	private static String getScreenShot(WebDriver driver, WebElement element) throws Exception {
		//Waits.ForBrowserLoad(driver);

		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		return getScreenShot(driver);
	}
	
	private static String getScreenShot(WebDriver driver) throws Exception {
    
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String snapShotName = TimeDate.getUniqueInteger();
		String SetFileName = Config.getScreenShotsPath() + snapShotName
				+ ".png";
		FileUtils.copyFile(src, new File(SetFileName));
		return SetFileName;
	}

	public static void generateReportsFile(String reportType,
			String testfileName) {
		Config.setTestReportType(reportType);
		if (Config.getTestReportType().equals("html")) {
			String fileName = TimeDate.getUniqueInteger();
			if (Config.isTestSuite() && !isreportFileCreated) {
				Report.configure(Config.getReportsPath() + "TestSuite_"
						+ fileName + ".html", "");
				isreportFileCreated = true;
			} else if (!Config.isTestSuite()) {
				Report.configure(
						Config.getReportsPath() + Config.getTestEnvironment()
								+ "_" + Config.getTestCycle() + "_"
								+ testfileName + "_" + fileName + ".html", "");
			}
		} else {
			Config.setTestReportType("console");
		}
	}
}
