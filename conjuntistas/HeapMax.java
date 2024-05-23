package conjuntistas;

public class HeapMax {
    private Comparable[] heap;
    private int ultimo;
    private static final int TAMANIO = 20;

    public HeapMax(){
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elem) {
        boolean exito;
        if (this.ultimo <= this.TAMANIO) {
            this.ultimo++;
            this.heap[this.ultimo] = elem;
            int lugar = this.ultimo;
            int padre = lugar / 2;
            hacerSubir(padre, lugar);
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    private void hacerSubir(int padre, int lugar) {
        while (padre > 0 && this.heap[lugar].compareTo(this.heap[padre]) > 0) {
            Comparable aux = this.heap[padre];
            this.heap[padre] = this.heap[lugar];
            this.heap[lugar] = aux;
            lugar = padre;
            padre = lugar / 2;
        }
    }


    public boolean eliminarCima(){
        boolean exito;
        if(this.ultimo == 0){
            //estructura vacia
            exito = false;
        }else{
            //saca ;a raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            //reestablece la propiedd de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre){
        int posHijo;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        while (!salir){
            posHijo = posPadre * 2;
            if(posHijo <= this.ultimo){
                //temp tiene al menos un hijo (izq) y lo considera mayor
                if(posHijo < this.ultimo){
                    //hijoMayor tiene hermano derecho
                    if(this.heap[posHijo +1 ].compareTo(this.heap[posHijo]) > 0){
                        //el hijo derecho es el mayor de los dos
                        posHijo++;
                    }
                }
                //compara al hijo mayor con el padre
                if(this.heap[posHijo].compareTo(temp) > 0){
                    //el hijo es mayor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posHijo];
                    this.heap[posHijo] = temp;
                    posPadre = posHijo;
                }else{
                    //el padre es mayor que sus hijos, esta bien ubicado
                    salir = true;
                }
            }else{
                //el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Object recuperarCima(){
        return this.heap[1];
    }

    public boolean esVacio(){
        return this.heap[1] == null;
    }

    public void vaciar(){
        this.ultimo = 0;
    }

    public HeapMax clone(){
        HeapMax clon = new HeapMax();
        for(int i = 1; i <= this.ultimo; i++){
            clon.heap[i] = this.heap[i];
            clon.ultimo++;
        }
        return clon;
    }

    public String toString(){
        String cadena = "";
        if(this.esVacio()){
            cadena = "Arbol Vacio";
        }else{
            for(int i = 1; i <= this.ultimo; i++){
                if((i*2)+1 <= this.ultimo && this.heap[(i*2)+1] != null){
                    cadena += this.heap[i] + ": HI--> " + this.heap[i*2] + ", HD--> " + this.heap[(i*2)+1] + "\n"; 
                }else{
                    if((i*2)+1 <= this.ultimo && this.heap[i*2] != null){
                    cadena += this.heap[i] + ": HI--> " + this.heap[i*2] + ", HD--> -\n";    
                    }else{
                        cadena += this.heap[i] + ": HI--> -, HD--> -\n";
                    }
                }
            }
        }
        return cadena;
    }
}
