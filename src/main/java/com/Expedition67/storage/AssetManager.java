package com.Expedition67.storage;

import com.Expedition67.card.CardName;
import com.Expedition67.unit.UnitName;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Objects;

public class AssetManager {

    /* singleton pattern in short: design pattern to make sure that a class can
    only have 1 instance (prevent other class to recreate. And have a global
    access point for everywhere) */

    private static AssetManager instance;
    private final HashMap<UnitName, BufferedImage[][]> spriteDatabase = new HashMap<>();
    private final HashMap<CardName, BufferedImage> cardDatabase = new HashMap<>();
    private final Font gameFont;

    // make it private to prevent other class from creating this
    private AssetManager() {
        loadSprite();
        loadCard();
        gameFont = loadFont("/fonts/Jersey10-Regular.ttf");
    }

    public static AssetManager Instance() {
        //  create an instance if it's not created yet
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private void loadSprite() {
        // hard code เอานะจ๊ะ
        // register all sprites with load method
        // use this -> spriteDatabase.put("name", load(path,w,h))
        spriteDatabase.put(UnitName.BIG_BAD_BOSS, loadSpriteImage("/images/sprites/Big_Bad_Boss.png"));
        spriteDatabase.put(UnitName.CRYING_SLIME, loadSpriteImage("/images/sprites/Crying_Slime.png"));
        spriteDatabase.put(UnitName.LUKCHIN, loadSpriteImage("/images/sprites/Lukchin.png"));
        spriteDatabase.put(UnitName.PLAYER, loadSpriteImage("/images/sprites/Player.png"));
        spriteDatabase.put(UnitName.RED_EYES, loadSpriteImage("/images/sprites/Red_Eyes.png"));
        spriteDatabase.put(UnitName.SON_AND_DAD, loadSpriteImage("/images/sprites/Son_And_Dad.png"));
        spriteDatabase.put(UnitName.TILLY_THE_BIRD, loadSpriteImage("/images/sprites/Tilly_The_Bird.png"));
        spriteDatabase.put(UnitName.VISION, loadSpriteImage("/images/sprites/Vision.png"));
    }

    private void loadCard() {
        cardDatabase.put(CardName.CELESTIAL_SINGULARITY, loadCardImage("/images/cards/Celestial_Singularity.png"));
        cardDatabase.put(CardName.ECHOING_STRIKE, loadCardImage("/images/cards/Echoing_Strike.png"));
        cardDatabase.put(CardName.ETERNAL_SOUL_REBIRTH, loadCardImage("/images/cards/Eternal_Soul_Rebirth.png"));
        cardDatabase.put(CardName.ETHEREAL_RESTORATION, loadCardImage("/images/cards/Ethereal_Restoration.png"));
        cardDatabase.put(CardName.EVENT_HORIZON, loadCardImage("/images/cards/Event_Horizon.png"));
        cardDatabase.put(CardName.HARMONIC_RESONANCE, loadCardImage("/images/cards/Harmonic_Resonance.png"));
        cardDatabase.put(CardName.REMNANT_HIT, loadCardImage("/images/cards/Remnant_Hit.png"));
        cardDatabase.put(CardName.SOUL_AEGIS, loadCardImage("/images/cards/Soul_Aegis.png"));
        cardDatabase.put(CardName.SOUL_FLICKER, loadCardImage("/images/cards/Soul_Flicker.png"));
        cardDatabase.put(CardName.SOUL_RESONANCE, loadCardImage("/images/cards/Soul_Resonance.png"));
        cardDatabase.put(CardName.SOVEREIGNS_OVERDRIVE, loadCardImage("/images/cards/Sovereigns_Overdrive.png"));
        cardDatabase.put(CardName.SPECTRAL_VEIL, loadCardImage("/images/cards/Spectral_Veil.png"));
        cardDatabase.put(CardName.VOID_DRAGON, loadCardImage("/images/cards/Void_Dragon.png"));
    }

    private BufferedImage loadCardImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (Exception e) {
            System.err.println("error loading " + path);
            e.printStackTrace();
            return null;
        }
    }

    // w and h is size per image (size for splitting)
    private BufferedImage[][] loadSpriteImage(String path) {
        try {
            BufferedImage sheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            int w = 500;
            int h = 500;
            int rows = sheet.getHeight() / h;
            int cols = sheet.getWidth() / w;
            BufferedImage[][] result = new BufferedImage[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    result[r][c] = sheet.getSubimage(c * w, r * h, w, h);
                }
            }
            return result;
        } catch (Exception e) {
            System.err.println("error loading " + path);
            //e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getSprite(UnitName key, int row, int index) {
        return spriteDatabase.get(key)[row][index];
    }

    public BufferedImage getCard(CardName key) {
        return cardDatabase.get(key);
    }

    public Font loadFont(String path) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream(path)));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (Exception e) {
            System.err.println("Could not load font, using Arial.");
            return new Font("Arial", Font.PLAIN, 12);
        }
    }

    public Font getGameFont() {
        return gameFont;
    }
}
