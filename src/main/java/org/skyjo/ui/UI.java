package org.skyjo.ui;

import org.skyjo.game.Game;
import org.skyjo.minigame.MiniGameWindow;
import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class UI extends JFrame {

    private Game game; // Game object
    private JPanel panel; // Main panel
    private int rows,cols; //N umber of rows and columns of the deck

    private PropertiesFile prop; // Properties file
    private int width,height,cardWidth,cardHeight,hgap,vgap; /* Variables are:
    - width and height: the width and height of the window
    - cardWidth and cardHeight: the width and height of the cards
    - hgap and vgap: the horizontal and vertical gap between the cards*/

    private boolean borderless;

    public UI(Game game, int rows, int cols) {
        super("Fail your Deutec"); // Title of the window
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.game = game;
        this.rows = rows;
        this.cols = cols;


        panel = new JPanel();
        this.MainMenu();
    }



    public void MainMenu(){

        prop=new PropertiesFile();
        width = Integer.parseInt(prop.getProperty("window_width"));
        height = Integer.parseInt(prop.getProperty("window_height"));
        borderless = Boolean.parseBoolean(prop.getProperty("window_borderless"));

        this.setSize(width, height);
        if(borderless){
            this.setUndecorated(true);
        }

        JButton start = new JButton("Start a new game");
        start.addActionListener(e -> {
            gameInterface();
        });
        JButton settings = new JButton("Settings");
        settings.addActionListener(e -> {
            SettingsWindow settingsWindow = new SettingsWindow();
        });
        JButton minigame = new JButton("MiniGame");
        minigame.addActionListener(e -> {
            MiniGameWindow miniGameWindow = new MiniGameWindow();
        });
        JButton quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(20, this.getHeight()/2)));
        panel.add(start);
        Dimension gap = new Dimension(0, 14);
        panel.add(Box.createRigidArea(gap));
        panel.add(settings);
        panel.add(Box.createRigidArea(gap));
        panel.add(minigame);
        panel.add(Box.createRigidArea(gap));
        panel.add(quit);
        this.getContentPane().add(panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // Disables the resizing of the window
        this.setVisible(true); // Makes the window visible

    }

    private void calculateCardSize(){
        cardWidth = (width * 80)/1280;
        cardHeight = (height * 112)/720;
        hgap = (width * 12)/1280;
        vgap = (height * 12)/720;
        //xPos = (width * 100)/1280;
        //yPos = (height * 180)/720;

    }
    public void gameInterface() {
        panel.removeAll(); // Clears the panel
        panel.setLayout(null); // Sets the layout to absolute
        panel.setBackground(Color.BLUE);
        JPanel playerPanel = new JPanel();
        playerPanel.setOpaque(false); // Makes the panel transparent
        JPanel opponentPanel = new JPanel();
        opponentPanel.setOpaque(false); // Makes the panel transparent


        calculateCardSize();
        LinkedHashMap<Integer, CardButton> map = new LinkedHashMap<>();
        for(int i=-2;i<=9;i++){
            map.put(i, new CardButton(i, true));
            map.get(i).setPreferredSize(new Dimension(cardWidth, cardHeight));
            playerPanel.add(map.get(i));
        }
        playerPanel.setLayout(new GridLayout(rows, cols, hgap, vgap));
        playerPanel.setBounds(0,0,cardWidth*cols + hgap*(cols-1),cardHeight*rows + vgap*(rows-1));


        panel.add(playerPanel, BorderLayout.CENTER);

        this.repaint(); // Clears the screen
        this.revalidate(); // Refreshes the screen

    }
}
