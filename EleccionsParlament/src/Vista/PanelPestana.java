package Vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelPestana extends JTabbedPane {
	//panel conjunto de pestañas
	
	//Els dos menus son iguals pero el territori de la circunsipcio es el que mostra el grafic i el de l'escrutinio el que actualitza els vots del territori indicat
	private JComboBox<String> combo1; //menu desplegable Circunsripcion
	private JComboBox<String> combo2; // menu desplegable Escrutinio

	private String titulo;
	private JTextField candidaturaTxt, votosTxt;
	private JButton aceptarBtn;
	private PanelResultados pCircun, pGeneral;
	
	public PanelPestana(String[] str, Iterable<Map.Entry<String,Integer>> escanosCircu,
			Iterable<Map.Entry<String,Integer>> escanosGeneral){ 
	    JPanel panel1 = creacionPestana1(str, escanosCircu);
	    addTab("Resultados Circunsripcion", panel1);
	    JPanel panel2 = creacionPestana2(str, escanosGeneral);
	    addTab("Resultados generales", panel2);
	}
	
	private JPanel creacionPestana1(String[] str, Iterable<Map.Entry<String,Integer>> m) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		//PANEL RESULTADOS
		titulo = str[0]; //es posa e primer de la llista com a titol pk es el primer del menu desplegable
        	pCircun = new PanelResultados("Elecciones 2017 Catalunya - " + titulo);
        	pCircun.setResultados(m);
        	GridBagConstraints gbc = new GridBagConstraints();
        	gbc.gridx = 0;
        	gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.5;
        gbc.weighty = 1.5;
        gbc.fill = GridBagConstraints.BOTH;
		panel.add(pCircun, gbc);
		gbc.weighty = 0;
		gbc.weightx = 0;
		
		//PANEL CIRCUNSRIPCION
		gbc.gridwidth = GridBagConstraints.NORTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		panel.add(crearPanelCircun(str), gbc);

		//PANEL ESCRUTINIO
		gbc.gridy++;
		panel.add(crearPanelEscrutinio(str), gbc);
		
        return panel;
	}
	
	private JPanel crearPanelCircun(String[] str) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new CompoundBorder(new TitledBorder("Circunscripcion"), 
				new EmptyBorder(20, 5, 20, 5)));
		GridBagConstraints gbc = new GridBagConstraints();
	    	gbc.gridx = 0;
	    	gbc.gridy = 0;
		panel.add(new JLabel("Territorio: "), gbc);
		gbc.gridx++;
		combo1 = creacionComboBox(str);
		panel.add(combo1, gbc);
        return panel;
	}
	
	private JPanel crearPanelEscrutinio(String[] str) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new CompoundBorder(new TitledBorder("Escrutinio"), 
				new EmptyBorder(20, 5, 20, 5)));
		GridBagConstraints gbc = new GridBagConstraints();
	    	gbc.gridx = 0;
	    	gbc.gridy = 0;
	    	gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Circunscripcion: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Candidatura: "), gbc);
		gbc.gridy++;
		panel.add(new JLabel("Votos: "), gbc);
		gbc.gridx++;
		gbc.gridy = 0;
		combo2 = creacionComboBox(str);
		panel.add(combo2, gbc);
		gbc.gridy++;
		candidaturaTxt = new JTextField();
		panel.add(candidaturaTxt, gbc);
		gbc.gridy++;
		votosTxt = new JTextField();
		panel.add(votosTxt, gbc);
		gbc.gridy++;
		aceptarBtn = new JButton("Aceptar");
		panel.add(aceptarBtn, gbc);
		return panel;
	}
	
	private JComboBox<String> creacionComboBox(String[] str) {
		JComboBox<String> cb = new JComboBox<>(); 
        cb.setModel(new DefaultComboBoxModel<>(str));
        return cb;
	}

	private JPanel creacionPestana2(String[] str, Iterable<Map.Entry<String,Integer>> m) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		pGeneral = new PanelResultados("Elecciones 2017 Catalunya - Resultados Generales");
		pGeneral.setResultados(m);
        	GridBagConstraints gbc = new GridBagConstraints();
        	gbc.gridx = 0;
        	gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.5;
        gbc.weighty = 1.5;
        gbc.fill = GridBagConstraints.BOTH;
		panel.add(pGeneral, gbc);
		gbc.weighty = 0;
		gbc.weightx = 0;

        return panel;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void addOyenteCircunsActual(ItemListener l) {
		combo1.addItemListener(l);
	}
	public String getCircunsActual(){
		return combo1.getItemAt(combo1.getSelectedIndex());
	}
	/*public void addOyenteEscrutinioActual(ItemListener l) {
		combo2.addItemListener(l);
	}*/
	public String getCircunsEscrutini(){
		return combo2.getItemAt(combo2.getSelectedIndex());
	}
	public String getCandidaturaTxt() {
		return candidaturaTxt.getText();
	}
	public String getVotosTxt() {
		return votosTxt.getText();
	}
	public int getVotos() {
		return  Integer.parseInt(votosTxt.getText());
	}
	public void addOyenteAceptarBtn(ActionListener l) {
		aceptarBtn.addActionListener(l);
	}
	public PanelResultados getpCircun() {
		return pCircun;
	}

	public PanelResultados getpGeneral() {
		return pGeneral;
	}
	
	/*public static void main(String[] args) {
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] list = new String[] {"T", "LL", "G", "B"};
		Map<String,Integer> m1 = new TreeMap<String,Integer>();
		for(int i=1; i<=20; i++) m1.put("Candidatura "+i, i);
		Map<String,Integer> m2 = new TreeMap<String,Integer>();
		for(int i=1; i<=20; i++) m2.put("Candidatura "+i, i);
		PanelPestana p = new PanelPestana(list, m1, m2);
		w.setContentPane(p);
		w.pack();
		w.setVisible(true);
	}*/

}
