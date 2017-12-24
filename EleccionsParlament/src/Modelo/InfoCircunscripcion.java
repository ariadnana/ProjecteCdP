package Modelo;

public class InfoCircunscripcion {
	private String nombre;
	private int diputados;
	private Iterable<String> candidaturas;
	
	public InfoCircunscripcion(String nombre, int diputados, Iterable<String> candidaturas) {
		super();
		this.nombre = nombre;
		this.diputados = diputados;
		this.candidaturas = candidaturas;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDiputados() {
		return diputados;
	}
	
	public Iterable<String> getCandidaturas() {
		return candidaturas;
	}
	
	public String toString() {
		String s = "Circunscripcion: " + getNombre() + "\n" + "Diputados: " + getDiputados() + "\n" + "Candidaturas:\n";
		for(String candidatura: candidaturas) {
			s += "\t" + candidatura + "\n";
		}
		return s;
	}
}
