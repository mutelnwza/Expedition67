package com.Expedition67.storage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class AssetManager {

    /* singleton pattern in short: design pattern to make sure that a class can
    only have 1 instance (prevent other class to recreate. And have an global
    access point for everywhere) */

    private static AssetManager instance;
    private HashMap<String, BufferedImage[][]> spriteDatabase = new HashMap<>();
    private HashMap<String, BufferedImage> cardDatabase = new HashMap<>();
    private Font gameFont;

    // make it private to prevent other class from creating this
    private AssetManager() {
        loadSprite();
        loadCard();
        gameFont = loadFont("/fonts/Jersey10-Regular.ttf");
    }

    public static AssetManager Instance() {
        //  create an instance if its not created yet
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private void loadSprite() {
        // hard code เอานะจ๊ะ
        // register all sprites with load method
        // use this -> spriteDatabase.put("name", load(path,w,h))
        spriteDatabase.put("test",load("/img/test.png",500,500));
    }

    private void loadCard(){
        cardDatabase.put("RemnantHit", load("/img/RemnantHit.png"));
        cardDatabase.put("Celestial_Singularity", load("/img/Celestial_Singularity.png"));
        cardDatabase.put("Echoing_Strike", load("/img/Echoing_Strike.png"));
        cardDatabase.put("Eternal_Soul_Rebirth", load("/img/Eternal_Soul_Rebirth.png"));
        cardDatabase.put("Ethereal Restoration", load("/img/Ethereal_Restoration.png"));
        cardDatabase.put("Event_Horizon", load("/img/Event_Horizon.png"));
        cardDatabase.put("Harmonic_Resonance", load("/img/Harmonic_Resonance.png"));
        cardDatabase.put("SoulAegis", load("/img/SoulAegis.png"));
        cardDatabase.put("SoulFlicker", load("/img/SoulFlicker.png"));
        cardDatabase.put("Soul_Resonance", load("/img/Soul_Resonance.png"));
        cardDatabase.put("Sovereigns_Overdrive", load("/img/Sovereigns_Overdrive.png"));
        cardDatabase.put("Spectral_Veil", load("/img/Spectral_Veil .png"));
        cardDatabase.put("Void_Dragon", load("/img/Void_Dragon.png"));
        
    }

    private BufferedImage load(String path){
        try{
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(path));
            return img;
        }
        catch(Exception e){
            System.err.println("error loading "+path);
            return null;
        }
    }

    // w and h is size per image (size for spliting)
    private BufferedImage[][] load(String path, int w, int h) {
        try {
            BufferedImage sheet = ImageIO.read(getClass().getResourceAsStream(path));
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
            return null;
        }
    }

    public BufferedImage getSprite(String key, int row, int index) {
        return spriteDatabase.get(key)[row][index];
    }

    public Font loadFont(String path) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(path));
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
