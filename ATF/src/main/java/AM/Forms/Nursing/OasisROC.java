package AM.Forms.Nursing;

import com.aventstack.extentreports.Status;

import DataSource.Datatable;
import components.Events;
import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class OasisROC {

	private static WebElement element = null;
	private static String strInputValue;
	private static Select list = null;

	// @Method To select the Form
	public static WebElement SelectForm(WebDriver driver, String formname)
			throws Exception {

		try {
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			for (WebElement row : allrows) {
				String linkTxt = row.getText();
				if (linkTxt.trim().contains(formname)) {
					row.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	// @Method To fill the Demographics form
	public static void FillDemographicForm(WebDriver driver) throws Exception {

		strInputValue = Datatable.GetValue("OA_cTO_timein");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeIn(driver).clear();
			txt_DG_TimeIn(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_timeout");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_TimeOut(driver).clear();
			txt_DG_TimeOut(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_cTO_visitdate");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_VisitDate(driver).clear();
			txt_DG_VisitDate(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_associatedMileage");
		if (!strInputValue.trim().isEmpty()) {
			txt_DG_AssociatedMileage(driver).clear();
			txt_DG_AssociatedMileage(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_dt_DG_M0090INFOCOMPLETEDDT");
		if (!strInputValue.trim().isEmpty()) {
			dt_DG_M0090INFOCOMPLETEDDT(driver).clear();
			dt_DG_M0090INFOCOMPLETEDDT(driver).sendKeys(strInputValue);
		}

		strInputValue = Datatable.GetValue("OA_M0032_ROC_DT_NA");
		if (!strInputValue.trim().isEmpty()
				&& strInputValue.trim().equals("Yes")) {
			chk_DG_ResumptionOfCareDate(driver).click();

		}

		strInputValue = Datatable.GetValue("OA_M0102_PHYSN_ORDRD_SOCROC_DT");
		if (!strInputValue.trim().isEmpty()) {
			dt_DG_M0102DateOfPhysician(driver).clear();
			dt_DG_M0102DateOfPhysician(driver).sendKeys(strInputValue);

		}
		strInputValue = Datatable.GetValue("OA_M0110_02");
		if (!strInputValue.trim().isEmpty()) {
			rbn_DG_M0110_Later(driver).click();
		}

		strInputValue = Datatable.GetValue("OA_M0140_ETHNIC_AI_AN");
		if (!strInputValue.trim().isEmpty()) {
			chk_DG_M0140_RaceEthnicity(driver).click();

		}

	}
	
	//@Method to fill Patient Diagnosis
	public static void FillPatientDiagnosisForm(WebDriver driver) throws Exception {
		strInputValue = Datatable.GetValue("OA_M1021_PatientDiagnosis");
		if (!strInputValue.trim().isEmpty()) {
			txt_PD_M1021_PrimaryDiagnosis(driver).clear();
			txt_PD_M1021_PrimaryDiagnosis(driver).sendKeys(strInputValue);
		}
		res_PD_M1021_PrimaryDiagnosis(driver).click();
		

		strInputValue = Datatable.GetValue("OA_M1021_OE");
		if (!strInputValue.trim().isEmpty()) {
			lst_PD_M1021_OE(driver).selectByVisibleText(strInputValue);
		}
		
		strInputValue = Datatable.GetValue("OA_M1021_OEDate");
		if (!strInputValue.trim().isEmpty()) {
			Events.Fire(driver).moveToElement(dt_PD_M1021_OEDate(driver))
					.click().perform();
			dt_PD_M1021_OEDate(driver).clear();
			dt_PD_M1021_OEDate(driver).sendKeys(strInputValue);
		}
	}
	
	//@Method to fill Therapy Need
	public static void FillTherapyNeedForm(WebDriver driver) throws Exception {
		strInputValue = Datatable.GetValue("OA_TN_M2200");
		if(!strInputValue.trim().isEmpty()) {
			txt_TN_M2200_TherapyNeed(driver).clear();
			txt_TN_M2200_TherapyNeed(driver).sendKeys(strInputValue);
		}
	}
	

	public static void saveAlertWindow(WebDriver driver)
			throws InterruptedException {
		WebElement element = driver.findElement(By.id("saveWindowText"));
		Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForBrowserLoad(driver);
		for (int i = 0; i <= 40; i++) {
			if (element.isDisplayed()
					&& element.getText().trim().equals("Page has been saved!")) {
				driver.navigate().refresh();
				break;
			}
		}
		driver.navigate().refresh();
	}

	// @ Objects for Oasis check
	public static WebElement chk_OC_ReturnToClinicianSignature(WebDriver driver) {
		element = driver.findElement(By.id("signatureRequired"));
		return element;
	}

	public static WebElement txt_OC_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement dt_OC_SignatureDate(WebDriver driver) {
		element = driver.findElement(By.name("signDate"));
		return element;
	}

	// @ OasisROC test objects
	// @ Demographics Section
	public static WebElement txt_DG_TimeIn(WebDriver driver) {
		element = driver.findElement(By.id("cTO_timein"));
		return element;
	}

	public static WebElement txt_DG_TimeOut(WebDriver driver) {
		element = driver.findElement(By.id("cTO_timeout"));
		return element;
	}

	public static WebElement txt_DG_VisitDate(WebDriver driver) {
		element = driver.findElement(By.id("cTO_visitdate"));
		return element;
	}

	public static WebElement txt_DG_AssociatedMileage(WebDriver driver) {
		element = driver.findElement(By.id("associatedMileage"));
		return element;
	}

	public static WebElement dt_DG_ResumptionOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("M0032_ROC_DT"));
		return element;
	}

	public static WebElement dt_DG_M0090INFOCOMPLETEDDT(WebDriver driver) {
		element = driver.findElement(By.id("M0090_INFO_COMPLETED_DT"));
		return element;
	}

	public static WebElement chk_DG_ResumptionOfCareDate(WebDriver driver) {
		element = driver.findElement(By.id("M0032_ROC_DT_NA"));
		return element;
	}

	public static WebElement dt_DG_M0102DateOfPhysician(WebDriver driver) {
		element = driver.findElement(By.id("M0102_PHYSN_ORDRD_SOCROC_DT"));
		return element;
	}

	public static WebElement dt_DG_M0104DateOfPhysician(WebDriver driver) {
		element = driver.findElement(By.id("M0104_PHYSN_RFRL_DT"));
		return element;
	}

	public static WebElement rbn_DG_M0110_Later(WebDriver driver) {
		element = driver.findElement(By.id("M0110_02"));
		return element;
	}

	public static WebElement chk_DG_M0140_RaceEthnicity(WebDriver driver) {
		element = driver.findElement(By.id("M0140_ETHNIC_AI_AN"));
		return element;
	}

	
	//Patient History/Diagnosis
	public static WebElement txt_PD_M1021_PrimaryDiagnosis(WebDriver driver) {
		element = driver.findElement(By.id("M1021_Diagnosis"));
		return element;
	}
	
	public static WebElement res_PD_M1021_PrimaryDiagnosis(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='noBorder']/tbody/tr[2]/td[2]/ul/li[2]/k-icd-lookup/div[2]/div[1]/div[1]/div[1]"));
		return element;
	}
	
	public static Select lst_PD_M1021_OE(WebDriver driver) {
		element = driver.findElement(By.id("M1021_OE"));
		list = new Select(driver.findElement(By.id("M1021_OE")));
		return list;
	}
	
	public static WebElement dt_PD_M1021_OEDate(WebDriver driver) {
		element = driver.findElement(By.id("M1021_Onset"));
		return element;
	}
	
	//@Integumentary Status Section
	
	public static WebElement btn_AddWound(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@class='btn add-wounds']"));
		return element;
	}	

	//@Therapy Need and POC Section
	public static WebElement txt_TN_M2200_TherapyNeed(WebDriver driver) {
		element = driver.findElement(By.id("M2200_THER_NEED_NUM"));
		return element;
	}
	
	public static WebElement btn_DG_Save(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("oasisSaveButton"));
		return element;
	}

	// For Oasis Form approval
	public static WebElement txt_ElectronicSignature(WebDriver driver) {
		element = driver.findElement(By.id("clinicianSignature"));
		return element;
	}

	public static WebElement btn_Submit(WebDriver driver) {
		element = driver.findElement(By.name("whattodo"));
		return element;
	}

	public static WebElement btn_Approve(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Approve']"));
		return element;
	}
	
	public static void VerifyM1028Options(WebDriver driver) {
		
		String expectedOptions[] = {
				"0   No",
				"1   Yes",				
				"-   Not assessed (no information)"
		};
		List<WebElement> rowsPVD = driver.findElements(By.cssSelector("input[id^='M1028_ACTV_DIAG_PVD_PAD_C2_']+label"));
		
		if( expectedOptions.length != rowsPVD.size() ){
			Report.Log(
					Status.FAIL, 
					"M1028 option for PVD " + String.valueOf(expectedOptions.length) + " options. " + 
					"Only " + String.valueOf(rowsPVD.size()) + " options were found"
					);
			Assert.fail("M1028 for PVD missing options.");
		} else {
			for( Iterator<WebElement> i = rowsPVD.iterator(); i.hasNext();){
				WebElement row = i.next();
				int position = rowsPVD.indexOf(row);
				boolean pass = row.getText().trim().equals( expectedOptions[position].trim() );
						
				Report.Log(
						pass ? Status.PASS : Status.ERROR, 
						"M1028 option for PVD " + String.valueOf(position+1) 
							+ " is <pre>" + row.getText() + "</pre> " 
							+ ( 
								!pass 
									? " expected was <pre>" + expectedOptions[position] + "</pre>." 
									: ""
							)
						);
			}
		}
		List<WebElement> rowsDM = driver.findElements(By.cssSelector("input[id^='M1028_ACTV_DIAG_DM_C2_']+label"));
		
		if( expectedOptions.length != rowsDM.size() ){
			Report.Log(
					Status.FAIL, 
					"M1028 for DM does not contain " + String.valueOf(expectedOptions.length) + " options. " + 
					"Only " + String.valueOf(rowsDM.size()) + " options were found"
					);
			Assert.fail("M1028 missing options.");
		} else {
			for( Iterator<WebElement> i = rowsDM.iterator(); i.hasNext();){
				WebElement row = i.next();
				int position = rowsDM.indexOf(row);
				boolean pass = row.getText().trim().equals( expectedOptions[position].trim() );
						
				Report.Log(
						pass ? Status.PASS : Status.ERROR, 
						"M1028 option for DM" + String.valueOf(position+1) 
							+ " is <pre>" + row.getText() + "</pre> " 
							+ ( 
								!pass 
									? " expected was <pre>" + expectedOptions[position] + "</pre>." 
									: ""
							)
						);
			}
		}
		}
		public static void VerifyM1060NotAssessed(WebDriver driver) {		
			String expectedOptions[] = {						
					"-   Not assessed (no information)"
			};
			List<WebElement> rowHeight = driver.findElements(By.cssSelector("label[for='M1060_HEIGHT_NOT_ASSESSED']"));
			
			if( expectedOptions.length != rowHeight.size() ){
				Report.Log(
						Status.FAIL, 
						"M1060 option for Height " + String.valueOf(expectedOptions.length) + " option. " + 
						"Only " + String.valueOf(rowHeight.size()) + " option was found"
						);
				Assert.fail("M1060 for Height missing options.");
			} else {
				for( Iterator<WebElement> i = rowHeight.iterator(); i.hasNext();){
					WebElement row = i.next();
					int position = rowHeight.indexOf(row);
					boolean pass = row.getText().trim().equals( expectedOptions[position].trim() );
							
					Report.Log(
							pass ? Status.PASS : Status.ERROR, 
							"M1060 option for Height " + String.valueOf(position+1) 
								+ " is <pre>" + row.getText() + "</pre> " 
								+ ( 
									!pass 
										? " expected was <pre>" + expectedOptions[position] + "</pre>." 
										: ""
								)
							);
				}
			}
		Report.attachScreenShotToReport(driver);
	}	
	
		public static void VerifyM1060DefaultValue(WebDriver driver) {		
	       if(driver.findElement(By.cssSelector("input[id='M1060_HEIGHT_NOT_ASSESSED']")).isSelected()==false)
	       {
	     	   Report.Log(
	  					Status.PASS, 
	  					" In M1060 section, for Height; '- Not assessed (no information)' is unchecked  by default as expected"
	  					);
	         }else {
	      	   Report.Log(
	     			   Status.FAIL,
	     			   " In M1060 section, for Height; '- Not assessed (no information)'  is not unchecked by default"
	  					); 
	        }	      
	        //@Step to check default value in " M1060 Weight  "
	        if(driver.findElement(By.cssSelector("input[id='M1060_WEIGHT_NOT_ASSESSED']")).isSelected()==false)
	       {
	    	   Report.Log(
	  					Status.PASS, 
	  					" In M1060 section, for Weight ; '- Not assessed (no information)' is unchecked  by default as expected"
	  					);
	         }else {
	      	   Report.Log(
	     			   Status.FAIL,
	     			   " In M1060 section, for Weight ; '- Not assessed (no information)'  is not unchecked by default"
	  					); 
	        }
	        Report.attachScreenShotToReport(driver);
		}
	}
