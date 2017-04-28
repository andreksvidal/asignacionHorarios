/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import BusquedaLocal.BusquedaTabu.algoritmo_base.ConfiguracionTabuSearch;
import BusquedaLocal.BusquedaTabu.algoritmo_base.Individual;
import BusquedaLocal.BusquedaTabu.algoritmo_base.TabuSearch;
import BusquedaLocal.BusquedaTabu.algoritmo_base.criterios_aspiracion.CriteriosAspiracionEnum;
import BusquedaLocal.BusquedaTabu.algoritmo_base.criterios_parada.CriteriosParadaEnum;
import BusquedaLocal.BusquedaTabu.algoritmo_base.lista_tabu.TabuListMovimientos;
import Evaluacion.Evaluador;
import Evaluacion.EvaluadorAgenteHorario;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GeneradorPoblacionAgenteHorario generador = new GeneradorPoblacionAgenteHorario();

        ArrayList<Agente> agentes = generador.generarPoblacion(50);

        System.out.println("Agentes " + agentes.size());
        Evaluador evaluador = new EvaluadorAgenteHorario();

        float mejor = Float.POSITIVE_INFINITY;
        int posicionMejor = 0;

        for (int i = 0; i < agentes.size(); i++) {
            Agente agente = agentes.get(i);

            float evaluacion = evaluador.evaluar((AgenteHorario) agente);
            System.out.println(evaluacion);
            if (evaluacion < mejor) {
                posicionMejor=i;
                mejor=evaluacion;
            }
            System.out.println(evaluacion);
        }

        
        
        TabuSearch busquedaTabu = new TabuSearch();

        ConfiguracionTabuSearch configuracion = new ConfiguracionTabuSearch();

        configuracion.setTipoProblema(false);
        configuracion.setCriterioParada(CriteriosParadaEnum.NUM_ITERACIONES, 1000);
        configuracion.setCriterioAspiracion(CriteriosAspiracionEnum.POR_OBJETIVO);
        configuracion.setListaTabu(new TabuListMovimientos(), 5);

        
        Individual seed= agentes.get(posicionMejor);
        
        
        Individual mejorado = busquedaTabu.tabuSearch(configuracion, seed);
        
        System.out.println("Mejor gen:" + mejorado.getEvaluacion());

    }

}
