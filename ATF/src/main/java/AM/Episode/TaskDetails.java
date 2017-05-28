package AM.Episode;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import DataSource.Datatable;
import components.Verify;
import components.Waits;

public class TaskDetails {

	private static WebElement element;
	private static String strInputValue;

	// @Method to fill in Task Details
	public static void FillTaskDetails(WebDriver driver) throws Exception {
		Verify.ExactPageHeader(driver, "Task Details");
		strInputValue = DataSource.Datatable.GetValue("TD_ExternalVisit");
		if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
			chk_TD_External(driver).click();
		}
		strInputValue = DataSource.Datatable.GetValue("TD_VisitDate");
		if (!strInputValue.isEmpty()) {
			String[] strInputValuenew = new String[3];
			strInputValuenew = strInputValue.split("/");
			txt_TD_VisitDateMonth(driver).sendKeys(strInputValuenew[0]);
			txt_TD_VisitDateDay(driver).sendKeys(strInputValuenew[1]);
			txt_TD_VisitDateYear(driver).sendKeys(strInputValuenew[2]);
		}

		strInputValue = DataSource.Datatable.GetValue("TD_TimeIn");
		if (!strInputValue.isEmpty()) {
			String[] strInputValuenew1 = new String[2];
			strInputValuenew1 = strInputValue.split(":");
			txt_TD_TimeInHour(driver).sendKeys(strInputValuenew1[0]);
			txt_TD_TimeInMins(driver).sendKeys(strInputValuenew1[1]);
		}

		strInputValue = DataSource.Datatable.GetValue("TD_TimeOut");
		if (!strInputValue.isEmpty()) {
			String[] strInputValuenew2 = new String[2];
			strInputValuenew2 = strInputValue.split(":");
			txt_TD_TimeOutHour(driver).sendKeys(strInputValuenew2[0]);
			txt_TD_TimeOutMins(driver).sendKeys(strInputValuenew2[1]);
			
		btn_UpdateTask(driver).click();
		Waits.ForElementStaleness(driver, txt_TaskDetailsHeader(driver));

		}
	}
	
	public static void FillMissedVisit(WebDriver driver) throws Exception {
		Verify.ExactPageHeader(driver, "Task Details");
		chk_TD_MissedVisit(driver).click();	      
	    btn_UpdateTask(driver).click();
	}

	public static void FillMissedVisitWithDateAndTime(WebDriver driver) throws Exception {
		AM.Episode.EpisodeManager.chk_MissedVisit(driver).click();
	    AM.Episode.EpisodeManager.chk_ExternalVisit(driver).click();
	     
	    strInputValue = Datatable.GetValue("Visit_TimeInHr");
		if (!strInputValue.trim().isEmpty()) {
			txt_TD_TimeInHour(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Visit_TimeInMin");
		if (!strInputValue.trim().isEmpty()) {
			txt_TD_TimeInMins(driver).sendKeys(strInputValue);
		}
		  
	    strInputValue = Datatable.GetValue("Visit_TimeOutHr");
		if (!strInputValue.trim().isEmpty()) {
			txt_TD_TimeOutHour(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("Visit_TimeOutMin");
		if (!strInputValue.trim().isEmpty()) {
			txt_TD_TimeOutMins(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("VD_Month");
				if (!strInputValue.trim().isEmpty()) {
					txt_TD_VisitDateMonth(driver).sendKeys(strInputValue);
		}
				strInputValue = Datatable.GetValue("VD_Day");
				if (!strInputValue.trim().isEmpty()) {
					txt_TD_VisitDateDay(driver).sendKeys(strInputValue);
		}
				

				strInputValue = Datatable.GetValue("VD_Year");
				if (!strInputValue.trim().isEmpty()) {
					txt_TD_VisitDateYear(driver).sendKeys(strInputValue);
					
		}
				AM.Episode.EpisodeManager.btn_taskdetails_UpdateTasks(driver).click();
				Waits.ForGlobalAjaxLoader(driver);
				
	}
	// @objects for Task Details
	public static WebElement chk_TD_External(WebDriver driver) {
		element = driver.findElement(By.id("ExternalVisit"));
		return element;

	}

	public static WebElement txt_TD_VisitDateMonth(WebDriver driver) {
		element = driver.findElement(By.id("VisitdateMonth"));
		return element;

	}

	public static WebElement txt_TD_VisitDateDay(WebDriver driver) {
		element = driver.findElement(By.id("VisitdateDay"));
		return element;

	}

	public static WebElement txt_TD_VisitDateYear(WebDriver driver) {
		element = driver.findElement(By.id("VisitdateYear"));
		return element;

	}

	public static WebElement txt_TD_TimeInHour(WebDriver driver) {
		element = driver.findElement(By.id("timeinHour"));
		return element;

	}

	public static WebElement txt_TD_TimeInMins(WebDriver driver) {
		element = driver.findElement(By.id("timeinMinutes"));
		return element;

	}

	public static WebElement txt_TD_TimeOutHour(WebDriver driver) {
		element = driver.findElement(By.id("TimeOutHour"));
		return element;

	}

	public static WebElement txt_TD_TimeOutMins(WebDriver driver) {
		element = driver.findElement(By.id("TimeOutMinutes"));
		return element;

	}

	public static WebElement chk_TD_MissedVisit(WebDriver driver) {
		element = driver.findElement(By.id("MissedVisit"));
		return element;

	}

	public static WebElement btn_UpdateTask(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("taskdetailsubmit"));
		Waits.ForBrowserLoad(driver);
		Waits.ForGlobalAjaxLoader(driver);
		Thread.sleep(Waits.getSleepLevelFive());
		return element;

	}
	
	public static WebElement txt_TaskDetailsHeader(WebDriver driver) {
		 element = driver.findElement(
				By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]"));
		return element;
	}


	public static WebElement cbo_SelectedHCPCSCode(WebDriver driver) {
		WebElement el = driver.findElement(By.xpath("//select[@id='selectedHCPCSCode']"));
		Waits.ForElementVisibility(driver, el);
		return el;
	}

	public static void verifySelectedHCPCSCode(WebDriver driver, String expectedValue) {

		Verify.verifyDropdownSelectedOption(driver, cbo_SelectedHCPCSCode(driver), expectedValue, "Task Details. HCPCS code selected ");
	}
}
