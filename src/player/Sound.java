package player;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {

    private String name;
    private Clip line;
    private boolean begin = true;

    public Sound(String name) {
        this.name = name;
    }

    public void play_or_stop() {
        begin = !begin;
    }

    public void play() {
        line.start();
    }

    public void close() {
        line.close();
    }

    public void stop() {
        line.stop();
    }

    public void start() {
        File f = new File(name);
        try {
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(f);
            AudioFormat af = aff.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, af);
            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("line is not supported");
                System.exit(0);
            }
            line = (Clip) AudioSystem.getLine(info);
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            line.open(stream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.err.println("Some problems with opening");
        }
    }

    public String getName() {
        return name;
    }

    public Clip getLine() {
        return line;
    }
}

