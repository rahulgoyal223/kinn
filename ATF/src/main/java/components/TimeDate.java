package components;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeDate {

	public static String getUniqueInteger() {
		Date now = new Date();
		return new SimpleDateFormat("MMddHHmmss", Locale.ENGLISH).format(now);
	}
}
