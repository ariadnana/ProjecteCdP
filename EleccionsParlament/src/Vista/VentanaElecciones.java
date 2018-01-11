package Vista;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaElecciones extends JFrame implements VistaElecciones { 
    private PanelOpcional panelOp;
    private PanelPestana panelPest;
	
	public VentanaElecciones(String[] str, Iterable<Map.Entry<String,Integer>> escanosCircu, 
			Iterable<Map.Entry<String,Integer>> escanosGeneral) {
		super();
		inicializar(str, escanosCircu, escanosGeneral);
	}
	
	private void inicializar(String[] str, Iterable<Map.Entry<String,Integer>> escanosCircu, 
			Iterable<Map.Entry<String,Integer>> escanosGeneral) {
		String titulo = "Elecciones";
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        
        JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panelOp = new PanelOpcional();
		panel.add(panelOp, BorderLayout.PAGE_START);
		
		panelPest = new PanelPestana(str, escanosCircu, escanosGeneral);
		panel.add(panelPest,BorderLayout.CENTER);
	    getContentPane().add(panel);
		
	}
	
	public PanelOpcional getPanelOpcional() {
		return panelOp;
	}
	
	public PanelPestana getPanelPestana() {
		return panelPest;
	}
	
	
}
