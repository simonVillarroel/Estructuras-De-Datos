package Conjuntista.TDA_Hash.Abierto;

import Conjuntista.TDA_Hash.FuncionesHash;
import Dinamica.Nodo;
import Dinamica.TDA_Lista.Lista;


public class TablaHash {
    private final static int TAMANIO = 10;
    private Nodo[] tabla;
    private int cant;

    public TablaHash(){
        this.tabla = new Nodo[TAMANIO];
        this.cant = 0;
    }
   
    public boolean insertar(Object nuevoElem){
        //Primero verifica si el elemento ya esta cargado
        //si no lo encuentra, lo pone adelante del resto
        int pos = FuncionesHash.hashEnteroUltimoDigito(nuevoElem);
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(nuevoElem);
            aux = aux.getEnlace();
        }
        if(!encontrado){
            this.tabla[pos] = new Nodo(nuevoElem, this.tabla[pos]);
            this.cant++;
        }
        return !encontrado;
    }

    public boolean eliminar(Object nuevoElem){
        int pos = FuncionesHash.hashEnteroUltimoDigito(nuevoElem);
        Nodo aux = this.tabla[pos];
        Nodo padre = null;
        boolean encontrado = false;
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(nuevoElem);
            if(!encontrado){
                padre = aux;
                aux = aux.getEnlace();
            }
        }
        if(encontrado){
            padre.setEnlace(aux.getEnlace());
            this.cant--;
        }
        return encontrado;
    }

    public boolean pertenece(Object elemento){
        int pos = FuncionesHash.hashEnteroUltimoDigito(elemento);
        Nodo aux = this.tabla[pos];
        boolean encontrado = false;
        while(!encontrado && aux != null){
            encontrado = aux.getElem().equals(elemento);
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean esVacia(){
        return this.cant == 0;
    }
    public void vaciar(){
        for(int i = 0; i < TAMANIO; i++){
            this.tabla[i] = null;
        }
    }

    public Lista listar(){
        Lista lista = new Lista();
        Nodo aux;
        for (int i = 0; i < TAMANIO; i++) {
            aux = this.tabla[i];
            while(aux != null){
                lista.insertar(aux.getElem(), lista.longitud() + 1);
                aux = aux.getEnlace();
            }
        }
        return lista;
    }

    public TablaHash clone(){
        TablaHash clon = new TablaHash();
        Nodo aux;
        for (int i = 0; i < TAMANIO; i++) {
            aux = this.tabla[i];
            clon.tabla[i] = clonarBucket(aux);
        }
        clon.cant = this.cant;
        return clon;
    }
    private Nodo clonarBucket(Nodo nodo){
        Nodo resultado = null;
        if(nodo != null){
            resultado = new Nodo(nodo.getElem(), clonarBucket(nodo.getEnlace()));
        }
        return resultado;
    }

    public String toString(){
        String text = "";
        Nodo aux;
        for (int i = 0; i < TAMANIO; i++) {
            text += "Contenido bucket "+ i + ": ";
            aux = this.tabla[i];
            while(aux != null){
                text += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
            }
            text += '\n';
        }
        return text;
    }
}
