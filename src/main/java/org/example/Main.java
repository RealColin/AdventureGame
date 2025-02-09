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
        player = new Player(50, 50, color(255, 255, 0));
        greenDragon = new Dragon(700, 300, loadImage("green_dragon.png"));
    }

    @Override
    public void draw() {
        // handle input first
        if (keysPressed.contains('w')) {
            player.move(0, -5);
        }
        if (keysPressed.contains('s')) {
            player.move(0, 5);
        }
        if (keysPressed.contains('a')) {
            player.move(-5, 0);
        }
        if (keysPressed.contains('d')) {
            player.move(5, 0);
        }


        // then draw stuff
        background(170, 170, 170);
        drawPlayer();
        image(greenDragon.img, greenDragon.x, greenDragon.y, greenDragon.img.width * 0.35f, greenDragon.img.height * 0.35f);
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
}