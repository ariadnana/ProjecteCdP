//Ariadna Xicota
package Modelo;

import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MetodoHare implements MetodoCalculo {
	
	
	public MetodoHare() {
		super();
	}

	@Override
	public Iterable<Entry<String, Integer>> escanosPorCandidatura(Iterable<Entry<String, Integer>> escrutinio,
			int listonElectoral, int totalvotos, int totalescanos) {
		int cociente = totalvotos/totalescanos;
		int escanosRepartidos = 0;
		int liston = totalvotos*listonElectoral/100;
		Map<String, Integer> escanos = new HashMap<String, Integer>();
		Map<String, Integer> residuos = new HashMap<String, Integer>();
		for(Entry<String, Integer> candidatura: escrutinio){
			if (candidatura.getValue()>liston){
				escanos.put(candidatura.getKey(), (candidatura.getValue()/cociente));
				escanosRepartidos += escanos.get(candidatura.getKey());
				residuos.put(candidatura.getKey(), (candidatura.getValue()-cociente*escanos.get(candidatura.getKey())));
			} else {
				escanos.put(candidatura.getKey(), 0);
				residuos.put(candidatura.getKey(), 0);
			}
		}
		List<String> escanosResiduo = new ArrayList<String>();
		while(escanosRepartidos!=totalescanos){
			int max=0;
			String candidaturaMax="";
			for(Entry<String, Integer> candidatura: residuos.entrySet()){
				if (candidatura.getValue()>max && !escanosResiduo.contains(candidatura.getKey())){
					candidaturaMax = candidatura.getKey();
					max = candidatura.getValue();
				}
			}
			escanos.put(candidaturaMax, escanos.get(candidaturaMax)+1);
			escanosResiduo.add(candidaturaMax);
			escanosRepartidos ++;
		}
		return escanos.entrySet();
	}
}
