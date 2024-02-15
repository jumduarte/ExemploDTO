package com.exemploDTO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemploDTO.dto.UsuarioDTO;
import com.exemploDTO.entities.Usuario;
import com.exemploDTO.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuarios", description = "API REST DE GERENCIAMENTO DE Usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza Usuario por ID")
    public ResponseEntity<Usuario> getProductById(@PathVariable Long id) {
    	Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    @Operation(summary = "Apresenta todos os Usuarios")
    public ResponseEntity<List<Usuario>> buscaTodos() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios);
    }
    @PostMapping("/")
    @Operation(summary = "Cadastra um Usuario")
    public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
    	UsuarioDTO salvarUsuario = usuarioService.salvar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarUsuario);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera a Tarefa")
    public ResponseEntity<UsuarioDTO> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
    	UsuarioDTO alteraUsuarioDTO = usuarioService.salvar(usuarioDTO);
        if (alteraUsuarioDTO != null) {
            return ResponseEntity.ok(alteraUsuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o Usuario")
    public ResponseEntity<Usuario> apagar(@PathVariable Long id) {
        boolean deleted = usuarioService.deleteUsuario(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}