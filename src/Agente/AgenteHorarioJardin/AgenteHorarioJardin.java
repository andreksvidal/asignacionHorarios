/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Agente.AgenteHorarioJardin;

import Agente.Agente;
import Agente.AgenteHorario.AsignacionHorario;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import Evaluacion.EvaluadorAgenteHorarioJardin;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorarioJardin;
import java.util.ArrayList;

/**
 * 
 * @author Andres Vidal Zemanate
/* FIET-Ingenieria de Sistemas
 */
public class AgenteHorarioJardin  extends Agente implements Solucion{

    
    private  EvaluadorAgenteHorarioJardin evaluador;
    
    private  GeneradorPoblacionAgenteHorarioJardin generador;

    public AgenteHorarioJardin(ArrayList<AsignacionHorario> horarios ,EvaluadorAgenteHorarioJardin evaluador, GeneradorPoblacionAgenteHorarioJardin generador) {
        memes=horarios;
        this.evaluador = evaluador;
        this.generador = generador;
    }
  
    
   @Override
    public void evaluar() {
        this.evaluador.evaluar(this);
    }

    @Override
    public double getEvaluacion() {
         return this.evaluacion;
    }

    @Override
    public ArrayList<Solucion> getVecinos() {

        System.out.println("No implementado...No necesario.");
        return null;
    }

    @Override
    public Solucion getVecinoAleatorio() {

        AgenteHorarioJardin vecino =(AgenteHorarioJardin) generador.generarVecino(this);

       this.evaluador.evaluar(vecino);

        Solucion solucion = (AgenteHorarioJardin) vecino;

        return solucion;
    }
    
    
    
    public GeneradorPoblacionAgenteHorarioJardin getGenerador() {
        return generador;
    }

    public void setGenerador(GeneradorPoblacionAgenteHorarioJardin generador) {
        this.generador = generador;
    }

    public EvaluadorAgenteHorarioJardin getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(EvaluadorAgenteHorarioJardin evaluador) {
        this.evaluador = evaluador;
    }
    
    
    
    

}
