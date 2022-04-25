package com.org.main.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.main.persistence.crud.SaludoCrudRepository;
import com.org.main.persistence.entity.Saludo;

@Repository
public class SaludoRepository {
	
	@Autowired
	private SaludoCrudRepository saludoCrudRepository;

	public List<Saludo> getAll(){
		return (List<Saludo>)saludoCrudRepository.findAll();
	}
	public Optional<List<Saludo>> getSaludo(String nombre){
		return Optional.ofNullable(saludoCrudRepository.findAllByNombre(nombre));
	}
	public Saludo save(Saludo saludo){
		return saludoCrudRepository.save(saludo);
	}
	public Optional<Saludo> getById(int id) {
		return saludoCrudRepository.findById(id);
	}
	public boolean delete(int id) {
		if(!getById(id).isPresent()) {
			saludoCrudRepository.deleteById(id); 
			return true;
		}else {
			return false;
		}
    }
	public Optional<Saludo> updateById(int id, Saludo saludo){
		Optional<Saludo> temp=getById(id);
		if(!temp.isPresent()) {
			temp.get().setApellido(saludo.getApellido());
			temp.get().setNombre(saludo.getNombre());
			saludoCrudRepository.save(temp.get());
		}
		return temp;
	}
}
