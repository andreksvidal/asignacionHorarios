/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memetico;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorario.AsignacionHorario;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author acer_acer
 */
public class RTRHorariosUniversidad implements Recombinacion {

    @Override
    public ArrayList<Agente> recombinacion(ArrayList<Agente> poblacion) {
        ArrayList<Agente> hijos = new ArrayList<>();
<<<<<<< HEAD:src/Memetico/RTRHorariosUniversidad.java
        for (int i = 0; i < poblacion.size(); i++) {
=======
        for (int i = 0; i < poblacion.size()-1; i += 2) {
>>>>>>> origin/master:src/Memetico/RTR.java
            Agente padre = poblacion.get(i);
            //Se cambió porque da indexofBoundException.
            int madreRandom=(int) (Math.random() * poblacion.size() - 1);
            Agente madre = poblacion.get(madreRandom);
            hijos.add(recombinar((AgenteHorario)padre,(AgenteHorario) madre));
        }
        return hijos;
    }

    private AgenteHorario recombinar(AgenteHorario padre, AgenteHorario madre) {
        ArrayList<AsignacionHorario> asignaciones = new ArrayList();
        Random rnd = new Random();

        for (int i = 0; i < madre.getMemes().size(); i++) {
            AsignacionHorario memePa = (AsignacionHorario) padre.getMemes().get(i);
            AsignacionHorario memeMa = (AsignacionHorario) madre.getMemes().get(i);
            if (memePa.compareTo(memeMa) == 0) {
                asignaciones.add(memePa);
            } else {
                if ((int) (rnd.nextDouble() * 2 + 0) == 0) {
                    asignaciones.add(memeMa);
                } else {
                    asignaciones.add(memePa);
                }
            }
        }
        
        AgenteHorario agente = new AgenteHorario(asignaciones,padre.getEvaluador(),padre.getGenerador());
        return agente;
    }

}
