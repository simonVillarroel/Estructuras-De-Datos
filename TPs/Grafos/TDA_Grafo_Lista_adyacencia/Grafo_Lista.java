package Grafos.TDA_Grafo_Lista_adyacencia;

import Dinamica.TDA_Cola.Cola;
import Dinamica.TDA_Lista.Lista;
import Grafos.Nodo_Ady;
import Grafos.Nodo_Vertice;

class Grafo_Lista{
    private Nodo_Vertice inicio;

    public Grafo_Lista(){
        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoVertice){
        boolean exito = false;
        Nodo_Vertice aux = this.ubicaVertice(nuevoVertice);
        if(aux == null){
            this.inicio = new Nodo_Vertice(nuevoVertice, this.inicio);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarVertice(Object elemento){
        boolean exito = false;
        Nodo_Vertice nodoEliminar = ubicaVertice(elemento);
        //verifico si el vertice pertenece al grafo
        if(nodoEliminar != null){
            //primero intento eliminar al vertice del grafo
            exito = eliminarVerticeAux(nodoEliminar);
            //luego si hubo exito, elimino todos los arcos asociados al nodo
            Nodo_Vertice aux = this.inicio;
            while(aux != null){
                if(existeArco(aux, nodoEliminar)){
                    eliminarArco(aux, nodoEliminar);
                }
                aux = aux.getSigVertice();
            }
        }
        return exito;
    }
    private boolean eliminarVerticeAux(Nodo_Vertice nodo){
        boolean eliminado = false;
        Nodo_Vertice aux = this.inicio;
        //Si el nodo a eliminar es el inicial:
        if(aux == nodo){
            this.inicio = nodo.getSigVertice();
        }else{
            //busco en la lista de vertices al nodoVertice
            while(!eliminado && aux != null){
                if(aux.getSigVertice() == nodo){
                    aux.setSigVertice(nodo.getSigVertice());
                    eliminado = true;
                }
            }
        }
        return eliminado;
    }

    public boolean existeVertice(Object buscado){
        boolean existe = false;
        Nodo_Vertice aux = this.inicio;
        while(!existe && aux != null){
            existe = aux.getElemento().equals(buscado);
            aux = aux.getSigVertice();
        }
        return existe;
    }

    private Nodo_Vertice ubicaVertice(Object buscado){
        Nodo_Vertice resultado = null;
        Nodo_Vertice aux = this.inicio;
        while(aux != null && resultado == null){
            if(aux.getElemento().equals(buscado)){
                resultado = aux;
            }
            aux = aux.getSigVertice();
        }
        return resultado;
    }

    public boolean insertarArco(Object elemento1, Object elemento2){
        boolean exito = false;
        Nodo_Vertice nodo1 = ubicaVertice(elemento1);
        Nodo_Vertice nodo2 = ubicaVertice(elemento2);
        if(nodo1 != null && nodo2 != null){
            if(!existeArco(nodo1, nodo2)){
                nodo1.setPrimerAdy(new Nodo_Ady(nodo2, nodo1.getPrimerAdy()));
                nodo2.setPrimerAdy(new Nodo_Ady(nodo1, nodo2.getPrimerAdy()));
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object elemento1, Object elemento2){
        boolean exito = false;
        Nodo_Vertice nodo1 = ubicaVertice(elemento1); 
        Nodo_Vertice nodo2 = ubicaVertice(elemento2);
        if(nodo1 != null && nodo2 != null){
            if(existeArco(elemento1, elemento2)){
                eliminarArcoAux(nodo1, nodo2);//elimina a nodo2 de adyacentes de nodo1
                eliminarArcoAux(nodo2, nodo1);//elimina a nodo1 de adyacentes de nodo2
                exito = true;
            }
        }
        return exito;
    }
    private void eliminarArcoAux(Nodo_Vertice nodoA, Nodo_Vertice nodoB){
        boolean eliminado = false;
        Nodo_Ady nodoAdy = nodoA.getPrimerAdy();
        if(nodoA.getPrimerAdy().getVertice() == nodoB){
            nodoA.setPrimerAdy(nodoAdy.getSigAdy());
            System.out.println("Entrada if");
        }else{
            System.out.println("Entrada else");
            while(!eliminado){
                if(nodoAdy.getSigAdy().getVertice() == nodoB){
                    nodoAdy.setSigAdy(nodoAdy.getSigAdy().getSigAdy());
                    eliminado = true;
                }
                nodoAdy = nodoAdy.getSigAdy();
            }
        }
    }

    public boolean existeArco(Object elemento1, Object elemento2){
        boolean existe = false;
        Nodo_Vertice nodo1 = ubicaVertice(elemento1);
        Nodo_Vertice nodo2 = ubicaVertice(elemento2);
        if(nodo1 != null && nodo2 != null){
            Nodo_Ady auxAdy = nodo1.getPrimerAdy();
            //obtengo el primer nodo adyacente del nodo1 y lo comparo con nodo2
            while(auxAdy != null && !existe){
                //si el contenido del nodo adyacente es igual al del nodo2
                //significa que existe un arco entre ambos nodos 
                //(nodo2 pertenece a la lista de adyacentes de nodo1 y viceversa)
                existe = auxAdy.getVertice().getElemento().equals(nodo2.getElemento());
                auxAdy = auxAdy.getSigAdy();
            }
        }
        return existe;
    }

    public boolean existeCamino(Object origen, Object destino){
        boolean exito = false;
        //verifica si ambos vertices existen
        Nodo_Vertice auxOrigen = null;
        Nodo_Vertice auxDestino = null;
        Nodo_Vertice aux = this.inicio;
        while((auxOrigen == null || auxDestino == null) && aux != null){
            if(aux.getElemento().equals(origen)) auxOrigen = aux;
            if(aux.getElemento().equals(destino)) auxDestino = aux;
            aux = aux.getSigVertice();
        }
        if(auxOrigen != null && auxDestino != null){
            //si ambos vertices existen busca si existe camino entre ambos
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxOrigen, destino, visitados);
        }
        return exito;
    }
    private boolean existeCaminoAux(Nodo_Vertice nodo, Object destino, Lista visitados){
        boolean exito = false;
        if(nodo != null){
            //si vertice nodo es el destino: HAY CAMINO!
            if(nodo.getElemento().equals(destino)){
                exito = true;
            }else{
                //si no es el destino verifica si hay camino entre nodo y destino
                visitados.insertar(nodo.getElemento(), visitados.longitud()+1);
                Nodo_Ady ady = nodo.getPrimerAdy();
                while(!exito && ady != null){
                    if(visitados.localizar(ady.getVertice().getElemento()) < 0){
                        exito = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return exito;
    }

    //Método para encontrar el camino más corto entre dos vértices
    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoMasCorto = new Lista();
        Nodo_Vertice nodoOrigen = ubicaVertice(origen);
        Nodo_Vertice nodoDestino = ubicaVertice(destino);
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
        Nodo_Vertice nodoOrigen = ubicaVertice(origen);
        Nodo_Vertice nodoDestino = ubicaVertice(destino);
        if (nodoOrigen != null && nodoDestino != null) {
            Lista visitados = new Lista();
            Lista caminoActual = new Lista();
            caminoMasLargo = encontrarMejorCamino(nodoOrigen, nodoDestino, visitados, caminoActual, caminoMasLargo, "largo");            
        }
        return caminoMasLargo;
    }
    
    // Método recursivo para encontrar el mejor camino entre dos vértices segun la opcion elegida
    private Lista encontrarMejorCamino(Nodo_Vertice nodoActual, Nodo_Vertice nodoDestino, Lista visitados,
                                         Lista caminoActual, Lista mejorCamino, String opcion) {
        visitados.insertar(nodoActual, visitados.longitud()+1);
        caminoActual.insertar(nodoActual.getElemento(), caminoActual.longitud()+1);
        if(opcion == "corto"){
            if (nodoActual.equals(nodoDestino) && (caminoActual.longitud() < mejorCamino.longitud() || mejorCamino.esVacia())) {
                mejorCamino = caminoActual.clone();
            }else{
                Nodo_Ady vecino = nodoActual.getPrimerAdy();
                while (vecino != null) {
                    if (visitados.localizar(vecino.getVertice()) < 0) {
                        mejorCamino = encontrarMejorCamino(vecino.getVertice(), nodoDestino, visitados, caminoActual, mejorCamino, opcion);
                    }
                    vecino = vecino.getSigAdy();
                }
            }
        }else{//opcion == largo
            if (nodoActual.equals(nodoDestino) && caminoActual.longitud() > mejorCamino.longitud()) {
                mejorCamino = caminoActual.clone();
            }else{
                Nodo_Ady vecino = nodoActual.getPrimerAdy();
                while (vecino != null) {
                    if (visitados.localizar(vecino.getVertice()) < 0) {
                        mejorCamino = encontrarMejorCamino(vecino.getVertice(), nodoDestino, visitados, caminoActual, mejorCamino, opcion);
                    }
                    vecino = vecino.getSigAdy();
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
        Nodo_Vertice aux = this.inicio;
        while(aux != null){
            if(visitados.localizar(aux.getElemento()) < 0){
                //si el vertice no fue visitado aun, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }
    private void listarEnProfundidadAux(Nodo_Vertice nodo, Lista visitados){
        if(nodo != null){
            //marca al vertice nodo como visitado
            visitados.insertar(nodo.getElemento(), visitados.longitud()+1);
            Nodo_Ady ady = nodo.getPrimerAdy();
            while(ady != null){
                //visita en profundidad los adyacentes de nodo aun no visitados
                if(visitados.localizar(ady.getVertice().getElemento()) < 0){
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdy();
            }
        }
    }

    public Lista recAnchura(){
        Lista visitados = new Lista();
        //define un vertice donde comenzar a recorrer
        Nodo_Vertice aux = this.inicio;
        while(aux != null){
            if(visitados.localizar(aux.getElemento()) < 0){
                anchuraDesde(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }
    private void anchuraDesde(Nodo_Vertice nodo, Lista visitados){
        Cola cola = new Cola();
        Nodo_Vertice aux;
        Nodo_Ady auxAdy;
        visitados.insertar(nodo.getElemento(), visitados.longitud()+1);
        cola.poner(nodo);
        while(!cola.esVacia()){
            aux = (Nodo_Vertice) cola.obtenerFrente();
            cola.sacar();
            auxAdy = aux.getPrimerAdy();
            while(auxAdy != null){
                if(visitados.localizar(auxAdy.getVertice().getElemento()) < 0){
                    visitados.insertar(auxAdy.getVertice().getElemento(), visitados.longitud()+1);
                    cola.poner(auxAdy.getVertice());
                }
                auxAdy = auxAdy.getSigAdy();
            }
        }
    }

    public boolean esVacio(){
        return this.inicio == null;
    }
    
    public void vaciar(){
        this.inicio = null;
    }

    public Grafo_Lista clone(){
        Grafo_Lista clon = new Grafo_Lista();
        clon = insertarVertices(this.inicio, clon);
        clon = insertarArcos(this.inicio, clon);
        return clon;
    }
    private Grafo_Lista insertarVertices(Nodo_Vertice nodoAux, Grafo_Lista clon){
        if(nodoAux != null){
            clon = insertarVertices(nodoAux.getSigVertice(), clon);
            clon.insertarVertice(nodoAux.getElemento());
        }
        return clon;
    }
    private Grafo_Lista insertarArcos(Nodo_Vertice nodoAux, Grafo_Lista clon){

        if(nodoAux != null){
            clon = insertarVertices(nodoAux.getSigVertice(), clon);
            clon.insertarVertice(nodoAux.getElemento());
        }
        return clon;
    }
}