package sounds;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {

    URL soundLibrary[] = new URL[10];
    static HashMap<String, URL> MusicTracks = new HashMap<String, URL>();
    static HashMap<String, URL> SoundEffects = new HashMap<String, URL>();

    public SoundManager() {
        MusicTracks.put("afterburner", this.getClass().getResource("/assets/sounds/afterburner.wav"));
        MusicTracks.put("theme", this.getClass().getResource("/assets/sounds/theme.wav"));
        MusicTracks.put("menu", this.getClass().getResource("/assets/sounds/menu.wav"));
        MusicTracks.put("laser", this.getClass().getResource("/assets/sounds/laser.wav"));

        SoundEffects.put("shot", this.getClass().getResource("/assets/sounds/shot.wav"));
        SoundEffects.put("laser", this.getClass().getResource("/assets/sounds/laser.wav"));
        SoundEffects.put("beam", this.getClass().getResource("/assets/sounds/beam.wav"));
    }

    public void play(String name) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(MusicTracks.get(name));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });
            audioIn.close();
            clip.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println(e);
        }
    }

    public MusicPlayer createMusicPlayer(String name) {
        return new MusicPlayer(name);
    }

    public SoundEffectPlayer createSoundEffect(String name) {
        return new SoundEffectPlayer();
    }
}
