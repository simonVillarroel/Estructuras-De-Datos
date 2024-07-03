package TDAsUsoEspecifico;

import lineales.dinamicas.Cola;

public class NodoCP {
    private int prioridad;
    private Cola items;

    public NodoCP(int prioridad){
        this.prioridad = prioridad;
        this.items = new Cola();
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Cola getItems() {
        return items;
    }

    public void setItems(Cola items) {
        this.items = items;
    }

    
}
