package Vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOpcional extends JPanel{
	private JComboBox<String> comboBox;
	private String Hondt;
	private String Hare;
	private JTextField liston;
	private JButton listonBtn;
	
	public PanelOpcional(){
		setLayout(new BorderLayout());
		JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        //Metodo texto
        gbc.gridx = 0; //el area del txto empieza en la columna 0
        gbc.gridy = 0; //El área de texto empieza en la fila cero
        gbc.anchor = GridBagConstraints.WEST;
        pan.add(new JLabel("  Metodo: "), gbc);
        
        //Liston texto
        gbc.gridy = 1;
        pan.add(new JLabel("  Liston: "), gbc);

        //Desplegable metodo
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.WEST;
        pan.add(creacionComboBox(), gbc);
        
        //Text field de liston
        gbc.gridy = 1;
        gbc.weightx = 1;
        liston = new JTextField(10);
        liston.setText("3");
        liston.setEditable(false);
        pan.add(liston, gbc);
        
        //% Texto
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.WEST;
        pan.add(new JLabel("%"), gbc);
        
        //Btn
        listonBtn = new JButton("Editar");
        gbc.gridx = 3;
        gbc.gridy = 1;
        pan.add(listonBtn, gbc);
        
        add(pan, BorderLayout.WEST);
	}
	
	private JComboBox<String> creacionComboBox() {
		comboBox = new JComboBox<>();
        Hondt = "Metodo Hondt";
        Hare = "Metodo Hare";
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {Hondt, Hare})); 
        return comboBox;
	}
	
	public void addOyenteMetodo(ItemListener l) {
		comboBox.addItemListener(l);
	}
	
	public String getMetodo(){
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}
	
	public void addOyenteListonBtn(ActionListener l) {
		listonBtn.addActionListener(l);
	}

	public void setListonBtnTxt(String st) {
		listonBtn.setText(st); //Editar o Aceptar
	}
	
	public int getListonDisplay() {
		return Integer.parseInt(liston.getText());
	}

	public void setListonDisplay(String st) {
		liston.setText(st);
	}
	
	public void setListonEditable(boolean bn) {
		liston.setEditable(bn);
	}
	
	
	public static void main(String[] args) {
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelOpcional p = new PanelOpcional();
		w.setContentPane(p);
		w.pack();
		w.setVisible(true);
	}
}
