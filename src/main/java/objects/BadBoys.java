package objects;

import java.awt.*;

public abstract class BadBoys {
        public abstract Rectangle getBound();

        public abstract void draw(Graphics g);

        public abstract void update();

        public abstract boolean isGone();

        public abstract boolean gameOver();

        public abstract boolean isScoreGot();

        public abstract void setIsScoreGet(boolean isScoreGot);
}
