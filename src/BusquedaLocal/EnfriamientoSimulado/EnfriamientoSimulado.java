/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusquedaLocal.EnfriamientoSimulado;



/**
 *
 * @author ingesis
 */

import BusquedaLocal.ProblemaEnfriamiento.Solucion;
public class EnfriamientoSimulado{

   
    public Solucion ejecutarAlgoritmo(Solucion solucionInicial, double tempInicial) {
        double temp = tempInicial;
    
        double coolingRate = 0.005;
        Solucion mejorSolucion = solucionInicial;
        Solucion solucionActual = solucionInicial;

        while (temp >0.005 ) {
            Solucion solucionPrima = solucionActual.getVecinoAleatorio();
            
            if (probabilidadAceptacion(solucionInicial.getEvaluacion(), solucionPrima.getEvaluacion(), temp) >= Math.random()) {
               solucionActual = solucionPrima;
            }
            if(solucionActual.getEvaluacion()< mejorSolucion.getEvaluacion()){
                mejorSolucion = solucionActual;
            }

         

            temp *= 1 - coolingRate;
          
        }

       
        return mejorSolucion;

    }

   
    public static double probabilidadAceptacion( double temperaturaSistema, double evaluacionActual, double nuevaEvaluacion) {
        if (nuevaEvaluacion < evaluacionActual) {
            return 1.0;
        }
        return Math.exp((evaluacionActual - nuevaEvaluacion) / temperaturaSistema);
    }

}
