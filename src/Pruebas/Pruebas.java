/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorario.Tiempo;
import BusquedaLocal.EnfriamientoSimulado.EnfriamientoSimulado;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import Evaluacion.Evaluador;
import Evaluacion.EvaluadorAgenteHorario;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import GestorArchivos.LectorArchivosHorarios;
import Memetico.AlgoritmoMemetico;
import Memetico.RTRHorariosUniversidad;
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
       LectorArchivosHorarios lector = new LectorArchivosHorarios();
       GeneradorPoblacionAgenteHorario generador = new GeneradorPoblacionAgenteHorario(lector.leerCursos(),lector.leerSalonesSimple(),new Tiempo(),105);
     

//        ArrayList<Agente> agentes = generador.generarPoblacion(500);
//        
//
//        //System.out.println("Agentes " + agentes.size());
//        Evaluador evaluador = new EvaluadorAgenteHorario();
//
//        float mejor = Float.POSITIVE_INFINITY;
//        int posicionMejor = 0;
//
//        for (int i = 0; i < agentes.size(); i++) {
//            Agente agente = agentes.get(i);
//
//            evaluador.evaluar((AgenteHorario) agente);
//       
//           
//            //System.out.println(evaluacion);
//            if (agente.getEvaluacionAgente()< mejor) {
//                posicionMejor = i;
//                mejor = agente.getEvaluacionAgente();
//            }
//            //System.out.println(evaluacion);
//        }
//                
//       
//       
//       EnfriamientoSimulado algoritmo = new EnfriamientoSimulado();
//
//       Solucion solucion = (AgenteHorario) agentes.get(posicionMejor);
//
//        System.out.println("Mejor generado : " + solucion.getEvaluacion());
//   
//
//        Solucion mejorado = (AgenteHorario) algoritmo.ejecutarAlgoritmo(solucion,10);
//
//        System.out.println("Mejora" + mejorado.getEvaluacion());
       
       Evaluador evaluador = new EvaluadorAgenteHorario();
        EnfriamientoSimulado algoritmo = new EnfriamientoSimulado();
        memetico.ejecutar(generador, 1500, 500, new RTRHorariosUniversidad(), algoritmo,evaluador);

    }

}
