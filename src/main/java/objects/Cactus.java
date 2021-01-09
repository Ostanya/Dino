package objects;

import util.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends BadBoys {
    private BufferedImage img;
    private int posX, posY;
    private Rectangle rect;
    private MainBody character;
    public boolean isScoreGot = false;

    public Cactus(MainBody character) {
        this.character = character;
        img = Resource.getResourceImage("data/cactus1.png");
        posX = 200;
        posY = 65;
        rect = new Rectangle();
    }

    public void update() {
        posX -= 2;
        rect.x = posX;
        rect.y = posY;
        rect.width  = img.getWidth();
        //    int right = rect.x + rect.y;
        rect.height = img.getHeight();
    }

    @Override
    public Rectangle getBound(){
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, posX, posY, null);
    }

    //when u use this u can use it as var
    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    @Override
    public boolean isGone() {
        return(posX + img.getWidth() < 0);
    }

    @Override
    public boolean gameOver() {
        return(character.getX() > posX);
    }

    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void setIsScoreGet(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }
}

