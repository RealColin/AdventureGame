package game.map;

public class Room {
    public Room left;
    public Room right;
    public Room up;
    public Room down;
    public int color;

    public final Structure[] structures;

    public Room(Wall[] walls, int color) {
        this.structures = walls;
        this.color = color;
    }
}
