package Modelo;

import java.util.Map.Entry;

public interface ModeloElecciones {
	public boolean actualizarEscrutinio(String circunscripcion, 
			String candidatura, int votos);
	public Iterable<Entry<String,Integer>> escañosCircunscripcion(String circunscripción,
			MetodoCalculo m); 
	public Iterable<Entry<String,Integer>> escañosTotales(MetodoCalculo m);
}
