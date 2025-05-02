package game.map;

import game.Main;
import processing.core.PImage;

public class Gate {
    public final Room room;
    public boolean isOpen;
    public boolean startOpening;

    public final int x = 575;
    public int y;
    public final int width = 130;
    public final int height = 150;
    public PImage img;

    public Gate(Room room) {
        this.room = room;
        this.isOpen = false;
        this.y = 410;
        this.img = Main.gateImg;
    }

    public boolean isInside(int x, int y, int w, int h) {
        return x < this.x + width && x + w > this.x && y < this.y + height && y + h > this.y;
    }

    public void setOpen() {
        startOpening = true;
    }

    public void onContact() {

    }
}
