package game;
import game.map.*;
import processing.core.PApplet;

import java.util.HashSet;

public class Main extends PApplet {
    private Player player;
    private Dragon greenDragon;
    private final HashSet<Character> keysPressed = new HashSet<>();
    private final int TRANSPARENT = color(255, 255, 255, 0);

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        windowResizable(true);

        RoomBuilder builder = new RoomBuilder();
        Room main = builder.getStartRoom();

        player = new Player(50, 50, 40, 7, color(255, 255, 0), main);
        greenDragon = new Dragon(700, 300, loadImage("green_dragon.png"));
    }

    @Override
    public void draw() {
        handleInput();

        // then draw stuff
        background(170, 170, 170);
        drawRoom();

        // player and player hitbox
        drawPlayer();

        // dragon and dragon hitbox
//        image(greenDragon.img, greenDragon.x, greenDragon.y, greenDragon.img.width * 0.35f, greenDragon.img.height * 0.35f);
//        stroke(255, 255, 255);
//        strokeWeight(2);
//        noFill();
//        rect(greenDragon.x, greenDragon.y, greenDragon.hitboxWidth, greenDragon.hitboxHeight);
    }

    private void drawRoom() {
        var room = player.currentRoom;

        for (var wall : room.walls()) {
            if (wall.nextRoom() != null)
                continue; // don't draw the wall if it's actually a path

            fill(room.color);
            noStroke();
            rect(wall.xPos(), wall.yPos(), wall.width(), wall.height());
        }
    }

    private void drawPlayer() {
        fill(player.color);
        noStroke();
        rect(player.x, player.y, 40, 40);
    }

    @Override
    public void keyPressed() {
        keysPressed.add(key);
    }

    @Override
    public void keyReleased() {
        keysPressed.remove(key);
    }

    private void handleInput() {
        for (var key : keysPressed) {
            switch (key) {
                case 'w' -> player.move(Direction.UP);
                case 'a' -> player.move(Direction.LEFT);
                case 's' -> player.move(Direction.DOWN);
                case 'd' -> player.move(Direction.RIGHT);
            }
        }
    }
}