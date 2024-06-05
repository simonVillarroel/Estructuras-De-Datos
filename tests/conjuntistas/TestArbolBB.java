package tests.conjuntistas;

import conjuntistas.ArbolBB;

public class TestArbolBB {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args){
        ArbolBB arbol = new ArbolBB();
        ArbolBB clon;
        
        System.out.println("Inserto el nro 25: " + arbol.insertar(25));
        System.out.println(arbol.toString());
        System.out.println("Elimino al nro 25 (raiz, caso 1) -->\t" + (arbol.eliminar(25) ? sOk : sErr));
        System.out.println(arbol.toString());
        
        System.out.println("\nMinimo elemento del arbol vacio debe devolver null -->\t" + arbol.minimoElem());
        System.out.println("Maximo elemento del arbol vacio debe devolver null -->\t" + arbol.maximoElem());
        System.out.println("Inserto el nro 25: " + arbol.insertar(25));
        System.out.println("Minimo elemento del arbol debe devolver 25 -->\t" + arbol.minimoElem());
        System.out.println("Maximo elemento del arbol debe devolver 25 -->\t" + arbol.maximoElem());
        System.out.println("Inserto el nro 11: " + arbol.insertar(11));
        System.out.println("Inserto el nro 43: " + arbol.insertar(43));
        System.out.println("Inserto el nro 50: " + arbol.insertar(50));
        System.out.println("Inserto el nro 22: " + arbol.insertar(22));
        System.out.println("Minimo elemento del arbol debe devolver 11 -->\t" + arbol.minimoElem());
        System.out.println("Maximo elemento del arbol debe devolver 50 -->\t" + arbol.maximoElem());
        System.out.println("Inserto el nro 15: " + arbol.insertar(15));
        System.out.println("Inserto el nro 4: " + arbol.insertar(4));
        System.out.println("Inserto el nro 30: " + arbol.insertar(30));
        System.out.println("Inserto el nro 28: " + arbol.insertar(28));
        System.out.println("Inserto el nro 35: " + arbol.insertar(35));
        System.out.println("Minimo elemento del arbol debe devolver 4 -->\t" + arbol.minimoElem());
        System.out.println("Maximo elemento del arbol debe devolver 50 -->\t" + arbol.maximoElem());

        //      _____25_____
        //     /            \
        //   _11_          _43_
        //  /    \        /    \
        // 4    _22     _30_    50
        //     /       /    \
        //    15      28    35

        System.out.println("\nArbol original:\n" + arbol.toString());
        System.out.println("Listado del arbol original -->\t\t\t\t\t" + arbol.listar());
        System.out.println("Listado del arbol entre 20 y 40, debe dar [22 25 28 30 35] -->\t" + arbol.listarRango(20, 40));
        System.out.println("Listado del arbol entre 0 y 15, debe dar [4 11 15] -->\t\t" + arbol.listarRango(0, 15));
        System.out.println("Listado del arbol entre 28 y 50, debe dar [28 30 35 43 50] -->\t" + arbol.listarRango(28, 50));

        System.out.println("\n*****Prueba de listarMayorIgual() y listarMenores()");
        System.out.println("Listar mayor o igual que 28, debe dar [ 28 30 35 43 50 ] -->\t" + arbol.listarMayorIgual(28));
        System.out.println("Listar mayor o igual que 44, debe dar [ 50 ] -->\t" + arbol.listarMayorIgual(44));
        System.out.println("Listar mayor o igual que 0, debe dar [ 4 11 15 22 25 28 30 35 43 50 ] -->\t" + arbol.listarMayorIgual(0));
        System.out.println("Listar menores que 28, debe dar [ 4 11 15 22 25 ] -->\t" + arbol.listarMenores(28));
        System.out.println("Listar menores que 43, debe dar [ 4 11 15 22 25 28 30 35 ] -->\t" + arbol.listarMenores(43));
        System.out.println("Listar menores que 0, debe dar [ ] -->\t" + arbol.listarMenores(0));

        System.out.println("\n*****Prueba de pertenece()*****");
        System.out.println("5 pertenece al arbol, debe devolver false -->\t" + arbol.pertenece(5));
        System.out.println("15 pertenece al arbol, debe devolver true -->\t" + arbol.pertenece(15));
        System.out.println("25 pertenece al arbol, debe devolver true -->\t" + arbol.pertenece(25));
        System.out.println("30 pertenece al arbol, debe devolver true -->\t" + arbol.pertenece(30));
        System.out.println("40 pertenece al arbol, debe devolver false -->\t" + arbol.pertenece(40));
        System.out.println("50 pertenece al arbol, debe devolver true -->\t" + arbol.pertenece(50));
        System.out.println("60 pertenece al arbol, debe devolver false -->\t" + arbol.pertenece(60));
        
        System.out.println("\n*****Prueba de clone()******");
        clon = arbol.clone();
        System.out.println("Arbol clonado:\n" + clon.toString());
        System.out.println("Listado del arbol original -->\t\t\t\t\t" + arbol.listar());
        System.out.println("Listado del arbol clonado -->\t\t\t\t\t" + clon.listar());


        System.out.println("\n*****Prueba de metodo eliminar() en arbol original*****");
        System.out.println("Elimino al nro 25 (raiz, caso 1) -->\t" + (arbol.eliminar(25) ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Elimino al nro 35 (caso 1) -->\t" + (arbol.eliminar(35) ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Elimino al nro 30 (caso 2) -->\t" + (arbol.eliminar(30) ? sOk : sErr));
        System.out.println(arbol.toString());
        System.out.println("Listado del arbol original -->\t\t\t\t\t" + arbol.listar());
        
        System.out.println("\n*****Prueba de listarRango()*****");
        System.out.println("Listado del arbol entre 20 y 40, debe dar [22 28] -->\t\t" + arbol.listarRango(20, 40));
        System.out.println("Listado del arbol entre 0 y 35, debe dar [4 11 15 22 28] -->\t" + arbol.listarRango(0, 35));
        System.out.println("Listado del arbol entre 28 y 50, debe dar [28 43 50] -->\t" + arbol.listarRango(28, 50));
 
        
        System.out.println("\nListado del arbol original -->\t\t\t\t\t" + arbol.listar());
        System.out.println("Listado del arbol clonado -->\t\t\t\t\t" + clon.listar());
    }
}