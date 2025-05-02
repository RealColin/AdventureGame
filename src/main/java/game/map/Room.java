package game.map;

public class Room {
    private Wall[] walls;
    public final int color;
    public Gate gate;

    public Room(Wall[] walls, int color) {
        this.walls = walls;
        this.color = color;
    }

    public void updateWalls(Wall[] walls) {
        if (this.walls == null)
            this.walls = walls;
    }

    public Wall[] walls() {
        return this.walls;
    }
}

