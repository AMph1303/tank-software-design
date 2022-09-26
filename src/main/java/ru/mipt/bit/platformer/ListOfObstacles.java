package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;

public class ListOfObstacles {
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
        public void addObstacle(Obstacle obstacle) {
            obstacles.add(obstacle);
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
