package lineales.estaticas;

public class Pila{
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    public Pila(){
        arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar(Object nuevoElem) {
        boolean exito;
        if (this.tope + 1 >= Pila.TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar(){
        boolean exito;
        if(this.tope == -1){
            //Error de pila vacia
            exito = false;
        }else{
            this.arreglo[tope] = null;
            tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elem = null;
        if(this.tope > -1){
            elem = arreglo[tope];
        }
        return elem;
    }
    
    public boolean esVacia(){
        return (this.tope == -1);
    }

    public void vaciar(){
        while(this.tope >= 0){
            this.arreglo[tope] = null;
            tope--;
        }
    }

    public Pila clone(){
        Pila clon = new Pila();
        for(int i = 0; i <= this.tope; i++){
            clon.tope++;
            clon.arreglo[clon.tope] = this.arreglo[i];
        }
        return clon;
    }

    public String toString(){
        String text = "[";
        if (this.tope == -1){
            text += " La pila esta vacia";
        } else{
            for (int i = 0; i <= tope; i++) {
                text += " " + String.valueOf(this.arreglo[i]);
            }
        }
        return text + " ]";
    }
}
