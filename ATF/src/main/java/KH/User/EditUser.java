package KH.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class EditUser {
	private static WebElement element = null;
	private static Select list = null;

	// @Edit User Objects
	public static WebElement btn_EU_Update(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='PTForm']/div/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @User Information Objects
	public static WebElement txt_UI_LastName(WebDriver driver) {
		element = driver.findElement(By.name("lastname"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_FirstName(WebDriver driver) {
		element = driver.findElement(By.name("FirstName"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Suffix(WebDriver driver) {
		element = driver.findElement(By.name("Suffix"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_UI_UserType(WebDriver driver) {
		element = driver.findElement(By.id("Discipline"));
		list = new Select(driver.findElement(By.id("Discipline")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_UI_UserName(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_AddressOne(WebDriver driver) {
		element = driver.findElement(By.id("addressOne"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_AddressTwo(WebDriver driver) {
		element = driver.findElement(By.id("AddressTwo"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("ZIPCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_City(WebDriver driver) {
		element = driver.findElement(By.id("City"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_State(WebDriver driver) {
		element = driver.findElement(By.id("State"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("email"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("phonea"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("phoneb"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("phonec"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_PhoneTwo1(WebDriver driver) {
		element = driver.findElement(By.id("phone2a"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_PhoneTwo2(WebDriver driver) {
		element = driver.findElement(By.id("phone2b"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_PhoneTwo3(WebDriver driver) {
		element = driver.findElement(By.id("phone2c"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_EmployeeID(WebDriver driver) {
		element = driver.findElement(By.name("employeeID"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_UI_DateOfBirth(WebDriver driver) {
		element = driver.findElement(By.id("adateofbirth"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Social1(WebDriver driver) {
		element = driver.findElement(By.id("Social1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Social2(WebDriver driver) {
		element = driver.findElement(By.id("Social2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_UI_Social3(WebDriver driver) {
		element = driver.findElement(By.id("Social3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement chk_UI_IncludeInPayroll(WebDriver driver) {
		element = driver.findElement(By.id("Payroll"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_UI_AsOfDate(WebDriver driver) {
		element = driver.findElement(By.id("asOfDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_UI_EmployeeType(WebDriver driver) {
		element = driver.findElement(By.id("EmployeeType"));
		list = new Select(driver.findElement(By.id("EmployeeType")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_UI_PrimaryBranch(WebDriver driver) {
		element = driver.findElement(By.name("ClinicBranchKey"));
		list = new Select(driver.findElement(By.name("ClinicBranchKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_UI_IsUserPhysician(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianCheckbox"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Weekend/Non-Business Hours Access
	public static WebElement chk_WA_WeekendAccess(WebDriver driver) {
		element = driver.findElement(By.id("WA"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_WA_EarliestLoginTime(WebDriver driver) {
		element = driver.findElement(By.id("AccessStart"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_WA_LatestLoginTime(WebDriver driver) {
		element = driver.findElement(By.id("AccessEnd"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Hire/Termination Dates
	public static WebElement dt_HTD_HireDate(WebDriver driver) {
		element = driver.findElement(By.id("HireDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_HTD_TerminationDate(WebDriver driver) {
		element = driver.findElement(By.id("TermDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// @Attachments
	public static WebElement btn_A_ChooseFile(WebDriver driver) {
		element = driver.findElement(By.id("attachment"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_A_UploadAttachment(WebDriver driver) {
		element = driver.findElement(By.id("uploadAttachmentattachment"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
