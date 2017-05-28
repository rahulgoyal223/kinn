package AM.PTG;


import java.util.List;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class Library {
	private static WebElement element = null;
	private static List<WebElement> list = null;
	
	public static String returnRowText (WebDriver driver, int row){
		list = driver.findElements(By.className("div-width-57 ng-binding"));
		element = list.get(0);
		String goalName = element.getText();
        return goalName;
	}
	
	public static void clickChkActive (WebDriver driver, String search){
		try{
			getTxtSearch(driver).sendKeys(search);
			Waits.ForBrowserLoad(driver);
			element = driver.findElement(By.id("goalStatus0"));
	        element.click();
		}
		catch(Exception e){
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}

	public static WebElement getChkActive (WebElement driver, int row){
		row = row - 1;
		String id = "goalStatus" + String.valueOf(row);  
		element = driver.findElement(By.id(id));
        return element;
	}
	
	public static WebElement getIconEdit (WebDriver driver, String search){
		WebElement editIcon = null;
		try{
			getTxtSearch(driver).sendKeys(search);
			Waits.ForBrowserLoad(driver);
			editIcon = driver.findElement(By.className("fa fa-pencil text-indent-L-23"));
		}
		catch(Exception e){
			Report.Log(Status.FAIL, e.getMessage());
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
        return editIcon;
	}
	
	public static WebElement getBtnInterventionTab (WebDriver driver){
		element = driver.findElement(By.id("buttonIntervention"));
        return element;
	}
	
	public static WebElement getBtnGoalTab (WebDriver driver){
		element = driver.findElement(By.id("buttonGoals"));
        return element;
	}
	
	public static WebElement getBtnNewGoal (WebDriver driver){
		element = driver.findElement(By.id("newGoalBtn"));
        return element;
	}
	
	public static WebElement getBtnNewIntervention (WebDriver driver){
		element = driver.findElement(By.id(""));
        return element;
	}
	
	public static WebElement getSelProblems (WebDriver driver){
		element = driver.findElement(By.id("selectProblems"));
        return element;
	}
	
	public static WebElement getChkInactiveGoals (WebDriver driver){
		element = driver.findElement(By.id("actionFilter"));
        return element;
	}
	
	public static WebElement getTxtSearch (WebDriver driver){
		element = driver.findElement(By.id("nameGoalsFilter"));
        return element;
	}
}
