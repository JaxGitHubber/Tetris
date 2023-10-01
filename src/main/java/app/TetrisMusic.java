package app;

import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;

public class TetrisMusic implements Runnable {
    public void run() {
        while(true) {
            try {
                AdvancedPlayer exPlay = new AdvancedPlayer(new FileInputStream("/Users/maxim/IdeaProjects/Tetris/src/main/resources/1-01 Title.mp3"), new JavaSoundAudioDevice());
                exPlay.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}