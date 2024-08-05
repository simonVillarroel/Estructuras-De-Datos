package tests.grafos;

import grafos.Grafo_Etiquetado;
import lineales.dinamicas.Lista;
import utiles.TecladoIn;

public class Test_Grafo_Etiquetado {
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String NEGRO = "\u001B[30m";
    public static final String RESET = "\u001B[0m";

        public static void main(String[] args) {
            Grafo_Etiquetado grafo = new Grafo_Etiquetado();
            Grafo_Etiquetado clon = new Grafo_Etiquetado();
            int opcion;
            boolean sigue = true;
            Object elem, elem2;
            Lista camino;
            
            while (sigue == true) {
                System.out.println(ANSI_YELLOW_BACKGROUND + NEGRO + "Ingrese el numero correspondiente a la accion que desea realizar." + RESET);
                opcion = menu();
                switch (opcion) {
                    case 0:
                        grafo = crearGrafoGenerico();
                        System.out.println("Se ha creado el grafo"+'\n');
                        System.out.println(grafo.toString() + '\n');
                        break;
                    case 1: //insertar vertice
                        System.out.println("Ingrese el vertice que desee insertar al grafo");
                        elem = TecladoIn.readChar();
                        if(grafo.insertarVertice(elem)){
                            System.out.println("Se ha insertado correctamente." + '\n');
                        }else{
                            System.out.println("No se ha podido insertar el vertice." + '\n');
                        }
                        break;
                    case 2: //eliminar vertice
                        System.out.println("Ingrese el vertice que desee eliminar del grafo");
                        elem = TecladoIn.readChar();
                        if(grafo.eliminarVertice(elem)){
                            System.out.println("Se ha eliminado correctamente." + '\n');
                        }else{
                            System.out.println("No se ha podido eliminar el vertice." + '\n');
                        }
                        break;
                    case 3: //insertar arco
                        System.out.println("Ingrese los vertices a los cuales desea insertar un arco entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        if(grafo.insertarArco(elem, elem2, null)){
                            System.out.println("Se ha insertado correctamente." + '\n');
                        }else{
                            System.out.println("No se ha podido insertar el arco." + '\n');
                        }
                        break;
                    case 4: //eliminar arco
                        System.out.println("Ingrese los vertices a los cuales desea eliminar el arco entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0); 
                        if(grafo.eliminarArco(elem, elem2)){
                            System.out.println("Se ha eliminado correctamente." + '\n');
                        }else{
                            System.out.println("No se ha podido eliminar el arco." + '\n');
                        }
                        break;
                    case 5: //existe vertice
                        System.out.println("Ingrese el vertice que desee saber si pertenece al grafo");
                        elem = TecladoIn.readChar();
                        System.out.println("El vertice pertenece al grafo: "+ grafo.existeVertice(elem) +'\n');
                        break;
                    case 6: //existe arco
                        System.out.println("Ingrese los vertices a los cuales desea saber si existe un arco entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        System.out.println("Existe un arco entre los vertices ingresados: "+ grafo.existeArco(elem, elem2) + '\n');
                        break;
                    case 7: //existe camino
                        System.out.println("Ingrese los vertices a los cuales desea saber si existe un camino entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        System.out.println("Existe un camino entre los vertices ingresados: "+ grafo.existeCamino(elem, elem2) + '\n');
                        break;
                    case 8: //camino mas corto
                        System.out.println("Ingrese los vertices a los cuales desea buscar el camino mas corto entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        camino = grafo.caminoMasCorto(elem, elem2);
                        if (camino.esVacia()) {
                            System.out.println("No hay camino entre ambos vertices" + '\n');
                        }else{
                            System.out.println("El camino mas corto entre ambos vertices es: " + camino.toString() + '\n');
                        }
                        break;
                    case 9: //camino mas largo
                        System.out.println("Ingrese los vertices a los cuales desea buscar el camino mas largo entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        camino = grafo.caminoMasLargo(elem, elem2);
                        if (camino == null) {
                            System.out.println("No hay camino entre ambos vertices" + '\n');
                        }else{
                            System.out.println("El camino mas largo entre ambos vertices es: " + camino.toString() + '\n');
                        }
                        break;
                    case 10: //camino mas corto en etiquetado
                        System.out.println("Ingrese los vertices a los cuales desea buscar el camino mas corto en etiquetado entre ellos");
                        System.out.println("Primer vertice:");
                        elem = TecladoIn.readLine().charAt(0);
                        System.out.println("Segundo vertice:");
                        elem2 = TecladoIn.readLine().charAt(0);
                        camino = grafo.caminoMasCortoEnTiempo(elem, elem2);
                        if (camino == null) {
                            System.out.println("No hay camino entre ambos vertices" + '\n');
                        }else{
                            System.out.println("El camino mas corto entre ambos vertices es: " + camino.toString() + '\n');
                        }
                        break;
                    case 11: //listar en profundidad
                        System.out.println("Grafo listado en profundidad: "+'\n'+ grafo.listarEnProfundidad().toString() + '\n');
                        break;
                    case 12: //listar por anchura
                        System.out.println("Grafo listado por anchura: "+'\n'+ grafo.recAnchura().toString() + '\n');
                        break;
                    case 13: //verificar si el grafo esta vacio
                        System.out.println("El grafo se encuentra vacio: " + grafo.esVacio() + '\n');
                        break;
                    case 14: //vaciar grafo
                        grafo.vaciar();
                        System.out.println("Se ha vaciado el grafo" + '\n');
                        break;
                    case 15: //clonar grafo
                        clon = grafo.clone();
                        System.out.println("Se ha clonado el grafo"+'\n');
                        System.out.println("Grafo original:\n" + grafo.toString() + '\n');
                        System.out.println("Grafo copia:\n" + clon.toString() + '\n');
                        break;
                    case 16:
                        sigue = false;
                        break;
                    default:
                        System.out.println("Opcion mal ingresada." + '\n');
                }
            }
        }
    
        public static Grafo_Etiquetado crearGrafoGenerico() {
            Grafo_Etiquetado grafo = new Grafo_Etiquetado();
            grafo.insertarVertice('l');        
            grafo.insertarVertice('k');
            grafo.insertarVertice('j');        
            grafo.insertarVertice('i');
            grafo.insertarVertice('h');        
            grafo.insertarVertice('g');
            grafo.insertarVertice('f');        
            grafo.insertarVertice('e');
            grafo.insertarVertice('d');        
            grafo.insertarVertice('c');
            grafo.insertarVertice('b');        
            grafo.insertarVertice('a');
            grafo.insertarArco('k', 'l', 10.);
            grafo.insertarArco('j', 'k', 10.);
            grafo.insertarArco('h', 'i', 30.); 
            grafo.insertarArco('f', 'g', 10.);
            grafo.insertarArco('e', 'k', 10.); 
            grafo.insertarArco('e', 'f', 5.);
            grafo.insertarArco('d', 'j', 5.); 
            grafo.insertarArco('c', 'h', 20.);
            grafo.insertarArco('b', 'f', 20.);
            grafo.insertarArco('b', 'e', 10.);
            grafo.insertarArco('a', 'd', 5.);
            grafo.insertarArco('a', 'c', 10.);
            grafo.insertarArco('a', 'b', 50.);
            return grafo;
            //DIAGRAMA DEL GRAFO CREADO DONDE A ES EL INICIO:
            //         l
            //         |
            //    j __ k __ e __ f __ g 
            //    |         |  /
            //    d __ a __ b /
            //         |
            //         c __ h __ i
        }
    
        public static int menu() {
            int opcion;
            System.out.println("0: Cargar un grafo con 12 vertices.\n"
                    + "1: Insertar un vertice al grafo.\n"
                    + "2: Eliminar un vertice del grafo.\n"
                    + "3: Insertar un arco al grafo.\n"
                    + "4: Eliminar un arco del grafo.\n"
                    + "5: Verificar si existe un vertice en el grafo.\n"
                    + "6: Verificar si existe un arco en el grafo.\n"
                    + "7: Verificar si existe un camino entre dos vertices del grafo.\n"
                    + "8: Obtener el camino mas corto entre dos vertices del grafo.\n"
                    + "9: Obtener el camino mas largo entre dos vertices del grafo.\n"
                    + "10: Obtener el camino mas corto en tiempo entre dos vertices del grafo.\n"
                    + "11: Listar el contenido del grafo en profundidad.\n"
                    + "12: Listar el contenido del grafo por anchura.\n"
                    + "13: Verificar si el grafo esta vacio.\n"
                    + "14: Vaciar el contenido del grafo.\n"
                    + "15: Clonar el grafo\n"
                    + "16: Terminar.");
            opcion = TecladoIn.readInt();
            return opcion;
        }
}
