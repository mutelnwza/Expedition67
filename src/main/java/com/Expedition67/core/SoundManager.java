package com.Expedition67.core;

import com.Expedition67.storage.AssetManager;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Manages the playback of sound effects and background music.
 */
public class SoundManager {

    private static SoundManager instance;

    private SoundManager() {
    }

    /**
     * Gets the single instance of the SoundManager.
     *
     * @return The single instance of SoundManager.
     */
    public static SoundManager Instance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void playSound(Clip clip, float volume, boolean loop) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        }
    }

    /**
     * Plays the background music on a continuous loop.
     */
    public void playBGM() {
        playSound(AssetManager.Instance().getSound(AssetManager.SoundName.BGM), -10.0f, true);
    }

    /**
     * Plays the select sound effect once.
     */
    public void playSelectSound() {
        playSound(AssetManager.Instance().getSound(AssetManager.SoundName.SELECT), -10.0f, false);
    }

    /**
     * Plays the take damage sound effect once.
     */
    public void playTakeDamageSound() {
        playSound(AssetManager.Instance().getSound(AssetManager.SoundName.TAKE_DAMAGE), -18.0f, false);
    }
}
