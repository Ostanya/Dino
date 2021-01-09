package objects;

import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Clouds {
    private BufferedImage cloudImage;
    private List<Cloud> clouds;
    public Clouds() {
        cloudImage = Resource.getResourceImage("data/cloud.png");
        clouds = new ArrayList<Cloud>();

        Cloud cloud1 = new Cloud();
        cloud1.posX = 100;
        cloud1.posY = 50;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 200;
        cloud1.posY = 30;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 300;
        cloud1.posY = 40;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 450;
        cloud1.posY = 15;
        clouds.add(cloud1);

        cloud1 = new Cloud();
        cloud1.posX = 600;
        cloud1.posY = 25;
        clouds.add(cloud1);
    }

    public void update(){
        for(Cloud cloud : clouds) {
            cloud.posX --;
        }
        Cloud oneCloud = clouds.get(0);
        if(oneCloud.posX + cloudImage.getWidth() < 0) {
            oneCloud.posX = 600;
            clouds.remove(oneCloud);
            clouds.add(oneCloud);

        }
    }

    public void draw(Graphics g) {
        for(Cloud cloud : clouds) {
            g.drawImage(cloudImage, (int) cloud.posX, (int) cloud.posY, null);
        }
    }

    private class Cloud  {
        float posX;
        float posY;
    }
}
