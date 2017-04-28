/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

import Agente.Agente;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;

import Evaluacion.EvaluadorAgenteHorario;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;

/**
 *
 * @author ingesis
 */
public class AgenteHorario extends Agente  implements Solucion, Comparable<AgenteHorario> {

    private EvaluadorAgenteHorario evaluador;

    public AgenteHorario(ArrayList<AsignacionHorario> horarios) {
        memes = horarios;
        this.evaluador = new EvaluadorAgenteHorario();
    }

    public AgenteHorario() {

    }

   
    public EvaluadorAgenteHorario getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(EvaluadorAgenteHorario evaluador) {
        this.evaluador = evaluador;
    }

    private ArrayList<Agente> obtenerVecinos() {

        //3 vecinos
        ArrayList<Agente> vecinos = new ArrayList();
        for (int i = 0; i < 3; i++) {
            vecinos.add(generarVecino());
        }

        return vecinos;

    }

    public AgenteHorario generarVecino() {

        AgenteHorario agenteVecino = new AgenteHorario((ArrayList<AsignacionHorario>) this.memes.clone());

        ArrayList<Integer> asignacionesVecino = generarCursosCambiar(1);//El 1 indica el numero de cursos al que se le cambian las flanjas.

       
        ArrayList<Object> memesVecino = agenteVecino.getMemes();

        
        for (int i = 0; i < asignacionesVecino.size(); i++) {
            AsignacionHorario asignacionVecino = (AsignacionHorario) memesVecino.get(asignacionesVecino.get(i));
            Tiempo tiempo = new Tiempo();

            int franjasNecesarias = asignacionVecino.getCurso().getHoras() / 2;

            ArrayList<FranjaHoraria> franjas = new ArrayList();

            while (franjas.size() < franjasNecesarias) {
                //obtener aleatoriamente un  dia y hora.
                int randomDia = (int) (Math.random() * tiempo.getDias().size() - 1);
                int randomHora = (int) (Math.random() * tiempo.getHoras().size() - 1);

                String dia = tiempo.getDias().get(randomDia);
                String hora = tiempo.getHoras().get(randomHora);

                //obtener un salon de forma aleatoria
                GeneradorPoblacionAgenteHorario generador = new GeneradorPoblacionAgenteHorario();

                int randomSalones = (int) (Math.random() * generador.getSalonesDisponibles().size() - 1);

                Salon salon = generador.getSalonesDisponibles().get(randomSalones);

                //mientras el salon no sea valido para el curso actual, escoger otro salon.
                //NOTA:AQUI SE CAMBIO EL TRABAJO DE GRADO EN EL ARCHIVO DE CURSOS, DADO QUE TIENE cupo maximo para 120 , Y NO HAY SALONES Con ese cupo.
                //Se quedaria en un ciclo infinito, aunque lo correcto será mandar un error...
                while (!generador.salonEsValidoParaCurso(salon, asignacionVecino.getCurso())) {
                    randomSalones = (int) (Math.random() * generador.getSalonesDisponibles().size() - 1);
                    salon = generador.getSalonesDisponibles().get(randomSalones);
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

            asignacionVecino.setFranjasHorario(franjas);

        }

        return agenteVecino;

    }

    private ArrayList<Integer> generarCursosCambiar(int posicionesGenerar) {

        ArrayList<Integer> posiciones = new ArrayList<>();

        while (posiciones.size() < posicionesGenerar) {
            Integer posrRandom = (int) (Math.random() * this.memes.size() - 1);

            if (!posiciones.contains(posrRandom)) {
                posiciones.add(posrRandom);
            }

        }

        return posiciones;
    }

    /**
     * *************************************************************************************************
     */
    /*FIN IMPLEMENTACION METODOS NECESARIOS PARA QUE EL AGENTE PUEDA SER MEJORADO CON LA BUSQUEDA LOCAL*/
    /**
     * *************************************************************************************************
     */
    @Override
    public int compareTo(AgenteHorario o) {
        for (int i = 0; i < memes.size(); i++) {
            AsignacionHorario asignacion = (AsignacionHorario) memes.get(i);
            AsignacionHorario asignacionVecina = (AsignacionHorario) o.getMemes().get(i);

            if (asignacion.compareTo(asignacionVecina) == 0) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public void evaluar() {
       this.evaluacion=this.evaluador.evaluar(this);
    }

    @Override
    public double getEvaluacion() {
       return this.evaluacion;
    }

    @Override
    public ArrayList<Solucion> getVecinos() {
       
        
      ArrayList<Agente> vecinosAgente=obtenerVecinos();
      ArrayList<Solucion> vecinosSolucion=new ArrayList<Solucion>();
      
        for (Agente agente: vecinosAgente) {
            
            Solucion solucion =(AgenteHorario) agente;
            vecinosSolucion.add(solucion);
        }
        return vecinosSolucion;
    }

    @Override
    public Solucion getVecinoAleatorio() {
          
           AgenteHorario vecino =(AgenteHorario) generarVecino();
           
        
           vecino.setEvaluacion(this.evaluador.evaluar(vecino));
         
           
           Solucion solucion = (AgenteHorario)vecino;
           
           return solucion;
    }
}
