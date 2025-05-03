package game.entity;
import game.Main;
import game.item.Chalice;
import game.item.Sword;
import game.map.Room;
import processing.core.*;

import java.util.ArrayList;
import java.util.Random;

public class Dragon {
    public int x;
    public int y;
    public final int speed;
    public PImage img;
    public PImage dead;
    public final int hitboxWidth;
    public final int hitboxHeight;
    public boolean isAlive = true;
    public Room currentRoom;
    private final Player target;
    private boolean guard = true;
    private boolean chase = false;
    private boolean roam = false;

    private Random random = new Random();

    private int numSwaps = 0;
    private final int persistance;
    private int swapDelay = 60;
    private int entryX = 0;
    private int entryY = 0;

    private int stuckTicks = 0;
    private int ticksRoaming = 0;

    public Dragon(int x, int y, int speed, int persistance, PImage img, PImage dead, Player player) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.persistance = persistance;
        this.img = img;
        this.dead = dead;
        hitboxWidth = 55;
        hitboxHeight = 135;
        this.target = player;
        randomizeEntry();
    }

    public void kill() {
        isAlive = false;
    }

    public void tick() {
        if (!isAlive)
            return;

        if (target.currentRoom == currentRoom) {
            guard = false;
            chase = true;
            roam = false;
            // move to the player and try and kill them
            int dx = Integer.compare(target.x - this.x, 0);
            int dy = Integer.compare(target.y - this.y, 0);

            this.x += dx * speed;
            this.y += dy * speed;

            if (target.currentItem != null && target.currentItem instanceof Sword sword) {
                boolean col = sword.isInside(x, y, (int)(img.width * 0.35f), (int)(img.height * 0.35f));
                if (col)
                    sword.onDragonInteract(this);
            }

            if (isInside(target.x, target.y, target.size, target.size)) {
                Main.LOST = true;
            }

            return;
        }

        if (guard) {
            int dx = Integer.compare(700 - this.x, 0);
            int dy = Integer.compare(500 - this.y, 0);

            this.x += dx * speed;
            this.y += dy * speed;
        }

        if (chase) {
            if (stuckTicks >= 180) {
                moveRandomRoom();
                chase = false;
                return;
            }

            if (swapDelay > 0) {
                swapDelay--;
            } else if (swapDelay == 0) {
                if (target.currentRoom.castle) {
                    stuckTicks++;
                    return;
                }

                if (numSwaps >= persistance) {
                    roam = true;
                    chase = false;
                    numSwaps = 0;
                    return;
                }

                x = entryX;
                y = entryY;
                currentRoom = target.currentRoom;
                swapDelay += 120;
                numSwaps++;
                randomizeEntry();
            }
        }

        if (roam) {
            if (!currentRoom.items.isEmpty()) {
                for (var item : currentRoom.items) {
                    if (item instanceof Chalice) {
                        this.guard = true;
                        this.roam = false;
                        return;
                    }
                }
            }

            if (ticksRoaming >= 1800) {
                this.roam = false;
                this.guard = true;
                return;
            }

            if (ticksRoaming % 120 == 10) {
                x = entryX;
                y = entryY;
                randomizeEntry();
                moveRandomRoom();
            }

            ticksRoaming++;
        }
    }

    private void randomizeEntry() {
        int tx = 0;
        int ty = 0;

        while (tx >= 0 && tx <= 1280 && ty >= 0 && ty <= 720) {
            tx = random.nextInt(-100, 1380);
            ty = random.nextInt(-50, 770);
        }

        entryX = tx;
        entryY = ty;
    }

    private void moveRandomRoom() {
        var walls = currentRoom.walls();

        ArrayList<Room> potential = new ArrayList<>();

        for (var wall : walls) {
            if (wall.nextRoom() != null) {
                potential.add(wall.nextRoom());
            }
        }

        int rand = random.nextInt(0, potential.size());

        currentRoom = potential.get(rand);
    }

    public boolean isInside(int x, int y, int w, int h) {
        return x < this.x + img.width * 0.35f && x + w > this.x && y < this.y + img.height * 0.35f && y + h > this.y;
    }
}
