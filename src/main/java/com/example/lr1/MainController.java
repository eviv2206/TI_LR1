package com.example.lr1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.security.InvalidParameterException;

public class MainController {


    @FXML
    private Button BtnCipher;

    @FXML
    private Button BtnDecipher;

    @FXML
    private RadioButton DecimationRadioBtn;

    @FXML
    private TextArea KeyArea;

    @FXML
    private Menu MenuFile;

    @FXML
    private MenuItem MenuFileOpen;

    @FXML
    private MenuItem MenuFileQuit;

    @FXML
    private TextArea ResultArea;

    @FXML
    private TextArea TextArea;

    @FXML
    private RadioButton VigenereRadioBtn;

    @FXML
    private ToggleGroup method;

    @FXML
    private MenuItem FileSaveAs;

    @FXML
    void cipher(ActionEvent event) {
        ResultArea.clear();
        if (DecimationRadioBtn.isSelected()) {
            try {
                ResultArea.setText(CaesarDecimation.encrypt(TextArea.getText(), KeyArea.getText()));
            } catch (InvalidParameterException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else if (VigenereRadioBtn.isSelected()) {
            try {
                ResultArea.setText(VigenereDirect.encrypt(TextArea.getText(), KeyArea.getText()));
            } catch (InvalidParameterException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Choose the method of encryption");
            alert.showAndWait();
        }
    }

    @FXML
    void decipher(ActionEvent event) {
        ResultArea.clear();
        if (DecimationRadioBtn.isSelected()) {
            try {
                ResultArea.setText(CaesarDecimation.decrypt(TextArea.getText(), KeyArea.getText()));
            } catch (InvalidParameterException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else if (VigenereRadioBtn.isSelected()) {
            try{
                ResultArea.setText(VigenereDirect.decrypt(TextArea.getText(), KeyArea.getText()));
            } catch (InvalidParameterException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Choose the method of decryption");
            alert.showAndWait();
        }
    }

    @FXML
    void fileOpen(ActionEvent event) throws IOException {
        Stage stage = (Stage) BtnCipher.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            FileReader reader = null;
            BufferedReader br = null;
            try {
                reader = new FileReader(selectedFile.getAbsolutePath());
                br = new BufferedReader(reader);

            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
            try {
                StringBuilder sb = new StringBuilder();
                String str;
                while ((str = br.readLine()) != null){
                    sb.append(str + '\n');
                }
                TextArea.setText(sb.toString());
            } catch (IOException e) {
                System.err.format("Ошибка чтения файла: %s%n", e);
            }
        }
    }

    @FXML
    void fileSaveAs(ActionEvent event) {
        Stage stage = (Stage) BtnCipher.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                FileWriter writer = new FileWriter(selectedFile.getAbsolutePath(), false);
                writer.write(ResultArea.getText());
                writer.close();
                System.out.println("Data saved to file.");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e){
                System.err.println("Запись пустого значения");
            }
        }
    }

    @FXML
    void quit(ActionEvent event) {
        Platform.exit();
    }

}
