package TDAsUsoEspecifico;

import lineales.dinamicas.Lista;

public class NodoHashMapeoM {
    private Comparable dominio;
    private Lista rango;
    private NodoHashMapeoM enlace;

    public NodoHashMapeoM(Comparable dominio, Comparable valorRango, NodoHashMapeoM enlace){
        this.dominio = dominio;
        this.rango = new Lista();
        this.rango.insertar(valorRango, 1);
        this.enlace = enlace;
    }

    public NodoHashMapeoM(Comparable dominio, Lista rango, NodoHashMapeoM enlace){
        this.dominio = dominio;
        this.rango = rango;
        this.enlace = enlace;
    }

    public Comparable getDominio() {
        return dominio;
    }

    public void setDominio(Comparable dominio) {
        this.dominio = dominio;
    }

    public Lista getRango() {
        return rango;
    }

    public NodoHashMapeoM getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoHashMapeoM enlace) {
        this.enlace = enlace;
    }

    
}
