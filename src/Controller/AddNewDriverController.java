package Controller;

import Module.Driver;
import Module.Vehicle;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class AddNewDriverController {

    static ArrayList<Driver> driverArrayList = new ArrayList();

    public TextField txtDrivingLicenseNo;
    public TextField txtDriverName;
    public TextField txtNIC;
    public TextField txtContactNo;
    public JFXTextArea txtAddress;
    public AnchorPane AddDriverContext;
    double x=0;
    double y=0;

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

    public void closeWindow1(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) AddDriverContext.getScene().getWindow();
            stage.close();
        }catch (Exception e) {}
    }

    public void closeWindow(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) AddDriverContext.getScene().getWindow();
            stage.close();
        }catch (Exception e) {}
    }

    public void add(ActionEvent actionEvent) {
        try {
            if (driverArrayList.size()<=15){
                if (!txtDriverName.getText().equals("") && !txtContactNo.getText().equals("") && !txtNIC.getText().equals("") && !txtDrivingLicenseNo.getText().equals("") && !txtAddress.getText().equals("")) {
                    try {
                        Driver driver = new Driver(txtDriverName.getText(), txtNIC.getText(), txtDrivingLicenseNo.getText(), txtAddress.getText(), txtContactNo.getText());
                        if (driverArrayList.add(driver) || txtDriverName.getLength() != 0) {
                            txtDriverName.clear();
                            txtNIC.clear();
                            txtDrivingLicenseNo.clear();
                            txtAddress.clear();
                            txtContactNo.clear();
                            URL resource = getClass().getResource("../view/DriverAddedSuccessfully.fxml");
                            Parent load = FXMLLoader.load(resource);
                            Scene scene = new Scene(load);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();
                        }
                    } catch (NumberFormatException e) {}
                } else {
                    URL resource = getClass().getResource("../view/PleaseTryAgain.fxml");
                    Parent load = FXMLLoader.load(resource);
                    Scene scene = new Scene(load);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                }
            }else{
                new Alert(Alert.AlertType.WARNING, "You can only add 16 drivers", ButtonType.OK).show();
            }
        }catch(Exception e){}
    }

    public void goToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void goToNIC(ActionEvent actionEvent) {
        txtNIC.requestFocus();
    }

    public void goToDrivingLicenseNo(ActionEvent actionEvent) {
        txtDrivingLicenseNo.requestFocus();
    }

    public void goToAddButton(ActionEvent actionEvent) {
        add(actionEvent);
    }
}
