package sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import game.GameScreen;

public class MusicPlayer {
    Clip clip;

    public MusicPlayer(String name) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SoundManager.MusicTracks.get(name));
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
