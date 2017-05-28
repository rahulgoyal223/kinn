package Scripts;

import org.testng.annotations.Test;


public class Dbtest {
	
	
	/*public static void main(String[] args) throws Exception {
		Test();
	}
    
    @Test*/
	public static void Test() throws Exception {
    	String[][] results = Database.Queries.getDBInsuranceList();
    	System.out.println(results[0][1]);
    	System.out.println(results[0].length);
    	System.out.println(results.length);
    }
}