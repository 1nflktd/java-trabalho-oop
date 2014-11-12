package javaapplication1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerializarClasse {
    
    public void serializarLista(ArrayList<?> lista, String caminho) throws IOException
    {
        ObjectOutputStream streamObj = new ObjectOutputStream(new FileOutputStream(caminho));
        streamObj.writeObject(lista);
        streamObj.close();
    }
    
    public void lerObj(ArrayList<?> aLista, String caminho) throws IOException, ClassNotFoundException
    {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(caminho));
        aLista = (ArrayList<?>) stream.readObject();
        stream.close();
        //return aLista;
    }


}
