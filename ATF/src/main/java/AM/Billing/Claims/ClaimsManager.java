package AM.Billing.Claims;

import DataSource.Datatable;
import DataSource.GlobalData;

import com.aventstack.extentreports.Status;

import components.Report;
import components.SwitchWindow;
import components.Verify;
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

	// @Methods in Claims manager page
	/**
	 * Generic method to select a Claim from the grid in claims manager
	 * @param driver
	 * @param patientName
	 * @throws Exception
	 */
	public static void SelectClaim(WebDriver driver, String patientName) 
			throws Exception {
		SelectPatient(driver, patientName);
	}
	
	/**
	 * Method to Select a pre-existing Claim from MC Primary Pending Payment
	 * @param driver
	 * @throws Exception
	 */
	public static void SelectClaimFromMCPendingPayment(WebDriver driver) throws Exception {
		AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Pending Payment");
		Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software"); 
		lst_Payer(driver).selectByVisibleText("Managed Care Insurance");
		WebElement table = driver.findElement(By.tagName("table"));
		Waits.fluentWaitIsEnabled(driver, table, 5);
	}
	
	/**
	 * Method to Select a pre-existing claim from RAP Pending Payment
	 * @param driver
	 * @throws Exception
	 */
	public static void SelectClaimFromRAPPendingPayment(WebDriver driver) throws Exception {
		AM.Billing.BillingManager.SelectMenu(driver, "RAP/Pending Payment");
		Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software"); 
		lst_Payer(driver).selectByVisibleText("Palmetto GBA");
		WebElement table = driver.findElement(By.tagName("table"));
		Waits.fluentWaitIsEnabled(driver, table, 5);
		WebElement billingPeriod = driver.findElement(By.xpath("//a[@class='ng-binding' and text()= 'Billing Period']"));
		billingPeriod.click();
	}
	
	/**
	 * Method to Search and Filter by Payer and Claim Age in MC Primary Ready tab
	 * @param driver
	 * @param patientName
	 * @param insuranceName
	 * @throws Exception
	 */
	public static void FilterAndSearchInMCReady(WebDriver driver, String patientName, String insuranceName)
				throws Exception{
		Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "Primary Payer/Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        lst_Payer(driver).selectByVisibleText(insuranceName);
        lst_ClaimAge(driver).selectByVisibleText("All Claims");
        WebElement table = driver.findElement(By.tagName("table"));
		Waits.ForElementVisibility(driver, table);
		WebElement row = table.findElement(By.tagName("tr"));
		WebElement checkbox = row.findElement(By
						.xpath(".//input[@type = 'checkbox']"));
		Waits.fluentWaitIsEnabled(driver, checkbox, 3);

        txt_Searchbox(driver).sendKeys(patientName);
        VerifyPatient(driver, patientName);
	}
	
	/**
	 * Method to search by Payer and Claim age in RAP Ready tab
	 * @param driver
	 * @param patientName
	 * @param insuranceName
	 * @throws Exception
	 */
	public static void FilterAndSearchInRAPReady(WebDriver driver, String patientName, String insuranceName)
				throws Exception{
		Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "RAP/Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        lst_Payer(driver).selectByVisibleText(insuranceName);
        lst_ClaimAge(driver).selectByVisibleText("All Claims");
        WebElement table = driver.findElement(By.tagName("table"));
		Waits.ForElementVisibility(driver, table);
		WebElement row = table.findElement(By.tagName("tr"));
		WebElement checkbox = row.findElement(By
						.xpath(".//input[@type = 'checkbox']"));
		Waits.fluentWaitIsEnabled(driver, checkbox, 3);

        txt_Searchbox(driver).sendKeys(patientName);
        VerifyPatient(driver, patientName);
	}
	
	/**
	 * Method to search by Payer and Claim age in EOE Ready tab
	 * @param driver
	 * @param patientName
	 * @param insuranceName
	 * @throws Exception
	 */
	public static void FilterAndSearchInEOEReady(WebDriver driver, String patientName, String insuranceName)
				throws Exception{
		Verify.ExactPageTitle(driver, "Billing Manager | Kinnser Software");
        AM.Billing.BillingManager.SelectMenu(driver, "EOE/Ready");
        Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        lst_Payer(driver).selectByVisibleText(insuranceName);
        lst_ClaimAge(driver).selectByVisibleText("All Claims");
        WebElement table = driver.findElement(By.tagName("table"));
		Waits.ForElementVisibility(driver, table);
		WebElement row = table.findElement(By.tagName("tr"));
		WebElement checkbox = row.findElement(By
						.xpath(".//input[@type = 'checkbox']"));
		Waits.fluentWaitIsEnabled(driver, checkbox, 3);

        txt_Searchbox(driver).sendKeys(patientName);
        VerifyPatient(driver, patientName);
	}
	
	/**
	 * Method to Filter and Search in the Rejected/Cancelled tabs of Claims Manager
	 * @param driver
	 * @param patientName - patient name to search for
	 * @param insuranceName - payer
	 * @param type - rejected, cancelled or voided
	 * @throws Exception
	 */
	public static void FilterAndSearchByPayerAndType(WebDriver driver, String patientName, String insuranceName, String type)
				throws Exception {
		Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        lst_Payer(driver).selectByVisibleText(insuranceName);
        lst_RC_Type(driver).selectByVisibleText(type);
        WebElement table = driver.findElement(By.tagName("table"));
		Waits.ForElementVisibility(driver, table);
		WebElement row = table.findElement(By.tagName("tr"));
		WebElement checkbox = row.findElement(By
						.xpath(".//input[@type = 'checkbox']"));
		Waits.fluentWaitIsEnabled(driver, checkbox, 3);

        txt_Searchbox(driver).sendKeys(patientName);
        VerifyPatient(driver, patientName);
	}
	
	/**
	 * Method to search in claims manager tabs that have the payer drop down only
	 * @param driver
	 * @param patientName - patient name to search for
	 * @param insuranceName - payer to select from drop down
	 * @throws Exception
	 */
	public static void FilterAndSearchByPayer(WebDriver driver, String patientName, String insuranceName)
				throws Exception {
		Verify.ExactPageTitle(driver, "Claims Manager | Kinnser Software");
        lst_Payer(driver).selectByVisibleText(insuranceName);
        WebElement table = driver.findElement(By.tagName("table"));
		Waits.ForElementVisibility(driver, table);
		WebElement row = table.findElement(By.tagName("tr"));
		WebElement checkbox = row.findElement(By
						.xpath(".//input[@type = 'checkbox']"));
		Waits.fluentWaitIsEnabled(driver, checkbox, 3);

        txt_Searchbox(driver).sendKeys(patientName);
        VerifyPatient(driver, patientName);
	}
	
	public static void CreateClaim(WebDriver driver) throws Exception {
		Waits.fluentWaitIsEnabled(driver, btn_RD_Createclaim(driver), 3);
		btn_RD_Createclaim(driver).click();
	    VerifyAlertmessage(driver, "Claim(s) have been created successfully and can be reviewed in the Pending Approval tab.");
	}
	
	public static void ApproveClaim(WebDriver driver) throws Exception {
		Waits.fluentWaitIsEnabled(driver, btn_PA_Approve(driver), 3);
		btn_PA_Approve(driver).click();
	    VerifyAlertmessage(driver, "Claim(s) have been approved successfully and can be reviewed in the Ready to Send tab.");	    	
	}
	
	public static void SendMedicareClaim(WebDriver driver) throws Exception {
		Waits.fluentWaitIsEnabled(driver, btn_RS_Claimactions(driver), 3);
		btn_RS_Claimactions(driver).click();
	    btn_RS_Sendmanually(driver).click();
	    VerifyAlertmessage(driver, "Palmetto GBA: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
	    	
	}
	
	public static void AdjustClaim(WebDriver driver) throws Exception {
		  Waits.fluentWaitIsEnabled(driver, btn_PD_Adjust(driver), 3);
		  btn_PD_Adjust(driver).click();
		  btn_PP_Yes(driver).click();
		  VerifyAlertmessage(driver, "Claim(s) marked as adjusted and returned to the Pending Approval tab.");
	}
	public static void VoidClaim(WebDriver driver) throws Exception {
	      Waits.fluentWaitIsEnabled(driver, btn_PA_Void(driver), 3);
		  btn_PA_Void(driver).click();
	      SwitchWindow.toTopWindow(driver);
	      btn_Resend_Yes(driver).click();
	      SwitchWindow.to(driver, "Claims Manager | Kinnser Software");
	      VerifyAlertmessage(driver, "Claim(s) have been voided and can be reviewed in the Rejected/Cancelled tab.");
	}
	
	public static void SendMCPrimaryClaim(WebDriver driver) throws Exception {
		Waits.fluentWaitIsEnabled(driver, btn_RS_Claimactions(driver), 3);
		btn_RS_Claimactions(driver).click();
	    btn_RS_Sendmanually(driver).click();
	    VerifyAlertmessage(driver, "Managed Care Insurance: Claim(s) have been generated successfully and can be reviewed in the Pending Payment tab.");
	    	
	}
	
	// @Method to select patient in Not Ready creation page
	public static void NR_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in Ready creation page
	public static void RD_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in pending approval page
	public static void PA_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in Ready to send page
	public static void RS_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in pending payment page
	public static void PP_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in paid page
	public static void PD_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to select patient in Rejected/Cancelled
	public static void RC_SelectPatient(WebDriver driver, String patientname)
			throws Exception {
		SelectPatient(driver, patientname);
	}

	// @Method to verify patient name in the tab
	public static void NR_VerifyPatient(WebDriver driver, String patientname) {
		VerifyPatient(driver, patientname);
	}

	public static WebElement VerifyPatient(WebDriver driver, String patientname) {
		try {
			WebElement table = driver.findElement(By.tagName("table"));
			Waits.fluentWaitIsDisplayed(driver, table, 2);
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			boolean isPatientVerified = false;
			for (WebElement row : allrows) {
				Waits.ForElementToBeClickable(driver, row);
				if (row.getText().trim().equals(patientname.trim())) {
					element = row.findElement(By
							.xpath("//*[@ng-class = 'definition.cellClass']"));
					Waits.fluentWaitIsDisplayed(driver, element, 2);
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
	
	public static WebElement VerifyClaim(WebDriver driver, String claimnum) {
		try {
			Waits.ForBrowserLoad(driver);
			List<WebElement> allrows = driver.findElements(By.tagName("td"));
			boolean isClaimVerified = false;
			for (WebElement row : allrows) {
				Waits.ForElementVisibility(driver, row);
				if (row.getText().trim().equals(claimnum.trim())) {
					element = row.findElement(By
							.xpath("//*[@ng-class = 'definition.cellClass']"));
					Waits.ForElementVisibility(driver, element);
					isClaimVerified = true;
					break;
				}
			}
			if (isClaimVerified) {
				Report.Log(Status.PASS, "" + claimnum + " is displayed");
				Report.attachScreenShotToReport(driver);
			} else {
				Report.Log(Status.FAIL, "" + claimnum + " is NOT displayed");
				Assert.fail("" + claimnum + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static void VerifyStatus(WebDriver driver, int rowIndex, int colIndex) {
		WebElement stat = driver.findElement(By
				.className("ks-pointer ng-scope"));
		if (stat.getAttribute("src").contains("/AM/images/GreenIcon.png")) {
			Report.Log(Status.INFO, "Status is in GREEN tick");
			System.out.println("Green");
		} else {
			Report.Log(Status.INFO, "Status is in RED tick");
			System.out.println("Red");
		}
	}

	public static WebElement SelectPatient(WebDriver driver, String patient)
			throws Exception {

		try {
			Waits.ForBrowserLoad(driver);
			WebElement table = driver.findElement(By.tagName("table"));
			Waits.ForElementVisibility(driver, table);
			List<WebElement> allrows = table.findElements(By.tagName("tr"));
			for (WebElement row : allrows) {
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

	// @objects in Claims Manager
	// @objects under Ready page


	// @Objects under Pending Payment page


	// @Objects under Rejected/Cancelled
    //@objects in Claims Manager
	public static Select lst_Payer(WebDriver driver) {
		element = driver.findElement(By.xpath("//select[@ng-model = 'insuranceKey']"));
		list = new Select(element);
		return list;
	}
	
	public static Select lst_ClaimAge(WebDriver driver) {
		element = driver.findElement(By.id("dayRange"));
		list = new Select(element);
		return list;
	}
	
	public static WebElement txt_Searchbox(WebDriver driver) {
        element = driver.findElement(By.name("gridQuery"));
        return element;
	}
	
    //@objects under Ready page
    public static Select lst_RE_Payer(WebDriver driver) {
        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
        list = new Select(element);
        return list;
    }

    public static Select lst_RE_ClaimAge(WebDriver driver) {
        element = driver.findElement(By.id("dayRange"));
        list = new Select(element);
        return list;
    }

    public static WebElement txt_RE_Searchbox(WebDriver driver) {
        element = driver.findElement(By.name("gridQuery"));
        return element;
    }

    public static WebElement btn_RE_Createclaim(WebDriver driver) {
        element = driver.findElement(By.id("claimsCreation"));
        return element;
    }

    //@objects under Pending Approval page
   public static Select lst_PA_Payer(WebDriver driver) {
        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
        list = new Select(element);
        return list;
    }
   public static WebElement btn_ubo4preview(WebDriver driver) {
       element = driver.findElement(By.id("UB04Preview"));
       return element;
   }
   

    public static WebElement txt_PA_Searchbox(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.name("gridQuery"));     
        return element;
    }

    public static WebElement btn_PA_Comment(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addCommentToClaim()']"));
        return element;
    }

    public static WebElement btn_PA_Followup(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
        return element;
    }

    public static WebElement btn_PA_Approve(WebDriver driver) {
        element = driver.findElement(By.id("claimsApproval"));
        return element;
    }

    public static WebElement btn_PA_Void(WebDriver driver) {
        element = driver.findElement(By.id("claimsVoid"));
        return element;
    }
    
    public static WebElement btn_PA_VoidEOEAndRAP(WebDriver driver) {
        element = driver.findElement(By.id("claimsVoid"));
        return element;
    }

    //@objects under ready to send page

    public static Select lst_RS_Payer(WebDriver driver) {
        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
        list = new Select(element);
        return list;
    }

    public static WebElement txt_RS_Searchbox(WebDriver driver) {
        element = driver.findElement(By.name("gridQuery"));
        return element;
    }

    public static WebElement btn_RS_Claimactions(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@class = 'btn dropdown-toggle']"));
        return element;
    }

    public static WebElement btn_RS_Comment(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addCommentToClaim()']"));
        return element;
    }

    public static WebElement btn_RS_Followup(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
        return element;
    }

    public static WebElement btn_RS_Sendmanually(WebDriver driver) {
        element = driver.findElement(By.id("sendManual"));
        return element;
    }

    public static WebElement btn_RS_Reopen(WebDriver driver) {
        element = driver.findElement(By.id("claimsReopen"));
        return element;
    }

    public static WebElement btn_RS_Print(WebDriver driver) {
        element = driver.findElement(By.id("printClaims"));
        return element;
    }

    public static WebElement btn_RS_PrintMarkpendinpayment(WebDriver driver) {
        element = driver.findElement(By.id("printAndMarkPendingPayment"));
        return element;
    }

    public static WebElement btn_RS_Markpendingpayment(WebDriver driver) {
        element = driver.findElement(By.id("markPendingPayment"));
        return element;
    }

    public static WebElement btn_RS_Void(WebDriver driver) {
        element = driver.findElement(By.id("claimsVoid"));
        return element;
    }
    
    public static WebElement lnk_RS_Savefile(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@ng-show='alert.link.linkText']"));
        return element;
    }
  
    public static WebElement btn_RS_PrintView(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[contains(@id,'openClaimPrintView')]")); 
        return element;
    } 
    //@Objects under Pending Payment page
    public static Select lst_PP_Payer(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
        list = new Select(element);          
        return list;
    }    

    public static WebElement txt_PP_Searchbox(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.name("gridQuery"));    
        return element;
    }

    public static WebElement btn_PP_Comment(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addCommentToClaim()']"));
        return element;
    }

    public static WebElement btn_PP_Followup(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
        return element;
    }

    public static WebElement btn_PP_Void(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='claimsVoid']"));
        return element;
    }

    public static WebElement btn_PP_Markrejected(WebDriver driver) {
        element = driver.findElement(By.id("claimsRejected"));
        return element;
    }

    public static WebElement btn_PP_ResendManually(WebDriver driver) {
        element = driver.findElement(By.id("resendManual"));
        return element;
    }
     //@Objects under Pending Payment -Managed Care 
    public static WebElement btn_PP_Markreject(WebDriver driver) {
       element = driver.findElement(By.id("claimsReject"));
       return element;
   }
     //@Objects under Pending Payment Resend -Managed Care 
     public static WebElement btn_PP_MCSResendManually(WebDriver driver) {
       element = driver.findElement(By.id("sendManual"));
       return element;
   }
    //@Objects under Paid Page
   public static Select lst_PD_Payer(WebDriver driver) {
        element = driver.findElement(By.xpath("//select[@ng-model ='insuranceKey']"));
        list = new Select(element);
        return list;
    }

    public static WebElement txt_PD_Searchbox(WebDriver driver) {
        element = driver.findElement(By.name("gridQuery"));
        return element;
    }

    public static WebElement btn_PD_Comment(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addCommentToClaim()']"));
        return element;
    }

    public static WebElement btn_PD_Followup(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
        return element;
    }

    public static WebElement btn_PD_Void(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='claimsVoid']"));
        return element;
    }

    public static WebElement btn_PD_Cancel(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='claimsCancel']"));
        return element;
    }

    public static WebElement btn_PD_Alert_Cancel(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@value='Cancel']"));
        return element;
    }

    public static WebElement btn_PD_Alert_SaveComment(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@value='Save Comment']"));
        return element;
    }
    
    public static WebElement btn_PD_Adjust(WebDriver driver) {
        element = driver.findElement(By.id("claimsAdjust"));

        return element;
    }

    
    public static WebElement btn_PP_Correct(WebDriver driver) {
        element = driver.findElement(By.id("correctClaim"));
        return element;
    }

    //@Objects under Rejected/Cancelled
    public static Select lst_RC_Payer(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.id("claimPayer"));
        list = new Select(element);
        return list;
    }

    public static WebElement txt_RC_Searchbox(WebDriver driver) throws InterruptedException {
        element = driver.findElement(By.name("gridQuery"));
        return element;
    }

    public static Select lst_RC_Type(WebDriver driver) {
        element = driver.findElement(By.id("rejectedCancelledTypeDropdown"));
        list = new Select(element);
        return list;
    }

    public static WebElement btn_RC_Comment(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addCommentToClaim()']"));
        return element;
    }

    public static WebElement btn_RC_Followup(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
        return element;
    }

    public static WebElement btn_AC_SaveComment(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@value='Save Comment']"));
        return element;
    }

    public static WebElement txt_AC_AddComment(WebDriver driver) {
        element = driver.findElement(By.id("comment-box"));
        return element;
    }    
    
	public static WebElement btn_RC_Correct(WebDriver driver) {
		element = driver.findElement(By.id("claimsCorrect"));
		return element;
	}

	public static WebElement btn_RC_Claimactions(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//a[@class = 'btn dropdown-toggle']"));
		return element;
	}

	// Added on 10-Nov-2016
	// @Objects for Not Ready tab
	public static Select lst_NR_Payer(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By
				.xpath("//select[@ng-model ='insuranceKey']"));
		list = new Select(element);
		//Waits.ForBrowserLoad(driver);
		return list;
	}

	public static Select lst_NR_ClaimAge(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("dayRange"));
		list = new Select(element);
		//Waits.ForBrowserLoad(driver);
		return list;
	}

	public static WebElement txt_NR_Searchbox(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("gridQuery"));
		//Waits.ForBrowserLoad(driver);
		return element;
	}

	public static Select lst_NR_Type(WebDriver driver) {
		element = driver.findElement(By.id("rejectedCancelledTypeDropdown"));
		list = new Select(element);
		return list;
	}

	public static WebElement btn_NR_Comment(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[@ng-click = 'addCommentToClaim()']"));
		return element;
	}

	public static WebElement btn_NR_Followup(WebDriver driver) {
		element = driver.findElement(By
				.xpath("//button[@ng-click = 'addFollowUpToClaim()']"));
		return element;
	}

	public static WebElement btn_NR_SaveComment(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Save Comment'"));
		return element;
	}

	public static WebElement txt_NR_AddComment(WebDriver driver) {
		element = driver.findElement(By.id("comment-box"));
		return element;
	}
	
	// @Objects for Ready Tab
	
	
	
	
	
	public static Select lst_RD_Payer(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By
				.xpath("//select[@ng-model ='insuranceKey']"));
		list = new Select(element);
		//Waits.ForBrowserLoad(driver);
		Report.Log(Status.INFO, "Acted on lst_R_Payer");
		return list;
	}

	public static Select lst_RD_ClaimAge(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("dayRange"));
		list = new Select(element);
		//Waits.ForBrowserLoad(driver);
		Report.Log(Status.INFO, "Acted on lst_R_ClaimAge");
		return list;
	}
	

	// Added on 21-Nov-2016
	public static WebElement btn_RD_Createclaim(WebDriver driver) {
		element = driver.findElement(By.id("claimsCreation"));
		return element;
	}

	public static WebElement txt_RD_Searchbox(WebDriver driver)
			throws InterruptedException {
		element = driver.findElement(By.name("gridQuery"));
		//Waits.ForElementVisibility(driver, element);
		//Waits.ForBrowserLoad(driver);
		// More sync is required to handle ajax loading for more no of patients
		Report.Log(Status.INFO, "Acted on txt_R_Searchbox");
		return element;
	}

	// Added on 21-Nov-2016
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
				Report.attachScreenShotToReport(driver);
				Assert.fail("Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			Report.attachScreenShotToReport(driver);
		}

	}

	public static void VerifyClaimsAlertmessage(WebDriver driver, String Msg)
			throws Exception {
		String getMsg;
		try {
			Waits.fluentWaitIsEnabled(driver, Alt_PA_Successfullmsg(driver), 5);
			getMsg = Alt_PA_Successfullmsg(driver).getText();
			if (getMsg.trim().equals(Msg.trim())) {
				Report.Log(Status.PASS, "Alert " + Msg + " is displayed");
			} else {
				Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
				Report.attachScreenShotToReport(driver);
				Assert.fail("Alert " + Msg + " is NOT displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Report.Log(Status.FAIL, "Alert " + Msg + " is NOT displayed");
			Report.attachScreenShotToReport(driver);
		}
	}

	// @ Method to select the status tab under RAP and EOE
	public static WebElement SelectStatusTab(WebDriver driver, String tabname)
			throws Exception {
		try {
			Waits.ForGlobalAjaxLoader(driver);
			boolean isStatusTabSelected = false;
			List<WebElement> allrows = driver.findElements(By.xpath("//*[contains(@href,'#/AM/billing/claims-manager/RAP/')" 
                    +"or contains(@href,'#/AM/billing/claims-manager/EOE/') or contains(@href,'#/AM/billing/claims-manager/managed-care/')"
                    + "or contains(@href,'#/AM/billing/claims-manager/managed-care-secondary/')]"));   	
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
				Report.attachScreenShotToReport(driver);
				Assert.fail("Tab - '" + tabname
						+ "' is NOT selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return element;
	}

	public static WebElement btn_RS_markPendingPayment(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='markPendingPayment']"));
		return element;
	}

	public static WebElement Alt_R_Successfullmsg(WebDriver driver) {
		element = driver
				.findElement(By
						.xpath("//*[(contains(@ng-bind-html-unsafe,'alert.message') and contains(@class,'ng-binding'))]"));
		return element;
	}

	public static WebElement Alt_PA_Successfullmsg(WebDriver driver) {
		element = driver.findElement(By.xpath("//*[@id='alert']/span"));
		return element;
	}

	public static WebElement btn_RD_Comment(WebDriver driver) {
		element = driver.findElement(By
				.xpath("/html/body/div[1]/div[5]/div[2]/div/form/div[2]/div[2]/button[1]"));
		return element;
	}

	public static WebElement btn_R_Followup(WebDriver driver) {
		element = driver.findElement(By
				.xpath("/html/body/div[1]/div[5]/div[2]/div/form/div[2]/div[2]/button[2]"));
		return element;
	}
  
    public static Select lst_RD_Type(WebDriver driver) {
        element = driver.findElement(By.id("rejectedCancelledTypeDropdown"));
        list = new Select(element);
        Report.Log(Status.INFO, "Acted on lst_R_Type");
        return list;
    }
    
    public static WebElement btn_RC_SaveComment(WebDriver driver) {
        element = driver.findElement(By.cssSelector("div.modal-footer > input[Value='Save Comment']"));
        return element;
    }

    public static WebElement txt_RC_AddComment(WebDriver driver) {
        element = driver.findElement(By.cssSelector("textarea#comment-box"));
        return element;
    }
    
    public static WebElement btn_Resend_Yes(WebDriver driver) {
    	 element = driver.findElement(By.xpath("*//input[@value='Yes']"));
        return element;
    }
    
    public static WebElement btn_Resend_No(WebDriver driver) {
        element = driver.findElement(By.xpath(".//input[@value='No']"));
        return element;
    }
	
	public static WebElement btn_Correct_Yes(WebDriver driver) {
		element = driver.findElement(By.xpath(".//input[@value='Yes']"));
		return element;
	}

	public static WebElement btn_PP_Yes(WebDriver driver) {
		element = driver.findElement(By.xpath(".//input[@value='Yes']"));
		Waits.ForElementToBeClickable(driver, element);
		return element;
	}
	   
	   public static WebElement btn_PP_No(WebDriver driver) {
	       element = driver.findElement(By.xpath(".//input[@value='No']"));
	       return element;
	   }	   
	   
	   public static WebElement PA_BillingPeriod(WebDriver driver) {
	        element = driver.findElement(By.xpath("//a[contains(@id,'billingPeriod')]"));
	        return element;
	    }
	   
	   public static WebElement Edit_PA_icon(WebDriver driver) {
	        element = driver.findElement(By.xpath("//a[contains(@id,'openWorksheet')]"));
	        return element;
	    }	   
	   
	   public static WebElement ClaimPreviewIcon(WebDriver driver) {
	        element = driver.findElement(By.xpath("//i[@ng-show = 'templateModel.previewDisplay']"));
	        return element;
	    }
	   
	   public static WebElement btn_PP_Reject(WebDriver driver) {
	         element = driver.findElement(By.id("claimsReject"));
	         return element;        
	    }
	   
	   //@Objects under Paid
	   public static WebElement btn_P_Cancel(WebDriver driver) {
	        element = driver.findElement(By.id("claimsCancel"));
	        return element;
	      }
	   
	    //@step for Comment hover
	    public static void toMouseHoverOnIcon(WebDriver driver) throws Exception {
	         element = driver.findElement(By.xpath("//i[@ng-click='templateModel.editComment(templateModel.claimTransactionKey, templateModel.claimCommentKey)']"));
	         Actions builder = new Actions(driver);
	         builder.moveToElement(element).click().build().perform();  
	         Report.Log(Status.INFO, "Mouse Hover Action is performed");
	    }
	    
	    //@step for Click on Green Icon hover
	    public static WebElement clk_greenicon(WebDriver driver) {
	         element = driver.findElement(By.xpath("//i[@ng-click='templateModel.editComment(templateModel.claimTransactionKey, templateModel.claimCommentKey)']"));
	         return element;    
	     
	    }
	    public static WebElement btn_RC_ClaimsCorrect(WebDriver driver) {
	        element = driver.findElement(By.id("correctClaim"));
	        return element;
	    }
	      public static WebElement btn_PP_reject(WebDriver driver) {
	        element = driver.findElement(By.id("claimsReject"));
	        return element;
	      }
	      
	   public static WebElement btn_RCP_OK(WebDriver driver) {
		   element = driver.findElement(By.xpath("*//*[@ng-show = 'modal.goButtonText.length > 0']"));
		   return element;
	   }
	   
	   public static WebElement msg_RCP_ErrorMessage(WebDriver driver) {
		   element = driver.findElement(By.xpath("//*[@ng-show = 'modal.fail']"));
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
	
	public static WebElement getClaimTable(WebDriver driver){
		 		element = driver.findElement(By.xpath(".//table[@class='table table-condensed table-striped ks-grid-standard ']"));
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
	
	public static String getClaimNumber(WebDriver driver, String subject){
		 		element = getClaimByMrn(driver, subject);
		 		List<WebElement> cols = getColumns(element);
		 		String Claimnum = cols.get(7).getText();
		 		System.out.println(Claimnum);
				return Claimnum;
		 	}
}
