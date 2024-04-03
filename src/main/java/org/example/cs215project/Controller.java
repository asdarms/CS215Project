package org.example.cs215project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextArea inputField;
    @FXML
    private ChoiceBox compressOption;
    @FXML
    private Button compressButton;
    @FXML
    private TextArea compressField;
    @FXML
    private ChoiceBox decompressOption;
    @FXML
    private Button decompressButton;
    @FXML
    private TextArea decompressField;
    @FXML
    private Label timeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compressOption.getItems().addAll("Brute Force", "Transform and Conquer", "Greedy Technique");
        compressOption.setValue("Select method...");
        decompressOption.getItems().addAll("Brute Force", "Transform and Conquer", "Greedy Technique");
        decompressOption.setValue("Select method...");
    }

    @FXML
    protected void onCompressButtonClick() {
        long beginTime = System.currentTimeMillis();
        switch (compressOption.getValue().toString()) {
            case "Brute Force":
                compressField.setText(Methods.bruteForceCompress(inputField.getText()));
                break;
            case "Transform and Conquer":
                compressField.setText(Methods.transformAndConquerCompress(inputField.getText()));
                break;
            case "Greedy Technique":
                compressField.setText(Methods.greedyTechniqueCompress(inputField.getText()));
                break;
        }
        updateTime(System.currentTimeMillis() - beginTime);
    }

    @FXML
    protected void onDecompressButtonClick() {
        long beginTime = System.currentTimeMillis();
        switch (decompressOption.getValue().toString()) {
            case "Brute Force":
                decompressField.setText(Methods.bruteForceDecompress(compressField.getText()));
                break;
            case "Transform and Conquer":
                decompressField.setText(Methods.transformAndConquerDecompress(compressField.getText()));
                break;
            case "Greedy Technique":
                decompressField.setText(Methods.greedyTechniqueDecompress(compressField.getText()));
                break;
        }
        updateTime(System.currentTimeMillis() - beginTime);
    }

    @FXML
    protected void updateTime(long time) {
        timeLabel.setText("Time: " + time + " ms");
    }

}