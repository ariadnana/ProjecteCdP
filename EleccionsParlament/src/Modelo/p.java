package Modelo;

import java.util.HashMap;
import java.util.Map;

public class p {

	public static void main(String[] args) {
		//Només he fet aquesta classe per mirar si funcionaven els metodes
		MetodoCalculo met = new MetodoHare();
		Map<String, Integer> votacionesB = new HashMap<String, Integer>();
		votacionesB.put("PP", 4);
		votacionesB.put("Junts", 10);
		votacionesB.put("E", 5);
		Circunscripcion B = new Circunscripcion("Barcelona", 85, votacionesB);
		System.out.println(votacionesB);
		Map<String, Integer> votacionesT = new HashMap<String, Integer>();
		votacionesT.put("PP", 3);
		votacionesT.put("Junts", 8);
		votacionesT.put("E", 10);
		System.out.println(votacionesT);
		Circunscripcion T = new Circunscripcion("Tarragona", 10, votacionesT);
		Map<String, Integer> votacionesLL = new HashMap<String, Integer>();
		votacionesLL.put("PP", 10);
		votacionesLL.put("Junts", 2);
		votacionesLL.put("E", 4);
		System.out.println(votacionesLL);
		Circunscripcion LL = new Circunscripcion("Lleida", 15, votacionesLL);
		Map<String, Circunscripcion> m = new HashMap<String, Circunscripcion>();
		m.put(B.getTerritorio(), B);
		m.put(T.getTerritorio(), T);
		m.put(LL.getTerritorio(), LL);
		Elecciones desembre = new Elecciones(m, 3);
		System.out.println(desembre.getVotosTotales());
		System.out.println("");
		desembre.actualizarEscrutinio("Barcelona", "PP", 10);
		desembre.actualizarEscrutinio("Tarragona", "E", 20);
		desembre.actualizarEscrutinio("Lleida", "Junts", 5);
		System.out.println(m.get("Barcelona").getVotaciones());
		System.out.println(m.get("Tarragona").getVotaciones());
		System.out.println(m.get("Lleida").getVotaciones());
		System.out.println(desembre.getVotosTotales());
		System.out.println("");
		Elecciones el = new Elecciones(LeerDatos.leerArchivoCircunscripciones("datosEleccionesParlament2017.csv"), 3);
		el.actualizarEscrutinio("Barcelona", "PSC-PSOE", 391000);
		el.actualizarEscrutinio("Barcelona", "PP", 311000);
		el.actualizarEscrutinio("Barcelona", "Cs", 184000);
		el.actualizarEscrutinio("Barcelona", "ERC-CatSi", 73000);
		el.actualizarEscrutinio("Barcelona", "DIALEG", 31000);
		el.actualizarEscrutinio("Barcelona", "CUP", 10000);
		System.out.println(el.escanosCircunscripcion("Barcelona", new MetodoHare()));
	}

}
