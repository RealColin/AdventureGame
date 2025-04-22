package game;

import game.map.Direction;
import game.map.Room;

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

    // TODO definitely make this cleaner
    public void move(Direction dir) {
        int tx = x, ty = y;

        switch (dir) {
            case UP -> ty -= speed;
            case LEFT -> tx -= speed;
            case RIGHT -> tx += speed;
            case DOWN -> ty += speed;
        }

        for (var wall : currentRoom.walls()) {
            boolean col = wall.isInside(tx, ty, size, size);

            if (!col) continue;

            if (wall.nextRoom() != null) {
                this.currentRoom = wall.nextRoom();
                switch (dir) {
                    case UP -> ty = 600;
                    case DOWN -> ty = 80;
                    case LEFT -> tx = 1200;
                    case RIGHT -> tx = 80;
                }
                continue;
            }

            switch (dir) {
                case UP -> ty = wall.yPos() + wall.height();
                case DOWN -> ty = wall.yPos() - size;
                case LEFT -> tx = wall.xPos() + wall.width();
                case RIGHT -> tx = wall.xPos() - size;
            }
        }

        x = tx;
        y = ty;
    }
}
