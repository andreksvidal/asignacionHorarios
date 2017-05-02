/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;

import Agente.AgenteHorario.TiempoJardin;
import Agente.AgenteHorarioJardin.AgenteHorarioJardin;
import BusquedaLocal.EnfriamientoSimulado.EnfriamientoSimulado;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import Evaluacion.Evaluador;
import Evaluacion.EvaluadorAgenteHorario;

import Evaluacion.EvaluadorAgenteHorarioJardin;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorarioJardin;
import GestorArchivos.LectorArchivosHorariosJardin;
import Memetico.AlgoritmoMemetico;
import Memetico.RTRHorariosUniversidad;
import Memetico.RTRHorariosJardin;
import java.util.ArrayList;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class PruebasJardin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoritmoMemetico memetico = new AlgoritmoMemetico();
        LectorArchivosHorariosJardin lector = new LectorArchivosHorariosJardin();
        GeneradorPoblacionAgenteHorarioJardin generador = new GeneradorPoblacionAgenteHorarioJardin(lector.leerCursos(), lector.leerSalonesSimple(), new TiempoJardin(), 15);

//        ArrayList<Agente> agentes = generador.generarPoblacion(250);
//
//        Evaluador evaluador = new EvaluadorAgenteHorarioJardin();
//
//        float mejor = Float.POSITIVE_INFINITY;
//        int posicionMejor = 0;
//
//        for (int i = 0; i < agentes.size(); i++) {
//            Agente agente = agentes.get(i);
//
//            evaluador.evaluar((AgenteHorarioJardin) agente);
//
//            //System.out.println(evaluacion);
//            if (agente.getEvaluacionAgente() < mejor) {
//                posicionMejor = i;
//                mejor = agente.getEvaluacionAgente();
//            }
//            System.out.println(agente.getEvaluacionAgente());
//        }
//
//        EnfriamientoSimulado algoritmo = new EnfriamientoSimulado();
//
//        Solucion solucion = (AgenteHorarioJardin) agentes.get(posicionMejor);
//
//        System.out.println("Mejor generado : " + solucion.getEvaluacion());
//
//        Solucion mejorado = (AgenteHorarioJardin) algoritmo.ejecutarAlgoritmo(solucion, 10);
//
//        System.out.println("Mejora" + mejorado.getEvaluacion());
        
        Evaluador evaluador = new EvaluadorAgenteHorarioJardin();
        EnfriamientoSimulado algoritmo = new EnfriamientoSimulado();
        memetico.ejecutar(generador,1500, 90, new RTRHorariosJardin(), algoritmo,evaluador,"jardin",5000);

    }

}
