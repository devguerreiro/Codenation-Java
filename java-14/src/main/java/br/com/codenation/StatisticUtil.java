package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	public static int average(int[] elements) {
		return (int) Arrays.stream(elements).average().getAsDouble();
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);

		int ocorrenciasModaAnterior = 1, ocorrenciasModaAtual = 1, moda = 0,
		arraySize = elements.length;

		for (int index = 0; index < arraySize; index++) {
			// Exemplo: 4 ocorrências do número 5, e faltam 3 elementos da lista para verificar
			// Logo, não tem necessidade de continuar percorrendo o array, pensando em performance.
			if (ocorrenciasModaAnterior > arraySize - index) {
				return moda;
			} else if (elements[index] == elements[index+1]) {
				ocorrenciasModaAtual++;
			} else if (elements[index] != elements[index+1]) {
				ocorrenciasModaAtual = 1;
			}
			if (ocorrenciasModaAtual > ocorrenciasModaAnterior) {
				ocorrenciasModaAnterior = ocorrenciasModaAtual;
				moda = elements[index];
			}
		}
		return moda;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);

		int position;
		int arraySize = elements.length;

		if (arraySize % 2 == 1) {
			position = Math.floorDiv(arraySize, 2);
			return elements[position];
		}
		position = arraySize / 2;
		return (elements[position] + elements[position-1]) / 2;
	}
}