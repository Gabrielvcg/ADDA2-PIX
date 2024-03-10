package ejercicios.ejercicio3;

import java.util.HashMap;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.Pair;

public class DatosDistribucion {
	 public static HashMap<Integer, Integer> destinos = new HashMap<>();
	    public static HashMap<Integer, Pair<Integer, HashMap<Integer,Integer>>> productos = new HashMap<>();

	    public static HashMap<Integer, Integer> getdestinos() {
	        return destinos;
	    }

	    public static HashMap<Integer, Pair<Integer, HashMap<Integer,Integer>>> getProductos() {
	        return productos;
	    }

	    public static Integer getNumDestinos() {
	        return destinos.size();
	    }

	    public static Integer getNumProductos() {
	        return productos.size();
	    }

	    public static Integer getDemandaDestino(Integer i) {
	        return destinos.get(i);
	    }

	    public static Integer getUnidadesProducto(Integer i) {
	        return productos.get(i).first();
	    }

	    public static Integer getCosteAlmacenamientoProducto(Integer i, Integer j) {
	        return DatosDistribucion.productos.get(i).second().get(j);
	    }

	    public static HashMap<Integer, Integer> stringToMap(String input) {
	        HashMap<Integer, Integer> map = new HashMap<>();
	        
	        // quitar paréntesis y punto y coma del string
	        input = input.replace("(", "").replace(")", "").replace(";", "");
	        
	        // Dividir el string en pares de valores
	        String[] pairs = input.split(",");
	        
	        for (String pair : pairs) {
	            // Dividir cada par en clave y valor
	            String[] keyValue = pair.split(":");
	            // Parsear clave y valor a enteros
	            int key = Integer.parseInt(keyValue[0]);
	            int value = Integer.parseInt(keyValue[1]);
	            // Agregar al mapa
	            map.put(key, value);
	        }
	        
	        return map;
	    }
	
	   

	    public static void iniDatos(String fichero) {
	        List<String> lineas = Files2.linesFromFile(fichero);
	        String tipo = ""; // Variable para identificar si la línea contiene información de huertos o variedades
	        for (String linea : lineas) {
	            if (linea.contains("PRODUCTOS")) {
	                tipo = "producto";
	            } else if (linea.contains("DESTINOS")) {
	                tipo = "destino";
	            } else {

	                
	                Integer clave = Integer.parseInt(linea.substring(1,2).trim()); // Extraemos el número y lo convertimos a entero
	                if (tipo.equals("destino")) {
		                String[] partesD = linea.split("[=;]");
	                    int demanda = Integer.parseInt(partesD[1].trim());
	                    destinos.put(clave, demanda);
	                } else if (tipo.equals("producto")) {
		                String[] partesP = linea.split("[-=;]");
	                    int unidades = Integer.parseInt(partesP[2].trim());
	                    String costeStr= partesP[4].trim();
	                    HashMap<Integer,Integer>costeAlmacenado=stringToMap(costeStr);
	                    productos.put(clave, new Pair<>(unidades, costeAlmacenado));
	                }
	            }
	        }
	        toConsole();

	    }


	    public static void toConsole() {
	        // Imprimir los destinos
	        System.out.println("Destinos de entrada:");
	        for (Integer destino : destinos.keySet()) {
	            System.out.println(destino + ": " + destinos.get(destino));
	        }

	        // Imprimir los productos
	        System.out.println("Productos:");
	        for (Integer producto : productos.keySet()) {
	            Pair<Integer, HashMap<Integer,Integer>> info = productos.get(producto);
	            int unidades = info.first();
	            HashMap<Integer,Integer> costeAlmacenarProductos = info.second();
	            System.out.println(producto + ": Unidades disponibles: " + unidades + ", costes de almacenaje: "
	                    + costeAlmacenarProductos);
	        }
	    }
	    public static void main(String[] args) {
			iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
			System.out.println(getNumDestinos());
			System.out.println(getNumProductos());
			System.out.println(getCosteAlmacenamientoProducto(0, 1));
			System.out.println(getUnidadesProducto(1));
			System.out.println(getDemandaDestino(0));
			
		}	
}
