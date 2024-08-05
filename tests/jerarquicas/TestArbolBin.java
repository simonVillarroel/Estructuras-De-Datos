package tests.jerarquicas;

import jerarquicas.ArbolBin;

public class TestArbolBin {

    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        ArbolBin clon = new ArbolBin();

        System.out.println("Se crea un arbol generico de altura 2 con 7 elementos: ");
        System.out.println("\n      _1_\n    _|   |_\n  _2_     _3_\n |   |   |   |\n 4   5   6   7\n");
        
        armarArbolGenerico(arbol);
        System.out.println("*****Prueba de recorridos del arbol*****");
        System.out.println("Arbol listado en pre-orden, deberia mostrar [ 1 2 4 5 3 6 7 ]\t--> "+arbol.listarPreorden().toString());
        System.out.println("Arbol listado en in-orden, deberia mostrar [ 4 2 5 1 6 3 7 ]\t--> "+arbol.listarInorden().toString());
        System.out.println("Arbol listado en pos-orden, deberia mostrar [ 4 5 2 6 7 3 1 ]\t--> "+arbol.listarPosorden().toString());
        System.out.println("Arbol listado por niveles, deberia mostrar [ 1 2 3 4 5 6 7 ]\t--> "+arbol.listarNiveles().toString());
        System.out.println("\n" + arbol.toString());
        System.out.println("Altura del arbol: " + arbol.altura());

        System.out.println("\n      _1_\n    _|   |_\n  _2_     _3_\n |   |   |   |\n 4   5   6   7\n");
        System.out.println("*****Prueba del metodo padre()*****");
        System.out.println("Busco al padre del elemento 1, deberia dar null:\t\t--> " + arbol.padre(1));
        System.out.println("Busco al padre del elemento 2, deberia dar 1:\t\t\t--> " + arbol.padre(2));
        System.out.println("Busco al padre del elemento 3, deberia dar 1:\t\t\t--> " + arbol.padre(3));
        System.out.println("Busco al padre del elemento 4, deberia dar 2:\t\t\t--> " + arbol.padre(4));
        System.out.println("Busco al padre del elemento 5, deberia dar 2:\t\t\t--> " + arbol.padre(5));
        System.out.println("Busco al padre del elemento 6, deberia dar 3:\t\t\t--> " + arbol.padre(6));
        System.out.println("Busco al padre del elemento 7, deberia dar 3:\t\t\t--> " + arbol.padre(7));
        System.out.println("\n");
        
        System.out.println("*****Primera prueba de metodo frontera*****");
        System.out.println("Frontera del arbol, debe dar [ 4 5 6 7 ]: \t\t\t--> "+ arbol.frontera() + "\n");
        
        System.out.println("*****Primera prueba de metodo obtenerAncestros*****");
        System.out.println("Ancestros de nodo con elem = 7, debe dar [ 1 3 ]: \t\t\t--> "+ arbol.obtenerAncestros(7));
        System.out.println("Ancestros de nodo con elem = 2, debe dar [ 1 ]: \t\t\t--> "+ arbol.obtenerAncestros(2));
        System.out.println("Ancestros de nodo con elem = 1, debe dar [ ]: \t\t\t\t--> "+ arbol.obtenerAncestros(1) + "\n");

        System.out.println("*****Primera prueba de metodo obtenerDescendientes*****");
        System.out.println("Descendientes de nodo con elem = 5, debe dar [ ]: \t\t\t--> "+ arbol.obtenerDescendientes(5));
        System.out.println("Descendientes de nodo con elem = 3, debe dar [ 6 7 ]: \t\t\t--> "+ arbol.obtenerDescendientes(3));
        System.out.println("Descendientes de nodo con elem = 1, debe dar [ 2 4 5 3 6 7 ]: \t\t--> "+ arbol.obtenerDescendientes(1) + "\n");

        System.out.println("*****Prueba del metodo insertar*****");
        System.out.println("Inserto a 8 como HI de 0, espera FALSE y [ 1 2 4 5 3 6 7]:\t\t--> " + (arbol.insertar(8, 0, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 1 como HD de 2, espera FALSE y [ 1 2 4 5 3 6 7]:\t\t--> " + (arbol.insertar(1, 2, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 8 como HD de 5, espera TRUE y [ 1 2 4 5 8 3 6 7]:\t\t--> " + (arbol.insertar(8, 5, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 9 como HI de 8, espera TRUE y [ 1 2 4 5 8 9 3 6 7]:\t\t--> " + (arbol.insertar(9, 8, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 1 como HI de 7, espera TRUE y [ 1 2 4 5 8 9 3 6 7 1]:\t\t--> " + (arbol.insertar(1, 7, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("\n" + arbol.toString());
        System.out.println("Altura del arbol: " + arbol.altura() + "\n");
        
        
        System.out.println("*****Prueba de nivel*****");
        System.out.println("Nivel del elemento 1, deberia dar 0:\t\t--> " + arbol.nivel(1));
        System.out.println("Nivel del elemento 2, deberia dar 1:\t\t--> " + arbol.nivel(2));
        System.out.println("Nivel del elemento 6, deberia dar 2:\t\t--> " + arbol.nivel(6));
        System.out.println("Nivel del elemento 8, deberia dar 3:\t\t--> " + arbol.nivel(8));
        System.out.println("Nivel del elemento 9, deberia dar 4:\t\t--> " + arbol.nivel(9));
        System.out.println("Nivel del elemento 20, deberia dar -1:\t\t--> " + arbol.nivel(20));
        System.out.println("\n" + arbol.toString());
        
        
        System.out.println("*****Segunda prueba de metodo frontera*****");
        System.out.println("Frontera del arbol, debe dar [ 4 9 6 1 ]: \t\t\t--> "+ arbol.frontera() + "\n");
        
        System.out.println("*****Segunda prueba de metodo obtenerAncestros*****");
        System.out.println("Ancestros de nodo con elem = 8, debe dar [ 1 2 5 ]: \t\t\t--> "+ arbol.obtenerAncestros(8));
        System.out.println("Ancestros de nodo con elem = 9, debe dar [ 1 2 5 8 ]: \t\t\t--> "+ arbol.obtenerAncestros(9));
        System.out.println("Ancestros de nodo con elem = 6, debe dar [ 1 3 ]: \t\t\t--> "+ arbol.obtenerAncestros(6) + "\n");
        
        System.out.println("*****Segunda prueba de metodo obtenerDescendientes*****");
        System.out.println("Descendientes de nodo con elem = 5, debe dar [ 8 9 ]: \t\t\t\t--> "+ arbol.obtenerDescendientes(5));
        System.out.println("Descendientes de nodo con elem = 3, debe dar [ 6 4 7 1 ]: \t\t\t--> "+ arbol.obtenerDescendientes(3));
        System.out.println("Descendientes de nodo con elem = 1, debe dar [ 2 4 5 8 9 3 6 4 7 1 ]: \t\t--> "+ arbol.obtenerDescendientes(1) + "\n");

        
        System.out.println("*****Prueba de insercion por posicion*****");
        System.out.println("Inserto a 4 como HI de 6 (pos 8), espera TRUE y [ 1 2 4 5 8 9 3 6 4 7 1]:\t--> " + (arbol.insertarPorPosicion(4, 8, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 2 como HD de 5 (pos 4), espera FALSE y [ 1 2 4 5 8 9 3 6 4 7 1]:\t--> " + (arbol.insertarPorPosicion(2, 4, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 3 como HI de 1 (pos 1), espera FALSE y [ 1 2 4 5 8 9 3 6 4 7 1]:\t--> " + (arbol.insertarPorPosicion(3, 1, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 2 como HD de 1 (pos 11), espera TRUE y [ 1 2 4 5 8 9 3 6 4 7 1 2]:\t--> " + (arbol.insertarPorPosicion(2, 11, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("\n" + arbol.toString());
        
        System.out.println("*****Prueba de metodo clone*****");
        System.out.println("Arbol original esta vacio: " + arbol.esVacio());
        System.out.println("Vacio el arbol original");
        arbol.vaciar();
        System.out.println("Arbol original esta vacio: " + arbol.esVacio());
        System.out.println("Armo el arbol original generico");
        armarArbolGenerico(arbol);
        System.out.println("Clono el arbol");
        clon = arbol.clone();
        System.out.println("\nArbol original:\n" + arbol.toString());
        System.out.println("\nArbol clon:\n" + clon.toString());
        System.out.println("Inserto a 8 como HD de 5, espera TRUE y [ 1 2 4 5 8 3 6 7]:\t--> " + (arbol.insertar(8, 5, 'D') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("Inserto a 9 como HI de 6, espera TRUE y [ 1 2 4 5 8 3 6 9 7]:\t--> " + (arbol.insertar(9, 6, 'I') ? sOk : sErr) + " " + arbol.listarPreorden().toString());
        System.out.println("");
        System.out.println("\nArbol original:\n" + arbol.toString());
        System.out.println("\nArbol clon:\n" + clon.toString());
        System.out.println("Vacio el arbol original");
        arbol.vaciar();
        System.out.println("\nArbol original:\n" + arbol.toString());
        System.out.println("\nArbol clon:\n" + clon.toString());
        
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