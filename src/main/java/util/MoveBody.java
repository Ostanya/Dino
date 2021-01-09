package util;

import java.awt.image.BufferedImage;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MoveBody {
    private final List<BufferedImage> frames;
    private int frameIndex = 0;
    private final int varTime;
    private long prevTime;


    public MoveBody(int varTime) {
        this.varTime = varTime;
        frames = new ArrayList<BufferedImage>();
    }

    public void update() {
        if(System.currentTimeMillis() - prevTime > varTime) {
            frameIndex++;
            if(frameIndex >= frames.size()) {
                frameIndex = 0;
            }
            prevTime = System.currentTimeMillis();
        }
    }

    public void addFrame(BufferedImage frame){
        frames.add(frame);
    }

    public BufferedImage getFrame() {
        if (frames.size() > 0) {
            return frames.get(frameIndex);
        }
        return null;
    }
}
