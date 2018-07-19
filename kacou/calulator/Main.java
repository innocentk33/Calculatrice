package kacou.calulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/***
 * Auteur : Kacou Innocent
 * Email : innocentk33@gmail.com
 * Date : 15/05/2018
 * ('_')
 * */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kacoulatrice ");
        primaryStage.setScene(new Scene(root, 375, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
