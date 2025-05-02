package game.map;

import game.Main;
import processing.core.PImage;

public class Gate {
    public final Room room;
    public boolean isOpen;

    public int x = 575;
    public int y = 410;
    public PImage img;

    public Gate(Room room) {
        this.room = room;
        this.isOpen = false;
        this.img = Main.gate;
    }

    public void onContact() {

    }
}
