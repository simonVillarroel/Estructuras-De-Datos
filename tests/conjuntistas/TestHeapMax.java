package tests.conjuntistas;

import conjuntistas.HeapMax;

public class TestHeapMax {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args){

        System.out.println("//////////TEST ARBOL HEAP MAXIMO//////////");
        HeapMax a = new HeapMax();
        HeapMax clon = new HeapMax();

        System.out.println("Checkeo si es vacio, devuelve OK!: " + ((  a.esVacio()) ? sOk : sErr));  
        System.out.println("Recupero cima de arbol vacio, devuelve null: " + ((  a.recuperarCima()==null) ? sOk : sErr)); 
        System.out.println("Elimino cima de arbol vacio, devuelve ERROR: " + ((  a.eliminarCima()) ? sOk : sErr)); 

        System.out.println("Inserto el 50, devuelve OK!: " + ((  a.insertar(50)) ? sOk : sErr));
        System.out.println("Recupero cima de arbol, devuelve 50: " + (int)a.recuperarCima()); 
        System.out.println("Elimino cima de arbol: " + ((  a.eliminarCima()) ? sOk : sErr)); 

        System.out.println("Inserto el 50: " + ((  a.insertar(50)) ? sOk : sErr));
        System.out.println("Inserto el 25: " + ((  a.insertar(25)) ? sOk : sErr));
        System.out.println("Inserto el 75: " + ((  a.insertar(75)) ? sOk : sErr));

        System.out.println("Checkeo si es vacio cuando tiene elementos, devuelve ERROR: " + ((  a.esVacio()) ? sOk : sErr));  
        System.out.println("Recupero cima de arbol, devuelve 75: " + (int)a.recuperarCima());


        System.out.println("\nToString() deberia dar"  
                            +"\n 75: HI--> 25 HD--> 50\n"  
                            +"\narbol.toString() = "
                            +"\n" + a.toString()+"\n");


        System.out.println("Inserto el 10: " + ((  a.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 40: " + ((  a.insertar(40)) ? sOk : sErr));
        System.out.println("Inserto el 80: " + ((  a.insertar(80)) ? sOk : sErr)+ "\n");

        System.out.println("ToString() deberia dar"
                            + "\n80: HI--> 40 HD--> 75 "
                            +"\n40: HI--> 10 HD--> 25 "
                            +"\n75: HI--> 50  HD--> - "
                            +"\n10: HI--> -  HD--> - "
                            +"\n25: HI--> - HD--> - "
                            +"\n50: HI--> -  HD--> - \n"
                            +"\narbol.toString() ="
                            +"\n" + a.toString()+"\n");

        System.out.println("Inserto el 60: " + ((  a.insertar(60)) ? sOk : sErr));
        System.out.println("Inserto el 30: " + ((  a.insertar(30)) ? sOk : sErr));
        System.out.println("Inserto el 30: " + ((  a.insertar(30)) ? sOk : sErr));
        System.out.println("Inserto el 5: " + ((  a.insertar(5)) ? sOk : sErr));
      

        System.out.println("Recupero cima de arbol, tiene que dar 80: " + (int)a.recuperarCima());

        System.out.println("\nToString() deberia dar"
                            + "\n80: HI--> 40 HD--> 75 "
                            +"\n40: HI--> 30 HD--> 25 "
                            +"\n75: HI--> 50  HD--> 60 "
                            +"\n30: HI--> 10  HD--> 30 "
                            +"\n25: HI--> 5 HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n60: HI--> - HD--> - "
                            +"\n10:  HI--> -  HD--> - "
                            +"\n30: HI--> - HD--> - "
                            +"\n5: HI--> - HD--> - \n"
                            +"\narbol.toString() ="
                            +"\n"+a.toString()+"\n");

        System.out.println("(Clono el arbol)");
        clon = a.clone();
        System.out.println("clone.toString() deberia dar"
                            + "\n80: HI--> 40 HD--> 75 "
                            +"\n40: HI--> 30 HD--> 25 "
                            +"\n75: HI--> 50  HD--> 60 "
                            +"\n30: HI--> 10  HD--> 30 "
                            +"\n25: HI--> 5 HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n60: HI--> - HD--> - "
                            +"\n10:  HI--> -  HD--> - "
                            +"\n30: HI--> - HD--> - "
                            +"\n5: HI--> - HD--> - \n"
                            +"\nclon.toString() ="
                            +"\n"+clon.toString()+"\n");

        System.out.println("Recupero cima de arbol, tiene que dar 80: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (80) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 75: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (75) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 60: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (60) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 50: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (50) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 40: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (40) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 30: " + (int)a.recuperarCima());


        System.out.println("Chequeo que no existan cambios en el clon");
        System.out.println("clone.toString() deberia dar"
                            + "\n80: HI--> 40 HD--> 75 "
                            +"\n40: HI--> 30 HD--> 25 "
                            +"\n75: HI--> 50  HD--> 60 "
                            +"\n30: HI--> 10  HD--> 30 "
                            +"\n25: HI--> 5 HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n60: HI--> - HD--> - "
                            +"\n10:  HI--> -  HD--> - "
                            +"\n30: HI--> - HD--> - "
                            +"\n5: HI--> - HD--> - \n"
                            +"\nclon.toString() ="
                            +"\n"+clon.toString()+"\n");

        System.out.println("\nToString() deberia dar"
                            +"\n30: HI--> 25 HD--> 30 "
                            +"\n25: HI--> 5  HD--> 10 "
                            +"\n30: HI--> -  HD--> - "
                            +"\n5: HI--> -  HD--> - "
                            +"\n10: HI--> -  HD--> - \n"
                            +"\narbol.toString() =" 
                            + "\n" + a.toString());

    }
}
