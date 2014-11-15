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

		aLivros = (ArrayList<Livro>) serClasse.<Livro>lerObj("C:/Users/Henrique/Documents/listaLivro.obj");
		aUsuario = (ArrayList<Usuario>) serClasse.<Usuario>lerObj("C:/Users/Henrique/Documents/listaUsuario.obj");
		aRating = (ArrayList<Rating>) serClasse.<Rating>lerObj("C:/Users/Henrique/Documents/listaRating.obj");
	}
	
	public static void carregar()
	{
		System.out.println("Carregar");
		try {
			LerCSV obj = new LerCSV();

			aLivros = obj.carregarLivros();
			aUsuario = obj.carregarUsuarios();
			aRating = obj.carregarRating();

			SerializarClasse serializar = new SerializarClasse();
			serializar.serializarLista(aLivros, "C:/Users/Henrique/Documents/listaLivro.obj");
			serializar.serializarLista(aUsuario, "C:/Users/Henrique/Documents/listaUsuario.obj");
			serializar.serializarLista(aRating, "C:/Users/Henrique/Documents/listaRating.obj");
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
		Ordenacao.ordenar(aLivros);
		for	(Livro livro : aLivros) {
			System.out.println(livro.getTitulo());
		}
	}
}
