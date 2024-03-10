package ejercicios.ejercicio3;

import java.util.HashMap;
import java.util.List;

import us.lsi.common.Pair;

public class SolucionDistribucion {
	public static SolucionDistribucion of_Range(List<Integer> ls) {
		return new SolucionDistribucion(ls);
	}
	private HashMap<Integer,Integer> costeAlmacenamientoProducto;
	private HashMap<Integer,Pair<Integer,Integer>> solucion;
	private Integer costeTotal;


	private SolucionDistribucion() {
		solucion = new HashMap<>();
	}
	
	
	private SolucionDistribucion(List<Integer> ls) {
		solucion = new HashMap<>();
		Integer m_destinos=DatosDistribucion.getNumDestinos();
        for (int i = 0; i < ls.size(); i++) {
            int cantidadAsignada = ls.get(i);
            if (cantidadAsignada != 0) {
                int producto = i / m_destinos; // Calcular el índice del producto
                int destino = i % m_destinos; // Calcular el índice del destino
              
                Pair<Integer, Integer> RepartoADestino = new Pair<>(destino, cantidadAsignada);
                solucion.put(producto, RepartoADestino);
            }
        }
        for (HashMap.Entry<Integer, Pair<Integer, Integer>> entry : solucion.entrySet()) {
            int producto = entry.getKey();
            Pair<Integer, Integer> repartoADestino = entry.getValue();
            int destino = repartoADestino.first();
            int cantidad = repartoADestino.second();

            int costo = DatosDistribucion.getCosteAlmacenamientoProducto(producto, destino);
            //costeAlmacenamientoProducto.put(producto, costo * cantidad);
           // costeTotal+=costo * cantidad;
            
        }
    }
	
	public static SolucionDistribucion empty() {
		return new SolucionDistribucion();
	}


	@Override
	public String toString() {
		return "SolucionDistribucion [costeAlmacenamientoProducto=" + costeAlmacenamientoProducto + ", solucion="
				+ solucion + ", costeTotal=" + costeTotal + "]";
	}


	
}
