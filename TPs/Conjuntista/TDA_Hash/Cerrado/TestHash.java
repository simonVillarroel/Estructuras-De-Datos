package Conjuntista.TDA_Hash.Cerrado;

import Dinamica.TDA_Lista.Lista;
import Estatica.TecladoIn;

public class TestHash {
    public static void main(String[] args){
        
        TablaHash tabla = new TablaHash();
        TablaHash clon = new TablaHash();
        Object n;
        Lista lista = new Lista();
        int opcion;
        boolean sigue = true;
        
        while (sigue == true) {
            System.out.println("Ingrese el numero correspondiente a la accion que desea realizar.");
            opcion = menu();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el elemento que desea agregar a la tabla.");
                    n = TecladoIn.readInt();     
                    if(tabla.insertar(n)){
                        System.out.println("Se ha ingresado correctamente."+'\n');
                    }else {
                        System.out.println("ERROR"+'\n');
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el elemento que desea eliminar de la tabla");
                    n = TecladoIn.readInt();
                    if(tabla.eliminar(n)){
                        System.out.println("Se ha eliminado correctamente"+'\n');
                    } else {
                        System.out.println("No se ha podido eliminar correctamente"+'\n');
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el elemento que desea verificar si pertenece a la tabla");
                    n = TecladoIn.readInt();
                    System.out.println("El elemento "+ n + " pertenece a la tabla: "+ tabla.pertenece(n) +'\n');
                    break;
                case 4:
                    lista = tabla.listar();
                    System.out.println(lista.toString() +'\n');
                    break;
                case 5:
                    System.out.println("La tabla se encuentra vacia: "+ tabla.esVacia() +'\n');
                    break;
                case 6:
                    tabla.vaciar();
                    System.out.println("Se ha vaciado correctamente."+'\n');
                    break;
                case 7:
                    clon = tabla.clone();
                    System.out.println("El arbol se ha clonado correctamente." + '\n');
                    break;
                case 8:
                    System.out.println(tabla.toString());
                    System.out.println('\n');  
                    break;
                case 9:
                    System.out.println(clon.toString());
                    System.out.println('\n');  
                    break;
                case 10:
                    sigue= false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada." + '\n');
            }
        }
    }

    public static int menu() {
        int opcion;
        System.out.println("1: Insertar un elemento a la tabla." + '\n'
                + "2: Eliminar un elemento de la tabla." + '\n'
                + "3: Verificar si un elemento pertenece la tabla." + '\n'
                + "4: Generar una lista completa de la tabla." + '\n'
                + "5: Verificar si la tabla se encuentra vacia." + '\n'
                + "6: Vaciar la tabla." + '\n'
                + "7: Clonar la tabla." + '\n'
                + "8: Imprimir la tabla." + '\n'
                + "9: Imprimir la tabla clonada." + '\n'
                + "10: Terminar de operar.");
        opcion = TecladoIn.readInt();
        return opcion;
    }
}
