package Modelo;

import java.util.Map.Entry;

public interface ModeloElecciones {
	public boolean actualizarEscrutinio(String circunscripcion, 
			String candidatura, int votos);
	public Iterable<Entry<String,Integer>> escanosCircunscripcion(String circunscripcion,
			MetodoCalculo m); 
	public Iterable<Entry<String,Integer>> escanosTotales(MetodoCalculo m);
}
