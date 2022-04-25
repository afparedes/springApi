package com.org.main.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="saludo")
public class Saludo {
	public Saludo() {

	}
	public Saludo(int id,String nombre,String apellido) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;

	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="genero")
	private String genero;
	
	@Column(name="pronombre")
	private String pronombre;

	public String getPronombre() {
		return pronombre;
	}

	public void setPronombre(String pronombre) {
		this.pronombre = pronombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
