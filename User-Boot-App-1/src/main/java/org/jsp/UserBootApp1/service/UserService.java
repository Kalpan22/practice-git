package org.jsp.UserBootApp1.service;

import java.util.List;
import java.util.Optional;

import org.jsp.UserBootApp1.Dao.UserDao;
import org.jsp.UserBootApp1.Dto.ResponseStructure;
import org.jsp.UserBootApp1.Dto.User;
import org.jsp.UserBootApp1.Exception.EmptyUser;
import org.jsp.UserBootApp1.Exception.IdNotFoundException;
import org.jsp.UserBootApp1.Exception.InvalidCredentialExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(user));
		structure.setMessage("User saved");
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findById(user.getId());
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setEmail(user.getEmail());
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			structure.setData(dbUser);
			structure.setMessage("User is updated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = dao.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("User is Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteById(@PathVariable(name = "id") int id) {
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		Optional<User> dbUser = dao.findById(id);
		if (dbUser.isPresent()) {
			dao.deleteById(id);
			structure.setData(true);
			structure.setMessage("User is delete succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findall() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> user = dao.findAllUser();
		if (user.isEmpty() == false) {
			structure.setData(user);
			structure.setMessage("details of the all the users");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.ACCEPTED);
		}
		throw new EmptyUser();
	}

	public ResponseEntity<ResponseStructure<User>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		Optional<User> dbUser = dao.verifyByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if (dbUser.isPresent()) {

			structure.setData(dbUser.get());
			structure.setMessage("verification succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialExceptionHandler();
	}

	public ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		Optional<User> dbUser = dao.verifyByEmail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<User>();
		if (dbUser.isPresent()) {

			structure.setData(dbUser.get());
			structure.setMessage("verification succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		throw new InvalidCredentialExceptionHandler();
	}
}
