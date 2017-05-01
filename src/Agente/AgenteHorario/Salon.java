/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agente.AgenteHorario;

/**
 *
 * @author ingesis
 */
public class Salon {

    private int identifacador;
    private int tipo;
    private int cupoMaximo;

    public Salon(int identifacador, int tipo, int cupoMaximo) {
        this.identifacador = identifacador;
        this.tipo = tipo;
        this.cupoMaximo = cupoMaximo;
    }

    public Salon() {
       
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getIdentifacador() {
        return identifacador;
    }

    public void setIdentifacador(int identifacador) {
        this.identifacador = identifacador;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

   

   

}
