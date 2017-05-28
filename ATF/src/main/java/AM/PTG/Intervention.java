package AM.PTG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Intervention extends PTG {

	// Buttons
	public WebElement getBtnInterventions(WebDriver driver) {
		return getElement(driver, By.id("buttonIntervention"));
	}

	public WebElement getBtnNewIntervention(WebDriver driver) {
		return getElement(driver, By.name("newInterventionBtn"));
	}
	
	public WebElement getBtnSave(WebDriver driver) {
		return getElement(driver, By.name("saveBtn"));
	}
	
	public WebElement getCancelSave(WebDriver driver) {
		return getElement(driver, By.name("submitBtn"));
	}

	// Input Texts
	public WebElement getTxtInterventionName(WebDriver driver) {
		return getElement(driver, By.id("instructionName"));
	}
	
	
	public WebElement getTxtNameInterventionsFilter(WebDriver driver){
		return getElement(driver, By.id("nameGoalsFilter"));
	}
	
	public WebElement getIconEdit(WebDriver driver, int pos) {
		
		List<WebElement> elements = getElements(driver, By.className("fa-pencil"));
		
        return elements.get(pos-1);
	}
	
}
