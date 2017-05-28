package AM.PendingAdmissions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.Waits;

public class PendingAdmissions {

	private static WebElement element;

	// @Method - To select the patient under Pending admission
	public static WebElement SelectPatient(WebDriver driver, String patientname)
			throws InterruptedException {
		System.out.println("Inside the loop");
		Waits.ForBrowserLoad(driver);
		List<WebElement> patient = driver
				.findElements(By
						.xpath("//a[contains(@ng-href,'/EHR/#/AM/episode-list/patient/')]"));
		for (WebElement pat : patient) {
			String patname = pat.getText();
			if (patname.trim().equals(patientname)) {
				pat.click();
				break;
			}

		}
		return element;
	}

	public static WebElement txt_PER_Searchbox(WebDriver driver) {
		element = driver.findElement(By.name("gridQuery"));
		return element;

	}

	public static WebElement txt_PA_Searchbox(WebDriver driver) {
		element = driver.findElement(By.id("gridQuery|angularGrid|c5ba0"));
		return element;

	}

	public static WebElement VerifyPatient(WebDriver driver, String patientname) {
		try {
			Waits.ForBrowserLoad(driver);
			List<WebElement> allrows = driver
					.findElements(By
							.xpath("//a[contains(@href,'/EHR/#/AM/episode-list/patient/')]"));
			boolean isPatientVerified = false;
			for (WebElement row : allrows) {
				Waits.ForElementVisibility(driver, row);
				if (row.getText().trim().equals(patientname.trim())) {
					isPatientVerified = true;
					break;
				}
			}
			if (isPatientVerified) {
				Report.Log(Status.PASS, "" + patientname + " is displayed");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + patientname + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// Eg : Input of value should be like : Pending Admission/Serachbox
	public static void SearchBox(WebDriver driver, String value)
			throws Exception {
		String[] val = value.split("/");
		String part1 = val[0];
		int i = 1;
		List<WebElement> alltxt = driver.findElements(By
				.xpath("//*[@ng-model='settings.query.text']"));
		switch (part1.trim()) {
		case "Physician E-Referrals":
			if (!GlobalData.getPatientFullName().isEmpty()) {
				alltxt.get(i).sendKeys(GlobalData.getPatientFullName());
			} else {
				alltxt.get(i).sendKeys(Datatable.GetValue("PE_Referrals"));
			}

		case "Pending Admissions":
			if (!GlobalData.getPatientFullName().isEmpty()) {
				alltxt.get(i + 1).sendKeys(GlobalData.getPatientFullName());
			} else {
				alltxt.get(i + 1).sendKeys(Datatable.GetValue("PA_Search"));
			}
		}
	}
}
