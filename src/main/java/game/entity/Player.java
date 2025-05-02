package game.entity;

import game.item.Item;
import game.item.Key;
import game.map.Direction;
import game.map.Room;

public class Player {
    public int x;
    public int y;
    public int size;
    public int speed;
    public int color;
    public Room currentRoom;
    public Item currentItem;
    public int ox;
    public int oy;

    public Player(int x, int y, int size, int speed, int color, Room currentRoom) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
        this.currentRoom = currentRoom;
    }

    // TODO definitely make this cleaner
    public void move(Direction dir) {
        int tx = x, ty = y;

        switch (dir) {
            case UP -> ty -= speed;
            case LEFT -> tx -= speed;
            case RIGHT -> tx += speed;
            case DOWN -> ty += speed;
        }
        
        Item old = null;
        Item _new = null;

        if (!currentRoom.items.isEmpty()) {
            for (var item : currentRoom.items) {
                boolean col = item.isInside(tx, ty, size, size);

                if (col) {
                    if (currentItem != null) {
                        old = currentItem;
                    }

                    _new = item;
                    break;
                }
            }
        }

        if (old != null) {
            dropItem();
        }

        if (currentItem == null &&_new != null) {
            int ix = tx, iy = ty;
            switch (dir) {
                case UP -> iy -= (_new.getImage().height + 5);
                case LEFT -> ix -= (_new.getImage().width + 5);
                case RIGHT -> ix += (size + 5);
                case DOWN -> iy += (size + 5);
            }

            ox = x - ix;
            oy = y - iy;

            _new.pickup(currentRoom, ix, iy);
            currentItem = _new;
        }

        if (currentRoom.gate != null) {
            var gate = currentRoom.gate;

            boolean col = gate.isInside(tx, ty, size, size);

            if (col) {
                if (gate.isOpen) {
                    this.currentRoom = gate.room;
                    switch (dir) {
                        case UP -> ty = 665;
                        case DOWN -> ty = 15;
                        case LEFT -> tx = 1225;
                        case RIGHT -> tx = 15;
                    }
                }  else {
                    switch (dir) {
                        case UP -> ty = gate.y + gate.height;
                        case DOWN -> ty = gate.y - size;
                        case LEFT -> tx = gate.y + gate.width;
                        case RIGHT -> tx = gate.y - size;
                    }

                    if (currentItem != null && currentItem instanceof Key key)
                        key.onGateInteract(gate);
                }
            }
        }

        for (var wall : currentRoom.walls()) {
            boolean col = wall.isInside(tx, ty, size, size);

            if (!col) continue;


            if (wall.nextRoom() != null) {
                this.currentRoom = wall.nextRoom();



                switch (dir) {
                    case UP -> ty = 665;
                    case DOWN -> ty = 15;
                    case LEFT -> tx = 1225;
                    case RIGHT -> tx = 15;
                }

                if (currentRoom.gate != null && ty == 15) {
                    tx = 620;
                    ty = 470;
                }

                x = tx;
                y = ty;
                return;
            }

            switch (dir) {
                case UP -> ty = wall.yPos() + wall.height();
                case DOWN -> ty = wall.yPos() - size;
                case LEFT -> tx = wall.xPos() + wall.width();
                case RIGHT -> tx = wall.xPos() - size;
            }
        }

        x = tx;
        y = ty;

        if (currentItem != null) {
            currentItem.move(x - ox, y - oy);
        }
    }

    public void dropItem() {
        if (currentItem != null) {

            for (var wall : currentRoom.walls()) {
                if (wall.isInside(currentItem.x(), currentItem.y(), currentItem.getImage().width, currentItem.getImage().height))
                    return;
            }

            currentItem.drop(currentRoom);
            this.currentItem = null;
        }
    }
}
