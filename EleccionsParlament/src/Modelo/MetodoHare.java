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
		int cociente = (int) Math.ceil((double)totalvotos/totalescanos);
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
		if(totalvotos!=0){
			List<String> escanosResiduo = new ArrayList<String>();
			while(escanosRepartidos!=totalescanos && escanosResiduo.size()<escanos.size()){
				int max=0;
				String candidaturaMax="";
				for(Entry<String, Integer> candidatura: residuos.entrySet()){
					if (candidatura.getValue()>max && !escanosResiduo.contains(candidatura.getKey())){
						candidaturaMax = candidatura.getKey();
						max = candidatura.getValue();
					}
				}
				if(candidaturaMax.equals("")){
					escanosResiduo = new ArrayList<String>();
				} else {
					escanos.put(candidaturaMax, escanos.get(candidaturaMax)+1);
					escanosRepartidos ++;
					escanosResiduo.add(candidaturaMax);
				}
			}
		}
		return escanos.entrySet();
	}
}
