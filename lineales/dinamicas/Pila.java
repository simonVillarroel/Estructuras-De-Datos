package lineales.dinamicas;

public class Pila {
    private Nodo tope;

    public Pila(){
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        Nodo nuevoNodo = new Nodo(nuevoElem, this.tope);
        this.tope = nuevoNodo;
        return true;
    }

    public boolean desapilar(){
        boolean exito = false;
        if(this.tope != null){
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elem = null;
        if(this.tope != null){
            elem = this.tope.getElem();
        }
        return elem;
    }
    
    public boolean esVacia(){
        return (this.tope == null);
    }

    public void vaciar(){
        this.tope = null;
    }

    public Pila clone(){
        Pila clon = new Pila();
        clon.tope = new Nodo(this.tope.getElem(), null);
        Nodo aux = this.tope;
        Nodo auxClon = clon.tope;
        aux = aux.getEnlace();
        while (aux != null){
            auxClon.setEnlace(new Nodo(aux.getElem(), null));
            auxClon = auxClon.getEnlace();
            aux = aux.getEnlace();
        }
        return clon;
    }

    public String toString(){
        String text = "]";
        if (this.tope == null) {
            text = "La pila esta vacia" + text;
        } else {
            Nodo aux = this.tope;
            while (aux != null) {
                text = aux.getElem().toString() + text;
                aux = aux.getEnlace();
                if (aux != null) {
                    text = " " + text;
                }
            }
        }
        return "[" + text ;
    }
}
