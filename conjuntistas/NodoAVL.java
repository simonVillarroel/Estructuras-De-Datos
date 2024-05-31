package conjuntistas;

public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem){
        this.elem = elem;
    }
    
    public NodoAVL(Comparable elem, NodoAVL izq, NodoAVL der){
        this.elem = elem;
        this.altura = 0;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int[] altura = {-1};
        recalcularAlturaAux(this, altura, 0);
        this.altura = altura[0];
    }
    
    private void recalcularAlturaAux(NodoAVL nodo, int[] alturaMax, int alturaActual) {
        if (nodo != null) {
            if (alturaActual > alturaMax[0]) {
                alturaMax[0] = alturaActual;
            }
            recalcularAlturaAux(nodo.getIzquierdo(), alturaMax, alturaActual + 1);
            recalcularAlturaAux(nodo.getDerecho(), alturaMax, alturaActual + 1);
        }
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
    
    
}
