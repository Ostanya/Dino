package objects;

import util.MainBody;
import util.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ManageBadBoys {
    private List<BadBoys> badBois;
    private Random random;
    private BufferedImage imageCactus1, imageCactus2;
    private MainBody character;
    private GameScreen scoreScreen;

    public ManageBadBoys(MainBody character, GameScreen scoreScreen) {
        this.scoreScreen = scoreScreen;
        this.character = character;
        badBois = new ArrayList<BadBoys>();
        imageCactus1 = Resource.getResourceImage("data/cactus1.png");
        imageCactus2 = Resource.getResourceImage("data/cactus2.png");
        random = new Random();
        badBois.add(getRandomCactus());
    }


    public void update() {
        for(BadBoys e : badBois) {
            e.update();
            if(e.gameOver() && !e.isScoreGot()) {
                scoreScreen.getScore(20);
                e.setIsScoreGet(true);
            }
            if(e.getBound().intersects(character.getBound())) {
                character.setAlive(false);
            }
        }
        BadBoys oneBadBoy = badBois.get(0);
        if(oneBadBoy.isGone()) {
            badBois.remove(oneBadBoy);
            badBois.add(getRandomCactus());
        }
    }

    public void draw(Graphics g) {
        for(BadBoys e : badBois) {
            e.draw(g);
        }
    }

    public void reset() {
        badBois.clear();
        badBois.add(getRandomCactus());
    }

    private Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus(character);
        cactus.setX(600);
        if(random.nextBoolean()) {
            cactus.setImg(imageCactus1);
        }else {
            cactus.setImg(imageCactus2);
        }
        return cactus;
    }
}
