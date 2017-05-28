package KH.BenefitPeriod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Events;
import components.Report;
import components.Waits;

public class AddNewBenefitPeriod {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	
	// @Create Benefit Reusable methods
    public static void FillAddNewBenefitPeriod(WebDriver driver) throws Exception {

        try {
            strInputValue = Datatable.GetValue("BP_StartDate");
            if (!strInputValue.trim().isEmpty()) {
            	Events.Fire(driver).moveToElement(dt_BP_StartDate(driver)).click().perform();
            	dt_BP_StartDate(driver).clear();
            	dt_BP_StartDate(driver).sendKeys(strInputValue); 
            	GlobalData.setDate(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_EndDate");
            if (!strInputValue.trim().isEmpty()) {
            	Events.Fire(driver).moveToElement(dt_BP_EndDate(driver)).click().perform();
            	dt_BP_EndDate(driver).clear();
            	dt_BP_EndDate(driver).sendKeys(strInputValue);               
            }
            
            strInputValue = Datatable.GetValue("BP_Triage");
            if (!strInputValue.trim().isEmpty()) {
            	txt_BP_TriageRiskCode(driver).clear();
            	txt_BP_TriageRiskCode(driver).sendKeys(strInputValue);               
            }
            
            strInputValue = Datatable.GetValue("BP_CaseManager");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_CaseManager(driver).selectByVisibleText(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_Physician");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_Physician(driver).selectByVisibleText(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_RegisteredNurse");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_RegisteredNurse(driver).selectByVisibleText(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_SocialWorker");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_SocialWorker(driver).selectByVisibleText(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_Chaplain");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_ChaplainCounselor(driver).selectByVisibleText(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("BP_Volunteer");
            if (!strInputValue.trim().isEmpty()) {
            	lst_BP_Volunteer(driver).selectByVisibleText(strInputValue);
            }
            
            Report.Log(Status.PASS, "New Benefit Period details have been filled successfully");
            
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "New Benefit Period details have NOT been filled");
        }
    }

	// @ Create Benefit Period Screen test objects
	public static WebElement dt_BP_StartDate(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodStartDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement dt_BP_EndDate(WebDriver driver) {
		element = driver.findElement(By.id("benefitPeriodEndDate"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_BP_TriageRiskCode(WebDriver driver) {
		element = driver.findElement(By.id("TriageRiskCode"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_BP_CaseManager(WebDriver driver) {
		element = driver.findElement(By.id("CaseManager"));
		list = new Select(driver.findElement(By.id("CaseManager")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_BP_Physician(WebDriver driver) {
		element = driver.findElement(By.id("idgPhysician"));
		list = new Select(driver.findElement(By.id("idgPhysician")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_BP_RegisteredNurse(WebDriver driver) {
		element = driver.findElement(By.id("idgRN"));
		list = new Select(driver.findElement(By.id("idgRN")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_BP_SocialWorker(WebDriver driver) {
		element = driver.findElement(By.id("idgMSW"));
		list = new Select(driver.findElement(By.id("idgRN")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_BP_ChaplainCounselor(WebDriver driver) {
		element = driver.findElement(By.id("idgChaplain"));
		list = new Select(driver.findElement(By.id("idgChaplain")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static Select lst_BP_Volunteer(WebDriver driver) {
		element = driver.findElement(By.id("idgVolunteer"));
		list = new Select(driver.findElement(By.id("idgVolunteer")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement btn_BP_SaveAndSubmit(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	public static WebElement alert_Verify(WebDriver driver) {
		element = driver.findElement(By.id("alert"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//To verify created benefit period for the patient 
	public static WebElement VerifyBenefit(WebDriver driver, String patientname) {
		try {
			Waits.ForBrowserLoad(driver);
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			boolean isBenefitVerified = false;
			for (WebElement row : allrows) {
				if (row.getText().trim().contains(patientname.trim())) {
					isBenefitVerified = true;
					break;
				}
			}
			if (isBenefitVerified) {
				Report.Log(Status.PASS, "" + patientname + " is displayed");
				Report.attachScreenShotToReport(driver);
				Assert.assertTrue(true);
			} else {
				Report.Log(Status.FAIL, "" + patientname + " is NOT displayed");
				Assert.fail("" + patientname + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public static void VerifyAlertmessage(WebDriver driver,String Msg) throws Exception {
		String getMsg;
		try {
			Waits.fluentWaitIsEnabled(driver, alert_Verify(driver), 5);
			getMsg = alert_Verify(driver).getText().substring(1);
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
		}

	}
}
