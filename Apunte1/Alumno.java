package Apunte1;

public class Alumno {
    private String legajo;
    private String nombre;
    private String apellido;
    private int dni;
    private String domicilio;
    private int telefono;
    private String usuario;
    private String clave;   
    
    public Alumno(String legajo, String nombre, String apellido, int dni,
                     String domicilio, int telefono, String usuario, String clave) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public String toString(){
        return "Legajo: " + this.legajo + ". Nombre: " + this.nombre + ". Dni: " + this.dni 
        + ". Domicilio: " + this.domicilio + ". Telefono: " + this.telefono + ". Usuario: " + this.usuario;
    }
}
