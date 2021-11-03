package com.fatec.scc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fatec.scc.controller.ClienteController;
import com.fatec.scc.model.Usuario;
import com.fatec.scc.model.UsuarioRepository;
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
	}
}
