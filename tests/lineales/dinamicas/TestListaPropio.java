package tests.lineales.dinamicas;

import lineales.dinamicas.Lista;

public class TestListaPropio {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] arg) {
        testLista();
    }

    public static  void testLista(){
        System.out.println("***************COMIENZO TEST LISTA***************");
        Lista l1 = new Lista();
        Lista l2 = new Lista();

        System.out.println("\n*****Se intenta eliminar en pos 1 en Lista vacia*****");
        System.out.println("Elimina pos 1 espera FALSE: \t\t\t" + ((l1.eliminar(1) == false) ? sOk : sErr));

        System.out.println("\n*****Se intenta insertar en posiciones invalidas en Lista vacia*****");
        System.out.println("Muestra lista vacia: \t\t\t\t\t--> " + l1.toString());
        System.out.println("Longitud de lista vacia:\t\t\t" + l1.longitud());
        System.out.println("Inserta 1 pos -1 espera FALSE: \t\t\t" + ((l1.insertar(1, -1) == false) ? sOk : sErr));
        System.out.println("Inserta 1 pos 2 espera FALSE: \t\t\t" + ((l1.insertar(1, 2) == false) ? sOk : sErr));
        System.out.println("Inserta 1 pos 0 espera FALSE: \t\t\t" + ((l1.insertar(1, 0) == false) ? sOk : sErr));
        System.out.println("\t--> " + l1.toString());
        
        System.out.println("\n*****Se insertan 5 elementos*****");
        System.out.println("Inserta 1 pos 1 espera TRUE: \t\t\t" + ((l1.insertar(1, 1) == true) ? sOk : sErr));
        System.out.println("Inserta 2 pos 2 espera TRUE: \t\t\t" + ((l1.insertar(2, 2) == true) ? sOk : sErr));
        System.out.println("Inserta 5 pos 3 espera TRUE: \t\t\t" + ((l1.insertar(5, 3) == true) ? sOk : sErr));
        System.out.println("Inserta 4 pos 3 espera TRUE: \t\t\t" + ((l1.insertar(4, 3) == true) ? sOk : sErr));
        System.out.println("Inserta 3 pos 3 espera TRUE: \t\t\t" + ((l1.insertar(3, 3) == true) ? sOk : sErr));
        System.out.println("\t--> " + l1.toString());
        
        System.out.println("\n*****Se clona la lista*****");
        l2 = l1.clone();
        System.out.println("Se clona la lista \n Lista original: " + l1.toString() + "\n Lista clon: " + l2.toString());
        
        System.out.println("\n*****Se intenta eliminar en posiciones invalidas en Lista de 5 elementos*****");
        System.out.println("Elimina pos 0 espera FALSE: \t\t\t" + ((l1.eliminar(0) == false) ? sOk : sErr));
        System.out.println("Elimina pos -1 espera FALSE: \t\t\t" + ((l1.eliminar(-1) == false) ? sOk : sErr));
        System.out.println("Elimina pos 6 (long+1) espera FALSE: \t\t" + ((l1.eliminar(6) == false) ? sOk : sErr));
        System.out.println("\t--> " + l1.toString());
        System.out.println("Longitud de la lista: \t\t\t"+ l1.longitud());

        System.out.println("\n*****Se elimina en posiciones validas en Lista original*****");
        System.out.println("Elimina pos 1 en original espera TRUE: \t\t" + ((l1.eliminar(1) == true) ? sOk : sErr));
        System.out.println("Se imprime la lista, se espera [2 3 4 5] \t\t--> " + l1.toString());
        System.out.println("Elimina pos 2 en original espera TRUE: \t\t" + ((l1.eliminar(2) == true) ? sOk : sErr));
        System.out.println("Se imprime la lista, se espera [2 4 5] \t\t\t--> " + l1.toString());
        System.out.println("Elimina pos 3 en original espera TRUE: \t\t" + ((l1.eliminar(3) == true) ? sOk : sErr));
        System.out.println("Se imprime la lista, se espera [2 4] \t\t\t--> " + l1.toString());
        System.out.println("Longitud de la lista: \t\t\t"+ l1.longitud());

        System.out.println("\n*****Prueba de metodo recuperar*****");
        System.out.println("Recupera pos 1 en clon espera 1: \t\t\t--> " + l2.recuperar(1));
        System.out.println("Recupera pos 3 en clon espera 3: \t\t\t--> " + l2.recuperar(3));
        System.out.println("Recupera pos 5 en clon espera 5: \t\t\t--> " + l2.recuperar(5));
        System.out.println("Recupera pos 0 en clon espera null: \t\t\t--> " + l2.recuperar(0));
        System.out.println("Recupera pos -1 en clon espera null: \t\t\t--> " + l2.recuperar(-1));
        System.out.println("Recupera pos 7 en clon espera null: \t\t\t--> " + l2.recuperar(7));
        System.out.println("\t--> " + l2.toString());

        System.out.println("\n*****Prueba de metodo localizar*****");
        System.out.println("Localiza 2 en original espera 1: \t\t\t--> " + l1.localizar(2));
        System.out.println("Localiza 4 en original espera 2: \t\t\t--> " + l1.localizar(4));
        System.out.println("Localiza 0 en original espera -1: \t\t\t--> " + l1.localizar(0));
        System.out.println("Localiza 25 en original espera -1: \t\t\t--> " + l1.localizar(25));
        System.out.println("Localiza 5 en original espera -1: \t\t\t--> " + l1.localizar(5));
        System.out.println("\t--> " + l1.toString());

        System.out.println("\n*****Prueba de metodo pertenece*****");
        System.out.println("Pertenece 1 a la lista? Espera TRUE: \t\t\t" + ((l2.pertenece(1) == true) ? sOk : sErr));
        System.out.println("Pertenece 4 a la lista? Espera TRUE: \t\t\t" + ((l2.pertenece(4) == true) ? sOk : sErr));
        System.out.println("Pertenece 5 a la lista? Espera TRUE: \t\t\t" + ((l2.pertenece(5) == true) ? sOk : sErr));
        System.out.println("Pertenece 0 a la lista? Espera FALSE: \t\t\t" + ((l2.pertenece(0) == false) ? sOk : sErr));
        System.out.println("Pertenece -32 a la lista? Espera FALSE: \t\t\t" + ((l2.pertenece(-32) == false) ? sOk : sErr));
        System.out.println("Pertenece 100 a la lista? Espera FALSE: \t\t\t" + ((l2.pertenece(100) == false) ? sOk : sErr));
        System.out.println("\t--> " + l2.toString());
    }
}
