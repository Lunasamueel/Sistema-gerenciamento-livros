package br.com.sgl.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	private static final EntityManagerFactory FACTORY = Persistence
			.createEntityManagerFactory("sistema_gerenciamento_livros");

	public static EntityManager getEntityManager() { 
		return FACTORY.createEntityManager();
	}

}
