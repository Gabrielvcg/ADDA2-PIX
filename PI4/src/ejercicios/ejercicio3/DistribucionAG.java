package ejercicios.ejercicio3;

import java.util.List;

import ejercicios.ejercicio2.DatosCesta;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class DistribucionAG implements ValuesInRangeData<Integer, SolucionDistribucion>{

	public static Integer costeTotal=null;

	

	@Override
	public Integer size() {
		return DatosDistribucion.getNumProductos() * DatosDistribucion.getNumDestinos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	  public boolean satisfaceDemandaDestino(int destino, int demandaMinima, List<Integer> ls) {
	        int totalProductosAsignados = 0;
	        int n_destinos=DatosDistribucion.getNumDestinos();
	        for (int i = destino; i < ls.size(); i += n_destinos) {
	            totalProductosAsignados += ls.get(i);
	        }
	        return totalProductosAsignados >= demandaMinima;
	    }
	  
	  public boolean noExcedeUnidadesProducto(int producto, int unidadesDisponibles, List<Integer> ls) {
	        int totalUnidadesAsignadas = 0;
	        int n_destinos=DatosDistribucion.getNumDestinos();
	        for (int i = producto * n_destinos; i < (producto + 1) * n_destinos; i++) {
	            totalUnidadesAsignadas += ls.get(i);
	        }
	        return totalUnidadesAsignadas <= unidadesDisponibles;
	    }
	  
	@Override
	public Double fitnessFunction(List<Integer> ls) {
	//	System.out.println(ls);
			double goal = 0, error = 0;
			Integer m_destinos= DatosDistribucion.getNumDestinos();
			for(int i=0; i<ls.size(); i++) {
					
				int producto = i / m_destinos; // Calcular el índice del producto
	            int destino = i % m_destinos;
				Integer unidadesDisponibles=DatosDistribucion.getUnidadesProducto(producto);
				Integer demandaMinima=DatosDistribucion.getDemandaDestino(destino);
				Boolean satisfaceDemanda=satisfaceDemandaDestino(destino,demandaMinima,ls);
				Boolean noExcede=noExcedeUnidadesProducto(producto,unidadesDisponibles,ls);
				if(!satisfaceDemanda ||  !noExcede){
					error+=1;
					//Math.abs(metrosH-metrosV);
				}else {
					goal+=1;
				}
				
				}
			
			return goal -10000*error;
	}


	@Override
	public SolucionDistribucion solucion(List<Integer> value) {
		return SolucionDistribucion.of_Range(value);
	}

	@Override
	public Integer max(Integer i) {
		Integer m_destinos = DatosDistribucion.getNumDestinos();
		return DatosDistribucion.getDemandaDestino(i%m_destinos);
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	public DistribucionAG(String linea) {
		DatosDistribucion.iniDatos(linea);
	}

}