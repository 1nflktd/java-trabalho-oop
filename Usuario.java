package javaapplication1;

import java.io.Serializable;
import java.util.ArrayList;

class Usuario implements Serializable, Comparable<Usuario>
{

	private long id;
	private int age;
	private String city;
	private String state;
	private String country;
    private ArrayList<Rating> lista;

	public Usuario(long id, String city, String state, String country, int age)
	{
		setId(id);
		setAge(age);
		setCity(city);
		setState(state);
		setCountry(country);
        lista = new ArrayList<>();
	}

    public Usuario()
    {
        
    }
    
    public void addLista(Rating r) 
	{
        lista.add(r);
    }
    
	@Override
	public int compareTo(Usuario usuario) 
	{
		return this.getCountry().toUpperCase().compareTo(usuario.getCountry().toUpperCase());
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    public ArrayList<Rating> getLista()
    {
        return lista;
    }
    
}
