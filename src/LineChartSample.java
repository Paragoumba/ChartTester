import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Random;


public class LineChartSample extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Line Chart Sample");

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Number of Month");

        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        //defining a series
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("bite");

        Scene scene = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();

        Task task = new Task<Void>(){

            @Override
            protected Void call() throws Exception {

                while (true){

                    Platform.runLater(() -> series.getData().add(new XYChart.Data<>(Math.random() * 10, Math.random() * 10)));

                    Thread.sleep(1000);

                    System.out.println("test");
                }

            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public static void main(String[] args) {

        launch(args);

    }
}