package KH.Patient;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.TimeDate;
import components.Verify;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class PatientManager {
	static String strInputValue;
	static String strGlobalInputValue;
	private static WebElement element = null;

	public static void SelectInActivePatient(WebDriver driver) throws Exception {
		try {
			Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
			strInputValue = Datatable.GetValue("PM_PatientName");
			strGlobalInputValue = GlobalData.getPatientFullName();
			Waits.fluentWaitIsEnabled(driver, driver.findElement(By.xpath("//a[contains(@href,'editEpisode.cfm?Episodekey=')]")),10);
			WebElement tableElement = driver.findElement(By.xpath("//a[contains(@href,'editEpisode.cfm?Episodekey=')]"));
			if (!strInputValue.trim().isEmpty()
					&& strGlobalInputValue.trim().isEmpty()) {
				SortPatientBy(driver, strInputValue).click();
			} else {
				SortPatientBy(driver, strGlobalInputValue).click();
			}
			Waits.ForElementStaleness(driver, tableElement);
			strInputValue = Datatable.GetValue("PM_ShowC");
			if (!strInputValue.trim().isEmpty()) {
				ShowHideInactive(driver).click();
			}
			if (!strInputValue.trim().isEmpty()
					&& strGlobalInputValue.trim().isEmpty()) {
				SelectSortedPatients(driver, strInputValue);
			} else {
				SelectSortedPatients(driver, strGlobalInputValue);
			}
			Report.Log(Status.PASS, "INActive patient is selected successfully");
		} catch (Exception e) {
			Report.Log(Status.FAIL, "INActive patient is NOT selected");
			e.printStackTrace();
		}

	}

public static void SelectActivePatient(WebDriver driver) throws Exception {
		
		try {
			Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
			strInputValue = Datatable.GetValue("PM_PatientName");
			strGlobalInputValue = GlobalData.getPatientFullName();
			Waits.fluentWaitIsEnabled(driver, driver.findElement(By.xpath("//a[contains(@href,'editEpisode.cfm?Episodekey=')]")),10);
			WebElement tableElement = driver.findElement(By.xpath("//a[contains(@href,'editEpisode.cfm?Episodekey=')]"));
			if (!strInputValue.trim().isEmpty()
					&& strGlobalInputValue.trim().isEmpty()) {
				SortPatientBy(driver, strInputValue).click();
			} else {
				SortPatientBy(driver, strGlobalInputValue).click();
			}
			Waits.ForElementStaleness(driver, tableElement);
			if (!strInputValue.trim().isEmpty()
					&& strGlobalInputValue.trim().isEmpty()) {
				SelectSortedPatients(driver, strInputValue);
			} else {
				SelectSortedPatients(driver, strGlobalInputValue);
			}
		} catch (Exception e) {
			Report.Log(Status.FAIL, "Patient was NOT selected from Patient Manager");
			Assert.fail("Patient was NOT selected from Patient Manager");
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// @ Patient Manager test objects
	// To sort the Active patients
	public static WebElement SortPatientBy(WebDriver driver, String patientname) throws InterruptedException {
		char FL = patientname.charAt(0);
		String FLnew = String.valueOf(FL).toUpperCase();
		element = driver.findElement(By.xpath("//*[@id='" + FLnew + "']"));
		Waits.fluentWaitIsEnabled(driver, element, 5);
		return element;
	}

	// @ Patient Manager test objects
	// to click HideInactive link
	public static WebElement ShowHideInactive(WebDriver driver) {
		element = driver.findElement(By.id("ShowC"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	// To enter text in textbox
	public static WebElement txt_CPR_InactivePatients(WebDriver driver) {
		element = driver.findElement(By.id("filterInactivePatientsInput"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	// To select Patient with start letter of Lastname
		public static WebElement ToSelectAlphabetOfPatient(WebDriver driver, String Alphabet) throws InterruptedException {
			element = driver.findElement(By.xpath("//*[@id='" + Alphabet + "']"));
	     	Waits.ForElementVisibility(driver, element);
			return element;

		}

	// To select Patient with name
	public static WebElement SelectPatients(WebDriver driver, String FirstName,
			String LastName) throws InterruptedException {

		String FullName = LastName.concat(",").concat(" ").concat(FirstName)
				.concat(" ");
		System.out.println("FN : " + FullName);
		element = driver.findElement(By.linkText("" + FullName + ""));
		Waits.ForElementVisibility(driver, element);
		return element;

	}

	public static void SelectSortedPatients(WebDriver driver, String PatientName)
			throws Exception {
		boolean isPatientSelected = false;
		String firstName = PatientName.split(",")[1].trim();
		Waits.ForElementToBeClickable(driver, driver.findElement(By.partialLinkText(firstName)));
		if(driver.findElement(By.partialLinkText(firstName)).isDisplayed()){
			driver.findElement(By.partialLinkText(firstName)).click();
			Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
			isPatientSelected = true;
		}
		if (isPatientSelected) {
			Report.Log(Status.PASS, "Patient - '" + PatientName
					+ "' is selected");
			Assert.assertTrue(isPatientSelected, "Patient is selected");
		} else {
			Report.Log(Status.FAIL, "Patient - '" + PatientName
					+ "' is NOT selected");
			Assert.assertFalse(false);
		}
	}

	public static String getPatientDetails(WebDriver driver,
			String patientname, int columnno) {

		// WebElement element =
		// driver.findElement(By.xpath("//table/tbody/tr/td[contains(@class,'patientCol"+columnno+"')]"));

		// List<WebElement> tabele =
		// driver.findElements(By.xpath("//*[@class ='tr0']"));
		List<WebElement> tabele = driver.findElements(By.tagName("tr"));
		for (WebElement ntabele : tabele) {
			// String [] arr = new String[5];
			// String celltext
			// =tabele.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnno).getText();
			// System.out.println(celltext);
			System.out.println(ntabele.getText());
			if (ntabele.getText().contains(patientname)) {
				String[] arr = new String[tabele.size()];
				if (arr[1].trim().equals(patientname)) {
					System.out.println(arr[0]);
					System.err.println(arr[1]);

					System.out.println(arr[2]);
					return arr[2];
				} else {
					return "Text not fetched";
				}

			}
		}
		return null;

	}

}