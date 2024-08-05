package TDAsUsoEspecifico;

import lineales.dinamicas.Lista;

public class MapeoAMuchos{
    
    private static final int TAMANIO = 10;
    private NodoHashMapeoM[] hash;
    private int cant;

    public MapeoAMuchos(){
        this.hash = new NodoHashMapeoM[TAMANIO];
        this.cant = 0;
    }

    public boolean asociar(Comparable valorDominio, Comparable valorRango){
        int pos = Math.abs(valorDominio.hashCode()) % this.TAMANIO;
        NodoHashMapeoM aux = this.hash[pos];
        boolean encontrado = false, exito = false;

        while(!encontrado && aux != null){
            //primero busca a valorDominio en el dominio
            encontrado = aux.getDominio().equals(valorDominio);
            if(!encontrado) aux = aux.getEnlace();
        }
        if(encontrado && aux != null){ 
            //si encuentra el valor en el dominio
            if(!aux.getRango().pertenece(valorRango)){
                //si el valorDominio no esta asociado al valorRango
                //this.hash[pos] = new NodoHashMapeoM(valorDominio, valorRango, this.hash[pos]);
                aux.getRango().insertar(valorRango, 1);
                exito = true;
                this.cant++;
            }
        }else{
            //si no encuentra el valor en el dominio
            this.hash[pos] = new NodoHashMapeoM(valorDominio, valorRango, this.hash[pos]);
            this.cant++;
            exito = true;
        }
        return exito;
    }

    public boolean desasociar(Comparable valorDominio, Comparable valorRango){
        int pos = valorDominio.hashCode() % this.TAMANIO;
        NodoHashMapeoM aux = this.hash[pos], anterior = null;
        boolean encontrado = false, exito = false;
        Lista rango = null;
        if(aux.getDominio().equals(valorDominio)){
            //en caso de que el nodo buscado sea el primero de la lista
            rango = aux.getRango();
            rango.eliminar(rango.localizar(valorRango));
            if(rango.esVacia()){
                //si al eliminar la relacion de valorDominio con valorRango, el conjunto rango queda vacio
                aux = aux.getEnlace();
            }
            encontrado = true;
        }else{
            while(!encontrado && aux != null){
                encontrado = aux.getDominio().equals(valorDominio);
                anterior = aux;
                aux = aux.getEnlace();
            }
            if (encontrado) {
                rango = aux.getRango();
                rango.eliminar(rango.localizar(valorRango));
                if(aux.getRango().esVacia()){
                    //si al eliminar la relacion de valorDominio con valorRango, el conjunto rango queda vacio
                    anterior.setEnlace(aux.getEnlace());
                }
                this.cant--;
            }
        }
        return encontrado;
    }

    public Lista obtenerValores(Comparable valorDominio){
        int pos = Math.abs(valorDominio.hashCode()) % this.TAMANIO;
        NodoHashMapeoM aux = this.hash[pos];
        boolean encontrado = false;
        Lista valorRango = null;
        while(!encontrado && aux != null){
            encontrado = aux.getDominio().equals(valorDominio);
            if(!encontrado) aux = aux.getEnlace();
        }
        if (encontrado) {
            valorRango = aux.getRango();
        }
        return valorRango;
    }

    public Lista obtenerConjuntoDominio(){
        Lista lista = new Lista();
        if(!this.esVacia()){
            NodoHashMapeoM aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                while(aux != null){
                    lista.insertar(aux.getDominio(), i+1);
                    aux = aux.getEnlace();
                }
            }
        }
        return lista;
    }

    public Lista obtenerConjuntoRango(){
        Lista lista = new Lista();
        Lista rango;
        int n, posLista = 1;
        if(!this.esVacia()){
            NodoHashMapeoM aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                while(aux != null){
                    rango = aux.getRango();
                    n = 1;
                    while(n <= rango.longitud()){
                        lista.insertar(rango.recuperar(n), posLista);
                        posLista++;
                    }
                    
                    aux = aux.getEnlace();
                }
            }
        }
        return lista;
    }

    public boolean esVacia(){
        return this.cant == 0;
    }

    public String toString(){
        String cadena = "";
        if(this.esVacia()){
            cadena = "La tabla hash esta vacia";
        }else{
            NodoHashMapeoM aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                cadena += "\nPos " + i + ": [ ";
                while(aux != null){
                    cadena += "(" + aux.getDominio() + ", " + aux.getRango().toString() + ")";
                    aux = aux.getEnlace();
                    if (aux != null) {
                        cadena += "\n\t, ";
                    }
                }
                cadena += " ]";
            }
        }
        return cadena;
    }

    public MapeoAMuchos clone(){
        MapeoAMuchos clon = new MapeoAMuchos();
        if(!this.esVacia()){
            clon.cant = this.cant;
            NodoHashMapeoM aux;
            for(int i = 0; i < this.TAMANIO; i++){
                aux = this.hash[i];
                if(aux != null){
                    clon.hash[i] = clonarNodo(aux);
                }
            }
        }
        return clon;
    }

    private NodoHashMapeoM clonarNodo(NodoHashMapeoM nodoActual){
        NodoHashMapeoM nodoNuevo = null;
        if(nodoActual != null){
            nodoNuevo = new NodoHashMapeoM(nodoActual.getDominio(), nodoActual.getRango().clone(), clonarNodo(nodoActual.getEnlace()));
        }
        return nodoNuevo;
    }
}
