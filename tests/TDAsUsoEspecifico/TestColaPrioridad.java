package tests.TDAsUsoEspecifico;

import TDAsUsoEspecifico.ColaPrioridad;

public class TestColaPrioridad {
    static String sOk = "OK!", sErr = "ERROR";

    public static void main(String[] args) {
        ColaPrioridad cola = new ColaPrioridad();

        System.out.println("Inserto a Tomas con prioridad 2 :" + (cola.insertar("Tomas", 2) ? sOk : sErr));
        System.out.println("Inserto a Luca con prioridad 4 :" + (cola.insertar("Luca", 4) ? sOk : sErr));
        System.out.println("Inserto a Josefina con prioridad 2 :" + (cola.insertar("Josefina", 2) ? sOk : sErr));
        System.out.println("Inserto a Victoria con prioridad 1 :" + (cola.insertar("Victoria", 1) ? sOk : sErr));
        System.out.println("Inserto a Camila con prioridad 4 :" + (cola.insertar("Camila", 4) ? sOk : sErr));
        System.out.println("Inserto a Valentin con prioridad 1 :" + (cola.insertar("Valentin", 1) ? sOk : sErr));
        System.out.println("Inserto a Mateo con prioridad 2 :" + (cola.insertar("Mateo", 2) ? sOk : sErr));

        System.out.println('\n' + cola.toString());

        System.out.println("Frente de la cola de prioridad: " + cola.obtenerFrente());
        System.out.println("Elimino el frente de la cola: " + (cola.eliminarFrente() ? sOk : sErr));
        System.out.println("Frente de la cola de prioridad: " + cola.obtenerFrente());
        System.out.println("Elimino el frente de la cola: " + (cola.eliminarFrente() ? sOk : sErr));
        System.out.println("Frente de la cola de prioridad: " + cola.obtenerFrente());

        System.out.println('\n' + cola.toString());
    }
}
