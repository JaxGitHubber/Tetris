package app;

import javax.swing.*;

import tetrisgame.TetrisGame;

public class App {
    static int borderWidth = 400;
    static int borderHeight = 700;

    public static void main(String[] args) {
        playMusic();
        createGame();
    }

    private static void playMusic() {
        Thread threadMusic = new Thread(new TetrisMusic());
        threadMusic.setDaemon(true);
        threadMusic.start();
    }

    private static void createGame() {
        JFrame gameFrame = new JFrame();
        gameFrame.setTitle("Tetris");
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(borderWidth, borderHeight);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        TetrisGame tetrisGame = new TetrisGame(borderWidth, borderHeight);
        gameFrame.add(tetrisGame);
        gameFrame.pack();
        tetrisGame.requestFocus();
    }
}