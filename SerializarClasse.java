package javaapplication1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SerializarClasse {
    
	public void serializarLista(ArrayList<?> lista, String caminho) throws IOException
    {
        ObjectOutputStream streamObj = new ObjectOutputStream(new FileOutputStream(caminho));
        streamObj.writeObject(lista);		
    }

    public void serializarLista(LinkedHashMap<?, ?> lista, String caminho) throws IOException
    {
        ObjectOutputStream streamObj = new ObjectOutputStream(new FileOutputStream(caminho));
        streamObj.writeObject(lista);		
    }
	
	public LinkedHashMap<?, ?> lerObjMap(String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
		return (LinkedHashMap<?, ?>) stream.readObject();
    }
	/*
	public LinkedHashMap<String, ArrayList<Rating>> lerObjMapPaises(String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
		return (LinkedHashMap<String, ArrayList<Rating>>) stream.readObject();
    }
	*/
	public ArrayList<?> lerObjLista(String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
		return (ArrayList<?>) stream.readObject();
    }

}
