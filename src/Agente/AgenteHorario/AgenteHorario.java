/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import Agente.Agente;
import BusquedaLocal.BusquedaTabu.algoritmo_base.Individual;
import Evaluacion.EvaluadorAgenteHorario;
import java.util.ArrayList;


/**
 *
 * @author ingesis
 */
public class AgenteHorario extends Agente{

    
    private EvaluadorAgenteHorario  evaluador ;
    
    public AgenteHorario(ArrayList<AsignacionHorario> horarios) {        
       memes = horarios;
       this.evaluador= new EvaluadorAgenteHorario();
    }
    

    /****************************************************************************************************/
    /*IMPLEMENTAR ESTOS METODOS ES NECESARIO PARA QUE EL AGENTE PUEDA SER MEJORADO CON LA BUSQUEDA LOCAL*/
    /****************************************************************************************************/
    
    
    @Override
    public ArrayList<Individual> getNeighbourhood() { 
        ArrayList<Agente> agentes= obtenerVecinos();
        ArrayList<Individual> individuos= new ArrayList();
        for (Agente agente : agentes) {
            individuos.add(agente);
        }
        return  individuos;
    }

    @Override
    public double getEvaluacion() {
        this.evaluacion=this.evaluador.evaluar(this);
        return this.evaluacion;
    }

    @Override
    public int getIndividualSize() {
       return this.getMemes().size();
    }

    @Override
    public Object getValue(int position) {
        return this.getMemes().get(position);
    }

    @Override
    public Individual clonar() {
        try {
            return (Individual)this.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Error, No se pudo realizar el proceso de clonación");
            return null;
        }
        
    }

    private ArrayList<Agente> obtenerVecinos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /****************************************************************************************************/
    /*FIN IMPLEMENTACION METODOS NECESARIOS PARA QUE EL AGENTE PUEDA SER MEJORADO CON LA BUSQUEDA LOCAL*/
    /****************************************************************************************************/    
}
