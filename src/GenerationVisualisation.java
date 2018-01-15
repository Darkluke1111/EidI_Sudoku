/**
 * Created by lg18 on 15.01.2018.
 */
public interface GenerationVisualisation {

    void visualizeFillCell(int xPos, int yPos, int val, long step);

    void visualizeDeleteCell(int xPos, int yPos, long step);

    void visualizeTest(int xPos, int yPos, int xTest, int yTest, boolean possible);
}
