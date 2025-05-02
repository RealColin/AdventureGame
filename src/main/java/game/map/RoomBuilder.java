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
        Room blackCastleEntry = new Room(null, Util.rgbToInt(0, 0, 0));
        Room blackCastleRoom = new Room(null, Util.rgbToInt(0, 0, 0)); // TODO change this to orange
        Room chaliceRoom = new Room(null, Util.rgbToInt(0, 0, 0)); // TODO change this to purple

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
        rooms.add(11, blackCastleEntry);
        rooms.add(12, blackCastleRoom);
        rooms.add(13, chaliceRoom);

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
        bottomRoom.updateWalls(createBottomRoom());
        blackCastleEntry.updateWalls(createBlackCastleEntry());
        blackCastleRoom.updateWalls(createBlackCastleRoom());
        chaliceRoom.updateWalls(createChaliceRoom());

        yellowCastleEntry.gate = new Gate(yellowCastleRoom);
        blackCastleEntry.gate = new Gate(blackCastleRoom);
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
                leftEntry(rooms.getFirst()),
                RIGHT
        };
        return combine(walls, bottomEntry(rooms.get(10)));
    }

    private Wall[] createYellowCastleEntry() {
        Wall[] B = bottomEntry(rooms.getFirst());

        var walls = new Wall[] {
                LEFT,
                RIGHT,
        };

        var walls2 = combine(walls, B);

        return combine(walls2, createCastle());
    }

    private Wall[] createBlackCastleEntry() {
        var walls = new Wall[] {
                LEFT,
                RIGHT,
        };

        var walls2 = combine(walls, bottomEntry(rooms.get(9)));

        return combine(walls2, createCastle());
    }

    private Wall[] createYellowCastleRoom() {
        var walls = new Wall[] {
                LEFT,
                RIGHT,
                TOP
        };
        return combine(walls, bottomEntry(rooms.get(3)));
    }

    private Wall[] createBlackCastleRoom() {
        var walls = combine(new Wall[]{LEFT, RIGHT}, topEntry(rooms.get(13)));
        return combine(walls, bottomEntry(rooms.get(11)));
    }

    private Wall[] createChaliceRoom() {
        return combine(new Wall[]{LEFT, RIGHT, TOP}, bottomEntry(rooms.get(12)));
    }

    private Wall[] createBottomRoom() {
        return combine(new Wall[]{LEFT, RIGHT, BOTTOM}, topEntry(rooms.get(2)));
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
        return new Wall[] {
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
    }

    private Wall[] createMazeBigRoom() {
        return new Wall[] {
                BOTTOM,

                // top horizontals
                new Wall(0, 0, 210, 40, null),
                new Wall(350, 0, 60, 40, null),
                new Wall(870, 0, 60, 40, null),
                new Wall(1070, 0, 210, 40, null),

                // mid horizontals
                new Wall(210, 185, 530 - 210, 120, null),
                new Wall(750, 185, 1070 - 750, 120, null),

                // bottom horizontals
                new Wall(0, 680 - (145 + 120), 270, 120, null),
                new Wall(1010, 680 - (145 + 120), 270, 120, null),

                // top verticals
                new Wall(470, 0, 60, 185, null),
                new Wall(750, 0, 60, 185, null),
                new Wall(210, 0, 60, 185, null),
                new Wall(1010, 0, 60, 185, null),

                // left and right chunk verticals
                new Wall(0, 185, 110, 230, null),
                new Wall(1170, 185, 110, 230, null),

                // bottom verticals
                new Wall(210, 680 - 145, 60, 145, null),
                new Wall(1010, 680 - 145, 60, 145, null),

                // paths
                new Wall(0, 0, 1280, 10, rooms.get(6)),
                new Wall(0, 0, 10, 720, rooms.get(8)),
                new Wall(1270, 0, 10, 720, rooms.get(9)),
        };
    }

    private Wall[] createMazeTop() {
        return new Wall[] {
                TOP,

                // bottom horizontal
                new Wall(0, 680, 210, 40, null),
                new Wall(1070, 680, 210, 40, null),

                // middle horizontal
                new Wall(210, (680 - 145) - 120, 530 - 210, 120, null),
                new Wall(750, (680 - 145) - 120, 320, 120, null),

                // top horizontal
                new Wall(0, 185, 350, 120, null),
                new Wall(410, 185, 870 - 410, 120, null),
                new Wall(930, 185, 350, 120, null),

                // bottom vertical
                new Wall(210, 680 - 145, 60, 185, null),
                new Wall(350, 680, 60, 40, null),
                new Wall(470, 680 - 145, 60, 185, null),
                new Wall(750, 680 - 145, 60, 185, null),
                new Wall(870, 680, 60, 40, null),
                new Wall(1010, 680 - 145, 60, 185, null),

                // middle vertical
                new Wall(590, 305, 100, 720 - 305, null),

                // side verticals
                new Wall(0, 305, 110, 680 - 145 - 305, null),
                new Wall(1170, 305, 110, 680 - 145 - 305, null),

                //paths
                new Wall(0, 710, 1280, 10, rooms.get(5)),
                new Wall(0, 0, 10, 720, rooms.get(9)),
                new Wall(1270, 0, 10, 720, rooms.get(7)),
        };
    }

    private Wall[] createMazeExit() {
        var walls = new Wall[] {
                // bottom row - horizontal
                new Wall(0, 680, 110, 40, null),
                new Wall(370, 680, 160, 40, null),
                new Wall(750, 680, 160, 40, null),
                new Wall(1170, 680, 110, 40, null),

                // mid row - horizontal
                new Wall(0, 415, 590, 120, null),
                new Wall(690, 415, 590, 120, null),

                // lower columns - vertical
                new Wall(210, 535, 60, 720 - 535, null),
                new Wall(530, 535, 60, 720 - 535, null),
                new Wall(690, 535, 60, 720 - 535, null),
                new Wall(1010, 535, 60, 720 - 535, null),

                // upper columns
                new Wall(0, 185, 110, 230, null),
                new Wall(1170, 185, 110, 230, null),

                new Wall(270, 40, 60, 415 - 40, null),
                new Wall(950, 40, 60, 415 - 40, null),

                new Wall(490, 40, 60, 230, null),
                new Wall(550, 185, 40, 270 - 185, null),
                new Wall(690, 185, 40, 270-185, null),
                new Wall(730, 40, 60, 230, null),



                // paths
                new Wall(0, 0, 10, 720, rooms.get(7)), // left side
                new Wall(1270, 0, 10, 720, rooms.get(8)), // right side
                new Wall(0, 710, 1280, 10, rooms.get(6)), // bottom side
        };

        return combine(walls, topEntry(rooms.get(11)));
    }

    /*
        Helper functions
     */

    private Wall[] createCastle() {
        return new Wall[]{
                // top row
                new Wall(0, 0, 365, 40, null),
                new Wall(405, 0, 40, 40, null),
                new Wall(485, 0, 40, 40, null),

                new Wall(755, 0, 40, 40, null),
                new Wall(835, 0, 40, 40, null),


                new Wall(1280-365, 0, 365, 40, null),

                // left tower
                new Wall(325, 40, 200, 250, null),

                // right tower
                new Wall(755, 40, 200, 250, null),

                // middle
                new Wall(515, 190, 250, 220, null),

                // left bottom
                new Wall(375, 290, 200, 270, null),

                // right bottom
                new Wall(705, 290, 200, 270, null),
        };
    }

    private Wall[] topEntry(Room room) {
        Wall TL = new Wall(0, 0, 550, 40, null);
        Wall TM = new Wall(550, 0, 180, 10, room);
        Wall TR = new Wall(730, 0, 550, 40, null);

        return new Wall[] {TL, TM, TR};
    }

    private Wall[] bottomEntry(Room room) {
        Wall BL = new Wall(0, 680, 550, 40, null);
        Wall BM = new Wall(550, 710, 180, 10, room);
        Wall BR = new Wall(730, 680, 550, 40, null);

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
