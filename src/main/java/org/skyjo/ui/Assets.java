package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class Assets {

    public static final int MENU_BUTTON_ORIGINAL_WIDTH = 146, MENU_BUTTON_ORIGINAL_HEIGHT = 42; // Original width and height of the menu buttons

    private ImageIcon background, menuBackground, start, startHover, settings, settingsHover, minigame, minigameHover, quit, quitHover, cardBack, cardBackSmall; // All the images
    private ImageIcon[] cards, cardsSmall; // All the card images in arrays

    /**
     * Loads all the assets used in the menu
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     */
    public void loadMenu(int screenWidth, int screenHeight) {
        // Background
        Image tmp = new ImageIcon("src/main/resources/assets/menu/menu_background.png").getImage();
        this.menuBackground = new ImageIcon(tmp.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH));

        int menuWidth, menuHeight; // Width and height of the menu buttons
        menuWidth = 2*(screenWidth * MENU_BUTTON_ORIGINAL_WIDTH)/UI.ORIGINAL_WIDTH; // 146 is the original width of the image
        menuHeight = 2*(screenHeight * MENU_BUTTON_ORIGINAL_HEIGHT)/UI.ORIGINAL_HEIGHT; // 42 is the original height of the image

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

    /**
     * Unloads all the assets used in the menu
     */
    public void unloadMenu(){
        this.menuBackground = null;
        this.start = null;
        this.startHover = null;
        this.settings = null;
        this.settingsHover = null;
        this.minigame = null;
        this.minigameHover = null;
        this.quit = null;
        this.quitHover = null;
    }

    /**
     * Loads all the assets used in the game
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param cardWidth the width of the cards
     * @param cardHeight the height of the cards
     */
    public void loadInGame(int screenWidth, int screenHeight, int cardWidth, int cardHeight) {
        // Background
        Image tmp = new ImageIcon("src/main/resources/assets/background.png").getImage();
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

    /**
     * Unloads all the assets used in the game
     */
    public void unloadInGame() {
        this.background = null;
        this.cardBack = null;
        this.cardBackSmall = null;
        this.cards = null;
        this.cardsSmall = null;
    }

    /**
     * @return the menu background
     */
    public ImageIcon getMenuBackground() {
        return menuBackground;
    }

    /**
     * @return the start button
     */
    public ImageIcon getStart() {
        return start;
    }

    /**
     * @return the start button when hovered
     */
    public ImageIcon getStartHover() {
        return startHover;
    }

    /**
     * @return the settings button
     */
    public ImageIcon getSettings() {
        return settings;
    }

    /**
     * @return the settings button when hovered
     */
    public ImageIcon getSettingsHover() {
        return settingsHover;
    }

    /**
     * @return the minigame button
     */
    public ImageIcon getMinigame() {
        return minigame;
    }

    /**
     * @return the minigame button when hovered
     */
    public ImageIcon getMinigameHover() {
        return minigameHover;
    }

    /**
     * @return the quit button
     */
    public ImageIcon getQuit() {
        return quit;
    }

    /**
     * @return the quit button when hovered
     */
    public ImageIcon getQuitHover() {
        return quitHover;
    }

    /**
     * @return the background
     */
    public ImageIcon getBackground() {
        return background;
    }

    /**
     * @return the card back
     */
    public ImageIcon getCardBack() {
        return cardBack;
    }

    /**
     * @return the small card back small
     */
    public ImageIcon getCardBackSmall() {
        return cardBackSmall;
    }

    /**
     * @param value the value of the card
     * @return the card
     */
    public ImageIcon getCard(int value) {
        return cards[value];
    }

    /**
     * @param value the value of the card
     * @return the small card
     */
    public ImageIcon getCardSmall(int value) {
        return cardsSmall[value];
    }
}