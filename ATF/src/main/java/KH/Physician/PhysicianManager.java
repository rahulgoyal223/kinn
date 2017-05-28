package KH.Physician;

	
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.testng.Assert;
	import com.aventstack.extentreports.Status;	
	import components.Report;
	
	public class PhysicianManager {
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
				try {
				String FullName = LastName.concat(",").concat(" ").concat(FirstName);
				List<WebElement> tableContents = driver.findElements(By
						.xpath("//*[@id='AMContainer']/div[6]/table[2]/tbody/tr/td[2]/label"));
				int count = tableContents.size();
				String Text = tableContents.get(count-1).getText();
					if (!Text.trim().equals(FullName)) {
						Report.Log(Status.PASS, "Created Physician deleted successfully");
						Report.attachScreenShotToReport(driver);
		            	Assert.assertTrue(true);
					}
					else if (Text.trim().equals(FullName)){
					Report.Log(Status.FAIL, "Created Physician is not deleted successfully");
					}
							
				}
				catch (Exception e) {
		        	e.printStackTrace();
					Report.Log(Status.FAIL, "Created Physician is not deleted successfully");
			      	Assert.fail("Created Physician is not deleted successfully");
					
				}
			}
			
			public static void SelectPhysicianEditLink(WebDriver driver) throws InterruptedException {

				WebElement webtable=driver.findElement(By.xpath("//*[@id='AMContainer']/div[6]/table[2]/tbody/tr")); 

				List <WebElement> rowCollection=webtable.findElements(By.xpath("//*[@id='AMContainer']/div[6]/table[2]/tbody/tr/td"));
				int count = rowCollection.size();
				rowCollection.get(count-1).click();
			}

}
