/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorPoblacion;

import Agente.Agente;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public interface GeneradorPoblacion {
 

    public ArrayList<Agente> generarPoblacion(int tamanioPoblacion);
    
}
