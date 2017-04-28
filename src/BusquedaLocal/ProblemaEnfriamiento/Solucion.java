/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusquedaLocal.ProblemaEnfriamiento;

import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public interface Solucion {

   

    public  void evaluar();

    public double getEvaluacion();
    
    public  ArrayList<Solucion> getVecinos();
    public  Solucion getVecinoAleatorio();
    

}
