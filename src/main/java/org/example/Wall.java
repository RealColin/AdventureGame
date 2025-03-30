package org.example;

public record Wall(int xPos, int yPos, int width, int height, int color) {

    // Check if hitbox is inside this wall
    public boolean isInside(int x, int y, int w, int h) {
        if (x < xPos+width && x+w > xPos && y < yPos+height && y+h > yPos)
            return true;

        return false;
    }
}
