import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartSample extends Application {

    @FXML
    LineChart<Number, Number> levelLineChart;

    @FXML
    LineChart<Number, Number> powerLineChart;

    @FXML
    LineChart<Number, Number> freqLineChart;

    public void initialize(){

        //defining a series
        XYChart.Series<Number, Number> level = new XYChart.Series<>();
        level.setName("Normes FR");

        levelLineChart.getData().add(level);
        levelLineChart.setAnimated(false);
        levelLineChart.getXAxis().setLabel("Temps (ms)");
        levelLineChart.getYAxis().setLabel("Niveau (dbm)");

        //level.getNode().setStyle("-fx-stroke: black;");

        XYChart.Series<Number, Number> power = new XYChart.Series<>();
        power.setName("Normes FR");

        powerLineChart.getData().add(power);
        powerLineChart.setAnimated(false);
        powerLineChart.getXAxis().setLabel("Temps (ms)");
        powerLineChart.getYAxis().setLabel("Puissance (W)");

        //power.getNode().setStyle("-fx-stroke: black;");

        XYChart.Series<Number, Number> freq = new XYChart.Series<>();
        freq.setName("Normes");

        freqLineChart.getData().add(freq);
        freqLineChart.setAnimated(false);
        freqLineChart.getXAxis().setLabel("Frequence (Hz)");
        freqLineChart.getYAxis().setLabel("Unité arbitraire");

        Thread loop = new Thread(() -> {

            long start = System.currentTimeMillis();
            double dbmMax = 20;

            while (true) {

                long time = System.currentTimeMillis() - start;

                Platform.runLater(() -> {

                    /*if (level.getData().size() > 15) {

                        level.getData().remove(0);

                    }

                    if (power.getData().size() > 15) {

                        power.getData().remove(0);

                    }*/

                    level.getData().add(new XYChart.Data<>(time, dbmMax));
                    power.getData().add(new XYChart.Data<>(time,  Math.pow(10, dbmMax / 10 - 3)));

                });

                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }
            }
        });

        loop.setDaemon(true);
        loop.start();

        freq.getData().add(new XYChart.Data<>(0, 0));
        freq.getData().add(new XYChart.Data<>(2.4, 0));
        freq.getData().add(new XYChart.Data<>(2.4, 1));
        freq.getData().add(new XYChart.Data<>(2.4, 0));
        freq.getData().add(new XYChart.Data<>(5, 0));
        freq.getData().add(new XYChart.Data<>(5, 1));
        freq.getData().add(new XYChart.Data<>(5, 0));
        freq.getData().add(new XYChart.Data<>(10, 0));
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        stage.setTitle("Line Chart Sample");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}