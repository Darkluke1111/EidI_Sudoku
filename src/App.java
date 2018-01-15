import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by lg18 on 15.01.2018.
 */
public class App extends Application {
    SudokuView view;
    Controller c;

    @Override
    public void start(Stage primaryStage) throws Exception {
        view = new SudokuView();
        c = new Controller(view);
        view.connectHandlers(c);
        primaryStage.setScene(view.scene);
        primaryStage.show();

        SudokuGenerator sg = new SudokuGenerator(9,c);
        new Thread(sg::generateSudoku).start();

    }

    public static void main(String[] args) {
        launch();
    }
}
