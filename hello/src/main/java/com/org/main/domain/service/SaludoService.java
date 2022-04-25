package com.org.main.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.main.domain.repository.SaludoRepository;
import com.org.main.persistence.entity.Saludo;

@Service
public class SaludoService {
	@Autowired
	private SaludoRepository saludoRepository;
	
	public List<Saludo> getAll(){
		return saludoRepository.getAll();
	}
	public Optional<Saludo> getSaludo(int id){
		return saludoRepository.getById(id);
	}
	public Optional<List<Saludo>> getSaludo(String nombre){
		return saludoRepository.getSaludo(nombre);
	}
	public Optional<List<Saludo>> getSaludoFormal(String nombre){
		return saludoRepository.getSaludo(nombre);
	}
	public Saludo save(Saludo saludo) {
		return saludoRepository.save(saludo);
	}
	public Saludo saveSaludo(Saludo saludo) {
		return saludoRepository.save(saludo);
	}
	public boolean delete(int id) {
		
		return saludoRepository.delete(id);
	}
	public Optional<Saludo> updateById(int id, Saludo saludo){
		return saludoRepository.updateById(id, saludo);
	}
	public String pronombres(Saludo saludo){
		return ((saludo.getPronombre() == null) ? "Saludos estimado " : "Saludos "+saludo.getPronombre()+" ");
	}

	public  String testMockito() {
		return "hola";
	}

}
