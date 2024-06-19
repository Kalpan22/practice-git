package org.jsp.UserBootApp1.Exception;

public class InvalidCredentialExceptionHandler extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid credential Exception occured";
	}
}

