package ejercicios.ejercicio1;

import java.util.HashMap;
import java.util.List;

import ejemplos.DatosMulticonjunto;
import ejemplos.SolucionMulticonjunto;
import us.lsi.common.Multiset;



public class SolucionHuerto {

	public static SolucionHuerto of_Range(List<Integer> ls) {
		return new SolucionHuerto(ls);
	}
	private Integer agragadas;
	private HashMap<Integer,Integer> solucion;

	private SolucionHuerto() {
	
		solucion = new HashMap<>();
	}
	
	private SolucionHuerto(List<Integer> ls) {
		solucion = new HashMap<>();
		for(int i=0; i<ls.size(); i++) {
			Integer huerto = ls.get(i);
			Integer verdura = i;
			solucion.put(verdura, huerto);
		}
		agragadas=solucion.size();
	}
	
	public static SolucionHuerto empty() {
		return new SolucionHuerto();
	}

	@Override
	public String toString() {
		return "SolucionHuerto [agragadas=" + agragadas + ", solucion=" + solucion + "]";
	}

	
}
