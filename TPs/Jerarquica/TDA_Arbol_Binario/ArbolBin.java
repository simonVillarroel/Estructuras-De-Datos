package Jerarquica.TDA_Arbol_Binario;

import Dinamica.Nodo;
import Dinamica.TDA_Cola.Cola;
import Dinamica.TDA_Lista.Lista;
import Jerarquica.NodoArbol;

public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if ((lugar == 'I') && (nodoPadre.getIzquierdo() == null)) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else {
                    if ((lugar == 'D') && (nodoPadre.getDerecho() == null)) {
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    } else {
                        exito = false;
                    }
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar(){
        this.raiz = null;
    }

    public int altura() {
        return alturaAux(this.raiz);
    }
    private int alturaAux(NodoArbol nodo) {
        int resultado;
        if (nodo == null) {
            resultado = -1;
        } else {
            resultado = (1 + Math.max(alturaAux(nodo.getIzquierdo()), alturaAux(nodo.getDerecho())));
        }
        return resultado;
    }

    public int nivel(Object elem) {
        NodoArbol aux = this.raiz;
        return nivelAux(aux, elem);
    }
    private int nivelAux(NodoArbol n, Object buscado) {
        int nivel = -1;
        if (n != null) {
            if (this.raiz.getElem() == buscado) {
                nivel = 0;
            } else {
                if (n.getElem() == buscado) {
                    nivel = nivel + 1;
                } else {
                    nivel = nivelAux(n.getIzquierdo(), buscado);
                    if (nivel == -1) {
                        nivel = nivelAux(n.getDerecho(), buscado);
                    }
                    if (nivel != -1) {
                        nivel++;
                    }
                }
            }
        }
        return nivel;
    }

    public Object padre(Object buscado) {
        Object padre = null;
        NodoArbol aux = this.raiz;
        if (aux != null) {
            if (this.raiz.getElem().equals(buscado)) {
                padre = null;
            } else {
                padre = padreAux(aux, buscado);
            }
        }
        return padre;
    }
    private Object padreAux(NodoArbol nodo, Object buscado) {
        Object resultado = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                if (nodo.getIzquierdo().getElem().equals(buscado)) {
                    resultado = nodo.getElem();
                } else {
                    resultado = padreAux(nodo.getIzquierdo(), buscado);
                }
            }
            if (resultado == null) {
                if (nodo.getDerecho() != null) {
                    if (nodo.getDerecho().getElem().equals(buscado)) {
                        resultado = nodo.getElem();
                    } else {
                        resultado = padreAux(nodo.getDerecho(), buscado);
                    }
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
    private Lista ancestrosAux(NodoArbol nodo, NodoArbol padre, Object elem, Lista ls) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else {
                ls = ancestrosAux(nodo.getIzquierdo(), nodo, elem, ls);
                if (encontrado == false) {
                    ls = ancestrosAux(nodo.getDerecho(), nodo, elem, ls);
                }
            }
        }
        if (encontrado && padre != null) {
            ls.insertar(padre.getElem(), ls.longitud() + 1);
            ls = ancestrosAux(this.raiz, null, padre.getElem(), ls);
        }
        return ls;
    }

    public Lista descendientes(Object ancestro){
        Lista descendientes = new Lista();
        descendientesAux(this.raiz, descendientes, ancestro);
        return descendientes;
    }
    private Lista descendientesAux(NodoArbol nodo, Lista descendientes, Object ancestro){
        if(nodo != null){
            if(nodo.getElem().equals(ancestro)){
                descendientes = this.listarPreordenAux(nodo.getIzquierdo(), descendientes);
                descendientes = this.listarPreordenAux(nodo.getDerecho(), descendientes);
            }else{
                descendientesAux(nodo.getIzquierdo(), descendientes, ancestro);
                descendientesAux(nodo.getDerecho(), descendientes, ancestro);
            }
        }
        return descendientes;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public ArbolBin clone() {
        ArbolBin clon = null;
        if (this.raiz == null) {
            System.out.println("Arbol vacío");
        } else {
            clon = cloneAux(this.raiz);
        }
        return clon;
    }
    private ArbolBin cloneAux(NodoArbol n) {
        NodoArbol clon;
        if (n == null) {
            clon = null;
        } else {
            ArbolBin izqClon, derClon;
            izqClon = cloneAux(n.getIzquierdo());
            derClon = cloneAux(n.getDerecho());
            clon = new NodoArbol(n.getElem());
            clon.setIzquierdo(izqClon.raiz);
            clon.setDerecho(derClon.raiz);
        }
        ArbolBin nuevo = new ArbolBin();
        nuevo.raiz = clon;
        return nuevo;
    }

    public String toString() {
        String resultado = "El arbol esta vacio.";
        NodoArbol aux = this.raiz;
        if (this.raiz != null) {
            resultado = toStringAux(aux);
        }
        return resultado;
    }
    private String toStringAux(NodoArbol nodo) {
        String resultado = "";
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                resultado += "[Nodo: " + nodo.getElem() + " no posee hijos.]" + '\n';
            } else {
                if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                    resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: No posee. Hijo derecho: " + nodo.getDerecho().getElem() + ".]" + '\n';
                } else {
                    if (nodo.getDerecho() == null && nodo.getIzquierdo() != null) {
                        resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: " + nodo.getIzquierdo().getElem() + ". Hijo derecho: No posee.]" + '\n';
                    } else {
                        resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: " + nodo.getIzquierdo().getElem() + ". Hijo derecho: " + nodo.getDerecho().getElem() + ".]" + '\n';
                    }
                }
            }

            resultado += toStringAux(nodo.getIzquierdo());
            resultado += toStringAux(nodo.getDerecho());
        }
        return resultado;
    }

    public Lista frontera() {
        Lista resultado = new Lista();
        resultado = fronteraAux(this.raiz, resultado);
        return resultado;
    }
    private Lista fronteraAux(NodoArbol n, Lista ls) {
        if (n != null) {
            if ((n.getIzquierdo() == null) && (n.getDerecho() == null)) {
                ls.insertar(n.getElem(), ls.longitud() + 1);
            } else {
                ls = fronteraAux(n.getIzquierdo(), ls);
                ls = fronteraAux(n.getDerecho(), ls);
            }
        }
        return ls;
    }

    //Recorridos de arbol binario//
    public Lista listarPreorden() {
        Lista salida = new Lista();
        salida = listarPreordenAux(this.raiz, salida);
        return salida;

    }
    private Lista listarPreordenAux(NodoArbol nodo, Lista ls) {
        if (nodo != null) {
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), ls);
            listarPreordenAux(nodo.getDerecho(), ls);
        }
        return ls;
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        salida = listarInordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarInordenAux(NodoArbol nodo, Lista ls) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), ls);
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), ls);
        }
        return ls;
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        salida = listarPosordenAux(this.raiz, salida);
        return salida;
    }
    private Lista listarPosordenAux(NodoArbol nodo, Lista ls) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), ls);
            listarPosordenAux(nodo.getDerecho(), ls);
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
        }
        return ls;
    }

    public Lista listarPornivel() {
        Lista salida = new Lista();
        salida = pornivelAux(this.raiz, salida);
        return salida;
    }
    private Lista pornivelAux(NodoArbol nodo, Lista lista) {
        Cola q = new Cola();
        q.poner(nodo);
        while (q.esVacia() != true) {
            nodo = (NodoArbol) q.obtenerFrente();
            q.sacar();
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            if (nodo.getIzquierdo() != null) {
                q.poner(nodo.getIzquierdo());
            }
            if (nodo.getDerecho() != null) {
                q.poner(nodo.getDerecho());
            }
        }
        return lista;
    }
}
