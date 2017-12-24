package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LeerDatos {
	private static String delimitador = ";";
	
	public static Iterable<InfoCircunscripcion> leerArchivoCircunscripciones(String nombreArchivo) {
		List<InfoCircunscripcion> circunscripciones = new ArrayList<InfoCircunscripcion>();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File (nombreArchivo);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null) {
				String[] fields = linea.split(delimitador);
				List<String> candidaturas = new ArrayList<String>(); 
	            for (int i = 2; i < fields.length; i++) {
	            	candidaturas.add(fields[i]);
	            }			
				circunscripciones.add(new InfoCircunscripcion(fields[0], Integer.parseInt(fields[1]), candidaturas));
 	        }
		}
		catch(Exception e){
			e.printStackTrace();
			circunscripciones = null;
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{
				if( null != fr ){
					fr.close();
				}
			}catch (Exception e2){
				e2.printStackTrace();
			}
		}
		return circunscripciones;
	}
	
	public static void main(String[] args) {
		Iterable<InfoCircunscripcion> circunscripciones = leerArchivoCircunscripciones("datosEleccionesParlament2017.csv");
		if(circunscripciones != null) {
			for(InfoCircunscripcion circunscripcion: circunscripciones) {
				System.out.println(circunscripcion);
			}
		}
	}
}
