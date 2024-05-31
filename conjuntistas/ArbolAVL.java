package conjuntistas;

public class ArbolAVL {
    private NodoAVL raiz;
    
    public ArbolAVL(){
        this.raiz = null;
    }
    
    public boolean pertenece(Comparable elem){
        boolean exito = false;
        exito = perteneceAux(this.raiz, elem);
        return exito;
    }
    
    private boolean perteneceAux(NodoAVL nodo, Comparable buscado){
        boolean exito = false;
        if(nodo != null){
            if(buscado.compareTo(nodo.getElem()) == 0){
                exito = true;
            }else if(buscado.compareTo(nodo.getElem()) < 0){
                exito = perteneceAux(nodo.getIzquierdo(), buscado);
            }else{
                exito = perteneceAux(nodo.getIzquierdo(), buscado);
            }
        }
        return exito;
    }
    
    public boolean insertar(Comparable elem){
        boolean exito = true;
        if(this.raiz == null){
            this.raiz = new NodoAVL(elem);
        }else{
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoAVL nodo, Comparable elem){
        //precondicion: n no es nulo
        boolean exito = true;
        if(elem.compareTo(nodo.getElem()) == 0){
            //elemento repetido
           exito = false;
        }else if(elem.compareTo(nodo.getElem()) < 0){
            //elem es menor que nodo.getElem()
            //si tiene HI baja a la izquierda, sino agrega elem
            if(nodo.getIzquierdo() == null){
                nodo.setIzquierdo(new NodoAVL(elem));
            }else{
                exito = insertarAux(nodo.getIzquierdo(), elem);
            }
        }else{
            //elem es mayor que nodo.getElem()
            //si tiene HD baja a la derecha, sino agrega elem
            if(nodo.getDerecho() == null){
                nodo.setDerecho(new NodoAVL(elem));
            }else{
                exito = insertarAux(nodo.getDerecho(), elem);
            }
        }
        
        if(exito){
            //si se pudo insertar verifica el balance del nodo actual
            balancear(nodo);
        }
        return exito;
    }
    
    private void balancear(NodoAVL nodo){
        int balanceHijo, balance = calcularBalance(nodo);
        if(balance == 2){
            //el arbol esta desbalanceado hacia la izquierda
            balanceHijo = calcularBalance(nodo.getIzquierdo());
            if(balanceHijo == -1){
                //rotacionDobleIzqDer
                rotarIzquierda(nodo.getIzquierdo());
                rotarDerecha(nodo);
            }else{
                rotarDerecha(nodo);
            }
        }
        if(balance == -2){
            //el arbol esta desbalanceado hacia la derecha
            balanceHijo = calcularB
        }
    }
    
    private int calcularBalance(NodoAVL nodo){
        int balance, alturaHI = -1, alturaHD = -1;
        if(nodo.getIzquierdo() != null){
            alturaHI = nodo.getIzquierdo().getAltura();
        }
        if(nodo.getDerecho() != null){
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
}
