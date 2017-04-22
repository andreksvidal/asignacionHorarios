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
public class AsignacionHorario {

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

}
