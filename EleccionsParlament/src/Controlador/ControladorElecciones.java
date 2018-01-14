package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import Modelo.Elecciones;
import Modelo.InfoCircunscripcion;
import Modelo.LeerDatos;
import Modelo.MetodoCalculo;
import Modelo.MetodoHare;
import Modelo.MetodoHondt;
import Modelo.ModeloElecciones;
import Vista.VistaElecciones;

public class ControladorElecciones {
    private VistaElecciones v;
    private ModeloElecciones m;
    private MetodoCalculo metodoHondt, metodoHare, metodo;
    private String circ;
    
    public ControladorElecciones(VistaElecciones nv, ModeloElecciones nm){
    	super();
        v=nv;
        m=nm;
        circ= v.getPanelPestana().getCircunsActual();
        metodoHondt=new MetodoHondt();
        metodoHare=new MetodoHare();
        if(v.getPanelOpcional().getMetodo().equals("Metodo Hondt")) HondtPulsado();
        else HarePulsado();
        
        v.getPanelOpcional().addOyenteMetodo(new ItemListener(){
        	public void itemStateChanged(ItemEvent event){
        		if(event.getItem().toString().equals("Metodo Hondt")) HondtPulsado();
        		else HarePulsado();
        	}
        });
        
        v.getPanelOpcional().addOyenteListonBtn(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                teclaListonPulsada();
            }
        });
        
        v.getPanelPestana().addOyenteCircunsActual(new ItemListener(){
        	public void itemStateChanged(ItemEvent event){
        		canviCircunscripcio(event.getItem().toString());
        	}
        });
        
        v.getPanelPestana().addOyenteAceptarBtn(new ActionListener(){
        	public void actionPerformed(ActionEvent event) {
                afegirEscrutini(v.getPanelPestana().getCircunsEscrutini(), v.getPanelPestana().getCandidaturaTxt(), v.getPanelPestana().getVotos());
            }
        });
    }
    
    protected void HondtPulsado(){
        metodo = metodoHondt;
        actualitzaResultats();
    }
    
    protected void HarePulsado(){
        metodo = metodoHare;
        actualitzaResultats();
    }
    
    protected void teclaListonPulsada(){
        m.setListon(v.getPanelOpcional().getListonDisplay());
        actualitzaResultats();
    }
    
    protected void canviCircunscripcio(String c){
    	circ=c;
    	v.getPanelPestana().setTituloCircun(c);
    	actualitzaResultats();
    }
    
    protected void afegirEscrutini(String circ, String cand, int v){
    	m.actualizarEscrutinio(circ, cand, v);
    	actualitzaResultats();
    }
    
    protected void actualitzaResultats(){
    	v.getPanelPestana().getpCircun().setResultados(m.escanosCircunscripcion(circ, metodo));
    	v.getPanelPestana().getpGeneral().setResultados(m.escanosTotales(metodo));
    }
    
    
}
