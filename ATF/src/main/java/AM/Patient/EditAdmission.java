package AM.Patient;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

	import DataSource.Datatable;
import DataSource.GlobalData;
import components.Waits;

	public class EditAdmission {
		public static Select lst_AdmissionSource(WebDriver driver) {
			WebElement element = driver.findElement(By.id("listReferralTypeKey"));
	        Select list = new Select(element);
	        Waits.ForElementVisibility(driver, element);
	        return list;
	        }
		public static WebElement btn_Save(WebDriver driver) {
			WebElement element = driver.findElement(By.id("submitBtn"));	
			Waits.ForElementVisibility(driver, element);
			return element;
		}
		public static Select lst_AdmissionType(WebDriver driver) {
			WebElement element = driver.findElement(By.id("admissionType"));
	        Select list = new Select(element);
	        Waits.ForElementVisibility(driver, element);
	        return list;
	        }
//		public static WebElement btn_Save(WebDriver driver) {
//			WebElement element = driver.findElement(By.id("submitBtn"));	
//			Waits.ForElementVisibility(driver, element);
//			return element;
		
//		public static WebElement txt_EE_DischargeDate(WebDriver driver)   {
//			element = driver.findElement(By.name("dischargeDate"));
//			return element;
		
		public static WebElement txt_EE_DischargeDate(WebDriver driver) {
			WebElement element = driver.findElement(By.id("dischargeDate"));
			return element;
	}
		public static Select lst_DischargeStatus(WebDriver driver) {
			WebElement element = driver.findElement(By.id("admissionDischargeStatus"));
	        Select list = new Select(element);
	        Waits.ForElementVisibility(driver, element);
	        return list;
	        }
	
		}
		
		
	
	

