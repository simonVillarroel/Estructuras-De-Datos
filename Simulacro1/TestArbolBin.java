package Simulacro1;

import lineales.dinamicas.Lista;
public class TestArbolBin {
    //TEST PARA EJERCICIO TIPO 3 (e)
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        ArbolBin clon;
        Lista lista = new Lista();

        //armo un arbol de altura 2 con 7 elementos
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

        //armo una lista con 3 elementos
        lista.insertar(1, lista.longitud()+1);
        lista.insertar(2, lista.longitud()+1);
        lista.insertar(4, lista.longitud()+1);
        System.out.println(lista.toString());
        System.out.println("Verifica patron [1 2 4] devuelve TRUE: "+arbol.verificarPatron(lista));
        lista.vaciar();
        //armo una lista con 3 elementos
        lista.insertar(1, lista.longitud()+1);
        lista.insertar(2, lista.longitud()+1);
        lista.insertar(5, lista.longitud()+1);
        System.out.println(lista.toString());
        System.out.println("Verifica patron [1 2 5] devuelve TRUE: "+arbol.verificarPatron(lista));
        lista.vaciar();
        //armo una lista con 3 elementos
        lista.insertar(1, lista.longitud()+1);
        lista.insertar(3, lista.longitud()+1);
        lista.insertar(7, lista.longitud()+1);
        System.out.println(lista.toString());
        System.out.println("Verifica patron [1 3 7] devuelve TRUE: "+arbol.verificarPatron(lista));
        
        lista.eliminar(1);
        System.out.println(lista.toString());
        System.out.println("Verifica patron [3 7] devuelve FALSE: "+arbol.verificarPatron(lista));

        lista.eliminar(1);
        System.out.println(lista.toString());
        System.out.println("Verifica patron [7] devuelve FALSE: "+arbol.verificarPatron(lista));


        clon = arbol.clonarInvertido();
        System.out.println("Arbol original:\n"+ arbol.toString());
        System.out.println("Arbol invertido:\n"+ clon.toString());
    }
}
