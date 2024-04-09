package ass4;

import java.util.HashMap;
import java.util.Map;

interface MazeBuilder {
    void buildRoom(int roomNo);
    void buildWall(int roomNo, Direction direction);
    void buildDoor(int room1No, int room2No, Direction direction);
    Maze getMaze();
}

class StandardMazeBuilder implements MazeBuilder {
    private Maze currentMaze;

    public StandardMazeBuilder() {
        currentMaze = new Maze();
    }

    @Override
    public void buildRoom(int roomNo) {
        if (currentMaze.roomNo(roomNo) == null) {
            Room room = new Room(roomNo);
            currentMaze.addRoom(room);
        }
    }

    @Override
    public void buildWall(int roomNo, Direction direction) {
        Room room = currentMaze.roomNo(roomNo);
        if (room != null) {
            room.setSide(direction, new Wall());
        }
    }

    @Override
    public void buildDoor(int room1No, int room2No, Direction direction) {
        Room room1 = currentMaze.roomNo(room1No);
        Room room2 = currentMaze.roomNo(room2No);
        if (room1 != null && room2 != null) {
            DoorWall door = new DoorWall(room1, room2);
            room1.setSide(direction, door);
            room2.setSide(direction.opposite(), door);
        }
    }

    @Override
    public Maze getMaze() {
        return currentMaze;
    }
}

class MazeDirector {
    private MazeBuilder builder;

    public MazeDirector(MazeBuilder builder) {
        this.builder = builder;
    }

    public Maze construct() {
        builder.buildRoom(1);
        builder.buildRoom(2);
        builder.buildWall(1, Direction.NORTH);
        builder.buildDoor(1, 2, Direction.EAST);

        return builder.getMaze();
    }
}



