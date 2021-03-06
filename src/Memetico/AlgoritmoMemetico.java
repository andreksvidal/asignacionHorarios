/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memetico;

import Agente.Agente;
import Agente.AgenteHorario.AgenteHorario;
import Agente.AgenteHorarioJardin.AgenteHorarioJardin;
import BusquedaLocal.EnfriamientoSimulado.EnfriamientoSimulado;
import BusquedaLocal.ProblemaEnfriamiento.Solucion;
import Evaluacion.Evaluador;
import GeneradorPoblacion.GeneradorPoblacion;
import GeneradorPoblacion.GeneradorPoblacionAgenteHorario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author acer_acer
 */
public class AlgoritmoMemetico {

    EnfriamientoSimulado algoritmo;
    GeneradorPoblacion generador;
    int tamPob;
    int tamConservar;
    int maxIter;
    Evaluador evaluador;

    public Agente ejecutar(GeneradorPoblacion generador, int numPob, int tamConservar, Recombinacion tipoRecombinacion, EnfriamientoSimulado algoritmo, Evaluador evaluador, String problema,int maxIter) {
        
        this.evaluador = evaluador;
        this.tamPob = numPob;
        int it = 0;
        this.tamConservar = tamConservar;
        this.algoritmo = algoritmo;
        this.generador = generador;
        this.maxIter=maxIter;

        ArrayList<Agente> pobInicial = generador.generarPoblacion(numPob);

        EvaluarPoblacion(pobInicial);

        System.out.println("Problema : [ " + problema + "]");
        System.out.println("[ok]Se evaluó la poblacion");
        System.out.println("Aplicando busqueda local");
        ArrayList<Agente> poblacion = busquedaLocal(pobInicial, problema);
        System.out.println("[ok]Se aplicó busqueda local");

        //500 para universidad...
        //1000 o mas para jardin...
        while (it < maxIter) {

            //System.out.println("it" + it);
            ArrayList<Agente> nuevaPoblacion = tipoRecombinacion.recombinacion(poblacion);
            EvaluarPoblacion(nuevaPoblacion);
            // ArrayList<Agente> mejora= busquedaLocal(nuevaPoblacion);
            //Probar aplicando una vez mas busqueda local

            //poblacion = busquedaLocalMejores(mejores,1);
            poblacion = (ArrayList<Agente>) actualizarPoblacion(numPob - 1, poblacion, nuevaPoblacion);

            if (poblacion.get(0).getEvaluacionAgente() == 0) {
                break;
            }
            //Criterio convergencia
            if (evaluacionesSimilares()) {
                poblacion = reiniciarPoblacion(poblacion, problema);
            }

            it++;
        }

        System.out.println("El mejor : " + poblacion.get(0).getEvaluacionAgente());
        return poblacion.get(0);
    }

    public ArrayList<Agente> busquedaLocal(ArrayList<Agente> pobInicial, String problema) {
        ArrayList<Agente> poblacion = new ArrayList<>();
        int cont = 0;
        for (Agente agente : pobInicial) {
            Solucion solucion;
            if (problema.equals("jardin")) {
                solucion = (AgenteHorarioJardin) agente;
            } else {
                solucion = (AgenteHorario) agente;
            }

            poblacion.add((Agente) algoritmo.ejecutarAlgoritmo(solucion, 15));
            //System.out.println("busqueda local" + cont);
            cont++;
        }
        return poblacion;
    }

    public ArrayList<Agente> busquedaLocalAleatoria(ArrayList<Agente> pobInicial) {

        ArrayList<Integer> posMejorar = generarIndividuosMejorar(20);

        for (Integer posmejorar : posMejorar) {
            Solucion solucion = (AgenteHorario) pobInicial.get(posmejorar);
            pobInicial.set(posmejorar, (Agente) algoritmo.ejecutarAlgoritmo(solucion, 10));

        }

        return pobInicial;
    }

    public ArrayList<Agente> busquedaLocalMejores(ArrayList<Agente> pobInicial, int primerosi) {
        for (int i = 0; i < primerosi; i++) {
            Solucion solucion = (AgenteHorario) pobInicial.get(i);
            pobInicial.set(i, (Agente) algoritmo.ejecutarAlgoritmo(solucion, 10));
        }

        return pobInicial;

    }

    private List<Agente> actualizarPoblacion(int tamPob, ArrayList<Agente> poblacion, ArrayList<Agente> nuevaPoblacion) {
        ArrayList<Agente> unionPoblacion = new ArrayList<>();
        unionPoblacion.addAll(poblacion);
        unionPoblacion.addAll(nuevaPoblacion);
        Collections.sort(unionPoblacion, (Agente o1, Agente o2) -> {
            return (int) ((o2.getEvaluacionAgente() - o1.getEvaluacionAgente()) * -1);
        });
        return new ArrayList<>(unionPoblacion.subList(0, tamPob));
    }

    private ArrayList<Agente> reiniciarPoblacion(ArrayList<Agente> poblacion, String problema) {
        ArrayList<Agente> nuevaPoblacion = generador.generarPoblacion(tamPob - tamConservar);
        nuevaPoblacion = busquedaLocal(nuevaPoblacion, problema);
        Collections.sort(poblacion, (Agente o1, Agente o2) -> {
            return (int) ((o2.getEvaluacionAgente() - o1.getEvaluacionAgente()));
        });

        nuevaPoblacion.addAll(new ArrayList<>(poblacion.subList(0, tamConservar)));
        return nuevaPoblacion;
    }

    private boolean evaluacionesSimilares() {
        //Si un gran porcentaje de los agentes tienen evaluacion parecida
        return false;
    }

    public void EvaluarPoblacion(ArrayList<Agente> poblacion) {
        poblacion.stream().forEach((agente) -> {
            evaluador.evaluar(agente);
        });
    }

    private ArrayList<Integer> generarIndividuosMejorar(int posicionesGenerar) {

        ArrayList<Integer> posiciones = new ArrayList<>();

        while (posiciones.size() < posicionesGenerar) {
            Integer posrRandom = (int) (Math.random() * tamPob - 1);
            if (!posiciones.contains(posrRandom)) {
                posiciones.add(posrRandom);
            }
        }

        return posiciones;
    }

}
