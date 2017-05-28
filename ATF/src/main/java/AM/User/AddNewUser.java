package AM.User;

import components.Report;
import components.TimeDate;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import DataSource.GlobalData;
import org.openqa.selenium.interactions.Actions;

public class AddNewUser {

	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;
	
	
	public static void FillAddNewUser(WebDriver driver) throws Exception {

        try {
        	Waits.ForBrowserLoad(driver);
        	strInputValue = Datatable.GetValue("US_LastName");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_U_LastName(driver).clear();
        		txt_U_LastName(driver).sendKeys(strInputValue);
                GlobalData.setLastName(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_FirstName");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_U_FirstName(driver).clear();
        		txt_U_FirstName(driver).sendKeys(strInputValue);
                GlobalData.setFirstName(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_UserType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_U_UserType(driver).selectByVisibleText(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_PreferredUserName");
        	if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
            	strInputValue = "A" + TimeDate.getUniqueInteger();
        		txt_U_UserName(driver).clear();
        		txt_U_UserName(driver).sendKeys(strInputValue);
                GlobalData.setUserName(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_TemporaryPassword");
        	if (!strInputValue.trim().isEmpty()) {
        		txt_U_NewTemporaryPassword(driver).clear();
        		txt_U_NewTemporaryPassword(driver).sendKeys(strInputValue);
        		GlobalData.setTemporaryPwd(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_EmailAddress");
        	if (strInputValue.trim().isEmpty() || strInputValue.trim().toLowerCase().equals("dynamicvalue")){
        		strInputValue = TimeDate.getUniqueInteger() + "@test.com";
        		txt_U_EmailAddress(driver).clear();
        		txt_U_EmailAddress(driver).sendKeys(strInputValue);
                GlobalData.setEmailAddress(strInputValue);
            }
        	
        	strInputValue = Datatable.GetValue("US_EmployeeType");
        	if (!strInputValue.trim().isEmpty()) {
        		lst_U_EmployeeType(driver).selectByVisibleText(strInputValue);
            }
        	
        	//Waits.ForGlobalAjaxLoader(driver);
        	
        	Report.Log(Status.PASS, "User details have been filled successfully");
        	} catch (Exception e) {
            e.printStackTrace();
            Report.Log(Status.FAIL, "User details have NOT been filled");
        }
    }

	// @ Test Objects for add new user screen
	// @ Objects new user
	public static WebElement txt_U_LastName(WebDriver driver) {
		element = driver.findElement(By.name("lastname"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_U_FirstName(WebDriver driver) {
		element = driver.findElement(By.name("firstname"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_U_Suffix(WebDriver driver) {
		element = driver.findElement(By.name("suffix"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_U_UserType(WebDriver driver) {
		element = driver.findElement(By.id("Discipline"));
		list = new Select(driver.findElement(By.id("Discipline")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_U_UserName(WebDriver driver) {
		element = driver.findElement(By.id("username"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static String txt_U_TemporaryPassword(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		Waits.ForElementVisibility(driver, element);
		String tempPassword = element.getAttribute("value");
		return tempPassword;
	}
	//@step To find the password element without returning existing password present
	public static WebElement txt_U_NewTemporaryPassword(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_U_PrimaryBranch(WebDriver driver) {
		element = driver.findElement(By.name("ClinicBranchKey"));
		list = new Select(driver.findElement(By.name("ClinicBranchKey")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement txt_U_AgencyEmployeeID(WebDriver driver) {
		element = driver.findElement(By.name("employeeID"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_U_EmailAddress(WebDriver driver) {
		element = driver.findElement(By.id("userEmail"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static Select lst_U_EmployeeType(WebDriver driver) {
		element = driver.findElement(By.id("EmployeeType"));
		list = new Select(driver.findElement(By.id("EmployeeType")));
		Waits.ForElementVisibility(driver, element);
		return list;
	}

	public static WebElement btn_U_CreateUser(WebDriver driver) {
		element = driver.findElement(By.id("createUserButton"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To click on Complete User Setup button
	public static WebElement btn_U_CompleteUserSetup(WebDriver driver) throws InterruptedException {
		element = driver.findElement(By.xpath("//input[@type='Submit']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To check the access manager check box
	public static WebElement chk_Access_Manager(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='16']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To check the Approve Authorization check box
	public static WebElement chk_Approve_Auth(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='76']"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To click on Update Roles button
	public static WebElement btn_U_UpdateRoles(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='CFForm_1']/div[4]/input"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To click Logout button
	public static WebElement btn_U_Logout(WebDriver driver) {
		element = driver.findElement(By.linkText("Log Out"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To locate the initial element after logging into the application
	public static WebElement find_After_Login(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='AMContainer']/div[3]/div[1]/ul/li[1]"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}
	
	//Step To locate the username tooltip
	public static WebElement tool_get_Username(WebDriver driver) {
		Actions builder=new Actions(driver);
		element = driver.findElement(By.xpath("//*[@id='insertuser']/ul[5]/li[2]/div/div[2]"));
		Waits.ForElementVisibility(driver, element);
		builder.moveToElement(element).perform();
		return element;
	}
	
	//Step To verify the login page
	 public static void verify_Login_Page(WebDriver driver) throws Exception {

	        try {
	        	Waits.ForGlobalAjaxLoader(driver);
	            String text = find_After_Login(driver).getText().trim();
	            String verify = "Your current (or temporary) password has expired; please update your password.";
	        	if(text.equals(verify)){
	        		Report.Log(Status.PASS, "Logged in Successfully"); 
	        		Assert.assertEquals(text, verify);
	        	}
	        }
	        	catch (Exception e) {
	        	e.printStackTrace();
	        	Report.Log(Status.FAIL, "Not Logged in Successfully");
	        	Assert.fail("Not Logged in Successfully with the created user");
	        	}
	        }
	 
	 //Step To verify the username tooltip
	 public static void verify_Valid_Username(WebDriver driver) throws Exception {

	        try {
	        	Waits.ForGlobalAjaxLoader(driver);
	        	String tooltip_msg = tool_get_Username(driver).getText().trim();
	        	String name = "* Valid user name";
	        	if(name.equals(tooltip_msg))
	        	{
	        		Report.Log(Status.PASS, "verified username");       		
	        	}
	        }
	        	catch (Exception e) {
	        	e.printStackTrace();
	        	Report.Log(Status.FAIL, "username not verified");
	        	}
	       	
	        	}

}
