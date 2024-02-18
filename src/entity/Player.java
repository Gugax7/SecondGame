package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gamePanel = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            stopUp = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroStoppedUp.png"));
            runUp1 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun1Up.png"));
            runUp2 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun2Up.png"));

            stopRight = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroStoppedRight.png"));

            runRight1 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun1Right.png"));
            runRight2 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun2Right.png"));

            stopLeft = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroStoppedLeft.png"));

            runLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun1Left.png"));
            runLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun2Left.png"));
            stopDown = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroStopped.png"));
            runDown1 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun1down.png"));
            runDown2 = ImageIO.read(getClass().getResourceAsStream("/player/cangaceiroRun2down.png"));


        }catch (IOException e ){
            e.printStackTrace();
        }
    }
    public void update() {
        if(keyHandler.rightPressed == true || keyHandler.upPressed == true || keyHandler.leftPressed == true || keyHandler.downPressed== true) {
          movement = true;
            if (keyHandler.upPressed == true) {
                y -= speed;
                direction = "up";
            }
            if (keyHandler.downPressed == true) {
                y += speed;
                direction = "down";
            }
            if (keyHandler.leftPressed == true) {
                x -= speed;
                direction = "left";
            }
            if (keyHandler.rightPressed == true) {
                x += speed;
                direction = "right";
            }
            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if(keyHandler.downPressed== false && keyHandler.leftPressed==false && !keyHandler.upPressed &&  !keyHandler.rightPressed){
            movement = false;

        }
    }
    public void draw(Graphics2D g2){
       // g2.setColor(Color.white);
       // g2.fillOval(x,y,gamePanel.tileSize,gamePanel.tileSize);
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(!movement){
                    image = stopUp;
                }
                if(spriteNum == 1&& movement){
                    image = runUp1;
                }
                if(spriteNum == 2 && movement){
                    image = runUp2;
                }
                break;
            case "down":
                if(!movement){
                    image = stopDown;
                }
                if(spriteNum == 1&& movement){
                    image = runDown1;
                }
                if(spriteNum == 2 && movement){
                    image = runDown2;
                }
                break;
            case "right":
                if(!movement){
                    image = stopRight;
                }
                if(spriteNum == 1 && movement){
                image = runRight1;
                 }
                if(spriteNum == 2 && movement){
                    image = runRight2;
                }
                break;
            case "left":
                if(!movement){
                    image = stopLeft;
                }
                if(spriteNum == 1 && movement){
                    image = runLeft1;
                }
                if(spriteNum == 2 && movement){
                    image = runLeft2;
                }
                break;
            default: image = stopDown; break;
        }
        g2.drawImage(image, x,y, gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
