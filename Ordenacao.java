package javaapplication1;

import java.util.List;

public class Ordenacao {

	public static void ordenar(List lista) {
		ordenar(lista, 0, lista.size() - 1);
	}

	private static void ordenar(List lista, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separar(lista, inicio, fim);
			ordenar(lista, inicio, posicaoPivo - 1);
			ordenar(lista, posicaoPivo + 1, fim);
		}
	}

	@SuppressWarnings("unchecked")
	private static int separar(List lista, int inicio, int fim) {
		Comparable<Object> pivo = ((Comparable<Object>) lista.get(inicio));
		int i = inicio + 1, f = fim;
		while (i <= f) {
			if (pivo.compareTo((Comparable<Object>) lista.get(i)) >= 0)
				i++;
			else if (pivo.compareTo((Comparable<Object>) lista.get(f)) < 0)
				f--;
			else {
				Object troca = lista.get(i);
				lista.set(i, lista.get(f));
				lista.set(f, troca);
				i++;
				f--;
			}
		}
		lista.set(inicio, lista.get(f));
		lista.set(f, pivo);
		return f;
	}

}
