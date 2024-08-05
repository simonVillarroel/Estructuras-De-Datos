package TPFinal;

public class Equipo {
    private String pais;
    private String tecnico;
    private char grupo;
    private int puntos;
    private int golesFavor;
    private int golesContra;
    
    public Equipo(String pais, String tecnico, char grupo, int puntos, int golesFavor, int golesContra) {
        this.pais = pais;
        this.tecnico = tecnico;
        this.grupo = grupo;
        this.puntos = puntos;
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
    }

    public Equipo(String pais, String tecnico, char grupo) {
        this.pais = pais;
        this.tecnico = tecnico;
        this.grupo = grupo;
        this.puntos = 0;
        this.golesFavor = 0;
        this.golesContra = 0;
    }

    public String getPais() {
        return pais;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void sumarGolesFavor(int golesFavor) {
        this.golesFavor += golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void sumarGolesContra(int golesContra) {
        this.golesContra += golesContra;
    }

    public String toString(){
        return "[Tecnico: " + tecnico + ". Grupo: " + grupo + "| Puntos: " + puntos + ". Goles a favor: " + golesFavor
            + ". Goles en contra: " + golesContra + ". Diferencia de goles: " + (golesFavor - golesContra) + "]";
    }
}
