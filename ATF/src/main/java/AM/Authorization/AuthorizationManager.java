package AM.Authorization;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Verify;
import components.Waits;

public class AuthorizationManager {

	static WebElement element;
	private static Select list;
	
	//@Method - To mark auth elgible
	public static void MarkAuthElgible(WebDriver driver, String patientName) throws Exception {
	      Waits.ForElementToBeClickable(driver, btn_Branch_SelectAll(driver));
	      Verify.ExactPageTitle(driver, "Authorization Manager - Pending Verification | Kinnser Software");
	      btn_Branch_SelectAll(driver).click();
	      btn_Insurance_SelectAll(driver).click();
	      btn_ApplyFilters(driver).click();
	      Waits.fluentWaitIsEnabled(driver, txt_Searchbox(driver), 5);
		  txt_Searchbox(driver).sendKeys(patientName);
	      SelectPatient(driver, patientName);	      
	      btn_Eligibility(driver).click();	      
	      SelectEligibility(driver, "Eligible");
	      VerifyAlertMsg(driver, "Eligible patients have been moved to Pre-Authorization"
	      		+ " or Ready to Schedule tabs as relevant to their insurance.");
	}
	
	//@Method - To approve Auth in pre-authorization tab
	public static void ApprovePreAuth(WebDriver driver, String patientName) {
		try{
		    Verify.ExactPageTitle(driver, "Authorization Manager - Pre Authorization | Kinnser Software");
		    Waits.ForElementToBeClickable(driver, btn_Branch_SelectAll(driver));
		    btn_Branch_SelectAll(driver).click();
		    btn_Insurance_SelectAll(driver).click();
		    btn_AuthorizationStatus_SelectAll(driver).click();
		    btn_ApplyFilters(driver).click();
		    Waits.fluentWaitIsEnabled(driver, txt_Searchbox(driver), 5);
		    txt_Searchbox(driver).sendKeys(patientName);
		    SelectPatient(driver, patientName);
		    btn_PA_Approve(driver).click();
		    VerifyAlertMsg(driver, "Authorization(s) approved. This Authorization has "
		    		+ "been moved to the 'Ready to Schedule' tab.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Method to Search for Patient in Ready to Schedule tab
	public static void SearchInReady2Schedule(WebDriver driver, String patientName) {
		try{
			Waits.ForElementToBeClickable(driver, btn_Branch_SelectAll(driver));
		    btn_Branch_SelectAll(driver).click();
		    btn_Insurance_SelectAll(driver).click();
		    btn_ApplyFilters(driver).click();
		    Waits.fluentWaitIsEnabled(driver, txt_Searchbox(driver), 5);
		    txt_Searchbox(driver).sendKeys(patientName); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// @Method - To select Patient
	public static WebElement SelectPatient(WebDriver driver, String patientName)
			throws Exception {
		try {
			boolean isPatientSelected = false;
			String firstName = patientName.split(",")[1].trim();
			Waits.ForElementToBeClickable(driver, driver.findElement(By.partialLinkText(firstName)));
			if(driver.findElement(By.partialLinkText(firstName)).isDisplayed()){
				driver.findElement(By.xpath(".//input[@type = 'checkbox']")).click();
				isPatientSelected = true;
			}
			if (isPatientSelected) {
				Report.Log(Status.PASS, "Patient - '" + patientName
						+ "' is selected");
				Assert.assertTrue(isPatientSelected, "Patient is selected");
			} else {
				Report.Log(Status.FAIL, "Patient - '" + patientName
						+ "' is NOT selected");
				Assert.assertFalse(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @Objects Searchbox under AM

	public static WebElement btn_PO_Update(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//input[contains(@value,'Update Visits')]"));
		return element;

	}

	/*
	 * public static WebElement txt_Searchbox(WebDriver driver) { element =
	 * driver.findElement(By.xpath(
	 * "//input[contains(@id,'gridQuery|angularGrid|') and contains(@name,'gridQuery')]"
	 * )); Waits.ForElementVisibility(driver, element); return element;
	 * 
	 * }
	 */

	// @Method - To Select Eligibility
	public static WebElement SelectEligibility(WebDriver driver, String criteria)
			throws Exception {
		try {
			boolean isEligibilitySelected = false;
			List<WebElement> allrows = driver.findElements(By
					.xpath("//a[contains(@ng-click,'setPatients')]"));

			for (WebElement row : allrows) {
				if (row.getText().equals(criteria)) {
					// element =
					// row.findElement(By.xpath("//a[contains(@ng-click,'setPatientsEligible()')]"));
					// element = row.findElement(By.linkText(criteria));
					row.click();
					isEligibilitySelected = true;
					break;
				}
			}
			if (isEligibilitySelected) {
				Report.Log(Status.PASS, "" + criteria + " is selected");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + criteria + " is NOT selected");
				Assert.fail("" + criteria + " is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static WebElement btn_Eligibility(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[contains(@data-toggle,'dropdown')]"));
		return element;
	}

	// @Method - To select the Tabs
	public static WebElement SelectTab(WebDriver driver, String tabName) {
		try {
			boolean isTabSelected = false;
			List<WebElement> tabs = driver
					.findElements(By
							.xpath("//a[contains(@href,'#/AM/authorization-manager/') and @class='ng-scope']"));
			for (WebElement tab : tabs) {
				if (tab.getText().equals(tabName)) {
					tab.click();
					isTabSelected = true;
					break;
				}
			}
			if (isTabSelected) {
				Report.Log(Status.PASS, "" + tabName
						+ " tab is selected successfully");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + tabName
						+ "tab is NOT selected successfully");
				Assert.fail("" + tabName+ "tab is NOT selected successfully");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return element;
	}

	public static void VerifyAlertMsg(WebDriver driver,String Msg) throws Exception {
		String getMsg;
		try {
			Waits.fluentWaitIsEnabled(driver, txt_AlertMsg(driver), 5);
			getMsg = txt_AlertMsg(driver).getText();
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
				Report.attachScreenShotToReport(driver);
				Assert.fail("Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			Report.attachScreenShotToReport(driver);
		}

	}
	// @Method to verify the patient
	public static WebElement VerifyPatient(WebDriver driver, String patientname) {
		try {
			boolean isPatientVerified = false;
			Waits.fluentWaitIsEnabled(driver, driver.findElement(By.linkText(patientname)), 5);
			if(driver.findElement(By.linkText(patientname)).isDisplayed()){
				isPatientVerified = true;
			}
			if (isPatientVerified) {
				Report.Log(Status.PASS, "Patient - '" + patientname
						+ "' is displayed");
				Assert.assertTrue(isPatientVerified, "Patient is displayed");
			} else {
				Report.Log(Status.FAIL, "Patient - '" + patientname
						+ "' is NOT selected");
				Assert.assertFalse(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @Objects Searchbox under AM
	public static WebElement txt_Searchbox(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//input[contains(@id,'gridQuery|angularGrid|') and contains(@name,'gridQuery')]"));
		return element;

	}

	public static WebElement txt_AlertMsg(WebDriver driver) {
		element = driver.findElement(By.id("alert"));
		return element;
	}
	// @Objects Buttons Under Branch
	public static WebElement btn_Branch_SelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_Branch_btn_select_all"));
		return element;

	}

	public static WebElement btn_Branch_Clear(WebDriver driver) {
		element = driver.findElement(By.id("filter_Branch_btn_clear_all"));
		return element;

	}

	// @Objects Buttons Under Insurance
	public static WebElement btn_Insurance_SelectAll(WebDriver driver) {
		element = driver.findElement(By.id("filter_Insurance_btn_select_all"));
		return element;

	}

	public static WebElement btn_Insurance_Clear(WebDriver driver) {
		element = driver.findElement(By.id("filter_Insurance_btn_clear_all"));
		return element;

	}

	public static WebElement btn_ApplyFilters(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//button[contains(@type,'button') and contains(@ng-click,'fetchData()')]"));
		Waits.ForElementVisibility(driver, element);
		return element;

	}

	public static Select lst_OT_Action(WebDriver driver, int rowIndex) {
		element = driver.findElements(
				By.xpath("//select[contains(@name,'action_')]")).get(rowIndex);
		list = new Select(element);
		return list;
	}

	// @Objects under Authorization Status
	public static WebElement btn_AuthorizationStatus_SelectAll(WebDriver driver) {
		element = driver.findElement(By
				.id("filter_Authorization_Status_btn_select_all"));
		return element;
	}

	public static WebElement btn_AuthorizationStatus_Clear(WebDriver driver) {
		element = driver.findElement(By
				.id("filter_Authorization_Status_btn_clear_all"));
		return element;
	}

	public static WebElement btn_PA_Approve(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[@ng-click='approvePatients()']"));
		return element;
	}

}
