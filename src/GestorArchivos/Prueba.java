/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorArchivos;

import Agente.AgenteHorario.Curso;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GeneradorPoblacionAgenteHorario generador = new GeneradorPoblacionAgenteHorario();
        
        generador.generarPoblacion(2);
    }
    
}
