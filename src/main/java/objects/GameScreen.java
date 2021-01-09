package objects;

import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameScreen extends JPanel implements Runnable, KeyListener {
    public static final int GAME_FIRST_START = 0;
    public static final int GAME_PLAY_START = 1;
    public static final int GAME_OVER_START = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUNDY = 110;

    private MainBody character;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private ManageBadBoys manageBadBoys;
    private int gameStart = GAME_FIRST_START;
    private BufferedImage gameOverImg;
    private BufferedImage gameStartImg;
    private int score;

    public GameScreen() {
        setBackground(Color.lightGray);
        thread = new Thread(this);
        character = new MainBody();
        character.setX(50);
        character.setY(70);
        land = new Land(this);
        clouds = new Clouds();
        manageBadBoys = new ManageBadBoys(character, this);
        gameOverImg = Resource.getResourceImage("data/gameover.png");
        gameStartImg = Resource.getResourceImage("data/gamestart.png");
    }

    public void startGame() {
        thread.start();
    };

    @Override
    public void run() {
        while(true) {
            try{
                update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        switch(gameStart) {
            case GAME_PLAY_START:
                character.update();
                land.update();
                clouds.update();
                manageBadBoys.update();
                if(!character.getAlive()) {
                    gameStart = GAME_OVER_START;
                }
                break;
        }
    }

    public void getScore(int score) {
        this.score += score;
    }

    @Override
    public void paint(Graphics g) {
        //    super.paint(g);
        //    g.setColor(Color.GREEN);
        //    g.drawLine(0, (int)GROUNDY, getWidth(), (int) GROUNDY);

        switch(gameStart) {
            case GAME_FIRST_START:
                character.draw(g);
                g.drawImage(gameStartImg, 200, 50, null);
                break;
            case GAME_PLAY_START:
                clouds.draw(g);
                land.draw(g);
                character.draw(g);
                manageBadBoys.draw(g);
                g.drawString("YOUR SCORE IS: " + String.valueOf(score), 400, 20); //convert score into string
                break;
            case GAME_OVER_START:
                clouds.draw(g);
                land.draw(g);
                character.draw(g);
                manageBadBoys.draw(g);
                g.drawImage(gameOverImg, 200, 50, null);
                break;
        }
    }

    private void getReset() {
        character.setAlive(true);
        character.setX(50);
        character.setY(70);
        manageBadBoys.reset();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(gameStart == GAME_FIRST_START) {
                    gameStart = GAME_PLAY_START;
                }else if (gameStart == GAME_PLAY_START) {
                    character.jump();
                }
                else if(gameStart == GAME_OVER_START) {
                    getReset();
                    gameStart = GAME_PLAY_START;
                }
                break;
        }
    }
}
