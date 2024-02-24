package ejercicios.ejercicio1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class Ejercicio1PLE {

 
    public static HashMap<String, Integer> huertos = new HashMap<>();
    public static HashMap<String, Pair<Integer, List<String>>> incompatibilidades = new HashMap<>();

	public static HashMap<String, Integer> getHuertos() {
		return huertos;
	}
	
	public static Integer getNumHuertos() {
		return huertos.size();
	}
	
	public static Integer getMetrosHuerto(String i) {
		return huertos.get(i);
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
            	
                String[] partes = linea.split("[-:;]");
                String clave = partes[0].trim();
                if (tipo.equals("huerto")) {
                    int metrosDisponibles = Integer.parseInt(partes[1].trim().replaceAll("[^0-9]", ""));
                    huertos.put(clave, metrosDisponibles);
                } else if (tipo.equals("variedad")) {
                    int metrosRequeridos = Integer.parseInt(partes[1].trim().replaceAll("[^0-9]", ""));
                    String[] incompatibilidadesArray = partes[2].trim().substring(7).split(",");
                    List<String> incompatibilidadesList = Arrays.asList(incompatibilidadesArray);
                    incompatibilidades.put(clave, new Pair<>(metrosRequeridos, incompatibilidadesList));
                }
            }
        }
        toConsole();
        
    }


    public static void toConsole() {
        // Imprimir los huertos
        System.out.println("Huertos de entrada:");
        for (String huerto : huertos.keySet()) {
            System.out.println(huerto + ": " + huertos.get(huerto));
        }

        // Imprimir las verduras incompatibles
        System.out.println("Verduras incompatibles:");
        for (String variedad : incompatibilidades.keySet()) {
            Pair<Integer, List<String>> info = incompatibilidades.get(variedad);
            int metrosRequeridos = info.first();
            List<String> incompatibles = info.second();
            System.out.println(variedad + ": Metros requeridos: " + metrosRequeridos + ", Incompatibles: " + incompatibles);
        }
    }
    public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
	}	
    
    
}
