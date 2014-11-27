package javaapplication1;

import java.io.Serializable;
import java.util.ArrayList;

public class Livro implements Serializable, Comparable<Livro>
{

	private static final long serialVersionUID = 1L;
	private long isbn;
	private String titulo;
    private int somaRating;
    private int qtdeRating;
    private ArrayList<Rating> lista;

	public Livro()
	{

	}

	public Livro(long isbn, String titulo)
	{
		this.setIsbn(isbn);
		this.setTitulo(titulo);
        somaRating = 0;
        qtdeRating = 0;
        lista = new ArrayList<>();
	}

    public void addLista(Rating r) 
	{
        lista.add(r);
    }

	public ArrayList<Rating> getLista()
    {
        return lista;
    }

	public void addSoma(int valor) {
        somaRating += valor;
    }
    
    public void addQtde() {
        qtdeRating++;
    }
    
    public double getMedia() {
        if (qtdeRating > 0) {
            return (double) somaRating / qtdeRating;
        }
        else
        {
            return somaRating;
        }
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
	public int compareTo(Livro livro) 
    {
		//return this.titulo.toUpperCase().compareTo(livro.titulo.toUpperCase());
        double media = this.getMedia() - livro.getMedia();
        if (media > 0) 
        {
            return 1;
        }
        else if (media < 0)
        {
            return -1;
        }
        else {
            return 0;
        }
	}

}
