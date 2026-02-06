package com.Expedition67.storage;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class AssetManager {

    /* singleton pattern in short: design pattern to make sure that a class can
    only have 1 instance (prevent other class to recreate. And have an global
    access point for everywhere) */

    private static AssetManager instance;
    private HashMap<String, BufferedImage[][]> spriteDatabase = new HashMap<>();

    // make it private to prevent other class from creating this
    private AssetManager(){
        loadSprite();
    }

    public static AssetManager Instance(){
        //  create an instance if its not created yet
        if(instance==null){
            instance = new AssetManager();
        }return instance;
    }

    private void loadSprite(){
        // hard code เอานะจ๊ะ
        // register all sprites with load method
        // use this -> spriteDatabase.put("name", load(path,w,h))
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

    public BufferedImage getSprite(String key, int row, int index){
        return spriteDatabase.get(key)[row][index];
    }
}
