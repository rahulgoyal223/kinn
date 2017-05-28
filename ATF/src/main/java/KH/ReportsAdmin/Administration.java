package KH.ReportsAdmin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

public class Administration {
	private static WebElement element = null;

	// @Method to select the menus
	public static void SelectTask(WebDriver driver, String Menus)
			throws InterruptedException {
		try {
			String[] parts = Menus.split("/");
			String part1 = parts[0];

			switch (part1) {
			case "Administration":
				String part2 = parts[1];
				boolean isMenuSelected = false;
				List<WebElement> menus = driver.findElements(By.xpath("//*[contains(@href,'/am/')" + "or (contains(@href,'/EHR/#/HM/facilities/'))]"));
				for (WebElement menu : menus) {
					String linktxt = menu.getText().trim();
					if (linktxt.equals(part2)) {
						menu.click();
						isMenuSelected = true;
						break;
					}
				}
				if (isMenuSelected = true) {
					Report.Log(Status.PASS, "" + part2 + " under " + part1
							+ " is selected successfully");
					Assert.assertTrue(isMenuSelected, "Patient is selected");
					break;
				} else {
					Report.Log(Status.FAIL, "" + part2 + " under " + part1
							+ " is NOT selected successfully");
					Assert.assertFalse(false);
					break;

				}

				// TODO To add cases for other menus
			case "":

				// To Navigate thru Reports Links
			case "Reports":
				String part3 = parts[1];
				boolean isSelected = false;
				List<WebElement> menuReports = driver.findElements(By.xpath("//*[contains(@href,'/am/')" + "or (contains(@href,'/EHR/#/HM/'))]"));
				for (WebElement menu : menuReports) {
					String linktxt = menu.getText().trim();
					if (linktxt.equals(part3)) {
						menu.click();
						isSelected = true;
						break;
					}
				}
				if (isSelected = true) {
					Report.Log(Status.PASS, "" + part3 + " under " + part1
							+ " is selected successfully");
					Assert.assertTrue(isSelected, "Patient is selected");
					break;
				} else {
					Report.Log(Status.FAIL, "" + part3 + " under " + part1
							+ " is NOT selected successfully");
					Assert.assertFalse(false);
					break;

				}


				// To Navigate thru Hard Close Links
			case "Hard Close":

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	//Objects for links under Reports 

	//To click Active Bereavement
	public static WebElement link_Active_Bereavement(WebDriver driver) {
		element = driver.findElement(By.linkText("Active Bereavement"));		
		return element;
	}

	//To click Admissions
	public static WebElement link_Admissions(WebDriver driver) {
		element = driver.findElement(By.linkText("Admissions"));		
		return element;
	}

	//To click Average Daily Census
	public static WebElement link_Average_Daily_Census(WebDriver driver) {
		element = driver.findElement(By.linkText("Average Daily Census"));		
		return element;
	}
	//To click CAHPS Export
	public static WebElement link_CAHPS_Export(WebDriver driver) {
		element = driver.findElement(By.linkText("CAHPS Export"));		
		return element;
	}
	//To click CAP Report
	public static WebElement link_CAP_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("CAP Report"));		
		return element;
	}
	//To click Discharge Report
	public static WebElement link_Discharge_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("Discharge Report"));		
		return element;
	}
	//To click HIS Measures Report
	public static WebElement link_HIS_Measures_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("HIS Measures Report"));		
		return element;
	}
	//To click IDG Meeting Report
	public static WebElement link_IDG_Meeting_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("IDG Meeting Report"));		
		return element;
	}
	//To click Length of Stay (LOS)
	public static WebElement link_Length_of_Stay_LOS(WebDriver driver) {
		element = driver.findElement(By.linkText("Length of Stay (LOS)"));		
		return element;
	}
	//To click Level of Care
	public static WebElement link_Level_of_Care(WebDriver driver) {
		element = driver.findElement(By.linkText("Level of Care"));		
		return element;
	}
	//To click Marketing Report
	public static WebElement link_Marketing_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("Marketing Report"));		
		return element;
	}
	//To click Mileage Detail Report
	public static WebElement link_Mileage_Detail_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("Mileage Detail Report"));		
		return element;
	}
	//To click Non-Admitted Patients
	public static WebElement link_Non_Admitted_Patients(WebDriver driver) {
		element = driver.findElement(By.linkText("Non-Admitted Patients"));		
		return element;
	}
	//To click Patient Roster
	public static WebElement link_Patient_Roster(WebDriver driver) {
		element = driver.findElement(By.linkText("Patient Roster"));
		return element;
	}

	//To click Pending Admissions
	public static WebElement link_Pending_Admissions(WebDriver driver) {
		element = driver.findElement(By.linkText("Pending Admissions"));
		return element;
	}

	//To click Pending (Re)Certifications / F2F Encounters
	public static WebElement link_Pending_ReCertifications_F2F_Encounters(WebDriver driver) {
		element = driver.findElement(By.linkText("Pending (Re)Certifications / F2F Encounters"));		
		return element;
	}
	//To click Utilization Report
	public static WebElement link_Utilization_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("Utilization Report"));		
		return element;
	}
	//To click Volunteer Hour Report
	public static WebElement link_Volunteer_Hour_Report(WebDriver driver) {
		element = driver.findElement(By.linkText("Volunteer Hour Report"));		
		return element;
	}


}