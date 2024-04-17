package tests.lineales.estaticas;

import Apunte1.Alumno;
//import lineales.estaticas.Pila;
import lineales.dinamicas.Pila;
import utiles.TecladoIn;
import utiles.Aleatorio;
import java.util.Scanner;   

public class testPila {

    public static void main(String[] args) {
        Pila pila = new Pila();
        Pila clon = new Pila();
        Alumno alumno;
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean desap, sigue = true, vacia;
        Object elem, topeObtenido;

        while (sigue == true) {
            opcion = menu();
            switch (opcion) {
                case 1: //apilar(Object)
                    System.out.println("Ingrese el elemento que desea apilar.");
                    elem = sc.next();
                    if (pila.apilar(elem)) {
                        System.out.println("Se ha apilado correctamente.");
                    } else {
                        System.out.println("No se ha podido apilar, la pila alcanzo su limite.");
                    }
                    break;
                case 2: //apilar(Alumno)
                    alumno = crearAlumno();
                    if (pila.apilar(alumno)) {
                        System.out.println("Se ha apilado correctamente.");
                    } else {
                        System.out.println("No se ha podido apilar, la pila alcanzo su limite.");
                    }
                    break;
                case 3: //desapilar()
                    desap = pila.desapilar();
                    if (desap) {
                        System.out.println("Se ha desapilado.");
                    } else {
                        System.out.println("No se puede desapilar, la pila ya esta vacia.");
                    }
                    break;
                case 4: //obtenerTope()
                    topeObtenido = pila.obtenerTope();
                    System.out.println("El elemento que esta en el tope de la pila es: " + topeObtenido);
                    break;
                case 5: //esvacia()
                    vacia = pila.esVacia();
                    System.out.println("La pila esta vacia: " + vacia);
                    break;
                case 6: //vaciar()
                    pila.vaciar();
                    System.out.println("La pila se ha vaciado.");
                    System.out.println(pila.toString() + '\n');
                    break;
                case 7: //clone()
                    clon = pila.clone();
                    System.out.println("La pila se ha clonado." + '\n');
                    break;
                case 8: //capicua()
                    System.out.println("La pila es capicua: " + esCapicua(pila) + '\n');
                    break;
                case 9: //toString()
                    System.out.println(pila.toString());
                    System.out.println(clon.toString() + '\n');
                    break;
                case 0: //Terminar de operar
                    sigue = false;
                    break;
                default:
                    System.out.println("Opcion mal ingresada." + '\n');
            }
        }
    }

    public static int menu() {
        //Muestra el menu de operaciones que se pueden realizar sobre una pila
        int opcion;
        System.out.println("Ingrese el numero correspondiente a la accion que desea realizar." + '\n'
                + "1: Apilar elemento de tipo entero." + '\n'
                + "2: Apilar elemento de tipo Alumno (creado aleatoriamente)." + '\n'
                + "3: Desapilar elemento." + '\n'
                + "4: Obtener tope de la pila." + '\n'
                + "5: Verificar si la pila esta vacia." + '\n'
                + "6: Vaciar la pila." + '\n'
                + "7: Clonar la pila." + '\n'
                + "8: Verificar si los elementos de la pila forman un numero capicua." + '\n'
                + "9: Imprimir pila original y clonada." + '\n'
                + "0: Terminar.");
        opcion = TecladoIn.readInt();
        return opcion;
    }

    public static boolean esCapicua(Pila pila){
        /*Dada una pila con elementos de tipo entero
        devuelve true si forman un numero capicua y false en caso contrario*/
        boolean exito = true;
        Pila clonada = pila.clone();
        String cadena = "";
        int frente = 0, fin;
        while(!clonada.esVacia()){
            cadena += clonada.obtenerTope();
            clonada.desapilar();
        }
        fin = cadena.length()-1;
        while(frente < fin){
            if(cadena.charAt(frente) != cadena.charAt(fin)){
                System.out.println("hola");      
                exito = false;
            }
            frente++;
            fin--;
        }
        return exito;
    }

    public static Alumno crearAlumno(){
        String legajo, nombre, apellido, domicilio, usuario, clave;
        int dni, telefono;

        legajo = Aleatorio.stringAleatorio(6);
        nombre = Aleatorio.nombreAleatorio();
        apellido = Aleatorio.stringAleatorio(6);
        dni = Aleatorio.intAleatorio(8, 8);
        domicilio = Aleatorio.stringAleatorio(10);
        telefono = Aleatorio.intAleatorio(9, 9);
        usuario = Aleatorio.stringAleatorio(5);
        clave = Aleatorio.stringAleatorio(8);

        Alumno alumno = new Alumno(legajo, nombre, apellido, dni, domicilio, telefono, usuario, clave);       
        return alumno;
    }

}
