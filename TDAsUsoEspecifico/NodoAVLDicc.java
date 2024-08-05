package TDAsUsoEspecifico;

public class NodoAVLDicc {
    private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc hijoIzquierdo;
    private NodoAVLDicc hijoDerecho;
    
    public NodoAVLDicc(Comparable clave, Object dato, NodoAVLDicc hijoIzquierdo, NodoAVLDicc hijoDerecho) {
        this.clave = clave;
        this.dato = dato;
        this.altura = 0;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public NodoAVLDicc(Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        this.altura = 0;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave){
        this.clave = clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }

    public void recalcularAltura() {
        int[] altura = {0};
        recalcularAlturaAux(this, altura, 0);
        this.altura = altura[0];
    }
    
    private void recalcularAlturaAux(NodoAVLDicc nodo, int[] alturaMax, int alturaActual) {
        if (nodo != null) {
            if (alturaActual > alturaMax[0]) {
                alturaMax[0] = alturaActual;
            }
            recalcularAlturaAux(nodo.getHijoIzquierdo(), alturaMax, alturaActual + 1);
            recalcularAlturaAux(nodo.getHijoDerecho(), alturaMax, alturaActual + 1);
        }
    }

    public NodoAVLDicc getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoAVLDicc hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoAVLDicc getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoAVLDicc hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }    
}
