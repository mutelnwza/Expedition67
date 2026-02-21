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

    public void invoke(){
        loadSprite();
        loadCard();
    }

    private void loadSprite() {
        // hard code เอานะจ๊ะ
        // register all sprites with load method
        // use this -> spriteDatabase.put("name", load(path,w,h))
        spriteDatabase.put("Player", loadSpriteImage("/images/sprites/player.png", 2000, 2000)); // Should be w = 500, h = 500
        spriteDatabase.put("CryingSlime", loadSpriteImage("/images/sprites/crying_slime.png",500,500));
        spriteDatabase.put("Lukchin", loadSpriteImage("/images/sprites/luk_chin.png",500,500));
        spriteDatabase.put("Vision", loadSpriteImage("/images/sprites/vision.png",500,500));
        spriteDatabase.put("Dad", loadSpriteImage("/images/sprites/dadteerawat.png",500,500));
        spriteDatabase.put("Son", loadSpriteImage("/images/sprites/child.png",500,500));
        spriteDatabase.put("BigBadBoss", loadSpriteImage("/images/sprites/big_bad_boss.png",500,500));
        spriteDatabase.put("TillyTheBird", loadSpriteImage("/images/sprites/tilly_the_bird.png",500,500));
        spriteDatabase.put("RedEye", loadSpriteImage("/images/sprites/red_eyes.png",500,500));
    }

    private void loadCard(){
        cardDatabase.put("Remnant Hit", loadCardImage("/images/cards/remnant_hit.png"));
        cardDatabase.put("Celestial Singularity", loadCardImage("/images/cards/celestial_singularity.png"));
        cardDatabase.put("Echoing Strike", loadCardImage("/images/cards/echoing_strike.png"));
        cardDatabase.put("Eternal Soul Rebirth", loadCardImage("/images/cards/eternal_soul_rebirth.png"));
        cardDatabase.put("Ethereal Restoration", loadCardImage("/images/cards/ethereal_restoration.png"));
        cardDatabase.put("Event Horizon", loadCardImage("/images/cards/event_horizon.png"));
        cardDatabase.put("Harmonic Resonance", loadCardImage("/images/cards/harmonic_resonance.png"));
        cardDatabase.put("Soul Aegis", loadCardImage("/images/cards/soul_aegis.png"));
        cardDatabase.put("Soul Flicker", loadCardImage("/images/cards/soul_flicker.png"));
        cardDatabase.put("Soul Resonance", loadCardImage("/images/cards/soul_resonance.png"));
        cardDatabase.put("Sovereign's Overdrive", loadCardImage("/images/cards/sovereign_is_overdrive.png"));
        cardDatabase.put("Spectral Veil", loadCardImage("/images/cards/spectral_veil.png"));
        cardDatabase.put("Void Dragon", loadCardImage("/images/cards/void_dragon.png"));

    }

    private BufferedImage loadCardImage(String path){
        try{
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(path));
            return img;
        }
        catch(Exception e){
            System.err.println("error loading "+path);
            e.printStackTrace();
            return null;
        }
    }

    // w and h is size per image (size for spliting)
    private BufferedImage[][] loadSpriteImage(String path, int w, int h) {
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
            //e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getSprite(String key, int row, int index) {
        return spriteDatabase.get(key)[row][index];
        
    }

    public BufferedImage getCard(String key) {
        return cardDatabase.get(key);
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
