/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class Tiempo {

     ArrayList<Integer> horas;
     HashMap<Integer,String> dias;

    public HashMap<Integer ,String> getDiasUniversidad() {
        dias = new HashMap<>();
        dias.put(0, "LUNES");
        dias.put(1, "MARTES");
        dias.put(2, "MIERCOLES");
        dias.put(3, "JUEVES");
        dias.put(4, "VIERNES");
        
        return dias;
    }

    public ArrayList<Integer> getHorasUniversidad() {
        horas = new ArrayList();

       horas.add(7);
       horas.add(9);
       horas.add(11);
       horas.add(14);
       horas.add(16);
       horas.add(18);
       
        
        
        return horas;
    }

}
