package Controlador;


import java.util.Map;

import Modelo.Elecciones;
import Modelo.LeerDatos;
import Modelo.MetodoHondt;
import Vista.VentanaElecciones;
import Vista.VistaElecciones;

public class AplicacionElecciones {

	public static void main(String[] args) {
		String[] list = new String[] {"Barcelona", "Lleida", "Girona", "Tarragona"};
		Elecciones el = new Elecciones(LeerDatos.leerArchivoCircunscripciones("datosEleccionesParlament2017.csv"), 3);
		Iterable<Map.Entry<String,Integer>> m1 = el.escanosCircunscripcion("Barcelona", new MetodoHondt());
		Iterable<Map.Entry<String,Integer>> m2 = el.escanosTotales(new MetodoHondt());
		VistaElecciones p = new VentanaElecciones(list, m1, m2);		
		ControladorElecciones controlador = new ControladorElecciones(p);
		p.setVisible(true);
	}

}
