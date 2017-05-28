package KH.Admission;

import DataSource.Datatable;
import DataSource.GlobalData;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import components.Events;
import components.Report;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EditAdmission {

	static String strInputValue;
	private static WebElement element = null;
	private static Select list = null;


	//@ Fill Insurance Information required for Room and Board admission.
	public static void FillInsuranceInformationRoomAndBoard(WebDriver driver) throws Exception {

		try {

			strInputValue = Datatable.GetValue("II_Insurance");
			if (!strInputValue.trim().isEmpty()) {
				lst_II_Insurance(driver).selectByVisibleText(strInputValue);
			}

			strInputValue = Datatable.GetValue("II_MedicareNumber");
			if (!strInputValue.trim().isEmpty()) {
				txt_II_MedicareNumber(driver).clear();
				txt_II_MedicareNumber(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("II_MedicaidNumber");
			if (!strInputValue.trim().isEmpty()) {
				txt_II_MedicaidNumber(driver).clear();
				txt_II_MedicaidNumber(driver).sendKeys(strInputValue);
			}

			// Select the first facility in the list.
			lst_II_MedicaidRoomAndBoardFacility(driver).selectByValue("0");

			strInputValue = Datatable.GetValue("II_RUGRate");
			if (!strInputValue.trim().isEmpty()) {
				lst_II_RUGRate(driver).selectByVisibleText(strInputValue);
			}

			strInputValue = Datatable.GetValue("II_StartOfCareMedicaidRoomAndBoard");
			if (!strInputValue.trim().isEmpty()) {
				dt_II_StartOfCareMedicaidRoomAndBoard(driver).clear();
				dt_II_StartOfCareMedicaidRoomAndBoard(driver).sendKeys(strInputValue);
			}

			strInputValue = Datatable.GetValue("II_EndOfCareMedicaidRoomAndBoard");
			if (!strInputValue.trim().isEmpty()) {
				dt_II_EndOfCareMedicaidRoomAndBoard(driver).clear();
				dt_II_EndOfCareMedicaidRoomAndBoard(driver).sendKeys(strInputValue);
			}

		}
		catch (Exception e){
			e.printStackTrace();
			Report.Log(Status.FAIL, "Patient details have NOT been filled");
		}

		return;
	}


	//@ Edit Admission Screen test objects

	//@ Insurance Information
	public static Select lst_II_Insurance(WebDriver driver) {
		element = driver.findElement(By.id("patientInsurance"));
		list = new Select(driver.findElement(By.id("patientInsurance")));
		return list;
	}

	public static WebElement txt_II_MedicareNumber(WebDriver driver) {
		element = driver.findElement(By.id("policyNumber"));
		return element;
	}

	public static WebElement txt_II_MedicaidNumber(WebDriver driver) {
		element = driver.findElement(By.id("medicaidNumber"));
		return element;
	}

	public static Select lst_II_MedicaidRoomAndBoardFacility(WebDriver driver) {
		element = driver.findElement(By.id("facilityList-0"));
		list = new Select(element);
		return list;
	}

	public static Select lst_II_RUGRate(WebDriver driver) {
		element = driver.findElement(By.id("RUGRates-0"));
		list = new Select(element);
		return list;
	}

	public static WebElement dt_II_StartOfCareMedicaidRoomAndBoard(WebDriver driver) {
		element = driver.findElement(By.id("roomAndBoardStartDate-0"));
		return element;
	}

	public static WebElement dt_II_EndOfCareMedicaidRoomAndBoard(WebDriver driver) {
		element = driver.findElement(By.id("roomAndBoardEndDate-0"));
		return element;
	}

	//@Method to add and verify attachment
	public static void addAndVerifyAttachment(WebDriver driver,String path){

		try{
			btn_AddAttachment(driver).sendKeys(path);	
			String attachment=driver.findElement(By.xpath("//*[contains(@href,'/EHR/services/Attachment/AttachmentService.cfc?method')]")).getText().trim();
			//System.out.println("Added Attachment : " +attachment);
			Assert.assertEquals(attachment, "TestAttachment.txt","Attachment was not uploaded successfully");
		}
		catch(Exception e){
			e.printStackTrace();
			Report.Log(Status.FAIL, "Unable to upload attachment in View-->Admission Summary");
		}
	}

	//@Add Attachment
	public static WebElement btn_AddAttachment(WebDriver driver){
		element=driver.findElement(By.id("files"));
		return element;
	}


	//@ Save and Submit
	public static WebElement btn_BP_SaveAndSubmit(WebDriver driver) {
		element = driver.findElement(By.id("submitBtn"));
		return element;
	}

}
