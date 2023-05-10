package org.skyjo.ui;

import org.skyjo.game.Game;
import org.skyjo.minigame.MiniGameWindow;
import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UI extends JFrame {

    private Game game; // Game object

    private PropertiesFile prop; // Properties file

    private int width, height; // Width and height of the window

    private int cardWidth,cardHeight,hgap,vgap; /* Variables are:
    - cardWidth and cardHeight: the width and height of the cards
    - hgap and vgap: the horizontal and vertical gap between the cards*/

    private int menuWidth, menuHeight; // Width and height of the menu buttons

    private ArrayList<Integer> positionsX, positionsY; // List of the positions of the cards

    private int panelWidth, panelHeight; // Width and height of the cards panels

    LinkedHashMap<Integer, JPanel> panels; // Map of the panels containing the cards buttons and labels


    public UI(Game game) {
        super("Fail your Deutec"); // Title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Ends the program when the main window is closed

        this.game = game;
        this.game.getUI(this);

        this.MainMenu();
    }

    /**
     * Calculates the size of the images.
     * We could have put the values in a file, but we decided to hardcode them for optimization purposes.
     */
    private void calculateImgSize(){
        positionsX = new ArrayList<>();
        positionsY = new ArrayList<>();

        width = this.getWidth();
        height = this.getHeight();

        cardWidth = (width * 80)/1280;
        cardHeight = (height * 112)/720;
        hgap = (width * 12)/1280;
        vgap = (height * 12)/720;
        menuWidth = 2*(width * 146)/1280;
        menuHeight = 2*(height * 42)/720;

        // First position
        positionsX.add((width * 460)/1280);
        positionsY.add((height * 300)/720);

        // Second position
        positionsX.add((width * 50)/1280);
        positionsY.add((height * 420)/720);

        // Third position
        positionsX.add((width * 50)/1280);
        positionsY.add((height * 200)/720);

        // Fourth position
        positionsX.add((width * 460)/1280);
        positionsY.add((height * 100)/720);

        // Fifth position
        positionsX.add((width * 870)/1280);
        positionsY.add((height * 200)/720);

        // Sixth position
        positionsX.add((width * 870)/1280);
        positionsY.add((height * 420)/720);

        // Seventh position
        positionsX.add((width * 460)/1280);
        positionsY.add((height * 500)/720);

        // Eighth position
        positionsX.add((width * 50)/1280);
        positionsY.add((height * 500)/720);

        panelWidth = Game.DECK_COLS*cardWidth + (Game.DECK_COLS-1)*hgap;
        panelHeight = Game.DECK_ROWS*cardHeight + (Game.DECK_ROWS-1)*vgap;
    }

    /**
     * Updates the window settings.
     */
    private void updateWindowSettings(){
        prop=new PropertiesFile();
        width = prop.getIntProperty("window_width");
        height = prop.getIntProperty("window_height");

        this.setSize(width, height);
        this.calculateImgSize();
        this.repaint();
        this.revalidate();
    }

    private void MainMenu(){
        prop=new PropertiesFile();
        if(prop.getBooleanProperty("borderless")){
            this.setUndecorated(true);
        }
        this.updateWindowSettings();
        this.calculateImgSize();

        MenuButton start = new MenuButton(menuWidth,menuHeight,"src/main/resources/assets/menu/start.png");
        start.addActionListener(e -> new PlayersWindow(game));
        MenuButton settings = new MenuButton(menuWidth,menuHeight,"src/main/resources/assets/menu/settings.png");
        settings.addActionListener(e -> {
            new SettingsWindow();
            this.updateWindowSettings();

        });
        MenuButton minigame = new MenuButton(menuWidth,menuHeight,"src/main/resources/assets/menu/minigame.png");
        minigame.addActionListener(e -> new MiniGameWindow());
        MenuButton quit = new MenuButton(menuWidth,menuHeight,"src/main/resources/assets/menu/quit.png");
        quit.addActionListener(e -> System.exit(0));
        JPanel mainPanel = new JPanel();
        mainPanel.add(start);
        mainPanel.add(settings);
        mainPanel.add(minigame);
        mainPanel.add(quit);
        mainPanel.setVisible(true);

        JLayeredPane layeredPane = new JLayeredPane();

        mainPanel.setBounds(0, (500*height)/720, width, height);
        Image img = new ImageIcon("src/main/resources/assets/menu/background.png").getImage();
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);

        layeredPane.add(mainPanel, 1);
        layeredPane.add(background, 0);


        this.getContentPane().add(layeredPane);
        this.setLocationRelativeTo(null);

        this.addComponentListener(new ComponentListener() { // It needs to override all the methods
            @Override
            public void componentResized(ComponentEvent e) {calculateImgSize();}
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
            panels.get(0).add(new CardButton(cardWidth, cardHeight));
        }

        for(int i=1;i<8;i++){
            panels.put(i, new JPanel());
            panels.get(i).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap/2, vgap/2));
            for(int j = 0; j < Game.DECK_ROWS * Game.DECK_COLS; j++){
                panels.get(i).add(new CardLabel(cardWidth/2, cardHeight/2));
            }
        }
    }

    public void gameInterface() {
        this.generatePanels();
        this.getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        Image img = new ImageIcon("src/main/resources/assets/background.png").getImage();
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);

        panels.get(0).setBounds(positionsX.get(0), positionsY.get(0), panelWidth, panelHeight);
        mainPanel.add(panels.get(0));
        for(int i=1;i<4;i++){
            mainPanel.add(panels.get(i));
            panels.get(i).setBounds(positionsX.get(i), positionsY.get(i), panelWidth/2, panelHeight/2);
        }

        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, width, height);


        layeredPane.add(mainPanel, 1);
        layeredPane.add(background, 0);

        this.getContentPane().add(layeredPane);

        this.repaint();
        this.revalidate();
    }
}
