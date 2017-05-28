package KH.Billing.Claims;

import com.aventstack.extentreports.Status;

import components.Report;
import components.Waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ClaimsManager {

	private static WebElement element;
	private static Select list;

	// @Methods in Claims manager page-Tabs
	
	
	// @Method to select patient in Ready creation page
	public static void RD_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}
	
	// @Method to select patient in Ready TO SEND page
	public static WebElement btn_RS_Sendmanually(WebDriver driver) {
        element = driver.findElement(By.id("claimsSendManually"));
        return element;
    }
	
	// @Method to select patient in pending approval page
	public static void PA_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}
	 
	
	   public static Select lst_PA_Payer(WebDriver driver) {
	        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
	        list = new Select(element);
	        Waits.ForElementVisibility(driver, element);
	        return list;
	    }
	   

	    public static WebElement txt_PA_Searchbox(WebDriver driver) throws InterruptedException {
	        element = driver.findElement(By.id("caFilter"));     
	        Waits.ForElementVisibility(driver, element);
	        return element;
	    }

	    public static WebElement btn_PA_Approve(WebDriver driver) {
	        element = driver.findElement(By.id("claimsApproval"));
	        return element;
	    }

	    // @Method to select patient in pending payment page
	    public static WebElement txt_PP_Searchbox(WebDriver driver) throws InterruptedException {
	        element = driver.findElement(By.id("csFilter"));    
	        return element;
	    }


	public static WebElement SelectPatient(WebDriver driver, String patient)
			throws Exception {

		try {
			Waits.ForBrowserLoad(driver);
			WebElement table = driver.findElement(By.tagName("table"));
			Waits.ForElementVisibility(driver, table);
			List<WebElement> allrows = table.findElements(By.tagName("tr"));
			for (WebElement row : allrows) {
				System.out.println(row.getText());
				if (row.getText().contains(patient)) {
					element = row.findElement(By
							.xpath(".//input[@type = 'checkbox']"));
					Waits.ForElementVisibility(driver, element);
					element.click();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static void clk_btnYes(WebDriver driver, String option) {
		try {
			List<WebElement> btn = driver.findElements(By
					.xpath("//*[@type='button']"));
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

	public static void clk_btnNo(WebDriver driver, String option) {
		try {
			List<WebElement> btn = driver.findElements(By
					.xpath("//*[@type='button']"));
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


    public static Select lst_RE_ClaimAge(WebDriver driver) {
        element = driver.findElement(By.id("dayRange"));
        list = new Select(element);
        Waits.ForElementVisibility(driver, element);
        return list;
    }


  
	// @Objects for Not Ready tab
	public static Select lst_NR_Payer(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By
				.xpath("//select[@ng-model ='insuranceKey']"));
		list = new Select(element);
		Thread.sleep(Waits.getSleepLevelFive());
		Thread.sleep(Waits.getSleepLevelFive());
		Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForElementVisibility(driver, element);
		Waits.ForBrowserLoad(driver);
		return list;
	}

	public static Select lst_NR_ClaimAge(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("dayRange"));
		list = new Select(element);
		Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForElementVisibility(driver, element);
		Waits.ForBrowserLoad(driver);
		return list;
	}


	// @Objects for Ready Tab
	
	public static Select lst_RD_Payer(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By
				.xpath("//select[@ng-model ='insuranceKey']"));
		list = new Select(element);
		Waits.ForElementVisibility(driver, element);
		Thread.sleep(Waits.getSleepLevelFive());
		Thread.sleep(Waits.getSleepLevelFive());
		Report.Log(Status.INFO, "Acted on lst_R_Payer");
		return list;
	}

	public static Select lst_RD_ClaimAge(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("dayRange"));
		list = new Select(element);
		Waits.ForElementVisibility(driver, element);
		Thread.sleep(Waits.getSleepLevelFive());
		Waits.ForBrowserLoad(driver);
		Report.Log(Status.INFO, "Acted on lst_R_ClaimAge");
		return list;
	}
	

	public static WebElement btn_RD_Createclaim(WebDriver driver) {
		element = driver.findElement(By.id("claimsCreation"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}

	public static WebElement txt_RD_Searchbox(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("ccFilter"));
		/*Waits.ForElementVisibility(driver, element);
		Waits.ForBrowserLoad(driver);*/
		Report.Log(Status.INFO, "Acted on txt_R_Searchbox");
		return element;
	}
	
	public static WebElement txt_TD_Searchbox(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.id("caFilter"));
		Report.Log(Status.INFO, "Acted on txt_R_Searchbox");
		return element;
	}

	
	//@Method to select item from table	
	public static void VerifyAlertmessage(WebDriver driver,String Msg) throws Exception {
		String getMsg;
		try {
			Waits.fluentWaitIsEnabled(driver, Alt_PA_Successfullmsg(driver), 5);
			getMsg = Alt_R_Successfullmsg(driver).getText();
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
		}

	}

	public static void VerifyClaimsAlertmessage(WebDriver driver, String Msg)
			throws Exception {
		String getMsg;
		try {
			Thread.sleep(Waits.getSleepLevelFive());
			Thread.sleep(Waits.getSleepLevelFive());
			getMsg = Alt_PA_Successfullmsg(driver).getText();
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
		}
	}

	


	public static WebElement Alt_R_Successfullmsg(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//*[(contains(@ng-bind-html-unsafe,'alert.message') and contains(@class,'ng-binding'))]"));
		return element;
	}

	public static WebElement Alt_PA_Successfullmsg(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='alert']/span"));
		Waits.ForElementVisibility(driver, element);
		return element;
	}



//Pending Approval
	   
	   public static WebElement PA_BillingPeriod(WebDriver driver) {
	        element = driver.findElement(By.xpath("//a[contains(@id,'billingPeriod')]"));
	        Waits.ForElementVisibility(driver, element);
	        return element;
	    }
	   
	   public static WebElement Edit_PA_icon(WebDriver driver) {
	        element = driver.findElement(By.xpath("//a[contains(@id,'openWorksheet')]"));
	        Waits.ForElementVisibility(driver, element);
	        return element;
	    }	   



	      
	   public static WebElement VerifyErrorMessage(WebDriver driver, String errorMsg) {
		   boolean isErrorMsgVerified = false;
		   try {
			element = driver.findElement(By.xpath("//*[@ng-show = 'modal.fail']"));
			System.out.println(element.getText());
			if (element.getText().equals(errorMsg)) {
				isErrorMsgVerified = true;
			}
			if (isErrorMsgVerified) {
				Report.attachScreenShotToReport(driver);
				Report.Log(Status.PASS, "Error messgae : " + errorMsg
						+ " is displayed");
			} else {
				Report.attachScreenShotToReport(driver);
				Report.Log(Status.FAIL, "Error messgae : " + errorMsg
						+ " is NOT displayed");
				Assert.fail("Error messgae : " + errorMsg
						+ " is NOT displayed");
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return element;
	}
	   
	 	    
	    
	


	public static WebElement VerifyPatientDetails(WebDriver driver,
			String toverify) {
		boolean isPatientDetailsDisplayed = false;
		try {
			element = driver.findElement(By.tagName("pre"));
			if (element.getText().contains(toverify)) {
				isPatientDetailsDisplayed = true;
			}
			if (isPatientDetailsDisplayed) {
				Report.Log(Status.PASS, "" + toverify + " is displayed");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + toverify + " is NOT displayed");
				Assert.fail("" + toverify + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	
	// @ Method to select the status tab under RAP and EOE
		public static WebElement SelectStatusTab(WebDriver driver, String tabname)
				throws Exception {
			try {
				Waits.ForGlobalAjaxLoader(driver);
				boolean isStatusTabSelected = false;
				List<WebElement> allrows = driver.findElements(By.xpath("//*[contains(@href,'#/HM/billing/claims-manager/hospice/')" 
	                    +"or contains(@href,'#/HM/billing/claims-manager/EOE/') or contains(@href,'#/HM/billing/claims-manager/managed-care/')"
	                    + "or contains(@href,'#/HM/billing/claims-manager/managed-care-secondary/')]"));   	
				for(WebElement row : allrows)
				{
					String linkTxt = row.getText();
					if (linkTxt.trim().equals(tabname)) {
						Waits.ForBrowserLoad(driver);
						row.click();
						isStatusTabSelected = true;
						break;
					}
				}
				Waits.ForGlobalAjaxLoader(driver);
				if (isStatusTabSelected) {
					Report.Log(Status.PASS, "Tab - '" + tabname + "' is selected");
				} else {
					Report.Log(Status.FAIL, "Tab - '" + tabname
							+ "' is NOT selected");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return element;
		}
		
		
		// @Method to verify patient name in the tab
		public static void NR_VerifyPatient(WebDriver driver, String patientname) {
			VerifyPatient(driver, patientname);
		}

		public static WebElement VerifyPatient(WebDriver driver, String patientname) {
			try {
				Waits.ForBrowserLoad(driver);
				List<WebElement> allrows = driver.findElements(By.tagName("td"));
				boolean isPatientVerified = false;
				for (WebElement row : allrows) {
					//Waits.ForElementVisibility(driver, row);
					if (row.getText().trim().contains(patientname)) {
						/*element = row.findElement(By
								.xpath("//*[@ng-class = 'col.colCSS']"));
						Waits.ForElementVisibility(driver, element);*/
						isPatientVerified = true;
						break;
					}
				}
				if (isPatientVerified) {
					Report.Log(Status.PASS, "" + patientname + " is displayed");
					Report.attachScreenShotToReport(driver);
					Assert.assertTrue(true);
				} else {
					Report.Log(Status.FAIL, "" + patientname + " is NOT displayed");
					Assert.fail("" + patientname + " is NOT displayed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return element;
		}
		
		public static WebElement getClaimTable(WebDriver driver){
	 		element = driver.findElement(By.xpath(".//table[@class='table kinnser-table table-condensed table-striped']"));
	 		return element;	
	 	}
	 	
		public static List<WebElement> getRows(WebElement table){		
	 		List<WebElement> rows = table.findElements(By.tagName("tr"));
	 		return rows;	
	 	}
	 	
		public static List<WebElement> getColumns(WebElement row){
	 		List<WebElement> columns = row.findElements(By.tagName("td"));
	 		return columns;
	 	}
	 	
		public static WebElement getClaimByMrn(WebDriver driver, String subject){
			WebElement table = getClaimTable(driver);
			List<WebElement> rows = getRows(table);
			for(WebElement row: rows){
				List<WebElement> cols = getColumns(row);
			for(WebElement td:cols){
	 		 if(td.getText().trim().contains(subject)){
	 		 	return row;
	 		 	}
	 	}
	 	}
	 		return null;
	 	}

		public static String getClaimNumber(WebDriver driver, String subject, int i){
	 		element = getClaimByMrn(driver, subject);
	 		List<WebElement> cols = getColumns(element);
	 		String Claimnum = cols.get(i).getText();
	 		System.out.println(Claimnum);
			return Claimnum;
	 	}
}
