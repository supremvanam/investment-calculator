package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

public class Controller extends Application {
    // as per the instructions, I changed variable names to camel case.
    private final Label title = new Label("Investment Calculator");

    private final TextField investmentAmountFX = new TextField();
    private final TextField numberOfYearsFX = new TextField();
    private final TextField annualInterestRateFX = new TextField();
    private final TextField futureValueFX = new TextField();

    private final Button calculateFX = new Button("Calculate");
    private final Button resetButtonFX = new Button("Reset");
    private final Button fillDefaultValues = new Button("Auto-fill Random Values");

    private final Label developer = new Label("Developed by Suprem Vanam");

    @Override
    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        VBox vBox = new VBox();

        // Setting the styles
        title.setFont(Font.font ("Verdana", FontWeight.EXTRA_BOLD, 25));
        developer.setAlignment(Pos.BOTTOM_CENTER);
        developer.setStyle("-fx-text-fill: #A9ACAE");
        developer.setPadding(new Insets(20));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        // Adding to the layout
        // First element
        vBox.getChildren().add(title);

        // Second element
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(40, 10, 30, 10));

        gridPane.add(new Label("Investment Amount"),0,0);
        gridPane.add(investmentAmountFX, 1, 0);
        gridPane.add(new Label("Number of years"), 0, 1);
        gridPane.add(numberOfYearsFX, 1, 1);
        gridPane.add(new Label("Annual Interest Rate"), 0, 2);
        gridPane.add(annualInterestRateFX, 1, 2);
        gridPane.add(new Label("Future Value"), 0, 3);
        gridPane.add(futureValueFX, 1, 3);
        vBox.getChildren().addAll(gridPane);

        // Third element
        vBox.getChildren().addAll(calculateFX, fillDefaultValues, resetButtonFX, developer);

        calculateFX.setMinWidth(280);
        fillDefaultValues.setMinWidth(280);
        resetButtonFX.setMinWidth(280);

        resetButtonFX.setStyle("-fx-text-fill: #ff4f4f");
        calculateFX.setStyle("-fx-background-color: MediumSeaGreen;"+"-fx-text-fill: White;");

        gridPane.setAlignment(Pos.CENTER);
        futureValueFX.setEditable(false);
        GridPane.setHalignment(calculateFX, HPos.CENTER);
        GridPane.setValignment(calculateFX, VPos.TOP);

        calculateFX.setOnAction(e -> {
            try
            {
                calculateFutureValue();
            } catch (NumberFormatException numberFormatException){
                if (numberFormatException.getMessage().equals("empty String")) {
                    PopupAlertWindow.displayPopup("Empty Field", "Please don't leave any of the fields blank");
                }
                else {
                    PopupAlertWindow.displayPopup("Invalid Format", "The data you entered must be a number\n"+numberFormatException.getMessage());
                }

            } catch (Exception exception){
                PopupAlertWindow.displayPopup("Error", "You have an error. Please check the values you entered and try again.");
            }
        });

        resetButtonFX.setOnAction(e -> {
            investmentAmountFX.setText("");
            numberOfYearsFX.setText("");
            annualInterestRateFX.setText("");
            futureValueFX.setText("");
        });

        fillDefaultValues.setOnAction(e -> {
            investmentAmountFX.setText(""+Math.ceil(Math.random()*2000));
            numberOfYearsFX.setText(""+(int)(Math.ceil(Math.random()*10)));
            annualInterestRateFX.setText(""+Math.ceil(Math.random()*15));
            futureValueFX.setText("");
        });

        Scene scene = new Scene(vBox,450,600);
        primaryStage.setTitle("COMP228 Lab 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void calculateFutureValue(){
        double investmentAmount = Double.parseDouble(investmentAmountFX.getText());
        int years = Integer.parseInt(numberOfYearsFX.getText());
        double annualInterestRate = Double.parseDouble(annualInterestRateFX.getText());

        Loan loan = new Loan(investmentAmount, years, annualInterestRate);

        futureValueFX.setText(String.format("$%.2f", loan.getFutureValue()));
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }

}
