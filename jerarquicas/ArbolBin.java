package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object nuevoElem, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            //si el arbol esta vacio, pone elemNuevo en la raiz
            this.raiz = new NodoArbol(nuevoElem, null, null);
        } else {
            //si arbol no esta vacio, busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            //si padre existe y lugar no esta ocupado lo pone, sino da error
            if (nodoPadre != null) {
                if (lugar == 'I' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(nuevoElem, null, null));
                } else if (lugar == 'D' && nodoPadre.getDerecho() == null) {
                    nodoPadre.setDerecho(new NodoArbol(nuevoElem, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol nodo, Object buscado) {
        //metodo PRIVADO que busca un elemento y devuelve el nodo que
        //lo contiene. Si no se encuentra buscado devuelve null
        NodoArbol resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                //si el buscado es n, lo devuelve
                resultado = nodo;
            } else {
                //no es el buscado: busca primero en el HI
                resultado = obtenerNodo(nodo.getIzquierdo(), buscado);
                //si no lo encontro en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        int[] altura = new int[1];
        if (this.raiz == null) {
            altura[0] = -1;
        } else {
            alturaAux(this.raiz, altura, 0);
        }
        return altura[0];
    }

    private void alturaAux(NodoArbol nodo, int[] alturaMax, int alturaActual) {
        if (nodo != null) {
            if (alturaActual > alturaMax[0]) {
                alturaMax[0] = alturaActual;
            }
            alturaAux(nodo.getIzquierdo(), alturaMax, alturaActual + 1);
            alturaAux(nodo.getDerecho(), alturaMax, alturaActual + 1);
        }
    }
    
    public int nivel(Object buscado){
        int[] nivel = {-1};
        boolean[] encontrado = {false};
        if(this.raiz != null){
            nivelAux(this.raiz, buscado, nivel, 0, encontrado);
        }
        return nivel[0];
    }
    private void nivelAux(NodoArbol nodo, Object buscado, int[] nivel, int nivelActual, boolean[] encontrado){
        if(nodo != null && !encontrado[0]){
            if(nodo.getElem().equals(buscado)){
                nivel[0] = nivelActual;
                encontrado[0] = true;
            }else{
                nivelAux(nodo.getIzquierdo(), buscado, nivel, nivelActual+1, encontrado);
                if(!encontrado[0]){
                    nivelAux(nodo.getDerecho(), buscado, nivel, nivelActual+1, encontrado);
                }
            }
        }
    }

    public Object padre(Object hijo) {
        Object padreObtenido = null;
        padreObtenido = obtenerPadre(this.raiz, hijo, null, null);
        return padreObtenido;
    }

    private Object obtenerPadre(NodoArbol nodo, Object hijoBuscado, Object padreActual, Object padreEncontrado) {
        if (nodo != null && padreEncontrado == null) {
            if (nodo.getElem().equals(hijoBuscado)) {
                padreEncontrado = padreActual;
            } else {
                padreEncontrado = obtenerPadre(nodo.getIzquierdo(), hijoBuscado, nodo.getElem(), padreEncontrado);
                padreEncontrado = obtenerPadre(nodo.getDerecho(), hijoBuscado, nodo.getElem(), padreEncontrado);
            }
        }
        return padreEncontrado;
    }

    public Lista listarPreorden() {
        //retorna una lista con los elementos del arbol en PREORDEN
        Lista lista = new Lista();
        listarPreordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lista) {
        //metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            //visita el elemento en el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            //recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lista);
            listarPreordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarInorden() {
        //retorna una lista con los elementos del arbol en INORDEN
        Lista lista = new Lista();
        listarInordenAux(this.raiz, lista);
        return lista;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lista) {
        //metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lista);
            //visita el elemento en el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPosorden() {
        //retorna una lista con los elementos del arbol en POSORDEN
        Lista lista = new Lista();
        listarPosordenAux(this.raiz, lista);
        return lista;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lista) {
        //metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lista);
            listarPosordenAux(nodo.getDerecho(), lista);
            //visita el elemento en el nodo
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarNiveles() {
        Lista lista = new Lista();
        Cola cola = new Cola();
        cola.poner(this.raiz);
        while (!cola.esVacia()) {
            NodoArbol nodoActual = (NodoArbol) cola.obtenerFrente();
            cola.sacar();
            lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            if (nodoActual.getIzquierdo() != null) {
                cola.poner(nodoActual.getIzquierdo());
            }
            if (nodoActual.getDerecho() != null) {
                cola.poner(nodoActual.getDerecho());
            }
        }
        return lista;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        String cadena = "";
        if (this.raiz == null) {
            cadena = "Arbol vacio";
        } else {
            cadena = toStringAux(this.raiz);
        }
        return cadena;
    }

    private String toStringAux(NodoArbol nodo) {
        String cadena = "";
        if (nodo != null) {
            NodoArbol hijoIzqNodo = nodo.getIzquierdo();
            NodoArbol hijoDerNodo = nodo.getDerecho();
            cadena += nodo.getElem().toString();
            if (hijoIzqNodo != null) {
                cadena += " --> HI: " + hijoIzqNodo.getElem();
            } else {
                cadena += " --> HI: null";
            }
            if (hijoDerNodo != null) {
                cadena += " --> HD: " + hijoDerNodo.getElem();
            } else {
                cadena += " --> HD: null";
            }
            cadena += "\n";
            cadena += toStringAux(nodo.getIzquierdo());
            cadena += toStringAux(nodo.getDerecho());
        }
        return cadena;
    }
}
