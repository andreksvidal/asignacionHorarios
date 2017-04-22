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
    private String tipo;
    private int cupoMaximo;

    public Salon(int identifacador, String tipo, int cupoMaximo) {
        this.identifacador = identifacador;
        this.tipo = tipo;
        this.cupoMaximo = cupoMaximo;
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

   

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
