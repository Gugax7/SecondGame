package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        JFrame window = new JFrame();
        window.add(gamePanel); // putting the configs of game panel
        window.pack(); // size to fit the configs of game panel
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // setting the method that you can finish the window, in this case, its clicking on "x" upon.
        window.setLocationRelativeTo(null); // setting where the window appears at first time
        window.setTitle("Second Window"); // setting the title that appears upon the window
        window.setResizable(false); // basically if you can change the size of window
        window.setVisible(true); // basically if the window its visible
        gamePanel.startGameThread();
    }
}