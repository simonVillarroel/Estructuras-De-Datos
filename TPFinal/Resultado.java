package TPFinal;

public class Resultado implements Comparable{
    private String ronda;
    private String ciudad;
    private String estadio;
    private int golesEq1;
    private int golesEq2;
 
    public int compareTo(Object otroResultado){
        return this.compareTo(otroResultado);
    }
    public Resultado(String ronda, String ciudad, String estadio, int golesEq1, int golesEq2){
        this.ronda = ronda;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getGolesEq1() {
        return golesEq1;
    }

    public void setGolesEq1(int golesEq1) {
        this.golesEq1 = golesEq1;
    }

    public int getGolesEq2() {
        return golesEq2;
    }

    public void setGolesEq2(int golesEq2) {
        this.golesEq2 = golesEq2;
    }
    
    public String toString(){
        return "( " + this.ronda + " --> " + this.estadio + ", " 
        + this.ciudad + ". Resultado: " + this.golesEq1 + " - " + golesEq2 + " )";
    }
}
