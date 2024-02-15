package com.exemploDTO.entities;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@SuppressWarnings("deprecation")
	@NotNull
	private String nome;
	
	@SuppressWarnings("deprecation")
	@NotNull
	private String senha;
	
	private String permissao;
	
	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
}