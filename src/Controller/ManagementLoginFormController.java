package Controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;


public class ManagementLoginFormController {

    public AnchorPane loginContext;
    public TextField txtUserName;
    public PasswordField txtPassword;
    double x = 0;
    double y = 0;

    public void dragged(MouseEvent mouseEvent) {
        try {
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        }catch (Exception e){}
    }

    public void pressed(MouseEvent mouseEvent) {
        x=mouseEvent.getSceneX();
        y=mouseEvent.getSceneY();
    }

    public void cancel(ActionEvent actionEvent) throws IOException {

        try {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.close();

        }catch (Exception e){}
    }

    public void clicked(MouseEvent mouseEvent) throws IOException {

        try {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.close();

        }catch (Exception e){}
    }

    public void Clicked1(MouseEvent mouseEvent) throws IOException {
        try {
            Stage stage = (Stage) loginContext.getScene().getWindow();
            stage.close();

        }catch (Exception e) {}
    }

    public void goToManagementActivities(ActionEvent actionEvent) throws IOException {
        try {
            if(txtUserName.getText().equals("admin") && txtPassword.getText().equals("12345")) {
                Stage stage = (Stage) loginContext.getScene().getWindow();
                stage.close();
                URL resource = getClass().getResource("../view/ManagementActivities.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.show();
            }else{
                new Alert(Alert.AlertType.WARNING, "UserName or Password is incorrect..please try again with correct login details..! ", ButtonType.OK).show();
            }
        }catch (Exception e) {}

    }

    public void goToPasswordTxt(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void goToLoginButton(ActionEvent actionEvent) throws Exception {
        goToManagementActivities(actionEvent);
    }
}
