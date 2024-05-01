package Simulacro1;

import lineales.dinamicas.Nodo;
public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista(){
        this.longitud = 0;
    }

    //EJERCICIO TIPO 1 (a)
    
    public Lista obtenerMultiplosIterativo(int num){
        Lista lista = new Lista();
        Nodo aux = this.cabecera;
        int cont = 1;
        while(aux != null){
            if(cont == num){
                lista.insertar(aux.getElem(), lista.longitud+1);
                cont = 1;
            }else{
                aux = aux.getEnlace();
                cont++;
            }
        }
        return lista;
    }

    public Lista obtenerMultiplosRecursivo(int num){
        Lista lista = new Lista();
        Nodo aux = this.cabecera;
        int[] cont = {1};
        obtenerMultiplosAux(aux, lista, num, cont);
        return lista;
    }
    private void obtenerMultiplosAux(Nodo nodo, Lista lista, int num, int[] cont){
        if(nodo != null){
            if(cont[0] == num){
                lista.insertar(nodo.getElem(), lista.longitud+1);
                cont[0] = 1;
            }else{
                cont[0]++;
                obtenerMultiplosAux(nodo.getEnlace(), lista, num, cont);
            }
        }
    }

    // EJERCICIO TIPO 1 (b)
    public void eliminarApariciones(Object elem){
        Nodo aux = this.cabecera;
        while(this.cabecera.getElem().equals(elem)){
                this.cabecera = this.cabecera.getEnlace();
        }
        aux = this.cabecera;
        while(aux.getEnlace() != null){
            if(aux.getEnlace().getElem().equals(elem)){
                aux = aux.getEnlace().getEnlace();
            }else{
                aux = aux.getEnlace();
            }
        }
    }
    


    public boolean insertar(Object nuevoElem, int pos){
        boolean exito = true;
        if(pos < 1 || pos > this.longitud() + 1){
            exito = false;
        }else{
            if(pos == 1){
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            }else{ //Avanza hasta el elemento pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos -1){
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevoNodo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
                this.longitud++;
            }
        }
        return exito;
    }

    public boolean eliminar(int pos){
        boolean exito = false;
        if (pos >= 1 && pos <= this.longitud()) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }
            exito = true;
        } 
        return exito;
    }

    public Object recuperar(int pos){
        Object objeto = null;
        if(pos >= 1 && pos <= this.longitud()){
            if(pos == 1){
                objeto = this.cabecera.getElem();
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos){
                    aux = aux.getEnlace();
                    i++;
                }
                objeto = aux.getElem();
            }
        }
        return objeto;
    }

    public int localizar(Object buscado) {
        Nodo aux = this.cabecera;
        int pos = -1, i = 1;
        boolean encontrado = false;
        while(aux != null && !encontrado){
            if(aux.getElem().equals(buscado)){
                pos = i;
                encontrado = true;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }

    public int longitud(){
        //sin atributo longitud
        /*int contador = 0;
        if(this.cabecera != null){
            Nodo aux = this.cabecera;
            while(aux != null){
                contador++;
                aux = aux.getEnlace();
            } 
        }*/
        return this.longitud;
    }

    public boolean pertenece(Object elemento){
        boolean encontrado = false;
        Nodo aux = this.cabecera;
        while(aux != null && !encontrado){
            if(aux.getElem() == elemento){
                encontrado = true;
            }
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean esVacia(){
        return this.cabecera == null;
    }
    
    public void vaciar(){
        this.cabecera = null;
    }

    public Lista invertir(){
        int pos = this.longitud();
        Lista invertida = new Lista();
        while (pos > 0){
            invertida.insertar(this.recuperar(pos), invertida.longitud()+1);
            pos--;
        }
        return invertida;
    }
    
    /*
    public Lista clone(){ //implementacion iterativa
        Lista clon = new Lista();
        
        if(this.cabecera != null){
            clon.cabecera = new Nodo(this.cabecera.getElem(), null);
            Nodo aux = this.cabecera;
            Nodo auxClon = clon.cabecera;
            aux = aux.getEnlace();
            while(aux != null){
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
        }
        return clon;
    }
    */

    public Lista clone(){ //implementacion recursiva
        Lista clon = new Lista();
        Nodo aux = this.cabecera;
        if (aux != null) {
            clon.cabecera = clonarNodo(aux); 
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

    public String toString(){
        String text = "[ ";
        if (this.cabecera == null) {
            text += "La lista esta vacia ";
        } else {
            Nodo aux = this.cabecera;
            while (aux != null) {
                text += aux.getElem().toString() + " ";
                aux = aux.getEnlace();
                
            }
        }
        return text + "]";
    }
}
