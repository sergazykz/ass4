package ass4;

import java.util.HashMap;
import java.util.Map;

class Wall implements Cloneable {

    @Override
    public Wall clone() {
        try {
            return (Wall) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


class PrototypeManager {
    private Map<String, Room> roomPrototypes = new HashMap<>();
    private Map<String, Wall> wallPrototypes = new HashMap<>();

    public void addRoomPrototype(String key, Room room) {
        roomPrototypes.put(key, room);
    }

    public void addWallPrototype(String key, Wall wall) {
        wallPrototypes.put(key, wall);
    }

    public Room getRoomClone(String key) {
        return roomPrototypes.get(key);
    }


    public Wall getWallClone(String key) {
        return wallPrototypes.get(key).clone();
    }
}

class MazeGame {
    private PrototypeManager prototypeManager;

    public MazeGame(PrototypeManager manager) {
        this.prototypeManager = manager;
    }

    public Maze createMaze() {
        Maze aMaze = new Maze();
        Room r1 = prototypeManager.getRoomClone("StandardRoom");
        Room r2 = prototypeManager.getRoomClone("StandardRoom");
        DoorWall d = (DoorWall) prototypeManager.getWallClone("DoorWall");

        aMaze.addRoom(r1);
        aMaze.addRoom(r2);

        r1.setSide(Direction.NORTH, d);
        r1.setSide(Direction.EAST, prototypeManager.getWallClone("Wall"));

        return aMaze;
    }
}
