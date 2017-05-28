package AM.PTG.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProblemUtil {

	public static WebElement getChkProblem (WebDriver driver, String problem){
		
		WebElement element = null;
		switch (problem){
			case "Supportive Assistance": 
				element = driver.findElements(By.id("problem0")).get(0);
			break;
			case "Sensory Status": 
				element = driver.findElements(By.id("problem1")).get(0);
			break;
			case "Pain": 
				element = driver.findElements(By.id("problem2")).get(0);
			break;
			case "Integumentary": 
				element = driver.findElements(By.id("problem0")).get(1);
			break;
			case "Respiratory": 
				element = driver.findElements(By.id("problem1")).get(1);
			break;
			case "Endocrine": 
				element = driver.findElements(By.id("problem2")).get(1);
			break;
			case "Cardiac": 
				element = driver.findElements(By.id("problem0")).get(2);
			break;
			case "Elimination": 
				element = driver.findElements(By.id("problem1")).get(2);
			break;
			case "Nutrition": 
				element = driver.findElements(By.id("problem3")).get(2);
			break;
			case "Neuro": 
				element = driver.findElements(By.id("problem0")).get(3);
			break;
			case "ADL": 
				element = driver.findElements(By.id("problem1")).get(3);
			break;
			case "Medications": 
				element = driver.findElements(By.id("problem2")).get(3);
			break;
		}
        return element;
	}
	
}
