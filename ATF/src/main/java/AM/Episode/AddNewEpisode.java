package AM.Episode;

import DataSource.Datatable;

import com.aventstack.extentreports.Status;

import components.Events;
import components.Report;
import components.Waits;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddNewEpisode {
	// @declare all episode variables in this section
	private static WebElement element = null;
	private static Select listbox = null;

	// @Create Episode Reusable methods
	public static void Filladdnewepisode(WebDriver driver) throws Exception {
		try {
			String StrInputvalue = Datatable.GetValue("AE_Startofcaredate");
			if (!StrInputvalue.trim().isEmpty()) {
				dt_AD_StartOfCareDate(driver).clear();
				Events.Fire(driver)
						.moveToElement(dt_AD_StartOfCareDate(driver)).click()
						.perform();
				dt_AD_StartOfCareDate(driver).sendKeys(StrInputvalue);
			}

			StrInputvalue = Datatable.GetValue("AE_EpisodeStartDate");
			if (!StrInputvalue.trim().isEmpty()) {
				dt_ED_EpisodeStartDate(driver).clear();
				Events.Fire(driver)
						.moveToElement(dt_ED_EpisodeStartDate(driver)).click()
						.perform();
				dt_ED_EpisodeStartDate(driver).sendKeys(StrInputvalue);
			}

			StrInputvalue = Datatable.GetValue("AE_ReferralDate");
			if (!StrInputvalue.trim().isEmpty()) {
				Events.Fire(driver).moveToElement(dt_AD_ReferralDate(driver))
						.click().perform();
				dt_AD_ReferralDate(driver).clear();
				dt_AD_ReferralDate(driver).sendKeys(StrInputvalue);
			}

			StrInputvalue = Datatable.GetValue("AE_InternalReferral");
			if (!StrInputvalue.trim().isEmpty()) {
				lst_AD_InternalReferral(driver).selectByValue(
						StrInputvalue);
				;
			}

			StrInputvalue = Datatable.GetValue("AE_AdmissionType");
			if (!StrInputvalue.trim().isEmpty()) {
				lst_AD_AdmissionType(driver).selectByVisibleText(StrInputvalue);
				;
			}

			/*
			 * StrInputvalue = Datatable.GetValue("AE_PrimaryInsurance"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_Ins_primaryinsurance(driver
			 * ).selectByVisibleText(StrInputvalue); }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_PrimaryAgent"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_primaryagent(driver).selectByVisibleText(StrInputvalue); }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_SecondaryAgent"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_PhysicalTherapist"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_ptherapist(driver).selectByVisibleText(StrInputvalue); }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_CaseManager"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_Oasis_Approver"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_PTApprover"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_OTApprover"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_STApprover"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 * 
			 * StrInputvalue = Datatable.GetValue("AE_Physician"); if
			 * (!StrInputvalue.trim().isEmpty()) {
			 * lst_CA_secondaryagent(driver).selectByVisibleText(StrInputvalue);
			 * }
			 */
			Report.Log(Status.PASS,
					"Episode details have been filled successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Episode details have not been filled");
			Assert.fail("Episode details have not been filled");
		}

	}

	public static void clk_btnYes(WebDriver driver, String option) {
		try {
			List<WebElement> btn = driver.findElements(By
					.xpath("//*[@type='button']"));
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

	public static void clk_btnNo(WebDriver driver, String option) {
		try {
			List<WebElement> btn = driver.findElements(By
					.xpath("//*[@type='button']"));
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

	// @ Create Episode Screen test objects
	public static WebElement dt_AD_StartOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("newAdmissionSOC"));
		return element;
	}

	public static WebElement dt_ED_EpisodeStartDate(WebDriver driver) {
		element = driver.findElement(By.id("Startdate"));
		return element;
	}

	public static WebElement dt_AD_startofcaredate(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("admissionKeySelect")));
		return element;
	}

	public static WebElement dt_ED_Edatedate(WebDriver driver) {
		element = driver.findElement(By.id("Startdate"));
		return element;
	}

	public static WebElement dt_AD_ReferralDate(WebDriver driver) {
		element = driver.findElement(By.id("ReferralDate"));
		return element;
	}

	public static Select lst_AD_InternalReferral(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("internal-referral")));
		return listbox;
	}

	public static Select lst_AD_AdmissionType(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("admission-type")));
		return listbox;
	}

	public static Select lst_Ins_primaryinsurance(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("primaryInsurance")));
		return listbox;
	}

	public static Select lst_CA_primaryagent(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("Clinician")));
		return listbox;
	}

	public static Select lst_CA_secondaryagent(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("Aide")));
		return listbox;
	}

	public static Select lst_CA_ptherapist(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("PT")));
		return listbox;
	}

	public static Select lst_CA_CaseManager(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("CM")));
		return listbox;
	}

	public static Select lst_CA_Oasisapprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("O")));
		return listbox;
	}

	public static Select lst_CA_PTapprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("PTA")));
		return listbox;
	}

	public static Select lst_CA_OTapprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("OTA")));
		return listbox;
	}

	public static Select lst_CA_STapprover(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("STA")));
		return listbox;
	}

	public static Select lst_CA_physician(WebDriver driver) {
		listbox = new Select(driver.findElement(By.id("MD")));
		return listbox;
	}

	public static WebElement btn_AddEpisode(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("addEpisodeButton"));
		return element;
	}

}
