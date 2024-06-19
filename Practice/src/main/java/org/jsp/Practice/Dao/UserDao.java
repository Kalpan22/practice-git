package org.jsp.Practice.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.Practice.Dto.ResponseStructure;
import org.jsp.Practice.Dto.User;
import org.jsp.Practice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao 
{
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user)
	{
		return repository.save(user);
	}
	public User updateUser(User user)
	{
		return repository.save(user);
	}
	public Optional<User> findById(int id)
	{
		return repository.findById(id);
	}
	public boolean deleteById(int id)
	{
		Optional<User> dbUser=repository.findById(id);
		if(dbUser.isPresent())
		{
			repository.delete(dbUser.get());
			return true;
		}
		return false;
	}
	public List<User> findAll()
	{
		return repository.findAll();
	}
	public Optional<User> verifyByPhoneAndPassword(long phone,String password)
	{
		return repository.verifyByPhoneAndPassword(phone, password);
	}
	public Optional<User> verifyByEmailAndPassword(String email,String password)
	{
		return repository.verifyByEmailAndPassword();
	}

}
