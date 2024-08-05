package TPFinal;

public class Ciudad {
    private String nombre;          //clave
    private boolean alojaminento;
    private boolean sede;

    public Ciudad(String nombre, boolean sede) {
        this.nombre = nombre;
        this.alojaminento = true;
        this.sede = sede;
    }

    public Ciudad(String nombre, boolean alojaminento, boolean sede) {
        this.nombre = nombre;
        this.alojaminento = alojaminento;
        this.sede = sede;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean hayAlojaminento() {
        return alojaminento;
    }

    public void setAlojaminento(boolean alojaminento) {
        this.alojaminento = alojaminento;
    }

    public boolean esSede() {
        return sede;
    }

    public void setSede(boolean sede) {
        this.sede = sede;
    }

    public String toString(){
        return "[Sede de la copa : " + this.sede + ". Alojamiento disponible: " + this.alojaminento + "]";
    }
}
