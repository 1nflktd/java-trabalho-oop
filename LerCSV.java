package javaapplication1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
 
public class LerCSV
{

    private static final String cvsSplitBy = ",";
    private static final String caminho = "C:/Users/UCS/Documents/ArquivosCsvTrabalhoOOP/";
 
    public LerCSV() 
    {
    	
    }
 
	public HashMap<Long, Livro> carregarLivros()
	{
        String csvFile = caminho + "BX-Books.csv";
        BufferedReader br = null;
        String line;
        HashMap<Long, Livro> mLivros = new HashMap<>();
        
        try 
        {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while((line = br.readLine()) != null) 
            {
        		String[] livro = line.split(cvsSplitBy);
    			Livro l = new Livro(Long.parseLong(livro[0].replaceAll("\\D+","")), livro[1]);
				mLivros.put(l.getIsbn(), l);
				//aLivros.add(l);
            }
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return mLivros;		
	}
	/*
    public ArrayList<Livro> carregarLivros() 
    {
        String csvFile = caminho + "BX-Books.csv";
        BufferedReader br = null;
        String line;
        ArrayList<Livro> aLivros = new ArrayList<>();
        
        try 
        {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while((line = br.readLine()) != null) 
            {
        		String[] livro = line.split(cvsSplitBy);
    			Livro l = new Livro(Long.parseLong(livro[0].replaceAll("\\D+","")), livro[1]);
    			aLivros.add(l);
            }
        }
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return aLivros;
    }
	*/
	
	public HashMap<Long, Usuario> carregarUsuarios()
	{
        String csvFile = caminho + "BX-Users.csv";
        BufferedReader br = null;
        String line;
        HashMap<Long, Usuario> mUsuarios = new HashMap<>();
        
        try 
        {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) 
            {
            	line = line.replaceAll("\"", "");
                String[] usuario = line.split(cvsSplitBy);
               // System.out.println(usuario[0]);
				long id;
				try {
					id = Integer.parseInt(usuario[0].replaceAll("\\D+",""));
				} catch (Exception e) {
					id = 0;
				}
				String city;
				try {
					city = usuario[1].trim();
				} catch (Exception e) {
					city = "";
				}
				String state;
				try {
					state = usuario[2].trim();
				} catch (Exception e) {
					state = "";
				}
				String country;
				try {
					country = usuario[3].trim();
				} catch (Exception e) {
					country = "";
				}
				int age;
				try {
					age = Integer.parseInt(usuario[4].replaceAll("NULL", "0"));
				} catch (Exception e) {
					age = 0;
				}
                Usuario u = new Usuario(id, city, state, country, age);
                mUsuarios.put(id, u);
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return mUsuarios;		
	}
	
	/*
    public ArrayList<Usuario> carregarUsuarios() 
    {
        String csvFile = caminho + "BX-Users.csv";
        BufferedReader br = null;
        String line;
        ArrayList<Usuario> aUsuarios = new ArrayList<>();
        
        try 
        {
            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) 
            {
            	line = line.replaceAll("\"", "");
                String[] usuario = line.split(cvsSplitBy);
               // System.out.println(usuario[0]);
				int id;
				try {
					id = Integer.parseInt(usuario[0].replaceAll("\\D+",""));
				} catch (Exception e) {
					id = 0;
				}
				String city;
				try {
					city = usuario[1].trim();
				} catch (Exception e) {
					city = "";
				}
				String state;
				try {
					state = usuario[2].trim();
				} catch (Exception e) {
					state = "";
				}
				String country;
				try {
					country = usuario[3].trim();
				} catch (Exception e) {
					country = "";
				}
				int age;
				try {
					age = Integer.parseInt(usuario[4].replaceAll("NULL", "0"));
				} catch (Exception e) {
					age = 0;
				}
                Usuario u = new Usuario(id, city, state, country, age);
                aUsuarios.add(u);
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return aUsuarios;
    }
    */
    public ArrayList<Rating> carregarRating() 
    {
        String csvFile = caminho + "BX-Book-Ratings.csv";
        BufferedReader br = null;
        String line;
        ArrayList<Rating> aRating = new ArrayList<>();
        
        try 
        {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) 
            {
                String[] rating = line.split(cvsSplitBy);
				Long usuario_id;
				try {
					usuario_id = Long.parseLong(rating[0].replaceAll("\\D+",""));
				} catch (Exception e) {
					usuario_id = 0L;
				}
				Long isbn;
				try {
					isbn = Long.parseLong(rating[1].replaceAll("\\D+",""));
				} catch (Exception e) {
					isbn = 0L;
				}
				int rating_;
				try {
					rating_= Integer.parseInt(rating[2].replaceAll("\\D+",""));
				} catch (Exception e) {
					rating_= 0;
				}
                Rating r = new Rating(usuario_id, isbn, rating_);
                aRating.add(r);
            }
       } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
        return aRating;
    }
    
}
