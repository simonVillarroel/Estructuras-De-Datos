package tests.jerarquicas;

import jerarquicas.ArbolGen;

public class TestArbolGen {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args){
        ArbolGen arbol = new ArbolGen();
        
        System.out.println(arbol.toString());
        System.out.println("*****Prueba de insertar*****");
        System.out.println("Inserto a en la raiz devuelve, OK!: "+ (arbol.insertar('a', 0) ? sOk : sErr));
        System.out.println("Inserto b en la raiz devuelve, ERROR: "+ (arbol.insertar('b', 0) ? sOk : sErr));
        System.out.println("Inserto b como hijo de a, devuelve OK!: "+ (arbol.insertar('b', 'a') ? sOk : sErr));
        System.out.println("Inserto c como hijo de a, devuelve OK!: "+ (arbol.insertar('c', 'a') ? sOk : sErr));
        System.out.println("Inserto d como hijo de a, devuelve OK!: "+ (arbol.insertar('d', 'a') ? sOk : sErr));
        System.out.println("Inserto e como hijo de c, devuelve OK!: "+ (arbol.insertar('e', 'c') ? sOk : sErr));   
        System.out.println("Inserto f como hijo de c, devuelve OK!: "+ (arbol.insertar('f', 'c') ? sOk : sErr));   
        System.out.println("Inserto g como hijo de a, devuelve OK!: "+ (arbol.insertar('g', 'a') ? sOk : sErr));
        System.out.print("\n");
                
        System.out.println("*****Prueba de pertenece()*****");
        System.out.println("'a' pertenece al arbol, devuelve, OK!: "+ (arbol.pertenece('a') ? sOk : sErr));
        System.out.println("'z' pertenece al arbol, devuelve, ERROR: "+ (arbol.pertenece('z') ? sOk : sErr));
        System.out.println("'f' pertenece al arbol, devuelve, OK!: "+ (arbol.pertenece('f') ? sOk : sErr));
        System.out.println("'h' pertenece al arbol, devuelve, ERROR: "+ (arbol.pertenece('h') ? sOk : sErr));
        System.out.println("'j' pertenece al arbol, devuelve, ERROR: "+ (arbol.pertenece('j') ? sOk : sErr));
        System.out.println("'e' pertenece al arbol, devuelve, OK!: "+ (arbol.pertenece('e') ? sOk : sErr));
        System.out.print("\n");
        
        System.out.println("*****Prueba de padre()*****");
        System.out.println("Busco al padre de 'f', devuelve, 'c': "+ arbol.padre('f'));
        System.out.println("Busco al padre de 'e', devuelve, 'c': "+ arbol.padre('e'));
        System.out.println("Busco al padre de 'g', devuelve, 'a': "+ arbol.padre('g'));
        System.out.println("Busco al padre de 'a', devuelve, null: "+ arbol.padre('a'));
        System.out.println("Busco al padre de 'z', devuelve, null: "+ arbol.padre('z'));
        System.out.println("Busco al padre de 'o', devuelve, null: "+ arbol.padre('o'));
        System.out.print("\n");
        
        
        System.out.println(arbol.toString());
        System.out.println("*****Prueba de listados*****");
        System.out.println("Arbol listado en preorden, deberia dar [ a g d c f e b ]: " + arbol.listarPreorden());
        System.out.println("Arbol listado en inorden, deberia dar [ g a d f c e b ]: " + arbol.listarInorden());
        System.out.println("Arbol listado en posorden, deberia dar [ g d f e c b a ]: " + arbol.listarPosorden());
        //System.out.println("Arbol listado por niveles, deberia dar [ a g d c b f e ]: " + arbol.listarNiveles());
        System.out.print("\n");
    
    }
}
