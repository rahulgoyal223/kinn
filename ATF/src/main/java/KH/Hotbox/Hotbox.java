package KH.Hotbox;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.List;

public class Hotbox {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list = null;
	private static WebDriver driver;

	// @ Demographics Section
	public static WebElement hdr_Hotbox(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@class='left']//h1[.='Hotbox']"));
		return element;
	}
	
	public static WebElement txt_Search_filter(WebDriver driver) {
		element = driver.findElement(By.id("dataTableFilter"));
		return element;
	}

}