package com.chilcho.demo.holamundo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable{
	
	
	/**
	 * Serial Version 
	 */
	private static final long serialVersionUID = -7045221184159048862L;
	
	private Integer id;
	private String nombre;
	private String paterno;
	private String materno;
	private Integer edad;
	private String direccion;
}
