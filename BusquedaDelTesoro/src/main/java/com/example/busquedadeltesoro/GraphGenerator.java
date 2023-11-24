package com.example.busquedadeltesoro;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphGenerator <K extends Comparable<K>> {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private PlayerGenerator<String> playerGenerator;
    GraphController<String> graph = new GraphController<>();

    private ArrayList<Vertex<String>> vertexArrayList = new ArrayList<>();
    private ArrayList<Edge<String>> edgeArrayList = new ArrayList<>();

    public GraphGenerator(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
//        vertexArrayList = graph.getVertexArrayList();
//        edgeArrayList = graph.getEdgeArrayList();
    }

    public void paint(){
        generateMap();
        drawMap();
    }

    private void generateMap() {

        graph.addVertex("A0", 807, 450, "A0",-1);

        graph.addVertex("A1", 772, 420, "A1",4);
        graph.addVertex("B1", 842, 420, "B1",4);
        graph.addVertex("C1", 772, 480, "C1",4);
        graph.addVertex("D1", 842, 480, "D1",4);
        graph.addVertex("E1", 707, 450, "E1",4);
        graph.addVertex("F1", 907, 450, "F1",4);
        graph.addVertex("G1", 807, 350, "G1",4);
        graph.addVertex("H1", 807, 550, "H1",4);
//        graph.addVertex("I1", 950, 500, 4);
//        graph.addVertex("J1", 1050, 550, 4);
        //Inicial
        graph.addEdge("A0", "A1", 1);
        graph.addEdge("A0", "B1", 1);
        graph.addEdge("A0", "C1", 1);
        graph.addEdge("A0", "D1", 1);
        graph.addEdge("A0", "E1", 4);
        graph.addEdge("A0", "F1", 4);
        graph.addEdge("A0", "G1", 4);
        graph.addEdge("A0", "H1", 4);


        //A1
        graph.addEdge("A1", "B1", 2);
        graph.addEdge("A1", "C1", 2);
        graph.addEdge("A1", "G1", 4);
        graph.addEdge("A1", "A0", 1);
        //B1
        graph.addEdge("B1", "A1", 2);
        graph.addEdge("B1", "D1", 2);
        graph.addEdge("B1", "F1", 4);
        //C1
        graph.addEdge("C1", "A1", 2);
        graph.addEdge("C1", "D1", 2);
        graph.addEdge("C1", "E1", 4);
        //D1
        graph.addEdge("D1", "C1", 2);
        graph.addEdge("D1", "B1", 2);
        graph.addEdge("D1", "H1", 4);

        graph.addEdge("E1", "C1", 3);
        graph.addEdge("E1", "F1", 3);
        graph.addEdge("E1", "H1", 3);

        graph.addEdge("F1", "E1", 3);
        graph.addEdge("F1", "B1", 3);
        graph.addEdge("F1", "H1", 3);

        graph.addEdge("H1", "E1", 3);
        graph.addEdge("H1", "F1", 3);


        //Islas izquierda Arriba
        graph.addVertex("A2", 525, 150, "A2",4);
        graph.addVertex("B2", 476, 100, "B1",4);
        graph.addVertex("C2", 476, 200, "C1",4);
        graph.addVertex("D2", 425, 150, "D1",4);

        graph.addVertex("E2", 376, 230, "E2",4);
        graph.addVertex("F2", 316, 70,  "F2",4);
        graph.addVertex("G2", 276, 170, "G2",4);
        graph.addVertex("H2", 196, 250, "H2",4);

        graph.addVertex("I2", 186, 110, "I2",4);
        graph.addVertex("J2", 136, 60, "J2",4);
        graph.addVertex("K2", 136, 160, "K2",4);
        graph.addVertex("L2", 86,  110, "L2",4);



        //Barcos izquierda abajo

        graph.addVertex("A3", 210, 490, "A3",4);
        graph.addVertex("B3", 156, 440, "B3",4);
        graph.addVertex("C3", 156, 540, "C3",4);
        graph.addVertex("D3", 96,  490, "D3",4);

        graph.addVertex("E3", 396, 575, "E3",4);

        graph.addVertex("F3", 546, 750, "F3",4);
        graph.addVertex("G3", 496, 680, "G3",4);
        graph.addVertex("H3", 496, 820, "H3",4);
        graph.addVertex("I3", 446, 750, "I3",4);

        graph.addVertex("J3", 196, 610,  "J2",4);
        graph.addVertex("K3", 356, 710, "K2",4);
        graph.addVertex("L3", 196, 710, "L3",4);
        graph.addVertex("M3", 116, 670, "M3",4);
        graph.addVertex("N3", 116,  750, "N3",4);
        graph.addVertex("O3", 276,  670, "O3",4);
        graph.addVertex("P3", 276,  750, "P3",4);

        //Ciudad derecha centro

        graph.addVertex("A4", 920, 150, "A4",4);
        graph.addVertex("B4", 1050, 670, "B4",4);
        graph.addVertex("C4", 1296, 150, "C4",4);
        graph.addVertex("D4", 1296,  550, "D4",4);

        graph.addVertex("E4", 1230, 210, "E4",4);
        graph.addVertex("F4", 1435, 290,  "F4",4);
        graph.addVertex("G4", 1360, 490, "G4",4);
        graph.addVertex("H4", 1156, 410, "H4",4);

        graph.addVertex("I4", 1195, 300, "I4",4);
        graph.addVertex("J4", 1350, 240, "J4",4);
        graph.addVertex("K4", 1410, 395, "K4",4);
        graph.addVertex("L4", 1250,  450, "L4",4);

//        graph.addVertex("A2",100,300,4);
//        graph.addVertex("B2",200,300,4);
//        graph.addVertex("C2",300,300,4);
//        graph.addVertex("D2",400,300,4);
//        graph.addVertex("E2",500,300,4);
//        graph.addVertex("F2",600,300,4);
//        graph.addVertex("G2",700,300,4);
//        graph.addVertex("H2",800,300,4);
//        graph.addVertex("I2",900,300,4);
//        graph.addVertex("J2",1000,300,4);
//
//        graph.addVertex("A3",100,500,4);
//        graph.addVertex("B3",200,500,4);
//        graph.addVertex("C3",300,500,4);
//        graph.addVertex("D3",400,500,4);
//        graph.addVertex("E3",500,500,4);
//        graph.addVertex("F3",600,500,4);
//        graph.addVertex("G3",700,500,4);
//        graph.addVertex("H3",800,500,4);
//        graph.addVertex("I3",900,500,4);
//        graph.addVertex("J3",1000,500,4);


//        graph.addEdge("A", "B", 4);
//        graph.addEdge("A", "C", 2);
//        graph.addEdge("B", "C", 1);
//        graph.addEdge("B", "D", 5);
//        graph.addEdge("C", "B", 1);
//        graph.addEdge("C", "D", 8);
//        graph.addEdge("C", "E", 9);
//        graph.addEdge("D", "F", 6);
//        graph.addEdge("D", "E", 2);
//        graph.addEdge("E", "F", 3);

//        Vertex<String> v1 = graph.searchVertex("A");
//        Vertex<String> v6 = graph.searchVertex("F");
//
////        graph.crearMatrizAdyacencia();
////        System.out.println("Matriz de Adyacencia:");
////        graph.mostrarMatrizAdyacencia();
//
        // Realizar una búsqueda en anchura desde el vértice "A"
        boolean bfsResult = graph.breadthFirstSearch("A0");
        System.out.println("BFS Result GraphGenerator: " + bfsResult);
//
//        // Realizar el algoritmo de Dijkstra desde el vértice "A"
//        String dijkstraResult = graph.algoritmoDijkstra(v1);
//        System.out.println("Dijkstra Result: " + dijkstraResult);
//
//        String dijkstraResultEn = graph.dijkstraAlgorithm(v1,v6);
//        System.out.println("Dijkstra Result: " + dijkstraResultEn);
    }

    private void drawMap() {

        vertexArrayList = graph.getVertexArrayList();
        edgeArrayList = graph.getEdgeArrayList();
        PlayerGenerator<K> playerGenerator1 = new PlayerGenerator(800, 450, graph.searchVertex("A0"));

        Vertex<String> playerVertex = graph.searchVertex("A1");

        //playerGenerator1.setActualVertex(playerVertex);

        playerGenerator1.setActualVertex(playerVertex);



        playerGenerator1.placePlayerOnVertex();

        for (Edge<String> e : edgeArrayList) {
            ((Pane) canvas.getParent()).getChildren().add(e.getLine());
            ((Pane) canvas.getParent()).getChildren().add(e.getWeightLabel());
        }

        for (Vertex<String> v : vertexArrayList) {
            ((Pane) canvas.getParent()).getChildren().add(v.getCircle());
            ((Pane) canvas.getParent()).getChildren().add(v.getNameLabel());
        }

        ((Pane) canvas.getParent()).getChildren().add(playerGenerator1.getPlayerCircle());
    }

    private void primAlgorithm() {
        Set<Vertex<String>> visited = new HashSet<>();
        List<Edge<String>> minimumSpanningTree = new ArrayList<>();

        // Comenzar desde un vértice aleatorio
        Vertex<String> startVertex = vertexArrayList.iterator().next();
        visited.add(startVertex);

        while (visited.size() < vertexArrayList.size()) {
            Edge minEdge = null;
            for (Edge edge : edgeArrayList) {
                if (visited.contains(edge.getOrigin()) && !visited.contains(edge.getDestiny())) {
                    if (minEdge == null || edge.getWeight() < minEdge.getWeight()) {
                        minEdge = edge;
                    }
                }
            }

            if (minEdge != null) {
                visited.add(minEdge.getDestiny());
                minimumSpanningTree.add(minEdge);
            }
        }
    }

}
