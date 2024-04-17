package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola(){
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object nuevoElem){
        boolean exito = false;
        if(((this.fin + 1) % TAMANIO) != this.frente){
            this.arreglo[fin] = nuevoElem;
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;
        }
        return exito;
    }
    
    public boolean sacar(){
        boolean exito = true;
        if(this.esVacia()){
            exito = false;
        }else{
            this.arreglo[frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
        return exito;
    }

    public Object obtenerFrente(){
        return this.arreglo[this.frente];
    }

    public boolean esVacia(){
        return (this.frente == this.fin);
    }

    public void vaciar(){
        while(this.frente == this.fin){
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }
        this.frente= 0;
        this.fin= 0;
    }

    public Cola clone(){
        Cola clon = new Cola();
        clon.frente= this.frente;
        clon.fin= this.fin;
        int cont = this.frente;
        while(cont != this.fin) {
            clon.arreglo[cont] = this.arreglo[cont];
            cont = (cont + 1) % TAMANIO;
        }
        return clon;
    }

    public String toString(){
        String text = "[";
        int aux= this.frente;
        while (this.fin != aux) {
            text = text + " " + String.valueOf(this.arreglo[aux]);
            aux = (aux + 1) % TAMANIO;
        }
        return text + " ]";
    }    
}
