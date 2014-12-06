package javaapplication1;

import java.io.Serializable;

public class Rating implements Serializable, Comparable<Rating> 
{

	private static final long serialVersionUID = 1L;
	private long usuario_id;
	private long isbn;
	private int rating;

	public Rating(long usuario_id, long isbn, int rating) 
	{
		this.setUsuario_id(usuario_id);
		this.setIsbn(isbn);
		this.setRating(rating);
	}

	@Override
	public int compareTo(Rating rating) 
	{
		return this.getRating() - rating.getRating();
	}

	public long getUsuario_id() 
	{
		return usuario_id;
	}

	public void setUsuario_id(long usuario_id) 
	{
		this.usuario_id = usuario_id;
	}

	public long getIsbn() 
	{
		return isbn;
	}

	public void setIsbn(long isbn) 
	{
		this.isbn = isbn;
	}

	public int getRating() 
	{
		return rating;
	}

	public void setRating(int rating) 
	{
		this.rating = rating;
	}

}
