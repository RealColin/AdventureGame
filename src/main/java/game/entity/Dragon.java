package game.entity;
import processing.core.*;

public class Dragon {
    public int x;
    public int y;
    public PImage img;
    public final int hitboxWidth;
    public final int hitboxHeight;
    private boolean isAlive = true;

    public Dragon(int x, int y, PImage img) {
        this.x = x;
        this.y = y;
        this.img = img;
        hitboxWidth = 55;
        hitboxHeight = 135;
    }

    public void kill() {
        isAlive = false;
    }

    public void tick() {

    }
}
