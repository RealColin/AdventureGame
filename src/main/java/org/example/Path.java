package org.example;

public record Path(int xPos, int yPos, int width, int height, Room next) {
    // Check if the hitbox is inside this path
    public boolean isInside(int x, int y, int w, int h) {
        return x < xPos + width && x + w > xPos && y < yPos + height && y + h > yPos;
    }
}