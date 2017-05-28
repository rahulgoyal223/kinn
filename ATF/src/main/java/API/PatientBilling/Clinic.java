package API.PatientBilling;

import API.EnvVar;
import API.WebRequestUtil;

public class Clinic {

	/* Http Post Methods */
	
	public String CreateClinic(String jsonClinicData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Clinicbaseuri + "Clinic";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,jsonClinicData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String UpdatePrefixInfo(String jsonClinicData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Clinicbaseuri + "PrefixInfo";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,jsonClinicData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String UpdateLogoInfo(String jsonClinicData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Clinicbaseuri + "LogoInfo";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,jsonClinicData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	public String UpdateClinicNoteInfo(String jsonClinicData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Clinicbaseuri + "ClinicNoteInfo";
        String response = "";
		try {
			response = webReq.requestBuilderTypePostJson(EnvVar.PBTestToken,jsonClinicData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Post Methods */
	
	/* Http Get Methods */
	
	public String GetByRefKey()
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Clinicbaseuri;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}		
	
	public String GetClinic(String ClinicId)
	{
		WebRequestUtil webReq = new WebRequestUtil();
		String endpoint = EnvVar.Baseuri + "/Clinic/" + ClinicId;
        String response = "";
		try {
			response = webReq.requestBuilderTypeGetJson(EnvVar.PBTestToken, endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Get Methods */
	
	/* Http Put Methods */
	
	public String UpdateClinic(String jsonClinicData)
	{
		WebRequestUtil webReq = new WebRequestUtil();
        String endpoint = EnvVar.Clinicbaseuri + "Clinic";
        String response = "";
		try {
			response = webReq.requestBuilderTypePutJson(EnvVar.PBTestToken,jsonClinicData,endpoint);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return response;
	}
	
	/* End of Http Put Methods */
}
