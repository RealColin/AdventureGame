package game.item;

import game.entity.Dragon;
import game.entity.Player;
import game.map.Castle;
import game.map.Gate;
import game.map.Room;
import processing.core.PImage;

public class Sword implements Item{
    public final PImage img;

    public Sword(PImage img) {
        this.img = img;
    }

    @Override
    public void onPlayerInteract(Player player) {
        // TODO pickup logic
    }

    @Override
    public void onDragonInteract(Dragon dragon) {
        // TODO logic for killing dragon
    }

    @Override
    public void onGateInteract(Gate gate) {
        // Nothing gets done when Sword interacts with the Gate
    }

    @Override
    public void onBroughtToCastle(Castle castle) {
        // Nothing gets done when Sword is brought into Castle
    }

    @Override
    public void drop(Room room) {

    }

    @Override
    public void pickup(Room room, int x, int y) {

    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public boolean isInside(int x, int y, int w, int h) {
        return false;
    }

    @Override
    public PImage getImage() {
        return img;
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 0;
    }
}
