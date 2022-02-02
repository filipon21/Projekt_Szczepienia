package edu.ib.projekt_szczepienia;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label loginMessageLabel;

    @FXML Button registerButton;


    @FXML
    void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    @FXML
    void registerButtonOnAction(ActionEvent event){
        createAccountStage();
    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {

        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Wprowadź pesel i hasło!");
        }

    }

    void validateLogin() {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();

        String verifyLogin = "Select count(1) FROM pacjenci WHERE pesel = '" + usernameTextField.getText() + "' AND passwordd = '" + enterPasswordField.getText() + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Zalogowano się!!");
                } else {
                    loginMessageLabel.setText("Nieudane logowanie. Spróbuj ponownie!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    ;


    @FXML
    void initialize() {
        assert closeButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'login.fxml'.";
        assert enterPasswordField != null : "fx:id=\"enterPasswordField\" was not injected: check your FXML file 'login.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'login.fxml'.";
        assert loginMessageLabel != null : "fx:id=\"loginMessageLabel\" was not injected: check your FXML file 'login.fxml'.";

    }

    public void createAccountStage() {
        try {
            Parent root =  FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 600, 700));
            registerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
