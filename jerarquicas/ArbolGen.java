package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elem, Object elemPadre) {
        boolean[] encontrado = {false};
        NodoGen padre = obtenerNodo(elemPadre, this.raiz, encontrado);
        if (this.raiz == null) {
            this.raiz = new NodoGen(elem, null, null);
            encontrado[0] = true;
        } else {
            if (padre != null) {
                padre.setHijoIzquierdo(new NodoGen(elem, null, padre.getHijoIzquierdo()));
            }
        }
        return encontrado[0];
    }
    private NodoGen obtenerNodo(Object elem, NodoGen nodo, boolean[] encontrado) {
        NodoGen padre = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                padre = nodo;
                encontrado[0] = true;
            } else {
                nodo = nodo.getHijoIzquierdo();
                while (nodo != null && !encontrado[0]) {
                    padre = obtenerNodo(elem, nodo, encontrado);
                    nodo = nodo.getHermanoDerecho();
                }
            }
        }
        return padre;
    }
    
    public boolean pertenece(Object elem){
        return perteneceAux(this.raiz, elem);
    }
    private boolean perteneceAux(NodoGen nodo, Object buscado){
        boolean exito = false;
        if(nodo != null){
            if(nodo.getElem().equals(buscado)){
                exito = true;
            }else{
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(!exito && hijo != null){
                    exito = perteneceAux(hijo, buscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    public Object padre(Object elem){
        Object[] padre = {null};
        if(!this.raiz.getElem().equals(elem)){
            padreAux(this.raiz, null, padre, elem);
        }
        return padre[0];
    }
    private boolean padreAux(NodoGen nodo, NodoGen padreActual, Object[] padre, Object elem){
        boolean exito = false;
        if(nodo != null){
            if(nodo.getElem().equals(elem)){
                padre[0] = padreActual.getElem();   
            }else{
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(!exito && hijo != null){
                    exito = padreAux(hijo, nodo, padre, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    public int altura() {
        int[] altura = {0};
        if (this.raiz == null) {
            altura[0] = -1;
        } else {
            alturaAux(this.raiz, altura, 0);
        }
        return altura[0];
    }

    private void alturaAux(NodoGen nodo, int[] alturaMax, int alturaActual) {
        if (nodo != null) {
            if (alturaActual > alturaMax[0]) {
                alturaMax[0] = alturaActual;
            }
            NodoGen hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                alturaAux(hijo, alturaMax, alturaActual + 1);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public int nivel(Object buscado) {
        int[] nivel = {-1};
        if (this.raiz != null) {
            nivelAux(this.raiz, buscado, nivel, 0);
        }
        return nivel[0];
    }

    private boolean nivelAux(NodoGen nodo, Object buscado, int[] nivel, int nivelActual) {
        boolean exito = false;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                nivel[0] = nivelActual;
                exito = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (!exito && hijo != null) {
                    exito = nivelAux(hijo, buscado, nivel, nivelActual + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }
    
    public Lista ancestros(Object elem){
        Lista ancestros = new Lista();
        if(!this.raiz.getElem().equals(elem)){
            ancestrosAux(this.raiz, null, ancestros, elem);
        }
        return ancestros;
    }
    private boolean ancestrosAux(NodoGen nodo, NodoGen padreActual, Lista ancestros, Object buscado){
        boolean exito = false;
        if(nodo != null){
            if(nodo.getElem().equals(buscado)){
                exito = true;
            }else{
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(!exito && hijo != null){
                    exito = ancestrosAux(hijo, nodo, ancestros, buscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            if(exito && padreActual != null){
                ancestros.insertar(padreActual.getElem(), ancestros.longitud()+1);
            }
        }
        return exito;
    }

    public boolean esVacio(){
        return this.raiz == null;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public Lista listarPreorden(){
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }
    private void listarPreordenAux(NodoGen nodo, Lista lista){
        if(nodo != null){
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            NodoGen hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                listarPreordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden(){
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }
    private void listarInordenAux(NodoGen nodo, Lista lista){
        if(nodo != null){
            if(nodo.getHijoIzquierdo() != null){
                listarInordenAux(nodo.getHijoIzquierdo(), lista);
            }
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            if(nodo.getHijoIzquierdo() != null){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while(hijo != null){
                    listarPreordenAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden(){
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }
    private void listarPosordenAux(NodoGen nodo, Lista lista){
        if(nodo != null){
            NodoGen hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                listarPosordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }
            lista.insertar(nodo.getElem(), lista.longitud()+1);
        }
    }

    public ArbolGen clone(){
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }
    private NodoGen cloneAux(NodoGen nodo){
        NodoGen aux = null;
        if(nodo != null){
            aux = new NodoGen(nodo.getElem(), null, null);
            NodoGen hijo = nodo.getHijoIzquierdo();
            if(hijo != null){
                aux.setHijoIzquierdo(cloneAux(hijo));
                hijo = hijo.getHermanoDerecho();
                NodoGen aux2 = aux.getHijoIzquierdo();
                while(hijo != null && aux2 != null){
                    aux2.setHermanoDerecho(cloneAux(hijo));
                    hijo = hijo.getHermanoDerecho();
                    aux2 = aux2.getHermanoDerecho();
                }
                
            }
        }
        return aux;
    }
    
    public String toString() {
        String cadena = "(Arbol vacio)";
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz);
        }
        return cadena;
    }

    private String toStringAux(NodoGen nodo) {
        String cadena = "";
        NodoGen hijo = null;
        if (nodo != null) {
            cadena += nodo.getElem() + " -> ";
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena += hijo.getElem() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cadena;
    }
}
