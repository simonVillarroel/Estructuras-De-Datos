package Conjuntista.TDA_Hash.Cerrado;

import Conjuntista.TDA_Hash.FuncionesHash;
import Dinamica.TDA_Lista.Lista;

public class TablaHash {
    private final static int TAMANIO = 10;
    private CeldaHash[] tabla;
    private int cant;

    public TablaHash(){
        this.tabla = new CeldaHash[TAMANIO];
        for(int i = 0; i < this.TAMANIO ; i++){
            this.tabla[i] = new CeldaHash();
        }
        this.cant = 0;
    }

    public boolean insertar(Object elemento){
        boolean exito = false;
        int intento = 1;
        //Calcula posicion inicial e incremento
        int pos = FuncionesHash.hashEnteroUltimoDigito(elemento) % this.TAMANIO;
        int incremento = FuncionesHash.reHashsumarDigitos(elemento) % this.TAMANIO;
        System.out.println("pos"+ pos);
        System.out.println("inc"+ incremento);
        //Busca una celda vacia hasta encontrarla o para despues de TAMANIO intentos
        while(!exito && intento < this.TAMANIO){
            if(this.tabla[pos].getEstado() == 0 || this.tabla[pos].getEstado() == -1){
                this.tabla[pos].setElemento(elemento);
                exito = true;
                this.cant++;
            }
            pos = (pos + intento * incremento) % this.TAMANIO;
            System.out.println("repos"+ pos);
            intento++;
        }
        return exito;
    }

    public boolean pertenece(Object elemento){
        boolean encontrado = false;
        int intento = 1;
        int pos = FuncionesHash.hashEnteroUltimoDigito(elemento) % this.TAMANIO;
        int incremento = FuncionesHash.reHashsumarDigitos(elemento) % this.TAMANIO;
        while(!encontrado && intento < this.TAMANIO && this.tabla[pos].getEstado() != 0){
            if(this.tabla[pos].getEstado() == 1){
                encontrado = this.tabla[pos].getElemento() == elemento;
            }
            pos = (pos + intento * incremento) % this.TAMANIO;
            intento++;
        }
        return encontrado;
    }

    public boolean eliminar(Object buscado){
        //Calcula posicion inicial e incremento
        int pos = FuncionesHash.hashEnteroUltimoDigito(buscado) % this.TAMANIO;
        int incremento = FuncionesHash.reHashsumarDigitos(buscado) % this.TAMANIO;
        boolean encontrado = false;
        int intento = 1;
        //Busca el elemento hasta encontrarlo o encontrar una celda vacia
        //o para despues de TAMANIO intentos
        while(!encontrado && intento < this.TAMANIO && this.tabla[pos].getEstado() != 0){
            if(this.tabla[pos].getEstado() == 1){
                encontrado = this.tabla[pos].getElemento() == buscado;
                if(encontrado){
                    //Si lo encuentra lo marca y para el ciclo
                    this.tabla[pos].setElemento(null);
                    this.cant--;
                }
            }
            pos = (pos + intento * incremento) % this.TAMANIO;
            intento++;
        }
        return encontrado;
    }

    public boolean esVacia(){
        return this.cant == 0;
    }

    public void vaciar(){
        for(int i = 0; i < this.TAMANIO ; i++){
            this.tabla[i] = null;
        }
    }

    public Lista listar(){
        Lista lista = new Lista();
        for(int i = 0; i < TAMANIO; i++) {
            if(this.tabla[i].getElemento() != null){
                lista.insertar(this.tabla[i].getElemento(), lista.longitud() + 1);
            }
        }
        return lista;
    }

    public TablaHash clone(){
        TablaHash clon = new TablaHash();
        for (int i = 0; i < TAMANIO; i++) {
            clon.tabla[i].setElemento(this.tabla[i].getElemento());
            clon.cant++;
        }
        return clon;
    }
    
    public String toString(){
        String text = "";
        if(this.esVacia()){
            text = "La tabla esta vacia";
        }else{
            for (int i = 0; i < TAMANIO; i++) {
                if(this.tabla[i].getEstado() == 1){
                    text += "Contenido bucket "+ i + ": " + this.tabla[i].getElemento().toString() +'\n';
                }
            }
        }
        return text;
    }
}
