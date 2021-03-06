/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GestorArchivos;

import Agente.AgenteHorario.Curso;
import Agente.AgenteHorario.Salon;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Andres Vidal Zemanate
/* FIET-Ingenieria de Sistemas
 */
public class LectorArchivosHorariosJardin {

     public HashMap<Integer, Curso> leerCursos() {

        System.out.println("invocado leer cursos");

        HashMap<Integer, Curso> cursos = new HashMap();

        ManejoArchivos lector = new ManejoArchivos();
        ArrayList<String> lineas = lector.leerArchivo("./test/cursoProfesor.txt");
        for (String linea : lineas) {
            String[] aux = linea.split(";");
            
            String tipo= aux[5];
            
            int codigoTipo=-1;
            
            switch(tipo)
            {
                case "T":
                    codigoTipo=1;
                    break;
                case "P":
                    codigoTipo=2;
                    break;
                case "TP":
                     codigoTipo=3;
                    break;
                case "L":
                      codigoTipo=4;
                    break;
            }
            
            
            Curso curso = new Curso(Integer.parseInt(aux[0]), aux[1], aux[2], Integer.parseInt(aux[3]), Integer.parseInt(aux[4]), codigoTipo, Integer.parseInt(aux[6]), aux[7]);
            cursos.put(curso.getId(), curso);
        }

        return cursos;
    }
    
    
    public HashMap<Integer, Salon> leerSalonesSimple() {

        System.out.println("invocado leer salones");

        HashMap<Integer, Salon> salones= new HashMap<>();

        ManejoArchivos lector = new ManejoArchivos();

        ArrayList<String> lineas = lector.leerArchivo("./test/Salones.txt");

        for (String linea : lineas) {

            String[] aux = linea.split(";");
            
            String tipo=aux[1];
            
            int codigoTipo=-1;
            
            switch (tipo)
            {
                case "T":
                    codigoTipo=1;
                    break;
                case "P":
                    codigoTipo=2;
                    break;
                case "L":
                    codigoTipo=3;
                    break;
            }
            
            Salon salon = new Salon(Integer.parseInt(aux[0]), codigoTipo, Integer.parseInt(aux[2]));

            salones.put(salon.getIdentifacador(), salon);

        }

        return salones;
    }
    
}
