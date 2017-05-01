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
import Evaluacion.EvaluadorAgenteHorario;
import GestorArchivos.LectorArchivosHorarios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author ingesis
 */
public class GeneradorPoblacionAgenteHorario implements GeneradorPoblacion {

    private final HashMap<Integer, Curso> cursosDisponibles;
    private final HashMap<Integer, Salon> salones;
    private final Tiempo tiempo;
    private final int cursosTotales;

    public GeneradorPoblacionAgenteHorario(HashMap<Integer, Curso> cursos, HashMap<Integer, Salon> salones, Tiempo tiempo, int cursosTotales) {
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
                int franjasNecesarias = curso.getHoras() / 2;
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
        AgenteHorario agente = new AgenteHorario(asignaciones, new EvaluadorAgenteHorario(),this);
        agente.setGenerador(this);
        return agente;

    }

    public Agente generarVecino(AgenteHorario original) {

        ArrayList memesVecino = new ArrayList();
        memesVecino = (ArrayList<AgenteHorario>) original.getMemes().clone();

        ArrayList<Integer> asignacionesCambiar = generarCursosCambiar(10, memesVecino.size());//El 10 indica el numero de cursos al que se le cambian las flanjas.

        for (int numCambio = 0; numCambio < asignacionesCambiar.size(); numCambio++) {

            AsignacionHorario asignacionVecino = (AsignacionHorario) memesVecino.get(asignacionesCambiar.get(numCambio));

            int randomFranjas = (int) (Math.random() * asignacionVecino.getFranjasHorario().size() - 1);

            int dia = generarDiaAleatorio();
            int hora = generarHoraAleatoria();

            //Una vez con todos los parametros correctos, se crea una franja horaria pra ese salon.
            FranjaHoraria franja = asignacionVecino.getFranjasHorario().get(randomFranjas);
            franja.setDia(dia);
            franja.setHora(hora);
                 
            //asignacionVecino.getFranjasHorario().set(randomFranjas, franja);
        }

        AgenteHorario vecino = new AgenteHorario(memesVecino, original.getEvaluador(),this);
        return vecino;

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
    
    

    @Override
    public ArrayList<Agente> generarPoblacion(int tamanioPoblacion) {
        ArrayList<Agente> poblacion = new ArrayList();

        for (int individuox = 0; individuox < tamanioPoblacion; individuox++) {
            poblacion.add(generarIndivuiduo());
        }

        System.out.println("Se generó la poblacion.");
        return poblacion;

    }

    public int generarDiaAleatorio() {

        return  (int) (Math.random() * this.tiempo.getDiasUniversidad().size() - 1);
        

    }

    public int generarHoraAleatoria() {
        int randomHora = (int) (Math.random() * this.tiempo.getHorasUniversidad().size() - 1);

        return tiempo.getHorasUniversidad().get(randomHora);
    }

    public Salon generarSalonValido(Curso curso) {
        int randomSalon = (int) ((Math.random() * this.salones.size()) + 1);

        Salon salon = salones.get(randomSalon);

        while (!salonEsValidoParaCurso(salon, curso)) {

            randomSalon = (int) ((Math.random() * this.salones.size()) + 1);
            salon = salones.get(randomSalon);
        }

        return salon;
    }

    /* public Salon generarSalonValido2(Curso curso) {
     Random random = new Random();

 

     boolean valido = false;

     while (valido == false) {
     Double valor = random.nextDouble();
     if (valor <= 0.33) {
     HashMap<Integer, Salon> salonesTeoricos = salonesDisponibles.get("T");

     int randomSalon = (int) (Math.random() * salonesTeoricos.size() - 1);
     Salon salon = salonesTeoricos.get(randomSalon);

     if (salonEsValidoParaCurso(salon, curso)) {
     return salon;
     }
     } else if (valor > 0.33 && valor < 0.66) {
     HashMap<Integer, Salon> salonesPracticos = salonesDisponibles.get("P");
     int randomSalon = (int) (Math.random() * salonesPracticos.size() - 1);
     Salon salon = salonesPracticos.get(randomSalon);
     if (salonEsValidoParaCurso(salon, curso)) {
     return salon;
     }
     } else {
     HashMap<Integer, Salon> salonesLaboratorio = salonesDisponibles.get("L");
     int randomSalon = (int) (Math.random() * salonesLaboratorio.size() - 1);
     Salon salon = salonesLaboratorio.get(randomSalon);
     if (salonEsValidoParaCurso(salon, curso)) {
     return salon;
     }
     }

     }

     return null;

     }*/
    public boolean salonEsValidoParaCurso(Salon salon, Curso curso) {
        return salon.getCupoMaximo() >= curso.getCupoMaximo();
    }

    /*
     public Salon generarSalonValido(Curso curso) {
     String tipo = curso.getTipo();

     Salon salon;

     if (tipo.equalsIgnoreCase("T")) {

     HashMap<Integer, Salon> salonesTeoricos = salonesDisponibles.get("T");

     int randomSalon = (int) (Math.random() * salonesTeoricos.size() - 1);
     salon = salonesTeoricos.get(randomSalon);

     if (randomSalon == -1) {
     System.out.println("salon -1.");
     }

     while (!salonEsValidoParaCurso(salon, curso)) {
     randomSalon = (int) (Math.random() * salonesTeoricos.size() - 1);
     salon = salonesTeoricos.get(randomSalon);
     }
     } else if (tipo.equalsIgnoreCase("TP")) {
     Random random = new Random();

     if (random.nextDouble() > 0.5) {
     //Le asigno un teorico.
     HashMap<Integer, Salon> salonesTeoricos = salonesDisponibles.get("T");
     int randomSalon = (int) (Math.random() * salonesTeoricos.size() - 1);
     salon = salonesTeoricos.get(randomSalon);

     while (!salonEsValidoParaCurso(salon, curso)) {
     randomSalon = (int) (Math.random() * salonesTeoricos.size() - 1);
     salon = salonesTeoricos.get(randomSalon);
     }

     } else {
     //Le asigno un practico
     HashMap<Integer, Salon> salonesPracticos = salonesDisponibles.get("P");
     int randomSalon = (int) (Math.random() * salonesPracticos.size() - 1);
     salon = salonesPracticos.get(randomSalon);

     while (!salonEsValidoParaCurso(salon, curso)) {
     randomSalon = (int) (Math.random() * salonesPracticos.size() - 1);
     salon = salonesPracticos.get(randomSalon);
     }
     }

     } else {
     HashMap<Integer, Salon> salonesLaboratorio = salonesDisponibles.get("L");
     int randomSalon = (int) (Math.random() * salonesLaboratorio.size() - 1);
     salon = salonesLaboratorio.get(randomSalon);

     while (!salonEsValidoParaCurso(salon, curso)) {
     randomSalon = (int) (Math.random() * salonesLaboratorio.size() - 1);
     salon = salonesLaboratorio.get(randomSalon);
     }

     }

     return salon;

     }
    
     */
}
