package com.Expedition67.core;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {
    public void playBGM(){
        try {
            URL bgmUrl = SoundManager.class.getResource("/song.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bgmUrl);
            if (bgmUrl == null) {
                // Fallback to File if not found as a resource (e.g., in a development environment)
                File soundFile = new File("/song.mp3");
                if (soundFile.exists()) {
                    bgmUrl = soundFile.toURI().toURL();
                } else {
                    System.err.println("Sound file not found: " + "/song.mp3");
                    return;
                }
            }
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
