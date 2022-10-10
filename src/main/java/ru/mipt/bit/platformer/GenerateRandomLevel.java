package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

public class GenerateRandomLevel {
    private GridPoint2 TankCoordinate;
    private GridPoint2 ObstacleCoordinate;
    List<GridPoint2> ObstacleCoordinates = new ArrayList<>();
    private int ObstacleNumber;

    public void GenerateRandomLevel() {
        TankCoordinate = generateTankCoordinate();
        ObstacleNumber = (int) (Math.random() * 20);
        ObstacleCoordinates = generateObstracleCoordinate(ObstacleNumber);

    }
    public GridPoint2 getTankCoordinate(){
        return TankCoordinate;
    }

    public List<GridPoint2> getObstracleCoordinate(){
        return ObstacleCoordinates;
    }

    private GridPoint2 generateCoordinate(){
        return new GridPoint2((int) (Math.random() * 10), (int) (Math.random() * 8));
    }
    private GridPoint2 generateTankCoordinate(){
        return generateCoordinate();
    }
    private List<GridPoint2> generateObstracleCoordinate(int N){
        List<GridPoint2> treeCoordinates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            GridPoint2 NewCoord = generateCoordinate();
            treeCoordinates.add(NewCoord);

        }
        return treeCoordinates;
    };

}
