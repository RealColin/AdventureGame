package game.map;

import game.item.Item;

import java.util.ArrayList;

public class Room {
    private Wall[] walls;
    public ArrayList<Item> items;
    public final int color;
    public Gate gate;
    public boolean castle = false;

    public Room(Wall[] walls, int color) {
        this.walls = walls;
        this.color = color;
        items = new ArrayList<>();
    }

    public void updateWalls(Wall[] walls) {
        if (this.walls == null)
            this.walls = walls;
    }

    public Wall[] walls() {
        return this.walls;
    }
}

