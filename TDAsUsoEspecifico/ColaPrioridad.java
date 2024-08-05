package TDAsUsoEspecifico;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class ColaPrioridad {
    private Lista listaNodos;

    public ColaPrioridad(){
        this.listaNodos = new Lista();
    }
    
    public boolean insertar(Object elem, int prioridad){
        boolean exito = false;
        NodoCP aux = null;
        if(this.listaNodos.esVacia()){
            //la lista esta vacia, inserto al principio
            listaNodos.insertar(new NodoCP(prioridad), 1);
            aux = (NodoCP) this.listaNodos.recuperar(1);
            aux.getItems().poner(elem);
            exito = true;
        }else{
            //si la lista no esta vacia, comienzo a buscar al nodo con misma prioridad
            int pos = 1;
            while(!exito && pos <= this.listaNodos.longitud()){
                aux = (NodoCP) this.listaNodos.recuperar(pos);
                int prioNodo = aux.getPrioridad();
                if(prioridad == prioNodo){
                    //si encuentro un nodo con la misma prioridad, inserto el elemento en Cola del NodoCP aux
                    aux.getItems().poner(elem);
                    exito = true;
                }else{
                    if(prioridad < prioNodo){
                        this.listaNodos.insertar(new NodoCP(prioridad), pos);
                    }else{
                        NodoCP sig = (NodoCP) this.listaNodos.recuperar(pos+1);
                        if(( prioridad > prioNodo ) && (  sig == null || prioridad < sig.getPrioridad() )){
                            //si encuentro un nodo con prioridad menor a la prioridad que busco
                            // y el siguiente es mayor
                            //  creo e inserto un nuevo nodo con la prioridad que busco a la lista
                            this.listaNodos.insertar(new NodoCP(prioridad), pos+1);
                        }
                        pos++; //solo aumenta de posicion cuando la prioridad que busco es mayor a la del nodo actual
                    }
                }
            }
        }
        return exito;
    }

    public boolean eliminarFrente(){
        boolean  exito = false;
        if(!this.listaNodos.esVacia()){
            NodoCP nodo = (NodoCP) this.listaNodos.recuperar(1);    
            nodo.getItems().sacar();
            if(nodo.getItems().esVacia()){
                this.listaNodos.eliminar(1);
            }
            exito = true;
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

    public String toString(){
        String cadena = "";
        int pos = 1, longitudLista = this.listaNodos.longitud();
        while(pos <= longitudLista){
            NodoCP aux = (NodoCP) this.listaNodos.recuperar(pos);
            cadena += "Prioridad " + aux.getPrioridad() + ". Items --> " + aux.getItems().toString() + '\n';
            pos++;
        }
        return cadena;
    }
}
