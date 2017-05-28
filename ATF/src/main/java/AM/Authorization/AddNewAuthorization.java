package AM.Authorization;

import components.Events;
import components.Report;
import components.TimeDate;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;

public class AddNewAuthorization {

	private static WebElement element = null;
	private static Select list = null;
	String StrInputvalue = null;

	public static void AddEditAuthorization(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, dt_D_StartDate(driver));
		String StrInputvalue = Datatable.GetValue("AE_Startdate");
		if (!StrInputvalue.trim().isEmpty()) {
			dt_D_StartDate(driver).clear();
			Events.Fire(driver).moveToElement(dt_D_StartDate(driver)).click()
					.perform();
			dt_D_StartDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("AE_Enddate");
		if (!StrInputvalue.trim().isEmpty()) {
			dt_D_EndDate(driver).clear();
			Events.Fire(driver).moveToElement(dt_D_EndDate(driver)).click()
					.perform();
			dt_D_EndDate(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("AE_Status");
		if (!StrInputvalue.trim().isEmpty()) {
			lst_D_Status(driver).selectByVisibleText(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("AE_Authorization");
		if (StrInputvalue.trim().toLowerCase().equals("dynamicvalue")) {
			StrInputvalue = TimeDate.getUniqueInteger();
			txt_D_AuthorizationNumber(driver).clear();
			txt_D_AuthorizationNumber(driver).sendKeys(StrInputvalue);
			GlobalData.setAuthorizationNumber(StrInputvalue);
		} else {
			txt_D_AuthorizationNumber(driver).clear();
			txt_D_AuthorizationNumber(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("VH_SNVisitCount");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_VHA_SNVisitCount(driver).clear();
			txt_VHA_SNVisitCount(driver).sendKeys(StrInputvalue);
		}

		StrInputvalue = Datatable.GetValue("VH_PTVisitCount");
		if (!StrInputvalue.trim().isEmpty()) {
			txt_VHA_PTVisitCount(driver).clear();
			txt_VHA_PTVisitCount(driver).sendKeys(StrInputvalue);
		}

	}
	
	public static void InsertUpdateAuth(WebDriver driver) {
		try{
			Waits.fluentWaitIsEnabled(driver, btn_A_InsertUpdateAuth(driver), 5);
			btn_A_InsertUpdateAuth(driver).click();
			Verify.ElementContainsText(driver, "Authorization List", 
					AM.Authorization.AuthorizationList.txt_AuthListHeader(driver));
		} catch (Exception e) {
			Report.Log(Status.FAIL, "Authorization was NOT Created/Edited successfully");
			Assert.fail("Authorization was NOT Created/Edited successfully");
			e.printStackTrace();
		}
	}

	// @ Test Objects for add new Authorization screen
	public static WebElement btn_A_InsertUpdateAuth(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='AuthForm']/fieldset[2]/div[7]/input"));
		return element;
	}

	// @ Objects under Detail Section
	public static WebElement dt_D_StartDate(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("StartDate"));
		return element;
	}

	public static WebElement dt_D_EndDate(WebDriver driver) {
		element = driver.findElement(By.id("EndDate"));
		return element;
	}

	public static Select lst_D_Branch(WebDriver driver) {
		element = driver.findElement(By.id("ListClinicBranchKey"));
		list = new Select(driver.findElement(By.id("ListClinicBranchKey")));
		return list;
	}

	public static Select lst_D_Status(WebDriver driver) {
		element = driver.findElement(By.id("authorizationStatusKey"));
		list = new Select(driver.findElement(By.id("authorizationStatusKey")));
		return list;
	}

	public static Select lst_D_Insurance(WebDriver driver) {
		element = driver.findElement(By.id("InsuranceDropdown"));
		list = new Select(driver.findElement(By.id("InsuranceDropdown")));
		return list;
	}

	public static WebElement txt_D_AuthorizationNumber(WebDriver driver) {
		element = driver.findElement(By.id("AuthorizationNumber"));
		return element;
	}

	// @Objects in Visits or Hours Authorized
	public static WebElement txt_VHA_SNVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("SNCount"));
		return element;
	}

	public static WebElement txt_VHA_PTVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("PTCount"));
		return element;
	}

	public static WebElement txt_VHA_OTVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("OTCount"));
		return element;
	}

	public static WebElement txt_VHA_STVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("STCount"));
		return element;
	}

	public static WebElement txt_VHA_MSWVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("MSWCount"));
		return element;
	}

	public static WebElement txt_VHA_HHAVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("HHACount"));
		return element;
	}

	public static WebElement txt_VHA_DieticianVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("DieticianCount"));
		return element;
	}

	public static WebElement txt_VHA_RNVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("RNCount"));
		return element;
	}

	public static WebElement txt_VHA_LVNVisitCount(WebDriver driver) {
		element = driver.findElement(By.id("LVNCount"));
		return element;
	}

	// @Objects Comment section
	public static WebElement txt_C_Comment(WebDriver driver) {
		element = driver.findElement(By.name("Comment"));
		return element;
	}

	public static WebElement Patient_D_Authorization(WebDriver driver) {
		element = driver.findElement(By.id("AuthorizationNumber"));
		return element;
	}

}
