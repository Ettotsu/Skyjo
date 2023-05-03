package org.skyjo.ui;

import org.skyjo.game.Game;
import org.skyjo.minigame.MiniGameWindow;
import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class UI extends JFrame {

    private Game game; // Game object

    private PropertiesFile prop; // Properties file
    private int width,height,cardWidth,cardHeight,hgap,vgap; /* Variables are:
    - width and height: the width and height of the window
    - cardWidth and cardHeight: the width and height of the cards
    - hgap and vgap: the horizontal and vertical gap between the cards*/

    private int menuWidth, menuHeight; // Width and height of the menu buttons


    public UI(Game game) {
        super("Fail your Deutec"); // Title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.game = game;

        this.MainMenu();
    }

    private void updateWindowSettings(){
        prop=new PropertiesFile();
        width = prop.getIntProperty("window_width");
        height = prop.getIntProperty("window_height");

        this.setSize(width, height);
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

        MenuButton start = new MenuButton("src/main/resources/assets/menu/start.png", menuWidth, menuHeight);
        start.addActionListener(e -> new PlayersWindow(game));
        MenuButton settings = new MenuButton("src/main/resources/assets/menu/settings.png", menuWidth, menuHeight);
        settings.addActionListener(e -> {
            new SettingsWindow();
            this.updateWindowSettings();

        });
        MenuButton minigame = new MenuButton("src/main/resources/assets/menu/minigame.png", menuWidth, menuHeight);
        minigame.addActionListener(e -> new MiniGameWindow());
        MenuButton quit = new MenuButton("src/main/resources/assets/menu/quit.png", menuWidth, menuHeight);
        quit.addActionListener(e -> System.exit(0));
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.add(start);
        Dimension gap = new Dimension(10, 0);
        mainMenuPanel.add(Box.createRigidArea(gap));
        mainMenuPanel.add(settings);
        mainMenuPanel.add(Box.createRigidArea(gap));
        mainMenuPanel.add(minigame);
        mainMenuPanel.add(Box.createRigidArea(gap));
        mainMenuPanel.add(quit);

        JLayeredPane layeredPane = new JLayeredPane();

        mainMenuPanel.setBounds(0, (500*height)/720, width, height);
        Image img = new ImageIcon("src/main/resources/assets/menu/background.png").getImage();
        JLabel background = new JLabel(new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, width, height);

        layeredPane.add(mainMenuPanel, 1);
        layeredPane.add(background, 0);


        this.getContentPane().add(layeredPane);
        this.setLocationRelativeTo(null);

        mainMenuPanel.repaint();
        mainMenuPanel.revalidate();

        this.setResizable(false); // Disables the resizing of the window
        this.setVisible(true); // Makes the window visible
    }

    private void calculateImgSize(){
        cardWidth = (width * 80)/1280;
        cardHeight = (height * 112)/720;
        hgap = (width * 12)/1280;
        vgap = (height * 12)/720;
        menuWidth = 2*(width * 146)/1280;
        menuHeight = 2*(height * 42)/720;
    }

    private void generatePanels(){
        LinkedHashMap<Integer, JPanel> panels = new LinkedHashMap<>();
        panels.put(0, new JPanel());
        panels.get(0).setPreferredSize(new Dimension(cardWidth, cardHeight));
        panels.get(0).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap, vgap));
        for(int i = 0; i< Game.DECK_ROWS * Game.DECK_COLS; i++){
            panels.get(0).add(new JLabel(new ImageIcon("src/main/resources/cards/back.png")));
        }


        for(int i=1;i<8;i++){
            panels.put(i, new JPanel());
            panels.get(i).setPreferredSize(new Dimension(cardWidth/2, cardHeight/2));
            panels.get(i).setLayout(new GridLayout(Game.DECK_ROWS, Game.DECK_COLS, hgap/2, vgap/2));
        }
    }


    private void gameInterface() {
        this.getContentPane().removeAll(); // Clears the panel
        this.getContentPane().setLayout(null); // Sets the layout to absolute
        this.getContentPane().setBackground(Color.BLUE);


        this.repaint(); // Clears the screen
        this.revalidate(); // Refreshes the screen
    }
}
