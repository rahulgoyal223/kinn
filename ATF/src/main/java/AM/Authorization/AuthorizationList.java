package AM.Authorization;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AuthorizationList {
	
	private static WebElement element = null;
	private static Select list = null;
	String StrInputvalue = null;
	
	//Method to get reference of an authorization row
	public static WebElement getRowReferenceByAuthNo(WebDriver driver, String authNo) throws Exception{
		WebElement table = getAuthorizationTable(driver);
		List<WebElement> rows = getAuthorizationRows(table);
		for(WebElement row: rows){
			List<WebElement> cols = getAuthorizationColumn(row);
			if(cols.get(0).getText().trim().equals(authNo)){
				return row;
			}
		}
		return null;
	}
	
	//Method to get authorization status by the authorization number
	public static String getAuthorizationStatus(WebDriver driver, String authNo) throws Exception{
		element = getRowReferenceByAuthNo(driver, authNo);
		List<WebElement> cols = element.findElements(By.tagName("td"));
		String authStatus = cols.get(4).getText();
		return authStatus;	
	}
	
	//Method to delete authorization by the authorization number
	public static void deleteAuthorization(WebDriver driver, String authNo) throws Exception{
		element = getRowReferenceByAuthNo(driver, authNo);
		List<WebElement> cols = element.findElements(By.tagName("td"));
		cols.get(5).findElement(By.linkText("Delete")).click();
	}
	
	//Method to click on edit authorization by the authorization number
	public static void editAuthorization(WebDriver driver, String authNo) throws Exception {
		element = getRowReferenceByAuthNo(driver, authNo);
		List<WebElement> cols = element.findElements(By.tagName("td"));
		cols.get(6).findElement(By.linkText("Edit")).click();
	}
	
	//@Authorization List Test Objects
	public static WebElement getAuthorizationTable(WebDriver driver){
		element = driver.findElement(By.xpath("//div[@class='sectionContentTableTop']/table[2]"));
		return element;	
	}
	
	public static List<WebElement> getAuthorizationRows(WebElement table){
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows;	
	}
	
	public static List<WebElement> getAuthorizationColumn(WebElement row){
		List<WebElement> cols = row.findElements(By.tagName("td"));
		return cols;
	}
	
	public static WebElement txt_AuthListHeader(WebDriver driver) {
		element = driver.findElement(By.xpath(".//h3"));
		return element;
	}
}
