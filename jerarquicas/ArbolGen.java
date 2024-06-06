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

    public boolean insertarPorPosicion(Object elem, int posPadre){
        boolean exito = false;
        int[] posActual = {0};
        NodoGen[] nodoPadre = {null};
        exito = localizarPreorden(this.raiz, nodoPadre, posActual, posPadre);
        if(exito){
            nodoPadre[0].setHijoIzquierdo(new NodoGen(elem, null, nodoPadre[0].getHijoIzquierdo()));
        }
        return exito;
    }

    private boolean localizarPreorden(NodoGen nodo, NodoGen[] nodoPadre, int[] posActual, int posPadre) {
        boolean encontrado = false;
        if (nodo != null) {
            posActual[0]++;
            if (posPadre == posActual[0]) {
                nodoPadre[0] = nodo;
                encontrado = true;
            }
            if (nodoPadre[0] == null) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(!encontrado && hijo != null){
                    encontrado = localizarPreorden(hijo, nodoPadre, posActual, posPadre);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return encontrado;
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

    public Lista listarNiveles(){
        Lista salida = new Lista();
        Cola cola = new Cola();
        cola.poner(this.raiz);
        listarNivelesAux(salida, cola);
        return salida;
    }
    private void listarNivelesAux(Lista lista, Cola cola){
            if(!cola.esVacia()){
                NodoGen nodoActual = (NodoGen) cola.obtenerFrente();
                cola.sacar();
                lista.insertar(nodoActual.getElem(), lista.longitud()+1);
                NodoGen hijo = nodoActual.getHijoIzquierdo();
                while(hijo != null){
                    cola.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }

                hijo = nodoActual.getHijoIzquierdo();
                while(hijo != null){
                    listarNivelesAux(lista, cola);
                    hijo = hijo.getHermanoDerecho();
                }
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

    //////////EJERCICIOS ADICIONALES//////////

    public Lista listarHastaNivel(int niv){
        Lista lista = new Lista();
        listarHastaNivelAux(this.raiz, niv, 0, lista);
        return lista;
    }
    private void listarHastaNivelAux(NodoGen nodo, int niv, int nivelActual, Lista lista){
        //flag para probar visitas a los nodos
        //System.out.println(nodo.getElem());
        if(nodo != null && nivelActual <= niv){
            lista.insertar(nodo.getElem(), lista.longitud()+1);
            nivelActual++;
            if(nivelActual <= niv){
                NodoGen hijo = nodo.getHijoIzquierdo();
                while(hijo != null){
                    listarHastaNivelAux(hijo, niv, nivelActual, lista);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public boolean verificarCamino(Lista l){
        boolean exito = false;
        if(this.raiz != null){
            exito = verificarCaminoAux(this.raiz, 1, l.longitud(), l);
        }
        return exito;
    }

    private boolean verificarCaminoAux(NodoGen nodo, int posLista, int longitudLista, Lista lista){
        boolean exito = false;
        while(!exito && nodo != null){
            if(nodo.getElem() == lista.recuperar(posLista)){
                nodo = nodo.getHijoIzquierdo();
                posLista++;
                if(nodo == null && posLista > longitudLista){
                    exito = true;
                }else if(posLista <= lista.longitud()){
                    exito = verificarCaminoAux(nodo, posLista, longitudLista, lista);
                }
            }else{
                nodo = nodo.getHermanoDerecho();
            }
        }
        return exito;
    }

    public boolean verificarCaminoHastaNodo(Lista l){
        boolean exito = false;
        if(this.raiz != null){
            exito = verificarCaminoHastaNodoAux(this.raiz, 1, l.longitud(), l);
        }
        return exito;
    }

    private boolean verificarCaminoHastaNodoAux(NodoGen nodo, int posLista, int longitudLista, Lista lista){
        boolean exito = false;
        while(!exito && nodo != null){
            if(nodo.getElem() == lista.recuperar(posLista)){
                nodo = nodo.getHijoIzquierdo();
                posLista++;
                if(lista.recuperar(posLista) == null){
                    exito = true;
                }else{
                    exito = verificarCaminoAux(nodo, posLista, longitudLista, lista);
                }
            }else{
                nodo = nodo.getHermanoDerecho();
            }
        }
        return exito;
    }

    public Lista listarEntreNiveles(int nivMin, int nivMax){
        Lista lista = new Lista();
        listarEntreNivelesAux(this.raiz, 0, nivMin, nivMax, lista);
        return lista;
    }
    
    private void listarEntreNivelesAux(NodoGen nodo, int nivelActual, int nivMin, int nivMax, Lista lista){
        if(nodo != null){
            System.out.println("Nodo: " + nodo.getElem());
            if(nivelActual >= nivMin && nivelActual <= nivMax){
                lista.insertar(nodo.getElem(), lista.longitud()+1);
            }
            NodoGen hijo = nodo.getHijoIzquierdo();
            if(nivelActual+1 <= nivMax){
                while(hijo != null){
                    listarEntreNivelesAux(hijo, nivelActual+1, nivMin, nivMax, lista);
                    hijo = hijo.getHermanoDerecho();
                }   
            }
        }
    }
}
