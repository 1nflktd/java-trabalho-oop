package javaapplication1;

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
			System.out.println(e.getMessage());
			ControllerTrabalho.carregar();
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
					ControllerTrabalho.mostrarMelhoresAvaliados();
				}
				else
				{
					System.out.println("Digite o pais");
					String pais = leitor.leiaString();
					ControllerTrabalho.mostrarMelhoresPais(pais);
				}
			}
			else if (opc == 2)
			{
				ControllerTrabalho.mostrarPaisesComMaisLeitores();
			}
			else 
			{
				System.out.println("Digite o numero do usuario");
				Long usuario_id = leitor.leiaLong();
				ControllerTrabalho.recomendarLivrosParaUsuario(usuario_id);
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
}
