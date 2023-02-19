import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sounds {

        // creating a file separator that allows a computer to find a specific
        // file by matching them to the names of the clips within the file folder
        private String soundsFolder = "sounds" + File.separator;

        // setting the location of the needed audio files (WAV files work, MP3s do not)
        private String capturePath = soundsFolder + "capture.wav";
        private String castlePath = soundsFolder + "castle.wav";
        private String checkPath = soundsFolder + "check.wav";
        private String gameEndPath = soundsFolder + "game_end.wav";
        private String movePath = soundsFolder + "move.wav";

        // initializing empty Clip variables for later assignment
        private Clip captureSound, castleSound, checkSound, gameEndSound, moveSound;

        // assigns the Clip variables with audio clips; will play their respective sound
        public Sounds() {
            try {
                // getting the clip or preview of a sound; preloaded so it can be used in game
                captureSound = AudioSystem.getClip();
                castleSound = AudioSystem.getClip();
                checkSound = AudioSystem.getClip();
                gameEndSound = AudioSystem.getClip();
                moveSound = AudioSystem.getClip();

                // assigning the Clip variable to the specific path of their audio, getAbsoluteFile matches the file names above
                captureSound.open(AudioSystem.getAudioInputStream(new File(capturePath).getAbsoluteFile()));
                castleSound.open(AudioSystem.getAudioInputStream(new File(castlePath).getAbsoluteFile()));
                checkSound.open(AudioSystem.getAudioInputStream(new File(checkPath).getAbsoluteFile()));
                gameEndSound.open(AudioSystem.getAudioInputStream(new File(gameEndPath).getAbsoluteFile()));
                moveSound.open(AudioSystem.getAudioInputStream(new File(movePath).getAbsoluteFile()));

            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                // catching exceptions
                e.printStackTrace();
            }
        }

        // methods play the sounds created above, setFramePosition allows the sounds to reset

        public void playCaptureSound() {
            captureSound.setFramePosition(0);
            captureSound.start();
        }

        public void playCastleSound() {
            castleSound.setFramePosition(0);
            castleSound.start();
        }

        public void playCheckSound() {
            checkSound.setFramePosition(0);
            checkSound.start();
        }

        public void playGameEndSound() {
            gameEndSound.setFramePosition(0);
            gameEndSound.start();
        }

        public void playMoveSound() {
            moveSound.setFramePosition(0);
            moveSound.start();
        }

        // this method specifically loops background music
        public void playMusicMain() {
//            musicMainSound.setFramePosition(0);
//            musicMainSound.loop(Clip.LOOP_CONTINUOUSLY);
        }

        // this method stops the music entirely
        public void stopMusicMain() {
//            musicMainSound.stop();
        }
}
