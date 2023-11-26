package game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundManager {

    URL soundLibrary[] = new URL[10];

    public SoundManager() {
        soundLibrary[0] = this.getClass().getResource("/sounds/theme.wav");
        soundLibrary[1] = this.getClass().getResource("/sounds/shot.wav");
        soundLibrary[2] = this.getClass().getResource("/sounds/explode.wav");
    }

    public void play(int i) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundLibrary[i]);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                            if (event.getType() == LineEvent.Type.STOP) {
                                clip.close();
                            }
                        };
                    });
                    audioInputStream.close();
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void loop(int i) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundLibrary[i]);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    audioInputStream.close();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
