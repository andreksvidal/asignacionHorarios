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

        float mejor= Float.POSITIVE_INFINITY;
        for (Agente agente : agentes) {
            float evaluacion = evaluador.evaluar((AgenteHorario) agente);
            if(evaluacion<mejor)
            {
                mejor=evaluacion;
            }
            System.out.println(evaluacion);
        }
        
        System.out.println("Mejor generado : " + mejor);

    }
}
