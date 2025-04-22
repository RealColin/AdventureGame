package game.map;

public class Room {
    public Room left;
    public Room right;
    public Room up;
    public Room down;
    public int color;

    public final Wall[] walls;

    public Room(Wall[] walls, int color) {
        this.walls = walls;
        this.color = color;
    }
}
