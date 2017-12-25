package Modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Circunscripcion {
	private String territorio;
	private int diputados;
	private Map<String, Integer> votaciones; //<Candidatura, votos>
	
	public Circunscripcion(String territorio, int diputados, Map<String, Integer> votaciones) {
		super();
		this.territorio = territorio;
		this.diputados = diputados;
		this.votaciones = votaciones;
	}
	
	public Circunscripcion(String territorio, int diputados, Iterable<String> candidaturas) {
		super();
		this.territorio = territorio;
		this.diputados = diputados;
		this.votaciones = IterableToHashMap(candidaturas);
	}
	
	public int getVotosTotalesCircunscripcion() {
		int votosTotales = 0;
		for(Map.Entry<String, Integer> entry: this.votaciones.entrySet()) {
			votosTotales += entry.getValue();
		}
		return votosTotales;
	}
	
	public HashMap<String, Integer> IterableToHashMap(Iterable<String> candidaturas){
		HashMap<String, Integer> map_votaciones = new HashMap<String, Integer>();
		if(candidaturas != null) {
			for(String c : candidaturas) {
				map_votaciones.put(c, 0); //0 vots a l'inici
			    }
		}
		return map_votaciones;
	}

	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public int getDiputados() {
		return diputados;
	}


	public Map<String, Integer> getVotaciones() {
		return votaciones;
	}
	
	
}
