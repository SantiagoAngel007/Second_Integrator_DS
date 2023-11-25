package Screens;

import GraphicElements.TileElement.Tile;
import com.example.busquedadeltesoro.GraphGenerator;
import com.example.busquedadeltesoro.MapGenerator;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.util.List;

public class MapScreen {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private MapGenerator mapGenerator;
    private GraphGenerator graphGenerator;

    Tile[] tile;
    int[][] mapTileNumber;

    public MapScreen(Canvas canvas, GraphGenerator graphGenerator) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.mapGenerator = new MapGenerator(canvas);
        this.graphGenerator = graphGenerator;
        tile = new Tile[10];
        mapTileNumber = new int[80][42];
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

    public void paint(){

        Image image = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/Images/MapaEnBlanco.jpg")));
        this.graphicsContext.clearRect(0.0, 0.0, this.canvas.getWidth(), this.canvas.getHeight());
        this.graphicsContext.drawImage(image, 0.0, 0.0, this.canvas.getWidth(), this.canvas.getHeight());
        this.graphicsContext.setFill(Color.TRANSPARENT);
        this.graphicsContext.fillRect(0.0, 0.0, this.canvas.getWidth(), this.canvas.getHeight());
        Image image1 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/Images/heart_game.png")));
        this.graphicsContext.drawImage(image1, 0.0, 0.0, 16, 16);
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setArcHeight(100);
//        rectangle.setWidth(500);
//        rectangle.setHeight(10);
//        rectangle.setFill(Color.RED);
//        rectangle.setStroke(Color.RED);
//
        loadMap();
        draw();

        //mapGenerator.paint();
        graphGenerator.paint();

    }



    public void draw(){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        Image image1 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/water01.png")));
        Image image2 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/wall.png")));
        Image image3 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/road00.png")));
        Image image4 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/grass00.png")));
        Image image5 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/grass01.png")));
        Image image6 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/floor01.png")));
        Image image7 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/earth.png")));
        Image image8 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/tree.png")));
        Image image9 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/hut.png")));
        Image image10 = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/TilesImages/table01.png")));

        while (col<80 && row<42){
            int tileNum = mapTileNumber[col][row];
            if(tileNum==0){
                this.graphicsContext.drawImage(image1, x, y, 20, 20);
            }else if(tileNum==1){
                this.graphicsContext.drawImage(image3, x, y, 20, 20);
            }else if(tileNum==2){
                this.graphicsContext.drawImage(image2, x, y, 20, 20);
            }else if(tileNum==3){
                this.graphicsContext.drawImage(image4, x, y, 20, 20);
            }else if(tileNum==4){
                this.graphicsContext.drawImage(image5, x, y, 20, 20);
            }else if(tileNum==5){
                this.graphicsContext.drawImage(image6, x, y, 20, 20);
            }else if(tileNum==7){
                this.graphicsContext.drawImage(image7, x, y, 20, 20);
            }else if(tileNum==8){
                this.graphicsContext.drawImage(image8, x, y, 20, 20);
            }else if(tileNum==9){
                this.graphicsContext.drawImage(image9, x, y, 20, 20);
            }else if(tileNum==6){
                this.graphicsContext.drawImage(image10, x, y, 20, 20);
            }





            col++;
            x += 20;

            if(col == 80){
                col=0;
                x=0;
                row++;
                y+= 20;
            }
        }
    }

    public void loadMap(){
        try {
            File file = new File( "/Map.txt");
            System.out.println(file.exists());
            //InputStream is = getClass().getResourceAsStream("C:/Users/merak/IdeaProjects/BusquedaDelTesoro/Map.txt");
            InputStream is = new FileInputStream( "src/main/java/Maps/Map1.txt");
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col<80 && row<42){

                String line = br.readLine();

                while (col<80){
                    String numbers[] = line.split(" ");
                    int num= Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col==80){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("Something " + e);
        }
    }
}
