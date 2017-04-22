/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente;

import BusquedaLocal.BusquedaTabu.algoritmo_base.Individual;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public abstract class Agente implements Individual {

    protected ArrayList memes;
    protected float evaluacion;

    public ArrayList<Object> getMemes() {
        return memes;
    }

    public void setMemes(ArrayList memes) {
        this.memes = memes;
    }

    public void setEvaluacion(float evaluacion) {
        this.evaluacion = evaluacion;
    }

}
