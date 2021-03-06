/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Evaluacion;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorario.AsignacionHorario;
import Agente.AgenteHorario.FranjaHoraria;
import Agente.AgenteHorarioJardin.AgenteHorarioJardin;
import java.util.ArrayList;

/**
 * 
 * @author Andres Vidal Zemanate
/* FIET-Ingenieria de Sistemas
 */
public class EvaluadorAgenteHorarioJardin  implements Evaluador{

    @Override
    public void evaluar(Agente agente) {
        AgenteHorarioJardin agenteHorario = (AgenteHorarioJardin) agente;
        agente.setEvaluacion(aplicarPenalizaciones(agenteHorario.getMemes()));
    }
    
    private float aplicarPenalizaciones(ArrayList<Object> horario) {
         float sumaPenalizaciones = 0;

        for (int asignacioni = 0; asignacioni < horario.size() - 1; asignacioni++) {

            AsignacionHorario asignacionActual = (AsignacionHorario)horario.get(asignacioni);
            
            for (int asignacionvecinai = asignacioni + 1; asignacionvecinai < horario.size(); asignacionvecinai++) {
                AsignacionHorario asignacionVecina = (AsignacionHorario)horario.get(asignacionvecinai);
         

                //Una vez obtenidas las 2 asignaciones a comparar, se comparan cada una de sus franjas.
                 sumaPenalizaciones += sumarPenalizacionesAsignacion(asignacionVecina, asignacionActual);
                //Esta no la entiendo, preguntar!
                // float penalizacionTipoCurso= aplicarPenalizacionesTipoCurso(asignacionActual.getFranjasHorario(), asignacionVecina.getFranjasHorario(), asignacionActual.getCurso(), asignacionVecina.getCurso());

               
            }

        }

        return sumaPenalizaciones;
    }
    
    
    
    public float sumarPenalizacionesAsignacion(AsignacionHorario asignacionVecina, AsignacionHorario asignacionActual) {
       
        float sumaPenalizaciones = 0;
     

        ArrayList<FranjaHoraria> franjasAsigActual = asignacionActual.getFranjasHorario();
        ArrayList<FranjaHoraria> franjasAsigVecina = asignacionVecina.getFranjasHorario();

        boolean mismoProfesor= asignacionVecina.getCurso().getProfesorImparte().equals(asignacionActual.getCurso().getProfesorImparte());
        boolean mismoSemestre= asignacionActual.getCurso().getSemestre()==asignacionVecina.getCurso().getSemestre();
        boolean mismoCurso= asignacionActual.getCurso().getNombreCurso().equals(asignacionVecina.getCurso().getNombreCurso());
        
        int cont=0;
        for (FranjaHoraria franjaAsigActual : franjasAsigActual) {
            for (FranjaHoraria franjaAsigVecina : franjasAsigVecina) {

                if (mismoProfesor) {
                    if (franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) {
                        sumaPenalizaciones += 5;
                    }
                }
                
                if(mismoSemestre)
                {
                    //pueden existir dos grupos del mismo curso de un mismo semestre, dictandose a la misma hora en el mismo día.
                    //Sin embargo, 2 cursos del mismo semestre , no se pueden dictar a la misma hora y el mismo dia.
                     if ((franjaAsigActual.compareToNoSalon(franjaAsigVecina) == 0) && (!mismoCurso))
                    {
                        sumaPenalizaciones += 2;
                    }
                }
                
               if(franjaAsigActual.compareTo(franjaAsigVecina)==0)
               {
                    sumaPenalizaciones += 5;
               }    
            }
            
            
            
           for (int aux=cont+1;aux<franjasAsigActual.size() ;aux++) {
                
               FranjaHoraria franjaHermana=franjasAsigActual.get(aux);
                    
             
                    if(franjaHermana.getDia()==franjaAsigActual.getDia())
                    {
                        sumaPenalizaciones+=5;
                    }
              
            }
           
           cont++;
        }  
        
        
        
       
        
        return sumaPenalizaciones;

    }

}
