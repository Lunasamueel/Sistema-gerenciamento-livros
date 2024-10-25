package br.com.sgl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.sgl.model.Livro;

public class LivroDao {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_gerenciamento_livros");

	public LivroDao() {}


	public void cadastrarLivro(Livro livro) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			System.out.println(livro);
			em.persist(livro);
			em.getTransaction().commit();
	        System.out.println("Livro Cadastrado com sucesso!");
 
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	}

	
	public void atualizarLivroPorId(Long id, String novoTitulo, String novoIsbn, int novoAnoPublicacao) {
		EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();

            // Encontrar o livro pelo ID
            Livro livro = em.find(Livro.class, id);

            if (livro != null) {
                // Atualizar os campos
                livro.setTitulo(novoTitulo);
                livro.setIsbn(novoIsbn);
                livro.setAnoPublicacao(novoAnoPublicacao);
              

                // Persistir as mudanças
                em.merge(livro);

                em.getTransaction().commit();
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Livro com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
	
	public List<Livro> buscarTodos() {
		EntityManager em = emf.createEntityManager();
		 List<Livro> livros = null;

	        try {
	        	em.getTransaction().begin();
	            // Criando consulta JPQL para selecionar todos os livros
	            livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	        return livros;
		
	}
	
//	
	public void excluirPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Livro livro = em.find(Livro.class, id);
			
			if(livro != null) {
				em.remove(livro);
				em.getTransaction().commit();
				System.out.println("Livro excluido com sucesso!");
			} else {
				System.out.println("Livro com ID " + id + " não encontrado.");
			}
			
		} catch (Exception e) {
			em.getTransaction().rollback();
        } finally {
            em.close();
        }
	}
	
	public List<Livro> buscarLivrosPorNomeAutor(String nome) {
		EntityManager entityManager = emf.createEntityManager();
        
        List<Livro> livros = null;

        try {
            // Consulta JPQL para buscar livros de um autor específico
            String jpql = "SELECT l FROM Livro l JOIN l.autor a WHERE a.nome = :nome";
            TypedQuery<Livro> query = entityManager.createQuery(jpql, Livro.class);
            query.setParameter("nome", nome);
            livros = query.getResultList();
        } catch (NoResultException e) {
            // Caso não encontre resultados, você pode lidar com a exceção se necessário
            livros = List.of(); // Retorna uma lista vazia
        } finally {
            entityManager.close();
        }

        return livros;
    }
}
