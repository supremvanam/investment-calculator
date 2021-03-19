package sample;

// Dear Professor, I was unable to install the required components to use JOptionPane (javax.swing) on my MacBook.
// For that reason, I built a pop-up window by myself to use it as an alert dialog box.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Controller extends Application {

    // as per the instructions, I used variable names with the camel case.
    private final Label headerFX = new Label("Investment Calculator");

    private final CheckBox checkBoxFX = new CheckBox("Add User Details");

    private final TextField investmentAmountFX = new TextField();
    private final TextField numberOfYearsFX = new TextField();
    private final TextField annualInterestRateFX = new TextField();
    private final TextField futureValueFX = new TextField();

    private final Label userNameLabelFX = new Label("User Name ");
    private final Label userAddressLabelFX = new Label("User Address ");
    private final TextField userNameFX = new TextField();
    private final TextField userAddressFX = new TextField();

    private final Button calculateFX = new Button("Calculate");
    private final Button fillRandomValuesFX = new Button("Auto-fill Random Values");
    private final Button resetButtonFX = new Button("Reset");

    private final Label footerFX = new Label("Developed by Suprem Vanam");

    GridPane gridPane = new GridPane();
    VBox vBox = new VBox();


    @Override
    public void start(Stage primaryStage){

        // Setting the styles
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 10, 30, 10));

        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        headerFX.setFont(Font.font ("Verdana", FontWeight.EXTRA_BOLD, 25));

        checkBoxFX.setPadding(new Insets(0, 0, 10, 0));

        userNameLabelFX.setVisible(false);
        userNameFX.setVisible(false);
        userAddressLabelFX.setVisible(false);
        userAddressFX.setVisible(false);

        calculateFX.setMinWidth(280);
        calculateFX.setStyle("-fx-background-color: MediumSeaGreen;"+"-fx-text-fill: White;");

        fillRandomValuesFX.setMinWidth(280);

        resetButtonFX.setMinWidth(280);
        resetButtonFX.setStyle("-fx-text-fill: #ff4f4f");
        futureValueFX.setEditable(false);

        footerFX.setAlignment(Pos.BOTTOM_CENTER);
        footerFX.setStyle("-fx-text-fill: #A9ACAE");
        footerFX.setPadding(new Insets(20));


        // Adding to the layout

        // First element
        vBox.getChildren().add(headerFX);

        // Second element
        gridPane.add(checkBoxFX, 0,0);
        gridPane.add(new Label("Investment Amount "),0,1);
        gridPane.add(investmentAmountFX, 1, 1);
        gridPane.add(new Label("Number of years"), 0, 2);
        gridPane.add(numberOfYearsFX, 1, 2);
        gridPane.add(new Label("Annual Interest Rate"), 0, 3);
        gridPane.add(annualInterestRateFX, 1, 3);
        gridPane.add(new Label("Future Value"), 0, 4);
        gridPane.add(futureValueFX, 1, 4);
        gridPane.add(userNameLabelFX, 0, 5);
        gridPane.add(userNameFX, 1, 5);
        gridPane.add(userAddressLabelFX, 0, 6);
        gridPane.add(userAddressFX, 1, 6);
        vBox.getChildren().addAll(gridPane);

        // Third element
        vBox.getChildren().addAll(calculateFX, fillRandomValuesFX, resetButtonFX, footerFX);


        // Functionality

        // User Details Checkbox
        checkBoxFX.setOnAction(e -> {
            try {
                if (checkBoxFX.isSelected()){
                    userNameLabelFX.setVisible(true);
                    userNameFX.setVisible(true);
                    userAddressLabelFX.setVisible(true);
                    userAddressFX.setVisible(true);
                } else {
                    userNameLabelFX.setVisible(false);
                    userNameFX.setVisible(false);
                    userAddressLabelFX.setVisible(false);
                    userAddressFX.setVisible(false);
                    userNameFX.clear();
                    userAddressFX.clear();
                }
            } catch (Exception ex) {
                System.out.println("Exception caught: "+ex.getMessage());
            }
        });

        // Calculate Button
        calculateFX.setOnAction(e -> {
            try
            {
                calculateFutureValue();
            } catch (NumberFormatException numberFormatException){
                if (numberFormatException.getMessage().equals("empty String")) {
                    PopupAlertWindow.displayPopup("Empty Field", "Please don't leave any of the fields blank");
                }
                else {
                    PopupAlertWindow.displayPopup("Invalid Format", "The data you entered must be a number\n"+numberFormatException.getMessage().toLowerCase());
                }

            } catch (Exception exception){
                PopupAlertWindow.displayPopup("Error", "You have an error. Please check the values you entered and try again.");
            }
        });

        // Auto-fill Random Values Button
        fillRandomValuesFX.setOnAction(e -> {
            investmentAmountFX.setText(""+Math.ceil(Math.random()*10000));
            numberOfYearsFX.setText(""+(int)(Math.ceil(Math.random()*10)));
            annualInterestRateFX.setText(""+Math.ceil(Math.random()*15));
            futureValueFX.setText("");
        });

        // Reset Button
        resetButtonFX.setOnAction(e -> {
            investmentAmountFX.clear();
            numberOfYearsFX.clear();
            annualInterestRateFX.clear();
            futureValueFX.clear();
            userNameFX.clear();
            userAddressFX.clear();
        });

        // Adding the scene to the stage
        Scene scene = new Scene(vBox,450,650);
        primaryStage.setTitle("COMP228 Lab 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateFutureValue(){
        double investmentAmount = Double.parseDouble(investmentAmountFX.getText());
        int years = Integer.parseInt(numberOfYearsFX.getText());
        double annualInterestRate = Double.parseDouble(annualInterestRateFX.getText());

        Investment investment = new Investment(investmentAmount, years, annualInterestRate);

        futureValueFX.setText(String.format("$%.2f", investment.getFutureValue()));

        if (!userNameFX.getText().equals("") && !userAddressFX.getText().equals("")) {
            PopupAlertWindow.displayPopup("Hello", "Name: "+ userNameFX.getText().toUpperCase()+"\nAddress: "+ userAddressFX.getText().toUpperCase()+"\nFuture Value "+String.format("$%.2f", investment.getFutureValue()));
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }
}