package com.chilcho.demo.holamundo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chilcho.demo.holamundo.dto.UsuarioDTO;
import com.chilcho.demo.holamundo.model.Usuario;
import com.chilcho.demo.holamundo.repository.UsuarioRepository;
import com.chilcho.demo.holamundo.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = false)
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public Integer create(UsuarioDTO request) {
		if(request.getNombre() == null || request.getNombre().isEmpty()) {
			log.error("El nombre es requerido");
			return null;
		}
		
		if(request.getPaterno() == null || request.getPaterno().isEmpty()) {
			log.error("El apellido paterno es requerido");
			return null;
		}
		
		Usuario usuario = new Usuario();
		usuario.setNombre(request.getNombre());
		usuario.setPaterno(request.getPaterno());
		usuario.setMaterno(request.getMaterno());
		usuario.setEdad(request.getEdad());
		usuario.setDireccion(request.getDireccion());
		
		usuario = usuarioRepository.save(usuario);
		return usuario.getId();
	}

	@Override
	public void update(UsuarioDTO request) {
		if(request.getId() == null) {
			log.error("El identificador es requerido");
		} else {
			if(request.getNombre() == null || request.getNombre().isEmpty()) {
				log.error("El nombre es requerido");
			} else if(request.getPaterno() == null || request.getPaterno().isEmpty()) {
				log.error("El apellido paterno es requerido");
			} else {
				Usuario usuario = usuarioRepository.getOne(request.getId());
				if(usuario == null) {
					log.error("El usuario no existe");
				} else {
					usuario.setNombre(request.getNombre());
					usuario.setPaterno(request.getPaterno());
					usuario.setMaterno(request.getMaterno());
					usuario.setEdad(request.getEdad());
					usuario.setDireccion(request.getDireccion());
					
					usuarioRepository.save(usuario);
				}
			}
		}
		
	}

	@Override
	public UsuarioDTO getById(Integer id) {
		UsuarioDTO rtrn = null;
		if(id == null) {
			log.error("El identificador es requerido");
		} else {
			Usuario usuario = usuarioRepository.getOne(id);
			if(usuario == null) {
				log.error("El usuario no existe");
				return null;
			} else {
				rtrn = new UsuarioDTO();
				rtrn.setId(usuario.getId());
				rtrn.setNombre(usuario.getNombre());
				rtrn.setPaterno(usuario.getPaterno());
				rtrn.setMaterno(usuario.getMaterno());
				rtrn.setEdad(usuario.getEdad());
				rtrn.setDireccion(usuario.getDireccion());
			}
		}
		
		return rtrn;
	}

	@Override
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = usuarioRepository.findAll();
		if(list.isEmpty()) {
			log.error("No existen usuarios en la base de datos");
			return null;
		}
		return list.stream().map(this::mapper).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if(id == null) {
			log.error("El identificador es requerido");
		} else {
			Usuario usuario = usuarioRepository.getOne(id);
			if(usuario == null) {
				log.error("El usuario no existe");
			} else {
				usuarioRepository.delete(usuario);
			}
		}
	}
	
	private UsuarioDTO mapper(Usuario entity) {
		UsuarioDTO user = new UsuarioDTO();
		user.setId(entity.getId());
		user.setNombre(entity.getNombre());
		user.setPaterno(entity.getPaterno());
		user.setMaterno(entity.getMaterno());
		user.setEdad(entity.getEdad());
		user.setDireccion(entity.getDireccion());
		return user;
	}
	
	
	
}
