package com.Expedition67.storage;

import com.Expedition67.card.CardName;
import com.Expedition67.unit.UnitName;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

/**
 * Manages loading and accessing all game assets, such as sprites, card images, and fonts.
 */
public class AssetManager {

    private static AssetManager instance;
    private final HashMap<UnitName, BufferedImage[][]> spriteDatabase = new HashMap<>();
    private final HashMap<CardName, BufferedImage> cardDatabase = new HashMap<>();
    private final HashMap<SoundName, Clip> soundDatabase = new HashMap<>();
    private Image icon;
    private final Font gameFont;

    public enum SoundName {
        BGM,
        SELECT,
        TAKE_DAMAGE
    }

    private AssetManager() {
        loadAllSprites();
        loadAllCards();
        loadAllSounds();
        loadIcon();
        gameFont = loadFont();
    }

    /**
     * Gets the single instance of the AssetManager.
     *
     * @return The single instance of AssetManager.
     */
    public static AssetManager Instance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private void loadAllSprites() {
        spriteDatabase.put(UnitName.BIG_BAD_BOSS, loadSpriteSheet("/images/sprites/Big_Bad_Boss.png"));
        spriteDatabase.put(UnitName.CRYING_SLIME, loadSpriteSheet("/images/sprites/Crying_Slime.png"));
        spriteDatabase.put(UnitName.LUKCHIN, loadSpriteSheet("/images/sprites/Lukchin.png"));
        spriteDatabase.put(UnitName.PLAYER, loadSpriteSheet("/images/sprites/Player.png"));
        spriteDatabase.put(UnitName.RED_EYES, loadSpriteSheet("/images/sprites/Red_Eyes.png"));
        spriteDatabase.put(UnitName.SON_AND_DAD, loadSpriteSheet("/images/sprites/Son_And_Dad.png"));
        spriteDatabase.put(UnitName.TILLY_THE_BIRD, loadSpriteSheet("/images/sprites/Tilly_The_Bird.png"));
        spriteDatabase.put(UnitName.VISION, loadSpriteSheet("/images/sprites/Vision.png"));
    }

    private void loadAllCards() {
        cardDatabase.put(CardName.CELESTIAL_SINGULARITY, loadImage("/images/cards/Celestial_Singularity.png"));
        cardDatabase.put(CardName.ECHOING_STRIKE, loadImage("/images/cards/Echoing_Strike.png"));
        cardDatabase.put(CardName.ETERNAL_SOUL_REBIRTH, loadImage("/images/cards/Eternal_Soul_Rebirth.png"));
        cardDatabase.put(CardName.ETHEREAL_RESTORATION, loadImage("/images/cards/Ethereal_Restoration.png"));
        cardDatabase.put(CardName.EVENT_HORIZON, loadImage("/images/cards/Event_Horizon.png"));
        cardDatabase.put(CardName.HARMONIC_RESONANCE, loadImage("/images/cards/Harmonic_Resonance.png"));
        cardDatabase.put(CardName.REMNANT_HIT, loadImage("/images/cards/Remnant_Hit.png"));
        cardDatabase.put(CardName.SOUL_AEGIS, loadImage("/images/cards/Soul_Aegis.png"));
        cardDatabase.put(CardName.SOUL_FLICKER, loadImage("/images/cards/Soul_Flicker.png"));
        cardDatabase.put(CardName.SOUL_RESONANCE, loadImage("/images/cards/Soul_Resonance.png"));
        cardDatabase.put(CardName.SOVEREIGNS_OVERDRIVE, loadImage("/images/cards/Sovereigns_Overdrive.png"));
        cardDatabase.put(CardName.SPECTRAL_VEIL, loadImage("/images/cards/Spectral_Veil.png"));
        cardDatabase.put(CardName.VOID_DRAGON, loadImage("/images/cards/Void_Dragon.png"));
    }

    private void loadAllSounds() {
        soundDatabase.put(SoundName.BGM, loadSound("/sound/BGM.wav"));
        soundDatabase.put(SoundName.SELECT, loadSound("/sound/Select.wav"));
        soundDatabase.put(SoundName.TAKE_DAMAGE, loadSound("/sound/Take_Damage.wav"));
    }

    private void loadIcon() {
        URL iconUrl = getClass().getResource("/logo.png");
        if (iconUrl != null) {
            ImageIcon imageIcon = new ImageIcon(iconUrl);
            icon = imageIcon.getImage();
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage[][] loadSpriteSheet(String path) {
        try {
            BufferedImage sheet = loadImage(path);
            if (sheet == null) return null;
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
            System.err.println("Error loading sprite sheet: " + path);
            return null;
        }
    }

    private Clip loadSound(String path) {
        try {
            URL soundUrl = getClass().getResource(path);
            if (soundUrl == null) {
                System.err.println("Sound file not found: " + path);
                return null;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + path);
            e.printStackTrace();
            return null;
        }
    }

    private Font loadFont() {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/fonts/Jersey10-Regular.ttf")));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            return font;
        } catch (Exception e) {
            System.err.println("Could not load font, using Arial.");
            return new Font("Arial", Font.PLAIN, 12);
        }
    }

    public BufferedImage getSprite(UnitName key, int row, int index) {
        return spriteDatabase.get(key)[row][index];
    }

    public BufferedImage getCard(CardName key) {
        return cardDatabase.get(key);
    }

    public Clip getSound(SoundName key) {
        return soundDatabase.get(key);
    }

    public Image getIcon() {
        return icon;
    }

    public Font getGameFont() {
        return gameFont;
    }
}
