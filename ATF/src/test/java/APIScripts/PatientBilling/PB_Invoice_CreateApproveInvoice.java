package APIScripts.PatientBilling;

import org.apache.logging.log4j.core.jackson.JsonConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonParser;

import API.CommonUtil;
import API.EnvVar;
import API.PatientBilling.Invoice;

public class PB_Invoice_CreateApproveInvoice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CallInvoiceCreateApproveTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public static void CallInvoiceCreateApproveTest() throws Exception {
		Invoice inv = new Invoice();
		String jData = "";		
		
		// Step1: Let's 1st read file from fileSystem
		// PB_UnbilledItems.txt path here			
		jData = CommonUtil.ReadJson(EnvVar.PBUnbilledItemsFilePath);
		Assert.assertNotNull(jData);
		
		// Create Invoice with Unbilled Items.		
		String createInvoiceResponse = inv.CreateInvoice(jData);
		String InvoiceId = new JsonParser().parse(createInvoiceResponse).getAsJsonObject().get("InvoiceId").getAsString();
		Assert.assertNotNull(InvoiceId); 
		
		// Approve Invoice
		String retVal = inv.ApproveInvoice(InvoiceId);
		Assert.assertEquals(retVal, "true");
		String getApprovedInvoiceResponse = inv.GetInvoice(InvoiceId);
		String StatusId = new JsonParser().parse(getApprovedInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "1");
		
		// Delete Invoice with http delete method.
		inv.DeleteInvoiceWithHttpDelete(InvoiceId);
		String getDeletedInvoiceResponse = inv.GetInvoice(InvoiceId);
		StatusId = new JsonParser().parse(getDeletedInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "4");
		
		//check for equality
		// ignore list can be comma delimited.
		boolean AreEqualJsonStrings = CommonUtil.AreJsonStringsEqual(createInvoiceResponse, getDeletedInvoiceResponse, "Status,fStatusId,Effective,Created,InvoiceNumber");
		Assert.assertEquals(AreEqualJsonStrings, true);
		
		System.out.println("PB_Invoice_CreateApprove Test Passed successfully.");
		
	}
}
