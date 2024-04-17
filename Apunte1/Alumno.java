package Apunte1;

public class Alumno {
    private String legajo;
    private String nombre;
    private int dni;
    
    public Alumno(String leg){
        this.legajo = leg;
        this.nombre = "";
        this.dni = 0;
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni(){
        return dni;
    }

    public void setDni(int dni){
        this.dni = dni;
    }

    public String toString(){
        return "Legajo: " + this.legajo + ". Nombre: " + this.nombre + ". Dni: " + this.dni;
    }
}
