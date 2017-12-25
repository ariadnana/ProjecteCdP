package Modelo;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Elecciones implements ModeloElecciones {
	private Map<String, Circunscripcion> resultados;
	private int liston;
	
	public Elecciones(Iterable<InfoCircunscripcion> circunscripciones,
			int liston) {
		super();
		this.resultados = IterableToHashMap(circunscripciones);
		this.liston = liston;
	}
	
	public Elecciones(Map<String, Circunscripcion> resultados, int liston) {
		super();
		this.resultados = resultados;
		this.liston = liston;
	}

	public boolean actualizarEscrutinio(String circunscripcion, 
			String candidatura, int votos){
		if(circunscripcion != null && candidatura != null && votos > 0) {
			resultados.get(circunscripcion).getVotaciones().put(candidatura, votos);
			return true;
		}
		return false;
	}
	
	public Iterable<Entry<String,Integer>> escañosCircunscripcion(String circunscripción,
			MetodoCalculo m){
		return m.escañosPorCandidatura(resultados.get(circunscripción).getVotaciones().entrySet(),
				liston, resultados.get(circunscripción).getVotosTotalesCircunscripcion(), 
				resultados.get(circunscripción).getDiputados());
	}
	
	//els dos metodes d'escanys no els he comprovat pero ho he escrit perque no dones error
	public Iterable<Entry<String,Integer>> escañosTotales(MetodoCalculo m){
		Map<String, Integer> mapEscaños = new HashMap<String, Integer>();
		for(Map.Entry<String, Circunscripcion> entry: this.resultados.entrySet()) {
			for(Map.Entry<String, Integer> i: escañosCircunscripcion(entry.getKey(), m)) {
				mapEscaños.put(i.getKey(), i.getValue() + mapEscaños.get(i.getKey()));
			}
		}
		return mapEscaños.entrySet();
	}
	
	public int getVotosTotales(){
		int votosTotales = 0;
		for(Map.Entry<String, Circunscripcion> entry: this.resultados.entrySet()) {
			votosTotales += entry.getValue().getVotosTotalesCircunscripcion();
		}
		return votosTotales;
	}
	
	public HashMap<String, Circunscripcion> 
			IterableToHashMap(Iterable<InfoCircunscripcion> circunscripciones){
		HashMap<String, Circunscripcion> map = new HashMap<String, Circunscripcion>();
		if(circunscripciones != null) {
			for(InfoCircunscripcion c : circunscripciones) {
				Circunscripcion cir = new Circunscripcion(c.getNombre(), c.getDiputados(),
						c.getCandidaturas());
				map.put(c.getNombre(), cir);
			    }
		}
		return map;	
	}

	public Map<String, Circunscripcion> getResultados() {
		return resultados;
	}

	public int getListon() {
		return liston;
	}
	
	
	
	
}
