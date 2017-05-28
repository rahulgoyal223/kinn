package KH.User;

import components.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditRole {

private static WebElement element = null;
	
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
	public static WebElement chk_BereavementCoordinatorRole(WebDriver driver) {
		element = driver.findElement(By.id("54"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_CaseManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("2"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ChaplainCounselorRole(WebDriver driver) {
		element = driver.findElement(By.id("48"));
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

	public static WebElement chk_PhysicianRole_KH(WebDriver driver) {
		element = driver.findElement(By.id("35"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_TherapistRole(WebDriver driver) {
		element = driver.findElement(By.id("13"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_VolunteerRole(WebDriver driver) {
		element = driver.findElement(By.id("49"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	//SPECIFIC RIGHTS
	public static WebElement chk_InsuranceAdminRole(WebDriver driver) {
		element = driver.findElement(By.id("67"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_LevelOfCareRole(WebDriver driver) {
		element = driver.findElement(By.id("50"));
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

	public static WebElement chk_VolunteerManagerRole(WebDriver driver) {
		element = driver.findElement(By.id("51"));
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

	public static WebElement chk_ApproveAssessmentsRole(WebDriver driver) {
		element = driver.findElement(By.id("52"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}
	
	public static WebElement chk_ApprovePlanOfCareRole(WebDriver driver) {
		element = driver.findElement(By.id("53"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_FilterQAAndHotboxRole(WebDriver driver) {
		element = driver.findElement(By.id("79"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_TelephoneOrdersRole(WebDriver driver) {
		element = driver.findElement(By.id("17"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_HISAdminRole(WebDriver driver) {
		element = driver.findElement(By.id("55"));
		Waits.ForElementVisibility(driver, element);
        return element;
	}

	public static WebElement chk_ImportPharmRefillRole(WebDriver driver) {
		element = driver.findElement(By.id("58"));
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

	public static WebElement chk_PatientMedRefillRole(WebDriver driver) {
		element = driver.findElement(By.id("59"));
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