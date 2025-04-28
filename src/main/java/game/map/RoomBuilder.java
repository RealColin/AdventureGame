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
        Room mazeHallway = new Room(null, Util.rgbToInt(0, 0, 255));
        Room mazeBigRoom = new Room(null, Util.rgbToInt(0, 0, 255));
        Room mazeTop = new Room(null, Util.rgbToInt(0, 0, 255));
        Room mazeExit = new Room(null, Util.rgbToInt(0, 0, 255));

        //
        Room bottomRoom = new Room(null, Util.rgbToInt(0, 255, 200));

        rooms.add(0, start);
        rooms.add(1, leftHallway);
        rooms.add(2, rightHallWay);
        rooms.add(3, yellowCastleEntry);
        rooms.add(4, yellowCastleRoom);
        rooms.add(5, mazeEntry);
        rooms.add(6, mazeHallway);
        rooms.add(7, mazeBigRoom);
        rooms.add(8, mazeTop);
        rooms.add(9, mazeExit);
        rooms.add(10, bottomRoom);

        start.updateWalls(createStartRoom());
        leftHallway.updateWalls(createLeftHallway());
        rightHallWay.updateWalls(createRightHallway());
        yellowCastleEntry.updateWalls(createYellowCastleEntry());
        yellowCastleRoom.updateWalls(createYellowCastleRoom());
        mazeEntry.updateWalls(createMazeEntry());
        mazeHallway.updateWalls(createMazeHallway());
        mazeBigRoom.updateWalls(createMazeBigRoom());
        mazeTop.updateWalls(createMazeTop());
        mazeExit.updateWalls(createMazeExit());
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
        Wall[] B = bottomEntry(rooms.get(0));
        Wall L = new Wall(0, 40, 10, 640, null);
        Wall R = new Wall(1270, 40, 10, 640, null);

        var walls = new Wall[] {
                TL,
                TM,
                TR,
                L,
                R
        };

        return combine(walls, B);
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
                new Wall(750, 0, 60, 185, null),

                // paths
                new Wall(0, 0, 10, 720, rooms.get(6)), // left side
                new Wall(1270, 0, 10, 720, rooms.get(6)), // right side
                new Wall(0, 0, 1280, 10, rooms.get(8)), // top side
        };

        var B = bottomEntry(rooms.get(1));

        return combine(walls, B);
    }

    private Wall[] createMazeHallway() {
        var walls = new Wall[]{
                new Wall(0, 450, 110, 120, null), // thick middle segment left side
                new Wall(1170, 450, 110, 120, null), // thick middle segment right side
                new Wall(0, 680, 270, 40, null), // bottom segment left side
                new Wall(1010, 680, 270, 40, null), // bottom segment right side

                new Wall(0, 185, 350, 120, null), // thick upper segment left side
                new Wall(930, 185, 350, 120, null), // thick upper segment right side

                new Wall(0, 0, 110, 40, null),
                new Wall(370, 0, 100, 40, null),
                new Wall(810, 0, 100, 40, null),
                new Wall(1170, 0, 110, 40, null),

                new Wall(210, 450, 60, 230, null), // bottom vertical segment left side
                new Wall(1010, 450, 60, 230, null), // bottom vertical segment right side

                new Wall(350, 185, 60, 720 - 185, null), // longer vertical left
                new Wall(870, 185, 60, 720 - 185, null), // longer vertical right

                new Wall(210, 0, 60, 185, null),
                new Wall(1010, 0, 60, 185, null),

                new Wall(470, 0, 60, 720, null), // longest vertical left
                new Wall(750, 0, 60, 720, null), // longest vertical right

                new Wall(530, 0, 60, 570, null),
                new Wall(690, 0, 60, 570, null),

                // paths
                new Wall(0, 0, 10, 720, rooms.get(5)), // left side
                new Wall(1270, 0, 10, 720, rooms.get(5)), // right side
                new Wall(0, 0, 1280, 10, rooms.get(9)), // top side
                new Wall(0, 710, 1280, 10, rooms.get(7)), // bottom side
        };

        return walls;
    }

    private Wall[] createMazeBigRoom() {
        var walls = new Wall[] {
                new Wall(0, 0, 1280, 10, rooms.get(6)),

        };

        return walls;
    }

    private Wall[] createMazeTop() {
        var walls = new Wall[] {
                new Wall(0, 710, 1280, 10, rooms.get(5)),
        };

        return walls;
    }

    private Wall[] createMazeExit() {
        var walls = new Wall[] {
                new Wall(0, 710, 1280, 10, rooms.get(6)),
        };

        return walls;
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
