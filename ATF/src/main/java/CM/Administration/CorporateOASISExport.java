package CM.Administration;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;
import DataSource.Datatable;

public class CorporateOASISExport {
	private static String strInputValue;
	private static WebElement element = null;
	
	// @Method To fill the Demographics form
	public static void FillFilters(WebDriver driver) throws Exception {
		
		strInputValue = Datatable.GetValue("CM_OE_dateFrom");
		if (!strInputValue.trim().isEmpty()) {
			getDateFrom(driver).clear();
			getDateFrom(driver).sendKeys(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("CM_OE_dateTo");
		if (!strInputValue.trim().isEmpty()) {
			getDateTo(driver).clear();
			getDateTo(driver).sendKeys(strInputValue);
		}
		
		if (Datatable.GetValue("CM_OE_clinicSelectAll").equals("Yes")) {
			getClinicSelectAll(driver).click();
		}
		
		if (Datatable.GetValue("CM_OE_branchSelectAll").equals("Yes")) {
			getBranchSelectAll(driver).click();
		}
		
		if (Datatable.GetValue("CM_OE_insuranceSelectAll").equals("Yes")) {
			getInsuranceSelectAll(driver).click();
		}
		
		if (Datatable.GetValue("CM_OE_statusSelectAll").equals("Yes")) {
			getStatusSelectAll(driver).click();
		}
	}
	
	// @ Objects
	public static WebElement getDateFrom(WebDriver driver) {
		element = driver.findElement(By.id("filter_Date_Range_startDate"));
		return element;
	}
	
	public static WebElement getDateTo(WebDriver driver) {
		element = driver.findElement(By.id("filter_Date_Range_endDate"));
		return element;
	}

	public static WebElement getClinicSelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_Clinic_btn_select_all"));
		return element;
	}

	public static WebElement getBranchSelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_Branch_btn_select_all"));
		return element;
	}

	public static WebElement getInsuranceSelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_Insurance_btn_select_all"));
		return element;
	}

	public static WebElement getStatusSelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_OASIS_Status_btn_select_all"));
		return element;
	}
	
	public static WebElement getBtnApplyFilter(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='grid-filter-panel']/div[2]/div/button[1]"));
		return element;
	}
	
	public static void VerifyReportRow(WebDriver driver, String patientName, String taskName) throws InterruptedException {
		element = driver.findElement(By.xpath("//*[contains(@id,'angularGrid|')]/div[5]/table"));
		//Waits.ForElementVisibility(driver, element);
		
		List<WebElement> rows = element.findElements(By.xpath("//*[contains(text(), '"+ patientName + "')]/ancestor::tr[1]//*[contains(text(), '"+ taskName + "')]"));
		
		if(rows.size() > 0){
			Report.Log(Status.PASS, "Row with for patient " + patientName + " with taskname " + taskName + " found");
		} else {
			Report.Log(Status.FAIL, "There is no row in report");
			Assert.fail("There is no row in report");
		}
		
	}
}
