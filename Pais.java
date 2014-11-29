/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Henrique
 */
public class Pais implements Serializable
{
	private String nome;
	private ArrayList<Rating> listaRating;
	private ArrayList<Usuario> listaUsuario;
	
	public Pais(String nome)
	{
		nome = nome;
		listaRating = new ArrayList<>();
		listaUsuario = new ArrayList<>();
	}
	
	public void addListaRating(Rating r)
	{
		listaRating.add(r);
	}
	
	public void addListaUsuario(Usuario u)
	{
		listaUsuario.add(u);
	}
	
	public ArrayList<Rating> getListaRating()
	{
		return listaRating;
	}
	
	public ArrayList<Usuario> getListaUsuario()
	{
		return listaUsuario;
	}
	
}