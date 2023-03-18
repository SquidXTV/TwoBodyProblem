package me.squidxtv.twobodyproblem;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.squidxtv.twobodyproblem.simulation.Simulation;
import me.squidxtv.twobodyproblem.ui.Configuration;

public class App extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private static final String TITLE = "Simulation";
    private Simulation simulation;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        Parent root = new AnchorPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();

        simulation = new Simulation(canvas);

        Configuration configuration = new Configuration(stage, simulation);
        configuration.show();
    }

}
