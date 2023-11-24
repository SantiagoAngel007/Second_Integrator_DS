package com.example.busquedadeltesoro;
import GraphicElements.TileElement.TileManager;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize =  originalTileSize + scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS =  60;

    TileManager tileM = new TileManager(this);

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        g2.dispose();

    }



    @Override
    public void run() {

    }
}
