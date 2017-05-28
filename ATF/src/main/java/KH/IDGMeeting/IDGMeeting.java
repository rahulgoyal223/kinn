package KH.IDGMeeting;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import components.Waits;

public class IDGMeeting {
	private static WebElement element = null;
	private static Select list = null;

	// @ IDG Meeting Screen Objects
	// ## Clinic Branches drop down
	public static Select lst_IDG_ClinicBranch(WebDriver driver) {
		element = driver.findElement(By.id("clinic-branch-filter"));
		list = new Select(driver.findElement(By.id("clinic-branch-filter")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// ## Show Removed Patients link
	public static WebElement lnk_IDG_ShowRemovedPatients(WebDriver driver) {
		element = driver.findElement(By.linkText("Show Removed Patients"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Hide Removed Patients link
	public static WebElement lnk_IDG_HideRemovedPatients(WebDriver driver) {
		element = driver.findElement(By.linkText("Hide Removed Patients"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Show XX Entries drop down
	public static Select lst_IDG_ShowEntries(WebDriver driver) {
		element = driver.findElement(By.id("idgPageSize"));
		list = new Select(driver.findElement(By.id("idgPageSize")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// ## Filter by Search text box
	public static WebElement txt_IDG_FilterBySearch(WebDriver driver) {
		element = driver.findElement(By.id("idgFilter"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Patient List patient check box
	public static WebElement chk_IDG_Patient(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[1]/div[5]/div[2]/div[1]/div[2]/div[1]/div/div/table/tbody[1]/tr[2]/td[1]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Remove icon
	public static WebElement btn_IDG_RemoveIcon(WebDriver driver) {
		element = driver.findElement(By.xpath("//table[@id='searchTextResults']/tbody[1]/tr[2]/td[7]/span[2]/a"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Use Template drop down
	public static Select lst_IDG_UseTemplate(WebDriver driver) {
		element = driver.findElement(By.id("IDGMeetingTemplate"));
		list = new Select(driver.findElement(By.id("IDGMeetingTemplate")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	// ## Summary/Comment text field
	public static WebElement txt_IDG_SummaryComments(WebDriver driver) {
		element = driver.findElement(By.id("comment"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// ## Attendees RN text field
	public static WebElement res_IDG_AttendeesRN(WebDriver driver) {
		element = driver.findElement(By.cssSelector("ul.select2-choices"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	// ## Attendees MSW text field
	public static WebElement res_IDG_AttendeesMSW(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_MSW_person_1']//ul[.='            ']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Physician text field
	public static WebElement res_IDG_AttendeesPhy(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_Physician_person_2']//ul[.='            ']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Chaplain/Counselor text field
	public static WebElement res_IDG_AttendeesCC(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_Chaplain/Counselor_person_3']//ul[.='            ']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Volunteer text field
	public static WebElement res_IDG_AttendeesVol(WebDriver driver) {
		element = driver.findElement(By.xpath("//div[@id='s2id_Volunteer_person_4']//ul[.='            ']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees RN Other First Name text field
	public static WebElement txt_IDG_AttendeesRNOtherFN(WebDriver driver) {
		element = driver.findElement(By.id("OtherFirst_0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees RN Other Last Name text field
	public static WebElement txt_IDG_AttendeesRNOtherLN(WebDriver driver) {
		element = driver.findElement(By.id("OtherLast_0"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees MSW Other First Name text field
	public static WebElement txt_IDG_AttendeesMSWOtherFN(WebDriver driver) {
		element = driver.findElement(By.id("OtherFirst_1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees MSW Other Last Name text field
	public static WebElement txt_IDG_AttendeesMSWOtherLN(WebDriver driver) {
		element = driver.findElement(By.id("OtherLast_1"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Physician Other First Name text field
	public static WebElement txt_IDG_AttendeesPsyOtherFN(WebDriver driver) {
		element = driver.findElement(By.id("OtherFirst_2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Physician Other Last Name text field
	public static WebElement txt_IDG_AttendeesPsyOtherLN(WebDriver driver) {
		element = driver.findElement(By.id("OtherLast_2"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Chaplain/Counselor Other First Name text field
	public static WebElement txt_IDG_AttendeesCCOtherFN(WebDriver driver) {
		element = driver.findElement(By.id("OtherFirst_3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Chaplain/Counselor Other Last Name text field
	public static WebElement txt_IDG_AttendeesCCOtherLN(WebDriver driver) {
		element = driver.findElement(By.id("OtherLast_3"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Volunteer Other First Name text field
	public static WebElement txt_IDG_AttendeesVolOtherFN(WebDriver driver) {
		element = driver.findElement(By.id("OtherFirst_4"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Attendees Volunteer Other Last Name text field
	public static WebElement txt_IDG_AttendeesVolOtherLN(WebDriver driver) {
		element = driver.findElement(By.id("OtherLast_4"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Electronic Signature text field
	public static WebElement txt_IDG_ElectronicSig(WebDriver driver) {
		element = driver.findElement(By.id("eSigSnF"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Signature Date text field
	public static WebElement dt_IDG_ElecSigDate(WebDriver driver) {
		element = driver.findElement(By.id("IDGDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// ## Submit button
	public static WebElement btn_IDG_SubmitButton(WebDriver driver) {
		element = driver.findElement(By.id("eSigSnFSubmit"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
}
