package br.com.sgl.tests;
import java.util.List;

import br.com.sgl.dao.AutorDao;
import br.com.sgl.dao.LivroDao;
import br.com.sgl.model.Autor;
import br.com.sgl.model.Livro;

public class CadastroLivros {

	public static void main(String[] args) {
		
		// INSTANCIAS de AUTOR
		Autor autor3 = new Autor("Raul Pompeia", "Brasileiro");
		Autor autor4 = new Autor("Machado de Assis", "Brasileiro");
		
		// CADASTRAR AUTOR
		AutorDao autorDao = new AutorDao();
//		autorDao.cadastrarAutor(autor3);
//		autorDao.cadastrarAutor(autor4);
		
		
		// INSTANCIAS DE LIVRO
		Livro livro8 = new Livro("O Ateneu", "xyz02506", 1998, autor3);
//		Livro livro7 = new Livro("principe caspian", "xyz0544", 1999, autor2);
		Livro livro5 = new Livro("Helena", "xyz0544", 1999, autor4);


		// CADASTRAR LIVROS
		LivroDao livroDao = new LivroDao();
//		livroDao.cadastrarLivro(livro8);	
//		livroDao.cadastrarLivro(livro7);
//		livroDao.cadastrarLivro(livro5);
		
		
		// EXCLUIR LIVRO
//		livroDao.excluirPorId(2l);
//		livroDao.excluirPorId(7l);
//		livroDao.excluirPorId(8l);
		
		
		//EXCLUIR AUTOR POR ID
//		autorDao.excluirPorId(6l);
//		autorDao.excluirPorId(6l);
//		autorDao.excluirPorId(7l);
		
	    // ATUALIZAR AUTOR POR ID
		//autorDao.atualizarAutorPorId(2l, "C.S.Lewis", "Hungaro");
		
		
		// LISTAR TODOS OS LIVROS
//		List<Livro> livros = livroDao.buscarTodos();
//		
//		if (livros != null && !livros.isEmpty()) {
//            System.out.println("Livros no banco de dados:");
//            for (Livro livro : livros) {
//                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() +
//                                   ", ISBN: " + livro.getIsbn() + ", Ano: " + livro.getAnoPublicacao() 
//                                    );
//            }
//        } else {
//            System.out.println("Nenhum livro encontrado.");
//        }
		
		
		// BSUCAR TODOS OS AUTORES
//		List<Autor> autores = autorDao.buscarTodosOsAutores();
//		
//		if (autores != null && !autores.isEmpty()) {
//            System.out.println("autores no banco de dados:");
//            for (Autor autor : autores) {
//                System.out.println("ID: " + autor.getAutor_id() + ", nome: " + autor.getNome() +
//                                   ", nacionalidade: " + autor.getNacionalidade());
//            }
//        } else {
//            System.out.println("Nenhum autor encontrado.");
//        }
//		
		
		
		// BUSCAR LIVROS POR AUTOR
//		List<Livro> livros1 = livroDao.buscarLivrosPorNomeAutor("Machado de Assis");
//		System.out.println(livros);
//		if (livros1 != null && !livros1.isEmpty()) {
//            System.out.println("livros no banco de dados:");
//            for (Livro livro : livros1) {
//                System.out.println("ID: " + livro.getId() + ", titulo: " + livro.getTitulo() +
//                                   ", ano pulbicacao: " + livro.getAnoPublicacao());
//            }
//        } else {
//            System.out.println("Nenhum livro encontrado.");
//        }
		

//		BUSCAR AUTOR PELO NOME DO LIVRO
//		Autor autor = autorDao.buscarAutorPorNomeLivro("principe caspian");
//		System.out.println(autor.getNome());
		
		
		
		List<Autor> a = autorDao.BuscarAutoresPorNacionalidade("Bugaro");
		if (a != null && !a.isEmpty()) {
          System.out.println("autores no banco de dados:");
          for (Autor autor : a) {
              System.out.println("ID: " + autor.getAutor_id() + ", nome: " + autor.getNome() +
                                 ", nacionalidade: " + autor.getNacionalidade());
          }
      } else {
          System.out.println("Nenhum autor encontrado.");
      }
		
	}	
}
