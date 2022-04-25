package com.org.main.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.org.main.persistence.entity.Saludo;

public interface SaludoCrudRepository extends CrudRepository<Saludo,Integer>{

	List<Saludo> findAllByNombre(String nombre);

}
