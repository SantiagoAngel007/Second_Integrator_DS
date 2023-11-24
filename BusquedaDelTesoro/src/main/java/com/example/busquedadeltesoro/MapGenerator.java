package com.example.busquedadeltesoro;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.canvas.Canvas;

import javafx.event.EventHandler;
import java.util.*;

public class MapGenerator {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    Circle circle;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private Set<Vertex> vertices = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();


    public MapGenerator(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        circle = new Circle();
        circle.setRadius(10);
        circle.setFill(Color.BLUE);
        circle.setStroke(Color.RED);
        ((Pane) canvas.getParent()).getChildren().add(circle);

    }


    public void paint(){
        generateMap();
        drawMap();
        drawAllEdges();
    }

    private void generateMap() {
        // Crear vértices
        Random random = new Random();
        //int i = random.nextInt(,) + 1;

        Vertex v1 = new Vertex("A", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v2 = new Vertex("B", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v3 = new Vertex("C", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v4 = new Vertex("D", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v5 = new Vertex("E", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v6 = new Vertex("F", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v7 = new Vertex("G", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);
        Vertex v8 = new Vertex("H", random.nextInt(100,1300) + 1, random.nextInt(100,700) + 1);


//        Vertex v1 = new Vertex("A", 100, 100);
//        Vertex v2 = new Vertex("B", 300, 100);
//        Vertex v3 = new Vertex("C", 300, 300);
//        Vertex v4 = new Vertex("D", 100, 300);
//        Vertex v5 = new Vertex("E", 400, 400);
//        Vertex v6 = new Vertex("F", 400, 200);
//        Vertex v7 = new Vertex("G", 325, 200);
//        Vertex v8 = new Vertex("H", 400, 400);

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);
        vertices.add(v8);


        // Crear aristas
        addEdge(v1, v2,1);
        addEdge(v2, v3,3);
        addEdge(v3, v4,2);
        addEdge(v4, v5,5);
        addEdge(v5, v6,4);
        addEdge(v6, v7,1);
        addEdge(v7, v1,3);
        addEdge(v8, v2,2);
        addEdge(v7, v4,6);
        addEdge(v1, v3,3);
        addEdge(v4, v6,4);

        // Aplicar algoritmo de Prim para generar árbol de expansión mínima
        //primAlgorithm();
    }

    private void addEdge(Vertex source, Vertex target, int weight) {
        Edge edge = new Edge(source, target, weight);
        edges.add(edge);
    }

    private void primAlgorithm() {
        Set<Vertex> visited = new HashSet<>();
        List<Edge> minimumSpanningTree = new ArrayList<>();

        // Comenzar desde un vértice aleatorio
        Vertex startVertex = vertices.iterator().next();
        visited.add(startVertex);

        while (visited.size() < vertices.size()) {
            Edge minEdge = null;
            for (Edge edge : edges) {
                if (visited.contains(edge.getSource()) && !visited.contains(edge.getTarget())) {
                    if (minEdge == null || edge.getWeight() < minEdge.getWeight()) {
                        minEdge = edge;
                    }
                }
            }

            if (minEdge != null) {
                visited.add(minEdge.getTarget());
                minimumSpanningTree.add(minEdge);
            }
        }

        // Actualizar conjunto de aristas con el árbol de expansión mínima
        edges = new HashSet<>(minimumSpanningTree);
    }

    private void drawMap() {

        circle.setCenterX(100);
        circle.setCenterY(100);

        for (Vertex v: vertices) {
            ((Pane) canvas.getParent()).getChildren().add(v.getCircle());
        }

        for (Edge e: edges) {
            ((Pane) canvas.getParent()).getChildren().add(e.getLine());
            ((Pane) canvas.getParent()).getChildren().add(e.getWeightLabel());
        }

//        for (Vertex vertex : vertices) {
//            canvas.getScene().getRoot().getChildrenUnmodifiable().add(vertex.getCircle());
//        }
//        for (Edge edge : edges) {
//            canvas.getScene().getRoot().getChildrenUnmodifiable().add(edge);
//        }


//        Platform.runLater(() -> {
//            StackPane root = (StackPane) canvas.getScene().getRoot();
//
//            // Dibujar vértices
//            for (Vertex vertex : vertices) {
//                Platform.runLater(() -> root.getChildren().add(vertex.getCircle()));
//            }
//        });
    }

    private void drawAllEdges() {
//        Platform.runLater(() -> {
//            StackPane root = (StackPane) canvas.getScene().getRoot();
//
//            // Dibujar aristas
//            for (Edge edge : edges) {
//                Platform.runLater(() -> root.getChildren().addAll(edge.getLine()));
//            }
//        });
    }



    private static class Vertex {
        private String name;
        private double x;
        private double y;
        private static final double RADIUS = 10;

        Vertex(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        Circle getCircle() {
            Circle circle = new Circle(x, y, RADIUS, Color.BLUE);
            return circle;
        }
    }

    private static class Edge extends Group {
        private Vertex source;
        private Vertex target;
        private int weight;
        Label weightLabel;
        private static final Color COLOR = Color.BLACK;

        Edge(Vertex source, Vertex target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;

            Line line = new Line(source.x, source.y, target.x, target.y);
            line.setStroke(COLOR);

            javafx.scene.control.Label weightLabel = new Label(Integer.toString(weight));
            weightLabel.setLayoutX((source.x + target.x) / 2);
            weightLabel.setLayoutY((source.y + target.y) / 2);
            weightLabel.setTextFill(Color.RED);

            // Agregar tanto la línea como la Label al Group actual (instancia de Edge)
            getChildren().addAll(line, weightLabel);
        }

        Line getLine() {
            Line line = new Line(source.x, source.y, target.x, target.y);
            line.setStroke(COLOR);
            return line;
        }

        public Label getWeightLabel() {
            javafx.scene.control.Label weightLabel = new Label(Integer.toString(weight));
            weightLabel.setLayoutX((source.x + target.x) / 2);
            weightLabel.setLayoutY((source.y + target.y) / 2);
            weightLabel.setTextFill(Color.RED);
            return weightLabel;
        }

        public Vertex getSource() {
            return source;
        }

        public void setSource(Vertex source) {
            this.source = source;
        }

        public Vertex getTarget() {
            return target;
        }

        public void setTarget(Vertex target) {
            this.target = target;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
