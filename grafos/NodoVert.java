package grafos;

public class NodoVert {
    private Object elem;
    private NodoVert sigVert;
    private NodoAdy primerAdy;

    public NodoVert(Object elem, NodoVert siguiente){
        this.elem = elem;
        this.sigVert = siguiente;
        this.primerAdy = null;
    }

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoVert getSiguienteVertice() {
        return sigVert;
    }

    public void setSiguienteVertice(NodoVert sigVert) {
        this.sigVert = sigVert;
    }

    public NodoAdy getPrimerAdyacente() {
        return primerAdy;
    }

    public void setPrimerAdyacente(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }    
}
