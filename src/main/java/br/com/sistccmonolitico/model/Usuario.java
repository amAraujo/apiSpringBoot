package br.com.sistccmonolitico.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Usuario {

	@Id
	private String id;
	private String nome;
	
}
