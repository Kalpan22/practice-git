package org.jsp.Practice.Exception;
public class InvalidCredentialException extends RuntimeException
{
	@Override
	public String getMessage() {
		return "invalid id , phone , email";
	}

}
