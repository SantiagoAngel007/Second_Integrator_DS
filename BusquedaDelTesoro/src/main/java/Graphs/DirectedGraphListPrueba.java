package Graphs;
import java.util.*;
public class DirectedGraphListPrueba<K extends Comparable<K>> {

    private final ArrayList<Vertex<K>> vertexArrayList = new ArrayList<>();
    private final ArrayList<Edge<K>> edgeArrayList = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> matrizAdyacencia;
    private int dimension;

    public Vertex<K> addVertex(K key) {
        if (searchVertex(key) == null) {
            Vertex<K> vertex = new Vertex<>(key, new ArrayList<>());
            vertexArrayList.add(vertex);
            return vertex;
        } else {
            return null;
        }
    }

    public Edge<K> addEdge(K vertex_1, K vertex_2, int peso) {
        Vertex<K> originVertex = searchVertex(vertex_1);
        Vertex<K> destinyVertex = searchVertex(vertex_2);

        if (originVertex != null && destinyVertex != null) {
            Edge<K> edge = new Edge<>(originVertex, destinyVertex, peso);
            edgeArrayList.add(edge);
            return edge;
        } else {
            return null;
        }
    }

    public void clear() {
        vertexArrayList.clear();
        edgeArrayList.clear();
    }

    public Vertex<K> searchVertex(K key) {
        for (Vertex<K> g : vertexArrayList) {
            if (g.getKey().equals(key)) {
                return g;
            }
        }
        return null;
    }

    public void crearMatrizAdyacencia() {
        dimension = vertexArrayList.size();
        matrizAdyacencia = new ArrayList<>();

        // Inicializar la matriz de adyacencia con ceros
        for (int i = 0; i < dimension; i++) {
            matrizAdyacencia.add(new ArrayList<>());
            for (int j = 0; j < dimension; j++) {
                matrizAdyacencia.get(i).add(0);
            }
        }

        // Llenar la matriz de adyacencia con las conexiones existentes
        for (Edge<K> arista : edgeArrayList) {
            Vertex<K> origen = arista.getOrigin();
            Vertex<K> destino = arista.getDestiny();
            int indiceOrigen = vertexArrayList.indexOf(origen);
            int indiceDestino = vertexArrayList.indexOf(destino);
            matrizAdyacencia.get(indiceOrigen).set(indiceDestino, arista.getWeight());
            matrizAdyacencia.get(indiceDestino).set(indiceOrigen, arista.getWeight());
        }
    }

    public boolean breadthFirstSearch(K key) {
        Boolean flag = true;
        Vertex<K> vertex = searchVertex(key);
        if (vertex == null) {
            return false;
        }

        for (Vertex<K> adjacentvertex : vertexArrayList) {
            adjacentvertex.setColor(ColorType.WHITE);
            adjacentvertex.setDistance(Integer.MAX_VALUE);
            adjacentvertex.setPrevious(null);
        }

        vertex.setColor(ColorType.GREY);
        vertex.setDistance(0);
        vertex.setPrevious(null);

        Queue<Vertex<K>> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertex);

        while (!vertexQueue.isEmpty()) {
            Vertex<K> u = vertexQueue.poll();
            for (Vertex<K> dequeue : u.getConnectedVertex()) {
                if (dequeue.getColor() == ColorType.WHITE) {
                    dequeue.setColor(ColorType.GREY);
                    dequeue.setDistance(u.getDistance() + 1);
                    dequeue.setPrevious(u);
                    vertexQueue.add(dequeue);
                }
            }
            u.setColor(ColorType.BLACK);
        }

        for (Vertex<K> g : vertexArrayList) {
            if (g.getColor() == ColorType.WHITE) {
                flag = false;
            }
        }

        return flag;
    }

    public String algoritmoDijkstra(Vertex<K> inicio) {
        ArrayList<Edge<K>> aristasVisitadas = new ArrayList<>();
        ArrayList<Vertex<K>> verticesVisitados = new ArrayList<>();
        encontrarCaminoMasCorto(inicio, verticesVisitados, aristasVisitadas);
        return muestraRecorrido(verticesVisitados, aristasVisitadas);
    }

    private void encontrarCaminoMasCorto(Vertex<K> origen, ArrayList<Vertex<K>> verticesVisitados, ArrayList<Edge<K>> aristasVisitadas) {
        HashMap<Vertex<K>, Integer> distancias = new HashMap<>();
        HashMap<Vertex<K>, Vertex<K>> padres = new HashMap<>();

        // Inicializar distancias con valor infinito para todos los vértices excepto el origen
        for (Vertex<K> v : vertexArrayList) {
            if (v == origen) {
                distancias.put(v, 0);
            } else {
                distancias.put(v, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Vertex<K>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
        colaPrioridad.offer(origen);

        while (!colaPrioridad.isEmpty()) {
            Vertex<K> actual = colaPrioridad.poll();
            // Verificar si el vértice actual ya ha sido visitado
            if (verticesVisitados.contains(actual)) {
                continue;
            }
            verticesVisitados.add(actual);
            for (Vertex<K> vecino : obtenerVecinos(actual)) {
                int distancia = distancias.get(actual) + obtenerPesoArista(actual, vecino);
                if (distancia < distancias.get(vecino)) {
                    distancias.put(vecino, distancia);
                    padres.put(vecino, actual);
                    colaPrioridad.offer(vecino);
                }
            }
        }
        // Construir la lista de aristas visitadas a partir de los padres
        for (Vertex<K> vertice : verticesVisitados) {
            Vertex<K> padre = padres.get(vertice);
            if (padre != null) {
                Edge<K> arista = aristaConectada(padre, vertice);
                aristasVisitadas.add(arista);
            }
        }
    }

    public String dijkstraAlgorithm(Vertex<K> start, Vertex<K> destination) {
        ArrayList<Edge<K>> visitedEdges = new ArrayList<>();
        ArrayList<Vertex<K>> visitedVertices = new ArrayList<>();
        findShortestPath(start, destination, visitedVertices, visitedEdges);
        return displayPath(visitedVertices, visitedEdges);
    }

    private void findShortestPath(Vertex<K> start, Vertex<K> destination, ArrayList<Vertex<K>> visitedVertices, ArrayList<Edge<K>> visitedEdges) {
        HashMap<Vertex<K>, Integer> distances = new HashMap<>();
        HashMap<Vertex<K>, Vertex<K>> parents = new HashMap<>();

        // Inicializar distancias con valor infinito para todos los vértices excepto el inicio
        for (Vertex<K> v : vertexArrayList) {
            if (v == start) {
                distances.put(v, 0);
            } else {
                distances.put(v, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Vertex<K>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        priorityQueue.offer(start);

        while (!priorityQueue.isEmpty()) {
            Vertex<K> current = priorityQueue.poll();

            // Verificar si el vértice actual ya ha sido visitado
            if (visitedVertices.contains(current)) {
                continue;
            }

            visitedVertices.add(current);

            // Si llegamos al destino, terminamos la búsqueda
            if (current == destination) {
                break;
            }

            for (Vertex<K> neighbor : getNeighbors(current)) {
                int distance = distances.get(current) + getEdgeWeight(current, neighbor);

                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    parents.put(neighbor, current);
                    priorityQueue.offer(neighbor);
                }
            }
        }

        // Construir la lista de aristas visitadas a partir de los padres
        for (Vertex<K> vertex : visitedVertices) {
            Vertex<K> parent = parents.get(vertex);
            if (parent != null) {
                Edge<K> edge = getConnectedEdge(parent, vertex);
                visitedEdges.add(edge);
            }
        }
    }

    public Edge<K> getConnectedEdge(Vertex<K> origen, Vertex<K> destino) {
        for (Edge<K> a : edgeArrayList) {
            if ((a.getOrigin() == origen && a.getDestiny() == destino) || (a.getOrigin() == destino && a.getDestiny() == origen)) {
                return a;
            }
        }
        return null;
    }

    private List<Vertex<K>> getNeighbors(Vertex<K> vertice) {
        List<Vertex<K>> vecinos = new ArrayList<>();
        int indice = vertexArrayList.indexOf(vertice);
        for (int i = 0; i < dimension; i++) {
            if (matrizAdyacencia.get(indice).get(i) != 0) {
                vecinos.add(vertexArrayList.get(i));
            }
        }
        return vecinos;
    }

    private int getEdgeWeight(Vertex<K> origen, Vertex<K> destino) {
        int indiceOrigen = vertexArrayList.indexOf(origen);
        int indiceDestino = vertexArrayList.indexOf(destino);
        return matrizAdyacencia.get(indiceOrigen).get(indiceDestino);
    }

    private String displayPath(ArrayList<Vertex<K>> verticesVisitados, ArrayList<Edge<K>> aristasVisitadas) {
        StringBuilder recorrido = new StringBuilder();
        for (int i = 0; i < verticesVisitados.size(); i++) {
            Vertex<K> nodo = verticesVisitados.get(i);
            recorrido.append(nodo.getKey());
            if (i < verticesVisitados.size() - 1) {
                recorrido.append(" -> ");
            }
        }
        //coloreaGrafo(verticesVisitados, aristasVisitadas);
        return "[" + recorrido + "]";
    }

    public Edge<K> aristaConectada(Vertex<K> origen, Vertex<K> destino) {
        for (Edge<K> a : edgeArrayList) {
            if ((a.getOrigin() == origen && a.getDestiny() == destino) || (a.getOrigin() == destino && a.getDestiny() == origen)) {
                return a;
            }
        }
        return null;
    }

    private List<Vertex<K>> obtenerVecinos(Vertex<K> vertice) {
        List<Vertex<K>> vecinos = new ArrayList<>();
        int indice = vertexArrayList.indexOf(vertice);
        for (int i = 0; i < dimension; i++) {
            if (matrizAdyacencia.get(indice).get(i) != 0) {
                vecinos.add(vertexArrayList.get(i));
            }
        }
        return vecinos;
    }

    private int obtenerPesoArista(Vertex<K> origen, Vertex<K> destino) {
        int indiceOrigen = vertexArrayList.indexOf(origen);
        int indiceDestino = vertexArrayList.indexOf(destino);
        return matrizAdyacencia.get(indiceOrigen).get(indiceDestino);
    }

    private String muestraRecorrido(ArrayList<Vertex<K>> verticesVisitados, ArrayList<Edge<K>> aristasVisitadas) {
        StringBuilder recorrido = new StringBuilder();
        for (int i = 0; i < verticesVisitados.size(); i++) {
            Vertex<K> nodo = verticesVisitados.get(i);
            recorrido.append(nodo.getKey());
            if (i < verticesVisitados.size() - 1) {
                recorrido.append(" -> ");
            }
        }
        //coloreaGrafo(verticesVisitados, aristasVisitadas);
        return "[" + recorrido + "]";
    }

    public static void main(String[] args) {
        // Crear un grafo dirigido
        DirectedGraphListPrueba<String> graph = new DirectedGraphListPrueba<>();

        // Agregar vértices
        Vertex<String> v1 = graph.addVertex("A");
        Vertex<String> v2 = graph.addVertex("B");
        Vertex<String> v3 = graph.addVertex("C");
        Vertex<String> v4 = graph.addVertex("D");
        Vertex<String> v5 = graph.addVertex("E");
        Vertex<String> v6 = graph.addVertex("F");

//        graph.addVertex("A");
//        graph.addVertex("B");
//        graph.addVertex("C");

        // Agregar aristas
//        graph.addEdge("A", "B", 1);
//        graph.addEdge("B", "C", 2);
//        graph.addEdge("A", "C", 3);

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "B", 1);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 9);
        graph.addEdge("D", "F", 6);
        graph.addEdge("D", "E", 2);
        graph.addEdge("E", "F", 3);


        // Mostrar la matriz de adyacencia
        graph.crearMatrizAdyacencia();
        System.out.println("Matriz de Adyacencia:");
        graph.mostrarMatrizAdyacencia();

        // Realizar una búsqueda en anchura desde el vértice "A"
        boolean bfsResult = graph.breadthFirstSearch("A");
        System.out.println("BFS Result: " + bfsResult);

        // Realizar el algoritmo de Dijkstra desde el vértice "A"
        String dijkstraResult = graph.algoritmoDijkstra(v1);
        System.out.println("Dijkstra Result: " + dijkstraResult);

        String dijkstraResultEn = graph.dijkstraAlgorithm(v1,v6);
        System.out.println("Dijkstra Result: " + dijkstraResultEn);

    }

    private void mostrarMatrizAdyacencia() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrizAdyacencia.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

}