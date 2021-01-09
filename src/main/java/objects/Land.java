package objects;

import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static objects.GameScreen.GROUNDY;


public class Land {
    private List<ImageLand> listImage;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;

    public Land(GameScreen game) {
        random = new Random();
        imageLand1 = Resource.getResourceImage("data/land.png");
        imageLand2 = Resource.getResourceImage("data/land2.png");
        imageLand3 = Resource.getResourceImage("data/land3.png");
        listImage = new ArrayList<ImageLand>();
        int numberOfLands = 600 / imageLand1.getWidth() + 2;

        for(int i = 0; i < numberOfLands; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int) (i * imageLand1.getWidth());
            imageLand.image = getRandomLand();
            listImage.add(imageLand);
        }
    }

    public void update() {
        for(ImageLand imageLand : listImage) {
            imageLand.posX -= 4;
        }
        ImageLand firstLand = listImage.get(0);
        if(listImage.get(0).posX + imageLand1.getWidth() <0) {
            firstLand.posX = listImage.get(listImage.size() - 1).posX + imageLand1.getWidth();
            listImage.add(firstLand);
            listImage.remove(0);
        }
    }

    public void draw(Graphics g) {
        for(ImageLand imageLand: listImage) {
            g.drawImage(imageLand.image, imageLand.posX, (int) GROUNDY - 30, null);
        }
    }

    private BufferedImage getRandomLand () {
        int i = random.nextInt(7);
        switch(i) {
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand {
        int posX;
        BufferedImage image;
    }
}
