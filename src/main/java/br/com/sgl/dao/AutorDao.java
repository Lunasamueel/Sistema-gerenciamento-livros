package br.com.sgl.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.sgl.model.Autor;

public class AutorDao {
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_gerenciamento_livros");

	public AutorDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Autor buscarAutorPorNomeLivro(String titulo) {
		EntityManager entityManager = emf.createEntityManager();
		Autor autor = null;
		
		try {
			String jpql = "SELECT a \r\n"
					+ "FROM Autor a \r\n"
					+ "JOIN a.livros l \r\n"
					+ "WHERE l.titulo = :titulo";
			TypedQuery<Autor> query = entityManager.createQuery(jpql, Autor.class);
            query.setParameter("titulo", titulo);
            autor = query.getSingleResult();
		} catch (NoResultException e) {
            // Caso não encontre resultados, você pode lidar com a exceção se necessário
            autor = (Autor) List.of(); // Retorna uma lista vazia
        } finally {
            entityManager.close();
        }
		
		return autor;

	}
	


	public void cadastrarAutor(Autor autor) {
		EntityManager em = emf.createEntityManager();
		System.out.println(autor);
		try {
			em.getTransaction().begin();
			
			if (autor.getAutor_id() == null) {
				em.persist(autor);
			}
			
			
			em.getTransaction().commit();
	        System.out.println("Autor Cadastrado com sucesso!");
 
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	}

	
	public void atualizarAutorPorId(Long id, String nome, String nacionalidade) {
		EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();

            // Encontrar o livro pelo ID
            Autor autor = em.find(Autor.class, id);

            if (autor != null) {
                // Atualizar os campos
                autor.setNome(nome);  
                autor.setNacionalidade(nacionalidade);
                // Persistir as mudanças
                em.merge(autor);

                em.getTransaction().commit();
                System.out.println("Autor atualizado com sucesso!");
            } else {
                System.out.println("Autor com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
	
	public List<Autor> buscarTodosOsAutores() {
		EntityManager em = emf.createEntityManager();
		 List<Autor> autores = null;

	        try {
	        	em.getTransaction().begin();
	            // Criando consulta JPQL para selecionar todos os livros
	            autores = em.createQuery("SELECT autor FROM Autor autor", Autor.class).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	        return autores;
		
	  
	}
	
//	
	public void excluirPorId(Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Autor autor = em.find(Autor.class, id);
			
			if(autor != null) {
				em.remove(autor);
				em.getTransaction().commit();
				System.out.println("autor excluido com sucesso!");
			} else {
				System.out.println("autor com ID " + id + " não encontrado.");
			}
			
		} catch (Exception e) {
			em.getTransaction().rollback();
        } finally {
            em.close();
        }
	}
	
	public Autor BuscarPeloNomeDoAutor(String nomeAutor) {
		EntityManager em = emf.createEntityManager();
		 String jpql = "SELECT a FROM Autor a WHERE a.nome = :nomeAutor";

	        // Criar a query e definir o parâmetro do nome do autor
	        TypedQuery<Autor> query = em.createQuery(jpql, Autor.class);
	        query.setParameter("nomeAutor", nomeAutor);

	        // Executar a consulta e retornar o autor (assumindo que o nome é único)
	        List<Autor> autores = query.getResultList();
	        
	        // Retorna o primeiro autor encontrado ou null se nenhum autor for encontrado
	        return autores.isEmpty() ? null : autores.get(0);
	}
}
