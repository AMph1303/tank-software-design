package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class ListOfObstacles {
    private List<Obstacle> obstacles = new ArrayList<>();
        public void add(Obstacle obstacle) {obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean PossibilityOfMovement(GridPoint2 TankCoordinates, GridPoint2 move) {
        GridPoint2 estimatedCoordinates = TankCoordinates.add(move);
        for (Obstacle obstacle : obstacles)
            if (obstacle.ObstacleCoordinates().equals(estimatedCoordinates)) {
                return false;
            }
        return true;
    }
}
