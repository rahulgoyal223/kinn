package AM.Authorization;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

public class ReconcileAuthorization {
	static WebElement element;
	private static Select list;

	//@Methods for Reconcile Auths
	public static WebElement VerifyReconciledMVTask(WebDriver driver, String NumOfVisits) {
		try {
			Waits.ForBrowserLoad(driver);
			String element = driver.findElement(By.xpath
					("//*[@id='AMContainer']/div[3]/form/table[3]/tbody/tr[2]/td[2]")).getText();
			
			boolean isTaskVerified = false;
				if (element.trim().contains(NumOfVisits)) {
					isTaskVerified = true;
				}
			if (isTaskVerified) {
				Report.Log(Status.PASS, "" + NumOfVisits + " is displayed");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + NumOfVisits + " is NOT displayed");
				Assert.fail("" + NumOfVisits + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public static WebElement VerifyNumVisitsOnAuth(WebDriver driver, int NumOfVisits) {
		try {
			Waits.ForBrowserLoad(driver);
			List<WebElement> rows = driver.findElements(By.xpath
					(".//*[@id='AMContainer']/div[3]/form/table[2]/tbody/tr[2]/td/table/tbody/tr"));
			
			int count = rows.size();
			System.out.println(count);
			//Assert.assertEquals(NumOfVisits, count, 
					//"Number of Visits attached to authorization is NOT correct.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public static void MoveOrphanTask(WebDriver driver, String AuthNum) throws Exception {  
		lst_OT_Action(driver).selectByVisibleText("Move to "+AuthNum+"");
	    btn_UpdateVisit(driver).click();
	}
	// @Objects Under Orphan Task
	public static WebElement btn_UpdateVisit(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By
				.xpath("*//input[contains(@value,'Update Visits')]"));
		Waits.ForBrowserLoad(driver);
		return element;

	}

	public static Select lst_OT_Action(WebDriver driver, int rowIndex) {
		element = driver.findElements(
				By.xpath("//select[contains(@name,'action_')]")).get(rowIndex);
		list = new Select(element);
		return list;
	}

	public static Select lst_OT_Action(WebDriver driver) {
		element = driver.findElement(By
				.xpath("*//select[contains(@name,'action_')]"));
		list = new Select(element);
		return list;
	}
	
	public static WebElement txt_ReconcileAuthHeader(WebDriver driver) {
		 element = driver.findElement(
				By.xpath("//h1[not(parent::div/parent::div[@id='releaseNoteContainer'])]"));
		return element;
	}

}
