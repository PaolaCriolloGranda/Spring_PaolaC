package com.example.spring.app.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.app.entity.User;
import com.example.spring.app.service.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	//CREAR UN USUARIO
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	//LEER USUARIO POR ID
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value="id")Long userId){
		Optional<User> oUser= userService.findById(userId);
		//si el usuario no esta
		if (!oUser.isPresent()) {
			//devuelve error 404
			return ResponseEntity.notFound().build();
		}
		//user encontrado
		return ResponseEntity.ok(oUser);
		
	}
	//ACTUALIZAR USUARIO
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody User userDetails, @PathVariable (value= "id")Long userid){
		Optional<User> user=userService.findById(userid);
		if (!user.isPresent()) {
			//devuelve error 404
			return ResponseEntity.notFound().build();
		}
		//BeanUtils.copyProperties(userDetails, user.get());
		user.get().setNombre(userDetails.getNombre());
		user.get().setClave(userDetails.getClave());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEstado(userDetails.getEstado());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
		
	}
	//ELIMINAR USUARIO POR ID
	@DeleteMapping("/{id}")
	public  ResponseEntity<?> delete(@PathVariable (value="id") Long userId){
		//verifico si esta en la bd el id
		if (!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteById(userId);
		return ResponseEntity.ok().build();
		
	}
	//MOSTRAR TODOS LOS USUARIOS
	@GetMapping
	
	public List <User> readAll(){
		List<User> users=StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return users;
		
	}
}
