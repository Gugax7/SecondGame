package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;
    public boolean movement;
    public BufferedImage stopUp,stopDown,stopRight,stopLeft,
                         runUp1,runUp2,runDown1,runDown2,
                         runRight1,runRight2,runLeft1,runLeft2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
