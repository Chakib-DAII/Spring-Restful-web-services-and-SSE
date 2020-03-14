package com.DaiiChakib.Restfulwebservices.Controller;

import java.net.URI;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.DaiiChakib.Restfulwebservices.Models.User;
import com.DaiiChakib.Restfulwebservices.dao.userDaoService;
import com.DaiiChakib.Restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private userDaoService service;
	
	@GetMapping("/users")
	public ArrayList<User> retreiveAllUsers()
	{
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public /*ResponseEntity<User>*//*User*/ Resource<User> retreiveUser(@PathVariable(name="id") int id)
	{	
		User user = service.findOne(id);
		if(user == null)
			//throw new RuntimeException();
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new UserNotFoundException("id-"+ id);
		//linking using HATEOAS
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo =  linkTo(methodOn(this.getClass()).retreiveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		return //user;
				//new ResponseEntity<User>(user, HttpStatus.OK);
				resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser = service.save(user);
		//return the path to created User
		URI location =  ServletUriComponentsBuilder
		.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name="id") int id)
	{
		User user = service.deleteById(id);
		if(user == null)
			//throw new RuntimeException();
			//return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			throw new UserNotFoundException("id-"+ id);
		return //user;
				new ResponseEntity<>(HttpStatus.OK);
	}
}
