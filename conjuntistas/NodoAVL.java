package conjuntistas;

public class NodoAVL {
    private Comparable elem;
    private NodoAVL izquierdo; 
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable elem){
        this.elem = elem;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 0;
    }

    public NodoAVL(Comparable elem, NodoAVL izq, NodoAVL der){
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL hijoIzquierdo) {
        this.izquierdo = hijoIzquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL hijoDerecho) {
        this.derecho = hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int[] altura = {0};
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
    
}
