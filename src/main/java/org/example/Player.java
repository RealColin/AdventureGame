package org.example;

public class Player {
    public int x;
    public int y;
    public int color;

    public Player(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void move(int amountX, int amountY) {
        this.x += amountX;
        this.y += amountY;
    }
}
