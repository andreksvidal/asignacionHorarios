/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memetico;

import Agente.Agente;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author acer_acer
 */
public class AlgoritmoMemetico {
    public void ejecutar(GeneradorPoblacionAgenteHorario generador,int numPob,Recombinacion tipoRecombinacion){
        ArrayList<Agente> pobInicial=generador.generarPoblacion(numPob);
        ArrayList<Agente> poblacion=busquedaLocal(pobInicial);        
        //Criterio Parada
        while(true){
            ArrayList<Agente> nuevaPoblacion=tipoRecombinacion.recombinacion(poblacion);
            //Probar aplicando una vez mas busqueda local
            poblacion=actualizarPoblacion(numPob-1,poblacion,nuevaPoblacion);
            //Criterio convergencia
            if(evaluacionesSimilares()){
                poblacion=reiniciarPoblacion();
            }
        }
    }
    
    public ArrayList<Agente> busquedaLocal(ArrayList<Agente> pobInicial){
        ArrayList<Agente> poblacion=new ArrayList<>();
        for (Agente agente : pobInicial) {
            poblacion.add(busqueda(agente));
        }
        return poblacion;
    }

    private Agente busqueda(Agente agente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ArrayList<Agente> actualizarPoblacion(int tamPob,ArrayList<Agente> poblacion, ArrayList<Agente> nuevaPoblacion) {
        ArrayList<Agente> unionPoblacion=new ArrayList<>();
        unionPoblacion.addAll(poblacion);
        unionPoblacion.addAll(nuevaPoblacion);
        unionPoblacion.subList(0, tamPob);
        //TODO: Hacer el comparator
        unionPoblacion.sort(c);
    }

    private ArrayList<Agente> reiniciarPoblacion(GeneradorPoblacionAgenteHorario generador,ArrayList<Agente> poblacion,int tamPob,int tamConservar) {
        ArrayList<Agente> nuevaPoblacion=generador.generarPoblacion(tamPob-tamConservar);        
        nuevaPoblacion=busquedaLocal(nuevaPoblacion);  
        for (int i = 0; i < tamConservar; i++) {
            //OrdenarConElComparator
            //Extraer el mejor y meterlo a la nuevaPob            
        }
        return nuevaPoblacion;
    }

    private boolean evaluacionesSimilares() {
        //Si un gran porcentaje de los agentes tienen evaluacion parecida
        return true;
    }
}
