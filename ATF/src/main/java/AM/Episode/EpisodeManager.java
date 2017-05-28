package AM.Episode;

import DataSource.Datatable;

import com.aventstack.extentreports.Status;


import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EpisodeManager {

	static String strInputValue;
	/* Global variables */
	private static Select list;
	private static WebElement element = null;

	/* Episode Manager Methods */
	public static void ScheduleTask(WebDriver driver) throws Exception {
		try {
			strInputValue = Datatable.GetValue("EM_Taskname");
			if (!strInputValue.trim().isEmpty()) {
				lst_Task(driver).selectByVisibleText(strInputValue);
			}

			strInputValue = Datatable.GetValue("EM_Assignto");
			if (!strInputValue.trim().isEmpty()) {
				lst_Assignto(driver).selectByValue(strInputValue);
			}

			strInputValue = DataSource.Datatable.GetValue("EM_Date");
			if (!strInputValue.trim().isEmpty()) {
				SelectDate(driver, strInputValue);
			}
			btn_InsertUpdateTasks(driver).click();
			Report.Log(Status.PASS, "Task has been scheduled successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Task has not been scheduled");
			Assert.fail("Task has not been scheduled");
		}
	}
	public static void AddScheduleTasks(WebDriver driver) throws Exception {
		try {
			lst_Task(driver).selectByVisibleText(
					Datatable.GetValue("EM_Taskname"));
			lst_Assignto(driver).selectByValue(
					Datatable.GetValue("EM_Assignto"));
			SelectDate(driver, Datatable.GetValue("EM_Date"));
			btn_InsertUpdateTasks(driver).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static void AddScheduleTasksWithDate(WebDriver driver, String date) throws Exception {
		try {
			lst_Task(driver).selectByVisibleText(
					Datatable.GetValue("EM_Taskname"));
			lst_Assignto(driver).selectByVisibleText(
					Datatable.GetValue("EM_Assignto"));
			SelectDate(driver, date);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void DeleteScheduleTasks(WebDriver driver, String TaskDetails)
			throws Exception {
		try {
			boolean isTaskFound = false;
			WebElement table = driver.findElement(By
					.xpath("//*[@id='AMContainer']/div[9]/form/div[1]/table"));
			List<WebElement> allrows = table.findElements(By.tagName("tr"));
			for (WebElement row : allrows) {
				System.out.println(row.getText());
				if (row.getText().contains(TaskDetails)) {
					element = row.findElement(By
							.xpath(".//input[@type = 'Checkbox']"));
					driver.manage().timeouts()
					.implicitlyWait(30, TimeUnit.SECONDS);
					element.click();
					isTaskFound = true;
					break;
				}
			}
			if (isTaskFound) {
				Report.Log(Status.PASS, "Task - '" + TaskDetails
						+ "' is selected to delete");
				btn_InsertUpdateTasks(driver).click();
			} else {
				Report.Log(Status.FAIL, "Task - '" + TaskDetails
						+ "' is NOT selected to delete");
				Assert.fail("Task - '" + TaskDetails
						+ "' is NOT selected to delete");
			}
		} catch (Exception e) {
			System.out.println("task not found");
			e.printStackTrace();
		}
	}

	/* date format in the Excel should be 'MM/DD/YYYY */
	private static WebElement SelectDate(WebDriver driver, String dateColumn)
			throws Exception {
		System.out.println(dateColumn);
		String[] datevalue;
		datevalue = dateColumn.split("/");
		if (datevalue[0].charAt(0) == '0') {
			datevalue[0] = String.valueOf(datevalue[0].charAt(1));
		}
		if (datevalue[1].charAt(0) == '0') {
			datevalue[1] = String.valueOf(datevalue[1].charAt(1));
		}
		element = driver.findElement(By
				.xpath(".//td[contains(@onclick, 'FillDate(" + datevalue[1]
						+ ", " + datevalue[0] + ", " + datevalue[2] + ")')]"));
		element.click();
		Report.Log(Status.PASS, "Date '" + dateColumn + "' is selected");
		return element;
	}

	public static WebElement SelectScheduleTasks(WebDriver driver,
			String taskname) throws Exception {
		try {
			boolean isTaskFound = false;
			List<WebElement> allrows = driver
					.findElements(By
							.xpath("//*[contains(@href,'/am/patient') and contains(@id,'Tasked')]"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().equals(taskname)) {
					row.click();
					isTaskFound = true;
					break;
				}
			}
			if (isTaskFound) {
				Report.Log(Status.PASS, "Task - '" + taskname + "' is selected");
			} else {
				Report.Log(Status.FAIL, "Task - '" + taskname
						+ "' is NOT selected");
				Assert.fail("Task - '" + taskname
						+ "' is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static WebElement SelectScheduledTaskDetails(WebDriver driver,
			String taskname) throws Exception {
		try {
			boolean isTaskFound = false;
			Waits.ForElementToBeClickable(driver, driver
					.findElement(By.xpath("//*[contains(@href,'/am/patient') and contains(@id,'Details')]")));
			List<WebElement> allrows = driver
					.findElements(By
							.xpath("//*[contains(@href,'/am/patient') and contains(@id,'Details')]"));
			System.out.println(allrows.size());
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				System.out.println(linkTxt);
				if (linkTxt.trim().equals(taskname)) {
					row.click();
					isTaskFound = true;
					break;
				}
			}
			if (isTaskFound) {
				Report.Log(Status.PASS, "Task - '" + taskname + "' is selected");
			} else {
				Report.Log(Status.FAIL, "Task - '" + taskname
						+ "' is NOT selected");
				Assert.fail("Task - '" + taskname
						+ "' is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public static void SelectTaskDetailsByRow(WebDriver driver, String rownum) throws Exception {
		try{
			Waits.ForElementToBeClickable(driver, driver.findElement(By.id("Details" + rownum)));
			driver.findElement(By.id("Details" + rownum)).click();
			Verify.ExactPageHeader(driver, "Task Details");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static WebElement PrintViewScheduleTasks(WebDriver driver,
			String taskname) throws Exception {
		try {
			boolean isTaskFound = false;
			List<WebElement> allrows = driver
					.findElements(By
							.xpath("//*[contains(@href,'/am/patient') "
									+ "and contains(@id,'Tasked') "
									+ "and text()='" + taskname + "']"
									+ "/ancestor::tr//a[starts-with(@id,'PrintView')]"
									));
			for (WebElement row : allrows) {
				row.click();
				isTaskFound = true;
				break;
			}
			if (isTaskFound) {
				Report.Log(Status.PASS, "Print View - '" + taskname + "' is selected");
			} else {
				Report.Log(Status.FAIL, "Print View - '" + taskname
						+ "' is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static WebElement OpenPrintViewTab(WebDriver driver){

		ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
		if (!availableWindows.isEmpty()) {
			driver.switchTo().window(availableWindows.get(1));
		}
		return element;
	}

	public static WebElement SelectTaskTab(WebDriver driver, String tabname)
			throws Exception {
		try {
			Waits.ForElementToBeClickable(driver, driver.findElement(By.linkText(tabname)));
			driver.findElement(By.linkText(tabname)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	//@Method to verify Patient Name in EpisodeManager
	public static void VerifyPatientName(WebDriver driver, String PatientName) {
		try {
			Waits.ForBrowserLoad(driver);
			String header = driver.findElement(
					By.xpath("//h3")).getText();
			if (header.trim().equals(PatientName)) {
				Report.Log(Status.PASS, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "The page header displayed is - '"
						+ header + "'");
				Report.attachScreenShotToReport(driver);
				Assert.fail("The page header displayed is - '"
						+ header + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Test objects captured under Episode Manager */
	public static Select lst_Task(WebDriver driver) {
		list = new Select(driver.findElement(By.id("PatientTask1")));
		Report.Log(Status.INFO, "Acted on lst_Task");
		return list;
	}

	public static Select lst_Assignto(WebDriver driver) {
		list = new Select(driver.findElement(By.id("AMUserkey1")));
		Report.Log(Status.INFO, "Acted on lst_Assignto");
		return list;
	}

	public static WebElement btn_InsertUpdateTasks(WebDriver driver) {
		element = driver.findElement(By.id("SubmitUpdateTasks"));
		Report.Log(Status.INFO, "Acted on btn_InsertUpdateTasks");
		return element;
	}
	
	//Task Details  object
	public static WebElement chk_MissedVisit(WebDriver driver) {
		element = driver.findElement(By.id("MissedVisit"));
		return element;
	}
	
	public static WebElement chk_ExternalVisit(WebDriver driver) {
		element = driver.findElement(By.id("ExternalVisit"));
		return element;
	}
	
	public static WebElement btn_taskdetails_UpdateTasks(WebDriver driver) {
		element = driver.findElement(By.id("taskdetailsubmit"));
		return element;
	}
	

	public static void VerifyStatus(WebDriver driver, int rownum) throws InterruptedException {
		Waits.ForBrowserLoad(driver);
		String status = null;
		Waits.fluentWaitIsDisplayed(driver, driver.findElement(By.id("Status" + rownum + "")), 10);
		WebElement row = driver.findElement(By.id("Status" + rownum + ""));
		if (row.getText().trim().equals("Completed")
				|| row.getText().trim().equals("Not Yet Due")) {
			status = row.getText();
			Report.Log(Status.PASS, "" + status + " is displayed");
			Report.attachScreenShotToReport(driver);
		} else {
			Report.Log(Status.FAIL, "" + status + " is NOT displayed");
			Report.attachScreenShotToReport(driver);
			Assert.fail("" + status + " is NOT displayed");
		}
	}

	public static void FillReviewDiagnosisandFrequencyOrders(WebDriver driver)
			throws Exception {

		strInputValue = Datatable.GetValue("Primary Diagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_Primary_Diagnosis(driver).clear();
			OA_Primary_Diagnosis(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("Secondary Diagnosis");
		if (!strInputValue.trim().isEmpty()) {
			OA_Secondary_Diagnosis(driver).clear();
			OA_Secondary_Diagnosis(driver).sendKeys(strInputValue);
		}
	}

	public static WebElement OA_Primary_Diagnosis(WebDriver driver) {
		element = driver.findElement(By.name("Primary"));
		return element;
	}

	public static WebElement OA_Secondary_Diagnosis(WebDriver driver) {
		element = driver.findElement(By.name("Secondary"));
		return element;
	}

	public static WebElement btn_UpdateDetails(WebDriver driver) {
		element = driver.findElement(By.id("updateButton"));
		Report.Log(Status.INFO, "Acted on btn_InsertUpdateTasks");
		return element;
	}

	
}
