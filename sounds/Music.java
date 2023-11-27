package sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.GameScreen;

public class Music {
    Clip clip;

    public Music(int i) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameScreen.soundManager.soundLibrary[i]);
                    clip = AudioSystem.getClip();
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

    public void stop() {
        clip.stop();
    }
}
