//Berta Cumellas
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MetodoHondt implements MetodoCalculo{
	
	public Iterable<Entry<String,Integer>> escanosPorCandidatura(Iterable<Entry<String,Integer>> escrutinio, 
			int listonElectoral, int totalvotos,int totalescanos){
		Map<String, Integer> escr = new HashMap<String, Integer>();
		Map<String, List<Double>> tablaVotos = new HashMap<String, List<Double>>();
		Map<String, Integer> escanos = new HashMap<String, Integer>();
		for(Map.Entry<String, Integer> entry: escrutinio) {
			if(entry.getValue()*100 > listonElectoral*totalvotos) {
				List<Double> votosEntreEsc = new ArrayList<Double>();
				for(int i = 1; i <= totalescanos; i++) {
					votosEntreEsc.add(entry.getValue().doubleValue()/i);
				}
				escr.put(entry.getKey(), entry.getValue());
				tablaVotos.put(entry.getKey(), votosEntreEsc);
			}
			escanos.put(entry.getKey(), 0);
		}
		
		for(int i = 1; i <= totalescanos; i++) {
			double num = 0;
			String partido = "";
			for(Map.Entry<String, List<Double>> listavotos: tablaVotos.entrySet()){
				for(double valor: listavotos.getValue()) {
					if (valor == num && partido != "") {
							if(escr.get(listavotos.getKey()) > escr.get(partido)) {
								num = valor;	
								partido = listavotos.getKey();
								//TODO: si hi haguessin els mateix vots s'haurien d'alternar els escons 
						}
					}else if(valor > num) {
						num = valor;	
						partido = listavotos.getKey();
					}
				}
			}
			if(!partido.equals("")) {
				int index = tablaVotos.get(partido).indexOf(num);
				tablaVotos.get(partido).remove(index);
				escanos.put(partido, escanos.get(partido) + 1);
			}
		}
		return escanos.entrySet();
	}
}
