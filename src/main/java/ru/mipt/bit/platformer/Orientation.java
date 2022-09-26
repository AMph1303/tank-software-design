package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Orientation {
    private final GridPoint2 coordinates;
    private final float angle;

    public Orientation(int x, int y) {
        coordinates = new GridPoint2(x, y);
        angle = (float) (Math.atan2(y, x) * 180 / Math.PI);
    }

    public float Angle() {
        return angle;
    }

    public GridPoint2 Coordinates() {
        return coordinates;
    }
}