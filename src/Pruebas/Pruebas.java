/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import BusquedaLocal.EnfriamientoSimulado.EnfriamientoSimulado;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import Evaluacion.Evaluador;
import Evaluacion.EvaluadorAgenteHorario;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import Memetico.AlgoritmoMemetico;
import Memetico.RTR;
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
       AlgoritmoMemetico memetico = new AlgoritmoMemetico();
     GeneradorPoblacionAgenteHorario generador = new GeneradorPoblacionAgenteHorario();
//
//        ArrayList<Agente> agentes = generador.generarPoblacion(250);
//
//        System.out.println("Agentes " + agentes.size());
//        Evaluador evaluador = new EvaluadorAgenteHorario();
//
//        float mejor = Float.POSITIVE_INFINITY;
//        int posicionMejor = 0;
//
//        for (int i = 0; i < agentes.size(); i++) {
//            Agente agente = agentes.get(i);
//
//            float evaluacion = evaluador.evaluar((AgenteHorario) agente);
//       
//            agente.setEvaluacion(evaluacion);
//            //System.out.println(evaluacion);
//            if (evaluacion < mejor) {
//                posicionMejor = i;
//                mejor = evaluacion;
//            }
//            //System.out.println(evaluacion);
//        }

        EnfriamientoSimulado algoritmo = new EnfriamientoSimulado();

//        Solucion solucion = (AgenteHorario) agentes.get(posicionMejor);
//
//        System.out.println("Mejor generado : " + solucion.getEvaluacion());
//   
//
//        Solucion mejorado = (AgenteHorario) algoritmo.ejecutarAlgoritmo(solucion, 400);
//
//        System.out.println("Mejora" + mejorado.getEvaluacion());
        
        memetico.ejecutar(generador, 10, 5, new RTR(), algoritmo);

    }

}
