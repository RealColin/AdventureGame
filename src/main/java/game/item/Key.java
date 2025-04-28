package game.item;

import game.entity.Dragon;
import game.entity.Player;
import game.map.Castle;
import game.map.Gate;
import processing.core.PImage;

public class Key implements Item {
    public int x;
    public int y;
    public PImage img;
    public Castle castle;

    // TODO determine how this will be constructed
    // I'm thinking just a regular constructor and then setting x, y when the item is initially spawned on the map

    @Override
    public void onPlayerInteract(Player player) {
        // TODO pickup logic
    }

    @Override
    public void onDragonInteract(Dragon dragon) {
        // Nothing gets done when a dragon interacts with a key
    }

    @Override
    public void onGateInteract(Gate gate) {
        // TODO gate opening logic
    }

    @Override
    public void onBroughtToCastle(Castle castle) {
        // Nothing gets done when Key is brought into Castle
    }
}
