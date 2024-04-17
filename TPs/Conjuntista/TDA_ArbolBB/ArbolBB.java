package Conjuntista.TDA_ArbolBB;

import Dinamica.TDA_Lista.Lista;

public class ArbolBB {
    
    private NodoABB raiz;

    public ArbolBB() {
        this.raiz = null;
    }
    
    public Comparable mejorCandidato(Comparable elem){
        Comparable res = mejorCandidatoAux(this.raiz, elem);
        return res;
    }
    
    private Comparable mejorCandidatoAux(NodoABB nodo, Comparable elem){
        Comparable res;
        if (nodo != null) {
            //comparo elem con nodo.getElem() y almaceno el resultado en la variable comp
            int comp = elem.compareTo(nodo.getElem());
            //si coinciden la busqueda culmina con exito
            if (comp == 0) {
                res = calcularCercano(nodo); 
            } else {
                if (comp < 0) {
                    //si comp es menor a 0, la busqueda continua por subarbol izquierdo
                    res = mejorCandidatoAux(nodo.getIzquierdo(), elem);
                } else {
                    //si comp es mayor a 0, la busqueda continua por subarbol derecho
                    res = mejorCandidatoAux(nodo.getDerecho(), elem);
                }
            }
        } else {
            //si se llega a un nulo la busqueda culmina sin exito
            res = -1;
        }
        return res;
    }
    
    private Comparable calcularCercano(NodoABB nodo){
        NodoABB aux = nodo;
        int original = (int) nodo.getElem(), n1, n2, diferencia1, diferencia2, res;
        nodo = nodo.getDerecho();
        aux = aux.getIzquierdo();
                while (nodo.getIzquierdo() != null) {
                    nodo = nodo.getIzquierdo();
                }
                n1 = (int) nodo.getElem();
                while (aux.getDerecho() != null) {
                    aux = aux.getDerecho();
                }
                n2 = (int) nodo.getElem();
                diferencia1 = Math.abs(original - n1);
                diferencia2 = Math.abs(original - n2);
                if(diferencia1 < diferencia2){
                    res = n1;
                }else{
                    res = n2;
                }
                return res;
    }

    public boolean pertenece(Comparable elem) {
        boolean exito = perteneceAux(elem, this.raiz);
        return exito;
    }
    private boolean perteneceAux(Comparable elem, NodoABB nodo) {
        boolean exito;
        if (nodo != null) {
            //comparo elem con el del nodo en el que estoy parado y almaceno el resultado en la variable comp
            int comp = elem.compareTo(nodo.getElem());
            //si coinciden la busqueda culmina con exito
            if (comp == 0) {
                exito = true;
            } else {
                if (comp < 0) {
                    //si comp es menor a 0, la busqueda continua por subarbol izquierdo
                    exito = perteneceAux(elem, nodo.getIzquierdo());
                } else {
                    //si comp es mayor a 0, la busqueda continua por subarbol derecho
                    exito = perteneceAux(elem, nodo.getDerecho());
                }
            }
        } else {
            //si se llega a un nulo la busqueda culmina sin exito
            exito = false;
        }
        return exito;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }
    private boolean insertarAux(NodoABB nodo, Comparable elem) {
        //precondicion, nodo no es nulo
        boolean exito = true;
        if (nodo != null) {
            if (elem.compareTo(nodo.getElem()) == 0) {
                //Reportar error: elemento repetido
                exito = false;
            } else if (elem.compareTo(nodo.getElem()) < 0) {
                //elem es menor que nodo.getElem()
                //Si tiene HI baja a la izquierda, sino agrega elem
                if (nodo.getIzquierdo() != null) {
                    exito = insertarAux(nodo.getIzquierdo(), elem);
                } else {
                    nodo.setIzquierdo(new NodoABB(elem, null, null));
                }
            } else {
                //elem es mayor que nodo.getElem()
                //Si tiene HD baja a la derecha, sino agrega elem
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), elem);
                } else {
                    nodo.setDerecho(new NodoABB(elem, null, null));
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        boolean resultado = eliminarAux(elem, this.raiz, null);
        return resultado;
    }
    public boolean eliminarAux(Comparable elem, NodoABB nodo, NodoABB padre) {
        boolean resultado = false, encontrado = false;

        while (nodo != null && encontrado == false) {
            //Comparo el elemento a eliminar con el elemento en el nodo actual del arbol
            if (elem.compareTo(nodo.getElem()) == 0) {
                encontrado = true;
            } else {
                if (elem.compareTo(nodo.getElem()) < 0) {
                    //Si comp es menor a 0 avanzo hacia el subárbol izquierdo
                    padre = nodo;
                    nodo = nodo.getIzquierdo();
                } else {
                    //Si comp es mayor a 0 avanzo hacia el subárbol derecho
                    padre = nodo;
                    nodo = nodo.getDerecho();
                }
            }
        }
        if (encontrado) {
            //Si encontro el elemento
            if ((nodo.getIzquierdo() == null) && (nodo.getDerecho()) == null) {
                //Si es hoja: eliminar según el caso 1
                eliminarCaso1(nodo, padre);
            } else {
                if ((nodo.getIzquierdo() != null) && (nodo.getDerecho()) != null) {
                    //Si tiene ambos hijos: eliminar según el caso 3
                    eliminarCaso3(nodo, padre);
                } else {
                    //Si tiene un hijo: eliminar según el caso 2
                    eliminarCaso2(nodo, padre);
                }
            }
            resultado = true;
        } else {
            //Si encontro un subarbol vacio, la operacion culmina sin exito y se devuelve error
            resultado = false;
        }
        return resultado;
    }

    private void eliminarCaso1(NodoABB nodo, NodoABB padre) {
        if (padre == null) {
            //En el caso de que el nodo hoja sea la raiz
            this.raiz = null;
        } else {
            if (padre.getIzquierdo() == nodo) {
                //Si el nodo hoja que elimino es HI de su padre
                padre.setIzquierdo(null);
            } else {
                //Si el nodo hoja que elimino es HD de su padre
                padre.setDerecho(null);
            }
        }
    }

    private void eliminarCaso2(NodoABB nodo, NodoABB padre) {
        if (padre == null) {
            //En el caso de que se quiera eliminar la raiz (la cual tiene un solo hijo)
            if (nodo.getIzquierdo() != null) {
                //Si el hijo que tiene es izquierdo, entonces HI pasa a ser la raiz 
                this.raiz = nodo.getIzquierdo();
            } else {
                //Si el hijo que tiene es derecho, entonces HD pasa a ser la raiz
                this.raiz = nodo.getDerecho();
            }
        } else {
            //En el caso de que el nodo que se quiere borrar no sea la raiz
            if (padre.getIzquierdo() == nodo) {
                if (nodo.getIzquierdo() != null) {
                    //Si el nodo que elimino es HI de su padre y a su vez tiene hijo izquierdo
                    padre.setIzquierdo(nodo.getIzquierdo());
                } else {
                    //Si el nodo que elimino es HI de su padre y a su vez tiene hijo derecho
                    padre.setIzquierdo(nodo.getDerecho());
                }
            } else {
                if (nodo.getIzquierdo() != null) {
                    //Si el nodo que elimino es HD de su padre y a su vez tiene hijo izquierdo
                    padre.setDerecho(nodo.getIzquierdo());
                } else {
                    //Si el nodo que elimino es HD de su padre y a su vez tiene hijo derecho
                    padre.setDerecho(nodo.getDerecho());
                }
            }
        }
    }

    private void eliminarCaso3(NodoABB nodo, NodoABB padre) {
        NodoABB minimo = nodo.getDerecho();
        padre = nodo;
        //busca el nodo menor del subarbol derecho
        while (minimo.getIzquierdo() != null) {
            padre = minimo;
            minimo = minimo.getIzquierdo();
        }
        //Intercambia el contenido del nodo a borrar por el del nodo menor del subarbol derecho (NodoABB minimo)
        nodo.setElem(minimo.getElem());
        //Ahora se procede a eliminar el nodo minimo
        if ((minimo.getIzquierdo() == null) && (minimo.getDerecho()) == null) {
            //Si el nodo min es hoja: eliminar según el caso 1
            eliminarCaso1(minimo, padre);
        } else {
            //Si el nodo min tiene un solo hijo: eliminar según el caso 2
            eliminarCaso2(minimo, padre);
        }
    }

    public Lista listar() {
        Lista salida = new Lista();
        salida = listarAux(this.raiz, salida);
        return salida;
    }
    private Lista listarAux(NodoABB nodo, Lista lista) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lista);
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            listarAux(nodo.getDerecho(), lista);
        }
        return lista;
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista salida = new Lista();
        salida = listarRangoAux(this.raiz, salida, min, max);
        return salida;
    }
    private Lista listarRangoAux(NodoABB nodo, Lista lista, Comparable min, Comparable max) {
        if (nodo != null){
            if ((min.compareTo(nodo.getElem()) <= 0) || (max.compareTo(nodo.getElem()) <= 0)) {
                //Si el minimo o el maximo son menores al elemento del nodo, bajo al subarbol izquierdo
                listarRangoAux(nodo.getIzquierdo(), lista, min, max);
            }
            if ((min.compareTo(nodo.getElem()) <= 0) && (max.compareTo(nodo.getElem()) >= 0)) {
                //Si el minimo es menor y el maximo mayor al elemento del nodo, éste se debe insertar en la lista
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
            if ((min.compareTo(nodo.getElem()) >= 0) || (max.compareTo(nodo.getElem()) >= 0)) {
                //Si el minimo o el maximo son menores al elemento del nodo, bajo al subarbol derecho 
                listarRangoAux(nodo.getDerecho(), lista, min, max);
            }
        }
        return lista;
    }

    public Comparable minimoElem() {
        NodoABB nodo = this.raiz;
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return (Comparable) nodo.getElem();
    }
    
    public Boolean eliminarMinimo() {
        boolean resultado = false;
        NodoABB nodo = this.raiz;
        NodoABB padre = null;
        if(nodo != null){
            while (nodo.getIzquierdo() != null) {
                padre = nodo;
                nodo = nodo.getIzquierdo();
            }
            padre.setIzquierdo(null);
            resultado = true;
        }
        return resultado;
    }

    public Comparable maximoElem() {
        NodoABB nodo = this.raiz;
        while (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
        }
        return (Comparable) nodo.getElem();
    }
    
    public Boolean eliminarMaximo() {
        boolean resultado = false;
        NodoABB nodo = this.raiz;
        NodoABB padre = null;
        if(nodo != null){
            while (nodo.getDerecho() != null) {
                padre = nodo;
                nodo = nodo.getDerecho();
            }
            padre.setDerecho(null);
            resultado = true;
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBB clone(){
        ArbolBB clon=null;
        if(this.raiz == null){
            System.out.println("Arbol vacío");
        }else{
            clon = cloneAux(this.raiz);
        }
        return clon;
    }
 
    private ArbolBB cloneAux(NodoABB nodo){
        NodoABB clon;
        if(nodo == null){
            clon = null;
        }else{
            ArbolBB izqClon, derClon;
            izqClon = cloneAux(nodo.getIzquierdo());
            derClon = cloneAux(nodo.getDerecho());
            clon = new NodoABB(nodo.getElem(), null, null);
            clon.setIzquierdo(izqClon.raiz);
            clon.setDerecho(derClon.raiz);
        }
        ArbolBB nuevo = new ArbolBB();
        nuevo.raiz= clon;
        return nuevo;
    }

    public String toString() {
        String resultado = "El arbol esta vacio.";
        NodoABB aux = this.raiz;
        if (this.raiz != null) {
            resultado = toStringAux(aux);
        }
        return resultado;
    }

    private String toStringAux(NodoABB nodo) {
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
}
