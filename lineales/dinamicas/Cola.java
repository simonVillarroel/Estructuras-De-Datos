package lineales.dinamicas;

public class Cola {
    private Nodo frente;
    private Nodo fin;
    
    public Cola(){
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object nuevoElem){
        Nodo nuevoNodo = new Nodo(nuevoElem, null); 
        if(this.frente == null){
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
        }else{
            this.fin.setEnlace(nuevoNodo);
            this.fin = this.fin.getEnlace();
        }
        return true;
    }
    
    public boolean sacar(){
        boolean exito = true;
        if(this.frente == null){
            exito = false;
        }else{
            this.frente = this.frente.getEnlace();
            if(this.frente == null){
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        Object elem;
        if (this.frente != null) {
            elem = this.frente.getElem();
        } else {
            elem = null;
        }
        return elem;
    }

    public boolean esVacia(){
        return (this.frente == null);
    }

    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }

    public Cola clone(){
        Cola clon = new Cola();
        if (this.frente != null) {
            clon.frente = new Nodo(this.frente.getElem(), null);
            Nodo aux1 = this.frente;
            Nodo aux2 = clon.frente;
            aux1 = aux1.getEnlace();
            while (aux1 != null) {
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                clon.fin = aux2;
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
        }
        return clon;
    }

    public String toString(){
        String text = "[";
        if (this.frente == null) {
            text = text + "La cola esta vacia";
        } else {
            Nodo aux = this.frente;
            while (aux != null) {
                text += " " + String.valueOf(aux.getElem());
                aux = aux.getEnlace();
            }
        }
        return text + " ]";
    }
}
