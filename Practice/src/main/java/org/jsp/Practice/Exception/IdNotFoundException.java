package org.jsp.Practice.Exception;

public class IdNotFoundException extends RuntimeException
{
	@Override
	public String getMessage() {
		return "id not found";
	}

}
