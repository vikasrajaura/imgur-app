package ls.imgur.app.restapi;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ls.imgur.app.exception.ResourceNotFoundException;
import ls.imgur.app.model.Privilege;
import ls.imgur.app.service.IPrivilegeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/privileges")
@Slf4j
public class PrivilegeControllerRest {

	@Autowired
	IPrivilegeService privilegeService;

	@GetMapping("/{privId}")
	public ResponseEntity getEntityById(@PathVariable("privId") Long privId)
			throws ResourceNotFoundException {

		Optional<Privilege> privilege = privilegeService.findById(privId);
		if(privilege.isEmpty()) {
			String msg ="Privilege not found for privId: " + privId;
			log.info(msg);
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Privilege>(privilege.get(),  HttpStatus.OK);
		}

	}

	@GetMapping("")
	public ResponseEntity<List<Privilege>> getAllEntities() {
		return new ResponseEntity<List<Privilege>>(privilegeService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value="", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Privilege> createEntity(@Valid @RequestBody Privilege priv) {
		return new ResponseEntity<Privilege>(privilegeService.save(priv), HttpStatus.CREATED);
	}

	@PutMapping("")
	public ResponseEntity<Privilege> updateEntity(@Valid @RequestBody Privilege priv) throws ResourceNotFoundException {
		Privilege privilege = privilegeService.findById(priv.getPrivId()).orElseThrow(
				() -> new ResourceNotFoundException("Privilege not found while updating for privId:" + priv.getPrivId()));
		
		privilege.setPrivName(priv.getPrivName());
		privilege.setDetails(priv.getDetails());
		privilegeService.save(privilege);
		return new ResponseEntity<Privilege>(privilege, HttpStatus.OK);
	}

	@DeleteMapping("/{privId}")
	public ResponseEntity deleteEntityById(@PathVariable("privId") Long privId) throws ResourceNotFoundException {
		Privilege privilege = privilegeService.findById(privId).orElseThrow(
				() -> new ResourceNotFoundException("Privilege not found while deleting for privId:" + privId));
		privilegeService.deleteById(privId);
		return new ResponseEntity<Privilege>(privilege, HttpStatus.OK);
	}
	
	
	
}
