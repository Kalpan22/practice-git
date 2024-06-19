package org.jsp.UserAndProduct.Exception;

public class InvalidCredentialsException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Invalid Phone or email or Password";
	}
}
