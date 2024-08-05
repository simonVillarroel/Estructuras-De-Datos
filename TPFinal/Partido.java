package TPFinal;

public class Partido {
    private Comparable equipo1;
    private Comparable equipo2;
    
    public Partido(Comparable equipo1, Comparable equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public Comparable getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Comparable equipo1) {
        this.equipo1 = equipo1;
    }

    public Comparable getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Comparable equipo2) {
        this.equipo2 = equipo2;
    }

    public String toString(){
        return equipo1 + " vs " + equipo2;
    }
}
