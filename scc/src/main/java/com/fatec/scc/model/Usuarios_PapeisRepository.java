package com.fatec.scc.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public class Usuarios_PapeisRepository {
	
	@Transactional
	public void insertWithQuery(Usuarios_Papeis papel) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
	    EntityManager entityManager = emf.createEntityManager();
	    entityManager.createNativeQuery("INSERT INTO usuarios_papeis (usuario_id, role_id) VALUES (?,?,?)")
	      .setParameter(1, papel.getRole_id())
	      .setParameter(2, papel.getUsuario_id())
	     
	      .executeUpdate();
	}

}
