/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestorArchivos;

import Agente.AgenteHorario.Curso;
import Agente.AgenteHorario.Salon;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class LectorArchivosHorarios {

    public ArrayList<Curso> leerCursos() {

        ArrayList<Curso> cursos = new ArrayList();
        ManejoArchivos lector = new ManejoArchivos();
        ArrayList<String> lineas = lector.leerArchivo("CursoProfesor.txt");

        for (String linea : lineas) {
            String[] aux = linea.split(";");
            Curso curso = new Curso(Integer.parseInt(aux[0]), aux[1], aux[2], Integer.parseInt(aux[3]), Integer.parseInt(aux[4]), aux[5], Integer.parseInt(aux[6]),aux[7]);
            cursos.add(curso);
        }

        return cursos;
    }

    public ArrayList<Salon> leerSalones() {

        ArrayList<Salon> salones = new ArrayList();
        ManejoArchivos lector = new ManejoArchivos();
        ArrayList<String> lineas = lector.leerArchivo("Salones.txt");

        for (String linea : lineas) {

            String[] aux = linea.split(";");
            Salon salon = new Salon(Integer.parseInt(aux[0]), aux[1], Integer.parseInt(aux[2]));
            salones.add(salon);
        }
        return salones;
    }
    
    
}
