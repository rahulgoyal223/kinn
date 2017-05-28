package AM.Forms.Nursing;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Browser;
import components.Report;
import components.Waits;

public class OasisCheck {
	
	
	private static OasisCheckMessageType getMessageType (WebElement element){
		OasisCheckMessageType messageType = OasisCheckMessageType.INFO;
		
		if( element.findElements(By.xpath("//*[contains(@src, 'RedIcon')]")).size() > 0 ){
			messageType = OasisCheckMessageType.ERROR;
		} else if( element.findElements(By.xpath("//*[contains(@src, 'icon_error')]")).size() > 0 ){
			messageType = OasisCheckMessageType.WARNING;
		}
		
		return messageType;
	}

	public static void VerifyMessage(WebDriver driver, OasisCheckMessageType expectedType, String messageText) throws Exception {
		Waits.ForBrowserLoad(driver);

		List<WebElement> rows = driver.findElements(By.xpath("//*[contains(text(),'" + messageText + "')]/ancestor::*[1]"));
		
		if( rows.size() == 0 ){
			Report.Log(
				Status.FAIL, 
				"Oasis check does not contain the message '" + messageText + "'"
				);
			Report.attachScreenShotToReport(driver);
			Assert.fail("Oasis check does not contain the message '" + messageText + "'");
		} else {
			
			boolean pass = expectedType == getMessageType(rows.get(0));

			Report.Log(
				Status.PASS, 
				"Oasis check contains the message '" + messageText + "'"
				);
			
			Report.Log(
				pass ? Status.PASS : Status.ERROR, 
				"Oasis check message '" + messageText + "' type is <b>" + 
				getMessageType(rows.get(0)).toString() + " </b>"
					+ ( 
						!pass 
							? " expected message type <b>" + expectedType.toString() + "</b>." 
							: ""
					)
				);
			
			

			Report.attachScreenShotToReport(driver, rows.get(0));
		}
	}
	
	
	public static void VerifyMissingMessage(WebDriver driver, OasisCheckMessageType expectedType, String messageText) throws Exception {
		Waits.ForBrowserLoad(driver);

		List<WebElement> rows = driver.findElements(By.xpath("//*[contains(text(),'" + messageText + "')]"));
		
		if( rows.size() == 0 ){
			Report.Log(
				Status.PASS, 
				"Oasis check does not contain the message '" + messageText + "'"
				);
			Report.attachScreenShotToReport(driver);
		} else {
			
			boolean pass = expectedType == getMessageType(rows.get(0));

			Report.Log(
				Status.FAIL, 
				"Oasis check contains the message '" + messageText + "'"
				);
			Assert.fail("Oasis check contains the message '" + messageText + "'");			
			Report.Log(
				pass ? Status.PASS : Status.ERROR, 
				"Oasis check message '" + messageText + "' type is <b>" + 
				getMessageType(rows.get(0)).toString() + " </b>"
					+ ( 
						!pass 
							? " expected message type <b>" + expectedType.toString() + "</b>." 
							: ""
					)
				);
			Report.attachScreenShotToReport(driver, rows.get(0));
		}
	}	
}
