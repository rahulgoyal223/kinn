package KH.NewClinic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CallTrackHome {

	private static WebElement element = null;

	// @Call Track Home Screen Objects
	public static WebElement link_AgencyList(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='navtbl']/tbody/tr[2]/td[2]/a[1]"));
		return element;
	}
	
	public static WebElement link_CreateAgency(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='navtbl']/tbody/tr[2]/td[2]/a[3]"));
		return element;
	}
	
	public static WebElement link_Logout(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='navtbl']/tbody/tr[1]/td[3]/a[2]"));
		return element;
	}
	
}