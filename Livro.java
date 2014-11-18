package javaapplication1;

import java.io.Serializable;

public class Livro implements Serializable, Comparable<Livro>
{

	private static final long serialVersionUID = 1L;
	private long isbn;
	private String titulo;
    private int somaRating;
    private int qtdeRating;

	public Livro()
	{

	}

	public Livro(long isbn, String titulo)
	{
		this.setIsbn(isbn);
		this.setTitulo(titulo);
        somaRating = 0;
        qtdeRating = 0;
	}

    public void addSoma(int valor) {
        somaRating += valor;
    }
    
    public void addQtde() {
        qtdeRating++;
    }
    
    public double getMedia() {
        return somaRating / qtdeRating;
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
