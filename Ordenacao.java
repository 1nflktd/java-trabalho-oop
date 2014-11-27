package javaapplication1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

			ArrayList<T> esq = new ArrayList<>(0);
			for (T obj : temp) {
				esq.add(obj);
			}

			temp = lista.subList(lista.size() / 2, lista.size());

			ArrayList<T> dir = new ArrayList<>(0);
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
		ArrayList<T> lista = new ArrayList<>();

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
    
    public static <T extends Comparable<T>> void mergeSort(LinkedHashMap<Long, T> lista)
	{
		if (lista.size() > 1)
		{
            int meio = lista.size() / 2;
			int location = 0;

            LinkedHashMap<Long, T> esq = new LinkedHashMap<>(0);
            LinkedHashMap<Long, T> dir = new LinkedHashMap<>(0);
         
            for (Map.Entry<Long, T> obj : lista.entrySet())
            {
                if (location < meio)
                {
                    esq.put(obj.getKey(), obj.getValue());
					location++;
                }
				else
				{
                    dir.put(obj.getKey(), obj.getValue());
                }
            }

			if (dir.size() != 1)
			{
				mergeSort(dir);
			}
			if (esq.size() != 1) 
			{
				mergeSort(esq);
			}

			lista.clear();
			lista.putAll(mergeSortedLists(esq, dir));
		}
	}

	public static <T extends Comparable<T>> LinkedHashMap<Long, T> mergeSortedLists(LinkedHashMap<Long, T> listaEsq, LinkedHashMap<Long, T> listaDir)
	{
		LinkedHashMap<Long, T> lista = new LinkedHashMap<>();
		Iterator<Map.Entry<Long, T>> iterEsq = listaEsq.entrySet().iterator();
		Iterator<Map.Entry<Long, T>> iterDir = listaDir.entrySet().iterator();
	
		Map.Entry<Long, T> entryEsq = null, entryDir = null;
		
		while (true)
		{
			if (entryEsq == null) {
				try
				{
					entryEsq = iterEsq.next();
				} catch (Exception e)
				{
					entryEsq = null;
				}
			}
			
			if (entryDir == null) {
				try
				{
					entryDir = iterDir.next();
				} catch (Exception e)
				{
					entryDir = null;
				}
			}
			
			if(entryDir == null || entryEsq == null) 
			{
				break;
			}
			
			if (entryEsq.getValue().compareTo(entryDir.getValue()) <= 0)
			{
				lista.put(entryEsq.getKey(), entryEsq.getValue());
				iterEsq.remove();
				entryEsq = null;
			}
			else
			{
				lista.put(entryDir.getKey(), entryDir.getValue());
				iterDir.remove();
				entryDir = null;
			}
		}
		
		for (Map.Entry<Long, T> entry : listaEsq.entrySet()) 
		{
			lista.put(entry.getKey(), entry.getValue());
		}

		for (Map.Entry<Long, T> entry : listaDir.entrySet()) 
		{
			lista.put(entry.getKey(), entry.getValue());
		}
		
		return lista;
	}

}
