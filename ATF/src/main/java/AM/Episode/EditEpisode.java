package AM.Episode;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Report;
import components.Waits;

public class EditEpisode {

	// @declare all episode variables in this section
	private static WebElement element = null;
	private static Select listbox = null;
	private static String strInputValue;

	// @Objects Edit Episode
	public static WebElement btn_EE_Update(WebDriver driver) {
		element = driver.findElement(By.id("updateButton"));
		return element;
	}

	public static WebElement txt_EE_MRN(WebDriver driver) {
		element = driver.findElement(By.id("MRText"));
		return element;
	}

	public static WebElement dt_EE_EpisodeStartDate(WebDriver driver) {
		element = driver.findElement(By.id("StartDate"));
		return element;
	}

	public static WebElement dt_EE_EpisodeEndDate(WebDriver driver) {
		element = driver.findElement(By.name("EndDate"));
		return element;
	}

	public static Select lst_EE_Branch(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("Branch")));
		return listbox;
	}

	public static Select lst_EE_PrimaryAgent(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("Clinician")));
		return listbox;
	}

	public static Select lst_EE_SecondaryAgent(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("Aide")));
		return listbox;
	}

	public static Select lst_EE_Therapist(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("PT")));
		return listbox;
	}

	public static Select lst_EE_CaseManager(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("casemanager")));
		return listbox;
	}

	public static Select lst_EE_OASISApprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("OASIS")));
		return listbox;
	}

	public static Select lst_EE_PrimaryInsurance(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("PatientInsuranceKey1")));
		return listbox;
	}

	public static Select lst_EE_PrimaryPhysician(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("Physiciankey")));
		return listbox;
	}

	public static Select lst_EE_SecondaryPhysician(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("PhysiciankeyS")));
		return listbox;
	}

	public static WebElement txt_EE_TherapyNeed(WebDriver driver) {
		element = driver.findElement(By.id("TherapyNeed"));
		return element;
	}

	public static Select lst_EE_PTApprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("PTA")));
		return listbox;
	}

	public static Select lst_EE_OTApprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("OTA")));
		return listbox;
	}

	public static Select lst_EE_STApprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("STA")));
		return listbox;
	}

	public static Select lst_EE_ExternalReferral(WebDriver driver) {
		listbox = new Select(driver.findElement(By.name("ExternalReferral")));
		return listbox;
	}

	public static WebElement txt_EE_PayrollSurcharge(WebDriver driver) {
		element = driver.findElement(By.name("VisitSurcharge"));
		return element;
	}

	public static WebElement txt_EE_EpisodeComment(WebDriver driver) {
		element = driver.findElement(By.name("EpisodeComment"));
		return element;
	}

	// @Objects Review Diagnosis and Frequency Orders section
	public static WebElement txt_DF_PrimaryDiagnosis(WebDriver driver) {
		element = driver.findElement(By.name("Primary"));
		return element;
	}

	public static WebElement txt_DF_SecondaryDiagnosis(WebDriver driver) {
		element = driver.findElement(By.name("Secondary"));
		return element;
	}

	public static WebElement txt_DF_TriageCode(WebDriver driver) {
		element = driver.findElement(By.name("Triage"));
		return element;
	}

	// @Objects Manage Episode section
	public static WebElement chk_ME_InactiveEpisode(WebDriver driver) {
		element = driver.findElement(By.id("deleteEpisode"));
		return element;
	}

	public static Select lst_ME_MoveToAdmission(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("admissionKey")));
		return listbox;
	}

	// @Objects Pre-Claim Review section
	public static WebElement txt_PCR_TrackingNumber(WebDriver driver) {
		element = driver.findElement(By.id("pcrTrackingNumber"));
		return element;
	}

	public static WebElement txt_PCR_Comment(WebDriver driver) {
		element = driver.findElement(By.id("pcrComment"));
		return element;
	}
	public static WebElement txt_EE_MedicalRecordKey(WebDriver driver) {
		element = driver.findElement(By.name("medicalrecordkey"));
		return element;
	}

	public static WebElement txt_EE_StartDate(WebDriver driver)  {
		element = driver.findElement(By.name("StartDate"));
		return element;
	}

	public static WebElement txt_EE_EndDate(WebDriver driver) {
		element = driver.findElement(By.name("EndDate"));
		return element;
	}

	public static void clk_btnYes(WebDriver driver, String option) {
		try {
			List<WebElement> btn = driver.findElements(By.xpath("//*[@type='button']"));
			for (WebElement btns : btn) {
				String btnsTxt = btns.getText();
				if (btnsTxt.equals(option)) {
					btns.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
