package DataCreation;

import org.testng.annotations.Test;

import Scripts.Claims.RAP.AM_BM2_Medicare_CMS485_HH2614;
import Scripts.Claims.RAP.AM_BM2_Medicare_OASIS_HH2730;

public class RAP {

	 public static void main(String[] args) throws Exception {
			Test("","");
		}
	    
    @Test
	public static void Test(String CreatePatient,String Expression) throws Exception {
    	if (CreatePatient.trim().toLowerCase().equals("yes")) {
    		AM_BM2_Medicare_OASIS_HH2730.AM_BM2_Medicare_OASIS_HH2730();
    		AM_BM2_Medicare_CMS485_HH2614.AM_BM2_Medicare_CMS485_HH2614();
    	}
    	switch(Expression) {
    	case "Pendingpayment" :
    		
    	case "NotReady" :
    		
    	default :
    	
    	}
    	
    	switch(Expression) {
    	case "Payment>Pendingpayment" :
    		
    	case "Pendingpayment>Ready" :
    		
    	default :
    		
    	
    	}
    	
    }
}
