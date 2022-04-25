package com.org.main.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.main.domain.service.SaludoService;
import com.org.main.persistence.entity.Saludo;

@RestController
@RequestMapping("/saludo")
public class HelloController {
	@Autowired
	private SaludoService saludoService;

	@GetMapping("/test34")
	public ResponseEntity<String> getTest(){
		return new ResponseEntity<>("hola23",HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Saludo>> getAllSaludos(){
		return new ResponseEntity<>(saludoService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/formal")
	public ResponseEntity<List<String>> getSaludoFormal(@RequestParam Optional<String> nombre) {
		
		
		if(nombre.isPresent()) {
			return new ResponseEntity<>(saludoService.getAll().stream().map(p-> saludoService.pronombres(p)+p.getNombre()).collect(Collectors.toList()),HttpStatus.OK);
	    }else {
	    	Optional<List<Saludo>>temp=saludoService.getSaludoFormal(nombre.get());
	    	if(!temp.isPresent()) {
	    
		return new ResponseEntity<>(temp.get().stream().map(p-> saludoService.pronombres(p)+p.getNombre()).collect(Collectors.toList()),HttpStatus.OK);
		}else {
			   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	    }
	}
	@PostMapping("/nuevo")
	public ResponseEntity<Saludo> createSaludo(@RequestBody Saludo saludo) {
		return new ResponseEntity<>(saludoService.save(saludo),HttpStatus.CREATED);
	}
	@PutMapping("/actualizar/{saludoId}")
	public ResponseEntity<Saludo> actualizarSaludo(@PathVariable int saludoId,@RequestBody Saludo saludo){
		Optional<Saludo> temp= saludoService.updateById(saludoId, saludo);
		if(!temp.isPresent()) {
			   
			   return new ResponseEntity<>(temp.get(),HttpStatus.OK);
		}else {
			   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("delete/{saludoId}")
	public ResponseEntity delete(@PathVariable int saludoId) {
	   if(saludoService.delete(saludoId)) {
		   
		   return new ResponseEntity(HttpStatus.OK);
	   }else {
		   return new ResponseEntity(HttpStatus.NOT_FOUND);
	   }
		
	}
	public String testMockito() {
		return saludoService.testMockito();
	}

}
