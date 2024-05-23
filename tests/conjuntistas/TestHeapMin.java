package tests.conjuntistas;

import conjuntistas.HeapMin;

public class TestHeapMin {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args){

        System.out.println("//////////TEST ARBOL HEAP MINIMO//////////");
        HeapMin a = new HeapMin();
        HeapMin clon = new HeapMin();

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
        System.out.println("Recupero cima de arbol, devuelve 25: " + (int)a.recuperarCima());


        System.out.println("\nToString() deberia dar"  
                            +"\n 25: HI--> 50 HD--> 75\n"  
                            +"\narbol.toString() = "
                            +"\n" + a.toString()+"\n");


        System.out.println("Inserto el 10: " + ((  a.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 40: " + ((  a.insertar(40)) ? sOk : sErr));
        System.out.println("Inserto el 80: " + ((  a.insertar(80)) ? sOk : sErr)+ "\n");

        System.out.println("ToString() deberia dar"
                            + "\n10: HI--> 25 HD--> 75 "
                            +"\n25: HI--> 50 HD--> 40 "
                            +"\n75: HI--> 80  HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n40: HI--> - HD--> - "
                            +"\n80: HI--> -  HD--> - \n"
                            +"\narbol.toString() ="
                            +"\n" + a.toString()+"\n");

        System.out.println("Inserto el 60: " + ((  a.insertar(60)) ? sOk : sErr));
        System.out.println("Inserto el 30: " + ((  a.insertar(30)) ? sOk : sErr));
        System.out.println("Inserto el 30: " + ((  a.insertar(30)) ? sOk : sErr));
        System.out.println("Inserto el 5: " + ((  a.insertar(5)) ? sOk : sErr));
      

        System.out.println("Recupero cima de arbol, tiene que dar 5: " + (int)a.recuperarCima());

        System.out.println("\nToString() deberia dar"
                            +"\n5: HI--> 10 HD--> 60 "
                            +"\n10:  HI--> 30  HD--> 25 "
                            +"\n60: HI--> 80 HD--> 75 "
                            +"\n30: HI--> 50  HD--> 30 "
                            +"\n25: HI--> 40  HD--> - "
                            +"\n80: HI--> -  HD--> - "
                            +"\n75: HI--> -  HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n30: HI--> -  HD--> - "
                            +"\n40: HI--> -  HD--> - \n"
                            +"\narbol.toString() ="
                            +"\n"+a.toString()+"\n");

        System.out.println("(Clono el arbol)");
        clon = a.clone();
        System.out.println("clone.toString() deberia dar"
                            +"\n5: HI--> 10 HD--> 60 "
                            +"\n10:  HI--> 30  HD--> 25 "
                            +"\n60: HI--> 80 HD--> 75 "
                            +"\n30: HI--> 50  HD--> 30 "
                            +"\n25: HI--> 40  HD--> - "
                            +"\n80: HI--> -  HD--> - "
                            +"\n75: HI--> -  HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n30: HI--> -  HD--> - "
                            +"\n40: HI--> -  HD--> - \n"
                            +"\nclon.toString() ="
                            +"\n"+clon.toString()+"\n");

        System.out.println("Recupero cima de arbol, tiene que dar 5: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (5) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 10: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (10) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 25: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (25) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 30: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (30) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 30: " + (int)a.recuperarCima());
        System.out.println("Elimino cima de arbol (30) " + ((  a.eliminarCima()) ? sOk : sErr)); 
        System.out.println("Recupero cima de arbol, tiene que dar 40: " + (int)a.recuperarCima());


        System.out.println("Chequeo que no existan cambios en el clon");
        System.out.println("\nClone.ToString() deberia dar"
                            +"\n5: HI--> 10 HD--> 60 "
                            +"\n10:  HI--> 30  HD--> 25 "
                            +"\n60: HI--> 80 HD--> 75 "
                            +"\n30: HI--> 50  HD--> 30 "
                            +"\n25: HI--> 40  HD--> - "
                            +"\n80: HI--> -  HD--> - "
                            +"\n75: HI--> -  HD--> - "
                            +"\n50: HI--> -  HD--> - "
                            +"\n30: HI--> -  HD--> - "
                            +"\n40: HI--> -  HD--> - \n"
                            +"\nclon.toString() ="
                            +"\n"+clon.toString()+"\n");
        
        System.out.println("\nToString() deberia dar"
                            +"\n40: HI--> 50 HD--> 60 "
                            +"\n50: HI--> 80  HD--> 75 "
                            +"\n60: HI--> -  HD--> - "
                            +"\n80: HI--> -  HD--> - "
                            +"\n75: HI--> -  HD--> - \n"
                            +"\narbol.toString() =" 
                            + "\n" + a.toString());
    }
}
