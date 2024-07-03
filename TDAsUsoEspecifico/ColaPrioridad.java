package TDAsUsoEspecifico;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Nodo;

public class ColaPrioridad {
    private Lista listaNodos;

    public ColaPrioridad(){
        this.listaNodos = new Lista();
    }
    
    public boolean insertar(Object elem, int prioridad){
        boolean  exito = false;
        NodoCP nodo = null;
        //primero verifico si en la lista hay un nodo con la misma prioridad:
        if(!this.listaNodos.esVacia()){
            nodo = ubicarNodo(prioridad);
        }
        if(nodo != null){
            //si true, inserto en la cola del NodoCP correspondiente 
            nodo.getItems().poner(elem);
        }else{
            //si false, creo e inserto un nuevo NodoCP en la lista de NodosCP
            
        }
        return exito;
    }

    private NodoCP ubicarNodo(int prioridad){
        boolean encontrado = false;
        int pos = 1, longitud = this.listaNodos.longitud();
        NodoCP nodo;
        while(!encontrado || pos <= longitud){
            nodo = (NodoCP) this.listaNodos.recuperar(pos);
            if(nodo.getPrioridad() == prioridad){
                encontrado = true;
            }
            pos++;
        }
        return nodo;
    }

    public boolean eliminarFrente(){
        boolean  exito = false;
        if(!this.listaNodos.esVacia()){
            NodoCP nodo = (NodoCP) this.listaNodos.recuperar(1);
            nodo.getItems().sacar();
        }
        //elimino el frente de la cola del NodoCP con mayor prioridad
        return exito;
    }

    public Object obtenerFrente(){
        Object frente = null;
        if(!this.listaNodos.esVacia()){
            NodoCP nodo = (NodoCP) this.listaNodos.recuperar(1);
            frente = nodo.getItems().obtenerFrente();
        }
        //retorno el frente de la cola del NodoCP con mayor prioridad
        return frente;
    }
}
