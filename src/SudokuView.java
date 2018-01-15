import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Created by lg18 on 15.01.2018.
 */
public class SudokuView {

    public Scene scene;

    private BorderPane root;

    Canvas canvas;

    public SudokuView() {
        generateView();
    }

    private void generateView() {

        root = new BorderPane();
        canvas = new Canvas(600,600);
        root.setCenter(canvas);
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        scene = new Scene(root);


    }

    public void connectHandlers(Controller c) {
        //TODO
    }
}
