package AM.Forms.Orders;

import com.aventstack.extentreports.Status;

import AM.Episode.EpisodeManager;
import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CMS485 {

	static String strInputValue;
	private static WebElement element = null;

	// Added on 10-Nov-2016
	// @Method for CMS485 form Submit
	public static void FillCMS485Form(WebDriver driver) throws Exception {
		try {
			Verify.ExactPageHeader(driver, "CMS 485");
			strInputValue = Datatable.GetValue("signatureRequired");
			if (!strInputValue.trim().isEmpty() && strInputValue.equals("Yes")) {
				chk_ReturnToClinician(driver).click();
			}
			strInputValue = Datatable.GetValue("signDate");
			if (!strInputValue.trim().isEmpty()) {
				dt_SignatureDate(driver).clear();
				Events.Fire(driver).moveToElement(dt_SignatureDate(driver))
						.click().build().perform();
				dt_SignatureDate(driver).sendKeys(strInputValue);
			}
			strInputValue = Datatable.GetValue("clinicianSignature");
			if (!strInputValue.trim().isEmpty()) {
				txt_ElectronicSignature(driver).sendKeys(strInputValue);
			}

			Report.Log(Status.PASS, "CMS485 form has been filled successfully");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "CMS485 form has not been filled");
			e.printStackTrace();
		}
	}

	public static void SubmitCMS485(WebDriver driver) {
		try {
			Waits.fluentWaitIsEnabled(driver, btn_Submit(driver), 1);
			btn_Submit(driver).click();
			Waits.ForHomePage(driver, "Hotbox");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "CMS 485 was NOT submitted successfully");
			e.printStackTrace();
		}
	}
	
	public static void ApproveCMS485(WebDriver driver) {
		try {
			Waits.fluentWaitIsEnabled(driver, btn_Approve(driver), 1);
			btn_Approve(driver).click();
			Waits.ForHomePage(driver, "Hotbox");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "CMS 485 was NOT approved");
			e.printStackTrace();
		}
	}
	// Added on 10-Nov-2016
	// @Method for CM485 for approve
	public static void CMS485_ElectronicSignature(WebDriver driver)
			throws Exception {
		try {
			strInputValue = Datatable.GetValue("taskname");
			if (!strInputValue.trim().isEmpty()) {
				AM.Episode.EpisodeManager.SelectTaskTab(driver, strInputValue);
			}

			strInputValue = Datatable.GetValue("ReturnToClinician");
			if (!strInputValue.trim().isEmpty()) {
				chk_ReturnToClinician(driver).click();
			}
			strInputValue = Datatable.GetValue("clinicianSignature");
			if (!strInputValue.trim().isEmpty()) {
				txt_ElectronicSignature(driver).sendKeys(strInputValue);
			}
			strInputValue = Datatable.GetValue("signDate");
			if (!strInputValue.trim().isEmpty()) {
				dt_SignatureDate(driver).sendKeys(strInputValue);
			}

			/*
			 * strInputValue = Datatable.GetValue("whattodo"); if
			 * (!strInputValue.trim().isEmpty()) { btn_Approve(driver).click();
			 * }
			 */
			Report.Log(Status.PASS,
					"CMS485 form has been APPROVED successfully");
		} catch (Exception e) {
			Report.Log(Status.PASS, "CMS485 form has NOT been APPROVED");
			e.printStackTrace();
		}
	}

	// @Objects for CMS485
	public static WebElement chk_ReturnToClinician(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
	}

	public static WebElement txt_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}
	
	public static WebElement printBoxNumber(WebDriver driver, int boxNumber) {
		element = driver.findElement(By.xpath("//*[starts-with(text(), '" + String.valueOf(boxNumber) + ".')]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_SignatureDate(WebDriver driver) {
		// *[@id="dp1479730062580"]
		element = driver.findElement(By.name("signDate"));
		return element;
	}

	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By.name("whattodo"));
		return element;
	}

	public static WebElement btn_Approve(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Approve']"));
		return element;
	}

}
