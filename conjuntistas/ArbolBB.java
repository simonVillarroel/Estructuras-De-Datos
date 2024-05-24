package conjuntistas;

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
            if (buscado.compareTo(this.raiz.getElem()) == 0) {
                exito = true;
            } else {
                if (buscado.compareTo(this.raiz.getElem()) < 0) {
                    exito = perteneceAux(nodo.getIzquierdo(), buscado);
                } else if(buscado.compareTo(nodo.getElem()) > 0){
                    exito = perteneceAux(nodo.getDerecho(), buscado);
                }
            }
        }
        return exito;
    }
    
    public boolean insertar(Comparable elem){
        boolean exito = false;
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
}
