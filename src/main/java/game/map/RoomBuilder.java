package game.map;

import game.Util;

import java.util.ArrayList;

public class RoomBuilder {
    private final ArrayList<Room> rooms;

    private final Wall TOP = new Wall(0, 0, 1280, 40, null);
    private final Wall BOTTOM = new Wall(0, 680, 1280, 40, null);
    private final Wall LEFT = new Wall(0, 40, 40, 640, null);
    private final Wall RIGHT = new Wall(1240, 40, 40, 640, null);

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

        // blue maze
        Room mazeEntry = new Room(null, Util.rgbToInt(0, 0, 255));

        //
        Room bottomRoom = new Room(null, Util.rgbToInt(0, 255, 200));

        rooms.add(0, start);
        rooms.add(1, leftHallway);
        rooms.add(2, rightHallWay);
        rooms.add(3, yellowCastleEntry);
        rooms.add(4, yellowCastleRoom);
        rooms.add(5, mazeEntry);
        rooms.add(6, bottomRoom);

        start.updateWalls(createStartRoom());
        leftHallway.updateWalls(createLeftHallway());
        rightHallWay.updateWalls(createRightHallway());
        yellowCastleEntry.updateWalls(createYellowCastleEntry());
        yellowCastleRoom.updateWalls(createYellowCastleRoom());
        mazeEntry.updateWalls(createMazeEntry());
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

    private Wall[] createMazeEntry() {
        var walls = new Wall[] {
                // bottom horizontal row
                new Wall(0, 450, 270, 120, null),
                new Wall(350, 450, 580, 120, null),
                new Wall(1010, 450, 270, 120, null),

                // middle horizontal row
                new Wall(0, 185, 110, 120, null),
                new Wall(470, 185, 340, 120, null),
                new Wall(1170, 185, 110, 120, null),

                // top horizontal row
                new Wall(0, 0, 270, 40, null),
                new Wall(1010, 0, 270, 40, null),
                new Wall(590, 0, 100, 40, null),

                // tall vertical row
                new Wall(210, 40, 60, 410, null),
                new Wall(350, 0, 60, 450, null),
                new Wall(870, 0, 60, 450, null),
                new Wall(1010, 40, 60, 410, null),

                // short vertical row
                new Wall(470, 0, 60, 185, null),
                new Wall(750, 0, 60, 185, null)


//                // tall vertical row
//                new Wall(210, 40, 60, 430, null),
//                new Wall(350, 0, 40, 470, null),
//                new Wall(890, 0, 40, 470, null),
//                new Wall(1010, 40, 60, 430, null),
//
//                // short vertical row
//                new Wall(460, 0, 40, 205, null),
//                new Wall(780, 0, 40, 205, null),

                // other
//                new Wall(300, 0, 50, 490, null),
//                new Wall(930, 0, 50, 490, null),
//                new Wall(400, 330, 480, 80, null),
//                new Wall(1270, 610, 10, 0, null),

        };

        var B = bottomEntry(rooms.get(1));

        return combine(walls, B);
    }

    /*
        Helper functions
     */

    private Wall[] createCastle() {

        return new Wall[] {};
    }

    private Wall[] topEntry(Room room) {
        Wall TL = new Wall(0, 0, 565, 40, null);
        Wall TM = new Wall(565, 0, 150, 10, room);
        Wall TR = new Wall(715, 0, 565, 40, null);

        return new Wall[] {TL, TM, TR};
    }

    private Wall[] bottomEntry(Room room) {
        Wall BL = new Wall(0, 680, 565, 40, null);
        Wall BM = new Wall(565, 710, 150, 10, room);
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
