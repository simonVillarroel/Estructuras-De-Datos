package conjuntistas;

import lineales.dinamicas.Nodo;

//implementacion hash abierto

public class TablaHash {
    private static final int TAMANIO = 10;
    private Nodo[] hash;
    private int cant;

    public TablaHash(){
        this.hash = new Nodo[TAMANIO];
        this.cant = 0;
    }

    public boolean pertenece(Object elem){
        boolean encontrado = false;
        int pos = elem.hashCode() % this.TAMANIO;
        Nodo aux = this.hash[pos];
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(elem);
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean insertar(Object nuevoElem){
        //primero verifica si el elemento ya esta cargado
        //si no lo encuentra, lo pone adelante del resto
        int pos = nuevoElem.hashCode() % this.TAMANIO;
        Nodo aux = this.hash[pos];
        boolean encontrado = false;
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(nuevoElem);
            aux = aux.getEnlace();
        }
        if (!encontrado) {
            this.hash[pos] = new Nodo(nuevoElem, this.hash[pos]);
            this.cant++;
        }
        return encontrado;
    }

    public boolean eliminar(Object elem){
        int pos = elem.hashCode() % this.TAMANIO;
        Nodo aux = this.hash[pos], anterior = null;
        boolean encontrado = false;
        if(aux.getElem().equals(elem)){
            //en caso de que el nodo buscado sea el primero de la lista
            aux = aux.getEnlace();
            encontrado = true;
        }else{
            while(!encontrado && aux != null){
                encontrado = aux.getElem().equals(elem);
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

    public boolean esVacia(){
        return this.cant == 0;
    }

    public void vaciar(){
        for(int i = 0; i < this.TAMANIO; i++){
            this.hash[i] = null;
        }
    }

    public String toString(){
        String cadena = "";
        if(this.esVacia()){
            cadena = "La tabla hash esta vacia";
        }else{
            Nodo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                cadena += "Pos " + i + ": [ ";
                while(aux != null){
                    cadena +=  aux.getElem();
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

    public TablaHash clone(){
        TablaHash clon = new TablaHash();
        if(!this.esVacia()){
            clon.cant = this.cant;
            Nodo aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                if(aux != null){
                    clon.hash[i] = clonarNodo(aux);
                }
            }
        }
        return clon;
    }

    private Nodo clonarNodo(Nodo nodoActual){
        Nodo nodoNuevo = null;
        if(nodoActual != null){
            nodoNuevo = new Nodo(nodoActual.getElem(), clonarNodo(nodoActual.getEnlace()));
        }
        return nodoNuevo;
    }
}
