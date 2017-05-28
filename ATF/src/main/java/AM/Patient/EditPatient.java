package AM.Patient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Waits;

public class EditPatient {
	private static WebDriver driver;
	static String strInputValue;
	private static WebElement element = null;
	
	public static void EditPatientDetails(WebDriver driver) throws Exception {
	  strInputValue = Datatable.GetValue("PD_LastName");
      if (!strInputValue.trim().isEmpty()) {
          txt_PD_LastName(driver).clear();
          txt_PD_LastName(driver).sendKeys(strInputValue);
          GlobalData.setPatientLastName(strInputValue);
      }
	}
      
    public static WebElement txt_PD_LastName(WebDriver driver) {
          element = driver.findElement(By.id("Lastname"));
          Waits.ForElementVisibility(driver, element);
          return element;
      }
	public static void EditPatientAddress(WebDriver driver,String Address) throws Exception {
		  strInputValue =Address;
	      if (!strInputValue.trim().isEmpty()) {
	          txt_PD_Address(driver).clear();
	          txt_PD_Address(driver).sendKeys(strInputValue);
	          GlobalData.setPatientLastName(strInputValue);
	      }
		}
	      
	    public static WebElement txt_PD_Address(WebDriver driver) {
	          element = driver.findElement(By.id("AddressOne"));
	          Waits.ForElementVisibility(driver, element);
	          return element;
	      }

	    public static  Select EditPatientGender(WebDriver driver)   {
	    	element = driver.findElement(By.id("Gender"));
	    	Select list = new Select(element);
	    	Waits.ForElementVisibility(driver, element);
	    	return list;
			
		}

		public static void EditPatientZip(WebDriver driver, String Zip) throws Exception {
			  strInputValue =Zip;
		      if (!strInputValue.trim().isEmpty()) {
		          txt_PD_ZipCode(driver).clear();
		          txt_PD_ZipCode(driver).sendKeys(strInputValue);
		          GlobalData.setPatientLastName(strInputValue);
		      }

		
		}

		private static WebElement txt_PD_ZipCode(WebDriver driver) {
	          element = driver.findElement(By.id("ZIPCode"));
	          Waits.ForElementVisibility(driver, element);
	          return element;
		}

		public static void EditPatientPharmacy(WebDriver driver, String Pharmacy) throws Exception {
			  strInputValue =Pharmacy;
		      if (!strInputValue.trim().isEmpty()) {
		          txt_PD_Pharmacy(driver).clear();
		          txt_PD_Pharmacy(driver).sendKeys(strInputValue);
		          GlobalData.setPatientLastName(strInputValue);
			
		      }
		}
		      private static WebElement txt_PD_Pharmacy(WebDriver driver) {
		          element = driver.findElement(By.id("PharmacyName"));
		          Waits.ForElementVisibility(driver, element);
		          return element;  
		      }

			public static void EmergencyContact(WebDriver driver, String ContactName) throws Exception {
				  strInputValue =ContactName;
			      if (!strInputValue.trim().isEmpty()) {
			          txt_PD_ContactName(driver).clear();
			          txt_PD_ContactName(driver).sendKeys(strInputValue);
			          GlobalData.setPatientLastName(strInputValue);
			      }       
			}
			
		      private static WebElement txt_PD_ContactName(WebDriver driver) {
		          element = driver.findElement(By.id("ContactName"));
		          Waits.ForElementVisibility(driver, element);
		          return element;  
		      }

			public static void ContactPhone(WebDriver driver, String ContactPhone) throws Exception {
				  strInputValue =ContactPhone;
			      if (!strInputValue.trim().isEmpty()) {
			          txt_PD_ContactPhone(driver).clear();
			          txt_PD_ContactPhone(driver).sendKeys(strInputValue);
			          GlobalData.setPatientLastName(strInputValue);
			      }       
			}
			
		      private static WebElement txt_PD_ContactPhone(WebDriver driver) {
		          element = driver.findElement(By.id("contactphonea"));
		          Waits.ForElementVisibility(driver, element);
		          return element;  
		      				
			}

		}

			


