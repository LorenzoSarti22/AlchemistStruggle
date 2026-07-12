package it.unicam.cs.mpgc.rpg118664;

import it.unicam.cs.mpgc.rpg118664.model.Game;
import it.unicam.cs.mpgc.rpg118664.model.GameFactory;
import it.unicam.cs.mpgc.rpg118664.panel.GamePanel;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            GameFactory factory = new GameFactory();
            Game game = factory.createNewGame("Alchimista");

            JFrame frame = new JFrame("Alchemist's Struggle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            GamePanel gamePanel = new GamePanel(game);
            frame.add(gamePanel);

            frame.pack();
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}