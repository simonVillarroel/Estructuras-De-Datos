package TDAsUsoEspecifico;

import lineales.dinamicas.Nodo;

public class MapeoAUno {
    private static final int TAMANIO = 10;
    private NodoHashMapeo[] hash;
    private int cant;

    public MapeoAUno(){
        this.hash = new NodoHashMapeo[TAMANIO];
        this.cant = 0;
    }

    public boolean asociar(Object valorDominio, Object valorRango){
        int pos = valorDominio.hashCode() % this.TAMANIO;
        NodoHashMapeo aux = this.hash[pos];
        boolean encontrado = false;
        while(!encontrado && aux != null){
            encontrado = aux.getDominio().equals(valorDominio);
            aux = aux.getEnlace();
        }
        if (!encontrado) {
            this.hash[pos] = new NodoHashMapeo(valorDominio, valorRango, this.hash[pos]);
            this.cant++;
        }
        return encontrado;
    }

    public boolean desasociar(Object valorDominio, Object valorRango){
        int pos = valorDominio.hashCode() % this.TAMANIO;
        NodoHashMapeo aux = this.hash[pos], anterior = null;
        boolean encontrado = false;
        if(aux.getDominio().equals(valorDominio)){
            //en caso de que el nodo buscado sea el primero de la lista
            aux = aux.getEnlace();
            encontrado = true;
        }else{
            while(!encontrado && aux != null){
                encontrado = aux.getDominio().equals(valorDominio);
                anterior = aux;
                aux = aux.getEnlace();
            }
            if (encontrado) {
                anterior.setEnlace(aux.getEnlace());
                this.cant--;
            }
        }
        return encontrado;
    }

    public Object obtenerValor(Object valorDominio){
        int pos = valorDominio.hashCode() % this.TAMANIO;
        NodoHashMapeo aux = this.hash[pos];
        boolean encontrado = false;
        Object valorRango = null;
        while(!encontrado && aux != null){
            encontrado = aux.getDominio().equals(valorDominio);
            aux = aux.getEnlace();
        }
        if (encontrado) {
            valorRango = aux.getRango();
        }
        return valorRango;
    }

    public Conjunto obtenerConjuntoDominio(){
        Conjunto conjunto = new Conjunto();
        if(!this.esVacia()){
            NodoHashMapeo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                while(aux != null){
                    conjunto.agregar((Comparable) aux.getDominio());
                    aux = aux.getEnlace();
                }
            }
        }
        return conjunto;
    }

    public Conjunto obtenerConjuntoRango(){
        Conjunto conjunto = new Conjunto();
        if(!this.esVacia()){
            NodoHashMapeo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                while(aux != null){
                    conjunto.agregar((Comparable) aux.getRango());
                    aux = aux.getEnlace();
                }
            }
        }
        return conjunto;
    }

    public boolean esVacia(){
        return this.cant == 0;
    }

    public String toString(){
        String cadena = "";
        if(this.esVacia()){
            cadena = "La tabla hash esta vacia";
        }else{
            NodoHashMapeo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                cadena += "Pos " + i + ": [ ";
                while(aux != null){
                    cadena += "(" + aux.getDominio() + ", " + aux.getRango() + ")";
                    aux = aux.getEnlace();
                    if (aux != null) {
                        cadena += ", ";
                    }
                }
                cadena += " ]";
            }
        }
        return cadena;
    }

    public MapeoAUno clone(){
        MapeoAUno clon = new MapeoAUno();
        if(!this.esVacia()){
            clon.cant = this.cant;
            NodoHashMapeo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                if(aux != null){
                    clon.hash[i] = clonarNodo(aux);
                }
            }
        }
        return clon;
    }

    private NodoHashMapeo clonarNodo(NodoHashMapeo nodoActual){
        NodoHashMapeo nodoNuevo = null;
        if(nodoActual != null){
            nodoNuevo = new NodoHashMapeo(nodoActual.getDominio(), nodoActual.getRango(), clonarNodo(nodoActual.getEnlace()));
        }
        return nodoNuevo;
    }
}
