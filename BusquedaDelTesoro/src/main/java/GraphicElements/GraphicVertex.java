package GraphicElements;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class GraphicVertex {
        private Circle circulo;

        private ItemType item;

        public GraphicVertex(double x, double y, Color color, ItemType item) {
            this.item = item;
            circulo = new Circle(x, y, 10, color); // 10 es el radio del círculo
        }

    public ItemType getItem() {
        return item;
    }

    public void setItem(ItemType item) {
        this.item = item;
    }

    public Circle getCirculo() {
            return circulo;
        }
    }

