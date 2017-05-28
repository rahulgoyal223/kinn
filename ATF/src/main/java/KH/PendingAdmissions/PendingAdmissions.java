package KH.PendingAdmissions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import components.Report;
import components.Waits;

public class PendingAdmissions {
	private static WebElement element = null;
	private static String pageTitle=null;

	//Pending Admissions Objects

	//To verify page title
	public static void verifyPageTitle(WebDriver driver){
		pageTitle=driver.getTitle();
		Assert.assertEquals("Pending Admissions | Kinnser Software", pageTitle);
	}	

	//To search Physician E_Referrals
	public static WebElement txt_Physician_EReferrals_Search(WebDriver driver) {
		element = driver.findElement(By.xpath("(//input[contains(@name,'gridQuery')])[1]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	//To search Pending Admissions
	public static WebElement txt_PendingAdmissions_Search(WebDriver driver) {
		element = driver.findElement(By.xpath("(//input[contains(@name,'gridQuery')])[3]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	//To verify patient name from pending admissions list
	public static WebElement patientNameInList(WebDriver driver){
		element=driver.findElement(By.xpath("//*[contains(@href,'/EHR/#/HM/admission/patient/')]"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}

	//Method to search and verify patient name in Pending Admissions
	public static void searchAndVerifyPatientName(WebDriver driver,String patientFullName){
		try{
			txt_PendingAdmissions_Search(driver).sendKeys(patientFullName);
			Report.attachScreenShotToReport(driver);
			String pName=patientNameInList(driver).getText().trim();
			Assert.assertEquals(patientFullName, pName, "Patient is not listed in Pending Admissions");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
