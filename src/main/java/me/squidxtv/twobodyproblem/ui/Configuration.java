package me.squidxtv.twobodyproblem.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.squidxtv.twobodyproblem.math.Vector2D;
import me.squidxtv.twobodyproblem.simulation.*;


public class Configuration extends Stage {

    private static final double WIDTH = 360;
    private static final double HEIGHT = 380;

    private static final double LEFT_PADDING = 20;
    private static final double RIGHT_PADDING = 20;

    private static final double INNER_WIDTH = WIDTH-LEFT_PADDING-RIGHT_PADDING;

    public static final class Input {
        private final TextField x;
        private final TextField y;
        private final TextField velX;
        private final TextField velY;

        private final TextField mass;
        private final ColorPicker color;


        public Input() {
            this.x = new TextField();
            this.y = new TextField();
            
            this.velX = new TextField();
            this.velY = new TextField();

            this.mass = new TextField();
            this.color = new ColorPicker();
            this.color.setMinWidth(INNER_WIDTH/2);

            this.x.setPromptText("x | in m");
            this.y.setPromptText("y | in m");

            this.velX.setPromptText("velX | in m/s");
            this.velY.setPromptText("velY | in m/s");

            this.mass.setPromptText("mass | in kg");
        }

        public int display(GridPane grid, int index, String title) {
            grid.addRow(index++, new Text(title));
            grid.addRow(index++, x, y);
            grid.addRow(index++, velX, velY);
            grid.addRow(index++, mass, color);
            return index;
        }

    }

    public Configuration(Stage root, Simulation simulation) {
        super(StageStyle.UTILITY);
        setTitle("Configuration");
        initModality(Modality.APPLICATION_MODAL);
        initOwner(root);
        setResizable(false);
        setOnCloseRequest(windowEvent -> root.close());

        Scene scene = new Scene(generateWindow(simulation), WIDTH, HEIGHT);
        setScene(scene);
    }

    private static Body parse(Input input, DistanceMode mode) throws NumberFormatException {
        double x = Double.parseDouble(input.x.getText());
        double y = Double.parseDouble(input.y.getText());
        double velX = Double.parseDouble(input.velX.getText());
        double velY = Double.parseDouble(input.velY.getText());

        double mass = Double.parseDouble(input.mass.getText());
        Color color = input.color.getValue();

        Vector2D pos = Vector2D.of(x, y);
        Vector2D vel = Vector2D.of(velX, velY);
        return new Body(pos, vel, mass, color, mode);
    }

    private static VBox generateWindow(Simulation simulation) {
        VBox window = new VBox();
        window.setSpacing(2);
        window.setMinWidth(WIDTH);
        window.setPadding(new Insets(0, RIGHT_PADDING, 0, LEFT_PADDING));

        Button start = new Button("Start");
        Button stop = new Button("Stop");
        start.setMinWidth(INNER_WIDTH);
        stop.setMinWidth(INNER_WIDTH);

        GridPane inputs = new GridPane();
        inputs.setPrefWidth(INNER_WIDTH);
        inputs.setMinWidth(INNER_WIDTH);
        inputs.setMaxWidth(INNER_WIDTH);
        inputs.setVgap(2);

        ColumnConstraints first = new ColumnConstraints();
        ColumnConstraints second = new ColumnConstraints();
        inputs.getColumnConstraints().addAll(first, second);
        first.setPercentWidth(50);
        second.setPercentWidth(50);

        int gridIndex = 0;

        Input input1 = new Input();
        Input input2 = new Input();

        gridIndex = input1.display(inputs, gridIndex, "Body 1:");
        gridIndex = input2.display(inputs, gridIndex, "Body 2:");

        Label slowmode = new Label("Slowmode:");
        CheckBox isSlow = new CheckBox();
        GridPane.setHalignment(isSlow, HPos.CENTER);

        Label time = new Label("TimeMode for 1s in real time:");
        ChoiceBox<TimeMode> timeUnit = new ChoiceBox<>();
        timeUnit.setMinWidth(INNER_WIDTH/2);
        for (TimeMode value : TimeMode.values()) {
            timeUnit.getItems().add(value);
        }
        timeUnit.setValue(TimeMode.HOUR);

        Label distance = new Label("DistanceMode for 1000px:");
        ChoiceBox<DistanceMode> distanceUnit = new ChoiceBox<>();
        distanceUnit.setMinWidth(INNER_WIDTH/2);
        for (DistanceMode value : DistanceMode.values()) {
            distanceUnit.getItems().add(value);
        }
        distanceUnit.setValue(DistanceMode.ASTRONOMICAL_UNIT);

        Label currentTime = new Label("Current time:");
        Label simulationTime = new Label("0");

        Label presetLabel = new Label("Presets:");
        ChoiceBox<Presets> presets = new ChoiceBox<>();
        presets.setMinWidth(INNER_WIDTH/2);
        for (Presets value : Presets.values()) {
            presets.getItems().add(value);
        }
        presets.setValue(Presets.NONE);
        // TODO: 16/03/2023 presets.setOnAction(actionEvent -> );

        inputs.addRow(gridIndex++, slowmode, isSlow);
        inputs.addRow(gridIndex++, time, timeUnit);
        inputs.addRow(gridIndex++, distance, distanceUnit);
        inputs.addRow(gridIndex++, currentTime, simulationTime);
        inputs.addRow(gridIndex, presetLabel, presets);

        for (Node child : inputs.getChildren()) {
            GridPane.setHgrow(child, Priority.ALWAYS);
        }

        start.setOnAction(actionEvent -> {
            try {
                DistanceMode mode = distanceUnit.getValue();
                Body firstBody = parse(input1, mode);
                Body secondBody = parse(input2, mode);
                simulation.start(firstBody, secondBody, simulationTime, timeUnit.getValue(), isSlow.isSelected());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
        stop.setOnAction(actionEvent -> simulation.stop());

        window.getChildren().addAll(start, stop, inputs);
        return window;
    }

}
