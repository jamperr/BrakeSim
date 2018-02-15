import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
  @Override
  public void start(Stage primaryStage)
  {
    BorderPane root = new BorderPane();
    root.setPrefSize(900,500);
    SimulationWorker simulationWorker = new SimulationWorker();
    Dashboard dashboard = new Dashboard(10,simulationWorker);
    dashboard.setAlignment(Pos.CENTER);
    dashboard.setPadding(new Insets(10,10,10,10));
    SimulationArea simulationArea = new SimulationArea();
    root.setLeft(dashboard);
    root.setCenter(simulationArea);
    Scene scene = new Scene(root);
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
    {
      @Override
      public void handle(WindowEvent event)
      {
        simulationWorker.setRunning(false);
        simulationWorker.setTerminate(true);
        while(simulationWorker.isAlive()){}
      }
    });
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args)
    {
        launch(args);
    }
}
