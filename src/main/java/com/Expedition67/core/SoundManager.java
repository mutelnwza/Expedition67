package com.Expedition67.core;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

/**
 * Manages the playback of sound effects and background music.
 */
public class SoundManager {

    /**
     * Plays the background music on a continuous loop.
     */
    public void playBGM() {
        try {
            URL bgmUrl = SoundManager.class.getResource("/song.wav");

            if (bgmUrl == null) {
                System.err.println("Sound file not found in resources: /song.wav");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bgmUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
