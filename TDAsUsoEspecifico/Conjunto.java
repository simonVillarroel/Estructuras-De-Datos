package TDAsUsoEspecifico;

//IMPLEMENTADO CON ARBOL-AVL
import conjuntistas.ArbolAVL;

public class Conjunto {
    private ArbolAVL conjunto;

    public Conjunto(){
        this.conjunto = new ArbolAVL();
    }

    public boolean agregar(Comparable elem){
        boolean exito = false;
        exito = conjunto.insertar(elem);
        return exito;
    }

    public boolean quitar(Comparable elem){
        boolean exito = false;
        exito = conjunto.eliminar(elem);
        return exito;
    }

    public boolean pertenece(Comparable elem){
        return conjunto.pertenece(elem);
    }

    public boolean esVacio(){
        return conjunto.esVacio();
    }
}
