package principal;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Paint.fxml"));
        primaryStage.setTitle("Imagem sem nome - SuperPaint");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.setResizable(false);
        PaintController.setStage(primaryStage);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("logo.png")));
        primaryStage.show();
    }
}
