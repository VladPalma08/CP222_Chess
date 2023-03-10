import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sounds {

        // creating a file separator that allows a computer to find a specific
        // file by matching them to the names of the clips within the file folder
        private String soundsFolder = "sounds" + File.separator;

        // setting the location of the needed audio files (WAV files work, MP3s do not)
        private String capturePath = soundsFolder + "capture.wav";
        private String checkPath = soundsFolder + "check.wav";
        private String gameEndPath = soundsFolder + "game_end.wav";
        private String movePath = soundsFolder + "move.wav";
        private String mainPath = soundsFolder + "menu_music.wav";
        private String gamePath = soundsFolder + "game_music.wav";

        // initializing empty Clip variables for later assignment
        private Clip captureSound, castleSound, checkSound, gameEndSound, moveSound, mainMusicSound, gameMusicSound;

        // assigns the Clip variables with audio clips; will play their respective sound
        public Sounds() {
            try {
                // getting the clip or preview of a sound; preloaded so it can be used in game
                captureSound = AudioSystem.getClip();
                checkSound = AudioSystem.getClip();
                gameEndSound = AudioSystem.getClip();
                moveSound = AudioSystem.getClip();
                mainMusicSound = AudioSystem.getClip();
                gameMusicSound = AudioSystem.getClip();

                // assigning the Clip variable to the specific path of their audio, getAbsoluteFile matches the file names above
                captureSound.open(AudioSystem.getAudioInputStream(new File(capturePath).getAbsoluteFile()));
                checkSound.open(AudioSystem.getAudioInputStream(new File(checkPath).getAbsoluteFile()));
                gameEndSound.open(AudioSystem.getAudioInputStream(new File(gameEndPath).getAbsoluteFile()));
                moveSound.open(AudioSystem.getAudioInputStream(new File(movePath).getAbsoluteFile()));
                mainMusicSound.open(AudioSystem.getAudioInputStream(new File(mainPath).getAbsoluteFile()));
                gameMusicSound.open(AudioSystem.getAudioInputStream(new File(gamePath).getAbsoluteFile()));

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
        public void playMenuMusic() {
            mainMusicSound.setFramePosition(0);
            mainMusicSound.loop(Clip.LOOP_CONTINUOUSLY);
        }

        public void playGameMusic() {
            gameMusicSound.setFramePosition(0);
            gameMusicSound.loop(Clip.LOOP_CONTINUOUSLY);
        }

        // these methods stop these clips entirely
        public void stopGameMusic() {
            gameMusicSound.stop();
        }
        public void stopMenuMusic() {
            mainMusicSound.stop();
        }
}

//        castleSound.open(AudioSystem.getAudioInputStream(new File(castlePath).getAbsoluteFile()));
//        private String castlePath = soundsFolder + "castle.wav";
//        castleSound = AudioSystem.getClip();

//        removing castle implementation:
//        public void playCastleSound() {
//            castleSound.setFramePosition(0);
//            castleSound.start();
//        }
