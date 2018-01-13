package Vista;

import java.awt.Dimension;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PanelResultados extends JScrollPane {
	private static int anchura = 1100;
	private static int altura = 400;
	private static String etiquetaEjeDominio = "Candidaturas";
	private static String etiquetaEjeRango = "Escanos";
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataSet;

	public PanelResultados(String titulo) {
		crearPanelDiagramaBarras(titulo);
		setPreferredSize(new Dimension(anchura,altura));
		setViewportView(chartPanel);
	}

	public void setResultados(Iterable<Entry<String,Integer>> resultados) {
		dataSet.clear();
		for(Entry<String,Integer> e: resultados) {
			dataSet.addValue(e.getValue(),e.getKey(),"");
		}
	}
	
	public void setTitulo(String titulo) {
		chartPanel.getChart().setTitle(titulo);
	}
	
	private void crearPanelDiagramaBarras(String titulo) {
		dataSet = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createBarChart(
				                titulo,                   // chart title
				                etiquetaEjeDominio,       // domain axis label
				                etiquetaEjeRango,  	      // range axis label
				                dataSet,                  // data
				                PlotOrientation.VERTICAL, // orientation
				                true,                     // include legend
				                true,                     // tooltips?
				                false                     // URLs?
				            );
		chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(anchura-50,altura-50));
        chart.setBackgroundPaint(chartPanel.getBackground());
	}

	public static void main(String[] args) {
		JFrame w = new JFrame();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PanelResultados p = new PanelResultados("Resultados Globales Elecciones - Parlament de Catalunya 2017");
		w.setContentPane(p);
		Map<String,Integer> m = new TreeMap<String,Integer>();
		for(int i=1; i<=20; i++) m.put("Candidatura "+i, i);
		p.setResultados(m.entrySet());
		w.pack();
		w.setVisible(true);
	}
}


