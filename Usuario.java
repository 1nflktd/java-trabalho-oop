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
		this.setId(id);
		this.setAge(age);
		this.setCity(city);
		this.setState(state);
		this.setCountry(country);
	}

	@Override
	public int compareTo(Usuario usuario) {
		return this.getCountry().toUpperCase().compareTo(usuario.getCountry().toUpperCase());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
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

}
