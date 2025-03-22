package org.example;

public class Room {
    public Room left;
    public Room right;
    public Room up;
    public Room down;

    public final Wall[] walls;

    public Room(Wall[] walls) {
        this.walls = walls;
    }
}
