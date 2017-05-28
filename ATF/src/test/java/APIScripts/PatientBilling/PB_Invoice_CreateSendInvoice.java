package APIScripts.PatientBilling;

import org.testng.Assert;
import org.testng.annotations.Test;

import API.CommonUtil;
import API.EnvVar;
import API.PatientBilling.Invoice;

import com.google.gson.JsonParser;

public class PB_Invoice_CreateSendInvoice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CallInvoiceCreateSendTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public static void CallInvoiceCreateSendTest() throws Exception {
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
		
		// Send Invoice
		inv.SendInvoice(InvoiceId);
		String getSentInvoiceResponse = inv.GetInvoice(InvoiceId);
		String StatusId = new JsonParser().parse(getSentInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "2");
		
		// Delete Invoice with http delete method.
		inv.DeleteInvoiceWithHttpDelete(InvoiceId);
		String getDeletedInvoiceResponse = inv.GetInvoice(InvoiceId);
		StatusId = new JsonParser().parse(getDeletedInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "4");
		
		//check for equality
		boolean AreEqualJsonStrings = CommonUtil.AreJsonStringsEqual(createInvoiceResponse, getDeletedInvoiceResponse, "Status,fStatusId,Effective,Created,InvoiceNumber");
		Assert.assertEquals(AreEqualJsonStrings, true);
		
		System.out.println("PB_Invoice_CreateSend Test Passed successfully.");
	}
}
