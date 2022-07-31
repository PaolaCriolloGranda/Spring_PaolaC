package com.example.spring.app.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 6614783562766670185L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String nombre;
	private String clave;
//VALDACION PARA EL CORREO QUE SEA UNICO CAMBIE EL NOMBRE Y UNA LONGITUD DE 50
	@Column(name = "mail", nullable = false, length = 50, unique = true)
	private String email;
	private boolean estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEstado() {
		return estado;

	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
