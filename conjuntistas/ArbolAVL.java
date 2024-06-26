package conjuntistas;

import lineales.dinamicas.Lista;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL(){
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem){
        boolean exito = perteneceAux(this.raiz, elem);
        return exito;
    }
    private boolean perteneceAux(NodoAVL nodo, Comparable elem){
        boolean exito = false;
        if(nodo != null){
            if(elem.compareTo(nodo.getElem()) == 0){
                exito = true;
            }else if(elem.compareTo(nodo.getElem()) < 0){
                exito = perteneceAux(nodo.getIzquierdo(), elem);
            }else{
                exito = perteneceAux(nodo.getDerecho(), elem);
            }
        }
        return exito;
    }

    public boolean insertar(Comparable elem){
        boolean[] exito = {true};
        if(this.raiz == null){
            this.raiz = new NodoAVL(elem);
        }else{
            this.raiz = insertarAux(this.raiz, elem, exito);
        }
        return exito[0];
    }
    
    private NodoAVL insertarAux(NodoAVL nodo, Comparable elem, boolean[] exito){
        //precondicion: n no es nulo
        if(elem.compareTo(nodo.getElem()) == 0){
            //elemento repetido
           exito[0] = false;
        }else if(elem.compareTo(nodo.getElem()) < 0){
            //elem es menor que nodo.getElem()
            //si tiene HI baja a la izquierda, sino agrega elem
            if(nodo.getIzquierdo() == null){
                nodo.setIzquierdo(new NodoAVL(elem));
            }else{
                nodo.setIzquierdo(insertarAux(nodo.getIzquierdo(), elem, exito));
            }
        }else{
            //elem es mayor que nodo.getElem()
            //si tiene HD baja a la derecha, sino agrega elem
            if(nodo.getDerecho() == null){
                nodo.setDerecho(new NodoAVL(elem));
            }else{
                nodo.setDerecho(insertarAux(nodo.getDerecho(), elem, exito));
            }
        }
        
        if(exito[0]){
            //si hubo insercion actualiza la altura y verifica el balance del nodo actual
            nodo.recalcularAltura();
            nodo = balancear(nodo);
        }
        return nodo;
    }
    
    private NodoAVL balancear(NodoAVL nodo){
        int balanceHijo, balance = calcularBalance(nodo);
        if(balance == 2){
            //el arbol esta desbalanceado hacia la izquierda
            balanceHijo = calcularBalance(nodo.getIzquierdo());
            if(balanceHijo == -1){
                //rotacionDobleIzqDer
                System.out.println("Rotacion doble izquierda-derecha con pivot: " + nodo.getElem() + " y h: " + nodo.getIzquierdo().getElem());
                nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
                nodo = rotarDerecha(nodo);
            }else{
                System.out.println("Rotacion simple derecha con pivot " + nodo.getElem());
                nodo = rotarDerecha(nodo);
            }
        }
        if(balance == -2){
            //el arbol esta desbalanceado hacia la derecha
            balanceHijo = calcularBalance(nodo.getDerecho());
            if(balanceHijo == 1){
                //rotacionDobleDerIzq
                System.out.println("Rotacion doble derecha-izquierda con pivot: " + nodo.getElem() + " y h: " + nodo.getDerecho().getElem());
                nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
                nodo = rotarIzquierda(nodo);
            }else{
                System.out.println("Rotacion simple izquierda con pivot " + nodo.getElem());
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }
    
    private int calcularBalance(NodoAVL nodo){
        //altura de hijo nulo = -1
        int balance, alturaHI = -1, alturaHD = -1;
        if(nodo.getIzquierdo() != null){
            nodo.getIzquierdo().recalcularAltura();
            alturaHI = nodo.getIzquierdo().getAltura();
        }
        if(nodo.getDerecho() != null){
            nodo.getDerecho().recalcularAltura();
            alturaHD = nodo.getDerecho().getAltura();
        }
        balance = alturaHI - alturaHD;
        return balance;
    }
    
    private NodoAVL rotarIzquierda(NodoAVL pivot){
        NodoAVL hijo, temp;
        hijo = pivot.getDerecho();
        temp = hijo.getIzquierdo();
        hijo.setIzquierdo(pivot);
        pivot.setDerecho(temp);
        System.out.println("retorno nodo " + hijo.getElem());
        return hijo;
    }
    
    private NodoAVL rotarDerecha(NodoAVL pivot){
        NodoAVL hijo, temp;
        hijo = pivot.getIzquierdo();
        temp = hijo.getDerecho();
        hijo.setDerecho(pivot);
        pivot.setIzquierdo(temp);
        return hijo;
    }

    public boolean eliminar(Comparable elem) {
        boolean[] exito = {false};
        this.raiz = eliminarAux(this.raiz, null, elem, exito);
        return exito[0];
    }

    private NodoAVL eliminarAux(NodoAVL nodo, NodoAVL padre, Comparable elem, boolean[] exito) {
        boolean encontrado = false;
        if(nodo != null) {
            //Comparo el elemento a eliminar con el elemento en el nodo actual del arbol
            if (elem.compareTo(nodo.getElem()) == 0) {
                encontrado = true;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    //Si comp es menor a 0 avanzo hacia el subárbol izquierdo
                    nodo.setIzquierdo(eliminarAux(nodo.getIzquierdo(), nodo, elem, exito));
                } else {
                    //Si comp es mayor a 0 avanzo hacia el subárbol derecho
                    nodo.setDerecho(eliminarAux(nodo.getDerecho(), nodo, elem, exito));    
                }
            }
            
            nodo = balancear(nodo);
            
        }
        if (encontrado) {
            //Si encontro el elemento
            if ((nodo.getIzquierdo() == null) && (nodo.getDerecho()) == null) {
                //Si es hoja: eliminar según el caso 1
                eliminarCaso1(nodo, padre);
                nodo = null;
            } else {
                if ((nodo.getIzquierdo() != null) && (nodo.getDerecho()) != null) {
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

    private void eliminarCaso1(NodoAVL n, NodoAVL padre) {
        if (padre == null) {
            //En el caso de que el nodo hoja sea la raiz
            this.raiz = null;
        } else {
            if (padre.getIzquierdo() == n) {
                //Si el nodo hoja que elimino es HI de su padre
                padre.setIzquierdo(null);
            } else {
                //Si el nodo hoja que elimino es HD de su padre
                padre.setDerecho(null);
            }
        }
    }

    private NodoAVL eliminarCaso2(NodoAVL nodoActual, NodoAVL nodoPadre){
        NodoAVL aux;
        if(nodoActual.getElem().compareTo(nodoPadre.getElem()) < 0){
            //el nodo a eliminar es HI de nodoPadre
            if(nodoActual.getIzquierdo() != null){
                //el HI de nodoActual pasa a ser HI de nodoPadre
                aux = nodoActual.getIzquierdo();
            }else{
                //el HD de nodoActual pasa a ser HI de nodoPadre
                aux = nodoActual.getDerecho();
            }
        }else{
            //el nodo a eliminar es HD de nodoPadre
            if(nodoActual.getIzquierdo() != null){
                //el HI de nodoActual pasa a ser HD de nodoPadre
                aux = nodoActual.getIzquierdo();
            }else{
                //el HD de nodoActual pasa a ser HD de nodoPadre
                aux = nodoActual.getDerecho();
            }
        }
        return aux;
    }

    private void eliminarCaso3(NodoAVL nodo, NodoAVL padre) {
        NodoAVL min = nodo.getDerecho();
        NodoAVL padreAux = nodo;
        while (min.getIzquierdo() != null) {
            padreAux = min;
            min = min.getIzquierdo();
        }
        //Intercambia el contenido del nodo a borrar por el del nodo menor del subarbol derecho (NodoABB min)
        nodo.setElem(min.getElem());
        //Ahora se procede a eliminar el nodo min
        if ((min.getIzquierdo() == null) && (min.getDerecho()) == null) {
            //Si el nodo min es hoja: eliminar según el caso 1
            eliminarCaso1(min, padreAux);
        } else {
            //Si el nodo min tiene un solo hijo: eliminar según el caso 1
            eliminarCaso2(min, padreAux);
        }
    }

     public Comparable minimoElem() {
        NodoAVL nodo = this.raiz;
        Comparable minimo = null;
        while (nodo != null) {
            minimo = nodo.getElem();
            nodo = nodo.getIzquierdo();
        }
        return minimo;
    }
    
    public Comparable maximoElem() {
        NodoAVL nodo = this.raiz;
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

    private void listarAux(NodoAVL nodo, Lista lista){
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

    private void listarRangoAux(NodoAVL nodo, Lista lista, Comparable min, Comparable max){
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
    
    public ArbolAVL clone(){
        ArbolAVL clon = new ArbolAVL();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoAVL cloneAux(NodoAVL nodo){
        NodoAVL nodoClon = null;
        if(nodo != null){
            nodoClon = new NodoAVL(nodo.getElem());
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

    private String toStringAux(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            NodoAVL hijoIzqNodo = nodo.getIzquierdo();
            NodoAVL hijoDerNodo = nodo.getDerecho();
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
