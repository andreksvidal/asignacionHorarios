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
public class RTR implements Recombinacion {

    @Override
    public ArrayList<Agente> recombinacion(ArrayList<Agente> poblacion) {
        ArrayList<Agente> hijos = new ArrayList<>();
        for (int i = 0; i < poblacion.size(); i += 2) {
            Agente padre = poblacion.get(i);
            Agente madre = poblacion.get(i + 1);
            hijos.add(recombinar(padre, madre));
        }
        return hijos;
    }

    private AgenteHorario recombinar(Agente padre, Agente madre) {
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
        AgenteHorario agente = new AgenteHorario(asignaciones);
        return agente;
    }

}
