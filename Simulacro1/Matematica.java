package Simulacro1;

import lineales.dinamicas.Pila;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

public class Matematica {
    //EJERCICIO TIPO 2 (d)
    public static boolean verificarBalanceo(Cola cola){
        boolean balanceada = true;
        Pila pila = new Pila();
        Cola colaAux = cola.clone();
        char frente;
        while(!colaAux.esVacia() && balanceada){
            frente = (char) colaAux.obtenerFrente();
            switch(frente){
                case '{':
                    pila.apilar('}');
                    break;
                case '(':
                    pila.apilar(')');
                    break;
                case '[':
                    pila.apilar(']');
                    break;
                case '}':
                case ')':
                case ']':
                    if(!pila.esVacia() && pila.obtenerTope().equals(frente)){
                        pila.desapilar();
                    }else{
                        balanceada = false;
                    }
                    break;
                default:
                    break;
            }
            colaAux.sacar();
        }
        if(!pila.esVacia()){
            balanceada = false;
        }
        return balanceada;
    }

    
    public static void main(String[] args) {
        boolean balanceada;
        Cola cola = new Cola();
        cola.poner('{'); cola.poner('5'); cola.poner('+'); cola.poner('[');    
        cola.poner('8'); cola.poner('*'); cola.poner('9'); cola.poner('-');
        cola.poner('('); cola.poner('4'); cola.poner('/'); cola.poner('2');
        cola.poner(')'); cola.poner('+'); cola.poner('7'); cola.poner(']');
        cola.poner('-'); cola.poner('1'); cola.poner('}');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion {5+[8*9-(4/2)+7]-1} esta balanceada, debe devolver TRUE: " + balanceada);
        cola.vaciar();

        cola.poner('{'); cola.poner('5'); cola.poner('+'); cola.poner('8');    
        cola.poner('*'); cola.poner('9'); cola.poner('-'); cola.poner('('); 
        cola.poner('4'); cola.poner('/'); cola.poner('2'); cola.poner(')');    
        cola.poner('+'); cola.poner('7'); cola.poner(']'); cola.poner('-');    
        cola.poner('1'); cola.poner('}');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion {5+8*9-(4/2)+7]-1} esta balanceada, debe devolver FALSE: " + balanceada);
        cola.vaciar();

        cola.poner('('); cola.poner('['); cola.poner(']'); cola.poner('[');
        cola.poner(']'); cola.poner('{'); cola.poner('('); cola.poner(')');
        cola.poner('}'); cola.poner(')');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion ([][]{()}) esta balanceada, debe devolver TRUE: " + balanceada);
        cola.vaciar();

        cola.poner('('); cola.poner('('); cola.poner(')'); cola.poner('(');    
        cola.poner(')'); cola.poner('('); cola.poner('('); cola.poner(')');   
        cola.poner(')'); cola.poner(')'); cola.poner(')');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion (()()(()))) esta balanceada, debe devolver FALSE: " + balanceada);
        cola.vaciar();

        cola.poner('('); cola.poner('a');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion (a esta balanceada, debe devolver FALSE: " + balanceada);
        cola.vaciar();

        cola.poner('a'); cola.poner(']');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion a] esta balanceada, debe devolver FALSE: " + balanceada);
        cola.vaciar();

        cola.poner('['); cola.poner('{'); cola.poner('('); cola.poner(')');
        cola.poner('}'); cola.poner(']');
        balanceada = verificarBalanceo(cola);
        System.out.println("La operacion a] esta balanceada, debe devolver FALSE: " + balanceada);
        cola.vaciar();
    }
}
