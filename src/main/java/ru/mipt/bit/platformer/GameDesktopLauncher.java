package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class GameDesktopLauncher implements ApplicationListener {
    private Batch batch;
    private Level level;
    private TileMovement tileMovement;
    private Keyboards keyboards;
    private Tank player;
    private Obstacle tree;
    private List<GridPoint2> ObstaclesCoord;
    private ListOfObstacles listOfObstacles = new ListOfObstacles();

    @Override
    public void create() {
        batch = new SpriteBatch();
        level = new Level("level.tmx", batch);

        tileMovement = new TileMovement(level.GroundLayer(), Interpolation.smooth);
        CreateLevelFromFile levelInfo = new CreateLevelFromFile("/home/a_madan/Документы/9sem/OOPOOD/tank_hw/tank-software-design/src/main/resources/Level1.txt");
        //GenerateRandomLevel levelinfo = new GenerateRandomLevel();
        CreatePlayer(levelInfo);
        CreateObstracles(levelInfo);

    }

    private void CreatePlayer(CreateLevelFromFile levelInfo) {
        GridPoint2 TankCoordinates = levelInfo.TankCoordinatesFromFile();
        player = new Tank(TankCoordinates, 0f);
        player.CreateTankGraphics("images/tank_blue.png");
    }

    private void CreateObstracles(CreateLevelFromFile levelInfo) {
        ObstaclesCoord = levelInfo.TreeCoordinatesFromFile();
        for (GridPoint2 obstacleCoord : ObstaclesCoord){
            tree = new Obstacle(obstacleCoord);
            tree.CreateObstacleGraphics("images/greenTree.png");
            moveRectangleAtTileCenter(level.GroundLayer(), tree.ObstacleRectangle(), tree.ObstacleCoordinates());
            listOfObstacles.add(tree);
        }


    }

    @Override
    public void render() {
        level.ClearScreen();

        //тоже двиующиеся объекты как-то вынести, те итерироваться по движущимся игровым объектам
        player.Move(listOfObstacles, keyboards); // некоторые параметры лучше загнать в сам класс
        tileMovement.moveRectangleBetweenTileCenters(player.TankRectangle(), player.TankCoordinates(),
                player.TankDestinationCoordinates(), player.TankMovementProgress());

        LevelRender();
    }

    private void LevelRender() {
        level.Render();
        batch.begin();
        player.Render(batch);

        for (Obstacle obstracle : listOfObstacles.getObstacles()) {
            obstracle.Render(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        player.dispose();
        for (Obstacle obstracle : listOfObstacles.getObstacles()) {
            obstracle.dispose();
        }
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
