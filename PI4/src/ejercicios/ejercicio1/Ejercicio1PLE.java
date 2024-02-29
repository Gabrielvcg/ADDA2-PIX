package ejercicios.ejercicio1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;
import ejercicios.ejercicio1.DatosHuertos;

public class Ejercicio1PLE {

	 public static Integer getNumHuertos() {
	        return DatosHuertos.huertos.size();
	    }

	    public static Integer getNumVerduras() {
	        return DatosHuertos.verduras.size();
	    }

	    public static Integer getMetrosHuerto(Integer i) {
	        return DatosHuertos.huertos.get(i);
	    }

	    public static Integer getMetrosVerdura(Integer i) {
	        return DatosHuertos.verduras.get(i).first();
	    }

	    public static List<Integer> getVerdurasIncompatibles(Integer i) {
	        return DatosHuertos.verduras.get(i).second();
	    }

	    public static Integer getIncompatibilidad(Integer i, Integer k) {
	        Integer incompatibles = 0;
	        List<Integer> incomp1 = DatosHuertos.verduras.get(i).second();
	        List<Integer> incomp2 = DatosHuertos.verduras.get(k).second();
	        if (incomp1.contains(k) || incomp2.contains(i)) {
	            incompatibles = 1;
	        }
	        return incompatibles;
	    }
	
    public static void ejercicio1_model() throws IOException {
    	DatosHuertos.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
    	DatosHuertos.huertos=DatosHuertos.getHuertos();
    	DatosHuertos.verduras=DatosHuertos.getVerduras();
		System.out.println("tamaño de verduras: "+DatosHuertos.getNumVerduras());
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
//		AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_modelps/Ejercicio1-2.lp");
		AuxGrammar.generate(Ejercicio1PLE.class,"modelos/ejercicio1.lsi","gurobi_modelos/Ejercicio1-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_modelos/Ejercicio1-3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
    public static void main(String[] args) throws IOException {
    	ejercicio1_model();
	}	
    
    
}
