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
import Agente.AgenteHorario.Salon;
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
        

            for (int asignacionvecinai = asignacioni + 1; asignacionvecinai < horario.size(); asignacionvecinai++) {
                AsignacionHorario asignacionVecina = horario.get(asignacionvecinai);
         

                //Una vez obtenidas las 2 asignaciones a comparar, se comparan cada una de sus franjas.
                 sumaPenalizaciones += sumarPenalizaciones(asignacionVecina, asignacionActual);
                //Esta no la entiendo, preguntar!
                // float penalizacionTipoCurso= aplicarPenalizacionesTipoCurso(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario(), asignacionActual.getCurso(), asignacionVecina.getCurso());

               
            }

        }

        return sumaPenalizaciones;
    }

    public float sumarPenalizaciones(AsignacionHorario asignacionVecina, AsignacionHorario asignacionActual) {

         float sumaPenalizaciones = 0;

        String profesorAsignacionVecina = asignacionVecina.getCurso().getProfesorImparte();
        String profesorAsignacionActual = asignacionActual.getCurso().getProfesorImparte();

        ArrayList<FranjaHoraria> franjasAsigActual = asignacionActual.getFranjasHorario();
        ArrayList<FranjaHoraria> franjasAsigVecina = asignacionVecina.getFranjasHorario();

        boolean mismoProfesor= profesorAsignacionActual.equalsIgnoreCase(profesorAsignacionVecina);
        boolean mismoSemestre= asignacionActual.getCurso().getSemestre()==asignacionVecina.getCurso().getSemestre();
        boolean mismoCurso= asignacionActual.getCurso().getNombreCurso().equalsIgnoreCase(asignacionVecina.getCurso().getNombreCurso());
        
        
        for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
            for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {

                if (mismoProfesor) {
                    if (franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) {
                        sumaPenalizaciones += 5;
                    }
                }
                
                if(mismoSemestre)
                {
                    
                     if ((franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) && (!mismoCurso))
                    {
                        sumaPenalizaciones += 2;
                    }
                }
                
                if (franjaAsigActual.compareTo(franjaAsigVecina) == 0) {

                    sumaPenalizaciones += 5;
                }
               
            }
        }
        
        
        
        
        return sumaPenalizaciones;

    }

    
    
    public boolean salonEsValidoParaCurso(Salon salon, Curso curso) {
        return salon.getCupoMaximo() >= curso.getCupoMaximo();
    }
    /**
     * *****************************************************************************************************************
     */
    /**
     * Estos tres metodos se van a convertir en uno solo, por ahora, se hace asi
     * para facilitar la deteccion de errores*
     */
    /**
     * *****************************************************************************************************************
     */
    private float aplicarPenalizacionesFranja(ArrayList<FranjaHoraria> franjasAsigActual, ArrayList<FranjaHoraria> franjasAsigVecina) {

        float sumaPenalizaciones = 0;

        for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
            for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {
                if (franjaAsigActual.compareTo(franjaAsigVecina) == 0) {

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

    /**
     * *****************************************************************************************************************
     */
    /**
     * Estos tres metodos se van a convertir en uno solo, por ahora, se hace asi
     * para facilitar la deteccion de errores*
     */
    /**
     * *****************************************************************************************************************
     */
}
