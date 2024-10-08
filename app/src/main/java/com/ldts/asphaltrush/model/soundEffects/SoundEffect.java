package com.ldts.asphaltrush.model.soundEffects;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {
    private final String soundFileName;
    private Clip soundEffect;

    public SoundEffect(String soundFileName) {
            this.soundFileName = soundFileName;
            initializeSounds();
        }

    public void initializeSounds() {
        try {
            URL resource = SoundEffect.class.getResource("/audio/" + soundFileName);
            assert resource != null;

            AudioInputStream sound = AudioSystem.getAudioInputStream(resource);
            soundEffect = AudioSystem.getClip();
            soundEffect.open(sound);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        soundEffect.setFramePosition(0);
        soundEffect.start();
    }

    public void closeWhenSoundEnds() {
        Thread waitThread = new Thread(() -> {
            while (soundEffect != null && soundEffect.isRunning()) {
                try {
                    Thread.sleep(20); // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            soundEffect.close(); // Close the clip when it's done playing
        });

        waitThread.start();
        try {
            waitThread.join(); // Ensure that the main thread waits for waitThread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
