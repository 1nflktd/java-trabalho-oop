package javaapplication1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	
	private static ArrayList<Livro> aLivros;
	private static ArrayList<Usuario> aUsuario;
	private static ArrayList<Rating> aRating;

	/**
	 * @param args
	 *            the command line arguments
	 */
	
	private static void ler() {
		System.out.println("LerOBJ");
		SerializarClasse serClasse = new SerializarClasse();

		aLivros = null;
		aUsuario = null;
		aRating = null;

		try {
			serClasse.lerObj(aLivros, "C:/listaLivro.obj");
			serClasse.lerObj(aUsuario, "C:/listaUsuario.obj");
			serClasse.lerObj(aRating, "C:/listaRating.obj");
			return;
		} catch (IOException ex) {
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

		System.out.println("LerCSV");
		try {
			LerCSV obj = new LerCSV();

			aLivros = obj.carregarLivros();
			aUsuario = obj.carregarUsuarios();
			aRating = obj.carregarRating();

			SerializarClasse serializar = new SerializarClasse();
			serializar.serializarLista(aLivros, "C:/listaLivro.obj");
			serializar.serializarLista(aUsuario, "C:/listaUsuario.obj");
			serializar.serializarLista(aRating, "C:/listaRating.obj");

			System.out.println("Arquivos carregados com sucesso!");
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

	}
	
	public static void main(String[] args) {
		
		ler();
		Ordenacao.ordenar(aLivros);

		for	(Livro livro : aLivros) {
			System.out.println(livro.getTitulo());
		}
		
	}

}
