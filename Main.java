package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main 
{
	
	private static ArrayList<Livro> aLivros;
	private static ArrayList<Usuario> aUsuario;
	private static ArrayList<Rating> aRating;
	
	private static LinkedHashMap<Long, Livro> mLivros;
	private static LinkedHashMap<Long, Usuario> mUsuarios;
	
    private static final String caminho = "C:/Users/Henrique/Documents/ArquivosCsvTrabalhoOOP/";

	private static void ler() throws IOException, ClassNotFoundException {
		System.out.println("Ler");
		aLivros = null;
		aUsuario = null;
		aRating = null;

		SerializarClasse serClasse = new SerializarClasse();

		//aLivros = (ArrayList<Livro>) serClasse.<Livro>lerObj(caminho + "listaLivro.obj");
		//aUsuario = (ArrayList<Usuario>) serClasse.<Usuario>lerObj(caminho + "listaUsuario.obj");
		
		mLivros = (LinkedHashMap<Long, Livro>) serClasse.<Livro>lerObjMap(caminho + "listaLivro.obj");
		mUsuarios = (LinkedHashMap<Long, Usuario>) serClasse.<Usuario>lerObjMap(caminho + "listaUsuario.obj");
		
		aRating = (ArrayList<Rating>) serClasse.<Rating>lerObjLista(caminho + "listaRating.obj");
	}
	
    public static void carregarRatingUsuario()
	{
        for (Rating r : aRating) 
        {
			if (mUsuarios.containsKey(r.getUsuario_id())) 
			{
				Usuario user = mUsuarios.get(r.getUsuario_id());
				user.addLista(r);
				mUsuarios.put(r.getUsuario_id(), user);
			}
			if (mLivros.containsKey(r.getIsbn())) 
            {
				Livro livro = mLivros.get(r.getIsbn());
				livro.addQtde();
				livro.addSoma(r.getRating());
				livro.addLista(r);
				mLivros.put(r.getIsbn(), livro);
			}
		}
	}
    
	public static void carregar()
	{
		System.out.println("Carregar");
		try {
			LerCSV obj = new LerCSV();

			//aLivros = obj.carregarLivros();
			//aUsuario = obj.carregarUsuarios();
			
			mLivros = obj.carregarLivros();
			mUsuarios = obj.carregarUsuarios();
			
			aRating = obj.carregarRating();
			
            try{
                //Ordenacao.mergeSort(aLivros);
                //Ordenacao.mergeSort(aUsuario);
                Ordenacao.mergeSort(aRating);
            } catch (Exception e) {
                System.out.println("Problema ao ordenar: " + e.getMessage());
            }
			
            carregarRatingUsuario();
            
            //System.out.println(aUsuario.get(0));
			
			SerializarClasse serializar = new SerializarClasse();
			
			//serializar.serializarLista(aLivros, caminho + "listaLivro.obj");
			//serializar.serializarLista(aUsuario, caminho + "listaUsuario.obj");
			
			serializar.serializarLista(mLivros, caminho + "listaLivro.obj");
			serializar.serializarLista(mUsuarios, caminho + "listaUsuario.obj");
			
			serializar.serializarLista(aRating, caminho + "listaRating.obj");
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		try 
		{
			ler();
		}
		catch (Exception e) 
		{
			carregar();
		}
		
        Ordenacao.mergeSort(mLivros);
        
        for (Map.Entry<Long, Livro> l : mLivros.entrySet())
        {
            System.out.println("Livro " + l.getValue().getTitulo() + " rAting  " + l.getValue().getMedia());
        }
        
        /*
		for (Map.Entry<Long, Usuario> u : mUsuarios.entrySet()) 
		{
			System.out.println("id " + u.getKey() + " pais " + u.getValue().getCountry());
            //  + " " + u.getValue().getLista()
            for (Rating r : u.getValue().getLista()) 
            {
                System.out.println(" " + r.getIsbn() + " " + r.getRating());
            }
		}
        */

        // 20 melhores avaliados
        // 10 melhores para um pais
        
		
        
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
