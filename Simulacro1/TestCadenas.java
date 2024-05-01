package Simulacro1;

import lineales.dinamicas.Pila;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class TestCadenas {
    //EJERCICIO TIPO 2 (c)
    public static void main(String[] args) {
        Cola cola = new Cola();
        Cola cola2;
        cola.poner('A');
        cola.poner('B');
        cola.poner('#');
        cola.poner('C');
        cola.poner('#');
        cola.poner('D');
        cola.poner('E');
        cola.poner('F');
        System.out.println(cola.toString());

        cola2 = generar(cola);
        System.out.println(cola2.toString());
    }

    public static Cola generar(Cola cola){
        Cola colaGenerada = new Cola(), clon = cola.clone();
        Pila pila = new Pila();
        Cola colaAux = new Cola();
        char elemento;
        while(!clon.esVacia()){
            elemento = (char) clon.obtenerFrente();
            if(elemento != '#'){
                colaGenerada.poner(elemento);
                pila.apilar(elemento);
                colaAux.poner(elemento);
                clon.sacar();
            }else{
                while(!pila.esVacia()){ 
                    //copia el contenido de la pila en la cola generada y desapila por completo
                    colaGenerada.poner(pila.obtenerTope());
                    pila.desapilar();
                }
                while(!colaAux.esVacia()){
                    //copia el contenido de la colaAux en la cola generada y vacia por completo
                    colaGenerada.poner(colaAux.obtenerFrente());
                    colaAux.sacar();
                }
                if(!clon.esVacia()){
                    colaGenerada.poner('#');
                    clon.sacar(); //saca '#'
                }
            }
        }
        //Para el el resto de la cola luego del ultimo simbolo #
        //copio el contenido de la pila y la colaAux luego de borrar el ultimo # de la cola original
        while(!pila.esVacia()){ 
            colaGenerada.poner(pila.obtenerTope());
            pila.desapilar();
        }
        while(!colaAux.esVacia()){
            colaGenerada.poner(colaAux.obtenerFrente());
            colaAux.sacar();
        }
        return colaGenerada;
    }
}
