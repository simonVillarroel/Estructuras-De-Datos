package Jerarquica.TDA_Arbol_Binario;

import Jerarquica.TecladoIn;

public class testArbolBin {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        ArbolBin clon = new ArbolBin();
        int opcion, nro;
        boolean sigue = true, vacio;
        char lugar;
        String cadena, cadenaClon;
        Object n, padre;

        while (sigue == true) {
            System.out.println("Ingrese el numero correspondiente a la accion que desea realizar.");
            opcion = menu();
            switch (opcion) {
                case 0:
                    arbol = crearArbolGenerico();
                    System.out.println("Se ha creado el arbol"+'\n');
                    break;
                case 1:
                    if (arbol.esVacio()) {
                        System.out.println("Primero ingrese el elemento raiz de su arbol.");
                        n = TecladoIn.readInt();
                        arbol.insertar(n, null, ' ');
                        System.out.println("Se ha ingresado correctamente." + '\n');
                    } else {
                        System.out.println("Ingrese el elemento que desea agregar al arbol.");
                        n = TecladoIn.readInt();
                        System.out.println("Ahora ingrese el padre de su elemento.");
                        padre = TecladoIn.readInt();
                        System.out.println("Ahora ingrese 'I' si el elemento es hijo izquierdo, o 'D' si el elemento es hijo derecho.");
                        lugar = TecladoIn.readChar();
                        if (arbol.insertar(n, padre, lugar)) {
                            System.out.println("Se ha ingresado correctamente." + '\n');
                        } else {
                            System.out.println("Error, datos mal ingresados." + '\n');
                        }
                    }
                    break;
                case 2:
                    vacio = arbol.esVacio();
                    System.out.println("El arbol se encuentra vacio: " + vacio + "." + '\n');
                    break;
                case 3:
                    nro = arbol.altura();
                    System.out.println("El arbol posee una altura de: " + nro + "." + '\n');
                    break;
                case 4:
                    System.out.println("Ingrese el elemento del cual necesita conocer en que nivel se encuentra.");
                    n = TecladoIn.readInt();
                    nro = arbol.nivel(n);
                    System.out.println("El elemento ingresado se encuentra en el nivel: " + nro + "." + '\n');
                    break;
                case 5:
                    System.out.println("Ingrese el elemento del cual se quiere conocer su padre.");
                    n = TecladoIn.readInt();
                    System.out.println("El padre del elemento " + n + " es: " + arbol.padre(n) + "." + '\n');
                    break;
                case 6:
                    System.out.println(arbol.listarPreorden().toString());
                    System.out.println('\n');
                    break;
                case 7:
                    System.out.println(arbol.listarInorden().toString());
                    System.out.println('\n');
                    break;
                case 8:
                    System.out.println(arbol.listarPosorden().toString());
                    System.out.println('\n');
                    break;
                case 9:
                    System.out.println(arbol.listarPornivel().toString());
                    System.out.println('\n');
                    break;
                case 10:
                    clon = arbol.clone();
                    System.out.println("El arbol se ha clonado." + '\n');
                    break;
                case 11:
                    arbol.vaciar();
                    System.out.println("Se ha vaciado el arbol." + '\n');
                    break;
                case 12:
                    cadena = arbol.toString();
                    System.out.println(cadena);
                    cadenaClon = clon.toString();
                    System.out.println(cadenaClon);
                    System.out.println('\n');
                    break;
                case 13:
                    System.out.println("Ingrese el elemento del cual de desea conocer sus descendientes.");
                    n = TecladoIn.readInt();
                    System.out.println("Los descendientes del elemento ingresado son:");
                    System.out.println(arbol.descendientes(n).toString()+'\n');
                    break;
                case 14:
                    System.out.println("Frontera del arbol:");
                    System.out.println(arbol.frontera().toString());
                    break;
                case 15:
                    sigue = false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada." + '\n');
            }
        }
    }

    public static ArbolBin crearArbolGenerico() {
        ArbolBin gen = new ArbolBin();
        gen.insertar(1, null, ' ');
        gen.insertar(2, 1, 'I');
        gen.insertar(3, 1, 'D');
        gen.insertar(4, 2, 'I');
        gen.insertar(5, 2, 'D');
        gen.insertar(6, 3, 'I');
        gen.insertar(7, 3, 'D');
        gen.insertar(8, 4, 'I');
        gen.insertar(9, 4, 'D');
        gen.insertar(10, 5, 'I');
        gen.insertar(11, 5, 'D');
        gen.insertar(12, 6, 'I');
        gen.insertar(13, 6, 'D');
        gen.insertar(14, 7, 'I');
        gen.insertar(15, 7, 'D');
        return gen;
    }

    public static int menu() {
        int opcion;
        System.out.println("0: cargar un arbol generico completo de altura = 3." + '\n'
                + "1: Agregar un elemento al arbol." + '\n'
                + "2: Verificar si se encuentra vacio." + '\n'
                + "3: Verificar la altura del arbol." + '\n'
                + "4: Verificar el nivel en el que se encuentra un elemento." + '\n'
                + "5: Obtener el padre de un elemento." + '\n'
                + "6: Listar el arbol en preOrden." + '\n'
                + "7: Listar el arbol en inOrden." + '\n'
                + "8: Listar el arbol en posOrden." + '\n'
                + "9: Listar el arbol por niveles." + '\n'
                + "10: Clonar el arbol." + '\n'
                + "11: Vaciar el arbol." + '\n'
                + "12: Imprimir el arbol." + '\n'
                + "13: Obtener lista de descendientes de un elemento." + '\n'
                + "14: Obtener la forntera del arbol." + '\n'
                + "15: Terminar.");
        opcion = TecladoIn.readInt();
        return opcion;
    }
}
