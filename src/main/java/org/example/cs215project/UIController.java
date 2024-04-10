package org.example.cs215project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    @FXML
    private TextArea inputField;
    @FXML
    private ChoiceBox compressOption;
    @FXML
    private TextArea outputField;
    @FXML
    private Label timeLabel;

    //adds dropdown menu options and sets default
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compressOption.getItems().addAll("Brute Force", "Transform and Conquer", "Greedy Technique");
        compressOption.setValue("Brute Force");
    }

    //handles what happens when the compress button is clicked depending on the dropdown choice
    @FXML
    protected void onCompressButtonClick() {
        long beginTime = System.currentTimeMillis();
        switch (compressOption.getValue().toString()) {
            case "Brute Force":
                outputField.setText(RunLengthEncoding.compress(inputField.getText()));
                break;
            case "Transform and Conquer":
                outputField.setText(BurrowsWheelerTransform.compress(inputField.getText()));
                break;
            case "Greedy Technique":
                outputField.setText(LempelZivWelch.compress(inputField.getText()));
                break;
        }
        updateTime(System.currentTimeMillis() - beginTime, "Compress");
    }

    //handles what happens when the compress button is clicked depending on the dropdown choice
    @FXML
    protected void onDecompressButtonClick() {
        long beginTime = System.currentTimeMillis();
        switch (compressOption.getValue().toString()) {
            case "Brute Force":
                outputField.setText(RunLengthEncoding.decompress(inputField.getText()));
                break;
            case "Transform and Conquer":
                outputField.setText(BurrowsWheelerTransform.decompress(inputField.getText()));
                break;
            case "Greedy Technique":
                outputField.setText(LempelZivWelch.decompress(inputField.getText()));
                break;
        }
        updateTime(System.currentTimeMillis() - beginTime, "Decompress");
    }

    //this method updates the text at the bottom of the application to show the time the last operation took and the compression ratio based on which operation was done
    @FXML
    protected void updateTime(long time, String mode) {
        String compressText = "";
        if (mode.equals("Compress")) {
            double compressionRatio = (double) outputField.getLength() / inputField.getLength() * 100;
            compressText = "Compression Ratio: " + compressionRatio + "%";
        } else if (mode.equals("Decompress")) {
            double compressionRatio = (double) inputField.getLength() / outputField.getLength() * 100;
            compressText = "Compression Ratio: " + compressionRatio + "%";
        }
        timeLabel.setText("Time: " + time + " ms " + compressText);
    }

}