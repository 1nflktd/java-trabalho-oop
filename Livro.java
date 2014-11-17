package javaapplication1;

import java.io.Serializable;

public class Livro implements Serializable, Comparable<Livro>
{

	private static final long serialVersionUID = 1L;
	private long isbn;
	private String titulo;

	public Livro()
	{

	}

	public Livro(long isbn, String titulo)
	{
		this.setIsbn(isbn);
		this.setTitulo(titulo);
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int compareTo(Livro livro) {
		return this.titulo.toUpperCase().compareTo(livro.titulo.toUpperCase());
	}

}
