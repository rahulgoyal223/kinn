package KH.ReportsAdmin;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Report;
import components.Waits;



public class ViewQualityDashboard {
	
	private static WebElement element = null;
	
	//Capture Quality Dashboard Element
	public static WebElement getQualityDashboardElement(WebDriver driver) {
		element = driver.findElement(By.linkText("Quality Dashboard"));
		return element;
	}
	
	//@Method to get on Add New Goals button
	public static WebElement getAddNewQAPIGoalElement(WebDriver driver){
		element = driver.findElement(By.id("addNewGoal_btn"));
		return element;
	}
	
	//@Method to get Preset QAPI goals
	public static WebElement getPresetQAPIGoals(WebDriver driver){
		element = driver.findElement(By.id("preset_qapi"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//@Method to get Custom QAPI goals
		public static WebElement getCustomQAPIGoals(WebDriver driver){
			element = driver.findElement(By.id("custom_goal"));
			return element;
		}
		
	//@Method to get Proceed to Select Goal button in Modal-1
	public static WebElement getM1ProceedWithGoal(WebDriver driver){
		element = driver.findElement(By.id("m1_proceed_to_goal"));
		return element;
	}	
	
	//@Method to get Preset condition field in Add new goal Modal-2
	public static WebElement getPresetCondition(WebDriver driver){
		element = driver.findElement(By.id("goal_preset_condition"));
		Waits.ForElementVisibility(driver, element);
		return element;
		}	
	
	//@Method to get Custom goal field in Add new goal Modal-2
	public static WebElement getCustomGoal(WebDriver driver){
		element = driver.findElement(By.id("goal_custom_qapi"));
		return element;
		}	
	
	//@Method to get Percentage field in Add new goal Modal-2
	public static WebElement getPercentage(WebDriver driver){
		element = driver.findElement(By.id("goal_preset_percent"));
		return element;
		}	
	
	//@Method to get Goal Start Date field in Add new goal Modal-2
	public static WebElement getGoalStartDate(WebDriver driver){
		element = driver.findElement(By.id("goalStartDate"));
		return element;
		}	
	
	//@Method to get Start Date date-picker in Modal-2
	public static WebElement getStartDateDatePicker(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='Modal2']/div[5]/img[1]"));
		return element;
	}
	
	//@Method to get End date date-picker in Modal-2
	public static WebElement getEndDateDatePicker(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='Modal2']/div[5]/img[2]"));
		return element;
	}
	
	//@Method to get Start date
	public static WebElement getStartDate(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[1]/a"));
		return element;
	}
	
	//@Method to get Start date
	public static WebElement getEndDate(WebDriver driver){
		element = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[5]/a"));
		return element;
	}
		
	//@Method to get Goal End Date field in Add new goal Modal-2
	public static WebElement getGoalEndDate(WebDriver driver){
		element = driver.findElement(By.id("goalEndDate"));
		return element;
		}	
	
	//@Method to get Proceed to Goal Notes button in Modal-2
	public static WebElement getM2ProceedtoGoalNotes(WebDriver driver){
		element = driver.findElement(By.id("m2_proceed_to_goal_notes"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}	
	
	//@Method to get Goal Notes field in Modal-3
	public static WebElement getGoalNotes(WebDriver driver){
		element = driver.findElement(By.id("goal_notes"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//@Method to get Goal Save button in Modal-3
	public static WebElement getGoalSave(WebDriver driver){
		element = driver.findElement(By.id("m3_savel"));
		return element;
	}	
		
	//@Method to get Success Alert
	public static WebElement getSuccessAlert(WebDriver driver){
		element = driver.findElement(By.id("success-alert"));
		return element;
	}	
	
	//@Method to Select dropdown index
	public static void SelectDropdownIndex(WebDriver driver, WebElement element, int index){
		Waits.ForElementToBeClickable(driver, element);
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}
	
	//@Method to take screenshot of the success alert
	public static void getSuccessScreenshot(WebDriver driver, WebElement element){
		Waits.ForElementVisibility(driver, element);
		Report.attachScreenShotToReport(driver);
	}
		
	//@Method to Add new HIS Goal to the QAPI Goals
	public static void AddHISGoal(WebDriver driver) throws IOException, InterruptedException, Exception{
		ViewQualityDashboard.getAddNewQAPIGoalElement(driver).click();
		String HISGoal = Datatable.GetValue("Preset_HIS_Goal");
		int index = Integer.parseInt(HISGoal);
		ViewQualityDashboard.SelectDropdownIndex(driver, ViewQualityDashboard.getPresetQAPIGoals(driver), index);
		ViewQualityDashboard.getM1ProceedWithGoal(driver).click();
		String condition = Datatable.GetValue("Symbol");
		ViewQualityDashboard.getPresetCondition(driver).sendKeys(condition);
		String percentage = Datatable.GetValue("Percentage");
		ViewQualityDashboard.getPercentage(driver).sendKeys(percentage);
		ViewQualityDashboard.getStartDateDatePicker(driver).click();
		ViewQualityDashboard.getStartDate(driver).click();
		ViewQualityDashboard.getEndDateDatePicker(driver).click();
		ViewQualityDashboard.getEndDate(driver).click();
		ViewQualityDashboard.getM2ProceedtoGoalNotes(driver).click();
		String goalnotes = Datatable.GetValue("Goal_Notes");
		ViewQualityDashboard.getGoalNotes(driver).sendKeys(goalnotes);
		ViewQualityDashboard.getGoalSave(driver).click();
		ViewQualityDashboard.getSuccessScreenshot(driver, ViewQualityDashboard.getSuccessAlert(driver));
	}
	
	//@Method to Add new ESAS Goal to the QAPI Goals
	public static void AddESASGoal(WebDriver driver) throws IOException, InterruptedException, Exception{
		ViewQualityDashboard.getAddNewQAPIGoalElement(driver).click();
		String ESASGoal = Datatable.GetValue("Preset_ESAS_Goal");
		int index = Integer.parseInt(ESASGoal);
		ViewQualityDashboard.SelectDropdownIndex(driver, ViewQualityDashboard.getPresetQAPIGoals(driver), index);
		ViewQualityDashboard.getM1ProceedWithGoal(driver).click();
		String condition = Datatable.GetValue("Symbol");
		ViewQualityDashboard.getPresetCondition(driver).sendKeys(condition);
		String ESASValue = Datatable.GetValue("ESAS_Value");
		ViewQualityDashboard.getPercentage(driver).clear();
		ViewQualityDashboard.getPercentage(driver).sendKeys(ESASValue);
		ViewQualityDashboard.getStartDateDatePicker(driver).click();
		ViewQualityDashboard.getStartDate(driver).click();
		ViewQualityDashboard.getEndDateDatePicker(driver).click();
		ViewQualityDashboard.getEndDate(driver).click();
		ViewQualityDashboard.getM2ProceedtoGoalNotes(driver).click();
		String goalnotes = Datatable.GetValue("Goal_Notes");
		ViewQualityDashboard.getGoalNotes(driver).sendKeys(goalnotes);
		ViewQualityDashboard.getGoalSave(driver).click();		
		ViewQualityDashboard.getSuccessScreenshot(driver, ViewQualityDashboard.getSuccessAlert(driver));
	}
	
	//@Method to Add new Custom Goal to the QAPI Goals
	public static void AddCustomGoal(WebDriver driver) throws IOException, InterruptedException, Exception{
		ViewQualityDashboard.getAddNewQAPIGoalElement(driver).click();
		String CustomGoal = Datatable.GetValue("Custom_Goal");
		ViewQualityDashboard.getCustomQAPIGoals(driver).sendKeys(CustomGoal);
		ViewQualityDashboard.getM1ProceedWithGoal(driver).click();
		String customgoalvalue = Datatable.GetValue("Custom_value");
		ViewQualityDashboard.getCustomGoal(driver).sendKeys(customgoalvalue);
		ViewQualityDashboard.getStartDateDatePicker(driver).click();
		ViewQualityDashboard.getStartDate(driver).click();
		ViewQualityDashboard.getEndDateDatePicker(driver).click();
		ViewQualityDashboard.getEndDate(driver).click();
		ViewQualityDashboard.getM2ProceedtoGoalNotes(driver).click();
		String goalnotes = Datatable.GetValue("Goal_Notes");
		ViewQualityDashboard.getGoalNotes(driver).sendKeys(goalnotes);
		ViewQualityDashboard.getGoalSave(driver).click();		
		ViewQualityDashboard.getSuccessScreenshot(driver, ViewQualityDashboard.getSuccessAlert(driver));
	}
	
	// @Method to select task inside Census report
	public static void SelectTask(WebDriver driver, String task) {
		boolean isTaskSelected = false;
		List<WebElement> census = driver.findElements(By.tagName("a"));
		for (WebElement cen : census) {
			String centxt = cen.getText();
			if (centxt.trim().equals(task)) {
				cen.click();
				break;
			}
		}
		if (isTaskSelected) {
			Report.Log(Status.PASS, "" + task
					+ " link is selected successfully");
		} else {
			Report.Log(Status.FAIL, "" + task
					+ " link is NOT selected successfully");
			Assert.fail();
		}
	}

	//@Method to select link inside Census report
	public static void clickLink(WebDriver driver, String linkname) {		
		boolean isLinkClicked = false;
		List<WebElement> census = driver.findElements(By.xpath("//*[contains(@href,'/am/reports/') and @class = 'hotbox']"));
		for(WebElement cen: census) {
			String centxt = cen.getText();
			if(centxt.trim().equals(linkname)) {
				cen.click();
				break;
			}
		}	
	if (isLinkClicked) {
        Report.Log(Status.PASS, "" + linkname + " link is selected successfully");
    } else {
        Report.Log(Status.FAIL, "" + linkname + " link is NOT selected successfully");
        Assert.fail();
    }
  }

}
