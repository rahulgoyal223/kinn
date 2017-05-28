package AM.Forms.Nursing;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class WoundCareWorksheet {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list = null;

	// @Method To select the Form
	public static WebElement SelectForm(WebDriver driver, String formname)
			throws Exception {

		try {
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().contains(formname)) {
					row.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @Method To fill the Wound Care Worksheet form
	public static void fillWoundCareWorhsheet(WebDriver driver, int wound) throws Exception {
		strInputValue = Datatable.GetValue("Location");
		Waits.fluentWaitIsEnabled(driver, txt_Location(driver,wound), 10);
		if (!strInputValue.trim().isEmpty()) {
			txt_Location(driver, wound).clear();
			txt_Location(driver, wound).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("Status");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.trim().equals("Closed")) {
				rdBtn_Closed(driver, wound).click();
		}
		
		else {
			if (!strInputValue.trim().isEmpty()
					&& strInputValue.trim().equals("Open")) {
					rdBtn_Open(driver, wound).click();
			}
		}
		
		strInputValue = Datatable.GetValue("Type");
		if (!strInputValue.trim().isEmpty()) {
			lst_Type(driver, wound).selectByVisibleText(strInputValue);
		}		
		
		strInputValue = Datatable.GetValue("Present On Admission");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.trim().equals("Yes")) {
			chk_PresentAdmission(driver, wound).click();

		}

	}
	
	public static void AddWound(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_AddWound(driver));
		btn_AddWound(driver).click();
		Waits.fluentWaitIsEnabled(driver, txt_Location(driver,1), 10);
	}
	
	public static void SaveWound(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_SaveWound(driver));
		btn_SaveWound(driver).click();
		saveAlertWindow(driver);
	}
	
	public static void SaveAndContinue(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, btn_SaveContWound(driver));
		btn_SaveContWound(driver).click();
		saveAlertWindow(driver);
	}
	
	public static void saveAlertWindow(WebDriver driver)
			throws InterruptedException {
		Waits.fluentWaitIsDisplayed(driver, driver.findElement(By.id("saveWindowText")), 1);
		WebElement element = driver.findElement(By.id("saveWindowText"));
		Waits.ForBrowserLoad(driver);
		Waits.ForElementStaleness(driver, element);
	}
	
	
	// @ Wound Care test objects
	public static WebElement txt_Location(WebDriver driver, int wound) {
		element = driver.findElement(By.id("frm_wound"+wound+"Location"));
		return element;
	}
	
	public static WebElement rdBtn_Closed(WebDriver driver, int wound) {
		element = driver.findElement(By.xpath("//*[@id='frm_wound"+wound+"Status'][@value='0']"));
		return element;
	}
	
	public static WebElement rdBtn_Open(WebDriver driver, int wound) {
		element = driver.findElement(By.xpath("//*[@id='frm_wound"+wound+"Status'][@value='1']"));
		return element;
	}

	
	public static WebElement txt_OnsetDate(WebDriver driver, int wound) {
		element = driver.findElement(By.id("frm_wound"+wound+"OnsetDate"));
		return element;
	}
	
	public static WebElement chk_PresentAdmission(WebDriver driver, int wound) {
		element = driver.findElement(By.id("frm_wound"+wound+"PresentOnAdmission"));
		return element;
	}
	
	public static Select lst_Type(WebDriver driver, int wound) {
		list = new Select(driver.findElement(By.id("frm_wound"+wound+"Type")));
		return list;
	}
	
	public static WebElement btn_AddWound(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@class='btn add-wounds']"));
		return element;
	}
	
	public static WebElement btn_SaveWound(WebDriver driver){
		List<WebElement> elements = driver.findElements(By.xpath("//button[contains(text(),'Save Wound')]"));
		for(WebElement item : elements){
			if(item.isDisplayed()){
				return item;
			}
		}
		return elements.get(0);
	}
	
	public static WebElement btn_SaveContWound(WebDriver driver){
		element = driver.findElement(By.id("woundCareButton"));
		return element;
	}
	
	public static void VerifyPrefilledWounds(WebDriver driver, int numberOfWounds) throws Exception {
		List<String> expectedWounds = getExpectedWoundsLocation( numberOfWounds);
		System.out.println( "Expected:" + expectedWounds.toString() );
		List<WebElement> wounds = driver.findElements(By.xpath("//*[contains(@id,'woundTitleLocation')]"));

		for (WebElement wound : wounds) {
			System.out.println( "Found:" + wound.getText() );
		}
		
		if( expectedWounds.size() != wounds.toArray().length ){
			Report.Log(
					Status.FAIL, 
					"Following Wounds " + String.valueOf(expectedWounds.toArray()) + " are expected " + 
					"But " + String.valueOf(wounds.toArray()) + " wounds were found"
					);
		} else {
			for( Iterator<WebElement> i = wounds.iterator(); i.hasNext();){
				WebElement row = i.next();
				int position = wounds.indexOf(row);
				boolean pass = row.getText().trim().equals(expectedWounds.get(position).trim());
						
				Report.Log(
						pass ? Status.PASS : Status.ERROR, 
						"Prefilled wound " + String.valueOf(position+1) 
							+ " is <pre>" + row.getText() + "</pre> " 
							+ ( 
								!pass 
									? " expected was <pre>" + expectedWounds.get(position) + "</pre>." 
									: ""
							)
						);
			}
		}
		Report.attachScreenShotToReport(driver);
	
	}

	private static List<String> getExpectedWoundsLocation(int numberOfWounds) throws Exception {
		List<String> list = new ArrayList<String>();
		try { 
			for(int i = 1; i <= numberOfWounds; i++){
				Datatable.setCurrentRow(i);
				String woundLocation = Datatable.GetValue("Location");
				list.add(woundLocation);
			}
		} catch( Exception e){
			e.printStackTrace();
		}
		return list;
	}


	
	
}
