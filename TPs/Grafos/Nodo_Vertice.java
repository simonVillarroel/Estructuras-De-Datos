package Grafos;

public class Nodo_Vertice {
    private Object elemento;
    private Nodo_Vertice sigVertice;
    private Nodo_Ady primerAdy;

    public Nodo_Vertice(Object elem, Nodo_Vertice sig){
        this.elemento = elem;
        this.sigVertice = sig;
        this.primerAdy = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public Nodo_Vertice getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(Nodo_Vertice sigVertice) {
        this.sigVertice = sigVertice;
    }

    public Nodo_Ady getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(Nodo_Ady primerAdy) {
        this.primerAdy = primerAdy;
    }
}
