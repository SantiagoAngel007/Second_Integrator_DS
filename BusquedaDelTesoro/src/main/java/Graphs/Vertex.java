package Graphs;

import GraphicElements.ItemType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//public class Vertex<K extends Comparable<K>> implements Comparable<K>{
//    private K key;
//    private ColorType color;
//    private Vertex<K> previous;
//    private int distance;
//    private int time;
//    List<Vertex<K>> connectedVertex;
//    private Circle circulo;
//    private ItemType item;
//
//    public Vertex(K key, ArrayList<Vertex<K>> connectedVertex) {
//        this.key = key;
//        color = ColorType.WHITE;
//        this.connectedVertex = connectedVertex;
//    }
//
//    public Vertex(K key, ArrayList<Vertex<K>> connectedVertex, double x, double y, Color graphicColor, ItemType item){
//        this.key = key;
//        color = ColorType.WHITE;
//        this.connectedVertex = connectedVertex;
//        circulo = new Circle(x, y, 10, graphicColor);
//    }
//
//    public ItemType getItem() {
//        return item;
//    }
//
//    public void setItem(ItemType item) {
//        this.item = item;
//    }
//
//    public Circle getCirculo() {
//        return circulo;
//    }
//
//    public K getKey() {
//        return key;
//    }
//
//    public void setKey(K key) {
//        this.key = key;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public void setDistance(int distance) {
//        this.distance = distance;
//    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public void setTime(int time) {
//        this.time = time;
//    }
//
//    public ColorType getColor() {
//        return color;
//    }
//
//    public void setColor(ColorType color) {
//        this.color = color;
//    }
//
//    public Vertex<K> getPrevious() {
//        return previous;
//    }
//
//    public void setPrevious(Vertex<K> previous) {
//        this.previous = previous;
//    }
//
//    public List<Vertex<K>> getConnectedVertex() {
//        return connectedVertex;
//    }
//
//    public void setConnectedVertex(ArrayList<Vertex<K>> connectedVertex) {
//        this.connectedVertex = connectedVertex;
//    }
//
//    @Override
//    public int compareTo(K o) {
//        return key.compareTo(o);
//    }
//}