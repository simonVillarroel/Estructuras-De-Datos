package TDAsUsoEspecifico;

import lineales.dinamicas.Lista;

public class Diccionario{
    private NodoAVLDicc raiz;

    public Diccionario(){
        this.raiz = null;
    }

    public boolean insertar(Comparable clave, Object dato){
        boolean[] exito = {true};
        if(this.raiz == null){
            this.raiz = new NodoAVLDicc(clave, dato);
        }else{
            this.raiz = insertarAux(this.raiz, clave, dato, exito);
        }
        return exito[0];
    }
    
    private NodoAVLDicc insertarAux(NodoAVLDicc nodo, Comparable clave, Object dato, boolean[] exito){
        //precondicion: n no es nulo
        if(clave.compareTo(nodo.getClave()) == 0){
            //elemento repetido
           exito[0] = false;
        }else if(clave.compareTo(nodo.getClave()) < 0){
            //elem es menor que nodo.getElem()
            //si tiene HI baja a la izquierda, sino agrega elem
            if(nodo.getHijoIzquierdo() == null){
                nodo.setHijoIzquierdo(new NodoAVLDicc(clave, dato));
            }else{
                nodo.setHijoIzquierdo(insertarAux(nodo.getHijoIzquierdo(), clave, dato, exito));
            }
        }else{
            //elem es mayor que nodo.getElem()
            //si tiene HD baja a la derecha, sino agrega elem
            if(nodo.getHijoDerecho() == null){
                nodo.setHijoDerecho(new NodoAVLDicc(clave, dato));
            }else{
                nodo.setHijoDerecho(insertarAux(nodo.getHijoDerecho(), clave, dato, exito));
            }
        }
        
        if(exito[0]){
            //si hubo insercion actualiza la altura y verifica el balance del nodo actual
            nodo.recalcularAltura();
            nodo = balancear(nodo);
        }
        return nodo;
    }

    private NodoAVLDicc balancear(NodoAVLDicc nodo){
        int balanceHijo, balance = calcularBalance(nodo);
        if(balance == 2){
            //el arbol esta desbalanceado hacia la izquierda
            balanceHijo = calcularBalance(nodo.getHijoIzquierdo());
            if(balanceHijo == -1){
                //rotacionDobleIzqDer
                nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
                nodo = rotarDerecha(nodo);
            }else{
                nodo = rotarDerecha(nodo);
            }
        }
        if(balance == -2){
            //el arbol esta desbalanceado hacia la derecha
            balanceHijo = calcularBalance(nodo.getHijoDerecho());
            if(balanceHijo == 1){
                //rotacionDobleDerIzq
                nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
                nodo = rotarIzquierda(nodo);
            }else{
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }
    
    private int calcularBalance(NodoAVLDicc nodo){
        //altura de hijo nulo = -1
        int balance, alturaHI = -1, alturaHD = -1;
        if(nodo.getHijoIzquierdo() != null){
            nodo.getHijoIzquierdo().recalcularAltura();
            alturaHI = nodo.getHijoIzquierdo().getAltura();
        }
        if(nodo.getHijoDerecho() != null){
            nodo.getHijoDerecho().recalcularAltura();
            alturaHD = nodo.getHijoDerecho().getAltura();
        }
        balance = alturaHI - alturaHD;
        return balance;
    }

    private NodoAVLDicc rotarIzquierda(NodoAVLDicc pivot){
        NodoAVLDicc hijo, temp;
        hijo = pivot.getHijoDerecho();
        temp = hijo.getHijoIzquierdo();
        hijo.setHijoIzquierdo(pivot);
        pivot.setHijoDerecho(temp);
        return hijo;
    }
    
    private NodoAVLDicc rotarDerecha(NodoAVLDicc pivot){
        NodoAVLDicc hijo, temp;
        hijo = pivot.getHijoIzquierdo();
        temp = hijo.getHijoDerecho();
        hijo.setHijoDerecho(pivot);
        pivot.setHijoIzquierdo(temp);
        return hijo;
    }

    public boolean eliminar(Comparable clave) {
        boolean[] exito = {false};
        this.raiz = eliminarAux(this.raiz, null, clave, exito);
        return exito[0];
    }

    private NodoAVLDicc eliminarAux(NodoAVLDicc nodo, NodoAVLDicc padre, Comparable clave, boolean[] exito) {
        boolean encontrado = false;
        if(nodo != null) {
            //Comparo el elemento a eliminar con el elemento en el nodo actual del arbol
            if (clave.compareTo(nodo.getClave()) == 0) {
                encontrado = true;
            } else {
                if (clave.compareTo(nodo.getClave()) < 0) {
                    //Si comp es menor a 0 avanzo hacia el subárbol izquierdo
                    nodo.setHijoIzquierdo(eliminarAux(nodo.getHijoIzquierdo(), nodo, clave, exito));
                } else {
                    //Si comp es mayor a 0 avanzo hacia el subárbol derecho
                    nodo.setHijoDerecho(eliminarAux(nodo.getHijoDerecho(), nodo, clave, exito));    
                }
            }
            
            nodo = balancear(nodo);
            
        }
        if (encontrado) {
            //Si encontro el elemento
            if ((nodo.getHijoIzquierdo() == null) && (nodo.getHijoDerecho()) == null) {
                //Si es hoja: eliminar según el caso 1
                eliminarCaso1(nodo, padre);
                nodo = null;
            } else {
                if ((nodo.getHijoIzquierdo() != null) && (nodo.getHijoDerecho()) != null) {
                    //Si tiene ambos hijos: eliminar según el caso 3
                    eliminarCaso3(nodo, padre);
                } else {
                    //Si tiene un hijo: eliminar según el caso 2
                    nodo = eliminarCaso2(nodo, padre);       
                }
            }
            exito[0] = true;
        }
        return nodo;
    }

    private void eliminarCaso1(NodoAVLDicc n, NodoAVLDicc padre) {
        if (padre == null) {
            //En el caso de que el nodo hoja sea la raiz
            this.raiz = null;
        } else {
            if (padre.getHijoIzquierdo() == n) {
                //Si el nodo hoja que elimino es HI de su padre
                padre.setHijoIzquierdo(null);
            } else {
                //Si el nodo hoja que elimino es HD de su padre
                padre.setHijoDerecho(null);
            }
        }
    }

    private NodoAVLDicc eliminarCaso2(NodoAVLDicc nodoActual, NodoAVLDicc nodoPadre){
        NodoAVLDicc aux;
        if(nodoActual.getClave().compareTo(nodoPadre.getClave()) < 0){
            //el nodo a eliminar es HI de nodoPadre
            if(nodoActual.getHijoIzquierdo() != null){
                //el HI de nodoActual pasa a ser HI de nodoPadre
                aux = nodoActual.getHijoIzquierdo();
            }else{
                //el HD de nodoActual pasa a ser HI de nodoPadre
                aux = nodoActual.getHijoDerecho();
            }
        }else{
            //el nodo a eliminar es HD de nodoPadre
            if(nodoActual.getHijoIzquierdo() != null){
                //el HI de nodoActual pasa a ser HD de nodoPadre
                aux = nodoActual.getHijoIzquierdo();
            }else{
                //el HD de nodoActual pasa a ser HD de nodoPadre
                aux = nodoActual.getHijoDerecho();
            }
        }
        return aux;
    }

    private void eliminarCaso3(NodoAVLDicc nodo, NodoAVLDicc padre) {
        NodoAVLDicc min = nodo.getHijoDerecho();
        NodoAVLDicc padreAux = nodo;
        while (min.getHijoIzquierdo() != null) {
            padreAux = min;
            min = min.getHijoIzquierdo();
        }
        //Intercambia el contenido del nodo a borrar por el del nodo menor del subarbol derecho (NodoABB min)
        nodo.setClave(min.getClave());
        nodo.setDato(min.getDato());
        //Ahora se procede a eliminar el nodo min
        if ((min.getHijoIzquierdo() == null) && (min.getHijoDerecho()) == null) {
            //Si el nodo min es hoja: eliminar según el caso 1
            eliminarCaso1(min, padreAux);
        } else {
            //Si el nodo min tiene un solo hijo: eliminar según el caso 1
            eliminarCaso2(min, padreAux);
        }
    }

    public Object obtenerDato(Comparable clave){
        Object dato = null;
        dato = obtenerDatoAux(this.raiz, clave);
        return dato;
    }
    private Object obtenerDatoAux(NodoAVLDicc nodo, Comparable clave){
        Object dato = null;
        if(nodo != null){
            if(clave.compareTo(nodo.getClave()) == 0){
                dato = nodo.getDato();
            }else if(clave.compareTo(nodo.getClave()) < 0){
                dato = obtenerDatoAux(nodo.getHijoIzquierdo(), clave);
            }else{
                dato = obtenerDatoAux(nodo.getHijoDerecho(), clave);
            }
        }
        return dato;
    }

    public boolean existeClave(Comparable clave){
        boolean exito = existeClaveAux(this.raiz, clave);
        return exito;
    }
    private boolean existeClaveAux(NodoAVLDicc nodo, Comparable clave){
        boolean exito = false;
        if(nodo != null){
            if(clave.compareTo(nodo.getClave()) == 0){
                exito = true;
            }else if(clave.compareTo(nodo.getClave()) < 0){
                exito = existeClaveAux(nodo.getHijoIzquierdo(), clave);
            }else{
                exito = existeClaveAux(nodo.getHijoDerecho(), clave);
            }
        }
        return exito;
    }

    public Lista listarClaves(){
        Lista lista = new Lista();
        if(this.raiz != null){
            listarClavesAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarClavesAux(NodoAVLDicc nodo, Lista lista){
        if(nodo != null){
            listarClavesAux(nodo.getHijoIzquierdo(), lista);
            lista.insertar(nodo.getClave(), lista.longitud()+1);
            listarClavesAux(nodo.getHijoDerecho(), lista);
        }
    }

    public Lista listarDatos(){
        Lista lista = new Lista();
        if(this.raiz != null){
            listarDatosAux(this.raiz, lista);
        }
        return lista;
    }

    private void listarDatosAux(NodoAVLDicc nodo, Lista lista){
        if(nodo != null){
            listarDatosAux(nodo.getHijoIzquierdo(), lista);
            lista.insertar(nodo.getDato(), lista.longitud()+1);
            listarDatosAux(nodo.getHijoDerecho(), lista);
        }
    }

    public void vaciar(){
        this.raiz = null;
    }
    
    public Diccionario clone(){
        Diccionario clon = new Diccionario();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoAVLDicc cloneAux(NodoAVLDicc nodo){
        NodoAVLDicc nodoClon = null;
        if(nodo != null){
            nodoClon = new NodoAVLDicc(nodo.getClave(), nodo.getDato());
            nodoClon.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
            nodoClon.setHijoDerecho(cloneAux(nodo.getHijoDerecho()));
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

    private String toStringAux(NodoAVLDicc nodo) {
        String cadena = "";
        if (nodo != null) {
            NodoAVLDicc hijoIzqNodo = nodo.getHijoIzquierdo();
            NodoAVLDicc hijoDerNodo = nodo.getHijoDerecho();
            cadena += nodo.getClave().toString() + ": " + nodo.getDato().toString();
            /*if (hijoIzqNodo != null) {
                cadena += " --> HI: " + hijoIzqNodo.getClave();
            } else {
                cadena += " --> HI: null";
            }
            if (hijoDerNodo != null) {
                cadena += " --> HD: " + hijoDerNodo.getClave();
            } else {
                cadena += " --> HD: null";
            }*/
            cadena += "\n";
            cadena += toStringAux(nodo.getHijoIzquierdo());
            cadena += toStringAux(nodo.getHijoDerecho());
        }
        return cadena;
    }
}