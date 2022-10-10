package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CreateLevelFromFile {

    private List<String> lines = new ArrayList<>();

    public CreateLevelFromFile(String PathLevel) {
        try {
            File file = new File(PathLevel);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String oneLine = reader.readLine();

            while (oneLine != null) {
                lines.add(0, oneLine);
                oneLine = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GridPoint2 TankCoordinatesFromFile() {
        GridPoint2 tankCoordinates = new GridPoint2();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'X') {
                    tankCoordinates = (new GridPoint2(j, i));

                }
            }
        }
        return tankCoordinates;
    }

    public List<GridPoint2> TreeCoordinatesFromFile(){
        List<GridPoint2> treeCoordinates = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == 'T'){
                    treeCoordinates.add(new GridPoint2(j, i));
                }
            }
        }
        return treeCoordinates;
    }


}

