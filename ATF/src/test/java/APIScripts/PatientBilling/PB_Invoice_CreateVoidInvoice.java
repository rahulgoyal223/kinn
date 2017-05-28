package APIScripts.PatientBilling;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonParser;

import API.CommonUtil;
import API.EnvVar;
import API.PatientBilling.Invoice;

public class PB_Invoice_CreateVoidInvoice {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			CallInvoiceCreateVoidTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public static void CallInvoiceCreateVoidTest() throws Exception {
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
		
		// Void Invoice
		inv.VoidInvoice(InvoiceId);
		String getVoidedInvoiceResponse = inv.GetInvoice(InvoiceId);
		String StatusId = new JsonParser().parse(getVoidedInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "3");
		
		// Delete Invoice with http post method.
		inv.DeleteInvoice(InvoiceId);
		String getDeletedInvoiceResponse = inv.GetInvoice(InvoiceId);
		StatusId = new JsonParser().parse(getDeletedInvoiceResponse).getAsJsonObject().get("fStatusId").getAsString();
		Assert.assertEquals(StatusId, "4");
		
		//check for equality
		boolean AreEqualJsonStrings = CommonUtil.AreJsonStringsEqual(createInvoiceResponse, getDeletedInvoiceResponse, "Status,fStatusId,Effective,Created,InvoiceNumber");
		Assert.assertEquals(AreEqualJsonStrings, true);
		
		System.out.println("PB_Invoice_CreateVoid Test Passed successfully.");
		
	}
}