package ejercicios.ejercicio1;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Pair;

public class DatosHuertosAG implements ValuesInRangeData<Integer, SolucionHuerto> {

	public static List<Integer> filtrarPorValor(List<Integer> lista, int i, int huerto) {
        List<Integer> copia = new ArrayList<>(lista); // Hacer una copia de la lista original
        copia.remove(i); // Eliminar el elemento en la posición i
        copia.removeIf(e -> e != huerto); // Filtrar los elementos que no sean igual a "huerto"
        return copia;
    }

	    public static boolean verificarIncompatibles(List<Integer> mismoHuerto, List<Integer> incompatibles) {
	        for (Integer entero : mismoHuerto) {
	            if (incompatibles.contains(entero)) {
	                return false; // Se encontró un entero incompatible
	            }
	        }
	        return true; // No se encontraron enteros incompatibles
	    }
	    
	    
	public DatosHuertosAG(String linea) {
		DatosHuertos.iniDatos(linea);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosHuertos.getNumVerduras();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
	//	System.out.print(ls);
		double goal = 0, error = 0;
		HashMap<Integer,Integer> huertos=new HashMap<>(DatosHuertos.huertos);
		HashMap<Integer, Pair<Integer, List<Integer>>> verduras=new HashMap<>(DatosHuertos.verduras);

		for(int i=0; i<ls.size(); i++) {
			Integer verdura=i;
			Integer huerto=ls.get(i);
			Integer metrosH=huertos.get(huerto);
			Integer metrosV=verduras.get(verdura).first();
			List<Integer> mismoHuerto=new ArrayList<>();
			
			if(metrosH >= 0 && metrosH-metrosV>=0) {
				goal+=1;
				huertos.put(huerto, metrosH-metrosV);
			}else {
				error+=Math.abs(metrosH-metrosV);
			}
			mismoHuerto=filtrarPorValor(ls,i,huerto);
			List<Integer>incompatibles=verduras.get(i).second();
			if( !mismoHuerto.stream().noneMatch(incompatibles::contains)) {
				error+=1;
			}
			}
		return goal -10000*error;
	}

	@Override
	public SolucionHuerto solucion(List<Integer> ls) {
		return SolucionHuerto.of_Range(ls);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosHuertos.getNumHuertos();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
