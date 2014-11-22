package javaapplication1;

import java.util.ArrayList;
import java.util.List;

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

}
