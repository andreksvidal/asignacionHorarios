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
public class FranjaHoraria implements Comparable<FranjaHoraria> {

    private int dia;
    private int hora;
    private Salon salon;

    public FranjaHoraria(int dia, int hora, Salon salon) {
        this.dia = dia;
        this.hora = hora;
        this.salon = salon;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;

    }

    @Override
    public int compareTo(FranjaHoraria otra) {

        if (this.dia == otra.getDia()) {
            if (this.hora == otra.getHora()) {
                if (this.salon.getIdentifacador() == otra.getSalon().getIdentifacador()) {
                    return 0;
                }
            }
        }

        return -1;
    }

    public int compareToNoSalon(FranjaHoraria otra) {

        if (this.dia == otra.getDia()) {
            if (this.hora == otra.getHora()) {

                return 0;

            }
        }

        return -1;
    }
}
