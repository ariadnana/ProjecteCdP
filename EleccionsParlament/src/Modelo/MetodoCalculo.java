package Modelo;

import java.util.Map.Entry;

public interface MetodoCalculo {
	public Iterable<Entry<String,Integer>> 
		escañosPorCandidatura(Iterable<Entry<String,Integer>> escrutinio,
	int listonElectoral, int totalvotos,
	int totalescaños);
}
