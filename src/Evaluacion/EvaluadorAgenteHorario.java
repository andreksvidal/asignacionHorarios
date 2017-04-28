/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluacion;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorario.AsignacionHorario;
import Agente.AgenteHorario.Curso;
import Agente.AgenteHorario.FranjaHoraria;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class EvaluadorAgenteHorario implements Evaluador {

    @Override
    public float evaluar(Agente agente) {
        AgenteHorario agenteHorario = (AgenteHorario) agente;
        return evaluarAgente(agenteHorario.getMemes());

    }

    private float evaluarAgente(ArrayList<Object> memes) {
        ArrayList<AsignacionHorario> horario = new ArrayList();

        for (Object meme : memes) {
            AsignacionHorario asignacion = (AsignacionHorario) meme;
            horario.add(asignacion);
        }

        return aplicarPenalizaciones(horario);

    }

    private float aplicarPenalizaciones(ArrayList<AsignacionHorario> horario) {

        float sumaPenalizaciones = 0;

        for (int asignacioni = 0; asignacioni < horario.size() - 1; asignacioni++) {

            AsignacionHorario asignacionActual = horario.get(asignacioni);
            String profesorAsignacionActual = asignacionActual.getCurso().getProfesorImparte();

            for (int asignacionvecinai = asignacioni + 1; asignacionvecinai < horario.size(); asignacionvecinai++) {
                AsignacionHorario asignacionVecina = horario.get(asignacionvecinai);
                String profesorAsignacionVecina = asignacionVecina.getCurso().getProfesorImparte();

                //Una vez obtenidas las 2 asignaciones a comparar, se comparan cada una de sus franjas.
                float penalizacionesFranja = aplicarPenalizacionesFranja(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario());
                float penalizacionProfesor = aplicarPenalizacionesProfesor(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario(), profesorAsignacionActual, profesorAsignacionVecina);
                float penalizacionSemestre = aplicarPenalizacionesSemestre(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario(), asignacionActual.getCurso(), asignacionVecina.getCurso());
                //Esta no la entiendo, preguntar!
                // float penalizacionTipoCurso= aplicarPenalizacionesTipoCurso(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario(), asignacionActual.getCurso(), asignacionVecina.getCurso());

                sumaPenalizaciones += penalizacionesFranja + penalizacionProfesor + penalizacionSemestre;
            }

        }

        return sumaPenalizaciones;
    }

    
    
    
    /********************************************************************************************************************/
    /**Estos tres metodos se van a convertir en uno solo, por ahora, se hace asi para facilitar la deteccion de errores**/
    /********************************************************************************************************************/
    
    private float aplicarPenalizacionesFranja(ArrayList<FranjaHoraria> franjasAsigActual, ArrayList<FranjaHoraria> franjasAsigVecina) {

        float sumaPenalizaciones = 0;

        for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
            for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {
                if (franjaAsigActual.compareTo(franjaAsigVecina) == 0) {
                    System.out.println("penalizacionFranja");
                    sumaPenalizaciones += 5;
                }
            }
        }

        return sumaPenalizaciones;
    }

    private float aplicarPenalizacionesProfesor(ArrayList<FranjaHoraria> franjasAsigActual, ArrayList<FranjaHoraria> franjasAsigVecina, String profesorAsignacionActual, String profesorAsignacionVecina) {
        float sumaPenalizaciones = 0;
        if (profesorAsignacionActual.equalsIgnoreCase(profesorAsignacionVecina)) {
            for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
                for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {
                    if (franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) {
                        sumaPenalizaciones += 5;
                    }
                }
            }

        }
        return sumaPenalizaciones;

    }

    private float aplicarPenalizacionesSemestre(ArrayList<FranjaHoraria> franjasAsigActual, ArrayList<FranjaHoraria> franjasAsigVecina, Curso curso1, Curso curso2) {

        float sumaPenalizaciones = 0;

        if (curso1.getSemestre() == curso2.getSemestre()) {
            for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
                for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {
                    if (franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) {
                        sumaPenalizaciones += 2;
                    }
                }
            }
        }

        return sumaPenalizaciones;
    }
    
    /********************************************************************************************************************/
    /**Estos tres metodos se van a convertir en uno solo, por ahora, se hace asi para facilitar la deteccion de errores**/
    /********************************************************************************************************************/

}
