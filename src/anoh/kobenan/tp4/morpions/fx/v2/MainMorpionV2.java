package anoh.kobenan.tp4.morpions.fx.v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMorpionV2 extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            primaryStage.setTitle("Morpions V2");
            FXMLLoader loader = new FXMLLoader() ;
            loader.setLocation(anoh.kobenan.tp4.morpions.fx.v2.MainMorpionV2.class.getResource("morpionV2.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root,400,400);
            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}