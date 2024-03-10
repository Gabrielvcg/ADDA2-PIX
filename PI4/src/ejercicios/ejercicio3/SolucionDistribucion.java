package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.lsi.common.Pair;

public class SolucionDistribucion {
	public static SolucionDistribucion of_Range(List<Integer> ls) {
		return new SolucionDistribucion(ls);
	}
	private Integer costeTotal=0;
	private HashMap<Pair<Integer,Integer>,Integer> solucion;



	private SolucionDistribucion() {
		solucion = new HashMap<>();
	}
	
	
	private SolucionDistribucion(List<Integer> ls) {
		System.out.println(ls);
		solucion = new HashMap<>();
		Integer m_destinos=DatosDistribucion.getNumDestinos();
        for (int i = 0; i < ls.size(); i++) {
            int cantidadAsignada = ls.get(i);
            if (cantidadAsignada != 0) {
                int producto = i / m_destinos; // Calcular el índice del producto
                int destino = i % m_destinos; // Calcular el índice del destino
              
                Pair<Integer, Integer> productoDestino = new Pair<>(producto, destino);

                solucion.put(productoDestino, cantidadAsignada);
            }
        }
        for (HashMap.Entry<Pair<Integer,Integer>, Integer> entry : solucion.entrySet()) {
            int producto = entry.getKey().first();
            int destino = entry.getKey().second();
            int cantidad = entry.getValue();

            int coste = DatosDistribucion.getCosteAlmacenamientoProducto(producto, destino);
            costeTotal+=coste * cantidad;
            
        }
    }
	
	public static SolucionDistribucion empty() {
		return new SolucionDistribucion();
	}

	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Reparto obtenido (cantidad de producto repartido a cada destino):\n");

	    for (int i = 0; i < DatosDistribucion.getNumDestinos(); i++) {
	        sb.append("Destino ").append(i).append(": [");
	        List<Integer> asignaciones = new ArrayList<>();
	        for (int j = 0; j < DatosDistribucion.getNumProductos(); j++) {
	            Pair<Integer, Integer> productoDestino = new Pair<>(j, i);
	            Integer cantidadAsignada = solucion.getOrDefault(productoDestino, 0);
	            asignaciones.add(cantidadAsignada);
	        }
	        for (int j = 0; j < asignaciones.size(); j++) {
	            sb.append(asignaciones.get(j));
	            if (j < asignaciones.size() - 1) {
	                sb.append(", ");
	            }
	        }
	        sb.append("]\n");
	    }

	    sb.append("Coste total de almacenamiento: ").append(costeTotal);
	    return sb.toString();
	}





	
}
