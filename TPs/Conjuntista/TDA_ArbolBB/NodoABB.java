package Conjuntista.TDA_ArbolBB;

import Jerarquica.NodoArbol;

public class NodoABB {
    private Object elem;
    private NodoABB hijoIzquierdo;
    private NodoABB hijoDerecho;

    public NodoABB(Object elem, NodoABB hijoI, NodoABB hijoD){
        this.elem = elem;
        this.hijoIzquierdo = hijoI;
        this.hijoDerecho = hijoD;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoABB getIzquierdo() {
        return hijoIzquierdo;
    }

    public void setIzquierdo(NodoABB hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoABB getDerecho() {
        return hijoDerecho;
    }

    public void setDerecho(NodoABB hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }    
}
