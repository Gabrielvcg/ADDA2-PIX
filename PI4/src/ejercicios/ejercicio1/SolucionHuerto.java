package ejercicios.ejercicio1;

import java.util.HashMap;
import java.util.List;

import us.lsi.common.Pair;



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
		HashMap<Integer,Integer> huertos=new HashMap<>(DatosHuertos.huertos);
		HashMap<Integer, Pair<Integer, List<Integer>>> verduras=new HashMap<>(DatosHuertos.verduras);

		solucion = new HashMap<>();
		for(int i=0; i<ls.size(); i++) {
			
			Integer huerto = ls.get(i);
			Integer verdura = i;
			Integer metrosH=huertos.get(huerto);
			Integer metrosV=verduras.get(verdura).first();
			if(metrosH-metrosV>=0) {
			solucion.put(verdura, huerto);
			huertos.put(huerto, metrosH-metrosV);
			}
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
