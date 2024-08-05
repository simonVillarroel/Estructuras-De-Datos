package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolBB {

    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable buscado) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, buscado);
        }
        return exito;
    }

    private boolean perteneceAux(NodoABB nodo, Comparable buscado) {
        boolean exito = false;
        if (nodo != null) {
            if (buscado.compareTo(nodo.getElem()) == 0) {
                exito = true;
            } else {
                if (buscado.compareTo(nodo.getElem()) < 0) {
                    exito = perteneceAux(nodo.getIzquierdo(), buscado);
                } else if(buscado.compareTo(nodo.getElem()) > 0){
                    exito = perteneceAux(nodo.getDerecho(), buscado);
                }
            }
        }
        return exito;
    }
    
    public boolean insertar(Comparable elem){
        boolean exito = true;
        if(this.raiz == null){
            this.raiz = new NodoABB(elem);
        }else{
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoABB nodo, Comparable elem){
        //precondicion: n no es nulo
        boolean exito = true;
        if(elem.compareTo(nodo.getElem()) == 0){
            //elemento repetido
           exito = false;
        }else if(elem.compareTo(nodo.getElem()) < 0){
            //elem es menor que nodo.getElem()
            //si tiene HI baja a la izquierda, sino agrega elem
            if(nodo.getIzquierdo() == null){
                nodo.setIzquierdo(new NodoABB(elem));
            }else{
                exito = insertarAux(nodo.getIzquierdo(), elem);
            }
        }else{
            //elem es mayor que nodo.getElem()
            //si tiene HD baja a la derecha, sino agrega elem
            if(nodo.getDerecho() == null){
                nodo.setDerecho(new NodoABB(elem));
            }else{
                exito = insertarAux(nodo.getDerecho(), elem);
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elem){
        boolean exito = true;
        exito = eliminarAux(this.raiz, null, elem);            
        return exito;
    }

    private boolean eliminarAux(NodoABB nodoActual, NodoABB nodoPadre, Comparable elem){
        boolean exito = true;
        if(nodoActual == null){
            exito = false;
        }else{
            if(elem.compareTo(nodoActual.getElem()) < 0){
                exito = eliminarAux(nodoActual.getIzquierdo(), nodoActual, elem);
            }else if(elem.compareTo(nodoActual.getElem()) > 0){
                exito = eliminarAux(nodoActual.getDerecho(), nodoActual, elem);
            }else{
                //se encontro el elemento a eliminar
                NodoABB hijoIzq, hijoDer;
                hijoIzq = nodoActual.getIzquierdo();
                hijoDer = nodoActual.getDerecho();
                if(hijoIzq == null && hijoDer == null){
                    //no tiene hijos: borrar segun caso 1
                    eliminarCaso1(nodoActual, nodoPadre);
                }else if((hijoIzq != null && hijoDer == null) || (hijoIzq == null && hijoDer != null)){
                    //tiene al menos un hijo: borrar segun caso 2
                    eliminarCaso2(nodoActual, nodoPadre);
                }else{
                    //tiene ambos hijos: borrar segun caso 3
                    eliminarCaso3(nodoActual, nodoPadre);
                }
            }
        }
        return exito;
    }

    private void eliminarCaso1(NodoABB nodoActual, NodoABB nodoPadre){
        if(nodoActual == this.raiz){
            this.raiz = null;
        }else{
            if(nodoActual.getElem().compareTo(nodoPadre.getElem()) < 0){
                //el nodo a eliminar es HI de nodoPadre
                nodoPadre.setIzquierdo(null);
            }else{
                //el nodo a eliminar es HD de nodoPadre
                nodoPadre.setDerecho(null);
            }
        }
    }

    private void eliminarCaso2(NodoABB nodoActual, NodoABB nodoPadre){
        if(nodoActual.getElem().compareTo(nodoPadre.getElem()) < 0){
            //el nodo a eliminar es HI de nodoPadre
            if(nodoActual.getIzquierdo() != null){
                //el HI de nodoActual pasa a ser HI de nodoPadre
                nodoPadre.setIzquierdo(nodoActual.getIzquierdo());
            }else{
                //el HD de nodoActual pasa a ser HI de nodoPadre
                nodoPadre.setIzquierdo(nodoActual.getDerecho());
            }
        }else{
            //el nodo a eliminar es HD de nodoPadre
            if(nodoActual.getIzquierdo() != null){
                //el HI de nodoActual pasa a ser HD de nodoPadre
                nodoPadre.setDerecho(nodoActual.getIzquierdo());
            }else{
                //el HD de nodoActual pasa a ser HD de nodoPadre
                nodoPadre.setDerecho(nodoActual.getDerecho());
            }
        }
    }

    private void eliminarCaso3(NodoABB nodoActual, NodoABB nodoPadre){
        if(nodoActual.getIzquierdo() != null){
            nodoActual.setElem(mejorCandidatoA(nodoActual, nodoPadre));
        }else{
            nodoActual.setElem(mejorCandidatoB(nodoActual, nodoPadre));        
        }
    }

    private Comparable mejorCandidatoA(NodoABB nodoActual, NodoABB nodoPadre){
        //busca el mayor elemento del subarbol izquierdo del nodoActual
        Comparable candidato;
        nodoPadre = nodoActual;
        nodoActual = nodoActual.getIzquierdo();//me muevo por unica vez al subarbol izquierdo
        while(nodoActual.getDerecho() != null){//busco el extremo derecho del subarbol derecho
            nodoPadre = nodoActual;
            nodoActual = nodoActual.getDerecho();
        }
        candidato = nodoActual.getElem();
        
        
        if(nodoActual.getIzquierdo() != null){
            //el candidato tiene HI: se debe eliminar segun caso 2
            nodoPadre.setDerecho(nodoActual.getIzquierdo());
        }else{
            //el candidato no tiene hijos: se debe eliminar segun caso 1
            nodoPadre.setDerecho(null);
        }
        return candidato;
    }
    private Comparable mejorCandidatoB(NodoABB nodoActual, NodoABB nodoPadre){
        //busca el menor elemento del subarbol derecho del nodoActual
        Comparable candidato;
        nodoPadre = nodoActual;
        nodoActual = nodoActual.getDerecho();//me muevo por unica vez al subarbol derecho
        while(nodoActual.getIzquierdo() != null){//busco el extremo izquierdo del subarbol izquierdo
            nodoPadre = nodoActual;
            nodoActual = nodoActual.getIzquierdo();
        }
        candidato = nodoActual.getElem();
        if(nodoActual.getDerecho() != null){
            //el candidato tiene HD: se debe eliminar segun caso 2
            nodoPadre.setIzquierdo((nodoActual.getDerecho()));
        }else{
            //el candidato no tiene hijos: se debe eliminar segun caso 1
            nodoPadre.setIzquierdo(null);
        }
        return candidato;
    }

    public Comparable minimoElem() {
        NodoABB nodo = this.raiz;
        Comparable minimo = null;
        while (nodo != null) {
            minimo = nodo.getElem();
            nodo = nodo.getIzquierdo();
        }
        return minimo;
    }
    
    public Comparable maximoElem() {
        NodoABB nodo = this.raiz;
        Comparable maximo = null;
        while (nodo != null) {
            maximo = nodo.getElem();
            nodo = nodo.getDerecho();
        }
        return maximo;
    }

    public boolean esVacio(){
        return this.raiz == null;
    }

    public Lista listar(){
        Lista lista = new Lista();
        if(this.raiz != null){
            listarAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarAux(NodoABB nodo, Lista lista){
        if(nodo != null){
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            listarAux(nodo.getDerecho(), lista);
        }
    }

    public Lista listarRango(Comparable minimo, Comparable maximo){
        Lista lista = new Lista();
        if(this.raiz != null){
            listarRangoAux(this.raiz, lista, minimo, maximo);
        }
        return lista;
    }

    private void listarRangoAux(NodoABB nodo, Lista lista, Comparable min, Comparable max){
        if(nodo != null){
            System.out.println("entre con nodo " + nodo.getElem());
            int mayorQueMinimo = nodo.getElem().compareTo(min);
            int menorQueMaximo = nodo.getElem().compareTo(max);
            
            listarRangoAux(nodo.getIzquierdo(), lista, min, max);
            
            if(mayorQueMinimo >= 0 && menorQueMaximo <= 0){
                //el elemento a insertar esta dentro de los limites o es igual a uno de estos
                lista.insertar(nodo.getElem(), lista.longitud()+1);
            }
            if(nodo.getElem().compareTo(max) < 0){
                listarRangoAux(nodo.getDerecho(), lista, min, max);
            }
        }
    }

    public void vaciar(){
        this.raiz = null;
    }
    
    public ArbolBB clone(){
        ArbolBB clon = new ArbolBB();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoABB cloneAux(NodoABB nodo){
        NodoABB nodoClon = null;
        if(nodo != null){
            nodoClon = new NodoABB(nodo.getElem());
            nodoClon.setIzquierdo(cloneAux(nodo.getIzquierdo()));
            nodoClon.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return nodoClon;
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

    private String toStringAux(NodoABB nodo) {
        String cadena = "";
        if (nodo != null) {
            NodoABB hijoIzqNodo = nodo.getIzquierdo();
            NodoABB hijoDerNodo = nodo.getDerecho();
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

    //////////EJERCICIOS ADICIONALES//////////

    public Lista listarMayorIgual(Comparable elem){
        Lista lista = new Lista();
        listarMayorIgualAux(this.raiz, lista, elem);
        return lista;
    }

    private void listarMayorIgualAux(NodoABB nodo, Lista lista, Comparable elem){
        if(nodo != null){
            if(nodo.getElem().compareTo(elem) >= 0){
                //nodo.elem es mayor a elem
                listarMayorIgualAux(nodo.getIzquierdo(), lista, elem);
                lista.insertar(nodo.getElem(), lista.longitud()+1);
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
            }else{
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
            }
        }
    }

    public Lista listarMenores(Comparable elem){
        Lista lista = new Lista();
        listarMenoresAux(this.raiz, lista, elem);
        return lista;
    }

    private void listarMenoresAux(NodoABB nodo, Lista lista, Comparable elem){
        if(nodo != null){
            if(nodo.getElem().compareTo(elem) < 0){
                //nodo.elem es mayor a elem
                listarMenoresAux(nodo.getIzquierdo(), lista, elem);
                lista.insertar(nodo.getElem(), lista.longitud()+1);
                listarMenoresAux(nodo.getDerecho(), lista, elem);
            }else{
                listarMenoresAux(nodo.getIzquierdo(), lista, elem);
            }
        }
    }
}