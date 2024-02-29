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

	private Integer metrosH,metrosV;
	private HashMap<Integer,Integer> solucion;

	private SolucionHuerto() {
		metrosV=metrosH = 0;
		solucion = new HashMap<>();
	}
	private SolucionHuerto(List<Integer> ls) {

		solucion = new HashMap<>();
		for(int i=0; i<ls.size(); i++) {
			Integer huerto = ls.get(i);
			Integer verdura = i;
			Integer metrosH=DatosHuertos.getMetrosHuerto(huerto);
			Integer metrosV=DatosHuertos.getMetrosVerdura(verdura);
			if(metrosV<=metrosH) {				
				
				solucion.put(verdura, huerto);
				metrosH-=metrosV;

			}
		}
	}
	
	public static SolucionHuerto empty() {
		return new SolucionHuerto();
	}
	@Override
	public String toString() {
		return "SolucionHuerto [metrosH=" + metrosH + ", metrosV=" + metrosV + ", solucion=" + solucion + "]";
	}
	
}
