package game.map;

import game.Util;

import java.util.ArrayList;

public class RoomBuilder {
    private final ArrayList<Room> rooms;

    public RoomBuilder() {
        rooms = new ArrayList<>();
        createRooms();
    }

    public Room getStartRoom() {
        return rooms.getFirst();
    }

    private void createRooms() {
        Room start = new Room(null, Util.rgbToInt(0, 255, 0));
        Room leftHallway = new Room(null, Util.rgbToInt(200, 255, 0));
        Room rightHallWay = new Room(null, Util.rgbToInt(0, 255, 100));
        Room yellowCastleEntry = new Room(null, Util.rgbToInt(255, 255, 0));
        Room yellowCastleRoom = new Room(null, Util.rgbToInt(255, 255 ,0));
        Room topRoom = new Room(null, Util.rgbToInt(0, 0, 255));
        Room bottomRoom = new Room(null, Util.rgbToInt(0, 255, 200));

        rooms.add(0, start);
        rooms.add(1, leftHallway);
        rooms.add(2, rightHallWay);
        rooms.add(3, yellowCastleEntry);
        rooms.add(4, yellowCastleRoom);
        rooms.add(5, topRoom);
        rooms.add(6, bottomRoom);

        start.updateWalls(createStartRoom());
        leftHallway.updateWalls(createLeftHallway());
        rightHallWay.updateWalls(createRightHallway());
    }

    private Wall[] createStartRoom() {
        Wall TL = new Wall(0, 0, 565, 40, null);
        Wall TM = new Wall(565, 0, 150, 40, rooms.get(3));
        Wall TR = new Wall(715, 0, 565, 40, null);
        Wall B = new Wall(0, 680, 1280, 40, null);
        Wall L = new Wall(0, 40, 10, 640, rooms.get(1));
        Wall R = new Wall(1270, 40, 10, 640, rooms.get(2));

        return new Wall[]{TL, TM, TR, B, L, R};
    }

    private Wall[] createLeftHallway() {
        Wall TL = new Wall(0, 0, 565, 40, null);
        Wall TM = new Wall(565, 0, 150, 40, rooms.get(5));
        Wall TR = new Wall(715, 0, 565, 40, null);
        Wall B = new Wall(0, 680, 1280, 40, null);
        Wall L = new Wall(0, 40, 10, 640, null);
        Wall R = new Wall(1270, 40, 10, 640, rooms.get(0));

        return new Wall[]{TL, TM, TR, B, L, R};
    }

    private Wall[] createRightHallway() {
        Wall T = new Wall(0, 0, 1280, 40, null);
        Wall BL = new Wall(0, 680, 565, 40, null);
        Wall BM = new Wall(565, 680, 150, 40, rooms.get(6));
        Wall BR = new Wall(715, 680, 565, 40, null);
        Wall L = new Wall(0, 40, 10, 640, rooms.get(0));
        Wall R = new Wall(1270, 40, 10, 640, null);

        return new Wall[]{T, BL, BM, BR, L, R};
    }
}
