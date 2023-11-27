package sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import game.GameScreen;

public class SoundEffect {
    Clip clip;

    public void play(int i) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameScreen.soundManager.soundLibrary[i]);
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
}
