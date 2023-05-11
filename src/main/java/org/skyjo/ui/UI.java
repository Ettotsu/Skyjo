package org.skyjo.ui;

import org.skyjo.game.Game;
import org.skyjo.minigame.MiniGameWindow;
import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.LinkedHashMap;

public class UI extends JFrame {

    private Game game; // Game object

    private PropertiesFile prop; // Properties file

    private Assets assets; // Assets object

    private int width, height; // Width and height of the window

    private int cardWidth, cardHeight; // Width and height of the cards

    private int hgap, vgap; // Horizontal and vertical gap between the cards

    private int[] positionsX, positionsY; // Arrays containing positions of the card panels
    private int stackX, stackY; // Position of the stack panel
    private int discardX, discardY; // Position of the discard panel
    private int panelWidth, panelHeight; // Width and height of the cards panels


    private LinkedHashMap<Integer, JPanel> panels; // Map of the panels containing the cards buttons and labels
    private DiscardButton discardButton; // Discard button



    public UI(Game game) {
        super("Fail your Deutec"); // Title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Ends the program when the main window is closed

        this.game = game;
        this.game.setUI(this);

        prop=new PropertiesFile();
        if(prop.getBooleanProperty("borderless")){
            this.setUndecorated(true);
        }
        this.readSettings();
        this.calculateSizes();
        assets = new Assets();

        this.MainMenu();
    }

    /**
     * Calculates the size of the images.
     * We could have put the values in a file, but we decided to hardcode them for optimization purposes.
     */
    private void calculateSizes(){
        final int h0 = 420, h1 = 170, h2 = 40; // Duplicated values
        final int w0 = 40, w1 = 1050;

        positionsX = new int[8];
        positionsY = new int[8];

        width = this.getWidth();
        height = this.getHeight();

        hgap = (width * 12)/1280;
        vgap = (height * 12)/720;

        // First position (player)
        positionsX[0] = (width * 320)/1280;
        positionsY[0] = (height * 300)/720;
        // Second position
        positionsX[1] = (width * w0)/1280;
        positionsY[1] = (height * h0)/720;
        // Third position
        positionsX[2] = (width * w0)/1280;
        positionsY[2] = (height * h1)/720;
        // Fourth position
        positionsX[3] = (width * 290)/1280;
        positionsY[3] = (height * h2)/720;
        // Fifth position
        positionsX[4] = (width * 550)/1280;
        positionsY[4] = (height * h2)/720;
        // Sixth position
        positionsX[5] = (width * 810)/1280;
        positionsY[5] = (height * h2)/720;
        // Seventh position
        positionsX[6] = (width * w1)/1280;
        positionsY[6] = (height * h1)/720;
        // Eighth position
        positionsX[7] = (width * w1)/1280;
        positionsY[7] = (height * h0)/720;

        // Stack position
        stackX = (width * 40)/1280;
        stackY = (height * 40)/720;

        // Discard pile position
        discardX = (width * 810)/1280;
        discardY = (height * 500)/720;

        this.cardWidth = (width * 80)/1280; // 80 is the original width of the image
        this.cardHeight = (height * 112)/720; // 112 is the original height of the image

        panelWidth = Game.DECK_COLS*cardWidth + (Game.DECK_COLS-1)*hgap;
        panelHeight = Game.DECK_ROWS*cardHeight + (Game.DECK_ROWS-1)*vgap;
    }

    /**
     * Updates the window settings.
     */
    private void readSettings(){
        prop=new PropertiesFile();
        width = prop.getIntProperty("window_width");
        height = prop.getIntProperty("window_height");

        this.setSize(width, height);
        this.calculateSizes();
    }

    private void MainMenu(){
        this.getContentPane().removeAll();

        assets.loadMenu(width, height);

        MenuButton start = new MenuButton(assets.getStart(), assets.getStartHover());
        start.addActionListener(e -> new PlayersWindow(game));
        MenuButton settings = new MenuButton(assets.getSettings(), assets.getSettingsHover());
        settings.addActionListener(e -> {
            new SettingsWindow();
            this.readSettings();
        });
        MenuButton minigame = new MenuButton(assets.getMinigame(), assets.getMinigameHover());
        minigame.addActionListener(e -> new MiniGameWindow());
        MenuButton quit = new MenuButton(assets.getQuit(), assets.getQuitHover());
        quit.addActionListener(e -> System.exit(0));
        JPanel mainPanel = new JPanel();
        mainPanel.add(start);
        mainPanel.add(settings);
        mainPanel.add(minigame);
        mainPanel.add(quit);
        mainPanel.setVisible(true);

        JLayeredPane layeredPane = new JLayeredPane();

        mainPanel.setBounds(0, (500*height)/720, width, height);
        JLabel background = new JLabel(assets.getMenuBackground());
        background.setBounds(0, 0, width, height);

        layeredPane.add(background, 0);
        layeredPane.add(mainPanel, 1);
        mainPanel.setOpaque(false);
        layeredPane.setOpaque(true);

        this.getContentPane().add(layeredPane);
        this.setLocationRelativeTo(null);

        this.addComponentListener(new ComponentListener() { // It needs to override all the methods
            @Override
            public void componentResized(ComponentEvent e) {calculateSizes();}
            @Override
            public void componentMoved(ComponentEvent e) {}
            @Override
            public void componentShown(ComponentEvent e) {}
            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        this.setVisible(true); // Makes the window visible
    }


    private void generatePanels(){
        panels = new LinkedHashMap<>();
        panels.put(0, new JPanel());
        panels.get(0).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap, vgap));
        for(int i = 0; i< Game.DECK_ROWS * Game.DECK_COLS; i++){
            panels.get(0).add(new CardButton(i, assets, game));
        }

        for(int i=1;i<game.getNbPlayers();i++){
            panels.put(i, new JPanel());
            panels.get(i).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap/2, vgap/2));
            for(int j = 0; j < Game.DECK_ROWS * Game.DECK_COLS; j++){
                panels.get(i).add(new CardLabel(i, j, assets, game));
            }
        }
    }

    public void gameInterface() {
        this.generatePanels();
        this.getContentPane().removeAll();

        assets.loadInGame(width, height, cardWidth, cardHeight);

        JPanel mainPanel = new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        Image img = new ImageIcon("src/main/resources/assets/background.png").getImage();
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);

        panels.get(0).setBounds(positionsX[0], positionsY[0], panelWidth, panelHeight);
        mainPanel.add(panels.get(0));
        //number of card sets
        for(int i=1;i<game.getNbPlayers();i++) {
            mainPanel.add(panels.get(i));
        }

        switch (game.getNbPlayers()) {
            case 2 -> panels.get(1).setBounds(positionsX[4], positionsY[4], panelWidth / 2, panelHeight / 2);
            case 3 -> {
                panels.get(1).setBounds(positionsX[3], positionsY[3], panelWidth / 2, panelHeight / 2);
                panels.get(2).setBounds(positionsX[5], positionsY[5], panelWidth / 2, panelHeight / 2);
            }
            case 4 -> {
                panels.get(1).setBounds(positionsX[3], positionsY[3], panelWidth / 2, panelHeight / 2);
                panels.get(2).setBounds(positionsX[4], positionsY[4], panelWidth / 2, panelHeight / 2);
                panels.get(3).setBounds(positionsX[5], positionsY[5], panelWidth / 2, panelHeight / 2);
            }
            case 5 -> {
                panels.get(1).setBounds(positionsX[1], positionsY[1], panelWidth / 2, panelHeight / 2);
                panels.get(2).setBounds(positionsX[2], positionsY[2], panelWidth / 2, panelHeight / 2);
                panels.get(3).setBounds(positionsX[6], positionsY[6], panelWidth / 2, panelHeight / 2);
                panels.get(4).setBounds(positionsX[7], positionsY[7], panelWidth / 2, panelHeight / 2);
            }
            case 6 -> {
                panels.get(1).setBounds(positionsX[2], positionsY[2], panelWidth / 2, panelHeight / 2);
                panels.get(2).setBounds(positionsX[3], positionsY[3], panelWidth / 2, panelHeight / 2);
                panels.get(3).setBounds(positionsX[4], positionsY[4], panelWidth / 2, panelHeight / 2);
                panels.get(4).setBounds(positionsX[5], positionsY[5], panelWidth / 2, panelHeight / 2);
                panels.get(5).setBounds(positionsX[6], positionsY[6], panelWidth / 2, panelHeight / 2);
            }
            case 7 -> {
                panels.get(1).setBounds(positionsX[1], positionsY[1], panelWidth / 2, panelHeight / 2);
                panels.get(2).setBounds(positionsX[2], positionsY[2], panelWidth / 2, panelHeight / 2);
                panels.get(3).setBounds(positionsX[3], positionsY[3], panelWidth / 2, panelHeight / 2);
                panels.get(4).setBounds(positionsX[5], positionsY[5], panelWidth / 2, panelHeight / 2);
                panels.get(5).setBounds(positionsX[6], positionsY[6], panelWidth / 2, panelHeight / 2);
                panels.get(6).setBounds(positionsX[7], positionsY[7], panelWidth / 2, panelHeight / 2);
            }
            default -> {
                for(int i=1;i<game.getNbPlayers();i++) {
                    panels.get(i).setBounds(positionsX[i], positionsY[i], panelWidth / 2, panelHeight / 2);
                }
            }
        }

        discardButton = new DiscardButton(assets, game);
        discardButton.setBounds(discardX, discardY, cardWidth, cardHeight);
        mainPanel.add(discardButton);

        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, width, height);

        layeredPane.add(background, 0);
        layeredPane.add(mainPanel, 1);

        this.getContentPane().add(layeredPane);

        this.repaint();
        this.revalidate();
    }

    public void putPlayerInTitle(int value){
        this.setTitle("Fail Your Deutec - " + game.getPlayerName(value) + "'s turn");
    }
}
