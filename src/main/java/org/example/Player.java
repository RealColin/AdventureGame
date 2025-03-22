package org.example;

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

    public void move(int amountX, int amountY) {
        this.x += amountX;
        this.y += amountY;
    }

    public void move(Direction dir) {
        int tx = x, ty = y;

        switch (dir) {
            case UP -> ty -= speed;
            case LEFT -> tx -= speed;
            case RIGHT -> tx += speed;
            case DOWN -> ty += speed;
        }

        for (var wall : currentRoom.walls) {
            boolean col = wall.isInside(tx, ty, size, size);

            // if col is true then i have to move tx or ty back in the direction it came from in the amount necessary to keep the player outside the wall

        }
    }
}
