package org.jsp.Practice.Controller;

import org.jsp.Practice.Dto.ResponseStructure;
import org.jsp.Practice.Dto.User;
import org.jsp.Practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController 
{
	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user)
	{
		return service.saveUser(user);
	}
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user)
	{
		return service.updateUser(user);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name="id") int id)
	{
		return service.findById(id);
	}
	@PostMapping("/users/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyByPhoneAndPassword(@RequestParam long phone,@RequestParam String password)
	{
		return service.verifyUserByPhoneAndPassword(phone, password);
	}
}