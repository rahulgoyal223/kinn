package KH.NewClinic;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Report;
import components.Waits;

	public class CreateClinic {
		static String strInputValue;
		private static WebElement element = null;
		private static Select list = null;
		
		public static void FillAddNewClinic(WebDriver driver) throws Exception {

	        try {
	        	Waits.ForBrowserLoad(driver);
	        	strInputValue = Datatable.GetValue("NC_ClinicType");
	        	if (!strInputValue.trim().isEmpty()) {
	        		lst_ClinicType(driver).selectByVisibleText(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_ClinicName");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_ClinicName(driver).clear();
	        		txt_ClinicName(driver).sendKeys(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_ClinicShortName");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_ClinicShortName(driver).clear();
	        		txt_ClinicShortName(driver).sendKeys(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_AddressOne");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_AddressOne(driver).clear();
	        		txt_AddressOne(driver).sendKeys(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_PrimaryCity");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_PrimaryCity(driver).clear();
	        		txt_PrimaryCity(driver).sendKeys(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_State");
	        	if (!strInputValue.trim().isEmpty()) {
	        		lst_State(driver).selectByVisibleText(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_ZipCode");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_ZipCode1(driver).clear();
	        		txt_ZipCode1(driver).sendKeys(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_AdminUser");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_AdminUserName(driver).clear();
	        		txt_AdminUserName(driver).sendKeys(strInputValue);
	        		GlobalData.setUserName(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_AdminPwd");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_AdminPassword(driver).clear();
	        		txt_AdminPassword(driver).sendKeys(strInputValue);
	        		GlobalData.setTemporaryPwd(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_TrainerUser");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_TrainerUserName(driver).clear();
	        		txt_TrainerUserName(driver).sendKeys(strInputValue);
	        		GlobalData.setTrainerName(strInputValue);
	            }
	        	
	        	strInputValue = Datatable.GetValue("NC_TrainerPwd");
	        	if (!strInputValue.trim().isEmpty()) {
	        		txt_TrainerPassword(driver).clear();
	        		txt_TrainerPassword(driver).sendKeys(strInputValue);
	        		GlobalData.setTrainerPwd(strInputValue);
	            }
	        	
	        	Report.Log(Status.PASS, "User details have been filled successfully");
        	} catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "User details have NOT been filled");
        }
    }
		// @New Agency Objects
		public static Select lst_ClinicType(WebDriver driver) {
			element = driver.findElement(By.name("ListClinicTypeKey"));
			list = new Select(driver.findElement(By.name("ListClinicTypeKey")));
			return list;
		}
		
		public static WebElement txt_ClinicName(WebDriver driver) {
			element = driver.findElement(By.name("ClinicName"));
			return element;
		}
		
		public static WebElement txt_ClinicShortName(WebDriver driver) {
			element = driver.findElement(By.name("BranchShortName"));
			return element;
		}
		
		public static WebElement txt_AddressOne(WebDriver driver) {
			element = driver.findElement(By.name("PrimaryAddressOne"));
			return element;
		}

		public static WebElement txt_PrimaryCity(WebDriver driver) {
			element = driver.findElement(By.name("PrimaryCity"));
			return element;
		}
		
		public static Select lst_State(WebDriver driver) {
			element = driver.findElement(By.name("PrimaryState"));
			list = new Select(driver.findElement(By.name("PrimaryState")));
			return list;
		}
		
		public static WebElement txt_ZipCode1(WebDriver driver) {
			element = driver.findElement(By.name("PrimaryZIPCode"));
			return element;
		}
		
		public static WebElement txt_AdminUserName(WebDriver driver) {
			element = driver.findElement(By.name("UserName"));
			return element;
		}
		
		public static WebElement txt_AdminPassword(WebDriver driver) {
			element = driver.findElement(By.name("AdminPassword"));
			return element;
		}
		
		public static WebElement txt_TrainerUserName(WebDriver driver) {
			element = driver.findElement(By.name("TrainerUserName"));
			return element;
		}
		
		public static WebElement txt_TrainerPassword(WebDriver driver) {
			element = driver.findElement(By.name("TrainerPassword"));
			return element;
		}
		
		public static WebElement btn_Create_Agency(WebDriver driver) {
			element = driver.findElement(By.id("CreateAgency"));
			return element;
		}
		
}
