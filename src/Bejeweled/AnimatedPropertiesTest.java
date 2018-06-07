/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bejeweled;

import java.net.URL;
import java.util.concurrent.Callable;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimatedPropertiesTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		final StackPane root = new StackPane();
		final Button button = new Button("Click Me");
		
		final Color startColor = Color.web("#e08090");
		final Color endColor = Color.web("#80e090");
		
		final ObjectProperty<Color> color = new SimpleObjectProperty<Color>(startColor);

		// String that represents the color above as a JavaFX CSS function:
		// -fx-body-color: rgb(r, g, b);
		// with r, g, b integers between 0 and 255
		final StringBinding cssColorSpec = Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return String.format("-fx-body-color: rgb(%d, %d, %d);", 
                        (int) (256*color.get().getRed()), 
                        (int) (256*color.get().getGreen()), 
                        (int) (256*color.get().getBlue()));
            }
        }, color);
		
		// bind the button's style property
        button.styleProperty().bind(cssColorSpec);
        
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(color, startColor)),
                new KeyFrame(Duration.seconds(1), new KeyValue(color, endColor)));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               timeline.play();
               //clicar para mudar de cor
            }
        });

        // Create a rotating rectangle and set it as the graphic for the button
        Rectangle rotatingRect = new Rectangle(50,50,50,50);
        Image img = new Image("/testing/gem7.png");
        rotatingRect.setFill(new ImagePattern(img));
        
        final Pane rectHolder = new Pane();
        rectHolder.setMinSize(100, 100);
        rectHolder.setPrefSize(100, 100);
        rectHolder.setMaxSize(100, 100);
        rectHolder.getChildren().add(rotatingRect);
        final RotateTransition rotate = new RotateTransition(Duration.seconds(1), rotatingRect);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        
        button.setGraphic(rectHolder);
        final TranslateTransition translateTransition
                    = new TranslateTransition(Duration.millis(2000), rotatingRect);
        translateTransition.setFromX(0);
            translateTransition.setToX(100);
          
            translateTransition.setCycleCount(1);
            translateTransition.setAutoReverse(true);
            
           
 
 
        // make the rectangle rotate when the mouse hovers over the button
		button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                rotate.play();
                translateTransition.play();
            }
        });
		
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotate.stop();
                rotatingRect.setRotate(0);
            }
        });
		
		
		root.getChildren().addAll(button);
		
		final Scene scene = new Scene(root, 300, 175);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}