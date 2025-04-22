package org.example;

public record Wall(int xPos, int yPos, int width, int height, int color) {
    // Check if the hitbox is inside this wall
    public boolean isInside(int x, int y, int w, int h) {
        return x < xPos + width && x + w > xPos && y < yPos + height && y + h > yPos;
    }
}
