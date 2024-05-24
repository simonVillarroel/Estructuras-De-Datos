package conjuntistas;

public class NodoABB {
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable elem){
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
    }
    
    public NodoABB(Comparable elem, NodoABB izq, NodoABB der){
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}
