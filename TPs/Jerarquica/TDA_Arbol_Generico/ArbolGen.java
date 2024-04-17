package Jerarquica.TDA_Arbol_Generico;

import Jerarquica.NodoGen;
import Dinamica.TDA_Lista.Lista;

class ArbolGen{
    private NodoGen raiz;

    public ArbolGen(){
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        if (this.raiz == null) { //si el arbol no tiene raiz, se inserta el elemento como raiz
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            //si el arbol posee raiz busco al nodo padre del nodo que quiero insertar
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (nodoPadre.getHijoIzquierdo() == null) {//si no tiene hijo izquierdo
                    nodoPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    if (nodoPadre.getHijoIzquierdo().getHermanoDerecho() == null) {
                        //si el hijo izquierdo aun no tiene hermanos derechos
                        nodoPadre.getHijoIzquierdo().setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                    } else {
                        //si tiene hermanos, busco el ultimo y le enlazo el nuevo nodo
                        NodoGen aux = nodoPadre.getHijoIzquierdo().getHermanoDerecho();
                        while (aux.getHermanoDerecho() != null) {
                            aux = aux.getHermanoDerecho();
                        }
                        aux.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    private NodoGen obtenerNodo(NodoGen nodo, Object buscado){
        NodoGen nodoEncontrado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                nodoEncontrado = nodo;
            } else {
                nodoEncontrado = obtenerNodo(nodo.getHijoIzquierdo(), buscado);
                if (nodoEncontrado == null) {
                    nodoEncontrado = obtenerNodo(nodo.getHermanoDerecho(), buscado);
                }
            }
        }
        return nodoEncontrado;
    }

    public boolean pertenece(Object elem) {
        boolean exito;
        exito = perteneceAux(this.raiz, elem);
        return exito;
    }
    private boolean perteneceAux(NodoGen nodo, Object buscado) {
        boolean resultado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                resultado = true;
            } else {
                resultado = perteneceAux(nodo.getHijoIzquierdo(), buscado);
                if (resultado == false) {
                    resultado = perteneceAux(nodo.getHermanoDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public Lista ancestros(Object elem) {
        Lista lista = new Lista();
        lista = ancestrosAux(this.raiz, null, elem, lista);
        return lista;
    }
    private Lista ancestrosAux(NodoGen nodo, NodoGen padre, Object elem, Lista lista) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else {
                lista = ancestrosAux(nodo.getHijoIzquierdo(), nodo, elem, lista);
                if (encontrado == false) {
                    lista = ancestrosAux(nodo.getHermanoDerecho(), padre, elem, lista);
                }
            }
        }
        if (encontrado && padre != null) {
            lista.insertar(padre.getElem(), lista.longitud() + 1);
            lista = ancestrosAux(this.raiz, null, padre.getElem(), lista);
        }
        return lista;
    }

    public int altura() {
        int resultado;
        resultado = alturaAux(this.raiz);
        return resultado;
    }
    private int alturaAux(NodoGen nodo) {
        int resultado;
        if(nodo == null) {
            resultado = -1;
        } else {
            if(nodo.getHijoIzquierdo() != null) {
                resultado = 1 + alturaAux(nodo.getHijoIzquierdo());
            } else {
                if (nodo.getHermanoDerecho() != null) {
                    resultado = alturaAux(nodo.getHermanoDerecho());
                } else {
                    resultado = 0;
                }
            }
        }
        return resultado;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        NodoGen aux = this.raiz;
        if (aux != null) {
            if (aux.getElem().equals(elem)) { //si la raiz posee el elemento buscado
                nivel = 0;
            } else {
                nivel = nivelAux(aux, elem);
            }
        }
        return nivel;
    }
    private int nivelAux(NodoGen nodo, Object buscado) {
        int nivel = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                nivel++;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    nivel = nivelAux(nodo.getHijoIzquierdo(), buscado);
                }
                if (nivel == -1) {
                    nivel = nivelAux(nodo.getHermanoDerecho(), buscado);
                } else {
                    nivel++;
                }
            }

        }
        return nivel;
    }

    public Object padre(Object buscado) {
        Object padre = null;
        NodoGen aux = this.raiz;
        if (aux != null) {
            if (this.raiz.getElem().equals(buscado)) {//si se busca el padre de la raiz
                padre = null;
            } else {
                padre = padreAux(aux, buscado);
            }
        }
        return padre;
    }
    private Object padreAux(NodoGen nodo, Object buscado) {
        Object resultado = null;
        if (nodo != null) {//////
            if (nodo.getHijoIzquierdo() != null) {
                if (nodo.getHijoIzquierdo().getElem().equals(buscado)) {
                    resultado = nodo.getElem();
                } else {
                    resultado = padreAux(nodo.getHijoIzquierdo(), buscado);
                }
            }
            if (resultado == null) {
                if (nodo.getHermanoDerecho() != null) {
                    if (nodo.getHermanoDerecho().getElem().equals(buscado)) {
                        //si el  elemento buscado es hermano derecho de nodo, se llama a padreAux()
                        //y ahora buscado es el elemento de nodo
                        resultado = padreAux(this.raiz, nodo.getElem());
                    } else {
                        resultado = padreAux(nodo.getHermanoDerecho(), buscado);
                    }
                }
            }
        }
        return resultado;
    }

    public boolean esVacio(){
        return this.raiz == null;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public ArbolGen clone() {
        ArbolGen resultado = new ArbolGen();
        NodoGen nuevo = new NodoGen(this.raiz.getElem(), null, null);
        if (this.raiz != null) {
            resultado.raiz = cloneAux(this.raiz, nuevo);
        }
        return resultado;
    }
    private NodoGen cloneAux(NodoGen nodoAux, NodoGen nuevo) {
        if (nodoAux.getHijoIzquierdo() != null) {
            nuevo.setHijoIzquierdo(new NodoGen(nodoAux.getHijoIzquierdo().getElem(), null, null));
            cloneAux(nodoAux.getHijoIzquierdo(), nuevo.getHijoIzquierdo());

        }
        if (nodoAux.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(new NodoGen(nodoAux.getHermanoDerecho().getElem(), null, null));
            cloneAux(nodoAux.getHermanoDerecho(), nuevo.getHermanoDerecho());
        }
        return nuevo;
    }

    public String toString(){
        return toStringAux(this.raiz);
    }
    private String toStringAux(NodoGen nodo){
        String text = "";
        if(nodo != null){
            //visita nodo
            text += nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
             text += hijo.getElem().toString() + " -> ";
                hijo = hijo.getHermanoDerecho();
            }
            //comienza recorrido de los hijos de nodo llamando recursivamente
            //para que cada hijo agregue su contenido a la lista
            hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                text += '\n' + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return text;
    }

    public Lista frontera() {
        Lista lista = new Lista();
        lista = fronteraAux(this.raiz, lista);
        return lista;
    }
    private Lista fronteraAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if ((nodo.getHijoIzquierdo() == null)) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if (nodo.getHijoIzquierdo() != null) {
                lista = fronteraAux(nodo.getHijoIzquierdo(), lista);
            }
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    lista = fronteraAux(hijo, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return lista;
    }

    public Lista listarPreorden(){
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarPreordenAux(NodoGen nodo, Lista salida){
        if(nodo != null){
            //visita el nodo actual
            salida.insertar(nodo.getElem(), salida.longitud()+1);
            //llamado recursivo con el primer hijo del nodo
            if(nodo.getHijoIzquierdo() != null){
                listarPreordenAux(nodo.getHijoIzquierdo(), salida);
            }
            //llamados recursivos con los otros hijos del nodo
            if(nodo.getHijoIzquierdo() != null){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while(hijo != null){
                    listarPreordenAux(hijo, salida);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return salida;
    }

    public Lista listarInorden(){
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarInordenAux(NodoGen nodo, Lista salida){
        if(nodo != null){
            //llamado recursivo con el primer hijo del nodo
            if(nodo.getHijoIzquierdo() != null){
                listarInordenAux(nodo.getHijoIzquierdo(), salida);
            }
            //visita el nodo actual
            salida.insertar(nodo.getElem(), salida.longitud()+1);
            //llamados recursivos con lo otros hijos de nodo
            if(nodo.getHijoIzquierdo() != null){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while(hijo != null){
                    listarInordenAux(hijo, salida);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return salida;
    }

    public Lista listarPosorden(){
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarPosordenAux(NodoGen nodo, Lista salida){
        if(nodo != null){
            //llamado recursivo con el primer hijo del nodo
            if(nodo.getHijoIzquierdo() != null){
                listarPosordenAux(nodo.getHijoIzquierdo(), salida);
            }
            //llamados recursivos con los otros hijos del nodo
            if(nodo.getHijoIzquierdo() != null){
                NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                while(hijo != null){
                    listarPosordenAux(hijo, salida);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            //visita el nodo actual
            salida.insertar(nodo.getElem(), salida.longitud()+1);
        }
        return salida;
    }
}