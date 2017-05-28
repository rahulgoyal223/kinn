package KH.Physician;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Events;
import components.Report;
import components.TimeDate;
import components.Waits;

public class AddNewPhysician {
	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	
	public static void FillAddNewPhysician(WebDriver driver) throws Exception {

        try {
            strInputValue = Datatable.GetValue("PD_LastName");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_LastName(driver).clear();
            	txt_AP_LastName(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianLastName(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_FirstName");
            if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
            	strInputValue = "A" + TimeDate.getUniqueInteger();
            	txt_AP_FirstName(driver).clear();
            	txt_AP_FirstName(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianFirstName(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_MiddleInitial");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_Suffix(driver).clear();
            	txt_AP_Suffix(driver).sendKeys(strInputValue);
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
            	txt_AP_PhysicianLicenseNum(driver).clear();
            	txt_AP_PhysicianLicenseNum(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianLicense(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Physician_Expiration");
            if (!strInputValue.trim().isEmpty()) {
            	Events.Fire(driver).moveToElement(dt_AP_LicenseExpiration(driver)).click().perform();
            	dt_AP_LicenseExpiration(driver).clear();
            	dt_AP_LicenseExpiration(driver).sendKeys(strInputValue);
                GlobalData.setPhysicianExpiration(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Community_Care");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_CommunityCareNum(driver).clear();
            	txt_AP_CommunityCareNum(driver).sendKeys(strInputValue);
                GlobalData.setCommunityCare(strInputValue);
            }
            
            strInputValue = Datatable.GetValue("PD_Address");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_Address(driver).clear();
            	txt_AP_Address(driver).sendKeys(strInputValue);
            }

            strInputValue = Datatable.GetValue("PD_ZIPCode");
            if (!strInputValue.trim().isEmpty()) {
            	txt_AP_ZipCode(driver).clear();
                Events.Fire(driver).moveToElement(txt_AP_ZipCode(driver)).click().perform();
                txt_AP_ZipCode(driver).clear();
                txt_AP_ZipCode(driver).sendKeys(strInputValue);
            }
            
            Report.Log(Status.PASS, "Physician details have been filled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "Physician details have NOT been filled");
        }
    }

	// @Add Physician Objects
	public static WebElement txt_AP_LastName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianLastName"));
		return element;
	}

	public static WebElement txt_AP_FirstName(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianFirstName"));
		return element;
	}

	public static WebElement txt_AP_Suffix(WebDriver driver) {
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

	public static WebElement txt_AP_PhysicianLicenseNum(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianStateID"));
		return element;
	}

	public static WebElement dt_AP_LicenseExpiration(WebDriver driver) {
		element = driver.findElement(By.name("LicenseExpiration"));
		return element;
	}

	public static WebElement txt_AP_CommunityCareNum(WebDriver driver) {
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

	public static WebElement txt_AP_ZipCode(WebDriver driver) {
		element = driver.findElement(By.id("PhysicianZIPCode"));
		return element;
	}

	public static WebElement txt_AP_ZipCodePlus4(WebDriver driver) {
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

	public static WebElement txt_AP_Phone1(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonea"));
		return element;
	}

	public static WebElement txt_AP_Phone2(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphoneb"));
		return element;
	}

	public static WebElement txt_AP_Phone3(WebDriver driver) {
		element = driver.findElement(By.id("Physicianphonec"));
		return element;
	}

	public static WebElement txt_AP_Fax1(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimilea"));
		return element;
	}

	public static WebElement txt_AP_Fax2(WebDriver driver) {
		element = driver.findElement(By.id("Physicianfacsimileb"));
		return element;
	}

	public static WebElement txt_AP_Fax3(WebDriver driver) {
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

	public static WebElement txt_AP_AlternateAddress(WebDriver driver) {
		element = driver.findElement(By.id("altAddr"));
		return element;
	}

	public static WebElement txt_AP_Comments(WebDriver driver) {
		element = driver.findElement(By.name("PhysicianComment"));
		return element;
	}

	public static WebElement btn_AP_AddPhysician(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		return element;
	}
	
	public static WebElement btn_DeletePhysician(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='PhysicianForm']/div[2]/div[6]/form/input[2]"));
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
