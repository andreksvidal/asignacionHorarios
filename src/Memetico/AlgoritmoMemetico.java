/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memetico;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import BusquedaLocal.EnfriamientoSimulado.EnfriamientoSimulado;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author acer_acer
 */
public class AlgoritmoMemetico {

    EnfriamientoSimulado algoritmo;
    GeneradorPoblacionAgenteHorario generador;
    int tamPob;
    int tamConservar;

    public void ejecutar(GeneradorPoblacionAgenteHorario generador, int numPob, int tamConservar, Recombinacion tipoRecombinacion, EnfriamientoSimulado algoritmo) {
        this.tamPob = numPob;int it=0;
        this.tamConservar = tamConservar;
        this.algoritmo = algoritmo;
        this.generador = generador;
        ArrayList<Agente> pobInicial = generador.generarPoblacion(numPob);
        ArrayList<Agente> poblacion = busquedaLocal(pobInicial);
        //Criterio Parada
        while (it<1) {
            System.out.println("iteracion " + it );
            it++;
            ArrayList<Agente> nuevaPoblacion = tipoRecombinacion.recombinacion(poblacion);
            //Probar aplicando una vez mas busqueda local
            poblacion = (ArrayList<Agente>) actualizarPoblacion(numPob - 1, poblacion, nuevaPoblacion);
            //Criterio convergencia
            if (evaluacionesSimilares()) {
                poblacion = reiniciarPoblacion(poblacion);
            }
        }
        System.out.println("Final");
    }

    public ArrayList<Agente> busquedaLocal(ArrayList<Agente> pobInicial) {
        ArrayList<Agente> poblacion = new ArrayList<>();
        int cont=0;
        for (Agente agente : pobInicial) {
            Solucion solucion = (AgenteHorario) agente;
            poblacion.add((Agente) algoritmo.ejecutarAlgoritmo(solucion, 400));
            System.out.println("busqueda local" + cont);
            cont++;
        }
        return poblacion;
    }

    private List<Agente> actualizarPoblacion(int tamPob, ArrayList<Agente> poblacion, ArrayList<Agente> nuevaPoblacion) {
        ArrayList<Agente> unionPoblacion = new ArrayList<>();
        unionPoblacion.addAll(poblacion);
        unionPoblacion.addAll(nuevaPoblacion);
        Collections.sort(unionPoblacion, (Agente o1, Agente o2) -> {
            return (int) (o2.getEvaluacionAgente()- o1.getEvaluacionAgente());
        });
        return new ArrayList<>(unionPoblacion.subList(0, tamPob));
    }

    private ArrayList<Agente> reiniciarPoblacion(ArrayList<Agente> poblacion) {
        ArrayList<Agente> nuevaPoblacion = generador.generarPoblacion(tamPob - tamConservar);
        nuevaPoblacion = busquedaLocal(nuevaPoblacion);
        Collections.sort(poblacion, (Agente o1, Agente o2) -> {
            return (int) (o2.getEvaluacionAgente()- o1.getEvaluacionAgente());
        });
        nuevaPoblacion.addAll(new ArrayList<> (poblacion.subList(0, tamConservar)));
        return nuevaPoblacion;
    }

    private boolean evaluacionesSimilares() {
        //Si un gran porcentaje de los agentes tienen evaluacion parecida
        return false;
    }
}
