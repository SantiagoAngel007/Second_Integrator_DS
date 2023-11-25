package com.example.busquedadeltesoro;
import GraphicElements.ItemType;
import Graphs.ColorType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

import java.util.*;
public class GraphController<K extends Comparable<K>> {

    private final ArrayList<Vertex<K>> vertexArrayList = new ArrayList<>();
    private final ArrayList<Edge<K>> edgeArrayList = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> matrizAdyacencia;
    private int dimension;
    private PlayerGenerator<K> playerGenerator;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    Circle circle;


    public GraphController() {
        playerGenerator = new PlayerGenerator<>(0,0,null);
    }


    public Vertex<K> addVertex(K key, double x, double y, String name, int item) {
        if (searchVertex(key) == null) {

            ItemType itemType = null;

            if(item==0){
                itemType = ItemType.TREASURE;
            } else if (item==1) {
                itemType = ItemType.COIN;
            }else if (item==2) {
                itemType = ItemType.HEART;
            }else if (item==3) {
                itemType = ItemType.TRAP;
            }else if (item==4) {
                itemType = ItemType.MAP;
            } else if (item==-1) {
                itemType = ItemType.INITIAL;
            } else {
                itemType = ItemType.NOTHING;
            }

            Vertex<K> vertex = new Vertex<>(key, new ArrayList<>(), x, y, name, itemType);
            vertexArrayList.add(vertex);
            return vertex;
        } else {
            return null;
        }
    }

    public Edge<K> addEdge(K vertex_1, K vertex_2, int weight) {
        Vertex<K> originVertex = searchVertex(vertex_1);
        Vertex<K> destinyVertex = searchVertex(vertex_2);

        if (originVertex != null && destinyVertex != null) {
            Edge<K> edge = new Edge<>(originVertex, destinyVertex, weight);
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

        for (Vertex<K> adjacentVertex : vertexArrayList) {
            adjacentVertex.setColor(ColorType.WHITE);
            adjacentVertex.setDistance(Integer.MAX_VALUE);
            adjacentVertex.setPrevious(null);
        }

        vertex.setColor(ColorType.GREY);
        vertex.setDistance(0);
        vertex.setPrevious(null);

        Queue<Vertex<K>> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertex);

        while (!vertexQueue.isEmpty()) {
            Vertex<K> u = vertexQueue.poll();
            for (Edge<K> edge : edgeArrayList) {
                if (edge.getOrigin() == u) {
                    Vertex<K> neighbor = edge.getDestiny();
                    if (neighbor.getColor() == ColorType.WHITE) {
                        neighbor.setColor(ColorType.GREY);
                        neighbor.setDistance(u.getDistance() + 1);
                        neighbor.setPrevious(u);
                        vertexQueue.add(neighbor);
                    }
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

//    public String algoritmoDijkstra(Vertex<K> inicio) {
//        ArrayList<Edge<K>> aristasVisitadas = new ArrayList<>();
//        ArrayList<Vertex<K>> verticesVisitados = new ArrayList<>();
//        encontrarCaminoMasCorto(inicio, verticesVisitados, aristasVisitadas);
//        return muestraRecorrido(verticesVisitados, aristasVisitadas);
//    }
//
//    private void encontrarCaminoMasCorto(Vertex<K> origen, ArrayList<Vertex<K>> verticesVisitados, ArrayList<Edge<K>> aristasVisitadas) {
//        HashMap<Vertex<K>, Integer> distancias = new HashMap<>();
//        HashMap<Vertex<K>, Vertex<K>> padres = new HashMap<>();
//
//        // Inicializar distancias con valor infinito para todos los vértices excepto el origen
//        for (Vertex<K> v : vertexArrayList) {
//            if (v == origen) {
//                distancias.put(v, 0);
//            } else {
//                distancias.put(v, Integer.MAX_VALUE);
//            }
//        }
//
//        PriorityQueue<Vertex<K>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
//        colaPrioridad.offer(origen);
//
//        while (!colaPrioridad.isEmpty()) {
//            Vertex<K> actual = colaPrioridad.poll();
//            // Verificar si el vértice actual ya ha sido visitado
//            if (verticesVisitados.contains(actual)) {
//                continue;
//            }
//            verticesVisitados.add(actual);
//            for (Vertex<K> vecino : obtenerVecinos(actual)) {
//                int distancia = distancias.get(actual) + obtenerPesoArista(actual, vecino);
//                if (distancia < distancias.get(vecino)) {
//                    distancias.put(vecino, distancia);
//                    padres.put(vecino, actual);
//                    colaPrioridad.offer(vecino);
//                }
//            }
//        }
//        // Construir la lista de aristas visitadas a partir de los padres
//        for (Vertex<K> vertice : verticesVisitados) {
//            Vertex<K> padre = padres.get(vertice);
//            if (padre != null) {
//                Edge<K> arista = aristaConectada(padre, vertice);
//                aristasVisitadas.add(arista);
//            }
//        }
//    }

//    public int dijkstraAlgorithmInt(Vertex<K> start, Vertex<K> destination) {
//        ArrayList<Edge<K>> visitedEdges = new ArrayList<>();
//        ArrayList<Vertex<K>> visitedVertices = new ArrayList<>();
//        return findShortestPathInt(start, destination, visitedVertices, visitedEdges);
//    }
//
//    private int findShortestPathInt(Vertex<K> start, Vertex<K> destination, ArrayList<Vertex<K>> visitedVertices, ArrayList<Edge<K>> visitedEdges) {
//        HashMap<Vertex<K>, Integer> distances = new HashMap<>();
//        HashMap<Vertex<K>, Vertex<K>> parents = new HashMap<>();
//
//        // Inicializar distancias con valor infinito para todos los vértices excepto el inicio
//        for (Vertex<K> v : vertexArrayList) {
//            if (v == start) {
//                distances.put(v, 0);
//            } else {
//                distances.put(v, Integer.MAX_VALUE);
//            }
//        }
//
//        PriorityQueue<Vertex<K>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
//        priorityQueue.offer(start);
//
//        while (!priorityQueue.isEmpty()) {
//            Vertex<K> current = priorityQueue.poll();
//
//            // Verificar si el vértice actual ya ha sido visitado
//            if (visitedVertices.contains(current)) {
//                continue;
//            }
//
//            visitedVertices.add(current);
//
//            // Si llegamos al destino, terminamos la búsqueda
//            if (current == destination) {
//                break;
//            }
//
//            for (Vertex<K> neighbor : getNeighbors(current)) {
//                int distance = distances.get(current) + getEdgeWeight(current, neighbor);
//
//                if (distance < distances.get(neighbor)) {
//                    distances.put(neighbor, distance);
//                    parents.put(neighbor, current);
//                    priorityQueue.offer(neighbor);
//                }
//            }
//        }
//
//        // Sumar las ponderaciones de las aristas visitadas
//        int sumOfWeights = 0;
//        for (Vertex<K> vertex : visitedVertices) {
//            Vertex<K> parent = parents.get(vertex);
//            if (parent != null) {
//                Edge<K> edge = getConnectedEdge(parent, vertex);
//                visitedEdges.add(edge);
//                sumOfWeights += edge.getWeight();
//            }
//        }
//
//        return sumOfWeights;
//    }


    public int dijkstraAlgorithmInt(Vertex<K> start, Vertex<K> destination) {
        ArrayList<Vertex<K>> visitedVertices = new ArrayList<>();
        return findShortestPathInt(start, destination, visitedVertices);
    }

    private int findShortestPathInt(Vertex<K> start, Vertex<K> destination, ArrayList<Vertex<K>> visitedVertices) {
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

        // Sumar las ponderaciones de las aristas visitadas
        int sumOfWeights = 0;
        Vertex<K> current = destination;
        while (current != start) {
            Vertex<K> parent = parents.get(current);
            Edge<K> edge = getConnectedEdge(parent, current);
            sumOfWeights += edge.getWeight();
            current = parent;
        }

        return sumOfWeights;
    }


    public List<Edge<K>> dijkstraAlgorithmEdge(Vertex<K> start, Vertex<K> destination) {
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
        List<Edge<K>> visitedEdges = new ArrayList<>();
        Vertex<K> current = destination;
        while (current != start) {
            Vertex<K> parent = parents.get(current);
            Edge<K> edge = getConnectedEdge(parent, current);
            visitedEdges.add(edge);
            current = parent;
        }

        Collections.reverse(visitedEdges);
        return visitedEdges;
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



    public void mostrarMatrizAdyacencia() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrizAdyacencia.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Crear un grafo dirigido
        GraphController<String> graph = new GraphController<>();

        // Agregar vértices
        graph.addVertex("A",100,100,"A",4);
        graph.addVertex("B",200,100,"B",4);
        graph.addVertex("C",300,100,"C",4);
//        graph.addVertex("D",400,100,"D",4);
//        graph.addVertex("E",500,100,"E",4);
//        graph.addVertex("F",500,100,"F",4);


        graph.addEdge("A", "B", 4);
        graph.addEdge("B", "A", 2);
        graph.addEdge("B", "C", 1);
//        graph.addEdge("B", "D", 5);
//        graph.addEdge("C", "B", 1);
//        graph.addEdge("C", "D", 8);
//        graph.addEdge("C", "E", 9);
//        graph.addEdge("D", "F", 6);
//        graph.addEdge("D", "E", 2);
//        graph.addEdge("E", "F", 3);

        Vertex<String> v1 = graph.searchVertex("A");
        Vertex<String> v6 = graph.searchVertex("C");


        // Mostrar la matriz de adyacencia
        graph.crearMatrizAdyacencia();
//        System.out.println("Matriz de Adyacencia:");
//        graph.mostrarMatrizAdyacencia();

        // Realizar una búsqueda en anchura desde el vértice "A"
        boolean bfsResult = graph.breadthFirstSearch("A");
        System.out.println("BFS Result: " + bfsResult);

        int dijkstraResultInt = graph.dijkstraAlgorithmInt(v1,v6);
        System.out.println("Dijkstra Result: " + dijkstraResultInt);

        ArrayList<Edge<String>> dijkstraResulEdge = (ArrayList<Edge<String>>) graph.dijkstraAlgorithmEdge(v1,v6);
        System.out.println("Dijkstra Result: " + dijkstraResulEdge);

//        // Realizar el algoritmo de Dijkstra desde el vértice "A"
//        String dijkstraResult = graph.algoritmoDijkstra(v1);
//        System.out.println("Dijkstra Result: " + dijkstraResult);
//
        String dijkstraResultEn = graph.dijkstraAlgorithm(v1,v6);
        System.out.println("Dijkstra Result: " + dijkstraResultEn);

    }

    public ArrayList<Vertex<K>> getVertexArrayList() {
        return vertexArrayList;
    }

    public ArrayList<Edge<K>> getEdgeArrayList() {
        return edgeArrayList;
    }
}
