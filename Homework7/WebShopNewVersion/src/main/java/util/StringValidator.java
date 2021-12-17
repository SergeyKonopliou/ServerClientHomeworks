package util;

import exception.ValidateException;

public class StringValidator implements IValidator {

	@Override
	public boolean valid(String item) {
		boolean isValid = true;
		if(item == null || item.isEmpty()) {
			isValid = false;
			throw new ValidateException("String content not found.");
		}
		return isValid;
	}

}
