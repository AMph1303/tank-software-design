package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank {

    private static final Texture TankTexture = new Texture("images/tank_blue.png");
    private static TextureRegion TankGraphics = new TextureRegion(TankTexture);
    private static Rectangle TankRectangle;
    private static GridPoint2 TankDestinationCoordinates = new GridPoint2(1, 1);
    private static GridPoint2 TankCoordinates = new GridPoint2(TankDestinationCoordinates);
    private static float TankMovementProgress = 1f;
    private static float TankRotation = 0f;

    public Tank() {
        this.TankRectangle = createBoundingRectangle(TankGraphics);
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

    public static void Move(GridPoint2 ObstaclesCoordinates, float deltaTime, float MOVEMENT_SPEED) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            if (isEqual(TankMovementProgress, 1f)) {
                // check potential player destination for collision with obstacles
                if (!ObstaclesCoordinates.equals(incrementedY(TankCoordinates))) {
                    TankDestinationCoordinates.y++;
                    TankMovementProgress = 0f;
                }
                TankRotation = 90f;
            }
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            if (isEqual(TankMovementProgress, 1f)) {
                if (!ObstaclesCoordinates.equals(decrementedX(TankCoordinates))) {
                    TankDestinationCoordinates.x--;
                    TankMovementProgress = 0f;
                }
                TankRotation = -180f;
            }
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            if (isEqual(TankMovementProgress, 1f)) {
                if (!ObstaclesCoordinates.equals(decrementedY(TankCoordinates))) {
                    TankDestinationCoordinates.y--;
                    TankMovementProgress = 0f;
                }
                TankRotation = -90f;
            }
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            if (isEqual(TankMovementProgress, 1f)) {
                if (!ObstaclesCoordinates.equals(incrementedX(TankCoordinates))) {
                    TankDestinationCoordinates.x++;
                    TankMovementProgress = 0f;
                }
                TankRotation = 0f;
            }
        }

        TankMovementProgress = continueProgress(TankMovementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(TankMovementProgress, 1f)) {
            // record that the player has reached his/her destination
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