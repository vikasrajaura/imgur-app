package ls.imgur.app.restapi;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ls.imgur.app.exception.ResourceNotFoundException;
import ls.imgur.app.model.Role;
import ls.imgur.app.service.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleControllerRest {

	@Autowired
	IRoleService roleService;

	@GetMapping("/{roleId}")
	public ResponseEntity<Role> getEntityById(@PathVariable("roleId") Long roleId)
			throws ResourceNotFoundException {
		Role role = roleService.findById(roleId)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found for roleId:" + roleId));
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Role>(role, headers, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<Role>> getAllEntities() {
		return new ResponseEntity<List<Role>>(roleService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value="", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> createEntity(@Valid @RequestBody Role role) {
		return new ResponseEntity<Role>(roleService.save(role), HttpStatus.CREATED);
	}

	@PutMapping("")
	public ResponseEntity<Role> updateEntity(@Valid @RequestBody Role r) throws ResourceNotFoundException {
		Role role = roleService.findById(r.getRoleId()).orElseThrow(
				() -> new ResourceNotFoundException("Role not found while updating for roleId:" + r.getRoleId()));
		
		role.setRoleName(r.getRoleName());
		role.setDetails(r.getDetails());
		roleService.save(role);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@DeleteMapping("/{roleId}")
	public ResponseEntity deleteEntityById(@PathVariable("roleId") Long roleId) throws ResourceNotFoundException {
		Role role = roleService.findById(roleId).orElseThrow(
				() -> new ResourceNotFoundException("Role not found while deleting for roleId:" + roleId));
		roleService.deleteById(roleId);
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	
	
}
