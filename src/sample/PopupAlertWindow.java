package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupAlertWindow {

    public static void displayPopup(String title, String message) {
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(300);
        stage.setMinHeight(200);

        Label label = new Label();
        label.setText(message);
        label.setFont(Font.font("Times New Roman", 15.0));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setMinWidth(250);
        closeButton.setOnAction(e -> stage.close());

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(12);
        gridPane.add(label, 1,1);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(closeButton, 1,3);


        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
