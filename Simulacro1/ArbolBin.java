package Simulacro1;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
import jerarquicas.NodoArbol;

public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    //EJERCICIO TIPO 3 (e)
    public boolean verificarPatron(Lista patron){
        boolean[] exito = {false};
        NodoArbol aux = this.raiz;
        verificarPatronAux(aux, patron, 1, exito);
        return exito[0];
    }

    //EJERCICIO TIPO 3 (f)
    public Lista frontera() {
        Lista frontera = new Lista();
        NodoArbol nodoAux = this.raiz;
        if (nodoAux != null) {
            fronteraAux(nodoAux, frontera);
        }
        return frontera;
    }

    private void fronteraAux(NodoArbol nodo, Lista frontera) {
        //Recorrre el arbol y agrega a la lista frontera solo los nodos que tienen 0 hijos
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                frontera.insertar(nodo.getElem(), frontera.longitud() + 1);
            }
            fronteraAux(nodo.getIzquierdo(), frontera);
            fronteraAux(nodo.getDerecho(), frontera);
        }
    }

    //EJERCICIO TIPO 3 (g)
    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        NodoArbol nodoAux = this.raiz;
        if (nodoAux != null) {
            clon.raiz = clonarInvertidoAux(nodoAux);
        }
        return clon;
    }

    private NodoArbol clonarInvertidoAux(NodoArbol nodo) {
        NodoArbol nodoClon = null;
        if (nodo != null) {
            nodoClon = new NodoArbol(nodo.getElem(), null, null);
            nodoClon.setIzquierdo(clonarInvertidoAux(nodo.getDerecho()));
            nodoClon.setDerecho(clonarInvertidoAux(nodo.getIzquierdo()));
        }
        return nodoClon;
    }


    private void verificarPatronAux(NodoArbol nodo, Lista patron, int ubicacion, boolean[] exito){
        if(nodo != null){
            if(nodo.getElem().equals(patron.recuperar(ubicacion))){
                if(nodo.getIzquierdo() == null && nodo.getDerecho() == null && patron.recuperar(ubicacion+1) == null){
                    exito[0] = true;
                }else{
                    verificarPatronAux(nodo.getIzquierdo(), patron, ubicacion+1, exito);
                    if(!exito[0]){
                        verificarPatronAux(nodo.getDerecho(), patron, ubicacion+1, exito);
                    }
                }
            }
        }
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

    public boolean insertarPorPosicion(Object elemNuevo, int posPadre, char posHijo) {
        boolean exito = false;
        NodoArbol[] nodoPadre = {null};
        int[] posActual = {0};
        localizarPreorden(this.raiz, nodoPadre, posActual, posPadre);
        if (nodoPadre[0] != null) {
            if (nodoPadre[0].getIzquierdo() == null && posHijo == 'I') {
                nodoPadre[0].setIzquierdo(new NodoArbol(elemNuevo, null, null));
                exito = true;
            } else if (nodoPadre[0].getDerecho() == null && posHijo == 'D') {
                nodoPadre[0].setDerecho(new NodoArbol(elemNuevo, null, null));
                exito = true;
            }
        }
        return exito;
    }

    private void localizarPreorden(NodoArbol nodo, NodoArbol[] nodoPadre, int[] posActual, int posPadre) {
        if (nodo != null) {
            posActual[0]++;
            if (posPadre == posActual[0]) {
                nodoPadre[0] = nodo;
            }
            if (nodoPadre[0] == null) {
                localizarPreorden(nodo.getIzquierdo(), nodoPadre, posActual, posPadre);
                localizarPreorden(nodo.getDerecho(), nodoPadre, posActual, posPadre);
            }
        }
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

    public int nivel(Object buscado) {
        int[] nivel = {-1};
        boolean[] encontrado = {false};
        if (this.raiz != null) {
            nivelAux(this.raiz, buscado, nivel, 0, encontrado);
        }
        return nivel[0];
    }

    private void nivelAux(NodoArbol nodo, Object buscado, int[] nivel, int nivelActual, boolean[] encontrado) {
        if (nodo != null && !encontrado[0]) {
            if (nodo.getElem().equals(buscado)) {
                nivel[0] = nivelActual;
                encontrado[0] = true;
            } else {
                nivelAux(nodo.getIzquierdo(), buscado, nivel, nivelActual + 1, encontrado);
                if (!encontrado[0]) {
                    nivelAux(nodo.getDerecho(), buscado, nivel, nivelActual + 1, encontrado);
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

    public Lista obtenerAncestros(Object elem) {
        Lista ancestros = new Lista();
        NodoArbol nodoAux = this.raiz;
        boolean[] encontrado = {false};
        if (nodoAux != null) {
            ancestrosAux(nodoAux, ancestros, elem, encontrado);
        }
        return ancestros;
    }

    private void ancestrosAux(NodoArbol nodo, Lista ancestros, Object buscado, boolean[] encontrado) {
        if (nodo != null) {
            if (!encontrado[0]) {
                if (nodo.getElem().equals(buscado)) {
                    encontrado[0] = true;
                } else {
                    ancestrosAux(nodo.getIzquierdo(), ancestros, buscado, encontrado);
                    ancestrosAux(nodo.getDerecho(), ancestros, buscado, encontrado);
                    if(encontrado[0]){
                        ancestros.insertar(nodo.getElem(), ancestros.longitud() + 1);
                    }
                }
            }
            
        }
    }

    public Lista obtenerDescendientes(Object elem) {
        Lista descendientes = new Lista();
        NodoArbol nodoAux = obtenerNodo(this.raiz, elem);
        if (nodoAux != null) {
            listarDescendientes(nodoAux.getIzquierdo(), descendientes);
            listarDescendientes(nodoAux.getDerecho(), descendientes);
        }
        return descendientes;
    }
    private void listarDescendientes(NodoArbol nodo, Lista descendientes) {
        if (nodo != null) {    
            descendientes.insertar(nodo.getElem(), descendientes.longitud() + 1);
            listarDescendientes(nodo.getIzquierdo(), descendientes);
            listarDescendientes(nodo.getDerecho(), descendientes);
        }
    }

    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        NodoArbol nodoAux = this.raiz;
        if (nodoAux != null) {
            clon.raiz = cloneAux(nodoAux);
        }
        return clon;
    }

    private NodoArbol cloneAux(NodoArbol nodo) {
        NodoArbol nodoClon = null;
        if (nodo != null) {
            nodoClon = new NodoArbol(nodo.getElem(), null, null);
            nodoClon.setIzquierdo(cloneAux(nodo.getIzquierdo()));
            nodoClon.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return nodoClon;
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
