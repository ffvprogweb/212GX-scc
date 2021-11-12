package com.fatec.scc;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fatec.scc.controller.ClienteController;
import com.fatec.scc.model.Usuario;
import com.fatec.scc.model.UsuarioRepository;
import com.fatec.scc.model.Usuarios_Papeis;
@SpringBootApplication
public class SccApplication {
	Logger logger = LogManager.getLogger(ClienteController.class);
	@Autowired
	UsuarioRepository repository;
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SccApplication.class, args);
	}
	/**
	 * 1) Cadastrar as credenciais do usuario (autenticacao) 2) Cadastrar o papel do usuario (responsabilidades) na aplicacao. 
	 */
	@Autowired
	public void inicializa() {
		// 1-cadastrar as crendenciais do usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Jose da Silva");
		usuario.setLogin("jose");
		usuario.setSenha(passwordEncoder.encode("123"));
		repository.save(usuario);
		usuario = repository.findByLogin("jose");
		logger.info(">>>>>> inicializacao da aplicacao =>  " + usuario.toString());
		usuario = new Usuario();
		usuario.setNome("Maria Silva");
		usuario.setLogin("maria");
		usuario.setSenha(passwordEncoder.encode("456"));
		repository.save(usuario);
		// 2 - cadastrar o papel (responsabilidades)
		//insert into roles values ('ROLE_ADMIN')
		//insert into roles values ('ROLE_USER')
		//insert into usuarios_papeis values ('jose','ROLE_ADMIN')
		//insert into usuarios_papeis values ('maria','ROLE_USER')
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory();
	   // EntityManager entityManager = emf.createEntityManager();
	    Usuarios_Papeis papel = new Usuarios_Papeis();
	    papel.setUsuario_id("jose");
	    papel.setRole_id("ROLE_ADMIN");
	    //entityManager.createNativeQuery("INSERT INTO usuarios_papeis (usuario_id, role_id) VALUES (?,?,?)")
	    //  .setParameter(1, papel.getRole_id())
	     // .setParameter(2, papel.getUsuario_id())
	     
	   //   .executeUpdate();
	}
}
