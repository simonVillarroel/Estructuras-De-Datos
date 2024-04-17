package Conjuntista.TDA_ArbolAVL;

import Dinamica.TDA_Lista.Lista;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }
    

    public boolean insertar(int d) {
        boolean exito = true;
        NodoAVL nuevo = new NodoAVL(d);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            raiz = insertarAux(nuevo, raiz);
        }
        return exito;
    }
    private NodoAVL insertarAux(NodoAVL nuevo, NodoAVL subAr) {
        NodoAVL nuevoPadre = subAr;
        if (nuevo.getElem() < subAr.getElem()) {//Me muevo en el sub-arbol izquierdo
            if (subAr.getHijoIzquierdo() == null) {
                subAr.setHijoIzquierdo(nuevo);
            } else {
                subAr.setHijoIzquierdo(insertarAux(nuevo, subAr.getHijoIzquierdo()));
                if ((obtenerFE(subAr.getHijoIzquierdo())) - obtenerFE(subAr.getHijoDerecho()) == 2) {
                    if (nuevo.getElem() < subAr.getHijoIzquierdo().getElem()) {
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        } else if (nuevo.getElem() > subAr.getElem()) {//Me muevo en el sub-arbol derecho
            if (subAr.getHijoDerecho() == null) {
                subAr.setHijoDerecho(nuevo);
            } else {
                subAr.setHijoDerecho(insertarAux(nuevo, subAr.getHijoDerecho()));
                if ((obtenerFE(subAr.getHijoIzquierdo()) - obtenerFE(subAr.getHijoDerecho()) == -2)) {
                    if (nuevo.getElem() > subAr.getHijoDerecho().getElem()) {
                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        }
        //Actualizo la altura//
        if ((subAr.getHijoIzquierdo() == null) && (subAr.getHijoDerecho() != null)) {//Si HI = null y HD != null 
            subAr.recalcularAltura(subAr.getHijoDerecho().getAltura() + 1);
        } else if ((subAr.getHijoIzquierdo() != null) && (subAr.getHijoDerecho() == null)) {//Si HI != null y HD = null
            subAr.recalcularAltura(subAr.getHijoIzquierdo().getAltura() + 1);
        } else {//Si HI y HD son != null
            subAr.recalcularAltura(Math.max(obtenerFE(subAr.getHijoIzquierdo()), obtenerFE(subAr.getHijoDerecho()) + 1));
        }
        return nuevoPadre;
    }

    public boolean eliminar(int elemento) {
        boolean exito = true;
        if(this.raiz == null || !pertenece(elemento)){
            exito = false;
        }else{
            this.raiz = eliminarAux(this.raiz, elemento);
        }
        return exito;
    }
    private NodoAVL eliminarAux(NodoAVL subAr, int elemento) {
        NodoAVL nuevoPadre = subAr;
        if (elemento < subAr.getElem()) {//Me muevo en el sub-arbol izquierdo
            subAr.setHijoIzquierdo(eliminarAux(subAr.getHijoIzquierdo(), elemento));
            } else if (elemento > subAr.getElem()) {
                subAr.setHijoDerecho(eliminarAux(subAr.getHijoDerecho(), elemento));
            } else {
                // Nodo con un solo hijo o sin hijos
                if (subAr.getHijoIzquierdo() == null) {
                    nuevoPadre = subAr.getHijoDerecho();
                } else{
                    if (subAr.getHijoDerecho() == null) {
                        nuevoPadre = subAr.getHijoIzquierdo();
                    }else{
                        // Nodo con dos hijos
                        ArbolAVL arbolAux = new ArbolAVL();
                        arbolAux.raiz = subAr.getHijoDerecho();
                        subAr.setElem(arbolAux.minimoElem());
                        subAr.setHijoDerecho(eliminarAux(subAr.getHijoDerecho(), subAr.getElem()));
                    }
                }
            }
        //Actualizo la altura//
        if ((subAr.getHijoIzquierdo() == null) && (subAr.getHijoDerecho() != null)) {//Si HI = null y HD != null 
            subAr.recalcularAltura(subAr.getHijoDerecho().getAltura() + 1);
        } else if ((subAr.getHijoIzquierdo() != null) && (subAr.getHijoDerecho() == null)) {//Si HI != null y HD = null
            subAr.recalcularAltura(subAr.getHijoIzquierdo().getAltura() + 1);
        } else {//Si HI y HD son != null
            subAr.recalcularAltura(Math.max(obtenerFE(subAr.getHijoIzquierdo()), obtenerFE(subAr.getHijoDerecho()) + 1));
        }
        return nuevoPadre;
    }
    
    //Obtiene factor de equilbrio///
    public int obtenerFE(NodoAVL x) {
        int res;
        if (x == null) {
            res = -1;
        } else {
            res = x.getAltura();
        }
        return res;
    }

    //Rotacion simple Izquierda///
    public NodoAVL rotacionIzquierda(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getHijoDerecho();
        temp = h.getHijoIzquierdo();
        h.setHijoIzquierdo(r);
        r.setHijoDerecho(temp);
        r.recalcularAltura(Math.max(obtenerFE(r.getHijoIzquierdo()), obtenerFE(r.getHijoDerecho())) + 1);
        h.recalcularAltura(Math.max(obtenerFE(h.getHijoIzquierdo()), obtenerFE(h.getHijoDerecho())) + 1);
        return h;
    }

    //Rotacion simple Derecho///
    public NodoAVL rotacionDerecha(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getHijoIzquierdo();
        temp = h.getHijoDerecho();
        h.setHijoDerecho(r);
        r.setHijoIzquierdo(temp);
        r.recalcularAltura(Math.max(obtenerFE(r.getHijoIzquierdo()), obtenerFE(r.getHijoDerecho())) + 1);
        h.recalcularAltura(Math.max(obtenerFE(h.getHijoIzquierdo()), obtenerFE(h.getHijoDerecho())) + 1);
        return h;
    }

    //Rotacion doble derecha-izquierda//
    public NodoAVL rotacionDobleDerecha(NodoAVL r) {
        NodoAVL temp;
        r.setHijoDerecho(rotacionDerecha(r.getHijoDerecho()));
        temp = rotacionIzquierda(r);
        return temp;
    }

    //Rotacion doble izquierda-derecha//
    public NodoAVL rotacionDobleIzquierda(NodoAVL r) {
        NodoAVL temp;
        r.setHijoIzquierdo(rotacionIzquierda(r.getHijoIzquierdo()));
        temp = rotacionDerecha(r);
        return temp;
    }

    public boolean pertenece(int elem) {
        boolean exito = perteneceAux(elem, this.raiz);
        return exito;
    }

    public boolean perteneceAux(int elem, NodoAVL n) {
        boolean exito;
        if (n != null) {
            //si coinciden la busqueda culmina con exito
            if (elem == n.getElem()) {
                exito = true;
            } else {
                if (elem < n.getElem()) {
                    //si elem es menor al elemento del nodo n, la busqueda continua por subarbol izquierdo
                    exito = perteneceAux(elem, n.getHijoIzquierdo());
                } else {
                    //si elem es mayor al elemento del nodo n, la busqueda continua por subarbol derecho
                    exito = perteneceAux(elem, n.getHijoDerecho());
                }
            }
        } else {
            //si se llega a un nulo la busqueda culmina sin exito
            exito = false;
        }
        return exito;
    }
    
    public Lista listar() {
        Lista salida = new Lista();
        salida = listarAux(this.raiz, salida);
        return salida;
    }

    private Lista listarAux(NodoAVL nodo, Lista ls) {
        if (nodo != null) {
            listarAux(nodo.getHijoIzquierdo(), ls);
            ls.insertar(nodo.getElem(), ls.longitud() + 1);
            listarAux(nodo.getHijoDerecho(), ls);
        }
        return ls;
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista salida = new Lista();
        salida = listarRangoAux(this.raiz, salida, min, max);
        return salida;
    }

    private Lista listarRangoAux(NodoAVL n, Lista ls, Comparable min, Comparable max) {
        if (n != null) {
            if ((min.compareTo(n.getElem()) <= 0) || (max.compareTo(n.getElem()) <= 0)) {
                //Si el minimo o el maximo son menores al elemento del nodo, bajo al subarbol izquierdo
                listarRangoAux(n.getHijoIzquierdo(), ls, min, max);
            }
            if ((min.compareTo(n.getElem()) <= 0) && (max.compareTo(n.getElem()) >= 0)) {
                //Si el minimo es menor y el maximo mayor al elemento del nodo, éste se debe insertar en la lista
                ls.insertar(n.getElem(), ls.longitud() + 1);
            }
            if ((min.compareTo(n.getElem()) >= 0) || (max.compareTo(n.getElem()) >= 0)) {
                //Si el minimo o el maximo son menores al elemento del nodo, bajo al subarbol derecho 
                listarRangoAux(n.getHijoDerecho(), ls, min, max);
            }
        }
        return ls;
    }

    public int minimoElem() {
        NodoAVL n = this.raiz;
        while (n.getHijoIzquierdo() != null) {
            n = n.getHijoIzquierdo();
        }
        return n.getElem();
    }

    public int maximoElem() {
        NodoAVL n = this.raiz;
        while (n.getHijoDerecho() != null) {
            n = n.getHijoDerecho();
        }
        return n.getElem();
    }

    public boolean esVacio() {
        boolean res;
        if (this.raiz == null) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolAVL clone(){
        ArbolAVL clon=null;
        if(this.raiz == null){
            System.out.println("Arbol vacío");
        }else{
            clon = cloneAux(this.raiz);
        }
        return clon;
    }
 
    private ArbolAVL cloneAux(NodoAVL n){
        NodoAVL clon;
        if(n ==null){
            clon=null;
        }else{
            ArbolAVL izqClon, derClon;
            izqClon = cloneAux(n.getHijoIzquierdo());
            derClon = cloneAux(n.getHijoDerecho());
            clon=new NodoAVL(n.getElem());
            clon.setHijoIzquierdo(izqClon.raiz);
            clon.setHijoDerecho(derClon.raiz);
        }
        ArbolAVL nuevo = new ArbolAVL();
        nuevo.raiz= clon;
        return nuevo;
    }
    
    public String toString() {
        String resultado = "El arbol esta vacio.";
        NodoAVL aux = this.raiz;
        if (this.raiz != null) {
            resultado = toStringAux(aux);
        }
        return resultado;
    }

    private String toStringAux(NodoAVL nodo) {
        String resultado = "";
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                resultado += "[Nodo: " + nodo.getElem() + " no posee hijos.]" + '\n';
            } else {
                if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() != null) {
                    resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: No posee. Hijo derecho: " + nodo.getHijoDerecho().getElem() + ".]" + '\n';
                } else {
                    if (nodo.getHijoDerecho() == null && nodo.getHijoIzquierdo() != null) {
                        resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: " + nodo.getHijoIzquierdo().getElem() + ". Hijo derecho: No posee.]" + '\n';
                    } else {
                        resultado += "[Nodo padre: " + nodo.getElem() + ". Hijo izquierdo: " + nodo.getHijoIzquierdo().getElem() + ". Hijo derecho: " + nodo.getHijoDerecho().getElem() + ".]" + '\n';
                    }
                }
            }
            resultado += toStringAux(nodo.getHijoIzquierdo());
            resultado += toStringAux(nodo.getHijoDerecho());
        }
        return resultado;
    }
}
