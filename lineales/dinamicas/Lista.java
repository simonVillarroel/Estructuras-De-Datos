package lineales.dinamicas;


public class Lista {
    private Nodo cabecera;
    private int longitud;

    public Lista(){
        this.longitud = 0;
    }

    public boolean insertar(Object nuevoElem, int pos){
        boolean exito = true;
        if(pos < 1 || pos > this.longitud() + 1){
            exito = false;
        }else{
            if(pos == 1){
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            }else{ //Avanza hasta el elemento pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos -1){
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevoNodo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
                this.longitud++;
            }
        }
        return exito;
    }

    public boolean eliminar(int pos){
        boolean exito = false;
        if (pos >= 1 && pos <= this.longitud()) {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }
            exito = true;
        } 
        return exito;
    }

    public Object recuperar(int pos){
        Object objeto = null;
        if(pos >= 1 && pos <= this.longitud()){
            if(pos == 1){
                objeto = this.cabecera.getElem();
            }else{
                Nodo aux = this.cabecera;
                int i = 1;
                while(i < pos){
                    aux = aux.getEnlace();
                    i++;
                }
                objeto = aux.getElem();
            }
        }
        return objeto;
    }

    public int localizar(Object buscado) {
        Nodo aux = this.cabecera;
        int pos = -1, i = 1;
        boolean encontrado = false;
        while(aux != null && !encontrado){
            if(aux.getElem().equals(buscado)){
                pos = i;
                encontrado = true;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }

    public int longitud(){
        //sin atributo longitud
        /*int contador = 0;
        if(this.cabecera != null){
            Nodo aux = this.cabecera;
            while(aux != null){
                contador++;
                aux = aux.getEnlace();
            } 
        }*/
        return this.longitud;
    }

    public boolean pertenece(Object elemento){
        boolean encontrado = false;
        Nodo aux = this.cabecera;
        while(aux != null && !encontrado){
            if(aux.getElem() == elemento){
                encontrado = true;
            }
            aux = aux.getEnlace();
        }
        return encontrado;
    }

    public boolean esVacia(){
        return this.cabecera == null;
    }
    
    public void vaciar(){
        this.cabecera = null;
        this.longitud = 0;
    }

    public Lista invertir(){
        int pos = this.longitud();
        Lista invertida = new Lista();
        while (pos > 0){
            invertida.insertar(this.recuperar(pos), invertida.longitud()+1);
            pos--;
        }
        return invertida;
    }
    
    /*
    public Lista clone(){ //implementacion iterativa
        Lista clon = new Lista();
        
        if(this.cabecera != null){
            clon.cabecera = new Nodo(this.cabecera.getElem(), null);
            Nodo aux = this.cabecera;
            Nodo auxClon = clon.cabecera;
            aux = aux.getEnlace();
            while(aux != null){
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
        }
        return clon;
    }
    */

    public Lista clone(){ //implementacion recursiva
        Lista clon = new Lista();
        Nodo aux = this.cabecera;
        if (aux != null) {
            clon.cabecera = clonarNodo(aux); 
        }
        clon.longitud = this.longitud;
        return clon;
    }
    private Nodo clonarNodo(Nodo nodoActual){
        Nodo nodoNuevo = null;
        if(nodoActual != null){
            nodoNuevo = new Nodo(nodoActual.getElem(), clonarNodo(nodoActual.getEnlace()));
        }
        return nodoNuevo;
    }

    public String toString(){
        String text = "[ ";
        if (this.cabecera == null) {
            text += "La lista esta vacia ";
        } else {
            Nodo aux = this.cabecera;
            while (aux != null) {
                text += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    text += ", ";
                }
            }
        }
        return text + " ]";
    }
}
