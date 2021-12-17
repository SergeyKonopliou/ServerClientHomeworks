package util;

import java.util.regex.Pattern;

import exception.ValidateException;

public class DecimalValidator implements IValidator {

	@Override
	public boolean valid(String item) {
		boolean isValid = isDecimal(item);
		if(!isValid) {
			throw new ValidateException("Decimal content not found.");
		}
		return isValid;
	}
	
	private boolean isDecimal(String str) {
		if(str.isEmpty()) return false;
		Pattern decimalPattern = Pattern.compile("\\d+(\\.)?\\d+");
		return decimalPattern.matcher(str).matches();
	}
}
