/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
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

        Evaluador evaluador = new EvaluadorAgenteHorario();
//
//        float mejor= Float.POSITIVE_INFINITY;
//        for (Agente agente : agentes) {
//            float evaluacion = evaluador.evaluar((AgenteHorario) agente);            
//            if(evaluacion<mejor)
//            {
//                mejor=evaluacion;
//            }
//            System.out.println(evaluacion);
//        }
//        System.out.println("Mejor gen:"+mejor);
        
        float mejorEval= evaluador.evaluar((AgenteHorario) agentes.get(0)); 
        Agente mejorAg=(AgenteHorario) agentes.get(0);
        float evaluacion;
        for (int i = 1; i < agentes.size(); i++) {
             evaluacion=evaluador.evaluar((AgenteHorario) agentes.get(i));
            if(evaluacion<mejorEval){
                mejorEval=evaluacion;
                mejorAg=(AgenteHorario) agentes.get(i);
            }           
        }
        System.out.println("Mejor :"+mejorEval);
        evaluador.evaluar(mejorAg);
        

    }
}
