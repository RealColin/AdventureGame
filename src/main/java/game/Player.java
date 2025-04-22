package game;

import game.map.Direction;
import game.map.Room;
import game.map.Wall;

public class Player {
    public int x;
    public int y;
    public int size;
    public int speed;
    public int color;
    public Room currentRoom;

    public Player(int x, int y, int size, int speed, int color, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
        this.currentRoom = currentRoom;
    }

    // TODO maybe make this a bit cleaner?
    public void move(Direction dir) {
        int tx = x, ty = y;

        switch (dir) {
            case UP -> ty -= speed;
            case LEFT -> tx -= speed;
            case RIGHT -> tx += speed;
            case DOWN -> ty += speed;
        }

        for (var struct : currentRoom.structures) {
            boolean col = struct.isInside(tx, ty, size, size);

            if (!col) continue;

            if (struct instanceof Wall wall) {
                switch (dir) {
                    case UP -> ty = wall.yPos() + wall.height();
                    case DOWN -> ty = wall.yPos() - size;
                    case LEFT -> tx = wall.xPos() + wall.width();
                    case RIGHT -> tx = wall.xPos() - size;
                }
            } else {
                // TODO it's a path, move to the next room
            }
        }

        x = tx;
        y = ty;
    }
}
