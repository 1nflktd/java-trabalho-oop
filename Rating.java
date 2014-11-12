package javaapplication1;

import java.io.Serializable;

public class Rating implements Serializable, Comparable<Rating> {

	private static final long serialVersionUID = 1L;
	private int usuario_id;
	private long isbn;
	private int rating;

	public Rating(int usuario_id, long isbn, int rating) {
		this.setUsuario_id(usuario_id);
		this.setIsbn(isbn);
		this.rating = rating;
	}

	@Override
	public int compareTo(Rating rating) {
		return this.rating - rating.rating;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

}
