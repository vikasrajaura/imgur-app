package ls.imgur.app.restapi;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ls.imgur.app.exception.ResourceNotFoundException;
import ls.imgur.app.model.User;
import ls.imgur.app.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerRest {

	@Autowired
	IUserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<User> getEntityById(@PathVariable("userId") Long userId)
			throws ResourceNotFoundException {
		User user = userService.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for userId:" + userId));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<User>> getAllEntities() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value="", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createEntity(@Valid @RequestBody User user) {
		return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
	}

	@PutMapping("")
	public ResponseEntity<User> updateEntity(@Valid @RequestBody User usr) throws ResourceNotFoundException {
		User user = userService.findById(usr.getUserId()).orElseThrow(
				() -> new ResourceNotFoundException("User not found while updating for userId:" + usr.getUserId()));
		userService.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity deleteEntityById(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
		User user = userService.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User not found while deleting for userId:" + userId));
		userService.deleteById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
}
