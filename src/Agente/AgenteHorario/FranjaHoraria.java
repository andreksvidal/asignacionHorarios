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
public class FranjaHoraria implements Comparable<FranjaHoraria>{

    private String dia;
    private String hora;
    private Salon salon;

    public FranjaHoraria(String dia, String hora, Salon salon)  {
        this.dia = dia;
        this.hora = hora;
        this.salon = salon;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
       
        if(this.dia.equalsIgnoreCase(otra.getDia()))
        {
            if(this.hora.equals(otra.getHora()))
            {
                if(this.salon.getIdentifacador()==otra.getSalon().getIdentifacador())
                {
                    return 0;
                }
            }
        }
        
        return -1;
    }

    

}
