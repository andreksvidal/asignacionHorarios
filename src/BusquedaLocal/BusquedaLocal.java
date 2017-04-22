/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusquedaLocal;

import Agente.Agente;
import BusquedaLocal.BusquedaTabu.algoritmo_base.ConfiguracionTabuSearch;
import BusquedaLocal.BusquedaTabu.algoritmo_base.Individual;
import BusquedaLocal.BusquedaTabu.algoritmo_base.TabuSearch;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class BusquedaLocal {

    /**
     * Permite aplicar una busqueda local especifica para mejorar un Agente.
     *
     * @param agente , Es el agente que se desea mejorar, para la busqueda.Todo
     * agente debe implementar la clase Individual ejecutado el algoritmo
     * @param tipoBusqueda recibe el tipo de busqueda Local que se va a aplicar.
     * @param configuracionBusqueda recibe la configuracion (parametros) que se
     * aplicaran a la busqueda local.
     * @return el Agente mejorado producto de la busqueda local. Null si no
     * encuentra el tipo de busqueda, los parametros de la busqueda son
     * incorrectos, o ocurre un error interno. criterio de parada establecido en
     * la configuración
     */
    public Agente mejorarAgente(Individual agente, Object tipoBusqueda, Object configuracionBusqueda) {

        String claseBusqueda = tipoBusqueda.getClass().getSimpleName();

        switch (claseBusqueda) {
            case "TabuSearch":
                return invocarBusquedaTabu(agente, (TabuSearch) tipoBusqueda, (ConfiguracionTabuSearch) configuracionBusqueda);
            default:
                return null;
        }

    }

    private Agente invocarBusquedaTabu(Individual agente, TabuSearch tipoBusqueda, ConfiguracionTabuSearch configuracionBusqueda) {

        Agente best = (Agente) tipoBusqueda.tabuSearch(configuracionBusqueda, agente);

        System.out.println("Evaluación: " + best.getEvaluacion() + "   Individuo: " + best.toString());

        return best;
    }

}
