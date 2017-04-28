/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memetico;

import Agente.Agente;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author acer_acer
 */
public class AlgoritmoMemetico {
    public void ejecutar(GeneradorPoblacionAgenteHorario generador,int poblacion){
        ArrayList<Agente> pobInicial=generador.generarPoblacion(poblacion);
        String pop=aplicarBusqueda(popInicial);
    }
}
