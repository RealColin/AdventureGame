package game.map;

import game.Main;
import processing.core.PImage;

public class Gate {
    public final Room room;
    public boolean isOpen;

    public final int x = 575;
    public final int y = 410;
    public final int width = 130;
    public int height;
    public PImage img;

    public Gate(Room room) {
        this.room = room;
        this.isOpen = false;
        this.height = 150;
        this.img = Main.gate;
    }

    public boolean isInside(int x, int y, int w, int h) {
        return x < this.x + width && x + w > this.x && y < this.y + height && y + h > this.y;
    }

    public void setOpen() {
        this.height = 20;
        isOpen = true;
    }

    public void onContact() {

    }
}
