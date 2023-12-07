package sounds;

import java.net.URL;
public class SoundManager {

    URL soundLibrary[] = new URL[10];

    public SoundManager() {
        soundLibrary[0] = this.getClass().getResource("/assets/sounds/theme.wav");
        soundLibrary[1] = this.getClass().getResource("/assets/sounds/shot.wav");
        soundLibrary[2] = this.getClass().getResource("/assets/sounds/explode.wav");
        soundLibrary[3] = this.getClass().getResource("/assets/sounds/afterburner.wav");
        soundLibrary[4] = this.getClass().getResource("/assets/sounds/laser.wav");
        soundLibrary[5] = this.getClass().getResource("/assets/sounds/laser_repeat.wav");
    }
}
