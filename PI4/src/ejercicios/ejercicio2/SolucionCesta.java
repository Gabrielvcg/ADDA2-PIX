package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.List;


public class SolucionCesta {
	public static SolucionCesta of_Range(List<Integer> ls) {
		return new SolucionCesta(ls);
	}
	private Integer costeCesta;
	private List<Integer> solucion;

	private SolucionCesta() {
		solucion = new ArrayList<>();
	}
	
	private SolucionCesta(List<Integer> ls) {
		solucion = new ArrayList<>();
		
		for(int i=0; i<ls.size(); i++) {
			if(ls.get(i)!=0) {
		
			solucion.add(i);
			
			}
		}
		costeCesta=solucion.stream().map(x->DatosCesta.getPrecioProducto(x)).reduce(0, (a,b)->a+b);
	}
	
	public static SolucionCesta empty() {
		return new SolucionCesta();
	}

	@Override
	public String toString() {
		return "SolucionCesta [costeCesta=" + costeCesta + ", solucion=" + solucion + "]";
	}

	

}
