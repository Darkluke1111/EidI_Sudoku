import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by lg18 on 15.01.2018.
 */
public class Controller implements GenerationVisualisation {

    public SudokuView view;
    private Canvas canvas;
    private GraphicsContext g;
    private final int SCALE = 20;
    private final int DELAY = 100;

    public Controller(SudokuView view) {
        this.view = view;
        canvas = view.canvas;
        g = canvas.getGraphicsContext2D();
    }

    @Override
    public void visualizeFillCell(int xPos, int yPos, int value, long step) {
        System.out.println("Draw fill Cell");
        Platform.runLater(() -> {
            g.setFill(Color.WHITE);
            g.fillRect(xPos * SCALE, yPos * SCALE, SCALE, SCALE);
            g.setFill(Color.BLACK);
            g.fillText(Integer.toString(value), xPos*  SCALE + 2, yPos * SCALE + SCALE - 2);
        });

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visualizeDeleteCell(int xPos, int yPos, long step) {
        System.out.println("Draw delete Cell");
        Platform.runLater(() -> {
            g.setStroke(Color.WHITE);
            g.fillRect(xPos * SCALE, yPos * SCALE, SCALE, SCALE);
        });

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visualizeTest(int xPos, int yPos, int xTest, int yTest, boolean possible) {
        System.out.println("Draw test Cell");
        Platform.runLater(() -> {
            if (possible) {
                g.setStroke(Color.BLUE);
            } else {
                g.setStroke(Color.RED);
            }

            g.strokeRect(xPos * SCALE, yPos * SCALE, SCALE, SCALE);
            g.strokeRect(xTest * SCALE, yTest * SCALE, SCALE, SCALE);
        });

        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            g.setStroke(Color.WHITE);
            g.set
            g.strokeRect(xTest * SCALE, yTest * SCALE, SCALE, SCALE);
            g.strokeRect(xPos * SCALE, yPos * SCALE, SCALE, SCALE);
        });
    }
}
