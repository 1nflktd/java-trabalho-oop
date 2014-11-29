package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main 
{
	
	//private static ArrayList<Livro> aLivros;
	//private static ArrayList<Usuario> aUsuario;
	private static ArrayList<Rating> aRating;
	
	private static LinkedHashMap<Long, Livro> mLivros;
	private static LinkedHashMap<Long, Usuario> mUsuarios;
	
	//private static LinkedHashMap<String, ArrayList<Rating>> mPaises = new LinkedHashMap<>();
	private static LinkedHashMap<String, Pais> mPaises;
	
    private static final String caminho = "C:/Users/Henrique/Documents/ArquivosCsvTrabalhoOOP/";

	private static void ler() throws IOException, ClassNotFoundException 
	{
		System.out.println("Ler");
		//aLivros = null;
		//aUsuario = null;
		aRating = null;
		
		SerializarClasse serClasse = new SerializarClasse();

		//aLivros = (ArrayList<Livro>) serClasse.<Livro>lerObj(caminho + "listaLivro.obj");
		//aUsuario = (ArrayList<Usuario>) serClasse.<Usuario>lerObj(caminho + "listaUsuario.obj");
		
		mLivros = (LinkedHashMap<Long, Livro>) serClasse.<Livro>lerObjMap(caminho + "listaLivro.obj");
		mUsuarios = (LinkedHashMap<Long, Usuario>) serClasse.<Usuario>lerObjMap(caminho + "listaUsuario.obj");
		//mPaises = (LinkedHashMap<String, ArrayList<Rating>>) serClasse.lerObjMapPaises(caminho + "listaPaises.obj");
		
		aRating = (ArrayList<Rating>) serClasse.<Rating>lerObjLista(caminho + "listaRating.obj");

		mPaises = (LinkedHashMap<String, Pais>) serClasse.<Pais>lerObjMap(caminho + "listaPais.obj");
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

				addListaPaises(user.getCountry(), r);
			}
		}
	}
    
	public static void addListaPaises(String pais, Rating rating) 
	{
		Pais p = mPaises.get(pais);
		if (p == null) 
		{
			p = new Pais(pais);
		}
		p.addListaRating(rating);
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
		System.out.println("Carregar");
		try {
			LerCSV obj = new LerCSV();

			//aLivros = obj.carregarLivros();
			//aUsuario = obj.carregarUsuarios();
			
			mLivros = obj.carregarLivros();
			mUsuarios = obj.carregarUsuarios();
			
			mPaises = new LinkedHashMap<>();
			
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

			serializar.serializarLista(mPaises, caminho + "listaPais.obj");
		} catch (IOException e) {
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
	
	public static void main(String[] args) {
		try 
		{
			ler();
		}
		catch (Exception e) 
		{
			carregar();
		}
		
        // 10 melhores para um pais
		mostrarMelhoresAvaliados();
        mostrarMelhoresPais("canada");
		
	}
}
