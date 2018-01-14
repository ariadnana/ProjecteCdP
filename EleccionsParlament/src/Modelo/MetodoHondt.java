//Berta Cumellas
package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

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
			List<String> igualVotos = new ArrayList<String>();//Només utilitzat quan coincideixen els quocients i els numeros de vots totals
			for(Map.Entry<String, List<Double>> listavotos: tablaVotos.entrySet()){
				for(double valor: listavotos.getValue()) {
					if (valor == num && partido != "") {
							if(escr.get(listavotos.getKey()) > escr.get(partido)) {
								num = valor;	
								partido = listavotos.getKey();
								igualVotos.clear();
							}else if(escr.get(listavotos.getKey()) == escr.get(partido)) {
								//si hi haguessin els mateix vots s'haurien d'alternar els escons 
								if(igualVotos.size() == 0) {
									igualVotos.add(partido);
								}
								igualVotos.add(listavotos.getKey());		
								num = valor;
								System.out.println("mateix " + num);
								System.out.println("llista repes " + igualVotos);
							}
					}else if(valor > num) {
						num = valor;	
						partido = listavotos.getKey();
						igualVotos.clear();
					}
				}
			}
			if(!partido.equals("")) {
				if(igualVotos.size() > 0) {
					//Cas en que els nums coincideixen i tmb els numeros de vots totals
					Random rand = new Random();
					for(int j = 0; (j < igualVotos.size()) && ((totalescanos - i + 1) > 0); j++) {
						int x = rand.nextInt(igualVotos.size());
						escanos.put(igualVotos.get(x), escanos.get(partido) + 1);
						System.out.println("eee" + igualVotos.get(x));
						int index = tablaVotos.get(igualVotos.get(x)).indexOf(num);
						tablaVotos.get(igualVotos.get(x)).remove(index);
						igualVotos.remove(x);
						totalescanos -= 1;
					}
				}else {
					int index = tablaVotos.get(partido).indexOf(num);
					tablaVotos.get(partido).remove(index);
					escanos.put(partido, escanos.get(partido) + 1);
				}
			}
		}
		return escanos.entrySet();
	}
}
