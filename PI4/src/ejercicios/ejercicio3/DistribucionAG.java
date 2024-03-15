package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.List;

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
	        return totalProductosAsignados == demandaMinima;
	    }
	  
	  public boolean noExcedeUnidadesProducto(int producto, int unidadesDisponibles, List<Integer> ls) {
	        int totalUnidadesAsignadas = 0;
	        int n_destinos=DatosDistribucion.getNumDestinos();
	        for (int i = producto * n_destinos; i < (producto + 1) * n_destinos; i++) {
	            totalUnidadesAsignadas += ls.get(i);
	        }
	        return totalUnidadesAsignadas <= unidadesDisponibles;
	    }
	  public boolean masBarato(int producto, int destino, List<Integer> ls) {
		    Integer n_destinos = DatosDistribucion.getNumDestinos();
		    int costoProducto = DatosDistribucion.getCosteAlmacenamientoProducto(producto, destino);
		    
		    for (int i = destino; i < ls.size(); i += n_destinos) {
		        int costoAsignado = DatosDistribucion.getCosteAlmacenamientoProducto(i / n_destinos, destino);
		        if (costoAsignado < costoProducto) {
		            return false; // Hay un producto más barato asignado a este destino
		        }
		    }
		    
		    return true; // El producto es el más barato asignado a este destino
		}
	  public boolean masBaratoElegido(int producto, int destino, List<Integer> ls) {
		    Integer n_destinos = DatosDistribucion.getNumDestinos();

	        int indiceProductoDestino = producto * n_destinos + destino; // Índice del producto en el destino
		    return masBarato(producto,destino,ls) && ls.get(indiceProductoDestino)==DatosDistribucion.getUnidadesProducto(producto); // El producto es el más barato asignado a este destino
		}




	  
	  public boolean masCaroNoAsignado(int producto, int destino, List<Integer> ls) {
		    Integer n_destinos = DatosDistribucion.getNumDestinos();
		    int costoProducto = DatosDistribucion.getCosteAlmacenamientoProducto(producto, destino);

		    // Verificamos si el producto especificado es el más barato en el destino indicado
		    if (!masBarato(producto, destino, ls)) {
		        // El producto no es el más barato, ahora verificamos si está asignado o no
		        int indiceProductoDestino = producto * n_destinos + destino; // Índice del producto en el destino
		        if (ls.get(indiceProductoDestino) == 0) {
		            // El producto no está asignado
		            return true;
		        }
		    }

		    // El producto es el más barato o está asignado, por lo tanto, retornamos false
		    return false;
		}

		    		






	  
	@Override
	public Double fitnessFunction(List<Integer> ls) {
	//System.out.println(ls);
		//	List<Integer>ls=new ArrayList<>();
			/*
			
			ls.add(5);
			ls.add(0);
			ls.add(0);
			ls.add(0);
			ls.add(0);
			ls.add(1);
			ls.add(3);
			ls.add(8);
			ls.add(10);
			ls.add(5);
			System.out.println(ls);
			
		//[6, 2, 1, 8, 3, 6, 2, 1, 9, 0]
		ls.add(6);
		ls.add(2);
		ls.add(1);
		ls.add(8);
		ls.add(3);
		ls.add(6);
		ls.add(2);
		ls.add(1);
		ls.add(9);
		ls.add(0);
		*/
			double goal = 0, error = 0;
			Integer m_destinos= DatosDistribucion.getNumDestinos();
			for(int i=0; i<ls.size(); i++) {
					
				int producto = i / m_destinos; // Calcular el índice del producto
	            int destino = i % m_destinos;
				Integer unidadesDisponibles=DatosDistribucion.getUnidadesProducto(producto);
				Integer demandaMinima=DatosDistribucion.getDemandaDestino(destino);
				Integer costeAlmacenarProductoDestino=DatosDistribucion.getCosteAlmacenamientoProducto(producto, destino);
				Boolean satisfaceDemanda=satisfaceDemandaDestino(destino,demandaMinima,ls);
				Boolean noExcede=noExcedeUnidadesProducto(producto,unidadesDisponibles,ls);
				Boolean masBaratoElegido=masBaratoElegido(producto,destino,ls);
				Boolean masCaroNoAsignado=masCaroNoAsignado(producto,destino,ls);

				  if (!satisfaceDemanda || !noExcede ) {
			            error += 1;
			        } else {
			            goal += 1;
			            // Ajusta los pesos para que masBarato y masCaroNoAsignado contribuyan menos a la función de aptitu
			        }if (masBaratoElegido || masCaroNoAsignado ) {
			        	goal+=1;
			        }else {
			        	error+=2;
			        }
			    }
			    // Ajusta la escala del error para que tenga un impacto adecuado en la función de aptitud
			    return goal - 10000 * error;
	}


	@Override
	public SolucionDistribucion solucion(List<Integer> value) {
		return SolucionDistribucion.of_Range(value);
	}

	@Override
	public Integer max(Integer i) {
		Integer m_destinos = DatosDistribucion.getNumDestinos();
		return DatosDistribucion.getDemandaDestino(i%m_destinos)+1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	public DistribucionAG(String linea) {
		DatosDistribucion.iniDatos(linea);
	}

}