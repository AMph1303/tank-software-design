package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {
    private static final float MOVEMENT_SPEED = 0.4f;
    private static Texture TankTexture;
    private static TextureRegion TankGraphics;
    private static Rectangle TankRectangle;
    private static GridPoint2 TankDestinationCoordinates = new GridPoint2(1, 1);
    private static GridPoint2 TankCoordinates;
    private static float TankMovementProgress = 1f;
    private static float TankRotation = 0f;

    public Tank(GridPoint2 coordinates, float rotation) {
        this.TankCoordinates = coordinates;
        this.TankRotation = rotation;
    }

    public void CreateTankGraphics(String PathTexture ){
        this.TankTexture = new Texture(PathTexture);
        this.TankGraphics = new TextureRegion(this.TankTexture);
        this.TankRectangle = createBoundingRectangle(this.TankGraphics);
    }
    public static Rectangle TankRectangle() {
        return TankRectangle;
    }

    public static GridPoint2 TankCoordinates() {
        return TankCoordinates;
    }

    public static float TankMovementProgress() {
        return TankMovementProgress;
    }

    public static GridPoint2 TankDestinationCoordinates() {
        return TankDestinationCoordinates;
    }

    public void checkMove(Orientation direction, ListOfObstacles listOfObstacles) {
        if (isEqual(TankMovementProgress, 1f)) {
            GridPoint2 estimateCoordinates = new GridPoint2(TankCoordinates);
            estimateCoordinates.add(direction.Coordinates());
            TankRotation = direction.Angle();
            if (listOfObstacles.PossibilityOfMovement(TankCoordinates, direction.Coordinates())) {
                TankMovementProgress = 0f;
                TankDestinationCoordinates = estimateCoordinates;
            }
        }
    }

    public void Move(ListOfObstacles listOfObstacles, Keyboards Keyboard) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Keyboard.isUp()) {
            checkMove(new Orientation(0, 1), listOfObstacles);
        }
        if (Keyboard.isLeft()) {
            checkMove(new Orientation(-1, 0), listOfObstacles);
        }
        if (Keyboard.isDown()) {
            checkMove(new Orientation(0, -1), listOfObstacles);
        }
        if (Keyboard.isRight()) {
            checkMove(new Orientation(1, 0), listOfObstacles);
        }
        TankMovementProgress = continueProgress(TankMovementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(TankMovementProgress, 1f)) {
            TankCoordinates.set(TankDestinationCoordinates);
        }
    }

    public void Render(Batch batch){
        drawTextureRegionUnscaled(batch, TankGraphics, TankRectangle, TankRotation);
    }

    public void dispose(){
    TankTexture.dispose();
    }

}