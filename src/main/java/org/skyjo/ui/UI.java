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

    public static final int ORIGINAL_WIDTH = 1280, ORIGINAL_HEIGHT = 720, GAP=12;// Original width and height of the window

    private final Game game; // Game object

    private final Assets assets; // Assets object

    private PropertiesFile prop; // Properties file

    private int width, height; // Width and height of the window

    private int cardWidth, cardHeight; // Width and height of the cards

    private int hgap, vgap; // Horizontal and vertical gap between the cards

    private int[] positionsX, positionsY; // Arrays containing positions of the card panels
    private int stackX, stackY; // Position of the stack panel
    private int discardX, discardY; // Position of the discard panel
    private int playerX, playerY; // Position of the player panel
    private int panelWidth, panelHeight; // Width and height of the cards panels


    private LinkedHashMap<Integer, JPanel> panels; // Map of the panels containing the cards buttons and labels

    private JLabel playerLabel; // Panel containing the player labels

    private StackButton stackButton; // Button that shows the stack pile
    private DiscardButton discardButton; // Button that shows the discard pile

    /**
     * Constructor of the UI class
     * @param game reference to the game object
     */
    public UI(Game game) {
        super("Fail your Deutec"); // Title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Ends the program when the main window is closed

        this.game = game;
        this.game.setUI(this);

        prop=new PropertiesFile();
        if(prop.getBooleanProperty("borderless")){ // If the borderless option is enabled
            this.setUndecorated(true); // Removes the border of the window
        }
        this.readSettings(); // Reads the settings from the properties file. The borderless feature is not included because it doesn't support to be updated while the program is running
        this.calculateSizes();
        assets = new Assets();

        this.MainMenu();
    }

    /**
     * Calculates the size of the images.
     * We could have put the values in a file, but we decided to hardcode them for optimization purposes.
     * The values are calculated with the original size of the window (1280x720).
     * Some values are not constant for the clarity of the code. (Sorry for the magic numbers).
     */
    private void calculateSizes() {

        positionsX = new int[8];
        positionsY = new int[8];

        width = this.getWidth();
        height = this.getHeight();

        hgap = (width * GAP)/ ORIGINAL_WIDTH;
        vgap = (height * GAP)/ORIGINAL_HEIGHT;

        // First position (player)
        positionsX[0] = (width * 320)/ORIGINAL_WIDTH;
        positionsY[0] = (height * 300)/ORIGINAL_HEIGHT;
        // Second position
        positionsX[1] = (width * 40)/ORIGINAL_WIDTH;
        positionsY[1] = (height * 420)/ORIGINAL_HEIGHT;
        // Third position
        positionsX[2] = (width * 40)/ORIGINAL_WIDTH;
        positionsY[2] = (height * 170)/ORIGINAL_HEIGHT;
        // Fourth position
        positionsX[3] = (width * 290)/ORIGINAL_WIDTH;
        positionsY[3] = (height * 40)/ORIGINAL_HEIGHT;
        // Fifth position
        positionsX[4] = (width * 550)/ORIGINAL_WIDTH;
        positionsY[4] = (height * 40)/ORIGINAL_HEIGHT;
        // Sixth position
        positionsX[5] = (width * 810)/ORIGINAL_WIDTH;
        positionsY[5] = (height * 40)/ORIGINAL_HEIGHT;
        // Seventh position
        positionsX[6] = (width * 1050)/ORIGINAL_WIDTH;
        positionsY[6] = (height * 170)/ORIGINAL_HEIGHT;
        // Eighth position
        positionsX[7] = (width * 1050)/ORIGINAL_WIDTH;
        positionsY[7] = (height * 420)/ORIGINAL_HEIGHT;

        // Stack position
        stackX = (width * 810)/ORIGINAL_WIDTH;
        stackY = (height * 330)/ORIGINAL_HEIGHT;

        // Discard pile position
        discardX = (width * 810)/ORIGINAL_WIDTH;
        discardY = (height * 520)/ORIGINAL_HEIGHT;

        // Player position
        playerX = (width * 320)/ORIGINAL_WIDTH;
        playerY = (height * 262)/ORIGINAL_HEIGHT;

        this.cardWidth = (width * 80)/ORIGINAL_WIDTH;
        this.cardHeight = (height * 112)/ORIGINAL_HEIGHT;

        panelWidth = Game.DECK_COLS*cardWidth + (Game.DECK_COLS-1)*hgap;
        panelHeight = Game.DECK_ROWS*cardHeight + (Game.DECK_ROWS-1)*vgap;
    }

    /**
     * Updates the window settings.
     */
    private void readSettings() {
        prop=new PropertiesFile();
        width = prop.getIntProperty("window_width");
        height = prop.getIntProperty("window_height");

        this.setSize(width, height);
        this.calculateSizes();
    }

    /**
     * Creates the main menu.
     */
    private void MainMenu() {
        this.getContentPane().removeAll(); // Removes all the components from the window
        assets.loadMenu(width, height); // Loads the menu images

        // Creates the buttons
        MenuButton start = new MenuButton(assets.getStart(), assets.getStartHover());
        start.addActionListener(e -> new PlayersWindow(game)); // Opens the window to choose the number of players and their names
        MenuButton settings = new MenuButton(assets.getSettings(), assets.getSettingsHover());
        settings.addActionListener(e -> {
            new SettingsWindow(); // Opens the settings window
            this.readSettings(); // Updates the window settings
        });
        MenuButton minigame = new MenuButton(assets.getMinigame(), assets.getMinigameHover());
        minigame.addActionListener(e -> new MiniGameWindow());
        MenuButton quit = new MenuButton(assets.getQuit(), assets.getQuitHover());
        quit.addActionListener(e -> System.exit(0));

        // Adds the buttons to the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.add(start);
        mainPanel.add(settings);
        mainPanel.add(minigame);
        mainPanel.add(quit);
        mainPanel.setVisible(true);

        mainPanel.setBounds(0, (500*height)/ORIGINAL_HEIGHT, width, height); // Sets the size and the position of the panel
        JLabel background = new JLabel(assets.getMenuBackground()); // Creates the background
        background.setBounds(0, 0, width, height); // Sets the size and the position of the background

        // Adds the background and the main panel to the layered pane (for transparency)
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(background, 0);
        layeredPane.add(mainPanel, 1);
        mainPanel.setOpaque(false);
        layeredPane.setOpaque(true);

        this.getContentPane().add(layeredPane); // Adds the layered pane to the window
        this.setLocationRelativeTo(null); // Centers the window

        // Adds a listener to the window to update the sizes when the window is resized
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

    /**
     * Generates the panels containing the matrix of cards.
     */
    private void generatePanels() {
        panels = new LinkedHashMap<>(); // Creates a map to store the panels
        panels.put(0, new JPanel()); // Creates the first panel separately because the player's cards are buttons
        panels.get(0).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap, vgap));
        for(int i = 0; i< Game.DECK_ROWS * Game.DECK_COLS; i++){
            panels.get(0).add(new CardButton(i, assets, game, this)); // Adds the buttons to the panel
        }

        for(int i=1;i<game.getNbPlayers();i++) {
            panels.put(i, new JPanel()); // Creates the other panels
            panels.get(i).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap/2, vgap/2));
            for(int j = 0; j < Game.DECK_ROWS * Game.DECK_COLS; j++){
                panels.get(i).add(new CardLabel(i - 1, j, assets, game)); // Adds the labels to the panel
            }
        }
    }

    /**
     * Sets the cards of the panels.
     */
    public void setAllCards() {
        for(int i=0; i<Game.DECK_ROWS * Game.DECK_COLS;i++){
            ((CardButton) panels.get(0).getComponent(i)).setCard();
        }
        for(int i=1;i<game.getNbPlayers();i++) {
            for(int j = 0; j < Game.DECK_ROWS * Game.DECK_COLS; j++){
                ((CardLabel) panels.get(i).getComponent(j)).setCard();
            }
        }
    }

    /**
     * Enables or disables all the card buttons
     * @param b true to enable, false to disable
     */
    public void EnableAllCards(boolean b) {
        for(int i=0;i<Game.DECK_ROWS * Game.DECK_COLS;i++) {
            panels.get(0).getComponent(i).setEnabled(b);
        }
    }

    /**
     * Updates the window to show the game interface.
     */
    public void gameInterface() {
        this.generatePanels(); // Generates the panels
        this.getContentPane().removeAll(); // Removes all the components from the window
        assets.unloadMenu(); // Unloads the menu images
        assets.loadInGame(width, height, cardWidth, cardHeight); // Loads the in-game images

        // Creates the panels
        JPanel mainPanel = new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        JLabel background = new JLabel(assets.getBackground());

        // Adds the panels to the layered pane
        panels.get(0).setBounds(positionsX[0], positionsY[0], panelWidth, panelHeight); // Sets the size and the position of the player panel
        for(int i=0;i<game.getNbPlayers();i++) {
            mainPanel.add(panels.get(i));
        }

        /**
         * Sets the size and the position of the other panels depending on the number of players
         */
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
            default -> { // 8 players
                for(int i=1;i<game.getNbPlayers();i++) {
                    panels.get(i).setBounds(positionsX[i], positionsY[i], panelWidth / 2, panelHeight / 2);
                }
            }
        }

        // Stack button
        stackButton = new StackButton(assets, game);
        stackButton.setBounds(stackX, stackY, cardWidth, cardHeight);
        // Discard button
        discardButton = new DiscardButton(assets, game);
        discardButton.setBounds(discardX, discardY, cardWidth, cardHeight);

        playerLabel = new JLabel() { // Label displaying the current player
            /**
             * Repaints the label
             * @param g the graphics
             */
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                this.repaint();
            }
        };
        playerLabel.setHorizontalAlignment(JLabel.CENTER); // Sets the text to be centered
        playerLabel.setBounds(playerX, playerY, panelWidth, 30); // Sets the size and the position of the label
        playerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Sets the font and the size of the text
        updatePlayerLabel(); // Updates the text of the label

        // Adds the components to the main panel
        mainPanel.add(stackButton);
        mainPanel.add(discardButton);
        mainPanel.add(playerLabel);
        mainPanel.setLayout(null); // Sets the layout to null to be able to set the position of the components
        background.setBounds(0, 0, width, height);
        mainPanel.setBounds(0, 0, width, height);
        layeredPane.setBounds(0, 0, width, height);

        // Reloads the window
        this.repaint();
        this.revalidate();

        // Adds the panels to the layered pane
        layeredPane.add(background, 0);
        layeredPane.add(mainPanel, 1);
        this.getContentPane().add(layeredPane); // Adds the layered pane to the window

        this.putPlayerInTitle(); // Puts the current player in the title of the window
    }

    /**
     * @return the stack button
     */
    public StackButton getStackButton() {
        return stackButton;
    }

    /**
     * @return the discard button
     */
    public DiscardButton getDiscardButton() {
        return discardButton;
    }

    /**
     * Puts the current player in the title of the window
     */
    public void putPlayerInTitle() {
        this.setTitle("Fail Your Deutec - " + game.getPlayer(game.getCurrentPlayer()).getName() + "'s turn");
    }

    /**
     * Updates the text of the player label
     */
    public void updatePlayerLabel() {
        playerLabel.setText(game.getPlayer(game.getCurrentPlayer()).getName());
    }

    /**
     * Increments the current player using the method in the Game class, puts the current player in the title and updates the player label
     */
    public void incrementCurrentPlayer() {
        game.incrementCurrentPlayer();
        putPlayerInTitle();
        updatePlayerLabel();
    }
}
