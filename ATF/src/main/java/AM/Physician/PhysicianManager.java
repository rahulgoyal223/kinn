package AM.Physician;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.GlobalData;
import components.Report;
import components.Waits;

public class PhysicianManager {
	
	// To select Physician with firstname and lastname in the parameter
		public static void SelectPhysicianNameLink(WebDriver driver, String FirstName,
				String LastName) throws InterruptedException {

			String FullName = LastName.concat(",").concat(" ").concat(FirstName);
			boolean isPatientSelected = false;
			List<WebElement> tableContents = driver.findElements(By
					.xpath("//*[contains(@href,'/am/physician/physicianDetail.cfm?pk=')]"));
			for (WebElement tbl : tableContents) {
				String linkTxt = tbl.getText();
				if (linkTxt.trim().equals(FullName)) {
					tbl.click();
					isPatientSelected = true;
					break;
				}
			}
			if (isPatientSelected) {
				Report.Log(Status.PASS, "Patient - '" + FullName
						+ "' is selected");
				Assert.assertTrue(isPatientSelected, "Patient is selected");
			} else {
				Report.Log(Status.FAIL, "Patient - '" + FullName
						+ "' is NOT selected");
				Assert.assertFalse(false);
			}
		}
		
		
		public static void VerifyEditedValue(WebDriver driver, String name, String check) throws Exception {

	        try {
	            	if(name.equals(check)){
	            		Report.attachScreenShotToReport(driver);
		            	Report.Log(Status.PASS, "Edited value is present");       		
		            	Assert.assertTrue(true);
	            	}
	        }
	        	catch (Exception e) {
	        	e.printStackTrace();
	        	Report.Log(Status.FAIL, "Edited value is not visible");
	        	Assert.fail("Edited value is not present");
	        	}
	        }
		
		public static void VerifyPhysicianDeleted(WebDriver driver, String FirstName,
				String LastName) throws InterruptedException {

			String FullName = LastName.concat(",").concat(" ").concat(FirstName);
			List<WebElement> tableContents = driver.findElements(By
					.xpath("//*[contains(@href,'/am/physician/physicianDetail.cfm?pk=')]"));
			for (WebElement tbl : tableContents) {
				String linkTxt = tbl.getText();
				if (!linkTxt.trim().contains(FullName)) {
					Report.Log(Status.PASS, "Created Physician deleted successfully");
					Report.attachScreenShotToReport(driver);
	            	Assert.assertTrue(true);
					break;
				}
				else
				{
					Report.Log(Status.FAIL, "Created Physician is not deleted successfully");
		        	Assert.fail("Created Physician is not deleted successfully");
				}
			}
		}

}
