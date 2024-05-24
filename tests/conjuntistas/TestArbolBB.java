package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestArbolBB {
    
    public static void main(String[] args){
        ArbolBB arbol = new ArbolBB();
        
        arbol.insertar(25);
        arbol.insertar(11);
        arbol.insertar(43);
        arbol.insertar(50);
        arbol.insertar(22);
        arbol.insertar(15);
        arbol.insertar(4);
        arbol.insertar(30);
        arbol.insertar(28);
        arbol.insertar(35);

        //      _____25_____
        //     /            \
        //   _11_          _43_
        //  /    \        /    \
        // 4    _22     _30_    50
        //     /       /    \
        //    15      28    35

        System.out.println(arbol.toString());
        System.out.println("5 pertenece a al arbol: " + arbol.pertenece(5));
        System.out.println("15 pertenece a al arbol: " + arbol.pertenece(15));
        System.out.println("25 pertenece a al arbol: " + arbol.pertenece(25));
        System.out.println("30 pertenece a al arbol: " + arbol.pertenece(30));
        System.out.println("40 pertenece a al arbol: " + arbol.pertenece(40));
        System.out.println("50 pertenece a al arbol: " + arbol.pertenece(50));
        System.out.println("60 pertenece a al arbol: " + arbol.pertenece(60));


    }
}
