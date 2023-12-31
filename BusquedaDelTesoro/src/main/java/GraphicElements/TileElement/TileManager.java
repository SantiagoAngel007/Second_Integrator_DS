package GraphicElements.TileElement;

import com.example.busquedadeltesoro.GamePanel;
import com.example.busquedadeltesoro.MapController;
import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){

        try {
            tile[0] =  new Tile();
            tile[0].image =  ImageIO.read(getClass().getResourceAsStream("/TilesImages/grass00.png"));

            tile[1] =  new Tile();
            tile[1].image =  ImageIO.read(getClass().getResourceAsStream("/TilesImages/wall.png"));

            tile[2] =  new Tile();
            tile[2].image =  ImageIO.read(getClass().getResourceAsStream("/TilesImages/water00.png"));

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize,null );
    }

    public void draw(){
        new Rectangle(0, 0, 16, 16);
    }





}
