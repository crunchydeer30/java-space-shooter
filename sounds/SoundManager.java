package sounds;

import java.net.URL;
import java.util.HashMap;

public class SoundManager {

    URL soundLibrary[] = new URL[10];
    static HashMap<String, URL> MusicTracks = new HashMap<String, URL>();
    static HashMap<String, URL> SoundEffects = new HashMap<String, URL>();

    public SoundManager() {
        MusicTracks.put("afterburner", this.getClass().getResource("/assets/sounds/afterburner.wav"));
        MusicTracks.put("theme", this.getClass().getResource("/assets/sounds/theme.wav"));

        SoundEffects.put("shot", this.getClass().getResource("/assets/sounds/shot.wav"));
        SoundEffects.put("laser", this.getClass().getResource("/assets/sounds/laser.wav"));
    }

    public MusicPlayer createMusicPlayer(String name) {
        return new MusicPlayer(name);
    }

    public SoundEffectPlayer createSoundEffect(String name) {
        return new SoundEffectPlayer();
    }
}
