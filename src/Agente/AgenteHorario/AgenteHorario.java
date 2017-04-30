/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import Agente.Agente;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;

import Evaluacion.EvaluadorAgenteHorario;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class AgenteHorario extends Agente implements Solucion, Comparable<AgenteHorario> {

    private final EvaluadorAgenteHorario evaluador;
    
    private GeneradorPoblacionAgenteHorario generador;

    public AgenteHorario(ArrayList<AsignacionHorario> horarios ,EvaluadorAgenteHorario evaluador,GeneradorPoblacionAgenteHorario generador) {
        memes = horarios;
        this.evaluador = evaluador;
        this.generador=generador;
    }

    public EvaluadorAgenteHorario getEvaluador() {
        return evaluador;
    }
    
    
  
    @Override
    public int compareTo(AgenteHorario o) {
        for (int i = 0; i < memes.size(); i++) {
            AsignacionHorario asignacion = (AsignacionHorario) memes.get(i);
            AsignacionHorario asignacionVecina = (AsignacionHorario) o.getMemes().get(i);

            if (asignacion.compareTo(asignacionVecina) == 0) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public void evaluar() {
        this.evaluador.evaluar(this);
    }


    @Override
    public ArrayList<Solucion> getVecinos() {

        System.out.println("No implementado...No necesario.");
        return null;
    }

    @Override
    public Solucion getVecinoAleatorio() {

        AgenteHorario vecino =(AgenteHorario) generador.generarVecino(this);

       this.evaluador.evaluar(vecino);

        Solucion solucion = (AgenteHorario) vecino;

        return solucion;
    }
    
    

    @Override
    public double getEvaluacion() {
        return this.evaluacion;
    }
    
    public GeneradorPoblacionAgenteHorario getGenerador() {
        return generador;
    }

    public void setGenerador(GeneradorPoblacionAgenteHorario generador) {
        this.generador = generador;
    }



  


}
