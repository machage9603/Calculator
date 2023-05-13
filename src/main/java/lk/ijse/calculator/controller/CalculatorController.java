package lk.ijse.calculator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class CalculatorController {

    @FXML
    private TextField display;
    private double number = 0;
    private String operation = "";
    private boolean selectedOperation = false;

    @FXML
    private void numberButtonOnAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String currentText = display.getText();
        if (selectedOperation) {
            display.setText(currentText + button.getText());
            selectedOperation = false;
        } else {
            display.setText(currentText + button.getText());
        }
    }

    @FXML
    private void operationButtonOnAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        number = Double.parseDouble(display.getText());
        operation = button.getText();
        display.setText(display.getText() + operation);
        selectedOperation = true;
    }

    @FXML
    private void clearButtonOnAction(ActionEvent event) {
        display.setText("");
        number = 0;
        operation = "";
    }

    @FXML
    private void handleDecimalButtonAction(ActionEvent event) {
        String currentText = display.getText();
        if (!currentText.contains(".")) {
            display.setText(currentText + ".");
        }
    }

    @FXML
    private void equalButtonOnAction(ActionEvent event) {
        String[] numbers = display.getText().split(Pattern.quote(operation), 2);
        if (numbers.length < 2) {
            return;
        }
        double secondNumber = Double.parseDouble(numbers[1]);
        double result;

        switch (operation) {
            case "+":
                result = number + secondNumber;
                break;
            case "-":
                result = number - secondNumber;
                break;
            case "*":
                result = number * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    display.setText("Error");
                    return;
                } else {
                    result = number / secondNumber;
                }
                break;
            default:
                return;
        }

        display.setText(String.valueOf(result));
        operation = "";
    }
}
