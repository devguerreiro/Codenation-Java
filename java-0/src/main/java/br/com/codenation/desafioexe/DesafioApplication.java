package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> numbersList = new ArrayList<>();
		int aux = 1, limit = 350, last_number = 0, next_number = 0;

		for(int i = 0; i < limit; i++) {
			if (numbersList.size() < 15) {
				numbersList.add(next_number);
			}
			next_number = aux + last_number;
			aux = last_number;
			last_number = next_number;
		}
		return numbersList;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}
}