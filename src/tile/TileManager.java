package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        loadMap();
        getTileImage();

    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/WallBrick.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int row = 0;
        int col = 0;
        try {
            while(row < gamePanel.maxScreenRow && col < gamePanel.maxScreenCol){
            String line = br.readLine();
                while(col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            for (int[] x: mapTileNumber){
                System.out.println(Arrays.toString(x));
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
            int tileNum = mapTileNumber[col][row];
            g2.drawImage(tile[tileNum].image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
            col++;
            x+= gamePanel.tileSize;
            if(col == gamePanel.maxScreenCol){
                col = 0;
                x = 0;
                row ++;
                y+=gamePanel.tileSize;
            }
        }

    }
}
