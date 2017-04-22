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
import Agente.AgenteHorario.Tiempo;
import GestorArchivos.LectorArchivosHorarios;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class GeneradorPoblacionAgenteHorario implements GeneradorPoblacion {

    ArrayList<Curso> cursosDisponibles;
    ArrayList<Salon> salonesDisponibles;

    public GeneradorPoblacionAgenteHorario() {
        LectorArchivosHorarios lector = new LectorArchivosHorarios();
        this.cursosDisponibles = lector.leerCursos();
        this.salonesDisponibles = lector.leerSalones();

    }

    public ArrayList<Salon> getSalonesDisponibles() {
        return salonesDisponibles;
    }

    public ArrayList<Curso> getCursosDisponibles() {
        return cursosDisponibles;
    }

    /**
     * Permite generar un individuo de Asignacion de Horarios. Este metodo
     * utiliza los salones y cursos disponibles pasados desde archivo. El metodo
     * se encarga de validar que para un curso, no se generen 2 franjas horarias
     * corruptas. Sin embargo, no verifica la validez respecto a las franjas
     * asignadas a otros cursos.
     *
     * @return Un nuevo individuo con una asignacion de horarios especifica. El
     * agente generado contiene en cada posicion un curso de los disponibles, y
     * el tamaño de la lista de memes es igual al numero de cursos disponibles.
     */
    public Agente generarIndivuiduo() {

        ArrayList<AsignacionHorario> asignaciones = new ArrayList();

        /*PARA CADA UNO DE LOS Cursos disponibles*/
        for (Curso curso : cursosDisponibles) {

            //Calcular las franjas necesarias
            int franjasNecesarias = curso.getHoras() / 2;
            ArrayList<FranjaHoraria> franjas = new ArrayList();

            Tiempo tiempo = new Tiempo();

            //para cada una de las franjas necesarias hacer:
            int franjasGeneradas = 0;
            while (franjas.size() < franjasNecesarias) {

                //obtener aleatoriamente un  dia y hora.
                int randomDia = (int) (Math.random() * tiempo.getDias().size() - 1);
                int randomHora = (int) (Math.random() * tiempo.getHoras().size() - 1);

                String dia = tiempo.getDias().get(randomDia);
                String hora = tiempo.getHoras().get(randomHora);

                //obtener un salon de forma aleatoria
                int randomSalones = (int) (Math.random() * this.salonesDisponibles.size() - 1);

                Salon salon = salonesDisponibles.get(randomSalones);

                //mientras el salon no sea valido para el curso actual, escoger otro salon.
                //NOTA:AQUI SE CAMBIO EL TRABAJO DE GRADO EN EL ARCHIVO DE CURSOS, DADO QUE TIENE cupo maximo para 120 , Y NO HAY SALONES Con ese cupo.
                //Se quedaria en un ciclo infinito, aunque lo correcto será mandar un error...
                while (!salonEsValidoParaCurso(salon, curso)) {
                    randomSalones = (int) (Math.random() * this.salonesDisponibles.size() - 1);
                    salon = salonesDisponibles.get(randomSalones);
                }

                //Una vez con todos los parametros correctos, se crea una franja horaria pra ese salon.
                FranjaHoraria franja = new FranjaHoraria(dia, hora, salon);

                //Se verifica que esa franja no esté ya generada : 
                boolean estaFranja = false;
                for (FranjaHoraria franjax : franjas) {
                    if (franjax.compareTo(franja) == 0) {
                        estaFranja = true;
                        break;
                    }
                }

                //si no esta generada, se añade a la lista
                if (!estaFranja) {
                    franjas.add(franja);
                }

            }

            //Una vez generadas las franjas necesarias, se crea la asignacion de horarios para el curso actual:
            AsignacionHorario asignacion = new AsignacionHorario(franjas, curso);
            asignaciones.add(asignacion);

        }

        //cuando se realiza la asignacion de horarios para todos los cursos, se crea un nuevo agente Horario y se retorna.
        AgenteHorario agente = new AgenteHorario(asignaciones);
        return agente;

    }

    @Override
    public ArrayList<Agente> generarPoblacion(int tamanioPoblacion) {
        ArrayList<Agente> poblacion = new ArrayList();

        for (int individuox = 0; individuox < tamanioPoblacion; individuox++) {
            poblacion.add(generarIndivuiduo());
        }

        System.out.println("Se generó la poblacion.");
        return poblacion;

    }

    private boolean salonEsValidoParaCurso(Salon salon, Curso curso) {
        return salon.getCupoMaximo() >= curso.getCupoMaximo();
    }

}
