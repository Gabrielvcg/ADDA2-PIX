package ejercicios.ejercicio1;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import ejemplos.DatosMulticonjunto;
import ejemplos.SolucionMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Multiset;


public class Ejercicio1AG implements ValuesInRangeData<Integer, HashMap<Integer,Integer>> {

	Integer numVerduras;
	Integer numHuertos;
	
	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return Ejercicio1PLE.getNumVerduras();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
	double goal = 0, sum = 0, error = 0;
		
//		System.out.println(ls);
		
		for (int i = 0; i < size(); i++) {
			Integer mHuerto=Ejercicio1PLE.getMetrosHuerto(ls.get(i));
			Integer mVerdura=Ejercicio1PLE.getMetrosVerdura(i);
			if(mHuerto>=mVerdura) {
				goal +=ls.get(i);			
				sum += ls.get(i)*DatosMulticonjunto.getElemento(i);
			}
		}
		error += Math.abs(sum - DatosMulticonjunto.getSuma());
		return goal -10000*error;
	}

	@Override
	public HashMap<Integer,Integer> solucion(List<Integer> ls) {

		HashMap<Integer,Integer> solucion = new HashMap<>();
		
		for(int i=0; i<ls.size(); i++) {
				Integer e = ls.get(i);
				solucion.put(i, e);
		}
		return solucion;
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return Ejercicio1PLE.getNumHuertos()-1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
