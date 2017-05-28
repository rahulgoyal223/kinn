package AM.User;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EditRole {
	
	private static WebElement element = null;
	private static boolean ischecked = false;
	
	// @Create Patient Reusable methods
	
	public static void addBasicRolesToNewUser(WebDriver driver) {
		
		chk_AdministratorRole(driver).click();
		chk_AddAndEditPatientRole(driver).click();
		chk_BillingReportsRole(driver).click();
		chk_CaseManagerRole(driver).click();
		chk_ClaimsManagerRole(driver).click();
		chk_ClinicianRole(driver).click();
		
		chk_PatientManagerRole(driver).click();
		chk_InboxRole(driver).click();
		chk_ReportsAdminRole(driver).click();
		chk_AddAndEditRolesRole(driver).click();
		chk_UserLicensesRole(driver).click();
		chk_CreateAndManageUsersRole(driver).click();
		btn_UpdateRoles(driver).click();
	}
	
	public static void CheckCheckbox(WebDriver driver, WebElement element,boolean unchecked){
		if(!element.isSelected()||(element.isSelected() && unchecked )){
			element.click();
			btn_UpdateRoles(driver).click();
		}
	}

	public static boolean ReturnRoleValue (WebElement element)	{
		if (element.isSelected()){
			ischecked = true;
		}
		return ischecked;
	}
	
	
	
	
	// @ Sections in Edit Role page
	
	//BILLING MANAGER
	public static WebElement chk_ClaimsManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("16"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement chk_BillingReportsRole(WebDriver driver) {
		element = driver.findElement(By.id("65"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_HardCloseRole(WebDriver driver) {
		element = driver.findElement(By.id("66"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	//DISTRIBUTION LISTS
	public static WebElement chk_InboxRole(WebDriver driver) {
		element = driver.findElement(By.id("31"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_BillingSummaryRole(WebDriver driver) {
		element = driver.findElement(By.id("23"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_EligibilityRole(WebDriver driver) {
		element = driver.findElement(By.id("30"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_FaxesRole(WebDriver driver) {
		element = driver.findElement(By.id("19"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}


	//JOB FUNCTIONS
	public static WebElement chk_ApproveAuthRole(WebDriver driver) {
		element = driver.findElement(By.id("76"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ApproveEligibiltyRole(WebDriver driver) {
		element = driver.findElement(By.id("75"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_CaseManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("2"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ChangeAuthStatusRole(WebDriver driver) {
		element = driver.findElement(By.id("77"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ClinicalReviewRole(WebDriver driver) {
		element = driver.findElement(By.id("11"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ClinicianRole(WebDriver driver) {
		element = driver.findElement(By.id("1"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ExternalUserRole(WebDriver driver) {
		element = driver.findElement(By.id("57"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_HHARole(WebDriver driver) {
		element = driver.findElement(By.id("14"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_MSWRole(WebDriver driver) {
		element = driver.findElement(By.id("15"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_TherapistRole(WebDriver driver) {
		element = driver.findElement(By.id("13"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	//SPECIFIC RIGHTS
	public static WebElement chk_AuthManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("69"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_InsuranceAdminRole(WebDriver driver) {
		element = driver.findElement(By.id("67"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PatientManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("25"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PatientRosterRole(WebDriver driver) {
		element = driver.findElement(By.id("22"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PatientSchedulesRole(WebDriver driver) {
		element = driver.findElement(By.id("5"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PayrollRole(WebDriver driver) {
		element = driver.findElement(By.id("26"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PhoneRosterRole(WebDriver driver) {
		element = driver.findElement(By.id("42"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ReportsAdminRole(WebDriver driver) {
		element = driver.findElement(By.id("33"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_AddAndEditPatientRole(WebDriver driver) {
		element = driver.findElement(By.id("21"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_AdministratorRole(WebDriver driver) {
		element = driver.findElement(By.id("4"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ApproveOASISRole(WebDriver driver) {
		element = driver.findElement(By.id("20"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_AuthRequestRole(WebDriver driver) {
		element = driver.findElement(By.id("71"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_BypassCaseManagementRole(WebDriver driver) {
		element = driver.findElement(By.id("36"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_CreateAuthRole(WebDriver driver) {
		element = driver.findElement(By.id("70"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_FilterQAAndHotboxRole(WebDriver driver) {
		element = driver.findElement(By.id("79"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_FrequencyExceptionRole(WebDriver driver) {
		element = driver.findElement(By.id("72"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_TelephoneOrdersRole(WebDriver driver) {
		element = driver.findElement(By.id("17"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_GoalAdministrationRole(WebDriver driver) {
		element = driver.findElement(By.id("80"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement chk_KinnserDMRole(WebDriver driver) {
		element = driver.findElement(By.id("60"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_KinnserDMAdminRole(WebDriver driver) {
		element = driver.findElement(By.id("61"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ManageTasksAndSettingsRole(WebDriver driver) {
		element = driver.findElement(By.id("45"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_OrdersManagementRole(WebDriver driver) {
		element = driver.findElement(By.id("18"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PatientReferralRole(WebDriver driver) {
		element = driver.findElement(By.id("24"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PrefillRole(WebDriver driver) {
		element = driver.findElement(By.id("32"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_RescheduleClinicianTasksRole(WebDriver driver) {
		element = driver.findElement(By.id("44"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_PRNRole(WebDriver driver) {
		element = driver.findElement(By.id("41"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_SearchRole(WebDriver driver) {
		element = driver.findElement(By.id("34"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ClaimCommentsUpdateRole(WebDriver driver) {
		element = driver.findElement(By.id("74"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ElectronicSigRole(WebDriver driver) {
		element = driver.findElement(By.id("27"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement chk_HHRGCalcRole(WebDriver driver) {
		element = driver.findElement(By.id("6"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	//USER MANAGER
	public static WebElement chk_AccountManagementRole(WebDriver driver) {
		element = driver.findElement(By.id("78"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement chk_AddAndEditRolesRole(WebDriver driver) {
		element = driver.findElement(By.id("37"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_CreateAndManageUsersRole(WebDriver driver) {
		element = driver.findElement(By.id("7"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_UserLicensesRole(WebDriver driver) {
		element = driver.findElement(By.id("64"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	
	public static WebElement btn_UpdateRoles(WebDriver driver) {
		element = driver.findElement(By.cssSelector("input[type='submit'][value='Update Roles']"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
}