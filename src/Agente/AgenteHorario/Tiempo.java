/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import java.util.ArrayList;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class Tiempo {

    ArrayList<String> horas;
    ArrayList<String> dias;

    public ArrayList<String> getDias() {
        dias = new ArrayList();

        dias.add("LUNES");
        dias.add("MARTES");
        dias.add("MIERCOLES");
        dias.add("JUEVES");
        dias.add("VIERNES");

        return dias;
    }

    public ArrayList<String> getHoras() {
        horas = new ArrayList();

        horas.add("7 a.m");
        horas.add("9 a.m");
        horas.add("11 a.m");
        horas.add("2 p.m");
        horas.add("4 p.m");
        horas.add("6 p.m");

        return horas;
    }

}
