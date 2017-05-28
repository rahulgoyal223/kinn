package AM.PTG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Goal extends PTG {
	
	
	//Buttons
	public WebElement getBtnGoals(WebDriver driver){
		return getElement(driver, By.id("buttonGoals"));
	}
	
	public WebElement getBtnNewGoal(WebDriver driver){
		return getElement(driver, By.id("newGoalBtn"));
	}
	
	public WebElement getBtnOpenModalGoal(WebDriver driver){
		return getElement(driver, By.id("openModalGoal"));
	}
	
	public WebElement getBtnSave (WebDriver driver){
		return getElement(driver, By.id("saveBtn"));
	}
	
	public WebElement getBtnCancel (WebDriver driver){
		return getElement(driver, By.id("submitBtn"));
	}
	
	public WebElement getBtnContinueModal (WebDriver driver){
		return getElement(driver, By.xpath("//button[@class='btn btn-blue' and contains(@ng-click,'addInterventions()')]"));
	}
	
	public WebElement getBtnCancelModal (WebDriver driver){
		return getElement(driver, By.className("grey-btn"));
	}
	
	
	//Input Texts
	public WebElement getTxtGoalName(WebDriver driver){
		return getElement(driver, By.id("goalName"));
	}
	
	public WebElement getTxtNameGoalsFilter(WebDriver driver){
		return getElement(driver, By.id("nameGoalsFilter"));
	}
	
	
	
	//Radios
	public WebElement getRadDiscipline (WebDriver driver, String discipline){
		
		WebElement element = null;
		switch (discipline){
		case "SN": element = getElement(driver, By.id("discipline1"));
					break;
		case "PT": element = getElement(driver, By.id("discipline2"));
					break;
		case "OT": element = getElement(driver, By.id("discipline3"));
					break;
		case "ST": element = getElement(driver, By.id("discipline4"));
					break;
		}
        return element;
	}
	
	//Checkboxes
	public List<WebElement> getInterventionsFromDialog(WebDriver driver) {
		return getElements(driver, By.id("interventionSelect"));
	}
	
	public WebElement getInterventionFromDialog(WebDriver driver, int pos) {
		return getElements(driver, By.id("interventionSelect")).get(pos-1);
	}
	
	public WebElement getIconEdit(WebDriver driver, int pos) {
		
		List<WebElement> elements = getElements(driver, By.className("fa-pencil"));
		
        return elements.get(pos-1);
	}
	
}
