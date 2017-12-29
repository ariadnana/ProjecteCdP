package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Modelo.Elecciones;
import Modelo.InfoCircunscripcion;
import Modelo.LeerDatos;
import Modelo.MetodoCalculo;
import Modelo.ModeloElecciones;

public class ControladorElecciones {
	private VistaElecciones v;
	private ModeloElecciones m;
	private MetodoCalculo metodo;
	private int liston;
	private Iterable<InfoCircunscripcion> circunscripciones=LeerDatos.leerArchivoCircunscripciones("datosEleccionesParlament2017");
	
	public ControladorElecciones(VistaElecciones nv){
		v=nv;
		liston=0;
		
		v.addOyenteTeclaListon(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				teclaListonPulsada();
			}
		});
		
		v.addOyenteTeclaHondt(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				teclaHondtPulsada();
			}
		});
		
		v.addOyenteTeclaHare(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				teclaHarePulsada();
			}
		});
	}
	
	protected void teclaListonPulsada(){
		liston = v.getListon();
		m = new Elecciones(circunscripciones, liston);
	}
	
	protected void teclaHondtPulsada(){
		metodo = new MetodoHondt();
	}
	
	protected void teclaHarePulsada(){
		metodo = new MetodoHare();
	}
	
	
}
