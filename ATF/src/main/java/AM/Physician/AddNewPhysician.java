package AM.Physician;

import components.Events;
import components.Report;
import components.TimeDate;
import components.Waits;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;

public class AddNewPhysician {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;

	
	public static void FillAddNewPhysician(WebDriver driver) throws Exception {
		Waits.ForElementToBeClickable(driver, txt_AP_PhysicianLastName(driver));
        try {
            strInputValue = Datatable.GetValue("PD_LastName");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_PhysicianLastName(driver).clear();
            	txt_AP_PhysicianLastName(driver).sendKeys(strInputValue);
                GlobalData.setPatientLastName(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_FirstName");
            if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
            	strInputValue = "A" + TimeDate.getUniqueInteger();
            	txt_AP_PhysicianFirstName(driver).clear();
            	txt_AP_PhysicianFirstName(driver).sendKeys(strInputValue);
                GlobalData.setPatientFirstName(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_MiddleInitial");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_PhysicianSuffix(driver).clear();
            	txt_AP_PhysicianSuffix(driver).sendKeys(strInputValue);
                GlobalData.setPatientMiddleInitial(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Physician_UPIN");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_PhysicianUPIN(driver).clear();
            	txt_AP_PhysicianUPIN(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianUpin(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Physician_NPI");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_PhysicianNPI(driver).clear();
            	txt_AP_PhysicianNPI(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianNpi(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Physician_License");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_PhysicianLicense(driver).clear();
            	txt_AP_PhysicianLicense(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianLicense(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Physician_Expiration");
            if (!strInputValue.trim().isEmpty()) {
            	Events.Fire(driver).moveToElement(txt_AP_PhysicianExpiration(driver)).click().perform();
            	txt_AP_PhysicianExpiration(driver).clear();
            	txt_AP_PhysicianExpiration(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianExpiration(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Community_Care");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_CommunityCareNumber(driver).clear();
            	txt_AP_CommunityCareNumber(driver).sendKeys(strInputValue);
                GlobalData.setCommunityCare(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Address");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_Address(driver).clear();
            	txt_AP_Address(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_ZIPCode");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_ZIPCode(driver).clear();
                Events.Fire(driver).moveToElement(txt_AP_ZIPCode(driver)).click().perform();
                txt_AP_ZIPCode(driver).clear();
                txt_AP_ZIPCode(driver).sendKeys(strInputValue);
            }
            
            Report.Log(Status.PASS, "Physician details have been filled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Physician details have NOT been filled");
            Assert.fail("Physician details have NOT been filled");
        }
    }
	
	public static void AddPhysician(WebDriver driver) {
		try{
			Waits.ForElementToBeClickable(driver, btn_AddPhysician(driver));
			btn_AddPhysician(driver).click();
			clk_btnYes(driver, "Yes");
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Physician was NOT added successfully");
			Assert.fail("Physician was NOT added successfully");
		}
	}
	
	// @ Test Objects for add new physician screen
	// @ Objects under Add Physician
	public static WebElement txt_AP_PhysicianLastName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianLastName"));
		return element;
	}

	public static WebElement txt_AP_PhysicianFirstName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianFirstName"));
		return element;
	}

	public static WebElement txt_AP_PhysicianSuffix(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianSuffix"));
		return element;
	}

	public static WebElement txt_AP_PhysicianUPIN(WebDriver driver) {
		element = driver.findElement(By.id("M0072"));
		return element;
	}

	public static WebElement txt_AP_PhysicianNPI(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianNPI"));
		return element;
	}

	public static WebElement txt_AP_PhysicianLicense(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianStateID"));
		return element;
	}

	public static WebElement txt_AP_PhysicianExpiration(WebDriver driver) {
		element = driver.findElement(By.name("LicenseExpiration"));
		return element;
	}

	public static WebElement txt_AP_CommunityCareNumber(WebDriver driver) {
		element = driver.findElement(By.id("CommunityCareID"));
		return element;
	}

	public static WebElement txt_AP_ContactPerson(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianContact"));
		return element;
	}

	public static WebElement txt_AP_Address(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianAddressOne"));
		return element;
	}

	public static WebElement txt_AP_ZIPCode(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianZIPCode"));
		return element;
	}

	public static WebElement txt_AP_ZIPCodePlus4(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianZIPCodePlus4"));
		return element;
	}

	public static WebElement txt_AP_City(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianCity"));
		return element;
	}

	public static WebElement txt_AP_State(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianState"));
		return element;
	}

	public static WebElement txt_AP_PhoneA(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonea"));
		return element;
	}

	public static WebElement txt_AP_PhoneB(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphoneb"));
		return element;
	}

	public static WebElement txt_AP_PhoneC(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonec"));
		return element;
	}

	public static WebElement txt_AP_FaxA(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimilea"));
		return element;
	}

	public static WebElement txt_AP_FaxB(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimileb"));
		return element;
	}

	public static WebElement txt_AP_FaxC(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimilec"));
		return element;
	}

	public static WebElement txt_AP_PhysicianContactEmail(WebDriver driver) {
		element = driver.findElement(By.id("physicianEmail"));
		return element;
	}

	public static WebElement chk_AP_ExternalReferral(WebDriver driver) {
		element = driver.findElement(By.id("ExternalReferral"));
		return element;
	}

	public static Select lst_AP_InternalReferralSource(WebDriver driver) {
		element = driver.findElement(By.id("fClinicUserKeyInternalReferral"));
		list = new Select(driver.findElement(By
				.id("fClinicUserKeyInternalReferral")));
		return list;
	}

	public static WebElement txt_AP_AlternateAddress(WebDriver driver) {
		element = driver.findElement(By.id("altAddr"));
		return element;
	}

	public static WebElement btn_AddPhysician(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		return element;
	}
	
	public static WebElement btn_DeletePhysician(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='PhysicianForm']/div[2]/div[8]/form/input[2]"));
		return element;
	}

	// Internal Liaison
	public static Select lst_AP_InternalLiaison(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='PhysicianForm']/div[2]/div[4]/ul/li/select"));
		list = new Select(driver.findElement(By
				.xpath("//*[@id='PhysicianForm']/div[2]/div[4]/ul/li/select")));
		return list;
	}

	// Comments
	public static WebElement txt_AP_Comments(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//*[@id='PhysicianForm']/div[2]/div[6]/ul/textarea"));
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
