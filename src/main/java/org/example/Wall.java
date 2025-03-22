package org.example;

public record Wall(int xPos, int yPos, int length, int width, boolean nextRoom) {

    // Check if a given hitbox is inside the wall (usually for collision purposes
    public boolean isInside(int x, int y, int l, int w) {


        return false;
    }
}
