package tests.jerarquicas;

import jerarquicas.ArbolBin;

public class TestArbolBin {

    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        System.out.println("Se crea un arbol generico de altura 2 con 7 elementos: ");
        System.out.println("\n      _1_\n    _|   |_\n  _2_     _3_\n |   |   |   |\n 4   5   6   7\n");
        
        armarArbolGenerico(arbol);
        System.out.println("Arbol listado en pre-orden, deberia mostrar [ 1 2 4 5 3 6 7 ]\t--> "+arbol.listarPreorden().toString());
        System.out.println("Arbol listado en in-orden, deberia mostrar [ 4 2 5 1 6 3 7 ]\t--> "+arbol.listarInorden().toString());
        System.out.println("Arbol listado en pos-orden, deberia mostrar [ 4 5 2 6 7 3 1 ]\t--> "+arbol.listarPosorden().toString());
        System.out.println("\n" + arbol.toString());
        System.out.println("Altura del arbol: " + arbol.altura());
        
        System.out.println("\n      _1_\n    _|   |_\n  _2_     _3_\n |   |   |   |\n 4   5   6   7\n");
        System.out.println("Busco al padre del elemento 1, deberia dar null:\t\t--> " + arbol.padre(1));
        System.out.println("Busco al padre del elemento 2, deberia dar 1:\t\t\t--> " + arbol.padre(2));
        System.out.println("Busco al padre del elemento 3, deberia dar 1:\t\t\t--> " + arbol.padre(3));
        System.out.println("Busco al padre del elemento 4, deberia dar 2:\t\t\t--> " + arbol.padre(4));
        System.out.println("Busco al padre del elemento 5, deberia dar 2:\t\t\t--> " + arbol.padre(5));
        System.out.println("Busco al padre del elemento 6, deberia dar 3:\t\t\t--> " + arbol.padre(6));
        System.out.println("Busco al padre del elemento 7, deberia dar 3:\t\t\t--> " + arbol.padre(7));

        System.out.println("Inserto a 8 como HI de 0, espera FALSE y [ 1 2 4 5 3 6 7]:\t--> " + (arbol.insertar(8, 0, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 1 como HD de 2, espera FALSE y [ 1 2 4 5 3 6 7]:\t--> " + (arbol.insertar(1, 2, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 8 como HD de 5, espera TRUE y [ 1 2 4 5 8 3 6 7]:\t--> " + (arbol.insertar(8, 5, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 9 como HI de 8, espera TRUE y [ 1 2 4 5 8 9 3 6 7]:\t--> " + (arbol.insertar(9, 8, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 1 como HI de 7, espera TRUE y [ 1 2 4 5 8 9 3 6 7 1]:\t--> " + (arbol.insertar(1, 7, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("\n" + arbol.toString());
        System.out.println("Altura del arbol: " + arbol.altura());
        
        System.out.println("Nivel del elemento 1, deberia dar 0:\t\t--> " + arbol.nivel(1));
        System.out.println("Nivel del elemento 2, deberia dar 1:\t\t--> " + arbol.nivel(2));
        System.out.println("Nivel del elemento 6, deberia dar 2:\t\t--> " + arbol.nivel(6));
        System.out.println("Nivel del elemento 8, deberia dar 3:\t\t--> " + arbol.nivel(8));
        System.out.println("Nivel del elemento 9, deberia dar 4:\t\t--> " + arbol.nivel(9));
        System.out.println("Nivel del elemento 20, deberia dar -1:\t\t--> " + arbol.nivel(20));



    }

    public static void armarArbolGenerico(ArbolBin arbol){
        //arma un arbol de altura 2 con 7 elementos
        //      _1_
        //    _|   |_
        //  _2_     _3_
        // |   |   |   |
        // 4   5   6   7
        arbol.insertar(1, null, 'I');
        arbol.insertar(2, 1, 'I');
        arbol.insertar(3, 1, 'D');
        arbol.insertar(4, 2, 'I');
        arbol.insertar(5, 2, 'D');
        arbol.insertar(6, 3, 'I');
        arbol.insertar(7, 3, 'D');
    }
}
