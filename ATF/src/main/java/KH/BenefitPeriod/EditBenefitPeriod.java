package KH.BenefitPeriod;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class EditBenefitPeriod {
	// @declare all Benefit Period variables in this section
	private static WebElement element = null;
	private static Select list = null;

	// @Objects Edit Benefit Period
	public static WebElement dt_EBP_StartDate(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodStartDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_EBP_EndDate(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodEndDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EBP_TriageRiskCode(WebDriver driver) {
		element = driver.findElement(By.id("TriageRiskCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_EBP_CaseManager(WebDriver driver) {
		element = driver.findElement(By.id("CaseManager"));
		list = new Select(driver.findElement(By.id("CaseManager")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EBP_Physician(WebDriver driver) {
		element = driver.findElement(By.id("idgPhysician"));
		list = new Select(driver.findElement(By.id("idgPhysician")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EBP_RegisteredNurse(WebDriver driver) {
		element = driver.findElement(By.id("idgRN"));
		list = new Select(driver.findElement(By.id("idgRN")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EBP_SocialWorker(WebDriver driver) {
		element = driver.findElement(By.id("idgMSW"));
		list = new Select(driver.findElement(By.id("idgRN")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EBP_ChaplainCounselor(WebDriver driver) {
		element = driver.findElement(By.id("idgChaplain"));
		list = new Select(driver.findElement(By.id("idgChaplain")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_EBP_Volunteer(WebDriver driver) {
		element = driver.findElement(By.id("idgVolunteer"));
		list = new Select(driver.findElement(By.id("idgVolunteer")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement chk_EBP_InactivateBenefitPeriod(WebDriver driver) {
		element = driver.findElement(By.name("benefitPeriodInactive"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_EBP_Comment(WebDriver driver) {
		element = driver.findElement(By.id("epidoseComment"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement btn_EBP_SaveAndSubmit(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
}
