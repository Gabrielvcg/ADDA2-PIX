package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class DatosHuertos {
	 public static HashMap<Integer, Integer> huertos = new HashMap<>();
	    public static HashMap<Integer, Pair<Integer, List<Integer>>> verduras = new HashMap<>();

	    public static HashMap<Integer, Integer> getHuertos() {
	        return huertos;
	    }

	    public static HashMap<Integer, Pair<Integer, List<Integer>>> getVerduras() {
	        return verduras;
	    }

	    public static Integer getNumHuertos() {
	        return huertos.size();
	    }

	    public static Integer getNumVerduras() {
	        return verduras.size();
	    }

	    public static Integer getMetrosHuerto(Integer i) {
	        return huertos.get(i);
	    }

	    public static Integer getMetrosVerdura(Integer i) {
	        return verduras.get(i).first();
	    }

	    public static List<Integer> getVerdurasIncompatibles(Integer i) {
	        return verduras.get(i).second();
	    }

	    public static Integer getIncompatibilidad(Integer i, Integer k) {
	        Integer incompatibles = 0;
	        List<Integer> incomp1 = verduras.get(i).second();
	        List<Integer> incomp2 = verduras.get(k).second();
	        if (incomp1.contains(k) || incomp2.contains(i)) {
	            incompatibles = 1;
	        }
	        return incompatibles;
	    }

	    public static void iniDatos(String fichero) {
	        List<String> lineas = Files2.linesFromFile(fichero);
	        String tipo = ""; // Variable para identificar si la línea contiene información de huertos o variedades
	        for (String linea : lineas) {
	            if (linea.contains("HUERTOS")) {
	                tipo = "huerto";
	            } else if (linea.contains("VARIEDADES")) {
	                tipo = "variedad";
	            } else {

	                String[] partes = linea.split("[-:;=,]");
	                Integer clave = Integer.parseInt(partes[0].substring(1).trim()); // Extraemos el número y lo convertimos a entero
	                if (tipo.equals("huerto")) {
	                    int metrosDisponibles = Integer.parseInt(partes[2]);
	                    huertos.put(clave, metrosDisponibles);
	                } else if (tipo.equals("variedad")) {
	                    int metrosRequeridos = Integer.parseInt(partes[2]);
	                    List<Integer> verdurasIncompatibles = new ArrayList<>();
	                    for (int i = 1; i < partes.length; i++) {
	                        if (partes[i].startsWith("V")) {
	                            verdurasIncompatibles.add(Integer.parseInt(partes[i].substring(1)));
	                        }
	                    }
	                    verduras.put(clave, new Pair<>(metrosRequeridos, verdurasIncompatibles));
	                }
	            }
	        }
	        toConsole();

	    }


	    public static void toConsole() {
	        // Imprimir los huertos
	        System.out.println("Huertos de entrada:");
	        for (Integer huerto : huertos.keySet()) {
	            System.out.println(huerto + ": " + huertos.get(huerto));
	        }

	        // Imprimir las verduras incompatibles
	        System.out.println("Verduras incompatibles:");
	        for (Integer variedad : verduras.keySet()) {
	            Pair<Integer, List<Integer>> info = verduras.get(variedad);
	            int metrosRequeridos = info.first();
	            List<Integer> incompatibles = info.second();
	            System.out.println(variedad + ": Metros requeridos: " + metrosRequeridos + ", Incompatibles: "
	                    + incompatibles);
	        }
	    }
	    public static void main(String[] args) {
			iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		}	
}
