package grafos;

import lineales.dinamicas.Lista;

public class Di_Grafo_Etiquetado{
    private NodoVert inicio;

    public Di_Grafo_Etiquetado(){
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
        //1 encontrar vertice
        NodoVert aux = this.inicio;
        NodoVert nodoEliminar = ubicarVertice(elem);
        //2 eliminar vertice de la lista de vertices
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
            //eliminar arcos salientes?
            //eliminar vertice de las listas de adyacencias de otros vertices
        }


        return exito;

        /* boolean exito = false;
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
        return exito; */
    }

    public boolean existeVertice(Object buscado){
        boolean encontrado = false;
        NodoVert aux = this.inicio;
        while(aux != null){
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
                //agregar verticeDestino a la lista de nodos adyacentes de verticeOrigen
                NodoAdy nodoAdyacente = verticeOrigen.getPrimerAdyacente();
                verticeOrigen.setPrimerAdyacente(new NodoAdy(verticeDestino, nodoAdyacente, etiqueta));
                exito = true;
            }
        }
        return exito;
    }

    private boolean existeArco(NodoVert verticeOrigen, NodoVert verticeDestino){
        boolean existe = false;
        //precondicion ambos nodos son distintos de nulo
        NodoAdy ady = verticeOrigen.getPrimerAdyacente();
        //obtengo el primer nodo adyacente de verticeOrigen y lo comparo con verticeDestino
        while(!existe && ady != null){
            //si el contenido del nodo adyacente es igual al de verticeDestino
            //significa que existe un arco entre verticeOrigen y verticeDestino
            existe = ady.getVertice().equals(verticeDestino);
            ady = ady.getSigAdyacente();
        }
        return existe;
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
                if(visitados.localizar(ady.getVertice()) < 0){
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
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
}