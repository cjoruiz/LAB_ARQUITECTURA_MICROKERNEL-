/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.microkerne.common.entities;

/**
 *
 * @author crist
 */
public class Project {

    private int id;
    private String nombre;
    private String fechaAprovacionFormatoA;
    private String Estudiante1;
    private String Estudiante2;
    private String Profesor;
    private String Tipo;
    private String programa;


    public Project(int id, String nombre, String fechaAprovacionFormatoA, String Estudiante1, String Estudiante2, String Profesor, String Tipo, String programa) {
        this.id = id;
        this.nombre = nombre;
        this.fechaAprovacionFormatoA = fechaAprovacionFormatoA;
        this.Estudiante1 = Estudiante1;
        this.Estudiante2 = Estudiante2;
        this.Profesor = Profesor;
        this.Tipo = Tipo;
        this.programa = programa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaAprovacionFormatoA() {
        return fechaAprovacionFormatoA;
    }

    public void setFechaAprovacionFormatoA(String fechaAprovacionFormatoA) {
        this.fechaAprovacionFormatoA = fechaAprovacionFormatoA;
    }

    public String getEstudiante1() {
        return Estudiante1;
    }

    public void setEstudiante1(String Estudiante1) {
        this.Estudiante1 = Estudiante1;
    }

    public String getEstudiante2() {
        return Estudiante2;
    }

    public void setEstudiante2(String Estudiante2) {
        this.Estudiante2 = Estudiante2;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String Profesor) {
        this.Profesor = Profesor;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }



}
