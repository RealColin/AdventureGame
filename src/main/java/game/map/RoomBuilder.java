package game.map;

import game.Util;

import java.util.ArrayList;

public class RoomBuilder {
    private final ArrayList<Room> rooms;

    private final Wall TOP = new Wall(0, 0, 1280, 40, null);
    private final Wall BOTTOM = new Wall(0, 680, 1280, 40, null);
    private final Wall LEFT = new Wall(0, 40, 10, 640, null);
    private final Wall RIGHT = new Wall(1270, 40, 10, 640, null);

    public RoomBuilder() {
        rooms = new ArrayList<>();
        createRooms();
    }

    public Room getStartRoom() {
        return rooms.getFirst();
    }

    private void createLevelOneRooms() {
        // TODO implement this
    }

    private void createLevelTwoRooms() {
        // TODO implement this
    }

    private void createLevelThreeRooms() {
        // TODO implement this
    }

    private void createRooms() {
        Room start = new Room(null, Util.rgbToInt(0, 255, 0));
        Room leftHallway = new Room(null, Util.rgbToInt(100, 255, 0));
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
        yellowCastleEntry.updateWalls(createYellowCastleEntry());
        yellowCastleRoom.updateWalls(createYellowCastleRoom());
    }

    /*
        Room building functions
     */

    private Wall[] createStartRoom() {
        var walls = new Wall[] {
                leftEntry(rooms.get(1)),
                rightEntry(rooms.get(2)),
                BOTTOM
        };
        return combine(walls, topEntry(rooms.get(3)));
    }

    private Wall[] createLeftHallway() {
        var walls = new Wall[] {
                LEFT,
                rightEntry(rooms.get(0)),
                BOTTOM
        };
        return combine(walls, topEntry(rooms.get(5)));
    }

    private Wall[] createRightHallway() {
        var walls = new Wall[] {
                TOP,
                leftEntry(rooms.get(0)),
                RIGHT
        };
        return combine(walls, bottomEntry(rooms.get(6)));
    }

    private Wall[] createYellowCastleEntry() {
        Wall TL = new Wall(0, 0, 565, 40, null);
        Wall TM = new Wall(565, 0, 150, 40, rooms.get(4));
        Wall TR = new Wall(715, 0, 565, 40, null);
        Wall BL = new Wall(0, 680, 565, 40, null);
        Wall BM = new Wall(565, 680, 150, 40, rooms.get(0));
        Wall BR = new Wall(715, 680, 565, 40, null);
        Wall L = new Wall(0, 40, 10, 640, null);
        Wall R = new Wall(1270, 40, 10, 640, null);

        return new Wall[] {TL, TM, TR, BL, BM, BR, L, R};
    }

    private Wall[] createYellowCastleRoom() {
        var walls = new Wall[] {
                LEFT,
                RIGHT,
                TOP
        };
        return combine(walls, bottomEntry(rooms.get(3)));
    }

    /*
        Helper functions
     */

    private Wall[] createCastle() {

        return new Wall[] {};
    }

    private Wall[] topEntry(Room room) {
        Wall TL = new Wall(0, 0, 565, 40, null);
        Wall TM = new Wall(565, 0, 150, 40, room);
        Wall TR = new Wall(715, 0, 565, 40, null);

        return new Wall[] {TL, TM, TR};
    }

    private Wall[] bottomEntry(Room room) {
        Wall BL = new Wall(0, 680, 565, 40, null);
        Wall BM = new Wall(565, 680, 150, 40, room);
        Wall BR = new Wall(715, 680, 565, 40, null);

        return new Wall[] {BL, BM, BR};
    }

    private Wall leftEntry(Room room) {
        return new Wall(0, 40, 10, 640, room);
    }

    private Wall rightEntry(Room room) {
        return new Wall(1270, 40, 10, 640, room);
    }

    private Wall[] combine(Wall[] first, Wall[] second) {
        var ret = new Wall[first.length + second.length];
        System.arraycopy(first, 0, ret, 0, first.length);
        System.arraycopy(second, 0, ret, first.length, second.length);

        return ret;
    }
}
