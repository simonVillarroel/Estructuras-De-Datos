package Jerarquica;

public class NodoGen {
     //Atributos//
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;


    //Constructor//
    public NodoGen(Object elem, NodoGen iz, NodoGen de) {
        this.elem = elem;
        this.hijoIzquierdo = iz;
        this.hermanoDerecho = de;
    }

    //Observadoras//
    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }
    
    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    //Modificadoras//
    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setHijoIzquierdo(NodoGen iz) {
        this.hijoIzquierdo = iz;
    }
    
    public void setHermanoDerecho(NodoGen de) {
        this.hermanoDerecho = de;
    }
}
