/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class AsignacionHorario implements Comparable<AsignacionHorario>{

    private ArrayList<FranjaHoraria> franjasHorario;
    private Curso curso;


    public AsignacionHorario(ArrayList<FranjaHoraria> franjasHorario, Curso curso) {
        this.franjasHorario = franjasHorario;
        this.curso = curso;             
    }
        
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public ArrayList<FranjaHoraria> getFranjasHorario() {
        return franjasHorario;
    }

    public void setFranjasHorario(ArrayList<FranjaHoraria> franjasHorario) {
        this.franjasHorario = franjasHorario;
    }

    @Override
    public int compareTo(AsignacionHorario o) {
        if(this.curso.getId()==o.getCurso().getId())
        {
            for (int i = 0; i < franjasHorario.size(); i++) {
               if(this.franjasHorario.get(i).compareTo(o.getFranjasHorario().get(i))==0)
               {
                   return 0;
               }
                
            }
        }
        return -1;
    }

}
