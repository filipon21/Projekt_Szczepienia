package edu.ib.projekt_szczepienia;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField dateBirthTextField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField numberPhoneTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordTextField;


    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void registerButtonOnAction(ActionEvent event) {

        if (setPasswordTextField.getText().equals(confirmPasswordTextField.getText())) {
            registerUser();
            confirmPasswordTextField.setText("");
            registrationMessageLabel.setText("Zarejestrowano się poprawnie!");

        } else {
            confirmPasswordTextField.setText("Hasła różnią się!");

        }

    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"closeButton\" was not injected: check your FXML file 'register.fxml'.";
        assert confirmPasswordTextField != null : "fx:id=\"confirmPasswordTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert dateBirthTextField != null : "fx:id=\"dateBirthTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert firstnameTextField != null : "fx:id=\"firstnameTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert lastnameTextField != null : "fx:id=\"lastnameTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert numberPhoneTextField != null : "fx:id=\"numberPhoneTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert peselTextField != null : "fx:id=\"peselTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'register.fxml'.";
        assert setPasswordTextField != null : "fx:id=\"setPasswordTextField\" was not injected: check your FXML file 'register.fxml'.";

    }

    public void registerUser() {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String pesel = peselTextField.getText();
        String phoneNumber = numberPhoneTextField.getText();
        String password = setPasswordTextField.getText();
        String dateBirth = dateBirthTextField.getText();

        String insertFields = "INSERT INTO pacjenci(pesel, passwordd, imie, nazwisko, numer_telefonu, data_urodzenia) VALUES('";
        String insertValues = pesel + "','" + password + "','" + firstname + "','" + lastname + "','" + phoneNumber + "','" + dateBirth +"')";
        String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("Zarejestrowano się poprawnie!");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
