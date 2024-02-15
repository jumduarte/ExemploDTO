package com.exemploDTO.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemploDTO.dto.UsuarioDTO;
import com.exemploDTO.entities.Usuario;
import com.exemploDTO.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
    	Usuario usuario = new Usuario(usuarioDTO.nome(),usuarioDTO.senha());
    	Usuario salvarUsuario = usuarioRepository.save(usuario);
    	return new UsuarioDTO(salvarUsuario.getId(),salvarUsuario.getNome(),salvarUsuario.getSenha());
    }
    public UsuarioDTO atualizar(Long id,UsuarioDTO usuarioDTO) {
    	Usuario existeUsuario = usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
    	existeUsuario.setNome(usuarioDTO.nome());
    	existeUsuario.setSenha(usuarioDTO.senha());
    	
    	Usuario updateUsuario = usuarioRepository.save(existeUsuario);
    	return new UsuarioDTO(updateUsuario.getId(),updateUsuario.getNome(),updateUsuario.getSenha());
    }
    
    public boolean deleteUsuario(Long id) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
        	usuarioRepository.deleteById(id);
           return true;
        }
        return false;
    }
    public List<Usuario> buscarTodos(){
    	return usuarioRepository.findAll();    
    }
    public Usuario buscarPorId(Long id) {
		Optional <Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElse(null);
	}
}