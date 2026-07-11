package it.unicam.cs.mpgc.rpg118664.panel;
 
import it.unicam.cs.mpgc.rpg118664.model.Game;
import it.unicam.cs.mpgc.rpg118664.model.GameFactory;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.Potion;    
import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import it.unicam.cs.mpgc.rpg118664.persistence.GameRepository;
import javax.swing.*;
import java.awt.*;
 
public class GamePanel extends JPanel {
    
    private Game game;
    private final GameRepository gameRepository;
    private final GameFactory gameFactory;
    
    private final JLabel lblAlchemistName;
    private final JLabel lblAlchemistHp;
    private final JLabel lblMonsterName;
    private final JLabel lblMonsterHp;
    private final JTextArea txtLog;
    
    private final JLabel lblGelatinaQty;
    private final JLabel lblPolvereQty;
    private final JLabel lblCristalloQty;
 
    private final JLabel lblPozioneHQty;
    private final JLabel lblPozioneAQty;
    private final JLabel lblPozioneDQty;
 
    public GamePanel(Game game) {
        this.game = game;
        this.gameRepository = new GameRepository();
        this.gameFactory = new GameFactory();
        
        this.setLayout(new BorderLayout(10, 10));
        
        txtLog = new JTextArea(15, 30);
        txtLog.setEditable(false);
        txtLog.setLineWrap(true);
        txtLog.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtLog);
        
        // pulsanti nuova partita, salva e carica
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton btnNewGame = new JButton(" Nuova Partita");
        JButton btnSave = new JButton(" Salva");
        JButton btnLoad = new JButton(" Carica");
        
        btnNewGame.addActionListener(e -> startNewGame());
        btnSave.addActionListener(e -> saveGameData());
        btnLoad.addActionListener(e -> loadGameData());
        
        topPanel.add(btnNewGame);
        topPanel.add(btnSave);
        topPanel.add(btnLoad);
        this.add(topPanel, BorderLayout.NORTH);
        
        //sezione per diaro
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.add(new JLabel(" Diario di Battaglia:"), BorderLayout.NORTH);
        leftPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel potionSectionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        potionSectionPanel.setBorder(BorderFactory.createTitledBorder(" Gestione Pozioni"));
 
        JPanel potionGridLabels = new JPanel(new GridLayout(1, 3, 5, 5));
        lblPozioneHQty = new JLabel("Cura: 0", SwingConstants.CENTER);
        lblPozioneAQty = new JLabel("Forza: 0", SwingConstants.CENTER);
        lblPozioneDQty = new JLabel("Ferro: 0", SwingConstants.CENTER);
        potionGridLabels.add(lblPozioneHQty);
        potionGridLabels.add(lblPozioneAQty);
        potionGridLabels.add(lblPozioneDQty);
        potionSectionPanel.add(potionGridLabels);
 
        JPanel potionGridButtons = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnUseCura = new JButton("Usa Cura");
        JButton btnUseAttacco = new JButton("Usa Forza");
        JButton btnUseDifesa = new JButton("Usa Ferro");
        
        potionGridButtons.add(btnUseCura);
        potionGridButtons.add(btnUseAttacco);
        potionGridButtons.add(btnUseDifesa);
        potionSectionPanel.add(potionGridButtons);
 
        JButton btnLaboratorio = new JButton(" Apri Laboratorio Alchemico");
        potionSectionPanel.add(btnLaboratorio);
 
        btnUseCura.addActionListener(e -> {
            var alchemist = this.game.getAlchemist();
            var pozione = alchemist.getInventory().stream()
                .filter(i -> i.getName().equals("Pozione di Vita"))
                .findFirst().orElse(null);
            if (pozione instanceof Potion p) {
                alchemist.removeItem(pozione);
                p.use(alchemist);
                txtLog.append(" Hai bevuto una Pozione di Vita! (+" + PotionType.HEALTH_POTION.getHpMaxBonus() + " HP Max)\n---------------------\n");
            } else {
                JOptionPane.showMessageDialog(this, "Non hai nessuna Pozione di Vita!", "Errore", JOptionPane.WARNING_MESSAGE);
            }
            updateInterface();
        });
 
        btnUseAttacco.addActionListener(e -> {
            var alchemist = this.game.getAlchemist();
            var pozione = alchemist.getInventory().stream()
                .filter(i -> i.getName().equals("Pozione della Forza"))
                .findFirst().orElse(null);
            if (pozione instanceof Potion p) {
                alchemist.removeItem(pozione);
                p.use(alchemist);
                txtLog.append(" Hai bevuto una Pozione della Forza! (+" + PotionType.STRENGTH_POTION.getAttackBonus() + " Attacco)\n---------------------\n");
            } else {
                JOptionPane.showMessageDialog(this, "Non hai nessuna Pozione della Forza!", "Errore", JOptionPane.WARNING_MESSAGE);
            }
            updateInterface();
        });
 
        btnUseDifesa.addActionListener(e -> {
            var alchemist = this.game.getAlchemist();
            var pozione = alchemist.getInventory().stream()
                .filter(i -> i.getName().equals("Pozione di Ferro"))
                .findFirst().orElse(null);
            if (pozione instanceof Potion p) {
                alchemist.removeItem(pozione);
                p.use(alchemist);
                txtLog.append(" Hai bevuto una Pozione di Ferro! (+" + PotionType.IRON_POTION.getDefenseBonus() + " Difesa)\n---------------------\n");
            } else {
                JOptionPane.showMessageDialog(this, "Non hai nessuna Pozione di Ferro!", "Errore", JOptionPane.WARNING_MESSAGE);
            }
            updateInterface();
        });
 
        btnLaboratorio.addActionListener(e -> {
            Frame topFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            LaboratoryPanel dialog = new LaboratoryPanel(topFrame, this.game, this::updateInterface);
            dialog.setVisible(true);
        });
        
        leftPanel.add(potionSectionPanel, BorderLayout.SOUTH);
        this.add(leftPanel, BorderLayout.WEST);
 
        // sezione combattimento
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
 
        JPanel alchemistCard = new JPanel(new GridLayout(2, 1));
        alchemistCard.setBorder(BorderFactory.createTitledBorder("Alchimista"));
        lblAlchemistName = new JLabel();
        lblAlchemistHp = new JLabel();
        alchemistCard.add(lblAlchemistName);
        alchemistCard.add(lblAlchemistHp);
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(alchemistCard, gbc);
 
        JButton btnAttacca = new JButton("⚔️ ATTACCA");
        btnAttacca.setFont(new Font("Arial", Font.BOLD, 16));
        btnAttacca.addActionListener(e -> executeCombatRound());
        gbc.gridx = 1; gbc.gridy = 0;
        centerPanel.add(btnAttacca, gbc);
 
        JPanel monsterCard = new JPanel(new GridLayout(2, 1));
        monsterCard.setBorder(BorderFactory.createTitledBorder("Nemico Corrente"));
        lblMonsterName = new JLabel();
        lblMonsterHp = new JLabel();
        monsterCard.add(lblMonsterName);
        monsterCard.add(lblMonsterHp);
        gbc.gridx = 2; gbc.gridy = 0;
        centerPanel.add(monsterCard, gbc);
 
        this.add(centerPanel, BorderLayout.CENTER);
 
        //sezione materiali base
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBorder(BorderFactory.createTitledBorder(" Inventario Materiali Base"));
        
        JPanel ingredientGrid = new JPanel(new GridLayout(1, 3, 20, 10));
        lblGelatinaQty = new JLabel(" Gelatina: 0", SwingConstants.CENTER);
        lblPolvereQty = new JLabel(" Polvere Fatata: 0", SwingConstants.CENTER);
        lblCristalloQty = new JLabel(" Cristallo: 0", SwingConstants.CENTER);
        
        ingredientGrid.add(lblGelatinaQty);
        ingredientGrid.add(lblPolvereQty);
        ingredientGrid.add(lblCristalloQty);
        
        southPanel.add(ingredientGrid, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
 
        updateInterface();
    }
 
    private void executeCombatRound() {
        String roundResult = game.executeTurn();
        txtLog.append(roundResult + "\n---------------------\n");
        updateInterface();
    }
 
    private void startNewGame() {
        String name = JOptionPane.showInputDialog(this, "Inserisci il nome del tuo Alchimista:", "Nuova Partita", JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.isBlank()) {
            return;
        }
        this.game = gameFactory.createNewGame(name.trim());
        txtLog.setText(" Nuova partita iniziata! Buona fortuna, " + name.trim() + "!\n---------------------\n");
        updateInterface();
    }
 
    private void saveGameData() {
        try {
            gameRepository.saveGame(this.game, "savegame.json");
            txtLog.append(" Partita salvata correttamente!\n---------------------\n");
            JOptionPane.showMessageDialog(this, "Salvataggio completato!", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore durante il salvataggio!", "Errore", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
 
    private void loadGameData() {
        try {
            this.game = gameRepository.loadGame("savegame.json");
            
            txtLog.setText(" Partita Caricata con Successo!\n---------------------\n");
            updateInterface(); 
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Impossibile caricare la partita.", "Errore", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
 
    private void updateInterface() {
        lblAlchemistName.setText("Nome: " + game.getAlchemist().getName());
        lblAlchemistHp.setText("Salute: " + game.getAlchemist().getHp() + "/" + game.getAlchemist().getHpMax());
        lblMonsterName.setText("Mostro: " + game.getCurrentMonster().getName());
        lblMonsterHp.setText("Salute: " + game.getCurrentMonster().getHp() + "/" + game.getCurrentMonster().getHpMax());
 
        int gelatina = 0, polvere = 0, cristallo = 0;
        int cura = 0, attacco = 0, difesa = 0;
 
        for (Item item : game.getAlchemist().getInventory()) {
            switch (item.getName()) {
                case "Gelatina" -> gelatina++;
                case "Polvere Fatata" -> polvere++;
                case "Cristallo" -> cristallo++;
                case "Pozione di Vita" -> cura++;
                case "Pozione della Forza" -> attacco++;
                case "Pozione di Ferro" -> difesa++;
            }
        }
 
        lblGelatinaQty.setText(" Gelatina: " + gelatina);
        lblPolvereQty.setText(" Polvere Fatata: " + polvere);
        lblCristalloQty.setText(" Cristallo: " + cristallo);
 
        lblPozioneHQty.setText("Cura: " + cura);
        lblPozioneAQty.setText("Forza: " + attacco);
        lblPozioneDQty.setText("Ferro: " + difesa);
        
        this.revalidate();
        this.repaint();
    }
}