package it.unicam.cs.mpgc.rpg118664.panel;

import it.unicam.cs.mpgc.rpg118664.model.Game;
import it.unicam.cs.mpgc.rpg118664.model.alchemy.Laboratory;
import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import javax.swing.*;
import java.awt.*;

public class LaboratoryPanel extends JDialog {

    private final Game game;
    private final Runnable onCraftSuccess;
    private final Laboratory laboratory;

    public LaboratoryPanel(Frame owner, Game game, Runnable onCraftSuccess) {
        super(owner, " Laboratorio Alchemico", true);
        this.game = game;
        this.onCraftSuccess = onCraftSuccess;
        this.laboratory = new Laboratory();

        this.setSize(450, 300);
        this.setLocationRelativeTo(owner);
        this.setLayout(new BorderLayout(10, 10));

        JPanel recipesPanel = new JPanel(new GridLayout(PotionType.values().length, 1, 10, 10));
        recipesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (PotionType potionType : PotionType.values()) {
            recipesPanel.add(createRecipeRow(potionType));
        }

        this.add(new JLabel(" Seleziona una pozione da creare:", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(recipesPanel, BorderLayout.CENTER);

        JButton btnClose = new JButton("Chiudi");
        btnClose.addActionListener(e -> dispose());
        this.add(btnClose, BorderLayout.SOUTH);
    }

    private JPanel createRecipeRow(PotionType potionType) {
        JPanel row = new JPanel(new BorderLayout(5, 5));

        StringBuilder reqs = new StringBuilder(" (Richiede: ");
        potionType.getRequiredIngredients().forEach((name, qty) -> reqs.append(name).append(" x").append(qty).append(" "));
        reqs.append(")");

        JLabel lblRecipe = new JLabel(potionType.getName() + reqs.toString());
        JButton btnCraft = new JButton(" Crea");

        btnCraft.addActionListener(e -> {
            boolean success = laboratory.craft(game.getAlchemist(), potionType);
            if (success) {
                onCraftSuccess.run(); 
            } else {
                JOptionPane.showMessageDialog(this, "Materiali insufficienti per creare questa pozione.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        row.add(lblRecipe, BorderLayout.CENTER);
        row.add(btnCraft, BorderLayout.EAST);
        return row;
    }
}