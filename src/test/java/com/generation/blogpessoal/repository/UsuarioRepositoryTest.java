package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

/*indicando que a classe UsuarioRepositoryTest é uma classe de teste,
*que vai rodar em uma porta aleatória em cada teste realizado
**/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

/*
 * cria uma instancia de testes, que define que o ciclo de vida do teste vai
 * respeitar o ciclo de vida da classe(será executado e resetado após o uso)
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@BeforeAll
	void start() {

		/** 
		 * Persiste (Grava) 4 Objetos Usuario no Banco de dados
		 */ 

        repository.save(new Usuario(0L, "Maiar da Silva", "isadora@gmail.com","51 e pinga","https://i.imgur.com/FETvs2O.jpg", "adm"));
		
		repository.save(new Usuario(0L, "Michael da Silva", "michaeltrimundial@gmail.com","nunca fui rebaixado","https://i.imgur.com/FETvs2O.jpg", "adm"));
		
		repository.save(new Usuario(0L, "Fernando da Silva", "brocco@gmail.com","broccolis","https://i.imgur.com/FETvs2O.jpg", "adm"));
		
		repository.save(new Usuario(0L, "Mayara da Costa", "will31smith@gmail.com","cenoura","https://i.imgur.com/FETvs2O.jpg", "adm"));
	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		/**
		 *  Executa o método findByUsuario para buscar um usuario pelo nome (joao@email.com.br)
		 */
		Optional<Usuario> usuario = repository.findByUsuario("isadora@gmail.com");

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou o usuario joao@email.com.br" é verdadeira
		 *  Se for verdaeira, o teste passa, senão o teste falha. 
		 */
		assertTrue(usuario.get().getUsuario().equals("isadora@gmail.com"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		/**
		 *  Executa o método findAllByNomeContainingIgnoreCase para buscar todos os usuarios cujo nome contenha
		 *  a palavra "Silva"
		 */
		List<Usuario> listaDeUsuarios = repository.findAllByNomeContainingIgnoreCase("Silva");

		/**
		 * Verifica se a afirmação: "É verdade que a busca retornou 3 usuarios, cujo nome possua a palavra Silva" 
		 * é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertEquals(3, listaDeUsuarios.size());

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na primeira posição da Lista o usuario 
		 * João da Silva" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Maiar da Silva"));

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na segunda posição da Lista a usuaria 
		 * Manuela da Silva" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Michael da Silva"));

		/**
		 *  Verifica se a afirmação: "É verdade que a busca retornou na primeira posição da Lista a usuaria 
		 * Adriana da Silva" é verdadeira
		 * Se for verdadeira, o teste passa, senão o teste falha.
		 */
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Fernando da Silva"));
		
	}

	@AfterAll
	public void end() {
		repository.deleteAll();
	}
}
