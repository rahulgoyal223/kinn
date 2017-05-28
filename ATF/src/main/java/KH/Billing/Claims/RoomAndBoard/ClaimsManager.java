package KH.Billing.Claims.RoomAndBoard;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ClaimsManager {

	private static WebElement element;
	private static Select list;

	public static void SelectPatient(WebDriver driver, String patient) throws Exception {

		try {
			Waits.ForGlobalAjaxLoader(driver); 
			WebElement table = driver.findElement(By.tagName("table"));
			List<WebElement> allrows = table.findElements(By.tagName("tr"));
			for (WebElement row : allrows) {
				System.out.println(row.getText());
				if (row.getText().contains(patient)) {
					element = row.findElement(By
							.xpath(".//input[@type = 'checkbox']"));
					element.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}

	public static void VerifyReadyAlertMessage(WebDriver driver,String message) throws Exception {
		String getMsg;
		try {
			Waits.ForGlobalAjaxLoader(driver); 
			getMsg = Alt_R_SuccessfulMessage(driver).getText();
			if (getMsg.trim().equals(message.trim())) {
				Report.Log(Status.PASS, "Alert " + message + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + message + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + message + " is NOT displayed");
		}
	}

	public static void SelectStatusTab(WebDriver driver, String tabName) throws Exception {
		try {

			Waits.ForGlobalAjaxLoader(driver); 
			boolean isStatusTabSelected = false;

			List<WebElement> allLinks = driver.findElements(By.tagName("a"));
			for (WebElement link : allLinks) {
				String linkText = link.getText();
				if (linkText.trim().equals(tabName)) {
					link.click();
					isStatusTabSelected = true;
					break;
				}
			}

			Waits.ForGlobalAjaxLoader(driver);
			if (isStatusTabSelected) {
				Report.Log(Status.PASS, "Tab - '" + tabName + "' is selected");
			} else {
				Report.Log(Status.FAIL, "Tab - '" + tabName	+ "' is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}

	
    //@objects under Not Ready page

	
    //@objects under Ready page

	public static WebElement Alt_R_SuccessfulMessage(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//*[(contains(@ng-bind-html-unsafe,'alert.message') and contains(@class,'ng-binding'))]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_R_ClaimAge(WebDriver driver) {
        element = driver.findElement(By.id("dayRange"));
        list = new Select(element);
        return list;
    }

    public static WebElement txt_R_Searchbox(WebDriver driver) {
        element = driver.findElement(By.name("gridQuery"));
        return element;
    }

    public static WebElement btn_R_CreateClaim(WebDriver driver) {
        element = driver.findElement(By.id("claimsCreation"));
        return element;
    }

    //@objects under Pending Approval page

    public static WebElement txt_PA_SearchBox(WebDriver driver) throws InterruptedException {
    	element = driver.findElement(By.name("gridQuery"));     
    	return element;
    }

    public static WebElement btn_PA_PrintView(WebDriver driver) {
    	element = driver.findElement(By.className("icon-print"));
    	return element;
    }

    
    //@objects under Ready to Send page

    //@objects under Pending Payment page

    //@objects under Paid page
    
    //@objects under Rejected Cancelled page
    

}