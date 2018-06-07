/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bejeweled;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author joao_
 */
public class Bejeweled extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {Pane root = FXMLLoader.load(getClass().getResource("layout2.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Bejeweled");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
