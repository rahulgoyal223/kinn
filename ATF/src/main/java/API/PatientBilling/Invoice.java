package API.PatientBilling;

import API.EnvVar;
import API.WebRequestUtil;

public class Invoice {
	
	/* Http Post Methods */
	
	public String CreateInvoice(String invoiceData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Invoicebaseuri + "Invoice";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,invoiceData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}		
	
	/* End of Http Post Methods */
	
	/* Http Put Methods */
	
	public String ApproveInvoice(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId + "/Approve";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken, "", endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String SendInvoice(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId + "/Send";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken, "", endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String VoidInvoice(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId + "/Void";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken, "", endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}	
	
	public String DeleteInvoice(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId + "/Delete";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken, "", endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Put Methods */
	
	/* Http Get Methods */
	
	public String GetInvoice(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetInvoiceByPatientId(String patientId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/ByPatientId/" + patientId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}	
	
	public String GetInvoiceDetails(String invoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + invoiceId + "/Details";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetAllInvoices()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetAllApprovedInvoices()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/Approved";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetAllSentInvoices()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/Sent";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String GetAllVoidedInvoices()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/Voided";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}	
	
	public String GetAllCreatedInvoices()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/Created";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}	
	
	public String GetAllInvoicesWithBalance()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/WithBalance";
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}	
	
	/* End of Http Get Methods */	
	
	/* Http Delete Methods. */
	
	public String DeleteInvoiceWithHttpDelete(String InvoiceId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Invoicebaseuri + "Invoice/" + InvoiceId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeDeleteJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Delete Methods. */
	
}
