package game;
import game.entity.Dragon;
import game.entity.Player;
import game.map.*;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashSet;

public class Main extends PApplet {

    public static boolean WON = false;
    public static boolean LOST = false;

    private Player player;
    private Dragon greenDragon;
    private Dragon yellowDragon;
    private final HashSet<Character> keysPressed = new HashSet<>();
    private final int TRANSPARENT = color(255, 255, 255, 0);

    public static PImage gateImg;
    public static PImage swordImg;
    public static PImage yellowKeyImg;
    public static PImage blackKeyImg;
    public static PImage chaliceImg;

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

        // have to do this before RoomBuilder BECAUSE a Gate gets constructed in RoomBuilder and needs gate to be initialized
        gateImg = loadImage("gate.png");
        swordImg = loadImage("sword.png");
        yellowKeyImg = loadImage("yellowKey.png");
        blackKeyImg = loadImage("blackKey.png");
        chaliceImg = loadImage("chalice.png");

        RoomBuilder builder = new RoomBuilder();
        Room main = builder.getStartRoom();

        player = new Player(50, 50, 40, 7, color(255, 255, 0), main);
        greenDragon = new Dragon(700, 500, 5, 3, loadImage("green_dragon.png"), loadImage("green_dragon_dead.png"), player);
        yellowDragon = new Dragon(300, 500, 3, 2, loadImage("yellow_dragon.png"), loadImage("yellow_dragon_dead.png"), player);

        builder.putDragon(greenDragon, 10);
        builder.putDragon(yellowDragon, 1);
    }

    @Override
    public void draw() {
        if (WON) {
            fill(255, 0, 0);
            textSize(64);
            textAlign(CENTER, CENTER);
            text("You win!", 640, 360);
            return;
        }

        if (LOST) {
            fill(255, 0, 0);
            textSize(64);
            textAlign(CENTER, CENTER);
            text("You died.", 640, 360);
            return;
        }

        handleInput();
        greenDragon.tick();
        yellowDragon.tick();

        // then draw stuff
        background(170, 170, 170);

        // draw items before room


        // draw room
        drawRoom();

        // player and player hitbox
        drawPlayer();

        drawDragon(greenDragon);
        drawDragon(yellowDragon);

        // dragon and dragon hitbox
//        image(greenDragon.img, greenDragon.x, greenDragon.y, greenDragon.img.width * 0.35f, greenDragon.img.height * 0.35f);
//        stroke(255, 255, 255);
//        strokeWeight(2);
//        noFill();
//        rect(greenDragon.x, greenDragon.y, greenDragon.hitboxWidth, greenDragon.hitboxHeight);
    }

    private void drawDragon(Dragon dragon) {
        if (player.currentRoom == dragon.currentRoom) {
            if (dragon.isAlive) {
                image(dragon.img, dragon.x, dragon.y, dragon.img.width * 0.35f, dragon.img.height * 0.35f);
            } else {
                image(dragon.dead, dragon.x, dragon.y, dragon.dead.width * 0.35f, dragon.dead.height * 0.35f);
            }

        }
    }

    private void drawRoom() {
        var room = player.currentRoom;

        if (room.gate != null) {
            var gate = room.gate;

            if (gate.startOpening) {
                gate.y -= 1;
                if (gate.y <= 300) {
                    gate.isOpen = true;
                    gate.startOpening = false;
                }
            }

            image(gateImg, gate.x, gate.y);
        }

        for (var wall : room.walls()) {
            if (wall.nextRoom() != null)
                continue; // don't draw the wall if it's actually a path

            fill(room.color);
            noStroke();
            rect(wall.xPos(), wall.yPos(), wall.width(), wall.height());
        }

        for (var item : room.items) {
            image(item.getImage(), item.x(), item.y());
        }
    }

    private void drawPlayer() {
        fill(player.color);
        noStroke();
        rect(player.x, player.y, 40, 40);

        if (player.currentItem != null) {
            image(player.currentItem.getImage(), player.currentItem.x(), player.currentItem.y());
        }
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
                case 'c' -> System.out.println(player.x + " " + player.y);
                case 'j' -> WON = true;
                case ' ' -> player.dropItem();
            }
        }
    }
}