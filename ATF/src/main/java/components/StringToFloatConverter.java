package components;

/**
 * Created by austin.ledbetter on 2/7/2017.
 */
public class StringToFloatConverter {
    public static Float ConvertToFloat(String s){
        String value = s.replace("$", "").replace(",", "").trim();
        if(value.contains("(") && value.contains(")")){
            return Float.parseFloat(value.replace("(", "").replace(")", "")) * -1;
        }
        return Float.parseFloat(value);
    }
}
