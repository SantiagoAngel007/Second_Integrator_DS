package com.example.busquedadeltesoro;

import GraphicElements.ItemType;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphGenerator <K extends Comparable<K>> {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private PlayerGenerator<String> playerGenerator;
    GraphController<String> graph;

    private ArrayList<Vertex<String>> vertexArrayList = new ArrayList<>();
    private ArrayList<Edge<String>> edgeArrayList = new ArrayList<>();

    public GraphGenerator(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        graph = new GraphController<>();
    }

    public void paint(){
        generateMap();
        drawMap();
    }

    public void generateMap() {

        graph.addVertex("A0", 807, 450, "A0",-1);

        graph.addVertex("A1", 772, 420, "A1",5);
        graph.addVertex("B1", 842, 420, "B1",1);
        graph.addVertex("C1", 772, 480, "C1",5);
        graph.addVertex("D1", 842, 480, "D1",1);
        graph.addVertex("E1", 707, 450, "E1",5);
        graph.addVertex("F1", 907, 450, "F1",1);
        graph.addVertex("G1", 807, 350, "G1",5);
        graph.addVertex("H1", 807, 550, "H1",1);
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

        //Islas izquierda Arriba
        graph.addVertex("A2", 525, 150, "A2",5);
        graph.addVertex("B2", 476, 100, "B2",3);
        graph.addVertex("C2", 476, 200, "C2",2);
        graph.addVertex("D2", 425, 150, "D2",1);

        graph.addVertex("E2", 376, 230, "E2",5);
        graph.addVertex("F2", 316, 70,  "F2",4);
        graph.addVertex("G2", 276, 170, "G2",2);
        graph.addVertex("H2", 196, 250, "H2",4);

        graph.addVertex("I2", 186, 110, "I2",3);
        graph.addVertex("J2", 136, 60, "J2",3);
        graph.addVertex("K2", 136, 160, "K2",1);
        graph.addVertex("L2", 86,  110, "L2",5);

        //Barcos izquierda abajo

        graph.addVertex("A3", 210, 490, "A3",5);
        graph.addVertex("B3", 156, 440, "B3",1);
        graph.addVertex("C3", 156, 540, "C3",5);
        graph.addVertex("D3", 96,  490, "D3",5);

        graph.addVertex("E3", 396, 575, "E3",2);

        graph.addVertex("F3", 546, 750, "F3",5);
        graph.addVertex("G3", 496, 680, "G3",2);
        graph.addVertex("H3", 496, 820, "H3",3);
        graph.addVertex("I3", 446, 750, "I3",1);

        graph.addVertex("J3", 196, 610,  "J3",5);
        graph.addVertex("K3", 356, 710, "K3",3);
        graph.addVertex("L3", 196, 710, "L3",0);
        graph.addVertex("M3", 116, 670, "M3",5);
        graph.addVertex("N3", 116,  750, "N3",1);
        graph.addVertex("O3", 276,  670, "O3",5);
        graph.addVertex("P3", 276,  750, "P3",1);

        //Ciudad derecha centro

        graph.addVertex("A4", 920, 150, "A4",1);
        graph.addVertex("B4", 1050, 670, "B4",3);
        graph.addVertex("C4", 1296, 150, "C4",5);
        graph.addVertex("D4", 1296,  550, "D4",5);

        graph.addVertex("E4", 1230, 210, "E4",1);
        graph.addVertex("F4", 1435, 290,  "F4",1);
        graph.addVertex("G4", 1360, 490, "G4",1);
        graph.addVertex("H4", 1156, 410, "H4",2);

        graph.addVertex("I4", 1195, 300, "I4",3);
        graph.addVertex("J4", 1350, 240, "J4",2);
        graph.addVertex("K4", 1410, 395, "K4",5);
        graph.addVertex("L4", 1250,  450, "L4",3);
        graph.addVertex("M4", 1296,  357, "M4",4);

        //Edges
        //Isla Central
        graph.addEdge("A0", "A1", 1);
        graph.addEdge("A0", "B1", 2);
        graph.addEdge("A0", "C1", 1);
        graph.addEdge("A0", "D1", 2);
        graph.addEdge("A0", "E1", 4);
        graph.addEdge("A0", "F1", 3);
        graph.addEdge("A0", "G1", 4);
        graph.addEdge("A0", "H1", 3);

        graph.addEdge("A1", "B1", 2);
        graph.addEdge("A1", "C1", 2);
        graph.addEdge("A1", "G1", 4);
        graph.addEdge("A1", "A0", 1);

        graph.addEdge("B1", "A1", 2);
        graph.addEdge("B1", "D1", 2);
        graph.addEdge("B1", "F1", 4);

        graph.addEdge("C1", "A1", 2);
        graph.addEdge("C1", "D1", 2);
        graph.addEdge("C1", "E1", 4);

        graph.addEdge("D1", "C1", 2);
        graph.addEdge("D1", "B1", 2);
        graph.addEdge("D1", "H1", 4);

        graph.addEdge("E1", "C1", 3);
        graph.addEdge("E1", "F1", 3);
        graph.addEdge("E1", "H1", 4);
        graph.addEdge("E1", "C2", 6);

        graph.addEdge("F1", "E1", 3);
        graph.addEdge("F1", "B1", 3);
        graph.addEdge("F1", "H1", 4);

        graph.addEdge("H1", "E1", 3);
        graph.addEdge("H1", "F1", 3);

        graph.addEdge("G1", "A4", 3);

        //Edges islas izquierdas
        //Isla 1
        graph.addEdge("G1", "A2", 6);

        graph.addEdge("A2", "B2", 2);
        graph.addEdge("A2", "C2", 2);
        graph.addEdge("A2", "D2", 4);

        graph.addEdge("B2", "D2", 2);
        graph.addEdge("B2", "F2", 2);
        graph.addEdge("B2", "G2", 5);

        graph.addEdge("C2", "D2", 4);
        graph.addEdge("C2", "E2", 3);

        graph.addEdge("D2", "G2", 4);
        graph.addEdge("D2", "F2", 3);

        //Islas pequenas

        graph.addEdge("E2", "G2", 5);

        graph.addEdge("F2", "G2", 5);
        graph.addEdge("F2", "I2", 5);

        graph.addEdge("G2", "I2", 5);
        graph.addEdge("G2", "H2", 5);
        graph.addEdge("G2", "K2", 5);

        graph.addEdge("H2", "B3", 5);

        //Isla izquierda arriba

        graph.addEdge("I2", "J2", 2);
        graph.addEdge("I2", "K2", 2);
        graph.addEdge("I2", "L2", 4);

        graph.addEdge("J2", "L2", 4);

        graph.addEdge("K2", "H2", 4);

        graph.addEdge("L2", "K2", 4);

        //Barcos izquieda abajo

        graph.addEdge("B3", "A3", 2);
        graph.addEdge("B3", "D3", 2);
        graph.addEdge("B3", "C3", 2);

        graph.addEdge("D3", "C3", 2);
        graph.addEdge("A3", "C3", 2);

        graph.addEdge("A3", "E3", 2);

        graph.addEdge("C3", "J3", 2);

        graph.addEdge("J3", "L3", 2);

        graph.addEdge("L3", "M3", 2);
        graph.addEdge("L3", "N3", 2);
        graph.addEdge("L3", "O3", 2);
        graph.addEdge("L3", "P3", 2);

        graph.addEdge("M3", "N3", 2);
        graph.addEdge("N3", "P3", 2);
        graph.addEdge("P3", "O3", 2);
        graph.addEdge("P3", "K3", 2);
        graph.addEdge("O3", "E3", 2);
        graph.addEdge("O3", "K3", 2);

        graph.addEdge("K3", "I3", 2);
        graph.addEdge("I3", "G3", 2);
        graph.addEdge("I3", "H3", 2);
        graph.addEdge("I3", "F3", 2);

        graph.addEdge("G3", "F3", 2);
        graph.addEdge("H3", "F3", 2);

        graph.addEdge("F3", "H1", 2);

        graph.addEdge("E3", "E1", 2);

        //Ciudad derecha centro

        graph.addEdge("A4", "C4", 2);
        graph.addEdge("C4", "J4", 2);
        graph.addEdge("J4", "F4", 2);
        graph.addEdge("F4", "K4", 2);
        graph.addEdge("K4", "G4", 2);
        graph.addEdge("G4", "L4", 2);
        graph.addEdge("L4", "H4", 2);
        graph.addEdge("H4", "I4", 2);
        graph.addEdge("I4", "E4", 2);
        graph.addEdge("E4", "M4", 2);
        graph.addEdge("M4", "D4", 2);
        graph.addEdge("D4", "B4", 2);
        graph.addEdge("B4", "F1", 2);

        playerGenerator = new PlayerGenerator(800, 450, graph.searchVertex("A0"));

        // Realizar una búsqueda en anchura desde el vértice "A"
        boolean bfsResult = graph.breadthFirstSearch("A0");
        System.out.println("BFS Result GraphGenerator: " + bfsResult);
    }



    private void drawMap() {

        vertexArrayList = graph.getVertexArrayList();
        edgeArrayList = graph.getEdgeArrayList();


//        Vertex<String> playerVertex = graph.searchVertex("A1");

        //playerGenerator1.setActualVertex(playerVertex);

//        playerGenerator1.setActualVertex(playerVertex);

        playerGenerator.placePlayerOnVertex();

        for (Edge<String> e : edgeArrayList) {
            ((Pane) canvas.getParent()).getChildren().add(e.getLine());
            ((Pane) canvas.getParent()).getChildren().add(e.getWeightLabel());
        }

        for (Vertex<String> v : vertexArrayList) {
            ((Pane) canvas.getParent()).getChildren().add(v.getCircle());
            ((Pane) canvas.getParent()).getChildren().add(v.getNameLabel());
        }

        ((Pane) canvas.getParent()).getChildren().add(playerGenerator.getPlayerCircle());
    }

    public void movementDynamic(int dadoResult, String initialPointText, String finalPointText){
        graph.crearMatrizAdyacencia();
        vertexArrayList = graph.getVertexArrayList();
        edgeArrayList = graph.getEdgeArrayList();
        int actualReach = 0;
        Vertex<String> vertex1 =  graph.searchVertex(initialPointText);
        Vertex<String> vertex2 =  graph.searchVertex(finalPointText);
        Vertex<String> vertex3 = playerGenerator.getActualVertex();
        if(vertex1 == vertex3){
                actualReach = graph.dijkstraAlgorithmInt(vertex1,vertex2);
                if (actualReach<=dadoResult){
                    playerGenerator.setActualVertex(vertex2);
                    playerGenerator.placePlayerOnVertex();
                } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado de movimiento");
                alert.setContentText("Numero de dado menor a recorrido:");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Punto inicial error");
            alert.setContentText("El punto inicial tiene que ser el mismo del jugador");
            alert.showAndWait();
        }
    }


    public void mostrarCaminoLongitudMinima(String initialPointText, String finalPointText){
        graph.crearMatrizAdyacencia();
        vertexArrayList = graph.getVertexArrayList();
        edgeArrayList = graph.getEdgeArrayList();
        Vertex<String> vertex1 =  graph.searchVertex(initialPointText);
        Vertex<String> vertex2 =  graph.searchVertex(finalPointText);
        if (vertex1!=null && vertex2!=null){
            ArrayList<Edge<String>> edgeArrayListShow = (ArrayList<Edge<String>>) graph.dijkstraAlgorithmEdge(vertex1,vertex2);
            for (Edge<String> e : edgeArrayListShow) {
                Line line = new Line(e.getOrigin().getX(), e.getOrigin().getY(), e.getDestiny().getX(), e.getDestiny().getY());
                line.setStroke(Color.YELLOW);
                ((Pane) canvas.getParent()).getChildren().add(line);
                Timeline timeline = new Timeline();
                KeyFrame keyFrame = new KeyFrame(
                        Duration.seconds(10),
                        new KeyValue(line.opacityProperty(), 0)
                );
                timeline.getKeyFrames().add(keyFrame);


                timeline.setOnFinished(event -> {
                    ((Pane) canvas.getParent()).getChildren().remove(line);
                });

                timeline.play();
            }
        }
    }

    public int checkItemVertex(){
        int type = 0;
        ItemType itemType = playerGenerator.getActualVertex().getItem();
        switch (itemType){
            case COIN -> type = 1;
            case HEART -> type = 2;
            case TRAP -> type = 3;
            case MAP -> type = 4;
            case NOTHING -> type = 5;
            case INITIAL -> type = 6;
            case TREASURE -> type = 7;
        }
        return type;
    }

    public void primAlgorithm() {
        ArrayList<Vertex<String>> vertexArrayListCopy = graph.getVertexArrayList();
        ArrayList<Edge<String>> edgeArrayListCopy = graph.getEdgeArrayList();
        Set<Vertex<String>> visited = new HashSet<>();
        List<Edge<String>> minimumSpanningTree = new ArrayList<>();

        // Comenzar desde un vértice aleatorio
        Vertex<String> startVertex = vertexArrayListCopy.iterator().next();
        visited.add(startVertex);

        while (visited.size() < vertexArrayListCopy.size()) {
            Edge minEdge = null;
            for (Edge edge : edgeArrayListCopy) {
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

        for (Edge<String> e : minimumSpanningTree) {
            Line line = new Line(e.getOrigin().getX(), e.getOrigin().getY(), e.getDestiny().getX(), e.getDestiny().getY());
            line.setStroke(Color.YELLOW);
            ((Pane) canvas.getParent()).getChildren().add(line);
        }
    }
}
