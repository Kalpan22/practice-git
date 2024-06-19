package org.jsp.UserBootApp.Controller;

import java.util.List;
import java.util.Optional;
import org.jsp.UserBootApp.Dto.ResponseStructure;
import org.jsp.UserBootApp.Dto.User;
import org.jsp.UserBootApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(repository.save(user));
		structure.setMessage("User saved");
		structure.setStatuscode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
	}

	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = repository.findById(user.getId());
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setEmail(user.getEmail());
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			structure.setData(dbUser);
			structure.setMessage("User is updated");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setMessage("User id is invalid");
		structure.setData(null);
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable(name = "id") int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbUser = repository.findById(id);
		if (dbUser.isPresent()) {
			structure.setData(dbUser.get());
			structure.setMessage("User is Found");
			structure.setStatuscode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("User is not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<Boolean>> deleteById(@PathVariable(name = "id") int id) {
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		Optional<User> dbUser = repository.findById(id);
		if (dbUser.isPresent()) {
			repository.delete(dbUser.get());
			structure.setData(true);
			structure.setMessage("User is delete succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Boolean>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData(false);
		structure.setMessage("User is not found");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Boolean>>(structure,HttpStatus.NOT_FOUND);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> findall() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		List<User> user = repository.findAll();
		if (user.isEmpty()==false) {
			structure.setData(repository.findAll());
			structure.setMessage("details of the all the users");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("Users are not there");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<ResponseStructure<List<User>>>(structure,HttpStatus.NOT_FOUND);

	}

	@PostMapping("/users/verify-by-phone")
	public ResponseEntity<ResponseStructure<User>> verifyByPhone(@RequestParam long phone, @RequestParam String password) {
		Optional<User> dbUser = repository.verify(phone, password);
		ResponseStructure<User> structure=new ResponseStructure<User>();
		if (dbUser.isPresent()) {
			
			structure.setData(dbUser.get());
			structure.setMessage("verification succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("invalid phone and password");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());	
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.NOT_FOUND);
	}

	@PostMapping("/users/verify-by-email")
	public ResponseEntity<ResponseStructure<User>> verifyByEmail(@RequestParam String email, @RequestParam String password) {
		Optional<User> dbUser = repository.verify(email, password);
		ResponseStructure<User> structure=new ResponseStructure<User>();
		if (dbUser.isPresent()) {
			
			structure.setData(dbUser.get());
			structure.setMessage("verification succesfully");
			structure.setStatuscode(HttpStatus.ACCEPTED.value());
			
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("invalid email and password");
		structure.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.NOT_FOUND);
	}
}