package org.jsp.UserAndProduct.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.UserAndProduct.Dto.User;
import org.jsp.UserAndProduct.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
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

	public List<User> findAll() {
		return repository.findAll();
	}

	public Optional<User> verify(long phone, String password) {
		return repository.verify(phone, password);
	}
}
