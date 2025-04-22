package game.map;

public class Room {
    public Room left;
    public Room right;
    public Room up;
    public Room down;

    public final Structure[] structures;

    public Room(Wall[] walls) {
        this.structures = walls;
    }
}
