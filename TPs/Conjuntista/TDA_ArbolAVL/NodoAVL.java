package Conjuntista.TDA_ArbolAVL;

public class NodoAVL {
    private int elem;
    private int altura;
    private NodoAVL hijoIzquierdo,hijoDerecho;
    
    
    public NodoAVL (int d){
        this.elem=d;
        this.altura = 0;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
    
    
    //Observadoras
    public int getElem() {  
        return this.elem;
    }
    
    public int getAltura() {
        return this.altura;
    }
    
    public NodoAVL getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }
    
    public NodoAVL getHijoDerecho() {
        return this.hijoDerecho;
    }
    
    //Modificadoras
    public void setElem(int elem) {
        this.elem = elem;
    }
    
    public void recalcularAltura(int alt) {
        this.altura = alt;
    }
    
    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }
    
    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
}