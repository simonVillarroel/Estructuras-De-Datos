package Conjuntista.TDA_ArbolAVL;

import Dinamica.TDA_Lista.Lista;
import Estatica.TecladoIn;

public class TestAVL {
    public static void main(String[] args){
        
        ArbolAVL arbol = new ArbolAVL();
        ArbolAVL clon = new ArbolAVL();
        Comparable  min, max;
        int n;
        Lista ls= new Lista();
        int opcion;
        boolean sigue = true;
        

        while (sigue == true) {
            System.out.println("Ingrese el numero correspondiente a la accion que desea realizar.");
            opcion = menu();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el elemento que desea agregar al arbol.");
                    n = TecladoIn.readInt();
                    if(arbol.insertar(n)){
                        System.out.println("Se ha ingresado correctamente."+'\n');
                    }else {
                        System.out.println("ERROR"+'\n');
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el elemento que desea eliminar del arbol");
                    n = TecladoIn.readInt();
                    if(arbol.eliminar(n)){
                        System.out.println("Se ha eliminado correctamente"+'\n');
                    } else {
                        System.out.println("No se ha podido eliminar correctamente"+'\n');
                    }
                    break;
                case 3:
                    ls = arbol.listar();
                    System.out.println(ls.toString()+'\n');
                    break;
                case 4:
                    System.out.println("Ingrese el elemento minimo de la lista que desea generar.");
                    min = TecladoIn.readInt();
                    System.out.println("Ingrese el elemento maximo de la lista que desea generar.");
                    max = TecladoIn.readInt();
                    ls = arbol.listarRango(min, max);
                    System.out.println('\n'+ls.toString()+'\n');
                    break;
                case 5:
                    System.out.println("El menor elemento del arbol es: " + arbol.minimoElem() + "." + '\n');
                    break;
                case 6:
                    System.out.println("El mayor elemento del arbol es: " + arbol.maximoElem() + "." + '\n');                    
                    break;
                case 7:
                    if(arbol.esVacio()){
                        System.out.println("El arbol se encuentra vacio."+'\n');
                    }else{
                        System.out.println("El arbol no se encuentra vacio."+'\n');
                    }
                    break;
                case 8:
                    arbol.vaciar();
                    System.out.println("Se ha vaiado correctamente."+'\n');
                    break;
                case 9:
                    clon = arbol.clone();
                    System.out.println("El arbol se ha clonado correctamente." + '\n');
                    break;
                case 10:
                    System.out.println(arbol.toString());
                    System.out.println('\n');  
                    break;
                case 11:
                    sigue= false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada." + '\n');
            }
        }
    }

    public static int menu() {
        int opcion;
        System.out.println("1: Insertar un elemento al arbol." + '\n'
                + "2: Eliminar un elemento del arbol." + '\n'
                + "3: Generar una lista completa del arbol." + '\n'
                + "4: Generar una lista del arbol con limites minimo y maximo." + '\n'
                + "5: Obtener el menor elemento del arbol." + '\n'
                + "6: Obtener el mayor elemento del arbol." + '\n'
                + "7: Verificar si el arbol se encuentra vacio." + '\n'
                + "8: Vaciar el arbol." + '\n'
                + "9: Clonar el arbol." + '\n'
                + "10: Imprimir el arbol." + '\n'
                + "11: Terminar de operar.");
        opcion = TecladoIn.readInt();
        return opcion;
    }
}
