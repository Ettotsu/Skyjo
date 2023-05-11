package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class Assets {

    private ImageIcon background, menuBackground, start, startHover, settings, settingsHover, minigame, minigameHover, quit, quitHover, cardBack, cardBackSmall;
    private ImageIcon[] cards, cardsSmall;

    public Assets() {
    }

    public void loadMenu(int screenWidth, int screenHeight) {
        // Background
        Image tmp = new ImageIcon("src/main/resources/assets/menu/menu_background.png").getImage();
        this.menuBackground = new ImageIcon(tmp.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH));

        int menuWidth, menuHeight; // Width and height of the menu buttons
        menuWidth = 2*(screenWidth * 146)/1280; // 146 is the original width of the image
        menuHeight = 2*(screenHeight * 42)/720; // 42 is the original height of the image

        // Start button
        tmp = new ImageIcon("src/main/resources/assets/menu/start.png").getImage();
        this.start = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        tmp = new ImageIcon("src/main/resources/assets/menu/start_hover.png").getImage();
        this.startHover = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        // Settings button
        tmp = new ImageIcon("src/main/resources/assets/menu/settings.png").getImage();
        this.settings = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        tmp = new ImageIcon("src/main/resources/assets/menu/settings_hover.png").getImage();
        this.settingsHover = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        // Minigame button
        tmp = new ImageIcon("src/main/resources/assets/menu/minigame.png").getImage();
        this.minigame = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        tmp = new ImageIcon("src/main/resources/assets/menu/minigame_hover.png").getImage();
        this.minigameHover = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        // Quit button
        tmp = new ImageIcon("src/main/resources/assets/menu/quit.png").getImage();
        this.quit = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
        tmp = new ImageIcon("src/main/resources/assets/menu/quit_hover.png").getImage();
        this.quitHover = new ImageIcon(tmp.getScaledInstance(menuWidth, menuHeight, Image.SCALE_SMOOTH));
    }

    public void loadInGame(int screenWidth, int screenHeight, int cardWidth, int cardHeight) {
        // Background
        Image tmp = new ImageIcon("src/main/resources/assets/menu/menu_background.png").getImage();
        this.background = new ImageIcon(tmp.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH));

        // Card back
        tmp = new ImageIcon("src/main/resources/assets/cards/back.png").getImage();
        this.cardBack = new ImageIcon(tmp.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
        this.cardBackSmall = new ImageIcon(tmp.getScaledInstance(cardWidth/2, cardHeight/2, Image.SCALE_SMOOTH));
        // Cards
        this.cards = new ImageIcon[15];
        this.cardsSmall = new ImageIcon[15];
        for (int i=-2;i<=12;i++) {
            tmp = new ImageIcon("src/main/resources/assets/cards/front_" + i + ".png").getImage();
            this.cards[i+2] = new ImageIcon(tmp.getScaledInstance(cardWidth, cardHeight, Image.SCALE_SMOOTH));
            this.cardsSmall[i+2] = new ImageIcon(tmp.getScaledInstance(cardWidth/2, cardHeight/2, Image.SCALE_SMOOTH));
        }
    }

    public ImageIcon getMenuBackground() {
        return menuBackground;
    }
    public ImageIcon getStart() {
        return start;
    }
    public ImageIcon getStartHover() {
        return startHover;
    }
    public ImageIcon getSettings() {
        return settings;
    }
    public ImageIcon getSettingsHover() {
        return settingsHover;
    }
    public ImageIcon getMinigame() {
        return minigame;
    }
    public ImageIcon getMinigameHover() {
        return minigameHover;
    }
    public ImageIcon getQuit() {
        return quit;
    }
    public ImageIcon getQuitHover() {
        return quitHover;
    }

    public ImageIcon getBackground() {
        return background;
    }
    public ImageIcon getCardBack() {
        return cardBack;
    }
    public ImageIcon getCardBackSmall() {
        return cardBackSmall;
    }
    public ImageIcon getCard(int value) {
        return cards[value];
    }
    public ImageIcon getCardSmall(int value) {
        return cardsSmall[value];
    }
}