package ejercicios.ejercicio2;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class CestaPLE {

	public static Integer getPresupuesto() {
        return DatosCesta.cestaPres.first();
    }
	 public static Integer getNumProductos() {
	        return DatosCesta.cestaPres.second().size();
	    }
	 
	 public static Integer getNumCategorias() {
	       	       Set<Integer> categorias= DatosCesta.cestaPres.second().values().stream().map(x->x.second()).collect(Collectors.toSet());
	       	       return categorias.size();
	    }
	    
	 public static Integer getPrecioProducto(Integer p) {
	        return DatosCesta.cestaPres.second().get(p).first();
	    }
	 public static Integer getCategoriaProducto(Integer p) {
	        return DatosCesta.cestaPres.second().get(p).second();
	    }
	 public static Integer getValoracionProducto(Integer p) {
	        return DatosCesta.cestaPres.second().get(p).third();
	    }
	 public static Integer perteneceCategoria(Integer p, Integer j) {
	        return DatosCesta.cestaPres.second().get(p).second().equals(j) ? 1:0;
	    }
	 public static void ejercicio2_model() throws IOException {
	    	DatosCesta.iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
	    	DatosCesta.cestaPres=DatosCesta.getCestaPres();
			System.out.println("Cantidad de productos disponibles: "+ DatosCesta.cestaPres.second().size());
			//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
//			AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_modelps/Ejercicio1-2.lp");
			AuxGrammar.generate(CestaPLE.class,"modelos/ejercicio2.lsi","gurobi_modelos/Ejercicio2-3.lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_modelos/Ejercicio2-3.lp");
			Locale.setDefault(Locale.of("en", "US"));
			System.out.println(solution.toString((s,d)->d>0.));
		}
		
	    public static void main(String[] args) throws IOException {
	    	ejercicio2_model();
		}	
}
