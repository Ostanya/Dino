package util;

import objects.GameScreen;

import javax.swing.*;

public class BackGround extends JFrame {
    public static final int SCREEN_WIDTH = 600;
    private GameScreen gameScreen;

    public BackGround() {
        super("Dino");
        //   this.setBounds(500, 500, 600, 300);
        //  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setSize(SCREEN_WIDTH,175);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        addKeyListener(gameScreen);
        add(gameScreen);
    }

    public void startGame() {
        gameScreen.startGame();
    }
}
