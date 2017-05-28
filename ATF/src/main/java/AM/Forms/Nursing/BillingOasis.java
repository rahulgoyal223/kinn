package AM.Forms.Nursing;

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
import org.testng.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class BillingOasis {

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

	// @Method To fill the Demographics form
	public static void FillDemographicSection(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("OA_cTO_timein");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeIn(driver).clear();
			txt_DG_TimeIn(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_timeout");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeOut(driver).clear();
			txt_DG_TimeOut(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_visitdate");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_VisitDate(driver).clear();
			txt_DG_VisitDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_dt_DG_M0090INFOCOMPLETEDDT");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_M0090INFOCOMPLETEDDT(driver).clear();
			txt_DG_M0090INFOCOMPLETEDDT(driver).sendKeys(strInputValue);
		}
	}	
	//@Method to fill 	ICD-10 Diagnosis Codes //
	public static void FillPrimaryDiagnosis(WebDriver driver) throws Exception {
		
		strInputValue = Datatable.GetValue("BO_PrimaryDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			txt_PrimaryDiag(driver).clear();
			txt_PrimaryDiag(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='I110']")).click();
		} 
	}
	
	public static void FillAdditionalDiagnosis(WebDriver driver) throws Exception {
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis1");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag1(driver).clear();
			txt_AdditionalDiag1(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='A0102']")).click();			
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis2");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag2(driver).clear();
			txt_AdditionalDiag2(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='A0223']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis3");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag3(driver).clear();
			txt_AdditionalDiag3(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='G4761']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis4");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag4(driver).clear();
			txt_AdditionalDiag4(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='H52511']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis5");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag5(driver).clear();
			txt_AdditionalDiag5(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='N501']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis6");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag6(driver).clear();
			txt_AdditionalDiag6(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='S12120A']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis7");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag7(driver).clear();
			txt_AdditionalDiag7(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='L512']")).click();
		}		
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis8");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag8(driver).clear();
			txt_AdditionalDiag8(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='T83728A']")).click();
		}
		
		strInputValue = Datatable.GetValue("BO_AdditionalDiagnosis9");
		if (!strInputValue.trim().isEmpty()) {
			txt_AdditionalDiag9(driver).clear();
			txt_AdditionalDiag9(driver).sendKeys(strInputValue);
			driver.findElement(By.xpath("//span[@class='ks-search-string-highlight' and text()='Y712']")).click();
		}
	}	
	
	//
	public static void saveAlertWindow(WebDriver driver)
			throws InterruptedException {
		WebElement element = driver.findElement(By.id("saveWindowText"));
		Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForBrowserLoad(driver);
		for (int i = 0; i <= 40; i++) {
			if (element.isDisplayed()
					&& element.getText().trim().equals("Page has been saved!")) {
				driver.navigate().refresh();
				break;
			}
		}
		driver.navigate().refresh();
	}
	// @ Demographics Section
	public static WebElement txt_DG_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("timein"));
		return element;
	}

	public static WebElement txt_DG_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("timeout"));
		return element;
	}

	public static WebElement txt_DG_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("VisitDate"));
		return element;
	}	

	public static WebElement txt_DG_M0090INFOCOMPLETEDDT(WebDriver driver) {
		element = driver.findElement(By.id("M0090"));
		return element;
	}
	
	//@ ICD-10 Diagnosis Codes	//
	public static WebElement txt_PrimaryDiag(WebDriver driver) {
		element = driver.findElement(By.name("icd0_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag1(WebDriver driver) {
		element = driver.findElement(By.name("icd1_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag2(WebDriver driver) {
		element = driver.findElement(By.name("icd2_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag3(WebDriver driver) {
		element = driver.findElement(By.name("icd3_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag4(WebDriver driver) {
		element = driver.findElement(By.name("icd4_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag5(WebDriver driver) {
		element = driver.findElement(By.name("icd5_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag6(WebDriver driver) {
		element = driver.findElement(By.name("icd6_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag7(WebDriver driver) {
		element = driver.findElement(By.name("icd7_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag8(WebDriver driver) {
		element = driver.findElement(By.name("icd8_10"));
		return element;
	}
	public static WebElement txt_AdditionalDiag9(WebDriver driver) {
		element = driver.findElement(By.name("icd9_10"));
		return element;
	}
	
	//@ ICD-10 Diagnosis Codes - Hidden
		
	public static WebElement verifyDiagnosis(WebDriver driver, int i) {
		element = driver.findElement(By.name("icd" + i + "_10_Code"));
		return element;
	}	
		
	// For Oasis Form approval
	public static WebElement txt_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement Save(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Save']"));
		return element;
	}

	// To verify that the correct value was selected
	public static void  CheckValue(WebDriver driver, String code, int index) {
		String expectedText = code;

		try {
	        Assert.assertEquals( AM.Forms.Nursing.BillingOasis.verifyDiagnosis(driver, index).getAttribute("value"), expectedText );
	        Report.Log(Status.PASS, "Primary Diagnosis has the correct value: " + expectedText );
	    	Report.attachScreenShotToReport(driver);
	    } catch(AssertionError ae){
	    	Report.Log(Status.FAIL, "Primary Diagnosis has not the correct value. The value should be: " + expectedText + " . Error: " + ae.getMessage());
	    	Report.attachScreenShotToReport(driver);
	    	throw ae;
	    }
	}
}
