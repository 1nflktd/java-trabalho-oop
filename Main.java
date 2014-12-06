package javaapplication1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main 
{
	public static void main(String[] args) 
	{
		try 
		{
			ControllerTrabalho.ler();
		}
		catch (Exception e) 
		{
			try
			{
				ControllerTrabalho.carregar();
			}
			catch(Exception e2)
			{
				System.out.println(e2.getMessage());
			}
		}
		
		Leitor leitor = new Leitor();
		mostrarMenu();
		int opc = leitor.leiaInt();
		while(opc == 1 || opc == 2 || opc == 3)
		{
			if (opc == 1) 
			{
				mostrarMenuOpc1();
				int opc12 = leitor.leiaInt();
				if (opc12 == 1) 
				{
					LinkedHashMap<Long, Livro> aux = ControllerTrabalho.obterMelhorAvaliados();
					mostrarMelhoresAvaliados(aux);
				}
				else
				{
					System.out.println("Digite o pais");
					String pais = leitor.leiaString();
					try
					{
						LinkedHashMap<Long, Livro> aux = ControllerTrabalho.obterMelhoresPais(pais);
						mostrarMelhoresPais(aux);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			else if (opc == 2)
			{
				TreeMap<String, Integer> aux = ControllerTrabalho.obterPaisesComMaisLeitores();
				mostrarPaisesComMaisLeitores(aux);
			}
			else 
			{
				System.out.println("Digite o numero do usuario");
				Long usuario_id = leitor.leiaLong();
				try
				{
					LinkedHashMap<Long, Livro> aux = ControllerTrabalho.recomendarLivrosParaUsuario(usuario_id);
					mostrarRecomendacaoParaUsuario(aux);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			mostrarMenu();
			opc = leitor.leiaInt();
		}
		
	}
	
	public static void mostrarMenu()
	{
		System.out.println("1 - Ranking dos livros melhor avaliados");
		System.out.println("2 - Consulta dos paises com maior numero de leitores");
		System.out.println("3 - Recomendar livros a usuario");
		System.out.println("0 - Sair");
	}
	
	public static void mostrarMenuOpc1()
	{
		System.out.println("1 - Lista dos 20 melhor avaliados");
		System.out.println("2 - Lista dos 10 melhor avaliados para um país");
	}
	
	public static void mostrarMelhoresAvaliados(LinkedHashMap<Long, Livro> lista)
	{
		System.out.println("20 melhores avaliados");
		for (Map.Entry<Long, Livro> l : lista.entrySet())
        {
            System.out.println("Livro " + l.getValue().getTitulo() + " Rating  " + l.getValue().getMedia());        
		}
	}
	
	public static void mostrarMelhoresPais(LinkedHashMap<Long, Livro> lista)
	{
		System.out.println("10 melhores para um pais");
        for (Map.Entry<Long, Livro> l : lista.entrySet())
        {
            System.out.println("Livro " + l.getValue().getTitulo() + " Rating  " + l.getValue().getMedia());
        }
	}
	
	public static void mostrarPaisesComMaisLeitores(TreeMap<String, Integer> lista)
	{
		for (Map.Entry<String, Integer> a : lista.entrySet())
		{
			System.out.println("Pais " + a.getKey() + " Numero de leitores " + a.getValue());
		}
	}

	public static void mostrarRecomendacaoParaUsuario(LinkedHashMap<Long, Livro> lista)
	{
		for (Map.Entry<Long, Livro> l : lista.entrySet())
		{
			System.out.println("Livro " + l.getValue().getTitulo() + " ISBN " + l.getValue().getIsbn());
		}
	}
	
}
