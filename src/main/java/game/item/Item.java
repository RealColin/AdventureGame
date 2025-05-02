package game.item;

import game.entity.Dragon;
import game.entity.Player;
import game.map.Castle;
import game.map.Gate;
import game.map.Room;
import processing.core.PImage;

public interface Item {
    void onPlayerInteract(Player player);
    void onDragonInteract(Dragon dragon);
    void onGateInteract(Gate gate);
    void onBroughtToCastle(Castle castle);

    void drop(Room room);
    void pickup(Room room, int x, int y);

    void move(int dx, int dy);
    boolean isInside(int x, int y, int w, int h);

    PImage getImage();
    int x();
    int y();
}
