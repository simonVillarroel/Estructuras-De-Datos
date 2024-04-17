package Grafos;

public class Nodo_Ady {
    private Nodo_Vertice vertice;
    private Nodo_Ady sigAdy;

    public Nodo_Ady(Nodo_Vertice vert, Nodo_Ady ady){
        this.vertice = vert;
        this.sigAdy = ady;
    }

    public Nodo_Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Nodo_Vertice vertice) {
        this.vertice = vertice;
    }

    public Nodo_Ady getSigAdy() {
        return sigAdy;
    }

    public void setSigAdy(Nodo_Ady sigAdy) {
        this.sigAdy = sigAdy;
    }
}
