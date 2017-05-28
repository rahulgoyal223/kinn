package API;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {
	public static String ReadJson(String filePath) throws Exception
	{
		String jData = "";	
		InputStream pbUnbilledInputStream = new FileInputStream(filePath);
		InputStreamReader unbilledReader = new InputStreamReader(pbUnbilledInputStream);
		BufferedReader br = new BufferedReader(unbilledReader);
		String line;
		while ((line = br.readLine()) != null) {
			jData += line + "\n";
		}
		br.close();		
		//JsonObject jsonObject = new JsonParser().parse(jData).getAsJsonObject();
		return jData;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean AreJsonStringsEqual(String input1, String input2, String commadelimitedfieldnames) throws Exception
	{
		String newJsonInput1 = input1;
		String newJsonInput2 = input2;
		if(commadelimitedfieldnames != null) {
			newJsonInput1 = JsonStringafterIgnoringFields(input1, commadelimitedfieldnames);
			newJsonInput2 = JsonStringafterIgnoringFields(input2, commadelimitedfieldnames);
		}
		ObjectMapper om = new ObjectMapper();
		Map<String, Object> m1 = (Map<String, Object>)(om.readValue(newJsonInput1, Map.class));
        Map<String, Object> m2 = (Map<String, Object>)(om.readValue(newJsonInput2, Map.class));        
		return m1.equals(m2);
	}
	
	private static String JsonStringafterIgnoringFields(String input, String ignoreStr)
	{		
		JsonObject obj = new JsonParser().parse(input).getAsJsonObject();
		List<String> ignoreList = Arrays.asList(ignoreStr.split(","));
		for(String str: ignoreList) {			
			obj.remove(str);
		}		
		return obj.toString();
	}
}
