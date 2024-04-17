package tests.lineales.dinamicas;

import lineales.dinamicas.Lista;
import utiles.TecladoIn;

public class TestLista {
    
    public static void main(String[] args) {
        Lista lista = new Lista();
        Lista clon = new Lista();
        int opcion, pos;
        boolean sigue = true;
        Object elem;

        while (sigue == true) {
            opcion = menu();
            switch (opcion) {
                case 1: //insertar(int)
                    System.out.println("Ingrese el elemento que desea insertar.");
                    elem = TecladoIn.readInt();
                    System.out.println("Ingrese la posicion en la que desea insertar.");
                    pos = TecladoIn.readInt();
                    lista.insertar(elem, pos);
                    System.out.println("Se ha insertado correctamente" + '\n');
                    break;
                case 2: //insertar(Object)
                    System.out.println("Ingrese el elemento que desea insertar.");
                    elem = TecladoIn.read();
                    System.out.println("Ingrese la posicion en la que desea insertar.");
                    pos = TecladoIn.readInt();
                    lista.insertar((int) elem, pos);
                    System.out.println("Se ha insertado correctamente" + '\n');
                    break;
                case 3: //eliminar()
                    System.out.println("Ingrese la posicion del elemento que desea eliminar");
                    pos = TecladoIn.readInt();
                    if (lista.eliminar(pos)) {
                        System.out.println("Se ha eliminado correctamente." + '\n');
                    } else {
                        System.out.println("No se ha podido eliminar." + '\n');
                    }
                    break;
                case 4: //recuperar()
                    System.out.println("Ingrese la posicion del elemento que desea recuperar");
                    pos = TecladoIn.readInt();
                    Object objeto =lista.recuperar(pos);
                    if (objeto != null) {
                        System.out.println("El elemento de la posicion "+ pos + " es: "+ objeto + '\n');
                    } else {
                        System.out.println("ERROR." + '\n');
                    }
                    break;
                case 5: //localizar()
                    System.out.println("Ingrese el elemento que desea localizar");
                    elem = TecladoIn.read();
                    pos = lista.localizar(elem);
                    if (pos != 0) {
                        System.out.println("El elemento " + elem + " se encuentra en la posicion: " + pos + '\n');
                    } else {
                        System.out.println("ERROR" + '\n');
                    }
                    break;
                case 6: //longitud()
                    System.out.println("Longitud de la lista: " + lista.longitud());
                    break;
                case 7: //esVacia()
                    System.out.println("La lista esta vacia: " + lista.esVacia() + '\n');
                    break;
                case 8: //vaciar()
                    lista.vaciar();
                    System.out.println("La lista se ha vaciado.");
                    break;
                case 9: //clone()
                    clon = lista.clone();
                    System.out.println("La lista se ha clonado." + '\n');
                    break;
                case 10: //toString()
                    System.out.println("Lista original: " + lista.toString());
                    System.out.println("Lista clonada: " + clon.toString() + '\n');
                    break;
                case 0:
                    sigue = false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada." + '\n');
            }
        }
    }

    public static int menu() {
        //Muestra el menu de operaciones que se pueden realizar sobre una pila
        int opcion;
        System.out.println("Ingrese el numero correspondiente a la accion que desea realizar." + '\n'
                + "1: Insertar int." + '\n'
                + "2: Insertar Object." + '\n'
                + "3: Eliminar." + '\n'
                + "4: Recuperar elemento." + '\n'
                + "5: Localizar elemento." + '\n'
                + "6: Verificar longitud de la lista." + '\n'
                + "7: Verificar si la lista esta vacia." + '\n'
                + "8: Vaciar la lista." + '\n'
                + "9: Clonar la lista." + '\n'
                + "10: Imprimir pila original y clonada." + '\n'
                + "0: Terminar.");
        opcion = TecladoIn.readInt();
        return opcion;
    }

}
