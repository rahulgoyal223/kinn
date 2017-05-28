package AM.KMail;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class KMailManager {

	static String strInputValue;
	static String strGlobalInputValue;
	private static WebElement element = null;
	
	//@Method to select an action

	//Method Get the reference to desired row in inbox based on subject
	public static WebElement getMailBySubject(WebDriver driver, String subject){
		WebElement table = getEmailTable(driver);
		List<WebElement> rows = getRows(table);
		for(WebElement row: rows){
			List<WebElement> cols = getColumns(row);
			for(WebElement td:cols){
				if(td.getText().trim().contains(subject)){
					return row;
				}
			}
		}
		return null;
	}
	
	//Method to select the check-box based on subject
	public static void SelectMailBySubject(WebDriver driver, String subject){
		element = getMailBySubject(driver, subject);
		List<WebElement> cols = getColumns(element);
		cols.get(0).click();
	}
	
	//Method to Delete Mail by Subject
	public static void DeleteMailBySubject(WebDriver driver, String subject){
		element = getMailBySubject(driver, subject);
		List<WebElement> cols = getColumns(element);
		cols.get(5).click();
	}
	
		
	//Method to verify if the deleted mail exist in Deleted Email page
	public static void verifyDeletedEmail(WebDriver driver, String subject){
		element = getMailBySubject(driver, subject);
		List<WebElement> cols = getColumns(element);
		Assert.assertTrue(cols.get(2).getText().trim().equals(subject.trim()), "The Deleted email is not in the list");;
	}
	
	//@KMail Manager Test Objects
	//WebElement Reference to Inbox Table
	public static WebElement getEmailTable(WebDriver driver){
		element = driver.findElement(By.xpath(".//*[@id='MessageForm']/table[2]"));
		return element;	
	}
	
	public static List<WebElement> getRows(WebElement table){		
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows;	
	}
	
	public static List<WebElement> getColumns(WebElement row){
		List<WebElement> columns = row.findElements(By.tagName("td"));
		return columns;
	}
	
	

}
