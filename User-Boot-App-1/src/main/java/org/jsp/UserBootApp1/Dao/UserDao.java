package org.jsp.UserBootApp1.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.UserBootApp1.Dto.User;
import org.jsp.UserBootApp1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}

	public boolean deleteById(int id) {
		Optional<User> recUser = findById(id);
		if (recUser.isPresent()) {
			repository.delete(recUser.get());
			return true;
		}
		return false;
	}
	public List<User> findAllUser()
	{
		return repository.findAll();
	}
	
	public Optional<User> verifyByPhone(long phone,String password)
	{
		return repository.verifyByPhone(phone, password);
	}
	public Optional<User> verifyByEmail(String email,String password)
	{
		return repository.verifyByEmail(email, password);
	}
	
	

	
}
