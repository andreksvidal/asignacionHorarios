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
public class Curso {

    private String nombreCurso;
    private int id;
    private String grupo;
    int horas;
    private int semestre;
    private int cupoMaximo;
    private String profesorImparte;
    private String tipo;

    public Curso(int id, String nombreCurso, String grupo, int horas, int semestre,String tipo, int cupoMaximo) {
        this.nombreCurso = nombreCurso;
        this.id = id;
        this.grupo = grupo;
        this.horas = horas;
        this.semestre = semestre;
        this.cupoMaximo = cupoMaximo;
        this.tipo=tipo;
        this.profesorImparte="pacho";
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

}
