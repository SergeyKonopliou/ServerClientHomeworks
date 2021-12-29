package servlet.util;

import java.util.regex.Pattern;

public class DecimalValidator {

	public String valid(String item) {
		boolean isValid = isDecimal(item);
		if (item.isEmpty() || !isValid) {
			return "0.0";
		}
		return item;
	}

	private boolean isDecimal(String str) {
		Pattern decimalPattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
		return decimalPattern.matcher(str).matches();
	}
}
