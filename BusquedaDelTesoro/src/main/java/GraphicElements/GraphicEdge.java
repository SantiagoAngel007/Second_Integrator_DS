package GraphicElements;

import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
public class GraphicEdge {

    private Line linea;

    public GraphicEdge(GraphicVertex inicio, GraphicVertex fin, Color color, double peso) {
        this.linea = new Line(inicio.getCirculo().getCenterX(), inicio.getCirculo().getCenterY(),
                fin.getCirculo().getCenterX(), fin.getCirculo().getCenterY());
        this.linea.setStroke(color);

    }

    public Line getLinea() {
        return linea;
    }
}
