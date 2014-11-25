package javaapplication1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SerializarClasse {
    
	public void serializarLista(ArrayList<?> lista, String caminho) throws IOException
    {
        ObjectOutputStream streamObj = new ObjectOutputStream(new FileOutputStream(caminho));
        streamObj.writeObject(lista);		
    }

    public void serializarLista(HashMap<?, ?> lista, String caminho) throws IOException
    {
        ObjectOutputStream streamObj = new ObjectOutputStream(new FileOutputStream(caminho));
        streamObj.writeObject(lista);		
    }
	
	public HashMap<?, ?> lerObjMap(String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
		return (HashMap<?, ?>) stream.readObject();
    }
	
	public ArrayList<?> lerObjLista(String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
		return (ArrayList<?>) stream.readObject();
    }

}
