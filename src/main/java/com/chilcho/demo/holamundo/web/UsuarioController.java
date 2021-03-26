package com.chilcho.demo.holamundo.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chilcho.demo.holamundo.dto.UsuarioDTO;
import com.chilcho.demo.holamundo.service.UsuarioService;

@RestController
@RequestMapping(value="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/usuario")
	public UsuarioDTO getById(@RequestParam(name="id", required = true) Integer id) {
		return usuarioService.getById(id);
	}
	
	@PostMapping("/usuario")
	public Integer create(@RequestBody UsuarioDTO usuario) {
		return usuarioService.create(usuario);
	}
	
	@PutMapping("/usuario")
	public void update(@RequestBody UsuarioDTO usuario) {
		usuarioService.update(usuario);
	}
	
	@DeleteMapping("/usuario")
	public void delete(@RequestParam(name="id", required = true) Integer id) {
		usuarioService.delete(id);
	}
	
	@GetMapping("/usuario/find-all")
	public List<UsuarioDTO> findAll() {
		return usuarioService.findAll();
	}
	
	
}
