package AM.PTG;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import AM.PTG.utils.ProblemUtil;
import components.Waits;

public class PTG {

	protected WebElement getElement(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	protected List<WebElement> getElements(WebDriver driver, By by) {
		List<WebElement> elements = driver.findElements(by);
		return elements;
	}

	
	//Buttons
	public WebElement getBtnMoreInstruction(WebDriver driver){
		return getElement(driver, By.className("fa-plus-circle"));
	}
	
	//Textboxes
	public WebElement getTxtInstruction(WebDriver driver, int pos) {
		return getElement(driver, By.id("instructionName" + Integer.valueOf(pos - 1)));
	}

	// Checkboxes
	public WebElement getChkProblem(WebDriver driver, String problem) {

		return ProblemUtil.getChkProblem(driver, problem);
	}

	public WebElement getChkEditable(WebDriver driver, int pos) {
		return getElement(driver, By.id("instructionEditable" + Integer.valueOf(pos - 1)));

	}

	public WebElement getLblPreview(WebDriver driver, int pos){
        return getElement(driver, By.id("instructionText"+(pos-1)));
	}
	
}
