package org.example;
import processing.core.PApplet;

import java.util.HashSet;

public class Main extends PApplet {
    private Player player;
    private Dragon greenDragon;
    private final HashSet<Character> keysPressed = new HashSet<>();

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

        // have to alter this for proper room setup
        Room main = new Room(new Wall[0]);

        player = new Player(50, 50, 40, 5, color(255, 255, 0), main);
        greenDragon = new Dragon(700, 300, loadImage("green_dragon.png"));
    }

    // test comment

    @Override
    public void draw() {
        handleInput();

        // TODO remove the collision handling code in this method
        boolean collision = isCollided(player, greenDragon);
        // handle input first
        if (keysPressed.contains('w')) {
            player.move(0, -7);
            if (isCollided(player, greenDragon) && (player.x < greenDragon.x + greenDragon.hitboxWidth) && (player.x + 40 > greenDragon.x) && player.y > greenDragon.y) {
                player.move(0, (greenDragon.y + greenDragon.hitboxHeight) - player.y);
            }
        }
        if (keysPressed.contains('s')) {
            player.move(0, 7);
            if (isCollided(player, greenDragon) && (player.x < greenDragon.x + greenDragon.hitboxWidth) && (player.x + 40 > greenDragon.x) && player.y < greenDragon.y) {
                player.move(0, greenDragon.y - (player.y + 40));
            }
        }
        if (keysPressed.contains('a')) {
            player.move(-7, 0);
            if (isCollided(player, greenDragon) && (player.y < greenDragon.y + greenDragon.hitboxHeight && player.y + 40 > greenDragon.y) && player.x > greenDragon.x) {
                player.move((greenDragon.x + greenDragon.hitboxWidth) - player.x, 0);
            }
        }
        if (keysPressed.contains('d')) {
            player.move(7, 0);
            if (isCollided(player, greenDragon) && (player.y < greenDragon.y + greenDragon.hitboxHeight && player.y + 40 > greenDragon.y) && player.x < greenDragon.x) {
                player.move(greenDragon.x - (player.x + 40), 0);
            }
        }


        // then draw stuff
        background(170, 170, 170);

        // player and player hitbox
        drawPlayer(collision);
        drawPlayerHitbox();

        // dragon and dragon hitbox
        image(greenDragon.img, greenDragon.x, greenDragon.y, greenDragon.img.width * 0.35f, greenDragon.img.height * 0.35f);
        stroke(255, 255, 255);
        strokeWeight(2);
        noFill();
        rect(greenDragon.x, greenDragon.y, greenDragon.hitboxWidth, greenDragon.hitboxHeight);
    }

    // TODO remove the parameter from this function
    private void drawPlayer(boolean collided) {
        fill(player.color);

        if (collided) {
            fill(255, 0, 0);
        }

        noStroke();
        rect(player.x, player.y, 40, 40);
    }

    private void drawPlayerHitbox() {
        stroke(255, 255, 255);
        strokeWeight(2);
        noFill();
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

    private boolean isCollided(Player player, Dragon dragon) {
        int pWidth = 40;
        int pHeight = 40;

        int pTop = player.y;
        int pBottom = player.y + 40;
        int pLeft = player.x;
        int pRight = player.x + pWidth;

        int dTop = dragon.y;
        int dBottom = dragon.y + dragon.hitboxHeight;
        int dLeft = dragon.x;
        int dRight = dragon.x + dragon.hitboxWidth;

        if (pLeft <= dRight && pRight >= dLeft && pTop <= dBottom && pBottom >= dTop)
            return true;

        return false;
    }
}