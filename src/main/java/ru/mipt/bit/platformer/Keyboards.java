package ru.mipt.bit.platformer;
import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.Input.Keys.*;
public class Keyboards {
        public static boolean isLeft() {
            return Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A);
        }

        public static boolean isUp() {
            return Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W);
        }

        public static boolean isRight() {
            return Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D);
        }

        public static boolean isDown() {
            return Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S);
        }
 }
