package tests.jerarquicas;

import jerarquicas.ArbolGen;

public class TestArbolGen {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args){
        ArbolGen arbol = new ArbolGen();
        //ARBOL
        //       ______ a ______
        //      /       |       \
        //   _ b _ ---- c ---- _ d _
        //  /  |  \     |     /  |  \
        // e - f - g    h    i - j - k
        //                           |
        //                           l
        
        System.out.println(arbol.toString());
        System.out.println("*****Prueba de insertar*****");
        System.out.println("Altura del arbol: " + arbol.altura());
        System.out.println("Inserto a en la raiz devuelve, OK!: "+ (arbol.insertar('a', 0) ? sOk : sErr));
        System.out.println("Inserto b en la raiz devuelve, ERROR: "+ (arbol.insertar('b', 0) ? sOk : sErr));
        System.out.println("Inserto d como hijo de a, devuelve OK!: "+ (arbol.insertar('d', 'a') ? sOk : sErr));
        System.out.println("Inserto c como hijo de a, devuelve OK!: "+ (arbol.insertar('c', 'a') ? sOk : sErr));
        System.out.println("Inserto b como hijo de a, devuelve OK!: "+ (arbol.insertar('b', 'a') ? sOk : sErr));
        System.out.println("Inserto g como hijo de b, devuelve OK!: "+ (arbol.insertar('g', 'b') ? sOk : sErr));   
        System.out.println("Altura del arbol: " + arbol.altura());
        System.out.println("Inserto f como hijo de b, devuelve OK!: "+ (arbol.insertar('f', 'b') ? sOk : sErr));   
        System.out.println("Inserto e como hijo de b, devuelve OK!: "+ (arbol.insertar('e', 'b') ? sOk : sErr));
        System.out.println("Inserto h como hijo de c, devuelve OK!: "+ (arbol.insertar('h', 'c') ? sOk : sErr));
        System.out.println("Inserto k como hijo de d, devuelve OK!: "+ (arbol.insertar('k', 'd') ? sOk : sErr));
        System.out.println("Inserto j como hijo de d, devuelve OK!: "+ (arbol.insertar('j', 'd') ? sOk : sErr));
        System.out.println("Inserto i como hijo de d, devuelve OK!: "+ (arbol.insertar('i', 'd') ? sOk : sErr));
        System.out.println("Inserto l como hijo de k, devuelve OK!: "+ (arbol.insertar('l', 'k') ? sOk : sErr));
        System.out.println("Altura del arbol: " + arbol.altura());
        System.out.print("\n");
                
        System.out.println("*****Prueba de pertenece()*****");
        System.out.println("'a' pertenece al arbol, devuelve OK!: "+ (arbol.pertenece('a') ? sOk : sErr));
        System.out.println("'f' pertenece al arbol, devuelve OK!: "+ (arbol.pertenece('f') ? sOk : sErr));
        System.out.println("'h' pertenece al arbol, devuelve OK!: "+ (arbol.pertenece('h') ? sOk : sErr));
        System.out.println("'l' pertenece al arbol, devuelve OK!: "+ (arbol.pertenece('l') ? sOk : sErr));
        System.out.println("'e' pertenece al arbol, devuelve OK!: "+ (arbol.pertenece('e') ? sOk : sErr));
        System.out.println("'n' pertenece al arbol, devuelve ERROR: "+ (arbol.pertenece('n') ? sOk : sErr));
        System.out.println("'u' pertenece al arbol, devuelve ERROR: "+ (arbol.pertenece('u') ? sOk : sErr));
        System.out.println("'t' pertenece al arbol, devuelve ERROR: "+ (arbol.pertenece('t') ? sOk : sErr));
        System.out.println("'z' pertenece al arbol, devuelve ERROR: "+ (arbol.pertenece('z') ? sOk : sErr));
        System.out.print("\n");
        
        System.out.println("*****Prueba de padre()*****");
        System.out.println("Busco al padre de 'f', devuelve 'b': "+ arbol.padre('f'));
        System.out.println("Busco al padre de 'e', devuelve 'b': "+ arbol.padre('e'));
        System.out.println("Busco al padre de 'd', devuelve 'a': "+ arbol.padre('d'));
        System.out.println("Busco al padre de 'i', devuelve 'd': "+ arbol.padre('i'));
        System.out.println("Busco al padre de 'h', devuelve 'c': "+ arbol.padre('h'));
        System.out.println("Busco al padre de 'l', devuelve 'k': "+ arbol.padre('l'));        
        System.out.println("Busco al padre de 'a', devuelve null: "+ arbol.padre('a'));
        System.out.println("Busco al padre de 'z', devuelve null: "+ arbol.padre('z'));
        System.out.println("Busco al padre de 'o', devuelve null: "+ arbol.padre('o'));
        System.out.print("\n");
        
        System.out.println("*****Prueba de nivel()*****");
        System.out.println("Nivel de 'a', devuelve 0: "+ arbol.nivel('a'));
        System.out.println("Nivel de 'b', devuelve 1: "+ arbol.nivel('b'));
        System.out.println("Nivel de 'f', devuelve 2: "+ arbol.nivel('f'));
        System.out.println("Nivel de 'h', devuelve 2: "+ arbol.nivel('h'));
        System.out.println("Nivel de 'j', devuelve 2: "+ arbol.nivel('j'));
        System.out.println("Nivel de 'l', devuelve 3: "+ arbol.nivel('l'));
        System.out.println("Nivel de 'z', devuelve -1: "+ arbol.nivel('z'));
        System.out.print("\n");
        
        System.out.println("*****Prueba de ancestros()*****");
        System.out.println("Busco ancestros de 'f', devuelve [ b a ]: "+ arbol.ancestros('f').toString());
        System.out.println("Busco ancestros de 'e', devuelve [ b a ]: "+ arbol.ancestros('e').toString());
        System.out.println("Busco ancestros de 'h', devuelve [ c a ]: "+ arbol.ancestros('h').toString());
        System.out.println("Busco ancestros de 'i', devuelve [ d a ]: "+ arbol.ancestros('i').toString());
        System.out.println("Busco ancestros de 'l', devuelve [ k d a ]: "+ arbol.ancestros('l').toString());
        System.out.println("Busco ancestros de 'a', devuelve []: "+ arbol.ancestros('a').toString());
        System.out.println("Busco ancestros de 'z', devuelve []: "+ arbol.ancestros('z').toString());
        System.out.println("Busco ancestros de 'o', devuelve []: "+ arbol.ancestros('o').toString());
        System.out.print("\n");
        
        System.out.println(arbol.toString());
        System.out.print("\n");

        System.out.println("*****Prueba de listados*****");
        System.out.println("Arbol listado en preorden, deberia dar [ a b e f g c h d i j k l ]: " + arbol.listarPreorden());
        System.out.println("Arbol listado en inorden, deberia dar [ e b f g a h c i d j l k ]: " + arbol.listarInorden());
        System.out.println("Arbol listado en posorden, deberia dar [ e f g b h c i j l k d a ]: " + arbol.listarPosorden());
        //System.out.println("Arbol listado por niveles, deberia dar [ a b c d e f g h i j k l ]: " + arbol.listarNiveles());
        System.out.print("\n");
    
    }
}
