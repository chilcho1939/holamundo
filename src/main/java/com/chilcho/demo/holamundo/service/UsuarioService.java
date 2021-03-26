package com.chilcho.demo.holamundo.service;

import java.util.List;

import com.chilcho.demo.holamundo.dto.UsuarioDTO;

public interface UsuarioService {
	/**
	 * 
	 * @param request
	 * @return Integer
	 */
	Integer create(UsuarioDTO request);
	
	/**
	 * 
	 * @param request
	 */
	void update(UsuarioDTO request);
	
	/**
	 * 
	 * @param id
	 * @return UsuarioDTO
	 */
	UsuarioDTO getById(Integer id);
	
	/**
	 * 
	 * @return List<UsuarioDTO>
	 */
	List<UsuarioDTO> findAll();
	
	/**
	 * 
	 * @param id
	 */
	void delete(Integer id);
}
