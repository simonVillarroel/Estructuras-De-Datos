package grafos;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class Grafo_Etiquetado{
    private NodoVert inicio;

    public Grafo_Etiquetado(){
        this.inicio = null;
    }

    private NodoVert ubicarVertice(Object buscado){
        NodoVert aux = this.inicio;
        while(aux != null && !aux.getElem().equals(buscado)){
            aux = aux.getSiguienteVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice){
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if(aux == null){
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;    
        }
        return exito;
    }

    public boolean eliminarVertice(Object elem){
        boolean exito = false;
        //primero encuentra el vertice
        NodoVert aux = this.inicio;
        NodoVert nodoEliminar = ubicarVertice(elem);
        //elimina al vertice de la lista de vertices
        if(nodoEliminar != null){
            if(aux.equals(nodoEliminar)){
                this.inicio = this.inicio.getSiguienteVertice();
            }else{
                while(!aux.getSiguienteVertice().equals(nodoEliminar)){
                    aux = aux.getSiguienteVertice();
                }
                aux.setSiguienteVertice(aux.getSiguienteVertice().getSiguienteVertice());
            }
            exito = true;
            //eliminar vertice de las listas de adyacencias de otros vertices
            aux = this.inicio;
            NodoAdy ady = nodoEliminar.getPrimerAdyacente();
            while(ady != null){
                //elimina a nodoEliminar de la lista de ady's del nodo ady
                eliminarAdyacencia(nodoEliminar, ady.getVertice());
                ady = ady.getSigAdyacente();
            }
        }
        return exito;
    }

    private void eliminarAdyacencia(NodoVert nodoEliminar, NodoVert nodoAdyacente){
        //elimina a nodoEliminar de la lista de vertices adyacentes de nodoAdyacente
        boolean eliminado = false;
        NodoAdy adyAux = nodoAdyacente.getPrimerAdyacente();
        if(adyAux != null){
            if(adyAux.getVertice().getElem().equals(nodoEliminar.getElem())){
                nodoAdyacente.setPrimerAdyacente(adyAux.getSigAdyacente());
            }else{
                NodoAdy sigAdy = adyAux.getSigAdyacente();
                while(!eliminado && sigAdy != null){
                    if(nodoEliminar.getElem().equals(sigAdy.getVertice().getElem())){
                        adyAux.setSigAdyacente(sigAdy.getSigAdyacente());
                        eliminado = true;
                    }else{
                        adyAux = adyAux.getSigAdyacente();
                        sigAdy = adyAux.getSigAdyacente();
                    }
                }
            }
        }
    }

    public boolean existeVertice(Object buscado){
        boolean encontrado = false;
        NodoVert aux = this.inicio;
        while(!encontrado && aux != null){
            if(aux.getElem().equals(buscado)){
                encontrado = true;
            }else{
                aux = aux.getSiguienteVertice();
            }
        }
        return encontrado;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta){
        boolean exito = false;
        NodoVert verticeOrigen = ubicarVertice(origen);
        NodoVert verticeDestino = ubicarVertice(destino);
        if(verticeOrigen != null && verticeDestino != null){
            if(!existeArco(verticeOrigen, verticeDestino)){
                //agregar verticeDestino a la lista de nodos adyacentes de verticeOrigen y viceversa
                verticeOrigen.setPrimerAdyacente(new NodoAdy(verticeDestino, verticeOrigen.getPrimerAdyacente(), etiqueta));
                verticeDestino.setPrimerAdyacente(new NodoAdy(verticeOrigen, verticeDestino.getPrimerAdyacente(), etiqueta));
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino){
        boolean exito = false;
        if(existeArco(origen, destino)){
            NodoVert verticeOrigen = ubicarVertice(origen);
            NodoVert verticeDestino = ubicarVertice(destino);
            //si existe un arco entre origen y destino elimino a cada uno
            // de la lista de adyacencias del otro.
            eliminarAdyacencia(verticeOrigen, verticeDestino);
            eliminarAdyacencia(verticeDestino, verticeOrigen);
            exito = true;
        }
        return exito;
    }

    public boolean existeArco(Object origen, Object destino){
        boolean existe = false;
        NodoVert verticeOrigen = ubicarVertice(origen);
        NodoVert verticeDestino = ubicarVertice(destino);
        if(verticeOrigen != null && verticeDestino != null){
            NodoAdy ady = verticeOrigen.getPrimerAdyacente();
            //obtengo el primer nodo adyacente de verticeOrigen y lo comparo con verticeDestino
            while(!existe && ady != null){
                //si el contenido del nodo adyacente es igual al de verticeDestino
                //significa que existe un arco entre verticeOrigen y verticeDestino
                existe = ady.getVertice().equals(verticeDestino);
                ady = ady.getSigAdyacente();
            }
        }
        return existe;
    }

    //Método para encontrar el camino más corto entre dos vértices
    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoMasCorto = new Lista();
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            Lista visitados = new Lista();
            Lista caminoActual = new Lista();
            caminoMasCorto = encontrarMejorCamino(nodoOrigen, nodoDestino, visitados, caminoActual, caminoMasCorto, "corto");            
        }
        return caminoMasCorto;
    }

    //Método para encontrar el camino más largo entre dos vértices
    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista caminoMasLargo = new Lista();
        NodoVert nodoOrigen = ubicarVertice(origen);
        NodoVert nodoDestino = ubicarVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            Lista visitados = new Lista();
            Lista caminoActual = new Lista();
            caminoMasLargo = encontrarMejorCamino(nodoOrigen, nodoDestino, visitados, caminoActual, caminoMasLargo, "largo");            
        }
        return caminoMasLargo;
    }
    
    // Método recursivo para encontrar el mejor camino entre dos vértices segun la opcion elegida
    private Lista encontrarMejorCamino(NodoVert nodoActual, NodoVert nodoDestino, Lista visitados,
                                         Lista caminoActual, Lista mejorCamino, String opcion) {
        visitados.insertar(nodoActual, visitados.longitud()+1);
        caminoActual.insertar(nodoActual.getElem(), caminoActual.longitud()+1);
        if(opcion == "corto"){
            if (nodoActual.equals(nodoDestino) && (caminoActual.longitud() < mejorCamino.longitud() || mejorCamino.esVacia())) {
                mejorCamino = caminoActual.clone();
            }else{
                NodoAdy vecino = nodoActual.getPrimerAdyacente();
                while (vecino != null) {
                    if (visitados.localizar(vecino.getVertice()) < 0) {
                        mejorCamino = encontrarMejorCamino(vecino.getVertice(), nodoDestino, visitados, caminoActual, mejorCamino, opcion);
                    }
                    vecino = vecino.getSigAdyacente();
                }
            }
        }else{//opcion == largo
            if (nodoActual.equals(nodoDestino) && caminoActual.longitud() > mejorCamino.longitud()) {
                mejorCamino = caminoActual.clone();
            }else{
                NodoAdy vecino = nodoActual.getPrimerAdyacente();
                while (vecino != null) {
                    if (visitados.localizar(vecino.getVertice()) < 0) {
                        mejorCamino = encontrarMejorCamino(vecino.getVertice(), nodoDestino, visitados, caminoActual, mejorCamino, opcion);
                    }
                    vecino = vecino.getSigAdyacente();
                }
            }
        }
        visitados.eliminar(visitados.localizar(nodoActual));
        caminoActual.eliminar(caminoActual.longitud());
        return mejorCamino;
    }

    public Lista listarEnProfundidad(){
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while(aux != null){
            if(visitados.localizar(aux.getElem()) < 0){
                //si el vertice no fue visitado aun, avanza
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSiguienteVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert nodo, Lista visitados){
        if(nodo != null){
            //marca al vertice nodo como visitado
            visitados.insertar(nodo.getElem(), visitados.longitud()+1);
            NodoAdy ady = nodo.getPrimerAdyacente();
            while(ady != null){
                //visita en profundidad los adyacentes de nodo aun no visitados
                if(visitados.localizar(ady.getVertice().getElem()) < 0){
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista recAnchura(){
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        NodoVert aux = this.inicio;
        while(aux != null){
            if(visitados.localizar(aux.getElem()) < 0){
                anchuraDesde(aux, visitados);
            }
            aux = aux.getSiguienteVertice();
        }
        return visitados;
    }
    private void anchuraDesde(NodoVert nodo, Lista visitados){
        Cola cola = new Cola();
        NodoVert aux;
        NodoAdy auxAdy;
        visitados.insertar(nodo.getElem(), visitados.longitud()+1);
        cola.poner(nodo);
        while(!cola.esVacia()){
            aux = (NodoVert) cola.obtenerFrente();
            cola.sacar();
            auxAdy = aux.getPrimerAdyacente();
            while(auxAdy != null){
                if(visitados.localizar(auxAdy.getVertice().getElem()) < 0){
                    visitados.insertar(auxAdy.getVertice().getElem(), visitados.longitud()+1);
                    cola.poner(auxAdy.getVertice());
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }

    public boolean existeCamino(Object origen, Object destino){
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVert auxOrigen = null;
        NodoVert auxDestino = null;
        NodoVert aux = this.inicio;

        while((auxOrigen == null || auxDestino == null) && aux != null){
            if(aux.getElem().equals(origen)) auxOrigen = aux;
            if(aux.getElem().equals(destino)) auxDestino = aux;
            aux = aux.getSiguienteVertice();
        }
        if(auxOrigen != null && auxDestino != null){
            //si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxOrigen, destino, visitados);
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert nodo, Object destino, Lista visitados){
        boolean exito = false;
        if(nodo != null){
            //si vertice nodo es el destino: HAY CAMINO!
            if(nodo.getElem().equals(destino)){
                exito = true;
            }else{
                //si no es el destino verifica si hay camino entre nodo y destino
                visitados.insertar(nodo.getElem(), visitados.longitud()+1);
                NodoAdy ady = nodo.getPrimerAdyacente();
                while(!exito && ady != null){
                    if(visitados.localizar(ady.getVertice().getElem()) < 0){
                        exito = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public boolean esVacio(){
        return this.inicio == null;
    }

    public void vaciar(){
        this.inicio = null;
    }

    public Grafo_Etiquetado clone(){ //implementacion iterativa
        Grafo_Etiquetado clon = new Grafo_Etiquetado();
        if(this.inicio != null){
            clon.inicio = new NodoVert(this.inicio.getElem(), null);
            NodoVert aux = this.inicio;
            NodoVert auxClon = clon.inicio;
            aux = aux.getSiguienteVertice();
            //clona los nodos a medida que los recorre como una lista
            while(aux != null){
                auxClon.setPrimerAdyacente(clonarAdyacentes(aux.getPrimerAdyacente())); //clona adyacentes
                auxClon.setSiguienteVertice(new NodoVert(aux.getElem(), null));
                aux = aux.getSiguienteVertice();
                auxClon = auxClon.getSiguienteVertice();
            }
        }
        return clon;
    }

    private NodoAdy clonarAdyacentes(NodoAdy nodoAdy){ //implementacion recursiva
        NodoAdy nodoClon = null;
        if(nodoAdy != null){
            nodoClon = new NodoAdy(nodoAdy.getVertice(), clonarAdyacentes(nodoAdy.getSigAdyacente()), nodoAdy.getEtiqueta());
        }
        return nodoClon;
    }

    public String toString(){
        String cadena = "";
        NodoVert aux = this.inicio;
        while(aux != null){
            cadena += aux.getElem() + " --> Adys: " + cadenaAdyacentes(aux) + '\n';
            aux = aux.getSiguienteVertice();
        }
        return cadena;
    }

    private String cadenaAdyacentes(NodoVert nodo){
        String cadenaAdys = "";
        NodoAdy auxAdy = nodo.getPrimerAdyacente();
        while(auxAdy != null){
            cadenaAdys += auxAdy.getVertice().getElem() + ", ";
            auxAdy = auxAdy.getSigAdyacente();
        }
        return cadenaAdys;
    }
}