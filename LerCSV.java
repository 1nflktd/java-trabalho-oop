package javaapplication1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 
public class LerCSV
{

    private static final String cvsSplitBy = ",";
 
    public LerCSV() 
    {
    	
    }
 
    public ArrayList<Livro> carregarLivros() 
    {
        String csvFile = "C:/BX-Books.csv";
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
 
    public ArrayList<Usuario> carregarUsuarios() 
    {
        String csvFile = "C:/BX-Users.csv";
        BufferedReader br = null;
        String line;
        ArrayList<Usuario> aUsuarios = new ArrayList<>();
        
        try 
        {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) 
            {
            	line.replaceAll("\"", "");
                String[] usuario = line.split(cvsSplitBy);
               // System.out.println(usuario[0]);
                Usuario u = new Usuario(Integer.parseInt(usuario[0].replaceAll("\\D+","")), usuario[1], usuario[2], usuario[3], Integer.parseInt(usuario[4].replaceAll("NULL", "0")));
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
    
    public ArrayList<Rating> carregarRating() 
    {
        String csvFile = "C:/BX-Book-Ratings.csv";
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
                Rating r = new Rating(Integer.parseInt(rating[0].replaceAll("\\D+","")), Long.parseLong(rating[1].replaceAll("\\D+","")), Integer.parseInt(rating[2].replaceAll("\\D+","")));
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
