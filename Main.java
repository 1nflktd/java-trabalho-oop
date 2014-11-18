package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	private static ArrayList<Livro> aLivros;
	private static ArrayList<Usuario> aUsuario;
	private static ArrayList<Rating> aRating;

	private static void ler() throws IOException, ClassNotFoundException {
		System.out.println("Ler");
		aLivros = null;
		aUsuario = null;
		aRating = null;

		SerializarClasse serClasse = new SerializarClasse();

		aLivros = (ArrayList<Livro>) serClasse.<Livro>lerObj("C:/Users/UCS/Documents/listaLivro.obj");
		aUsuario = (ArrayList<Usuario>) serClasse.<Usuario>lerObj("C:/Users/UCS/Documents/listaUsuario.obj");
		aRating = (ArrayList<Rating>) serClasse.<Rating>lerObj("C:/Users/UCS/Documents/listaRating.obj");
	}
	
    public static void carregarRatingUsuario()
    {
        for (Rating r : aRating) {
            for (Usuario user : aUsuario) {
                if (r.getUsuario_id() == user.getId()) {
                    user.addLista(r);
                    break;
                }
            }
            for (Livro livro : aLivros) {
                if (livro.getIsbn() == r.getIsbn()) {
                    livro.addQtde();
                    livro.addSoma(r.getRating());
                }
            }
            /*Usuario user = new Usuario();
            aUsuario.
            user.getUsuarioPorId(r.getUsuario_id());;*/
        }
    }
    
	public static void carregar()
	{
		System.out.println("Carregar");
		try {
			LerCSV obj = new LerCSV();

			aLivros = obj.carregarLivros();
			aUsuario = obj.carregarUsuarios();
			aRating = obj.carregarRating();

            try{
                //Ordenacao.mergeSort(aLivros);
                Ordenacao.mergeSort(aUsuario);
                Ordenacao.mergeSort(aRating);
            } catch (Exception e) {
                System.out.println("Problema ao ordenar: " + e.getMessage());
            }
                
            carregarRatingUsuario();
            
            System.out.println(aUsuario.get(0));
            
			SerializarClasse serializar = new SerializarClasse();
			serializar.serializarLista(aLivros, "C:/Users/UCS/Documents/listaLivro.obj");
			serializar.serializarLista(aUsuario, "C:/Users/UCS/Documents/listaUsuario.obj");
			serializar.serializarLista(aRating, "C:/Users/UCS/Documents/listaRating.obj");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		try {
			ler();
		} catch (Exception e) {
			carregar();
		}
		/*
        try{
            System.out.println("Ordenar Livros");
            Ordenacao.mergeSort(aLivros);
            
            for	(Livro livro : aLivros) {
                System.out.println(livro.getTitulo());
            }

            System.out.println("Ordenar Usuarios");
            Ordenacao.mergeSort(aUsuario);
            for	(Usuario usuario : aUsuario) {
                System.out.println(usuario.getCountry());
            }

            System.out.println("Ordenar Ratings");
            Ordenacao.mergeSort(aRating);
            for	(Rating rating : aRating) {
                System.out.println(rating.getRating());
            }
        } catch (Exception e) {
            System.out.println("Problema ao ordenar: " + e.getMessage());
        }
        */
        
        
        
	}
}
