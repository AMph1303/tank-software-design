package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Tree {
    private static Texture TreeTexture = new Texture("images/greenTree.png");
    private static TextureRegion TreeGraphics = new TextureRegion(TreeTexture);
    private GridPoint2 TreeCoordinates = new GridPoint2(1, 3);
    private static Rectangle TreeRectangle = new Rectangle();

    public Tree() {
        this.TreeRectangle = createBoundingRectangle(TreeGraphics);
    }

    public GridPoint2 TreeCoordinates() {
        return TreeCoordinates;
    }

    public static Rectangle TreeRectangle() {
        return TreeRectangle;
    }

    public void Render(Batch batch){
        drawTextureRegionUnscaled(batch, TreeGraphics, TreeRectangle, 0f);
    }
    public void dispose(){
        TreeTexture.dispose();
    }
}
