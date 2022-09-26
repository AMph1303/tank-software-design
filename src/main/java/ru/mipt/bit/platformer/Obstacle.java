package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Obstacle {
    private static Texture ObstacleTexture;
    private static TextureRegion ObstacleGraphics;
    private GridPoint2 ObstacleCoordinates;
    private static Rectangle ObstacleRectangle = new Rectangle();

    public Obstacle(GridPoint2 StartCoordinates){
        this.ObstacleCoordinates = StartCoordinates;
    }
    public void CreateObstacleGraphics(String PathTexture){
            this.ObstacleTexture = new Texture(PathTexture);
            this.ObstacleGraphics = new TextureRegion(this.ObstacleTexture);
            this.ObstacleRectangle = createBoundingRectangle(ObstacleGraphics);
        }
        public GridPoint2 ObstacleCoordinates() {
            return ObstacleCoordinates;
        }

        public static Rectangle ObstacleRectangle() {
            return ObstacleRectangle;
        }

        public void Render(Batch batch){
            drawTextureRegionUnscaled(batch, ObstacleGraphics, ObstacleRectangle, 0f);
        }
        public void dispose() {
            ObstacleTexture.dispose();
        }
    }
