package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Okay, here i will set the SCREEN SETTINGS... OKAY.
    final int originalTileSize = 16; // 16x16 tile -> player/ enemy/npc
    final int scale = 3; // to not be small for my big monitor B) yes i have and 1360 : 720 monitor
    public final int tileSize = originalTileSize*scale; // here we are just turning reality last comments.
    public final int maxScreenCol = 16; // (768 pixels) quantity of column tiles per screen
    public final int maxScreenRow = 12; // (576 pixels) quantity of row tiles per screen
    final int screenWidth = maxScreenCol * tileSize;
    final int screenHeight = maxScreenRow * tileSize;
    int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this,keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);// this start the key listener to move the character
        //this.setDoubleBuffered(true);
        this.setFocusable(true); // with this the game panel can be focused on receiving the key input;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // it starts the run method
    }
// SLEEP METHOD...
//    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        while(gameThread != null){
//          //  System.out.println("loop completed");
//        // 1# Update information like character/enemy position
//            update();
//        // 2# draw with updated information
//            repaint();
//
//            try {
//                double timeLeft = nextDrawTime - System.nanoTime();
//                timeLeft = timeLeft/1000000; // turning into milliseconds
//                if(timeLeft < 0){
//                    timeLeft = 0;
//                }
//                Thread.sleep((long)timeLeft);
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }// when the thread begins (start method) it automatically calls run method.
    //DELTA METHOD...
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        double timer = 0;
        double threadCounter = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                threadCounter++;
            }
            if(timer >= 1000000000){
                //System.out.println("FPS: " + (threadCounter));
                threadCounter = 0;
                timer = 0;

            }
        }
    }
    public void update(){
    player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//Graphics2D its a class that extends Graphics, and allows to control better geometry and coordinates
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
    }

}
