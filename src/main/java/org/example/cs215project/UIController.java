package org.example.cs215project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {
    @FXML
    private ImageView inputImageView;
    @FXML
    private ImageView outputImageView;
    @FXML
    private ChoiceBox compressOption;
    @FXML
    private Button compressButton;
    @FXML
    private Button selectImageButton;
    @FXML
    private Label summaryLabel;
    @FXML
    private Label inputLabel;
    @FXML
    private Label outputLabel;
    @FXML
    private Stage stage;
    File selectedFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        compressOption.getItems().addAll("Brute Force (RLE)", "Transform and Conquer (DCT)", "Greedy Technique (LZW)");
        compressOption.setValue("Brute Force (RLE)");
    }

    @FXML
    protected void onImageSelectButtonClick() {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(stage);
        inputImageView.setImage(new Image(selectedFile.toURI().toString()));
        inputLabel.setText("Input Image (" + selectedFile.getTotalSpace() + "):");
    }

    @FXML
    protected void onCompressButtonClick() throws IOException {
        long beginTime = System.currentTimeMillis();
        switch (compressOption.getValue().toString()) {
            case "Brute Force (RLE)":
                RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
                runLengthEncoding.compressFile(selectedFile.toString());
                break;
            case "Transform and Conquer (DCT)":
                DiscreteCosineTransform discreteCosineTransform = new DiscreteCosineTransform();
                discreteCosineTransform.prcocessDCTDWTTransformation(selectedFile.toString(), "16384");
                break;
            case "Greedy Technique (LZW)":
                LipmanZivWelch.compressFile(selectedFile.toString());
                break;
        }
        updateTime(System.currentTimeMillis() - beginTime);
    }

    @FXML
    protected void updateTime(long time) {
        String compressText = "";
            //double compressionRatio = (double) outputField.getLength() / inputField.getLength() * 100;
            //compressText = "Compression Ratio: " + compressionRatio + "%";

        //timeLabel.setText("Time: " + time + " ms " + compressText);
    }

}