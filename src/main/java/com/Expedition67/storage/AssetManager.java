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
    private HashMap<String, BufferedImage> staticDatabase = new HashMap<>();
    private Font gameFont;

    // make it private to prevent other class from creating this
    private AssetManager() {
        // loadSprite();
        // loadCard();
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
        spriteDatabase.put("test",load("/img/test.png",500,500));
    }

    private void loadCard(){
        staticDatabase.put("RemnantHit", load("/img/RemnantHit.png"));
    }

    private BufferedImage load(String path){
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
            e.printStackTrace();
            return null;
        }
    }

    public BufferedImage getSprite(String key, int row, int index) {
        return spriteDatabase.get(key)[row][index];
    }

    public BufferedImage getSprite(String key){
        return staticDatabase.get(key);
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
