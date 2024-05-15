package jerarquicas;

import lineales.dinamicas.Lista;

public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen(){
        this.raiz = null;
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
                    exito = perteneceAux(nodo, buscado);
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
        return padre;
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
        int[] altura = new int[1];
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
                alturaAux(nodo.getHijoIzquierdo(), alturaMax, alturaActual + 1);
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
                nivelAux(hijo, buscado, nivel, nivelActual + 1);
                if (!exito) {
                    nivelAux(hijo.getHermanoDerecho(), buscado, nivel, nivelActual + 1);
                    hijo = hijo.getHermanoDerecho();
                }
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
                listarPreordenAux(hijo, lista);
                hijo = hijo.getHermanoDerecho();
            }
            lista.insertar(nodo.getElem(), lista.longitud()+1);
        }
    }

    public String toString(){
        return toStringAux(this.raiz);
    }
    private String toStringAux(NodoGen nodo){
        String cadena = "";
        if(nodo != null){
            cadena += nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                cadena += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = nodo.getHijoIzquierdo();
            while(hijo != null){
                cadena += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cadena;
    }
}
