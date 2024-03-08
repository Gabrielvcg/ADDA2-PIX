package ejercicios.ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class CestaAG implements ValuesInRangeData<Integer, SolucionCesta>{

	public static Integer precioTotal=null;

	private Boolean superaPresupuestoMismaCategoria(List<Integer>ls,Integer categoria) {
		Set<Integer> mismaCategoria=obtenerCategorias(ls,categoria);
		Integer sumaPrecios=mismaCategoria.stream().map(x->DatosCesta.getPrecioProducto(x)).reduce(0, (a,b)->a+b);
		return sumaPrecios > DatosCesta.cestaPres.first();
	}
	private Set<Integer> obtenerCategorias(List<Integer> ls, Integer categoria) {
	    Set<Integer> mismaCategoria = new HashSet<>();
	    for (int i = 0; i < ls.size(); i++) {
	        if (ls.get(i).equals(1) && DatosCesta.cestaPres.second().get(i).second().equals(categoria)) {
	            mismaCategoria.add(i);
	        }
	    }
	    return mismaCategoria;
	}
	private Set<Integer> obtenerCategoriasTotales(List<Integer> ls, Integer categoria) {
	    Set<Integer> mismaCategoria = new HashSet<>();
	    for (int i = 0; i < ls.size(); i++) {
	        if (DatosCesta.cestaPres.second().get(i).second().equals(categoria)) {
	            mismaCategoria.add(i);
	        }
	    }
	    return mismaCategoria;
	}
	private Boolean mejorPrecioCategoria(List<Integer> ls, Integer producto, Integer categoria) {
	    Set<Integer> mismaCategoria = obtenerCategoriasTotales(ls, categoria);
	    if (mismaCategoria.size() == 0) {
	        return false; // No hay productos en la misma categoría
	    } else {
	        Integer precioProducto = DatosCesta.getPrecioProducto(producto);
	        for (Integer indice : mismaCategoria) {
	            if (!indice.equals(producto)) {
	                Integer precioActual = DatosCesta.getPrecioProducto(indice);
	                if (precioActual < precioProducto) {
	                    return false; // No tiene el mejor precio
	                }
	            }
	        }
	        return true; // Tiene el mejor precio
	    }
	}

	
	private Double mediaValoraciones(List<Integer>ls) {
		 Set<Integer> indicesElegidos = IntStream.range(0, ls.size())
		            .filter(i -> ls.get(i).equals(1))
		            .boxed()  // Convertir de int a Integer
		            .collect(Collectors.toSet());
		 Double suma = indicesElegidos.stream()
		            .mapToDouble(x -> DatosCesta.getValoracionProducto(x))
		            .sum();

		    Double media = indicesElegidos.isEmpty() ? 0.0 : suma / indicesElegidos.size();
		    return media;
		
	}
	

	@Override
	public Integer size() {
		return DatosCesta.getNumProductos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
	//	System.out.println(ls);
			double goal = 0, error = 0;
			
			for(int i=0; i<ls.size(); i++) {
					
				Integer producto=i;
				Integer categoria=DatosCesta.getCategoriaProducto(producto);
				Set<Integer> mismaCategoria = obtenerCategorias(ls, categoria);
				Double valoracionMediaElectos=mediaValoraciones(ls);
				Boolean mayorQuePresupuesto=superaPresupuestoMismaCategoria(ls,categoria);
				if(mismaCategoria.size()!=1 ||  valoracionMediaElectos<3 || mayorQuePresupuesto){
					error+=1;
					//Math.abs(metrosH-metrosV);
				}else {
					goal+=1;
				}
				
				if(mejorPrecioCategoria(ls,i,categoria)) {
					error+=1;
				}
				}
			
			return goal -10000*error;
	}


	@Override
	public SolucionCesta solucion(List<Integer> value) {
		return SolucionCesta.of_Range(value);
	}

	@Override
	public Integer max(Integer i) {
		return 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
	public CestaAG(String linea) {
		DatosCesta.iniDatos(linea);
	}

}
