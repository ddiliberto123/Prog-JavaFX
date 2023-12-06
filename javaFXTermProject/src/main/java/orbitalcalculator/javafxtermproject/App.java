package orbitalcalculator.javafxtermproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App for Orbital Calculations using Euler's method
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Creates the JavaFX program and sets the stage/root
     * @param stage The stage the program will use
     * @throws IOException Throws if the fxml isn't found
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainMenu"), 640, 480);
        stage.setTitle("Orbital Calculator");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/calc.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Launches the JavaFX program
     * @param args The args
     */
    public static void main(String[] args) {
        launch();
    }

}