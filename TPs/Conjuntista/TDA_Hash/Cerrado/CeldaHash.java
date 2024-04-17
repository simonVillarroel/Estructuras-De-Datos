package Conjuntista.TDA_Hash.Cerrado;

public class CeldaHash {
    private Object elemento;
    private int estado;

    public CeldaHash(){
        this.elemento = null;
        this.estado = 0;
        //Estados: VACIO = 0, BORRADO = -1, OCUPADO = 1
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        if (elemento == null) {
            setEstado(-1);
        }else{
            setEstado(1);
        }
        this.elemento = elemento;
    }

    public int getEstado() {
        return estado;
    }

    private void setEstado(int estado) {
        this.estado = estado;
    }
}
