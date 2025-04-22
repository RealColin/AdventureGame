package game.map;

import game.Main;
import processing.core.PImage;

public class Gate {
    public final Castle castle;
    public boolean isOpen;

    public int x = 0;
    public int y = 0;
    public PImage img;

    public Gate(Castle castle) {
        this.castle = castle;
        this.isOpen = false;
        this.img = Main.gate;
    }
}
