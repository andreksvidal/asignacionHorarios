/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneradorPoblacion;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorario.AsignacionHorario;
import Agente.AgenteHorario.Curso;
import Agente.AgenteHorario.FranjaHoraria;
import Agente.AgenteHorario.Salon;
import GestorArchivos.LectorArchivosHorarios;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class GeneradorPoblacionAgenteHorario implements GeneradorPoblacion {

    ArrayList<Curso> cursosDisponibles;
    ArrayList<Salon> salonesDisponibles;
    ArrayList<FranjaHoraria> franjasDisponibles;

    public GeneradorPoblacionAgenteHorario() {
        LectorArchivosHorarios lector = new LectorArchivosHorarios();
        this.cursosDisponibles = lector.leerCursos();
        this.salonesDisponibles = lector.leerSalones();
        this.franjasDisponibles = crearFranjas();
    }

    public ArrayList<Salon> getSalonesDisponibles() {
        return salonesDisponibles;
    }

    public ArrayList<Curso> getCursosDisponibles() {
        return cursosDisponibles;
    }

    @Override
    public Agente generarIndivuiduo() {

        ArrayList<AsignacionHorario> asignaciones = new ArrayList();
        ArrayList<FranjaHoraria> franjasMateria= new ArrayList();
        franjasMateria.add(franjasDisponibles.get(0));
        AsignacionHorario asignacion= new AsignacionHorario(franjasMateria,cursosDisponibles.get(0));
        
        asignaciones.add(asignacion);
        Agente agenteHorario = new AgenteHorario(asignaciones);
        
        return agenteHorario;
    }

    @Override
    public ArrayList<Agente> generarPoblacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ArrayList<FranjaHoraria> crearFranjas() {

        ArrayList<FranjaHoraria> franjas = new ArrayList();

        FranjaHoraria franjaHoraria;

        for (int i = 1; i <= 5; i++) {
            franjaHoraria = new FranjaHoraria();
            franjaHoraria.setDia(i);
            franjas.add(franjaHoraria);
        }

        int horainicio = 7;

        for (int i = 0; i < 5; i++) {

            franjas.get(i).setHora(7);
            franjas.get(i).setHora(9);
            franjas.get(i).setHora(11);
            franjas.get(i).setHora(2);
            franjas.get(i).setHora(4);
            franjas.get(i).setHora(6);

        }
        
        
        return  franjas;

    }

}
