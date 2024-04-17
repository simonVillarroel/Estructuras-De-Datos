package Conjuntista.TDA_Heap;

public class Heap {
    private static final int tamanio = 50;
    private Comparable[] heap;
    private int ultimo;

    public Heap() {
        this.heap = new Comparable[tamanio - 1];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable nuevo){
        boolean exito;
        if (this.ultimo <= this.tamanio) {
            this.ultimo++;
            this.heap[this.ultimo] = nuevo;
            int lugar = this.ultimo;
            int padre = lugar / 2;
            hacerSubir(padre, lugar);
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public void hacerSubir(int padre, int lugar) {
        //mientras la posicion del padre sea mayor a 0 y el valor de heap[lugar] sea menor a heap[padre]
        while (padre > 0 && this.heap[lugar].compareTo(this.heap[padre]) < 0) {
            Comparable aux = this.heap[padre];
            this.heap[padre] = this.heap[lugar];
            this.heap[lugar] = aux;
            lugar = padre;
            padre = lugar / 2;
        }
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            //estructura vacia
            exito = false;
        } else {
            //saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.heap[ultimo] = null;
            this.ultimo--;
            //reestablece la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                //temp tiene al menos un hijo (izq) y lo considera menor
                if (posH < this.ultimo) {
                    //hijoMenor tiene hermano derecho
                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        //el hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                //compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    //el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    //el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                //el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public Comparable recuperarCima() {
        Comparable res = null;
        if (this.ultimo > 0) {
            res = this.heap[1];
        }
        return res;
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    public Heap clone() {
        Heap nuevo = new Heap();
        for (int cont = 1; cont <= this.ultimo; cont++) {
            nuevo.heap[cont] = this.heap[cont];
            nuevo.ultimo++;
        }
        return nuevo;
    }

    public String toString() {
        String text = "";
        if (this.ultimo == 0) {
            text += "La pila esta vacia";
        } else {
            for (int i = 1; i <= ultimo; i++) {
                text += "\n" + this.heap[i] + ": HI--> " + this.heap[2 * i] + " HD--> " + this.heap[(2 * i) + 1];
            }
        }
        return text;
    }
}
