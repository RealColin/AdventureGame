package game.item;

import game.entity.Dragon;
import game.map.Castle;
import game.map.Gate;
import game.map.Room;
import processing.core.PImage;

public class Chalice implements Item {
    public int x;
    public int y;
    public PImage img;

    public Chalice(PImage img) {
        this.img = img;
    }

    @Override
    public void onDragonInteract(Dragon dragon) {

    }

    @Override
    public void onGateInteract(Gate gate) {

    }

    @Override
    public void onBroughtToCastle(Castle castle) {

    }

    @Override
    public void drop(Room room) {

    }

    @Override
    public void pickup(Room room, int x, int y) {

    }

    @Override
    public void move(int dx, int dy) {

    }

    @Override
    public boolean isInside(int x, int y, int w, int h) {
        return x < this.x + img.width && x + w > this.x && y < this.y + img.height && y + h > this.y;
    }

    @Override
    public PImage getImage() {
        return img;
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }
}
