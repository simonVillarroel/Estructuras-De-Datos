package tests.lineales.estaticas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

public class MixLineales {
    public static void main(String[] args){
        Cola cola1 = new Cola(), cola2;
        llenar(cola1);
        cola2 = generarOtraCola(cola1);
        System.out.println("Cola original: "+ cola1.toString());
        System.out.println("Cola generada: "+ cola2.toString());
    }

    public static Cola generarOtraCola(Cola cola){
        Cola colaGenerada = new Cola(), clon = cola.clone();
        Pila pila = new Pila();
        char elemento;
        while(!clon.esVacia()){
            elemento = (char) clon.obtenerFrente();
            if(elemento != '$'){
                colaGenerada.poner(elemento);
                pila.apilar(elemento);
                clon.sacar();
            }else{
                while(!pila.esVacia()){ 
                    //copia el contenido de la pila en la cola generada y desapila por completo
                    colaGenerada.poner(pila.obtenerTope());
                    pila.desapilar();
                }
                if(!clon.esVacia()){
                    colaGenerada.poner('$');
                    clon.sacar(); //saca '$'
                }
            }
        }
        return colaGenerada;
    }

    public static void llenar(Cola cola){
        cola.poner('A');
        cola.poner('B');
        cola.poner('$');
        cola.poner('C');
        cola.poner('$');
        cola.poner('D');
        cola.poner('E');
    }
}
