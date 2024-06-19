package org.jsp.Practice.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.jsp.Practice.Dao.UserDao;
import org.jsp.Practice.Dto.ResponseStructure;
import org.jsp.Practice.Dto.User;
import org.jsp.Practice.Exception.IdNotFoundException;
import org.jsp.Practice.Exception.InvalidCredentialException;
import org.jsp.Practice.Exception.UserBootExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService
{
	@Autowired
	private UserDao dao;

	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user)
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		User dbuser=dao.saveUser(user);
		structure.setMessage("User is saved");
		structure.setData(dbuser);
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User user)
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> dbUser=dao.findById(user.getId());
		if(dbUser.isPresent())
		{
			User recUser=dbUser.get();
			recUser.setName(user.getEmail());
			recUser.setPhone(user.getPhone());
			recUser.setEmail(user.getEmail());
			recUser.setPassword(user.getPassword());
			recUser=dao.updateUser(recUser);
			structure.setMessage("User is Updated");
			structure.setData(recUser);
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<User>> findById(int id)
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> dbUser=dao.findById(id);
		if(dbUser.isPresent())
		{
			structure.setData(dbUser.get());
			structure.setMessage("User id : "+id+" records");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		}
		throw  new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Boolean>> deleteUser( int id)
	{
		ResponseStructure<Boolean> structure=new ResponseStructure<>();
		Optional<User> dbUser=dao.findById(id);
		if(dbUser.isPresent())
		{
			dao.deleteById(id);
			structure.setData(true);
			structure.setMessage("User had been deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<List<User>>> FindAllUser(User user)
	{
		ResponseStructure<List<User>> structure=new ResponseStructure<>();
		structure.setData(dao.findAll());
		structure.setMessage("All the Users");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.OK);	
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUserByPhoneAndPassword(long phone,String password)
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> dbUser=dao.verifyByPhoneAndPassword(phone, password);
		if(dbUser.isPresent())
		{
			structure.setData(dbUser.get());
			structure.setMessage("User Verify By phone Password");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);	
		}
		throw new InvalidCredentialException();
	}
	public ResponseEntity<ResponseStructure<User>> verifyUserByEmailAndPassword(String email,String password)
	{
		ResponseStructure<User> structure=new ResponseStructure<>();
		Optional<User> dbUser=dao.verifyByEmailAndPassword(email, password);
		if(dbUser.isPresent())
		{
			structure.setData(dbUser.get());
			structure.setMessage("User Verify By email Password");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);	
		}
		throw new InvalidCredentialException();
	}
	
}
























