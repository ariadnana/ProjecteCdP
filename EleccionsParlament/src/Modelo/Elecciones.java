package Modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Elecciones implements ModeloElecciones {
	//en actualizarEscrutinio posa l'enunciat que s'ha d'actualitzar els vots totals, se que es pot calcular per� no se si volen un atribut. 
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
	
	public Iterable<Entry<String,Integer>> escanosCircunscripcion(String circunscripcion,
			MetodoCalculo m){
		return m.escanosPorCandidatura(resultados.get(circunscripcion).getVotaciones().entrySet(),
				liston, resultados.get(circunscripcion).getVotosTotalesCircunscripcion(), 
				resultados.get(circunscripcion).getDiputados());
	}
	
	//els dos metodes d'escanys no els he comprovat pero ho he escrit perque no dones error
	public Iterable<Entry<String,Integer>> escanosTotales(MetodoCalculo m){
		Map<String, Integer> mapEscanos = new HashMap<String, Integer>();
		for(Map.Entry<String, Circunscripcion> entry: this.resultados.entrySet()) {
			for(Map.Entry<String, Integer> i: escanosCircunscripcion(entry.getKey(), m)) {
				if(mapEscanos.containsKey(i.getKey())){
					mapEscanos.put(i.getKey(), i.getValue() + mapEscanos.get(i.getKey()));
				}else{
					mapEscanos.put(i.getKey(), i.getValue());
				}
			}
		}
		return mapEscanos.entrySet();
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

	public void setListon(int l) {
		liston=l;
	}
	
	
	
	
}
