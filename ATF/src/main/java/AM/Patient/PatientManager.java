package AM.Patient;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.Verify;
import components.Waits;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.sql.Timestamp;
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
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        System.out.println("Entering select patient: " + timestamp);
			Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
			strInputValue = Datatable.GetValue("PM_PatientName");
			SelectActivePatientByName(driver, strInputValue);
		} catch (Exception e) {
			Report.Log(Status.FAIL, "Active patient is NOT selected");
			e.printStackTrace();
		}
	}

	public static void SelectActivePatientByName(WebDriver driver, String patientName) throws Exception{
		try {
			strInputValue = patientName;
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
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
	        System.out.println("Exiting select patient: " + timestamp2);
		} catch (Exception e) {
			Report.Log(Status.FAIL, "Patient was NOT selected from Patient Manager");
			Assert.fail("Patient was NOT selected from Patient Manager");
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e);
			Timestamp timestamp3 = new Timestamp(System.currentTimeMillis());
	        System.out.println("Catch error select patient: " + timestamp3);
		}
	}

	public static void SelectPatient(WebDriver driver, String patientName) throws Exception {
		Verify.ExactPageTitle(driver, "Patient Manager | Kinnser Software");
		WebElement tableElement = driver.findElement(By.xpath("//a[contains(@href,'editEpisode.cfm?Episodekey=')]"));
		SortPatientBy(driver, patientName).click();;
		Waits.ForElementStaleness(driver, tableElement);
		SelectPatientLinkText(driver, patientName);
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
	public static WebElement txt_CPR_InacivePatients(WebDriver driver) {
		element = driver.findElement(By.id("filterInactivePatientsInput"));
		return element;
	}

	// To select Patient with name
	public static WebElement SelectPatients(WebDriver driver, String FirstName,
			String LastName) throws InterruptedException {

		String FullName = LastName.concat(",").concat(" ").concat(FirstName)
				.concat(" ");
		System.out.println("FN : " + FullName);
		element = driver.findElement(By.linkText("" + FullName + ""));
		return element;

	}
	
	// To select Patient with firstname and lastname in the parameter
	public static void SelectPatientNameLink(WebDriver driver, String FirstName,
			String LastName) throws InterruptedException {

		String FullName = LastName.concat(",").concat(" ").concat(FirstName);
		System.out.println("FN : " + FullName);
		Thread.sleep(Waits.getSleepLevelTwo());
		boolean isPatientSelected = false;
		List<WebElement> tableContents = driver.findElements(By
				.xpath("//*[contains(@href,'editEpisode.cfm?Episodekey=')]"));
		for (WebElement tbl : tableContents) {
			String linkTxt = tbl.getText();
			if (linkTxt.trim().equals(FullName)) {
				tbl.click();
				isPatientSelected = true;
				break;
			}
		}
		if (isPatientSelected) {
			Report.Log(Status.PASS, "Patient - '" + FullName
					+ "' is selected");
			Assert.assertTrue(isPatientSelected, "Patient is selected");
		} else {
			Report.Log(Status.FAIL, "Patient - '" + FullName
					+ "' is NOT selected");
			Assert.assertFalse(false);
		}
	}
	
	public static void SelectPatientLinkText(WebDriver driver, String patientName){
		try { 
			Waits.fluentWaitTextIsDisplayed(driver, txt_PatientNameLink(driver), patientName, 2);
			WebElement PatientNameLink = driver.findElement(By.linkText(patientName));
			PatientNameLink.click();
			Verify.ExactPageTitle(driver, "Episode Manager | Kinnser Software");
    	} catch (Exception e) {
    		e.printStackTrace();
    		Report.Log(Status.FAIL, "Patient was NOT Selected");
    		Assert.fail("Patient was NOT Selected");
    	}
		
	}

	// To select Patient with start letter of Lastname
	public static WebElement ToSelectAlphabetOfPatient(WebDriver driver, String Alphabet) throws InterruptedException {
		element = driver.findElement(By.xpath("//*[@id='" + Alphabet + "']"));
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
	
	public static WebElement tbl_PatientList(WebDriver driver) {
		element = driver.findElement(By.id("sortTable1"));
		return element;
	}
	
	public static WebElement txt_PatientNameLink(WebDriver driver) {
		element = driver.findElement(By.xpath(".//*[@id='sortTable1']//td[@class='patientCol1']"));
		return element;
	}

}
