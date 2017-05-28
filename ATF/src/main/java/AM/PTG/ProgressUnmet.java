package AM.PTG;


import java.util.List;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProgressUnmet {
	
	private static WebElement element = null;
	private static List<WebElement> elementList = null;
	
	//Reusable functionality
	public static void selectFirstGoal(WebDriver driver){
		WebElement btnNewGoal = getBtnNewGoal(driver);
		Waits.ForElementToBeClickable(driver, btnNewGoal);
		btnNewGoal.click();
		Waits.ForElementToBeClickable(driver, getBtnContinueModal(driver));
		List<WebElement> selection = listGoals(driver);
		selection.get(0).click();
		Report.attachScreenShotToReport(driver);
		getBtnContinueModal(driver).click();
	}
	
	public static void selectFreeTextGoal(WebDriver driver){
		
		WebElement btnNewGoal = getBtnNewGoal(driver);
		Waits.ForElementToBeClickable(driver, btnNewGoal);
		btnNewGoal.click();
		WebElement btnFreeText = AM.PTG.ProgressUnmet.getBtnFreeText(driver);
		Report.attachScreenShotToReport(driver);
		Waits.ForElementToBeClickable(driver, btnFreeText);
		btnFreeText.click();
	}
	
	//get Elements section 
	
	//Page section
	public static WebElement getBtnNewGoal(WebDriver driver){
		element = driver.findElement(By.id("openModalGoal"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getBtnSaveAndReturn(WebDriver driver){
		element = driver.findElement(By.id("saveBtn"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	public static WebElement getBtnCancelProgress(WebDriver driver){
		element = driver.findElement(By.id("submitBtn"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	//Assign Goal section
	public static WebElement getTxtName(WebDriver driver, int row){
		row = row - 1;
		String id = "goalFreeTextName".concat(Integer.toString(row));
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getDtpTargetDate (WebDriver driver, int row){
		row = row - 1;
		String id = "targetDate" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getRadLongTerm (WebDriver driver, int row){
		row = row - 1;
		String id = "longTime" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getChkByEndEpisode (WebDriver driver, int row){
		row = row - 1;
		String id = "byEndEpisode" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
		Waits.ForElementToBeClickable(driver, element);
        return element;
	}
	
	public static WebElement getTxtInstructions (WebDriver driver, int row){
		row = row - 1;
		List<WebElement> elements = driver.findElements(By.id("instructionDetail"));
		element  = elements.get(row);
		Waits.ForElementToBeClickable(driver, element);
        return element;
	}
	
	public static WebElement getRadShortTerm (WebDriver driver, int row){
		row = row - 1;
		String id = "shortTime" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getBtnAssignGoal (WebDriver driver, int row){
		row = row - 1;
		String id = "assignBtn" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getBtnCancelAssignGoal(WebDriver driver, int row){
		row = row - 1;
		String id = "cancelBtn" + String.valueOf(row); 
		element = driver.findElement(By.id(id));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	//Modal GOAL
	public static WebElement getSelProblem(WebDriver driver){
		element = driver.findElement(By.id("inputProblems"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	public static WebElement getTxtSearch(WebDriver driver){
		element = driver.findElement(By.className("span6 goals-search ng-pristine ng-valid"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	public static WebElement getBtnFreeText(WebDriver driver){
		element = driver.findElement(By.id("addFreeTextGoal"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getBtnCancelModal(WebDriver driver){
		element = driver.findElement(By.className("btn grey-btn"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement getBtnContinueModal(WebDriver driver){
		element = driver.findElement(By.cssSelector("#newAdmissionContainer > div > div.modal-footer.modal-footer-goals > button.btn.btn-blue"));
        Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static List<WebElement> listGoals(WebDriver driver){
		elementList = driver.findElements(By.id("goalSelect"));
        return elementList;
	}
	
	public static WebElement getAlert (WebDriver driver){
		element = driver.findElement(By.id("alert"));
        return element;
	}
	
	
}
