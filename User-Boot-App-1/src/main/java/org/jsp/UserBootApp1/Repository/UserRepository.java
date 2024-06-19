package org.jsp.UserBootApp1.Repository;

import java.util.Optional;

import org.jsp.UserBootApp1.Dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,Integer>
{
	@Query("select u from User u where u.phone=?1 and u.password=?2")
	public Optional<User> verifyByPhone(long phone,String password);
	
	@Query("select u from User u where u.email=?1 and password=?2")
	public Optional<User> verifyByEmail(String email,String password);

	@Query("select u from User u where u.id=?1 and u.password=?2")
	public Optional<User> verifyById(int id,String password);
	
	@Query("select u from User u where u.name=?1")
	public Optional<User> findUserByName(String name);
	
	@Query("select u from User u where u.phone=?1")
	public Optional<User> findUserByphone(long phone);
	
	@Query("select u from User u where u.email=?1")
	public Optional<User> findUserByEmail(String email);
	
	@Query("select u from User u where u.id=?1 and u.phone=?2")
	public Optional<User> verifyByIdAndPhone(int id , long phone);
	
	@Query("select u from User u where u.id=?1 and u.email=?2")
	public Optional<User> verifyByIdAndEmail(int id, String email);
}
