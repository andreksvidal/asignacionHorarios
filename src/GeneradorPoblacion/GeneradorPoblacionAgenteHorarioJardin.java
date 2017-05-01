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
import Agente.AgenteHorario.TiempoJardin;
import Agente.AgenteHorarioJardin.AgenteHorarioJardin;
import Evaluacion.EvaluadorAgenteHorario;
import Evaluacion.EvaluadorAgenteHorarioJardin;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Andres Vidal Zemanate /* FIET-Ingenieria de Sistemas
 */
public class GeneradorPoblacionAgenteHorarioJardin implements GeneradorPoblacion {

    private final HashMap<Integer, Curso> cursosDisponibles;
    private final HashMap<Integer, Salon> salones;
    private final TiempoJardin tiempo;
    private final int cursosTotales;

    public GeneradorPoblacionAgenteHorarioJardin(HashMap<Integer, Curso> cursos, HashMap<Integer, Salon> salones, TiempoJardin tiempo, int cursosTotales) {
        this.cursosDisponibles = cursos;
        this.salones = salones;
        this.tiempo = tiempo;
        this.cursosTotales = cursosTotales;
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

        for (int i = 1; i <= cursosTotales; i++) {

            Curso curso = this.cursosDisponibles.get(i);

            //Calcular las franjas necesarias
            if (this.cursosDisponibles.containsKey(i)) {
                int franjasNecesarias = curso.getHoras() / 1;
                ArrayList<FranjaHoraria> franjas = new ArrayList();

                //para cada una de las franjas necesarias hacer:
                while (franjas.size() < franjasNecesarias) {

                    //obtener aleatoriamente un  dia y hora.
                    int dia = generarDiaAleatorio();
                    int hora = generarHoraAleatoria();
                    Salon salon = generarSalonValido(curso);

                    //Una vez con todos los parametros correctos, se crea una franja horaria pra ese salon.
                    FranjaHoraria franja = new FranjaHoraria(dia, hora, salon);

                    //Se verifica que esa franja no esté ya generada : 
                    boolean estaFranja = false;

                    for (FranjaHoraria franjax : franjas) {
                        if (franjax.compareToNoSalon(franja) == 0) {
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

        }

        //cuando se realiza la asignacion de horarios para todos los cursos, se crea un nuevo agente Horario y se retorna.
        AgenteHorarioJardin agente = new AgenteHorarioJardin(asignaciones, new EvaluadorAgenteHorarioJardin(), this);
        agente.setGenerador(this);
        return agente;

    }

    public int generarDiaAleatorio() {

        int dia = (int) (Math.random() * this.tiempo.getDiasJardin().size());

        return dia;
    }

    public int generarHoraAleatoria() {
        int randomHora = (int) (Math.random() * this.tiempo.getHorasJardin().size());
        return tiempo.getHorasJardin().get(randomHora);
    }

    public Salon generarSalonValido(Curso curso) {

        if (curso.getNombreCurso().contains("Pre")) {
            return salones.get(1);
        } else if (curso.getNombreCurso().contains("Trans")) {
            return salones.get(3);
        } else {
            return salones.get(2);
        }

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

    public Agente generarVecino(AgenteHorarioJardin original) {

        ArrayList memesVecino = new ArrayList();
        memesVecino = (ArrayList<AgenteHorario>) original.getMemes().clone();

        ArrayList<Integer> asignacionesCambiar = generarCursosCambiar(5, memesVecino.size());//El 10 indica el numero de cursos al que se le cambian las flanjas.

        for (int numCambio = 0; numCambio < asignacionesCambiar.size(); numCambio++) {

            AsignacionHorario asignacionVecino = (AsignacionHorario) memesVecino.get(asignacionesCambiar.get(numCambio));

            int randomFranjas = (int) (Math.random() * asignacionVecino.getFranjasHorario().size() - 1);

            //int dia = generarDiaAleatorio();
            int hora = generarHoraAleatoria();

            //Una vez con todos los parametros correctos, se crea una franja horaria pra ese salon.
            FranjaHoraria franja = new FranjaHoraria(asignacionVecino.getFranjasHorario().get(randomFranjas).getDia(), hora, asignacionVecino.getFranjasHorario().get(randomFranjas).getSalon());
            //Una vez con todos los parametros correctos, se crea una franja horaria pra ese salon.
            // FranjaHoraria franja = asignacionVecino.getFranjasHorario().get(randomFranjas);
            // franja.setDia(dia);
            // franja.setHora(hora);
            boolean estaFranja = false;

            for (FranjaHoraria franjax : asignacionVecino.getFranjasHorario()) {
                if (franjax.compareToNoSalon(franja) == 0) {
                    estaFranja = true;

                    break;
                }
            }

            if (!estaFranja) {
                asignacionVecino.getFranjasHorario().set(randomFranjas, franja);
            }

        }

        AgenteHorarioJardin vecino = new AgenteHorarioJardin(memesVecino, original.getEvaluador(), this);
        return vecino;
    }

    public boolean salonEsValidoParaCurso(Salon salon, Curso curso) {
        return salon.getCupoMaximo() >= curso.getCupoMaximo();
    }

    private ArrayList<Integer> generarCursosCambiar(int posicionesGenerar, int tamanioIndividuo) {

        ArrayList<Integer> posiciones = new ArrayList<>();

        while (posiciones.size() < posicionesGenerar) {
            Integer posrRandom = (int) (Math.random() * tamanioIndividuo - 1);

            if (!posiciones.contains(posrRandom)) {
                posiciones.add(posrRandom);
            }

        }

        return posiciones;
    }
}
