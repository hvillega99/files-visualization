package main;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import window.MainWindow;

/**
 *
 * @author CDHTN
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Creando una instancia de MainWindow
        
        MainWindow window = new MainWindow();
        
        
        Scene scene = new Scene(window.getRoot(),500,180);

        
        
        primaryStage.setTitle("Tree Maping");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setHeight(180);
        primaryStage.setWidth(500);
        primaryStage.setMinHeight(180);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxHeight(180);
        primaryStage.setMaxWidth(500);
        primaryStage.setResizable(false);
        
        
        scene.getStylesheets().clear();
        scene.getStylesheets().add("estilos.css");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
