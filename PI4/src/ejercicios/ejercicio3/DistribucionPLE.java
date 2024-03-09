package ejercicios.ejercicio3;

import java.io.IOException;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class DistribucionPLE {

	   public static Integer getNumDestinos() {
	        return DatosDistribucion.destinos.size();
	    }

	    public static Integer getNumProductos() {
	        return DatosDistribucion.productos.size();
	    }

	    public static Integer getDemandaDestino(Integer i) {
	        return DatosDistribucion.destinos.get(i);
	    }

	    public static Integer getUnidadesProducto(Integer i) {
	        return DatosDistribucion.productos.get(i).first();
	    }

	    public static Integer getCosteAlmacenamientoProducto(Integer i, Integer j) {
	        return DatosDistribucion.productos.get(i).second().get(j);
	    }
	 public static void ejercicio3_model() throws IOException {
	    	DatosDistribucion.iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
	    	DatosDistribucion.destinos=DatosDistribucion.getdestinos();
			System.out.println("Cantidad de productos a repartir: "+ getNumProductos());
			System.out.println("Destinos a repartir: "+ getNumDestinos());

			//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
//			AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_modelps/Ejercicio1-2.lp");
			AuxGrammar.generate(DistribucionPLE.class,"modelos/ejercicio3.lsi","gurobi_modelos/Ejercicio3-1.lp");
			GurobiSolution solution = GurobiLp.gurobi("gurobi_modelos/Ejercicio3-1.lp");
			Locale.setDefault(Locale.of("en", "US"));
			System.out.println(solution.toString((s,d)->d>0.));
		}
		
	    public static void main(String[] args) throws IOException {
	    	ejercicio3_model();
		}	
	    
}
