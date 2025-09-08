package com.studentregistration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    private static final String FXML_PATH = "/com/studentregistration/view/student_form.fxml";
    private static final String CSS_PATH  = "/com/studentregistration/view/styles.css";
    private static final String ICON_PATH = "/com/studentregistration/view/app-icon.png"; // optional

    @Override
    public void start(Stage stage) throws Exception {
        // --- Load FXML (fail fast with a helpful message) ---
        URL fxmlUrl = getClass().getResource(FXML_PATH);
        if (fxmlUrl == null) {
            throw new IllegalStateException("FXML not found at: " + FXML_PATH);
        }
        FXMLLoader loader = new FXMLLoader(fxmlUrl);

        Scene scene = new Scene(loader.load(), 900, 600);

        // --- Try to apply stylesheet (optional) ---
        URL cssUrl = getClass().getResource(CSS_PATH);
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("No stylesheet found at " + CSS_PATH + " (skipping).");
        }

        // --- Optional app icon (ignore if missing) ---
        URL iconUrl = getClass().getResource(ICON_PATH);
        if (iconUrl != null) {
            stage.getIcons().add(new Image(iconUrl.toExternalForm()));
        }

        // --- Stage config ---
        stage.setTitle("Student Registration System");
        stage.setScene(scene);
        stage.setMinWidth(820);
        stage.setMinHeight(520);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
