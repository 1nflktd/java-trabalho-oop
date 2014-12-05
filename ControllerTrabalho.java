package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ControllerTrabalho 
{
	
	private static ArrayList<Rating> aRating;
	
	private static LinkedHashMap<Long, Livro> mLivros;
	private static LinkedHashMap<Long, Usuario> mUsuarios;
	
	private static LinkedHashMap<String, Pais> mPaises = new LinkedHashMap<>();
	
    private static final String caminho = "C:/Users/Henrique/Documents/ArquivosCsvTrabalhoOOP/";

	public static void ler() throws IOException, ClassNotFoundException 
	{
		aRating = null;
		SerializarClasse serClasse = new SerializarClasse();
		mPaises = (LinkedHashMap<String, Pais>) serClasse.<Pais>lerObjMap(caminho + "listaPais.obj");
		mLivros = (LinkedHashMap<Long, Livro>) serClasse.<Livro>lerObjMap(caminho + "listaLivro.obj");
		mUsuarios = (LinkedHashMap<Long, Usuario>) serClasse.<Usuario>lerObjMap(caminho + "listaUsuario.obj");
		aRating = (ArrayList<Rating>) serClasse.<Rating>lerObjLista(caminho + "listaRating.obj");
	}
	
    public static void carregarRatingUsuario()
	{
        for (Rating r : aRating) 
        {
			if (mUsuarios.containsKey(r.getUsuario_id()) && mLivros.containsKey(r.getIsbn()))
			{
				Usuario user = mUsuarios.get(r.getUsuario_id());
				user.addLista(r);
				mUsuarios.put(r.getUsuario_id(), user);

				Livro livro = mLivros.get(r.getIsbn());
				livro.addQtde();
				livro.addSoma(r.getRating());
				livro.addLista(r);
				mLivros.put(r.getIsbn(), livro);

				addListaPaises(user.getCountry(), r, user);
			}
		}
	}
    
	public static void addListaPaises(String pais, Rating rating, Usuario user) 
	{
		Pais p = mPaises.get(pais);
		if (p == null) 
		{
			p = new Pais(pais);
		}
		p.addListaRating(rating);
		p.addListaUsuario(user);
		mPaises.put(pais, p);
	}
	
	public static LinkedHashMap<Long, Livro> calcularRatingLivroPais(String pais)
	{
		Pais p = mPaises.get(pais);
		if (p == null) 
		{
			return null;
		}
		ArrayList<Rating> listaRatingPais = p.getListaRating();
		LinkedHashMap<Long, Livro> listaRatingLivro = new LinkedHashMap<>();
		for (Rating r : listaRatingPais)
		{
			Livro livro = listaRatingLivro.get(r.getIsbn());
			if (livro == null)
			{
				Livro attrLivro = mLivros.get(r.getIsbn());
				livro = new Livro(attrLivro.getIsbn(), attrLivro.getTitulo());
			}
			livro.addQtde();
			livro.addSoma(r.getRating());
			listaRatingLivro.put(r.getIsbn(), livro);
		}
		return listaRatingLivro;
	}
	
	public static void carregar()
	{
		try 
		{
			LerCSV obj = new LerCSV();
			
			mLivros = obj.carregarLivros();
			mUsuarios = obj.carregarUsuarios();
			aRating = obj.carregarRating();
			
            try
			{
                Ordenacao.mergeSort(aRating);
            } catch (Exception e) {
                System.out.println("Problema ao ordenar: " + e.getMessage());
            }
			
            carregarRatingUsuario();
			
			SerializarClasse serializar = new SerializarClasse();
			
			serializar.serializarLista(mPaises, caminho + "listaPais.obj");
			serializar.serializarLista(mLivros, caminho + "listaLivro.obj");
			serializar.serializarLista(mUsuarios, caminho + "listaUsuario.obj");
			serializar.serializarLista(aRating, caminho + "listaRating.obj");
		}
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}
	
	public static void mostrarMelhoresAvaliados()
	{
		Ordenacao.mergeSort(mLivros);
		System.out.println("20 melhores avaliados");
		int counter = 0;
		double mediaAnt = 0;
        for (Map.Entry<Long, Livro> l : mLivros.entrySet())
        {
			if (counter >= 10 && mediaAnt != l.getValue().getMedia()) 
			{
				break;
			}
            System.out.println("Livro " + l.getValue().getTitulo() + " Rating  " + l.getValue().getMedia());
			counter ++;
			mediaAnt = l.getValue().getMedia();
        }
	}
	
	public static void mostrarMelhoresPais(String pais)
	{
		System.out.println("10 melhores para um pais");
		LinkedHashMap<Long, Livro> listaLivros = calcularRatingLivroPais(pais);
		Ordenacao.mergeSort(listaLivros);
		int counter = 0;
		double mediaAnt = 0;
        for (Map.Entry<Long, Livro> l : listaLivros.entrySet())
        {
			if (counter >= 10 && mediaAnt != l.getValue().getMedia()) 
			{
				break;
			}
            System.out.println("Livro " + l.getValue().getTitulo() + " Rating  " + l.getValue().getMedia());
			counter ++;
			mediaAnt = l.getValue().getMedia();
        }
	}
	
	static <K,V extends Comparable<? super V>> 
	List<Map.Entry<K, V>> entriesSortedByValues(Map<K,V> map) 
	{
		List<Map.Entry<K,V>> sortedEntries = new ArrayList<>(map.entrySet());
		Collections.sort(sortedEntries, (Map.Entry<K,V> e1, Map.Entry<K,V> e2) -> e2.getValue().compareTo(e1.getValue()));
		return sortedEntries;
	}
	
	public static void mostrarPaisesComMaisLeitores()
	{
		LinkedHashMap<String, Pais> aux = mPaises;
		Map<String, Integer> listaAux = new TreeMap<>();
        for (Map.Entry<String, Pais> a : aux.entrySet())
		{
			listaAux.put(a.getKey(), a.getValue().getTamanhoListaUsuarios());
		}
		List<Map.Entry<String, Integer>> listaSorteada = entriesSortedByValues(listaAux);
		int counter = 0;
		TreeMap<String, Integer> listaFinal = new TreeMap<>();
		
		for(Map.Entry<String, Integer> e : listaSorteada)
		{
			if (counter >= 10) 
			{
				break;
			}
			counter ++;
			listaFinal.put(e.getKey(), e.getValue());
		}
		
		for (Map.Entry<String, Integer> a : listaFinal.entrySet())
		{
			System.out.println("Pais " + a.getKey() + " Numero de leitores " + a.getValue());
		}
	}
	
	public static void recomendarLivrosParaUsuario(long usuario_id)
	{
		Usuario user = mUsuarios.get(usuario_id);
		if(user == null)
		{
			System.out.println("Usuario nao encontrado!");
		}
		else
		{
			ArrayList<Rating> lista = user.getLista();
			Livro livroComMaiorNota = null;
			int maiorNota = 0;
			for (Rating r : lista)
			{
				if (r.getRating() >= maiorNota) 
				{
					Livro l = mLivros.get(r.getIsbn());
					if (l != null) 
					{
						maiorNota = r.getRating();
						livroComMaiorNota = l;
					}
				}
			}
			if (livroComMaiorNota != null) 
			{
				ArrayList<Rating> lista1 = livroComMaiorNota.getLista();
				int maiorRating = 0;
				Usuario usuarioMaiorRating = null;
				for (Rating r : lista1)
				{
					if (r.getRating() >= maiorRating && r.getUsuario_id() != usuario_id) 
					{
						Usuario user1 = mUsuarios.get(r.getUsuario_id());
						if (user1 != null && user1.getLista().size() > 1) 
						{
							usuarioMaiorRating = user1;
							maiorRating = r.getRating();
						}
					}
				}
				
				if (usuarioMaiorRating != null) 
				{
					ArrayList<Rating> lista2 = usuarioMaiorRating.getLista();
					for(Rating r : lista2)
					{
						if (r.getRating() >= 8) 
						{
							Livro l = mLivros.get(r.getIsbn());
							if (l != null) 
							{
								System.out.println("Livro " + l.getTitulo() + " ISBN " + l.getIsbn() + " Rating " + r.getRating());
							}
						}
					}
				}
			}
			else
			{
				System.out.println("Nao há como recomendar livros para esse usuário!");
			}
		}
	}
	
}