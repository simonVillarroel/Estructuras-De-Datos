package grafos;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Double etiqueta;

    public NodoAdy(NodoVert vert, NodoAdy siguiente, Double etiq){
        this.vertice = vert;
        this.sigAdyacente = siguiente;
        this.etiqueta = etiq;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public Double getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(Double etiqueta) {
        this.etiqueta = etiqueta;
    }

    
}
