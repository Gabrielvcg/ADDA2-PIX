package ejercicios.ejercicio1;



import java.util.List;

import ejemplos.DatosMulticonjunto;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DatosHuertosAG implements ValuesInRangeData<Integer, SolucionHuerto> {

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

	private Integer metrosV;
	private Integer metrosH;
	private Integer elegidas;
	private void calcula(List<Integer> ls) {
		this.metrosV = 0;
		this.metrosH = 0;
		this.elegidas=0;
		for (int i = 0; i < ls.size(); i++) {
			metrosH = metrosH + Ejercicio1PLE.getMetrosHuerto(ls.get(i));
			metrosV = metrosV + Ejercicio1PLE.getMetrosVerdura(i);
			if(ls.get(i)!=null) {
			elegidas=elegidas + 1;
			}
		}
	}
	private Double fitness;
	public Double fitnessFunction(List<Integer> dc) {
		calcula(dc);
		fitness = elegidas - 100*AuxiliaryAg.distanceToGeZero(metrosV.doubleValue() - metrosH.doubleValue());
		return fitness;
	}

	@Override
	public SolucionHuerto solucion(List<Integer> ls) {
		return SolucionHuerto.of_Range(ls);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosHuertos.getNumHuertos()-1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
