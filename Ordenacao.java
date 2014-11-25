package javaapplication1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ordenacao 
{

	public static <T extends Comparable<T>> void mergeSort(List<T> lista)
	{
		if (lista.size() > 1)
		{
			List<T> temp = lista.subList(0, lista.size() / 2);

			ArrayList<T> esq = new ArrayList<T>(0);
			for (T obj : temp) {
				esq.add(obj);
			}

			temp = lista.subList(lista.size() / 2, lista.size());

			ArrayList<T> dir = new ArrayList<T>(0);
			for (T obj : temp) {
				dir.add(obj);
			}

			if (dir.size() != 1) {
				mergeSort(dir);
			}
			if (esq.size() != 1) {
				mergeSort(esq);
			}

			lista.clear();
			lista.addAll(mergeSortedLists(esq, dir));
		}
	}

	public static <T extends Comparable<T>> List<T> mergeSortedLists(List<T> listaEsq, List<T> listaDir)
	{
		ArrayList<T> lista = new ArrayList<T>();

		while (!listaEsq.isEmpty() && !listaDir.isEmpty())
		{
			if ((listaEsq.get(0)).compareTo(listaDir.get(0)) <= 0) {
				lista.add(listaEsq.remove(0));
			} else {
				lista.add(listaDir.remove(0));
			}
		}

		while (!listaEsq.isEmpty()) {
			lista.add(listaEsq.remove(0));
		}

		while (!listaDir.isEmpty()) {
			lista.add(listaDir.remove(0));
		}

		return lista;
	}
    
    public static <T extends Comparable<T>> void mergeSort(HashMap<Long, T> lista)
	{
		if (lista.size() > 1)
		{
            
            int meio = lista.size() / 2;
            
            int location = 0;

            HashMap<Long, T> esq = new HashMap<Long, T>();
            HashMap<Long, T> dir = new HashMap<Long, T>();
         
            for (Map.Entry<Long, T> obj : lista.entrySet())
            {
                if (location < meio)
                {
                    esq.put(obj.getKey(), obj.getValue());
                } else {
                    dir.put(obj.getKey(), obj.getValue());
                }
                location++;
            }

			if (dir.size() != 1) {
				mergeSort(dir);
			}
			if (esq.size() != 1) {
				mergeSort(esq);
			}

			lista.clear();
			lista.putAll(mergeSortedLists(esq, dir));
		}
	}

	public static <T extends Comparable<T>> HashMap<Long, T> mergeSortedLists(HashMap<Long, T> listaEsq, HashMap<Long, T> listaDir)
	{
		HashMap<Long, T> lista = new HashMap<Long, T>();

        while(!listaEsq.isEmpty() && !listaDir.isEmpty())
        {
            Iterator<Map.Entry<Long, T>> iteratorEsq = listaEsq.entrySet().iterator();
            Iterator<Map.Entry<Long, T>> iteratorDir = listaDir.entrySet().iterator();
            Map.Entry<Long, T> tesq = iteratorEsq.next();
            Map.Entry<Long, T> tdir = iteratorDir.next();
            if (tesq.getValue().compareTo(tdir.getValue()) <= 0) 
            {
                lista.put(tesq.getKey(), tesq.getValue());
                iteratorEsq.remove();
            }
            else
            {
                lista.put(tdir.getKey(), tdir.getValue());
                iteratorDir.remove();
            }
        }

		while (!listaEsq.isEmpty()) {
            Iterator<Map.Entry<Long, T>> iteratorEsq = listaEsq.entrySet().iterator();
            Map.Entry<Long, T> tesq = iteratorEsq.next();
            lista.put(tesq.getKey(), tesq.getValue());
            iteratorEsq.remove();
		}

		while (!listaDir.isEmpty()) {
            Iterator<Map.Entry<Long, T>> iteratorDir = listaDir.entrySet().iterator();
            Map.Entry<Long, T> tdir = iteratorDir.next();
            lista.put(tdir.getKey(), tdir.getValue());
            iteratorDir.remove();
		}

		return lista;
	}

}
