package ejercicios.ejercicio2;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.common.Trio;

public class DatosCesta {
	
	public static Integer presupuesto=null;
	public static Pair<Integer, HashMap<Integer, Trio<Integer, Integer, Integer>>> cestaPres=Pair.of(null, null);
	public static HashMap<Integer,Trio<Integer,Integer,Integer>> cesta = new HashMap<>();
	public static Trio<Integer, Integer, Integer> propiedades = Trio.of(null, null, null);
	
	 public static HashMap<Integer,Trio<Integer,Integer,Integer>> getCesta() {
	        return cesta;
	    }
	 public static Integer getNumProductos() {
	        return cesta.size();
	    }
	 public static Trio<Integer,Integer,Integer> getPropiedadesProducto(Integer p) {
	        return cesta.get(p);
	    }
	 public static Integer getPrecioProducto(Integer p) {
	        return cesta.get(p).first();
	    }
	 public static Integer getCategoriaProducto(Integer p) {
	        return cesta.get(p).second();
	    }
	 public static Integer getValoracionProducto(Integer p) {
	        return cesta.get(p).third();
	    }
	 
	 
	
	
	public static void iniDatos(String fichero) {
	        List<String> lineas = Files2.linesFromFile(fichero);
	        for (String linea : lineas) {
	            if (linea.contains("Presupuesto")) {
	                presupuesto = Integer.parseInt(linea.split("[=]")[1].trim());
	            } else if (linea.contains("//Id_prod:Precio:Categoria:Valoracion")) {
	            	//pasamos la linea
	            } else {
	                String[] partes = linea.split("[:]");
	               
	                    Integer producto = Integer.parseInt(partes[0].trim());
	                    Integer precio = Integer.parseInt(partes[1].trim());
	                    Integer categoria = Integer.parseInt(partes[2].trim());
	                    Integer valoracion = Integer.parseInt(partes[3].trim());

	                    propiedades = Trio.of(precio, categoria, valoracion);
	                    cesta.put(producto, propiedades);
	                }
	        }
	        cestaPres=Pair.of(presupuesto, cesta);
	        toConsole();

	    }

	    public static void toConsole() {
	        
	        System.out.println("Presupuesto de la cesta: "+cestaPres.first());
	        
	        System.out.println("Producto y sus propiedades");
	        for (Entry<Integer, Trio<Integer, Integer, Integer>> propiedades : cesta.entrySet()) {
	            Integer producto = propiedades.getKey();
	            Integer precio = propiedades.getValue().first();
	            Integer categoria = propiedades.getValue().second();
	            Integer valoracion = propiedades.getValue().third();
	            System.out.println(producto + ": precios: " + precio + ", categoria: "
	                    + categoria + ", valoracion: " + valoracion);
	        }
	    }
	    public static void main(String[] args) {
			iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
		}	
	    
}
