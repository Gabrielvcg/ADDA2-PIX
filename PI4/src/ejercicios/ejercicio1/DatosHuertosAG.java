package ejercicios.ejercicio1;



import java.util.HashMap;
import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.mochila.datos.DatosMochila;

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

	
	private Integer metrosH;
	private Integer metrosV;
	private Double fitness = null;
	
	private void calcula(List<Integer> ls) {
		;
		this.metrosH = 0;
		this.metrosV = 0;
		for (int i = 0; i < ls.size(); i++) {
			metrosV= metrosV + DatosHuertos.getMetrosVerdura(i);
			metrosH= metrosH + DatosHuertos.getMetrosHuerto(ls.get(i));
		}
	}
	
	@Override
	public Double fitnessFunction(List<Integer> dc) {
		calcula(dc);
		fitness = dc.size() - 100*AuxiliaryAg.distanceToGeZero(metrosV.doubleValue() - metrosH.doubleValue());
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
