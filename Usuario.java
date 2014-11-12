package javaapplication1;

import java.io.Serializable;

class Usuario implements Serializable, Comparable<Usuario>
{
    
    private int id;
    private int age;
    private String city;
    private String state;
    private String country;
    
    public Usuario(int id, String city, String state, String country, int age)
    {
        this.id = id;
        this.age = age;
        this.city = city;
        this.state = state;
        this.country = country;
    }

	@Override
	public int compareTo(Usuario usuario) {
		return this.country.compareTo(usuario.country);
	}
    
}
