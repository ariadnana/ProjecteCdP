package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.ModeloElecciones;
import Vista.VistaElecciones;

public class ControladorPrueba {
    private VistaElecciones vista;
    private ModeloElecciones modelo;
    private int liston;
    private boolean isEditable = false;
    
    public ControladorPrueba(VistaElecciones vista/*, ModeloElecciones modelo*/) {
        super();
        this.vista = vista;
        //this.modelo = modelo;
        
        //PANEL OPCIONAL
        this.vista.getPanelOpcional().addOyenteMetodo(new ItemListener() { 
            public void itemStateChanged(ItemEvent e) { 
           if(e.getItem().toString().equals("Metodo Hondt")) HondtPulsado();//es pot canviar, nomes volia comprobar si funcionava la llista desplegable
           if(e.getItem().toString().equals("Metodo Hare")) HarePulsado();
            } 
         });
        
        liston = 3;
        this.vista.getPanelOpcional().addOyenteListonBtn(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    listonBtnPulsado();
                }
        });
    
        //PANEL PESTAÑA
        
        
    }
    
    protected void listonBtnPulsado() {
        String intento = vista.getPanelOpcional().getListonDisplay();
        int numDisplay = -1;
        try{
            numDisplay= Integer.parseInt(intento);
            if(isEditable) {
                if(numDisplay > 0 && numDisplay < 100) {
                    liston = numDisplay;
                    vista.getPanelOpcional().setListonEditable(false);
                    vista.getPanelOpcional().setListonBtnTxt("Editar");
                    isEditable = false;
                }else {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Porfavor introduce un valor entero entre el 0 y 100",
                            "Valor Incorrecto",
                            JOptionPane.ERROR_MESSAGE);
                }
            }else{
                vista.getPanelOpcional().setListonEditable(true);
                vista.getPanelOpcional().setListonBtnTxt("Aceptar");;
                isEditable = true;    
            }
        
        }catch(NumberFormatException nFE){
            JOptionPane.showMessageDialog(new JFrame(),
                    "Porfavor introduce un valor entero entre el 0 y 100",
                    "Valor Incorrecto",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    

    public void HondtPulsado() {
        //TODO
    }
    
    public void HarePulsado() {
        //TODO
    }
    
}